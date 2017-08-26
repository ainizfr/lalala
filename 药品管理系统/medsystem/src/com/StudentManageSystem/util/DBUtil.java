package com.StudentManageSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;//sql异常类
import com.StudentManageSystem.data.AppConstants;

public class DBUtil {
	private static DBUtil db;
	private Connection conn;//数据库连接对象
	private ResultSet rs;
	private PreparedStatement ps;//创建句柄对象，执行sql语句时必须用到的类
	/*若没有创建数据库的对象，此方法创建数据库的一个对象*/
	public static DBUtil getDBUtil(){
		if(db==null){
			//没有赋值，db永远是 空的
			db=new DBUtil();
		}
		return db;
	}
	public ResultSet executeQuery(String sql,Object []param){
		/*数据库连接的方法*/
		if(getConn()==null){
			return null;
		}
		try{
			ps=conn.prepareStatement(sql);
			for(int i=0;i<param.length;i++){
				ps.setObject(i+1,param[i]);
			}
			rs=ps.executeQuery();//利用对象conn实例化ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public int executeUpdate(String sql,Object []param){
		int result=-1;
		if(getConn()==null){
			return result;
		}
		try{
			ps=conn.prepareStatement(sql);
			for(int i=0;i<param.length;i++){
				ps.setObject(i+1,param[i]);
			}
			result=ps.executeUpdate();//利用对象conn实例化ps
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private Connection getConn(){
		try {
			if(conn==null||conn.isClosed()){
				Class.forName(AppConstants.JDBC_DRIVER);//固定语句，加载驱动程序类，所以要用Class.，不用自己创建实例
				conn=DriverManager.getConnection(AppConstants.JDBC_URL,AppConstants.JDBC_USERNAME,AppConstants.JDBC_PASSWORD);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/*关闭数据库,最底层的关闭*/
	public void close(){
		try {
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
