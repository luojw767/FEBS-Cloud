package cc.mrbird.febs.common.security.starter.properties;

import cc.mrbird.febs.common.core.entity.constant.EndpointConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luo
 */
@ConfigurationProperties(prefix = "febs.cloud.security")
public class FebsCloudSecurityProperties {

    /**
     * 是否开启安全配置
     */
    private Boolean enable;
    /**
     * 配置需要认证的uri，默认为所有/**
     */
    private String authUri = EndpointConstant.ALL;
    /**
     * 免认证资源路径，支持通配符
     * 多个值时使用逗号分隔
     */
    private String anonUris;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAuthUri() {
        return authUri;
    }

    public void setAuthUri(String authUri) {
        this.authUri = authUri;
    }

    public String getAnonUris() {
        return anonUris;
    }

    public void setAnonUris(String anonUris) {
        this.anonUris = anonUris;
    }

    @Override
    public String toString() {
        return "FebsCloudSecurityProperties{" +
                "enable=" + enable +
                ", authUri='" + authUri + '\'' +
                ", anonUris='" + anonUris + '\'' +
                '}';
    }
}
