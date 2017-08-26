package com.StudentManageSystem.view;

import java.awt.BorderLayout;//方向
import java.awt.GridLayout;//划分面板
import java.awt.event.ActionEvent;//动作事件
import java.awt.event.ActionListener;//监听器

import javax.swing.JButton;//按钮
import javax.swing.JFrame;//窗体
import javax.swing.JLabel;//文本控件
import javax.swing.JPanel;//面板
import javax.swing.JTextField;//文本框

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.dao.StudentDAO;
import com.StudentManageSystem.data.AppConstants;
import com.StudentManageSystem.data.DAO;
import com.StudentManageSystem.model.Student;

public class DeleteView extends JFrame {

	private static final long serialVersionUID = -7668153283910203959L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton deleteButton, exitButton;
	private JTextField name, sno; //

	public DeleteView() {
		init();
	}

	private void init() {
		setTitle("删除信息");
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		jPanelCenter.add(new JLabel(AppConstants.MED_NAME));
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(AppConstants.MED_CODE));
		sno = new JTextField();
		jPanelCenter.add(sno);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					//是否删除成功
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).delete(stu);
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
		jPanelSouth.add(deleteButton);
		exitButton = new JButton("退出");
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
		setBounds(470, 250, 400, 130);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(sno.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}
//删除信息
	private void buildStudent(Student stu) {
		stu.setname(name.getText());
		stu.setno(sno.getText());
	}

	private void setEmpty() {
		name.setText("");
		sno.setText("");
	}

}
