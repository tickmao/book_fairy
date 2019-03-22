package com.book.fairy.sys.config.shiro;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.book.fairy.sys.dao.PermissionDao;
import com.book.fairy.sys.dao.RoleDao;
import com.book.fairy.sys.model.Permission;
import com.book.fairy.sys.model.Role;
import com.book.fairy.sys.model.User;
import com.book.fairy.sys.service.UserService;
import com.book.fairy.utils.SpringUtil;
import com.book.fairy.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *  realm: 领域，相当于数据源，通过realm存取认证、授权相关数据
 */

public class MyShiroRealm extends AuthorizingRealm {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	// 用于认证 登录后台页面时
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		String username = usernamePasswordToken.getUsername(); // 获取登录的用户名
		UserService userService = SpringUtil.getBean(UserService.class); //  向spring的beanFactory动态地装载bean
		User user = userService.getUser(username); // 通过用户名查询用户信息
		if (user == null) {
			throw new UnknownAccountException("用户名不存在");
		}

		if (!user.getPassword().equals(userService.passwordEncoder(new String(usernamePasswordToken.getPassword()), user.getSalt()))) {
			throw new IncorrectCredentialsException("密码错误");
		}

		if (user.getStatus() != User.Status.VALID) {
			throw new IncorrectCredentialsException("无效状态，请联系管理员");
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), getName());

		UserUtil.setUserSession(user); // {"login_user":user}

		System.out.println(" 用于认证 SimpleAuthenticationInfo 对象@@@@@@@@@@@@@@@@@@@" + JSON.toJSONString(authenticationInfo));

		return authenticationInfo;
	}

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.debug("权限配置");

        // 编程式：通过写if/else授权代码块完成
		Subject subject = SecurityUtils.getSubject();
		if (null != subject) {
			Session session = subject.getSession();
		}



		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = UserUtil.getCurrentUser();
		List<Role> roles = SpringUtil.getBean(RoleDao.class).listByUserId(user.getId());
		Set<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toSet());
		authorizationInfo.setRoles(roleNames); // 设置角色字符串信息
		List<Permission> permissionList = SpringUtil.getBean(PermissionDao.class).listByUserId(user.getId()); // 根据人员ID获取他的权限信息列表
		UserUtil.setPermissionSession(permissionList);
		Set<String> permissions = permissionList.stream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(Permission::getPermission).collect(Collectors.toSet());
		authorizationInfo.setStringPermissions(permissions); // 设置Permission对象信息

		System.out.println("用于授权 SimpleAuthorizationInf 对象@@@@@@@@@@@@@@@@@@@" + JSON.toJSONString(authorizationInfo));

		return authorizationInfo;
	}

	/**
	 * 重写缓存key，否则集群下session共享时，会重复执行doGetAuthorizationInfo权限配置
	 */
	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
		Object object = principalCollection.getPrimaryPrincipal();

		if (object instanceof User) {
			User user = (User) object;

			return "authorization:cache:key:users:" + user.getId();
		}

		return super.getAuthorizationCacheKey(principals);
	}

}
