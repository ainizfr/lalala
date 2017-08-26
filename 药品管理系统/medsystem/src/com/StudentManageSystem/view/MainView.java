package com.StudentManageSystem.view;

import java.awt.BorderLayout;//方向
import java.awt.GridLayout;//划分面板
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;//动作事件
import java.awt.event.ActionListener;//监听器
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;//按钮
import javax.swing.JFrame;//窗体
import javax.swing.JLabel;//文本控件
import javax.swing.JPanel;//面板
import javax.swing.JPasswordField;//密码框
import javax.swing.JScrollPane;//垂直滚动条
import javax.swing.JTable;//管理表格的可视外观
import javax.swing.JTextField;//文本框
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;//默认的表格控制模型
import javax.swing.table.TableColumn;

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.dao.AdminDAO;
import com.StudentManageSystem.dao.StudentDAO;
import com.StudentManageSystem.data.AppConstants;
import com.StudentManageSystem.data.DAO;

public class MainView extends JFrame{
	//数据成员
	private static final long serialVersionUID = 5870864087464173884L;

	private final int maxPageNum = 99;

	private JPanel jPanelNorth, jPanelSouth, jPanelCenter;
	private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre, jButtonAdd, jButtonDelete, jButtonUpdate,jButtonFind;
	private JLabel currPageNumJLabel;//显示当前页
	private JTextField condition;
	public static JTable jTable;
	private JScrollPane jScrollPane;//垂直滚动条
	private DefaultTableModel myTableModel;//表格的结构模型

	public static String[] column = { "id", AppConstants.MED_NAME, AppConstants.MED_CODE,
			AppConstants.MED_DATE, AppConstants.MED_TERM, AppConstants.MED_PLACE,
			AppConstants.MED_TYPE, AppConstants.MED_AMOUNT, AppConstants.MED_PRICE };
	public static int currPageNum = 1;
	
	
	
	public MainView(){
		init();
	}
	private void init(){
		this.setTitle("欢迎登陆XXX药店管理系统");
		/*****************实现java图标替换****************************/
		 Image icon = Toolkit.getDefaultToolkit().getImage("img\\图标.png"); 
		 this.setIconImage(icon);
		 this.setSize(400, 400);
		 this.setVisible(true);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jPanelNorth=new JPanel();
		jPanelNorth.setLayout(new GridLayout(1,5));
		condition=new JTextField();
		condition.addKeyListener(new FindListener());
		jPanelNorth.add(condition);
		
		jButtonFind=new JButton(AppConstants.PARAM_FIND);
		jButtonFind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				find();
			}
		});
		jPanelNorth.add(jButtonFind);
		
		jButtonAdd=new JButton(AppConstants.PARAM_ADD);
		jButtonAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new AddView();
			}
		});
		jPanelNorth.add(jButtonAdd);
		
		jButtonDelete=new JButton(AppConstants.PARAM_DELETE);
		jButtonDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new DeleteView();
			}
		});
		jPanelNorth.add(jButtonDelete);
		
		jButtonUpdate=new JButton(AppConstants.PARAM_UPDATE);
		jButtonUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new UpdateView();
			}
		});
		jPanelNorth.add(jButtonUpdate);
		
		jPanelCenter=new JPanel();
		jPanelCenter.setLayout(new GridLayout(1,1));
		/*获取表格的内容*/
		
		// init jTable通过list获取的数据存到result中
		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);
		//结构模型实例化，result和column自动形成结构化数据
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		
		
		//相当于word中设置表格格式（文字居中摆放）
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();//单元格类的对象
		cr.setHorizontalAlignment(JLabel.CENTER);//设置文本的水平对齐方式
		
		//设置此Object.class的默认单元格渲染器,此 cr 要使用的默认单元格渲染器，Object.class 是指定渲染起作用的类
		jTable.setDefaultRenderer(Object.class, cr);
		initJTable(jTable, result);
				
		jScrollPane=new JScrollPane(jTable);
		jPanelCenter.add(jScrollPane);
		
		jPanelSouth=new JPanel();
		jPanelSouth.setLayout(new GridLayout(1,5));
		
		jButtonFirst=new JButton(AppConstants.MAINVIEW_FIRST);
		jButtonFirst.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currPageNum=1;
				String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);//把首页的数据以二维数组形式呈现
				MainView.initJTable(MainView.jTable, result);//二维数组的数据以表格形式呈现
				currPageNumJLabel.setText("第"+currPageNum+"/99页");//标签对象，以“第1/99页”形式显示
			}
		});
		
		
		jButtonPre=new JButton(AppConstants.MAINVIEW_PRE);
		jButtonPre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currPageNum--;
				if (currPageNum<=0) {
					currPageNum=1;
				}
				String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);
				MainView.initJTable(MainView.jTable, result);
				currPageNumJLabel.setText("第"+currPageNum+"/99页");//标签对象，以“第1/99页”形式显示
			}
		});
		
			
						
		
		jButtonNext=new JButton(AppConstants.MAINVIEW_NEXT);
		jButtonNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currPageNum++;
				if (currPageNum>99) {
					currPageNum=99;
				}
				String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);
				MainView.initJTable(MainView.jTable, result);
				currPageNumJLabel.setText("第"+currPageNum+"/99页");//标签对象，以“第1/99页”形式显示
			}
		});
		
		
		jButtonLast=new JButton(AppConstants.MAINVIEW_LAST);
		jButtonLast.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currPageNum=99;
				String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);//把首页的数据以二维数组形式呈现
				MainView.initJTable(MainView.jTable, result);//二维数组的数据以表格形式呈现
				currPageNumJLabel.setText("第"+currPageNum+"/99页");//标签对象，以“第1/99页”形式显示
			}
		});
		
		/*将内容在标签上显示,并居中*/
		currPageNumJLabel=new JLabel("第"+currPageNum+"/99页");
		currPageNumJLabel.setHorizontalAlignment(JLabel.CENTER);
		
		jPanelSouth.add(jButtonFirst);
		jPanelSouth.add(jButtonPre);
		jPanelSouth.add(currPageNumJLabel);
		jPanelSouth.add(jButtonNext);
		jPanelSouth.add(jButtonLast);
		
		this.add(jPanelNorth,BorderLayout.NORTH);
		this.add(jPanelCenter,BorderLayout.CENTER);
		this.add(jPanelSouth,BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(550,320,800,370);
		this.setResizable(false);
		this.setVisible(true);
		
	}
		
	private class FindListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				find();
			}
		}
	}

	private void find(){
		String para=condition.getText();
		if("".equals(para)||para==null){
			initJTable(MainView.jTable,null);//将查询到的结果和表格框架绑定，无返回值
		}
		else{
			StudentDAO studnetDAO=(StudentDAO)BaseDAO.getAbilityDAO(DAO.StudentDAO);
			String[][] result=studnetDAO.queryByName(para);
			condition.setText("");
			initJTable(MainView.jTable,result);
			//currPageNumJLabel.setText("查询结果");
			
		}
	}
	
	//将查询到的结果和表格框架绑定，无返回值
	public static void initJTable(JTable jTable,String[][] result){
		//将二维数组的了结果result与9个属性列和默认的表格模型绑定
		((DefaultTableModel)jTable.getModel()).setDataVector(result,column);
		//设置行高
		jTable.setRowHeight(20);
		TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
//		firsetColumn.setPreferredWidth(30);
//		firsetColumn.setMaxWidth(30);
//		firsetColumn.setMinWidth(30);
		firsetColumn.setWidth(30);
		TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(80);
		secondColumn.setMaxWidth(80);
		secondColumn.setMinWidth(80);
		TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(90);
		thirdColumn.setMaxWidth(90);
		thirdColumn.setMinWidth(90);
		TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
		fourthColumn.setPreferredWidth(100);
		fourthColumn.setMaxWidth(100);
		fourthColumn.setMinWidth(100);
		TableColumn seventhColumn = jTable.getColumnModel().getColumn(6);
		seventhColumn.setPreferredWidth(120);
		seventhColumn.setMaxWidth(120);
		seventhColumn.setMinWidth(120);
		TableColumn ninthColumn = jTable.getColumnModel().getColumn(8);
		ninthColumn.setPreferredWidth(90);
		ninthColumn.setMaxWidth(90);
		ninthColumn.setMinWidth(90);
	}
}
