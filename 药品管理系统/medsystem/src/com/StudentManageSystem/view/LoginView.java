package com.StudentManageSystem.view;

import java.awt.BorderLayout;//方向
import com.StudentManageSystem.base.*;
import java.awt.Color;
import java.awt.GridLayout;//划分面板
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;//动作事件
import java.awt.event.ActionListener;//监听器
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;//按钮
import javax.swing.JFrame;//窗体
import javax.swing.JLabel;//文本控件
import javax.swing.JPanel;//面板
import javax.swing.JPasswordField;//密码框
import javax.swing.JTextField;//文本框

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.dao.AdminDAO;
import com.StudentManageSystem.data.AppConstants;
import com.StudentManageSystem.data.DAO;
import java.awt.color.*;
public class LoginView extends JFrame {//所有窗体继承JFrame
	JButton jb=new JButton();
	private JPanel jpcenter,jpsouth;
	private JTextField username;
	private JPasswordField password;
	private JButton login,reset,regist;//登陆，重置，注册
	public LoginView() throws Exception{//构造方法一般为public
		init();
	}
	private void init() throws Exception{
		this.setTitle("欢迎登陆XXX药店管理系统");
		/*****************实现java图标替换****************************/
		 Image icon = Toolkit.getDefaultToolkit().getImage("img\\图标.png"); 
		 this.setIconImage(icon);
		 this.setSize(400,400);
		 this.setVisible(true);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
		 /********************实现欢迎图片****************************/
		//设置大小  
	     setSize(1200, 1200);  
	     //设置位置  
	    // setLocation(200, 50);  
	     //背景图片的路径。（相对路径或者绝对路径。本例图片放于"java项目名"的文件下）  
	     String path = "img\\背景.jpg";  
	     // 背景图片  
	     ImageIcon background = new ImageIcon(path);  
	     // 把背景图片显示在一个标签里面  
	     JLabel label = new JLabel(background);  
	     // 把标签的大小位置设置为图片刚好填充整个面板  
	     label.setBounds(0, 0, this.getWidth(), this.getHeight());  
	     // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
	     JPanel imagePanel = (JPanel) this.getContentPane();  
	     imagePanel.setOpaque(false);  
	     // 把背景图片添加到分层窗格的最底层作为背景  
	     this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
	     //设置可见  
	     setVisible(true);  
	     //点关闭按钮时退出  
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	     /*************登录窗口***************************/
	     Thread.sleep(3000);  
		
		jpcenter=new JPanel();
		jpcenter.setLayout(new GridLayout(4,2));//划分jpnorth面板三行两列
		jpcenter.add(new JLabel("********************************************登录"));
		jpcenter.add(new JLabel("界面********************************************"));
		jpcenter.add(new JLabel(AppConstants.LOGIN_USERNAME));
		username=new JTextField();
		jpcenter.add(username);
		jpcenter.add(new JLabel(AppConstants.LOGIN_PASSWORD));
		password=new JPasswordField();
		jpcenter.add(password);
		jpcenter.add(new JLabel("*************************************************"));
		jpcenter.add(new JLabel("*************************************************"));
		
		jpsouth=new JPanel();
		jpsouth.setLayout(new GridLayout(1,3));
		login=new JButton(AppConstants.LOGIN);
		/*↓登陆按钮功能↓*/
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				check();
			}
		});
		jpsouth.add(login);
		reset=new JButton(AppConstants.RESET);
		/*↓重置按钮功能↓*/
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				username.setText("");
				password.setText("");
				
			}
		});
		jpsouth.add(reset);
		regist=new JButton(AppConstants.REGIST);
		/*↓注册按钮功能↓*/
		regist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new RegistView();
			}
		});
		jpsouth.add(regist);
		
		this.add(jpcenter,BorderLayout.CENTER);
		this.add(jpsouth,BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//关闭的方式
		//只关闭当前窗口
		this.setBounds(550,350,500,200);//设置方位大小
		//新产生的JFrame界面的左上角距离屏幕左上角（450*250）像素位置
		//JFrame界面的大小为（275*140）像素
		this.setResizable(false);//参数为false表示不得改变窗口大小，true表示可以
		this.setVisible(true);//true表示可视
	}
	private void check(){
		AdminDAO adminDAO=(AdminDAO)BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if(adminDAO.queryForLogin(username.getText(),String.valueOf(password.getPassword()))){
			dispose();
			new MainView();
			
		}
				
	}

}
