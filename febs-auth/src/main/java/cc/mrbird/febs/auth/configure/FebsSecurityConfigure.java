package cc.mrbird.febs.auth.configure;

import cc.mrbird.febs.auth.service.impl.FebsUserDetailService;
import cc.mrbird.febs.auth.service.impl.MobileUserDetailsService;
import cc.mrbird.febs.auth.configure.oauth2.granter.MobileAuthenticationProvider;
import cc.mrbird.febs.auth.filter.ValidateCodeFilter;
import cc.mrbird.febs.common.core.entity.constant.EndpointConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurity配置
 *
 * @author luo
 */
@Order(2)
@EnableWebSecurity
@RequiredArgsConstructor
public class FebsSecurityConfigure extends WebSecurityConfigurerAdapter {

    private final FebsUserDetailService userDetailService;
    private final ValidateCodeFilter validateCodeFilter;
    private final PasswordEncoder passwordEncoder;
    private final MobileUserDetailsService mobileDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers(EndpointConstant.OAUTH_ALL)
                .and()
                .authorizeRequests()
                .antMatchers(EndpointConstant.OAUTH_ALL).authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
        // 设置手机验证码登陆的AuthenticationProvider
        auth.authenticationProvider(mobileAuthenticationProvider());
    }

    /**
     * 创建手机验证码登陆的AuthenticationProvider
     */
    @Bean
    public MobileAuthenticationProvider mobileAuthenticationProvider() {
        MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider(this.mobileDetailsService);
        mobileAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return mobileAuthenticationProvider;
    }
}
