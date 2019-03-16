package com.business.hall.sys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018/3/27 23:21
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class DruidConfig {
    private static final Log log = LogFactory.getLog(DruidConfig.class);

    @Value("${mysqlConn.driverClassName}")
    private String driverClassName;
    @Value("${mysqlConn.url}")
    private String connectionUrl;
    @Value("${mysqlConn.username}")
    private String username;
    @Value("${mysqlConn.password}")
    private String password;
    @Value("${dataSource.initialSize}")
    private Integer initialSize;
    @Value("${dataSource.minIdle}")
    private Integer minIdle;
    @Value("${dataSource.maxActive}")
    private Integer maxActive;
    @Value("${dataSource.maxWait}")
    private Integer maxWait;
    @Value("${dataSource.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${dataSource.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${dataSource.validationQuery}")
    private String validationQuery;
    @Value("${dataSource.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${dataSource.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${dataSource.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${dataSource.poolPreparedStatements}")
    private Boolean poolPreparedStatements;
    @Value("${dataSource.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${dataSource.filters}")
    private String filters;

    @Value("${dataSource.loginUsername}")
    private String loginUsername;
    @Value("${dataSource.loginPassword}")
    private String loginPassword;

    //    配置数据源
    @Bean(name = "basisDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource initDataSource() {
        log.info("初始化DruidDataSource");
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(driverClassName);
        dds.setUrl(connectionUrl);
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setInitialSize(initialSize);
        dds.setMinIdle(minIdle);
        dds.setMaxActive(maxActive);
        dds.setMaxWait(maxWait);
        dds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dds.setValidationQuery(validationQuery);
        dds.setTestWhileIdle(testWhileIdle);
        dds.setTestOnBorrow(testOnBorrow);
        dds.setTestOnReturn(testOnReturn);
        dds.setPoolPreparedStatements(poolPreparedStatements);
        dds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        //dds.setDbType("sqlserver");

        try {
            dds.setFilters(filters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dds;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername",loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword",loginPassword);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}