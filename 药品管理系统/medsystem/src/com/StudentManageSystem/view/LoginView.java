package com.StudentManageSystem.view;

import java.awt.BorderLayout;//����
import com.StudentManageSystem.base.*;
import java.awt.Color;
import java.awt.GridLayout;//�������
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;//�����¼�
import java.awt.event.ActionListener;//������
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;//��ť
import javax.swing.JFrame;//����
import javax.swing.JLabel;//�ı��ؼ�
import javax.swing.JPanel;//���
import javax.swing.JPasswordField;//�����
import javax.swing.JTextField;//�ı���

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.dao.AdminDAO;
import com.StudentManageSystem.data.AppConstants;
import com.StudentManageSystem.data.DAO;
import java.awt.color.*;
public class LoginView extends JFrame {//���д���̳�JFrame
	JButton jb=new JButton();
	private JPanel jpcenter,jpsouth;
	private JTextField username;
	private JPasswordField password;
	private JButton login,reset,regist;//��½�����ã�ע��
	public LoginView() throws Exception{//���췽��һ��Ϊpublic
		init();
	}
	private void init() throws Exception{
		this.setTitle("��ӭ��½XXXҩ�����ϵͳ");
		/*****************ʵ��javaͼ���滻****************************/
		 Image icon = Toolkit.getDefaultToolkit().getImage("img\\ͼ��.png"); 
		 this.setIconImage(icon);
		 this.setSize(400,400);
		 this.setVisible(true);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
		 /********************ʵ�ֻ�ӭͼƬ****************************/
		//���ô�С  
	     setSize(1200, 1200);  
	     //����λ��  
	    // setLocation(200, 50);  
	     //����ͼƬ��·���������·�����߾���·��������ͼƬ����"java��Ŀ��"���ļ��£�  
	     String path = "img\\����.jpg";  
	     // ����ͼƬ  
	     ImageIcon background = new ImageIcon(path);  
	     // �ѱ���ͼƬ��ʾ��һ����ǩ����  
	     JLabel label = new JLabel(background);  
	     // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
	     label.setBounds(0, 0, this.getWidth(), this.getHeight());  
	     // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
	     JPanel imagePanel = (JPanel) this.getContentPane();  
	     imagePanel.setOpaque(false);  
	     // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
	     this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
	     //���ÿɼ�  
	     setVisible(true);  
	     //��رհ�ťʱ�˳�  
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	     /*************��¼����***************************/
	     Thread.sleep(3000);  
		
		jpcenter=new JPanel();
		jpcenter.setLayout(new GridLayout(4,2));//����jpnorth�����������
		jpcenter.add(new JLabel("********************************************��¼"));
		jpcenter.add(new JLabel("����********************************************"));
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
		/*����½��ť���ܡ�*/
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				check();
			}
		});
		jpsouth.add(login);
		reset=new JButton(AppConstants.RESET);
		/*�����ð�ť���ܡ�*/
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				username.setText("");
				password.setText("");
				
			}
		});
		jpsouth.add(reset);
		regist=new JButton(AppConstants.REGIST);
		/*��ע�ᰴť���ܡ�*/
		regist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new RegistView();
			}
		});
		jpsouth.add(regist);
		
		this.add(jpcenter,BorderLayout.CENTER);
		this.add(jpsouth,BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//�رյķ�ʽ
		//ֻ�رյ�ǰ����
		this.setBounds(550,350,500,200);//���÷�λ��С
		//�²�����JFrame��������ϽǾ�����Ļ���Ͻǣ�450*250������λ��
		//JFrame����Ĵ�СΪ��275*140������
		this.setResizable(false);//����Ϊfalse��ʾ���øı䴰�ڴ�С��true��ʾ����
		this.setVisible(true);//true��ʾ����
	}
	private void check(){
		AdminDAO adminDAO=(AdminDAO)BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if(adminDAO.queryForLogin(username.getText(),String.valueOf(password.getPassword()))){
			dispose();
			new MainView();
			
		}
				
	}

}
