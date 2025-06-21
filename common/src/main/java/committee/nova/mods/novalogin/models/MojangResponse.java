package committee.nova.mods.novalogin.models;

/**
 * MojangResponse
 *
 * @author cnlimiter
 * @version 1.0
 * @description
 * @date 2024/4/12 下午10:25
 */

public class MojangResponse {
	private String id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
