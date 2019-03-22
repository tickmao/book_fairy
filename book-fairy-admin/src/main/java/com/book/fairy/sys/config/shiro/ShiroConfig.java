package com.book.fairy.sys.config.shiro;

import com.alibaba.fastjson.JSON;
import com.book.fairy.sys.constants.UserConstants;
import com.book.fairy.filter.LogoutFilter;
import com.book.fairy.filter.RestfulFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 * 
 * @author yz-he
 *
 */
@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //  securityManager: 安全管理器，主体进行认证和授权都是通过securityManager进行
		//  在ShiroFilterFactoryBean添加过滤器
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 配置认证通过可以访问的地址
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/sys/login/**", "anon");
		filterChainDefinitionMap.put("/files/*", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger-resources", "anon");
		filterChainDefinitionMap.put("/v2/api-docs", "anon");
		filterChainDefinitionMap.put("/swagger/**", "anon");
		filterChainDefinitionMap.put("/swagger-resources/configuration/ui","anon");
		filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
		filterChainDefinitionMap.put("/api/**", "anon");

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");

		/*
		 *	过滤链定义，从上向下顺序执行，一般将 /**放在最为下边,这是一个坑呢，一不小心代码就不好使了;
		 *	authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		 */

		filterChainDefinitionMap.put("/**", "authc");

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index.html");

		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/login.html");

		RestfulFilter restfulFilter = new RestfulFilter();

		shiroFilterFactoryBean.getFilters().put("authc", restfulFilter);
		shiroFilterFactoryBean.getFilters().put("logout", logoutFilter);

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		System.out.println(" 执行顺序 4 ：同其他框架一样，都有个切入点，这个核心Filter就是拦截所有请求 ShiroFilterFactoryBean 对象********" + shiroFilterFactoryBean);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager(EhCacheManager cacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());  //设置realm. 　Realms：聚集一个或多个用户安全数据的数据源
		securityManager.setCacheManager(cacheManager);  //注入缓存管理器;
		System.out.println(" 执行顺序 3 ：安全管理器 DefaultWebSecurityManager 对象 ********" + securityManager);
		return securityManager;
	}

	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());

		System.out.println(" 执行顺序 2 ：自定义 Realm 数据源 MyShiroRealm 对象********" + JSON.toJSONString(myShiroRealm));

		/**                {
		 "authenticationCacheName": "com.book.fairy.sys.config.shiro.MyShiroRealm.authenticationCache",
		 "authenticationCachingEnabled": false,
		 "authenticationTokenClass": "org.apache.shiro.authc.UsernamePasswordToken",
		 "authorizationCacheName": "com.book.fairy.sys.config.shiro.MyShiroRealm.authorizationCache",
		 "authorizationCachingEnabled": true,
		 "cachingEnabled": true,
		 "credentialsMatcher": {
		 "hashAlgorithmName": "md5",
		 "hashIterations": 3,
		 "hashSalted": false,
		 "storedCredentialsHexEncoded": true
		 },
		 "name": "com.book.fairy.sys.config.shiro.MyShiroRealm_0",
		 "permissionResolver": {}
		 }
		 *
		 */

		return myShiroRealm;
	}

	/**
	 * 凭证匹配器
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(UserConstants.HASH_ITERATIONS);

		System.out.println("执行顺序 1 ：认证匹配 HashedCredentialsMatcher 对象********" + JSON.toJSONString(hashedCredentialsMatcher));
		/**
		 *   {
		       "hashAlgorithmName": "md5",
		       "hashIterations": 3,
		      "hashSalted": false,
		      "storedCredentialsHexEncoded": true
		 }
		 */
		return hashedCredentialsMatcher;
	}

	/**
	 * Shiro生命周期处理器
	 * 
	 * @return
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
	 * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 
	 * @return
	 */
	@Bean
	@DependsOn({ "lifecycleBeanPostProcessor" })
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}