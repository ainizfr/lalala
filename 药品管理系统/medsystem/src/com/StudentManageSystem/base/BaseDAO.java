package com.StudentManageSystem.base;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.StudentManageSystem.dao.AdminDAO;
import com.StudentManageSystem.dao.StudentDAO;
import com.StudentManageSystem.data.DAO;
import com.StudentManageSystem.util.DBUtil;

public abstract class BaseDAO {
	protected final DBUtil db=DBUtil.getDBUtil();
	protected ResultSet rs;
	//同步，同一时间只能对一个对象操作
	private static BaseDAO baseDAO;
	//同步的
	public static synchronized BaseDAO getAbilityDAO(DAO dao){
		switch(dao){
		case AdminDAO:
			if (baseDAO==null||baseDAO.getClass()!=AdminDAO.class){  
                baseDAO = AdminDAO.getInstance();
			}
			break;
		case StudentDAO:
			if (baseDAO==null||baseDAO.getClass()!=StudentDAO.class){
                baseDAO = StudentDAO.getInstance();
			}
			break;
		default:
			break;
		}
		return baseDAO;
	}
	/*关闭数据库，第二层关闭*/
	protected void destroy(){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
	}
}
