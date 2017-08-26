package com.StudentManageSystem.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.model.Student;

public class StudentDAO extends BaseDAO{
	private final int fieldNum=9;
	private final int showNum=15;
	private static StudentDAO sd = null;
	public static synchronized StudentDAO getInstance(){
		if (sd==null){
            sd=new StudentDAO();
        }
		return sd;
	}
	
	/*按照姓名查询*/
	public String[][] queryByName(String name){
		String[][] result=null;
		if(name.length()<0){
			return result;
		}
		//stus为空，指定List中所存放的数据的类型为Student类型，泛型
		List<Student> stus=new ArrayList<Student>();
		int i=0;
		String sql="select * from med where name=?";
		String[] param={name};
		/*数据库对象db,定义到父类中BaseDAO,方法executeQuery()在数据库实现*/
		rs=db.executeQuery(sql,param);//rs定义在数据库中，是ResultSet类型，到数据库查询
		try {
			while(rs.next()){//判断集合rs中时候有数据存在，其实集合中已经拿到了一挑数据
				//将查询到的结果存放到链表stus里,数据类型为Student类型
				//i表示id
				buildList(rs,stus,i);
				i++;
			}
			if(stus.size()>0){
				result=new String[stus.size()][fieldNum];
				/*数据再stus中，最终要把数据放到result中*/
				for(int j=0;j<stus.size();j++){
					buildResult(result,stus,j);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			destroy();
		}
		
		return result;
	}
	
	/*将一页当中的所有数据转换成二维数组的形式*/
	public String[][] list(int pageNum){
		String[][] result=null;
		if(pageNum<1){
			return result;
		}
		List<Student> stus=new ArrayList<Student>();
		int i=0;
		int beginNum=(pageNum-1)*showNum;
		String sql="select * from med limit ?,?";
		Integer[] param={beginNum,showNum};//从beginNum开始，一页取showNum行
		/*数据库对象db,定义到父类中BaseDAO,方法executeQuery()在数据库实现*/
		rs=db.executeQuery(sql,param);//rs定义在数据库中，是ResultSet类型，到数据库查询
		try {
			while(rs.next()){//判断集合rs中时候有数据存在，其实集合中已经拿到了一挑数据
				//将查询到的结果存放到链表stus里,数据类型为Student类型
				//i表示id
				buildList(rs,stus,i);
				i++;
			}
			if(stus.size()>0){
				result=new String[stus.size()][fieldNum];
				/*数据再stus中，最终要把数据放到result中*/
				for(int j=0;j<stus.size();j++){
					buildResult(result,stus,j);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			destroy();
		}
		
		return result;
	}
	
	//rs中的所有数据都转成Student类型，再添加到链表stus中，刚开始stus为空
	private void buildList(ResultSet rs,List<Student> stus,int i) throws SQLException{
		Student stu=new Student();
		stu.setId(i+1);
		stu.setname(rs.getString("name"));
		stu.setterm(rs.getString("term"));
		stu.setamount(rs.getString("amount"));
		stu.setplace(rs.getString("place"));
		stu.setdate(rs.getString("date"));
		stu.settype(rs.getString("type"));
		stu.setterm(rs.getString("term"));
		stu.setno(rs.getString("no"));
		stu.setprice(rs.getString("price"));
		stus.add(stu);
	}
	private void buildResult(String[][] result,List<Student> stus,int j){
		Student stu=stus.get(j);
		result[j][0]=String.valueOf(stu.getId());
		result[j][1]=stu.getname();
		result[j][2]=stu.getno();
		result[j][3]=stu.getdate();
		result[j][4]=stu.getterm();
		result[j][5]=stu.getplace();
		result[j][6]=stu.gettype();
		result[j][7]=stu.getamount();
		result[j][8]=stu.getprice();
	}
	//按学号查询，返回结果为0（没有查到）或1（查到）
	private int queryBySno(String sno) throws SQLException{
		int result=0;
		if("".equals(sno)||sno==null)
			return result;
		String checksql="select * from med where no=?";
		String[] checkparam={sno};
		/*数据库对象db,定义到父类中BaseDAO,方法executeQuery()在数据库实现*/
		rs=db.executeQuery(checksql,checkparam);//rs定义在数据库中，是ResultSet类型，到数据库查询
		if(rs.next()){//判断集合rs中时候有数据存在，其实集合中已经拿到了一挑数据
			result=1;
		}
		return result;
	}
	
	//添加操作——select语句,返回值布尔型
	public boolean add(Student stu){
		boolean result=false;
		if(stu==null)
			return result;
		try {
			if(queryBySno(stu.getno())==1){
				return result;
			}
		String sql="insert into med(name,no,date,term,place,type,amount,price) values(?,?,?,?,?,?,?,?)";
		String[] param={stu.getname(),stu.getno(),stu.getdate(),stu.getterm(),stu.getplace(),stu.gettype(),stu.getamount(),stu.getprice()};
		
		if(db.executeUpdate(sql,param)==1)
			result=true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			destroy();
		}
		return result;
	}
	
	//删除操作
	public boolean delete(Student stu){
		boolean result=false;
		if(stu==null)
			return result;
		String sql="delete from med where name=? and no=?";
		String[] param={stu.getname(),stu.getno()};
		if(db.executeUpdate(sql,param)==1)
			result=true;
		destroy();
		return result;
	}
	
	//更新操作
	public boolean update(Student stu){
		boolean result=false;
		if(stu==null)
			return result;
		try {
			if(queryBySno(stu.getno())==0){
				return result;
			}
		String sql="update med set date=?,term=?,place=?,type=?,amount=?,price=? where name=? and no=?";
		String[] param={stu.getdate(),stu.getterm(),stu.getplace(),stu.gettype(),stu.getamount(),stu.getprice(),stu.getname(),stu.getno()};
		
		if(db.executeUpdate(sql,param)==1)
			result=true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			destroy();
		}
		return result;
	}

}
