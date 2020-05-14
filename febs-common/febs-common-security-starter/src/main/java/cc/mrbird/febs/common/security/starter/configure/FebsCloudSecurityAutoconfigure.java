package cc.mrbird.febs.common.security.starter.configure;

import cc.mrbird.febs.common.core.entity.constant.FebsConstant;
import cc.mrbird.febs.common.core.utils.FebsUtil;
import cc.mrbird.febs.common.security.starter.handler.FebsAccessDeniedHandler;
import cc.mrbird.febs.common.security.starter.handler.FebsAuthExceptionEntryPoint;
import cc.mrbird.febs.common.security.starter.properties.FebsCloudSecurityProperties;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;

/**
 * @author luo
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(FebsCloudSecurityProperties.class)
@ConditionalOnProperty(value = "febs.cloud.security.enable", havingValue = "true", matchIfMissing = true)
public class FebsCloudSecurityAutoconfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public FebsAccessDeniedHandler accessDeniedHandler() {
        return new FebsAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public FebsAuthExceptionEntryPoint authenticationEntryPoint() {
        return new FebsAuthExceptionEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FebsCloudSecurityInteceptorConfigure febsCloudSecurityInteceptorConfigure() {
        return new FebsCloudSecurityInteceptorConfigure();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(FebsConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(FebsConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = FebsUtil.getCurrentTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, FebsConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
        };
    }
}
