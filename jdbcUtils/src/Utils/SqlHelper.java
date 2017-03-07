package Utils;

import java.sql.*;


/**
 * Created by zhenz on 2017/3/6.
 */
public class SqlHelper {
    private Connection connection=null;
    private String Url="jdbc:sqlserver://localhost:1433;DatabaseName=";
    
    //构造方法：用于注册驱动，连接数据库
    //参数：驱动类名，数据库登录名，数据库密码，该项目数据库名
    public SqlHelper(String DriverName,String user,String password,String Database)
    {
        try {
            Class.forName(DriverName);

            try {
                connection= DriverManager.getConnection(Url+Database,user,password);



            } catch (SQLException e) {
               System.out.print("获取连接失败");
            }


        } catch (ClassNotFoundException e) {
            System.out.print("找不到类");
        }
    }

    
    //得到数据库连接，可以不用
    public Connection getConnection()
    {
        return connection;

    }

    
    
    //数据库查询方法，参数为查询字符串，返回一个ResultSet对象
    public ResultSet executeQuery(String sql)
    {
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
          return   statement.executeQuery();
        } catch (SQLException e) {
            System.out.print("查询失败");
        }
        return null;
    }
    
    //数据库执行增删改的方法，输入一个数据库执行语句，返回布尔值（成功返回true,失败返回false）
    public boolean execute(String sql)
    {
        try {
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.print("执行sql语句出错");
        }
        return false;

    }
    
    
    /*数据库执行增删改的方法，输入一个预编译的sql语句和数组对数据库进行操作（例：execute("insert into table values(?,?)",new String[]{"col1","col2"})）
    
    *成功返回true,失败返回false
    *
    */
    public boolean execute(String sql,String[] values)
    {
        try {
            PreparedStatement statement =connection.prepareStatement(sql);
            
            for(int i=0;i<values.length;i++)
            {
            	statement.setString(i+1, values[i]);
            }
            
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.print("执行sql语句出错");
        }
return false;

    }












}
