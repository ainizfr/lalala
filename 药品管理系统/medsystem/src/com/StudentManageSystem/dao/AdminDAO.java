package com.StudentManageSystem.dao;

import java.sql.SQLException;

import com.StudentManageSystem.base.BaseDAO;

public class AdminDAO extends BaseDAO{
	private static AdminDAO ad = null;
	public static synchronized AdminDAO getInstance(){
		if (ad==null){
            ad=new AdminDAO();
        }
		return ad;
	}
	public boolean queryForLogin(String username,String password){
		boolean result=false;
		if(username.length()== 0||password.length()==0)
			return result;
		//���ݿ������
		String sql="select * from admin where username=? and password=?";
		String[] param={username,password};
		/*���ݿ����db,���嵽������BaseDAO,����executeQuery()�����ݿ�ʵ��*/
		rs=db.executeQuery(sql,param);//rs���������ݿ��У���ResultSet���ͣ������ݿ��ѯ
		try {
			if(rs.next()){//�жϼ���rs��ʱ�������ݴ��ڣ���ʵ�������Ѿ��õ���һ������
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			destroy();
		}
		return result;
	}
	public boolean insertForRegister(String username,String password){
		boolean result=false;
		if(username.length()== 0||password.length()==0)
			return result;
		String sql="insert into admin(username,password) values(?,?)";
		String[] param={username,password};
		int rowCount=db.executeUpdate(sql,param);
		if(rowCount==1)
			result=true;
		return result;
	}

}
