package com.business.hall.sys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018/3/27 23:27
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class MybatisPlusConfig {

    private static final Log log = LogFactory.getLog(DruidConfig.class);

    /***
     * plus 的性能优化
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * @Description : mybatis-plus分页插件
     * ---------------------------------
     * @Author : Liang.Guangqing
     * @Date : Create in 2017/9/19 13:59
     */
    /*@Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }*/



    //    mybatisPlus全局配置
    @Bean(name = "globalConfig")
    public GlobalConfiguration globalConfig(
            @Value("${mybatisPlus.globalConfig.id-type}") Integer idType, //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
            @Value("${mybatisPlus.globalConfig.field-strategy}") Integer fieldStrategy, //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
            @Value("${mybatisPlus.globalConfig.db-column-underline}") Boolean dbColumnUnderline, //驼峰下划线转换
            @Value("${mybatisPlus.globalConfig.refresh-mapper}") Boolean refreshMapper, //刷新mapper 调试神器
            @Value("${mybatisPlus.globalConfig.capital-mode}") Boolean capitalMode, //数据库大写下划线转换
            @Value("${mybatisPlus.globalConfig.logic-delete-value}") String logicDeleteValue, //逻辑删除配置
            @Value("${mybatisPlus.globalConfig.logic-not-delete-value}") String logicNotDeleteValue //逻辑删除配置
    ) {
        log.info("初始化GlobalConfiguration");
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        if ( idType!=null) {
            globalConfig.setIdType(idType);  //主键类型
        }
        if ( fieldStrategy!=null) {
            globalConfig.setFieldStrategy(fieldStrategy); //字段策略
        }
        if ( dbColumnUnderline!=null ) {
            globalConfig.setDbColumnUnderline(dbColumnUnderline);  //驼峰下划线转换
        }
        if ( refreshMapper!=null ) {
            globalConfig.setRefresh(refreshMapper); //刷新mapper 调试神器
        }
        if (capitalMode!=null  ) {
            globalConfig.setCapitalMode(capitalMode); //数据库大写下划线转换
        }
        if ( logicDeleteValue!=null ) {
            globalConfig.setLogicDeleteValue(logicDeleteValue);  //逻辑删除配置
        }
        if ( logicNotDeleteValue!=null ) {
            globalConfig.setLogicNotDeleteValue(logicNotDeleteValue);  //逻辑删除配置
        }
        //配置公共字段自动填写
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        return globalConfig;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "globalConfig")GlobalConfiguration globalConfig,
                                               @Qualifier(value = "basisDataSource")DruidDataSource dataSource,
                                               @Value("${mybatisPlus.configuration.map-underscore-to-camel-case}") Boolean mapUnderscoreToCamelCase, //刷新mapper 调试神器
                                               @Value("${mybatisPlus.configuration.cache-enabled}") Boolean cacheEnabled //数据库大写下划线转换
    ) throws Exception {
        log.info("初始化SqlSessionFactory");
        String mapperLocations = "classpath:mapper/*/*.xml";
        //String configLocation = "classpath:db-ason/mybatis/mybatis-sqlconfig.xml";
        String typeAliasesPackage = "com.business.hall.sys.model,com.business.hall.modules.entity";
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource); //数据源
        sqlSessionFactory.setGlobalConfig(globalConfig); //全局配置

        /*#配置返回数据库(column下划线命名&&返回java实体是驼峰命名)，自动匹配无需as（没开启这个，SQL需要写as： select user_id as userId）
        map-underscore-to-camel-case: true
        cache-enabled: false
        #配置JdbcTypeForNull, oracle数据库必须配置
        #jdbc-type-for-null: 'null'*/
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setMapUnderscoreToCamelCase(mapUnderscoreToCamelCase);
        mybatisConfiguration.setCacheEnabled(cacheEnabled);
        sqlSessionFactory.setConfiguration(mybatisConfiguration); //全局配置

        Interceptor[] interceptor = {new PaginationInterceptor()};
        sqlSessionFactory.setPlugins(interceptor); //分页插件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //自动扫描Mapping.xml文件
            sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
            //sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
            sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //    MyBatis 动态扫描
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        log.info("初始化MapperScannerConfigurer");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        String basePackage = "com.business.hall.sys.dao,com.business.hall.modules.mapper";
        mapperScannerConfigurer.setBasePackage(basePackage);
        return mapperScannerConfigurer;
    }

    //    配置事务管理
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier(value = "basisDataSource") DruidDataSource dataSource) {
        log.info("初始化DataSourceTransactionManager");
        return new DataSourceTransactionManager(dataSource);
    }
}