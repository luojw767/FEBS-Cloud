package cc.mrbird.febs.auth.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author luois
 */
@Component
public class SmsProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 需要调取短信服务 通用 dubbo 或者 fegin
     */
    public String getSmsCode(String mobile,String smsType){
        return passwordEncoder.encode("123456");
    }


}
