package com.StudentManageSystem.view;

import java.awt.BorderLayout;//����
import java.awt.GridLayout;//�������
import java.awt.event.ActionEvent;//�����¼�
import java.awt.event.ActionListener;//������
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;//��ť
import javax.swing.JFrame;//����
import javax.swing.JLabel;//�ı��ؼ�
import javax.swing.JPanel;//���
import javax.swing.JPasswordField;//�����
import javax.swing.JTextField;//�ı���

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.dao.AdminDAO;
import com.StudentManageSystem.dao.StudentDAO;
import com.StudentManageSystem.data.AppConstants;
import com.StudentManageSystem.data.DAO;
import com.StudentManageSystem.model.Student;

public class AddView extends JFrame {

	private static final long serialVersionUID = -1984182788841566838L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton addButton, exitButton;
	private JTextField name, no, date,term, place, type, amount, price;//��Ʒ����

	public AddView() {
		init();
	}

	private void init() {
		setTitle("���ҩƷ��Ϣ");
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		jPanelCenter.add(new JLabel(AppConstants.MED_NAME));
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(AppConstants.MED_CODE));
		no = new JTextField();
		jPanelCenter.add(no);
		jPanelCenter.add(new JLabel(AppConstants.MED_DATE));
		date = new JTextField();
		jPanelCenter.add(date);
		jPanelCenter.add(new JLabel(AppConstants.MED_TERM));
		term = new JTextField();
		jPanelCenter.add(term);
		jPanelCenter.add(new JLabel(AppConstants.MED_PLACE));
		place = new JTextField();
		jPanelCenter.add(place); 
		jPanelCenter.add(new JLabel(AppConstants.MED_TYPE));
		type = new JTextField();
		jPanelCenter.add(type);
		jPanelCenter.add(new JLabel(AppConstants.MED_AMOUNT));
		amount = new JTextField();
		jPanelCenter.add(amount);
		jPanelCenter.add(new JLabel(AppConstants.MED_PRICE));
		price = new JTextField();
		jPanelCenter.add(price);
		jPanelCenter.add(new JLabel("-----------------------------------------------"));
		jPanelCenter.add(new JLabel("-----------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));//һ������
		addButton = new JButton("���");
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					//�Ƿ���ӳɹ�
					boolean isSuccess = ((StudentDAO)BaseDAO.getAbilityDAO(DAO.StudentDAO)).add(stu);
					if (isSuccess) {
						setEmpty();
						if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
							MainView.currPageNum = 1;
						}
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO))
								.list(MainView.currPageNum);
						MainView.initJTable(MainView.jTable, result);
					}
				}
			}
		});
		jPanelSouth.add(addButton);
		exitButton = new JButton("�˳�");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setResizable(true);//�����Ƿ�ɵ�����С
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(no.getText()) || "".equals(date.getText())
				|| "".equals(term.getText()) || "".equals(place.getText()) || "".equals(type.getText())
				|| "".equals(amount.getText()) || "".equals(price.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}
//��Ӳ����
	private void buildStudent(Student stu) {
		stu.setdate(date.getText());
		stu.setamount(amount.getText());
		stu.setplace(place.getText());
		stu.settype(type.getText());
		stu.setname(name.getText());
		stu.setno(no.getText());
		stu.setprice(price.getText());
		stu.setterm(term.getText());
	}

	private void setEmpty() {
		name.setText("");
		no.setText("");
		date.setText("");
		amount.setText("");
		type.setText("");
		place.setText("");
		price.setText("");
		term.setText("");
	}
}

