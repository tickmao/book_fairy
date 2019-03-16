/*
package com.business.hall.config.shiro;

import com.business.hall.entity.SysPermission;
import com.business.hall.entity.SysRole;
import com.business.hall.entity.SysUser;
import com.business.hall.mapper.SysPermissionMapper;
import com.business.hall.mapper.SysRoleMapper;
import com.business.hall.service.SysUserService;
import com.business.hall.utils.SpringUtil;
import com.business.hall.utils.UserUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MyShiroRealmCopy extends AuthorizingRealm {
	
	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		String username = usernamePasswordToken.getUsername();
		SysUserService sysUserService = SpringUtil.getBean(SysUserService.class);
		SysUser sysUser = sysUserService.getUser(username);
		if (sysUser == null) {
			throw new UnknownAccountException("用户名不存在");
		}

		if (!sysUser.getPassword()
				.equals(sysUserService.passwordEncoder(new String(usernamePasswordToken.getPassword()), sysUser.getSalt()))) {
			throw new IncorrectCredentialsException("密码错误");
		}

		if (sysUser.getStatus() != SysUser.Status.VALID) {
			throw new IncorrectCredentialsException("无效状态，请联系管理员");
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),
				ByteSource.Util.bytes(sysUser.getSalt()), getName());

		UserUtil.setUserSession(sysUser);

		return authenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.debug("权限配置");

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUser sysUser = UserUtil.getCurrentUser();
		List<SysRole> roles = SpringUtil.getBean(SysRoleMapper.class).listByUserId(sysUser.getId());
		Set<String> roleNames = roles.stream().map(SysRole::getName).collect(Collectors.toSet());
		authorizationInfo.setRoles(roleNames);
		List<SysPermission> permissionList = SpringUtil.getBean(SysPermissionMapper.class).listByUserId(sysUser.getId());
		UserUtil.setPermissionSession(permissionList);
		Set<String> permissions = permissionList.stream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(SysPermission::getPermission).collect(Collectors.toSet());
		authorizationInfo.setStringPermissions(permissions);

		return authorizationInfo;
	}

	*/
/**
	 * 重写缓存key，否则集群下session共享时，会重复执行doGetAuthorizationInfo权限配置
	 *//*

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
		Object object = principalCollection.getPrimaryPrincipal();

		if (object instanceof SysUser) {
			SysUser sysUser = (SysUser) object;

			return "authorization:cache:key:users:" + sysUser.getId();
		}

		return super.getAuthorizationCacheKey(principals);
	}

}
*/
