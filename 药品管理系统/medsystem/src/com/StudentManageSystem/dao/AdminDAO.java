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
		//数据库的连接
		String sql="select * from admin where username=? and password=?";
		String[] param={username,password};
		/*数据库对象db,定义到父类中BaseDAO,方法executeQuery()在数据库实现*/
		rs=db.executeQuery(sql,param);//rs定义在数据库中，是ResultSet类型，到数据库查询
		try {
			if(rs.next()){//判断集合rs中时候有数据存在，其实集合中已经拿到了一挑数据
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
