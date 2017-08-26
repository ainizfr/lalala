package com.StudentManageSystem.view;

import java.awt.BorderLayout;//����
import java.awt.Component;
import java.awt.GridLayout;//�������
import java.awt.event.ActionEvent;//�����¼�
import java.awt.event.ActionListener;//������
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;//��ť
import javax.swing.JFrame;//����
import javax.swing.JLabel;//�ı��ؼ�
import javax.swing.JPanel;//���
import javax.swing.JPasswordField;//�����
import javax.swing.JTextField;//�ı���
import javax.swing.JOptionPane;//����Ҫ���û��ṩֵ�����䷢��֪ͨ�ı�׼�Ի���

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.base.JavaSmsApi;
import com.StudentManageSystem.dao.AdminDAO;
import com.StudentManageSystem.data.AppConstants;
import com.StudentManageSystem.data.DAO;

public class RegistView extends JFrame{
	//���ݳ�Ա
	private JPanel jpnorth,jpshouth;
	private JTextField username;
	private JPasswordField password;
	private JButton definiteButton,cancelButton;
	//��Ա����
	public RegistView(){
		init();
	}
	private void init(){
		this.setTitle("��ע��");
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
			int option=JOptionPane.showConfirmDialog(RegistView.this,"ע��ɹ�","��ʾ",JOptionPane.YES_OPTION);
			try {
				new JavaSmsApi().sent();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

}
