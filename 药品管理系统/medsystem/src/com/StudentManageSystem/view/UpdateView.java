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

public class UpdateView extends JFrame {

	private static final long serialVersionUID = 5292738820127102731L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
	private JTextField name, no, date,term, place, type, amount, price;
	public UpdateView() {
		init();
	}

	private void init() {
		setTitle("更新信息");
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
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton("更新");
		//更新
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).update(stu);//是否更新成功
					if (isSuccess) {
						setEmpty();
						if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
							MainView.currPageNum = 1;
						}
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(MainView.currPageNum);//
						MainView.initJTable(MainView.jTable, result);
					}
				}
			}
		});
		jPanelSouth.add(updateButton);
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
		setBounds(470, 200, 400, 270);
		setResizable(false);
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
//更新学生信息并输出
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
		term.setText("");
		place.setText("");
		type.setText("");
		amount.setText("");
		price.setText("");
	}
}

