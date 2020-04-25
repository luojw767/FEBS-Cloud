package cc.mrbird.febs.auth.service.impl;

import cc.mrbird.febs.auth.manager.UserManager;
import cc.mrbird.febs.auth.provider.SmsProvider;
import cc.mrbird.febs.common.core.entity.FebsAuthUser;
import cc.mrbird.febs.common.core.entity.system.SystemUser;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 手机验证码登陆, 用户相关获取
 * @author J_luo
 */
@Slf4j
@Service("mobileUserDetailsService")
public class MobileUserDetailsService implements UserDetailsService {

    @Autowired
    private SmsProvider smsProvider;

    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String mobile) {

        //查询手机号是否存在
        SystemUser sysUser = userManager.findByMobile(mobile);
        if (null != sysUser){
            String permissions = userManager.findUserPermissions(sysUser.getUsername());

            boolean notLock = false;
            if (StrUtil.equals(SystemUser.STATUS_VALID,sysUser.getStatus())){
                notLock = true;
            }

            // 如果为mobile模式，从短信服务中获取验证码（动态密码）
            String credentials = smsProvider.getSmsCode(mobile, "LOGIN");

            FebsAuthUser authUser =new FebsAuthUser(mobile,credentials,true,true,true,notLock,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            BeanUtil.copyProperties(sysUser,authUser,true);

            return authUser;
        }
        throw new UsernameNotFoundException("not find mobile:"+ mobile);

    }
}
