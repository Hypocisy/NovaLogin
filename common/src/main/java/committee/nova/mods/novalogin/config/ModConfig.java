package committee.nova.mods.novalogin.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Config
 *
 * @author cnlimiter
 * @version 1.0
 * @description
 * @date 2024/4/17 下午11:39
 */

public class ModConfig {
    public String getConfigName() {
        return "config";
    }

    @SerializedName("common")
    private CommonConfig common = new CommonConfig();

    @SerializedName("mail")
    private MailConfig mail = new MailConfig();

    @SerializedName("yggdrasil")
    private YggdrasilConfig yggdrasil = new YggdrasilConfig();


    public static class MailConfig {
        @Expose String protocol = "smtp";
        @Expose String host = "smtp.gmail.com";
        @Expose int port = 465;
        @Expose boolean auth = true;
        @Expose boolean ssl = false;
        @Expose String username = "nova";
        @Expose String password = "nova";

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }

        public String getPassword() {
            return password;
        }

        public String getProtocol() {
            return protocol;
        }

        public String getUsername() {
            return username;
        }

        public void setAuth(boolean auth) {
            this.auth = auth;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public void setSsl(boolean ssl) {
            this.ssl = ssl;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }


    public static class CommonConfig {
        @SerializedName("load_local_pwd")
        private boolean loadLocalPwd = true;
        @SerializedName("uuid_trans")
        private boolean uuidTrans = true;
        @SerializedName("out_time")
        private int outTime = 600;
        @SerializedName("re_login_time")
        private int reLoginTime = 2;

        public int getOutTime() {
            return outTime;
        }

        public int getReLoginTime() {
            return reLoginTime;
        }
        public boolean isLoadLocalPwd() {
            return loadLocalPwd;
        }
        public boolean isUuidTrans() {
            return uuidTrans;
        }

        public void setLoadLocalPwd(boolean loadLocalPwd) {
            this.loadLocalPwd = loadLocalPwd;
        }

        public void setOutTime(int outTime) {
            this.outTime = outTime;
        }

        public void setReLoginTime(int reLoginTime) {
            this.reLoginTime = reLoginTime;
        }

        public void setUuidTrans(boolean uuidTrans) {
            this.uuidTrans = uuidTrans;
        }
    }


    public static class YggdrasilConfig {
        @SerializedName("enable")
        private boolean enable = false;
        @SerializedName("api_url")
        private String apiUrl = "https://littleskin.cn/api/yggdrasil";
        @SerializedName("api_name")
        private String apiName = "LittleSkin";

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        public String getApiName() {
            return apiName;
        }

        public String getApiUrl() {
            return apiUrl;
        }
    }

    public CommonConfig getCommon() {
        return common;
    }

    public MailConfig getMail() {
        return mail;
    }

    public YggdrasilConfig getYggdrasil() {
        return yggdrasil;
    }
}
