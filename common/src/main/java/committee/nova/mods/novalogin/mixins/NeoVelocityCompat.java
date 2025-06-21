package committee.nova.mods.novalogin.mixins;

import com.google.gson.JsonParser;
import committee.nova.mods.novalogin.models.MojangResponse;
import committee.nova.mods.novalogin.utils.HttpUtils;
import dev.g_ab.neovelocity.VelocityLoginPacketListenerImpl;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.login.ServerboundCustomQueryAnswerPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.net.HttpURLConnection;

import static committee.nova.mods.novalogin.Const.*;

@Pseudo
@Mixin(VelocityLoginPacketListenerImpl.class)
public abstract class NeoVelocityCompat extends ServerLoginPacketListenerImpl {

	public NeoVelocityCompat(MinecraftServer server, Connection connection, boolean transferred) {
		super(server, connection, transferred);
	}

	@Inject(method = "handleCustomQueryPacket", at =
	@At(value = "FIELD",
			target = "Ldev/g_ab/neovelocity/VelocityLoginPacketListenerImpl;authenticatedProfile:Lcom/mojang/authlib/GameProfile;",
			opcode = 181, // Opcodes.PUTFIELD
			shift = At.Shift.AFTER
	)
	)
	private void handleCustomQueryPacket(ServerboundCustomQueryAnswerPacket packet, CallbackInfo ci) {
		var userName = this.authenticatedProfile.getName();
		if (novaLogin$PlayerIsOnline(userName)&& !this.authenticatedProfile.getProperties().isEmpty()) {
			mojangAccountNamesCache.add(authenticatedProfile.getName());
		} else {
			LOGGER.info("Username '{}' tried to join with an offline UUID", userName);
		}
	}

	@Unique
	private boolean novaLogin$PlayerIsOnline(String playerName) {
		String url = "https://api.mojang.com/users/profiles/minecraft/" + playerName;
		try {
			HttpURLConnection con = HttpUtils.connect(url, 5000, null);
			int code = con.getResponseCode();
			String msg = HttpUtils.getResponseMsg(con);
			con.disconnect();
			if (code == HttpURLConnection.HTTP_OK) {
				var re = GSON.fromJson(JsonParser.parseString(msg), MojangResponse.class);
				StringBuilder uuid = new StringBuilder(re.getId());
				uuid.insert(8, "-");
				uuid.insert(12, "-");
				uuid.insert(16, "-");
				uuid.insert(20, "-");

				LOGGER.info("Player {} has a Mojang account, use online UUID {}", playerName, uuid);
				return true;
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}


}
