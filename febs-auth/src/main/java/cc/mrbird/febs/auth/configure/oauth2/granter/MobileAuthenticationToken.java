package cc.mrbird.febs.auth.configure.oauth2.granter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * 手机验证码登陆Token认证类
 * @author J_luo
 */
public class MobileAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public MobileAuthenticationToken(Authentication authenticationToken) {
        super(authenticationToken.getPrincipal(), authenticationToken.getCredentials());
    }
}
