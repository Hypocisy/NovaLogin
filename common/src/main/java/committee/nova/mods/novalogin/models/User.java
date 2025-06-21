package committee.nova.mods.novalogin.models;

/**
 * LoginPlayer
 *
 * @author cnlimiter
 * @version 1.0
 * @description
 * @date 2024/3/18 2:06
 */
public class User {
    public String name = "";
    public String pwd = "";
    public boolean isPremium = false;
    public boolean isYggdrasil = false;
    public boolean isRegister = false;
    public long lastLeaveTime = 0L;
    public String lastIp = "";

    public String getName() {
        return name;
    }

    public long getLastLeaveTime() {
        return lastLeaveTime;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public boolean isYggdrasil() {
        return isYggdrasil;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public void setLastLeaveTime(long lastLeaveTime) {
        this.lastLeaveTime = lastLeaveTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    public void setYggdrasil(boolean yggdrasil) {
        isYggdrasil = yggdrasil;
    }
}
