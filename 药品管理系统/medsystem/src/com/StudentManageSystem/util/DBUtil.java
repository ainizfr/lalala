package com.StudentManageSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;//sql�쳣��
import com.StudentManageSystem.data.AppConstants;

public class DBUtil {
	private static DBUtil db;
	private Connection conn;//���ݿ����Ӷ���
	private ResultSet rs;
	private PreparedStatement ps;//�����������ִ��sql���ʱ�����õ�����
	/*��û�д������ݿ�Ķ��󣬴˷����������ݿ��һ������*/
	public static DBUtil getDBUtil(){
		if(db==null){
			//û�и�ֵ��db��Զ�� �յ�
			db=new DBUtil();
		}
		return db;
	}
	public ResultSet executeQuery(String sql,Object []param){
		/*���ݿ����ӵķ���*/
		if(getConn()==null){
			return null;
		}
		try{
			ps=conn.prepareStatement(sql);
			for(int i=0;i<param.length;i++){
				ps.setObject(i+1,param[i]);
			}
			rs=ps.executeQuery();//���ö���connʵ����ps
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
			result=ps.executeUpdate();//���ö���connʵ����ps
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
				Class.forName(AppConstants.JDBC_DRIVER);//�̶���䣬�������������࣬����Ҫ��Class.�������Լ�����ʵ��
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
	/*�ر����ݿ�,��ײ�Ĺر�*/
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
