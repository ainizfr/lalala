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
	
	/*����������ѯ*/
	public String[][] queryByName(String name){
		String[][] result=null;
		if(name.length()<0){
			return result;
		}
		//stusΪ�գ�ָ��List������ŵ����ݵ�����ΪStudent���ͣ�����
		List<Student> stus=new ArrayList<Student>();
		int i=0;
		String sql="select * from med where name=?";
		String[] param={name};
		/*���ݿ����db,���嵽������BaseDAO,����executeQuery()�����ݿ�ʵ��*/
		rs=db.executeQuery(sql,param);//rs���������ݿ��У���ResultSet���ͣ������ݿ��ѯ
		try {
			while(rs.next()){//�жϼ���rs��ʱ�������ݴ��ڣ���ʵ�������Ѿ��õ���һ������
				//����ѯ���Ľ����ŵ�����stus��,��������ΪStudent����
				//i��ʾid
				buildList(rs,stus,i);
				i++;
			}
			if(stus.size()>0){
				result=new String[stus.size()][fieldNum];
				/*������stus�У�����Ҫ�����ݷŵ�result��*/
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
	
	/*��һҳ���е���������ת���ɶ�ά�������ʽ*/
	public String[][] list(int pageNum){
		String[][] result=null;
		if(pageNum<1){
			return result;
		}
		List<Student> stus=new ArrayList<Student>();
		int i=0;
		int beginNum=(pageNum-1)*showNum;
		String sql="select * from med limit ?,?";
		Integer[] param={beginNum,showNum};//��beginNum��ʼ��һҳȡshowNum��
		/*���ݿ����db,���嵽������BaseDAO,����executeQuery()�����ݿ�ʵ��*/
		rs=db.executeQuery(sql,param);//rs���������ݿ��У���ResultSet���ͣ������ݿ��ѯ
		try {
			while(rs.next()){//�жϼ���rs��ʱ�������ݴ��ڣ���ʵ�������Ѿ��õ���һ������
				//����ѯ���Ľ����ŵ�����stus��,��������ΪStudent����
				//i��ʾid
				buildList(rs,stus,i);
				i++;
			}
			if(stus.size()>0){
				result=new String[stus.size()][fieldNum];
				/*������stus�У�����Ҫ�����ݷŵ�result��*/
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
	
	//rs�е��������ݶ�ת��Student���ͣ�����ӵ�����stus�У��տ�ʼstusΪ��
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
	//��ѧ�Ų�ѯ�����ؽ��Ϊ0��û�в鵽����1���鵽��
	private int queryBySno(String sno) throws SQLException{
		int result=0;
		if("".equals(sno)||sno==null)
			return result;
		String checksql="select * from med where no=?";
		String[] checkparam={sno};
		/*���ݿ����db,���嵽������BaseDAO,����executeQuery()�����ݿ�ʵ��*/
		rs=db.executeQuery(checksql,checkparam);//rs���������ݿ��У���ResultSet���ͣ������ݿ��ѯ
		if(rs.next()){//�жϼ���rs��ʱ�������ݴ��ڣ���ʵ�������Ѿ��õ���һ������
			result=1;
		}
		return result;
	}
	
	//��Ӳ�������select���,����ֵ������
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
	
	//ɾ������
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
	
	//���²���
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
