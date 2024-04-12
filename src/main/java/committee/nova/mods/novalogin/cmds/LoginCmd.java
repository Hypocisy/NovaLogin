package committee.nova.mods.novalogin.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import committee.nova.mods.novalogin.NovaLogin;
import committee.nova.mods.novalogin.models.LoginUsers;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

/**
 * LoginCmd
 *
 * @author cnlimiter
 * @version 1.0
 * @description
 * @date 2024/4/12 上午11:46
 */
public class LoginCmd {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("login")
                .then(argument("password", StringArgumentType.word())
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayer();
                            String password = StringArgumentType.getString(ctx, "password");
                            String username = player.getGameProfile().getName();

                            if (!NovaLogin.SAVE.isReg(username)) {
                                ctx.getSource().sendSuccess(() -> Component.literal("§cYou're not registered! Use /register instead."), false);
                            } else if (NovaLogin.SAVE.checkPwd(username, password)) {
                                LoginUsers.LoginUser playerLogin = LoginUsers.INSTANCE.get(player);
                                playerLogin.setLogin(true);
                                ctx.getSource().sendSuccess(() -> Component.literal("§aLogged in."), false);
                                if (!player.isCreative()) {
                                    player.setInvulnerable(false);
                                }
                                //player.connection.send(new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_PLING, SoundSource.MASTER, player.position(), 100f, 0f));
                            } else {
                                //player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(new Identifier("minecraft:entity.zombie.attack_iron_door"), SoundCategory.MASTER, player.getPos(), 100f, 0.5f));
                                ctx.getSource().sendSuccess(() -> Component.literal("§cIncorrect password!"), false);
                            }
                            return 1;
                        })));
    }
}
