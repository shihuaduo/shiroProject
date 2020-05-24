package com.ceshi.utils;

import com.ceshi.model.ActiveUser;
import com.ceshi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Override
    public String getName(){
        return "UserRealm";
    }

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ActiveUser activeUser = (ActiveUser) principalCollection.getPrimaryPrincipal();
        List<String> roles1 = activeUser.getRoles();
        roles1.remove("role2");
        List<String> permissions = activeUser.getPermissions();
        permissions.remove("save");
//        List<String> stringList=new LinkedList<String>();
//        List<String> roles=new LinkedList<String>();
//        roles.add("role1");
//        stringList.add("save");
//        stringList.add("delete");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles1);
        return info;

//        log.info("ceshi");
//        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        info.addRole("role1");
//        return info;
    }

    /**
     *
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username =(String)authenticationToken.getPrincipal();
        String password="6b9d72e9feda2d0b2aa87a476d23a9ca";//假如从数据库中拿到的数据是这个
        User user=User.builder()
                .name(username)
                .password(password)
                .build();
        //赋值角色权限
        List<String> roles=new LinkedList<String>();
        roles.add("role1");
        roles.add("role2");
        List<String> permissions=new LinkedList<String>();
        permissions.add("save");
        permissions.add("delete");
        ActiveUser activeUser=ActiveUser.builder()
                .user(user)
                .roles(roles)
                .permissions(permissions)
                .build();
        ByteSource byteSource = ByteSource.Util.bytes("上海");
        //返回认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(activeUser,password,byteSource,getName());

        return simpleAuthenticationInfo;
    }
}
