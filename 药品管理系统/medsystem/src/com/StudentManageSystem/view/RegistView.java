package com.StudentManageSystem.view;

import java.awt.BorderLayout;//方向
import java.awt.Component;
import java.awt.GridLayout;//划分面板
import java.awt.event.ActionEvent;//动作事件
import java.awt.event.ActionListener;//监听器
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;//按钮
import javax.swing.JFrame;//窗体
import javax.swing.JLabel;//文本控件
import javax.swing.JPanel;//面板
import javax.swing.JPasswordField;//密码框
import javax.swing.JTextField;//文本框
import javax.swing.JOptionPane;//弹出要求用户提供值或向其发出通知的标准对话框

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.base.JavaSmsApi;
import com.StudentManageSystem.dao.AdminDAO;
import com.StudentManageSystem.data.AppConstants;
import com.StudentManageSystem.data.DAO;

public class RegistView extends JFrame{
	//数据成员
	private JPanel jpnorth,jpshouth;
	private JTextField username;
	private JPasswordField password;
	private JButton definiteButton,cancelButton;
	//成员方法
	public RegistView(){
		init();
	}
	private void init(){
		this.setTitle("请注册");
		jpnorth=new JPanel();
		jpnorth.setLayout(new GridLayout(2,1));
		jpnorth.add(new JLabel(AppConstants.LOGIN_USERNAME));
		username=new JTextField();
		jpnorth.add(username);
		jpnorth.add(new JLabel(AppConstants.LOGIN_PASSWORD));
		password=new JPasswordField();
		jpnorth.add(password);
		
		jpshouth=new JPanel();
		definiteButton=new JButton(AppConstants.DEFINITE);
		definiteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				check();
			}
		});
		jpshouth.setLayout(new GridLayout(1,2));
		jpshouth.add(definiteButton);
		cancelButton=new JButton(AppConstants.CANCEL);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		jpshouth.add(cancelButton);
		
		this.add(jpnorth,BorderLayout.NORTH);
		this.add(jpshouth,BorderLayout.SOUTH);
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(650,420,300,120);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	private void check(){
		AdminDAO adminDAO=(AdminDAO)BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if(adminDAO.insertForRegister(username.getText(),String.valueOf(password.getPassword()))){
			int option=JOptionPane.showConfirmDialog(RegistView.this,"注册成功","提示",JOptionPane.YES_OPTION);
			try {
				new JavaSmsApi().sent();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
