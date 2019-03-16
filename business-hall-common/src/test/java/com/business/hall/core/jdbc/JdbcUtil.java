package com.business.hall.core.jdbc;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018/4/24 14:15
 * To change this template use File | Settings | File Templates.
 * Description:
 */

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class JdbcUtil {
    // 表示定义数据库的用户名
    private static String USERNAME;
    // 定义数据库的密码
    private static String PASSWORD;
    // 定义数据库的驱动信息
    private static String DRIVER;
    // 定义访问数据库的地址
    private static String URL;
    // 定义数据库的链接
    private Connection connection;
    // 定义sql语句的执行对象
    private PreparedStatement pstmt;
    // 定义查询返回的结果集合
    private ResultSet resultSet;

    static{
        //加载数据库配置信息，并给相关的属性赋值
        loadConfig();
    }

    /**
     * 加载数据库配置信息，并给相关的属性赋值
     */
    public static void loadConfig() {
        try {
            InputStream inStream = JdbcUtil.class
                    .getClassLoader().getResourceAsStream("dbconfig.properties");

            Properties prop = new Properties();
            prop.load(inStream);
            USERNAME = prop.getProperty("username");
            PASSWORD = prop.getProperty("password");
            DRIVER= prop.getProperty("driverClassName");
            URL = prop.getProperty("url");
        } catch (Exception e) {
            throw new RuntimeException("读取数据库配置文件异常！", e);
        }
    }

    public JdbcUtil() {}

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection() {
        try {
            Class.forName(DRIVER); // 注册驱动
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 获取连接
        } catch (Exception e) {
            throw new RuntimeException("get connection error!", e);
        }
        return connection;
    }

    /**
     * 执行更新操作
     *
     * @param sql
     *            sql语句
     * @param params
     *            执行参数
     * @return 执行结果
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql, List<?> params)
            throws SQLException {
        boolean flag = false;
        int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        // 填充sql语句中的占位符
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     * 执行查询操作
     *
     * @param sql
     *            sql语句
     * @param params
     *            执行参数
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findResult(String sql, List<?> params)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        System.out.println("sql : " + sql);
        System.out.println("params : " + JSON.toJSONString(params));

        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 执行存储过程操作
     *
     * @param sql
     *            sql语句
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> execProcedureResult(String sql)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        Statement stmt = connection.createStatement();

        System.out.println("sql : " + sql);

        resultSet = stmt.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 释放资源
     */
    public void releaseConn() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*public static void main(String[] args) {
        List<Map<String, Object>> result = jdbcUtil.execProcedureResult("exec Proc_Terrace_QueryDiagnosis '650104197502095024',''");

        System.out.println("result : " + JSON.toJSONString(result));
    }*/

    public static void main(String[] args) {
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        try {
            String id = "29";
            String name = "湖北省妇幼保健院";

            List<Object> sqlParams = new ArrayList<Object>();
            StringBuffer sql = new StringBuffer("SELECT * from v_clinic_hospital where 1=1 ");
            /*if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(name)){
                sql.append(" where ");
            }*/
            if(StringUtils.isNotBlank(id)){
                sql.append(" and id_ = ? ");
                sqlParams.add(id);
            }
            if(StringUtils.isNotBlank(name)){
                sql.append(" and name_ = ? ");
                sqlParams.add(name);
            }

            List<Map<String, Object>> result = jdbcUtil.findResult(
                    //"SELECT * from v_clinic_hospital ? ", sqlParams);
                    sql.toString(), sqlParams);

            System.out.println("result : " + JSON.toJSONString(result));

            /*for (Map<String, Object> m : result) {
                System.out.println(m);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
    }

    //
    public List<Map<String, Object>> execProcedureWithParm(String sql, List<String> params)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        pstmt = connection.prepareStatement(sql);
        for(int i=0;i<params.size();i++){
            pstmt.setString(i+1, params.get(i));
        }

        resultSet = pstmt.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }

        return list;
    }

    public String appendSql(String username, String email, String company, String status, Integer duestatus){
        String sql = "select * from user where 1 = 1 ";
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isEmpty(username)){
            sql += " and username like ?";
            params.add("%"+username+"%");
        }
        if(!StringUtils.isEmpty(email)){
            sql +=" and email like ?";
            params.add("%"+email+"%");
        }
        if(!StringUtils.isEmpty(company)){
            sql +=" and company like ?";
            params.add("%"+company+"%");
        }
        if(status != null){
            sql += " and status = ?";
            params.add(status);
        }
        if(duestatus != null){
            if(duestatus == 1){
                sql += " and  date(duedate) < date(now())";
            }else{
                sql += " and  date(duedate) > date(now())";
            }
        }
        sql +=" order by regdate desc";
        return sql;
    }


}