package com.manageplat.admin.realm;

import com.manageplat.admin.dao.AdminUserDao;
import com.manageplat.admin.domain.AdminUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 描述:
 * author:  吴仕涛
 * 创建日期: 2017-03-02 9:23
 * 修改日期：
 * 修改人:
 * 修改说明:
 **/
public class AdminRealm extends AuthorizingRealm {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AdminUserDao adminUserDao;

    /**
     * 登录验证的方法
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        String username = userToken.getUsername();

        //获取用户登录信息
        AdminUser user = adminUserDao.findByLoginName(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //账户未启用状态的验证
//        if("0".equals(user.getDelTag())){
//            throw new AuthenticationException("账户未启用，请联系管理员");
//        }
        // 登录名
        Object principal = username;
        //登录密码
        Object credentials = user.getPassWord();
        //调用父类的方法获取的realmName
        String realmName = getName();
        //进行秘密验证  密码验证的方法 使用MD5 或者SHA1 shiro中配置
        //4). 盐值.
         ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //返回验证的信息
        this.setSession("currentUser", user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, credentials, realmName);
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, credentials, credentialsSalt, realmName);
        return info;
    }

    /**
     * 授权认知的方法
     *  shiro 权限管理
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AdminUser user = (AdminUser) principals.getPrimaryPrincipal();
        AdminUser adminUser = adminUserDao.findOne(user.getId());
        adminUser.getRole().getPermissions().forEach(tmp -> {
                    if (tmp.getModCode()!=null && !tmp.getModCode().isEmpty())
                        authorizationInfo.addStringPermission(tmp.getModCode());
                }
        );
        return authorizationInfo;
    }

    /**
     * 保存登录名
     */
    private void setSession(Object key, Object value) {
        Session session = getSession();
        log.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
        if (null != session) {
            session.setAttribute(key, value);
        }
    }

    private Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
