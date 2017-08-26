package com.StudentManageSystem.view;

import java.awt.BorderLayout;//����
import java.awt.GridLayout;//�������
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;//�����¼�
import java.awt.event.ActionListener;//������
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;//��ť
import javax.swing.JFrame;//����
import javax.swing.JLabel;//�ı��ؼ�
import javax.swing.JPanel;//���
import javax.swing.JPasswordField;//�����
import javax.swing.JScrollPane;//��ֱ������
import javax.swing.JTable;//������Ŀ������
import javax.swing.JTextField;//�ı���
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;//Ĭ�ϵı�����ģ��
import javax.swing.table.TableColumn;

import com.StudentManageSystem.base.BaseDAO;
import com.StudentManageSystem.dao.AdminDAO;
import com.StudentManageSystem.dao.StudentDAO;
import com.StudentManageSystem.data.AppConstants;
import com.StudentManageSystem.data.DAO;

public class MainView extends JFrame{
	//���ݳ�Ա
	private static final long serialVersionUID = 5870864087464173884L;

	private final int maxPageNum = 99;

	private JPanel jPanelNorth, jPanelSouth, jPanelCenter;
	private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre, jButtonAdd, jButtonDelete, jButtonUpdate,jButtonFind;
	private JLabel currPageNumJLabel;//��ʾ��ǰҳ
	private JTextField condition;
	public static JTable jTable;
	private JScrollPane jScrollPane;//��ֱ������
	private DefaultTableModel myTableModel;//���Ľṹģ��

	public static String[] column = { "id", AppConstants.MED_NAME, AppConstants.MED_CODE,
			AppConstants.MED_DATE, AppConstants.MED_TERM, AppConstants.MED_PLACE,
			AppConstants.MED_TYPE, AppConstants.MED_AMOUNT, AppConstants.MED_PRICE };
	public static int currPageNum = 1;
	
	
	
	public MainView(){
		init();
	}
	private void init(){
		this.setTitle("��ӭ��½XXXҩ�����ϵͳ");
		/*****************ʵ��javaͼ���滻****************************/
		 Image icon = Toolkit.getDefaultToolkit().getImage("img\\ͼ��.png"); 
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
		/*��ȡ��������*/
		
		// init jTableͨ��list��ȡ�����ݴ浽result��
		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);
		//�ṹģ��ʵ������result��column�Զ��γɽṹ������
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		
		
		//�൱��word�����ñ���ʽ�����־��аڷţ�
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();//��Ԫ����Ķ���
		cr.setHorizontalAlignment(JLabel.CENTER);//�����ı���ˮƽ���뷽ʽ
		
		//���ô�Object.class��Ĭ�ϵ�Ԫ����Ⱦ��,�� cr Ҫʹ�õ�Ĭ�ϵ�Ԫ����Ⱦ����Object.class ��ָ����Ⱦ�����õ���
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
				String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);//����ҳ�������Զ�ά������ʽ����
				MainView.initJTable(MainView.jTable, result);//��ά����������Ա����ʽ����
				currPageNumJLabel.setText("��"+currPageNum+"/99ҳ");//��ǩ�����ԡ���1/99ҳ����ʽ��ʾ
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
				currPageNumJLabel.setText("��"+currPageNum+"/99ҳ");//��ǩ�����ԡ���1/99ҳ����ʽ��ʾ
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
				currPageNumJLabel.setText("��"+currPageNum+"/99ҳ");//��ǩ�����ԡ���1/99ҳ����ʽ��ʾ
			}
		});
		
		
		jButtonLast=new JButton(AppConstants.MAINVIEW_LAST);
		jButtonLast.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currPageNum=99;
				String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);//����ҳ�������Զ�ά������ʽ����
				MainView.initJTable(MainView.jTable, result);//��ά����������Ա����ʽ����
				currPageNumJLabel.setText("��"+currPageNum+"/99ҳ");//��ǩ�����ԡ���1/99ҳ����ʽ��ʾ
			}
		});
		
		/*�������ڱ�ǩ����ʾ,������*/
		currPageNumJLabel=new JLabel("��"+currPageNum+"/99ҳ");
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
			initJTable(MainView.jTable,null);//����ѯ���Ľ���ͱ���ܰ󶨣��޷���ֵ
		}
		else{
			StudentDAO studnetDAO=(StudentDAO)BaseDAO.getAbilityDAO(DAO.StudentDAO);
			String[][] result=studnetDAO.queryByName(para);
			condition.setText("");
			initJTable(MainView.jTable,result);
			//currPageNumJLabel.setText("��ѯ���");
			
		}
	}
	
	//����ѯ���Ľ���ͱ���ܰ󶨣��޷���ֵ
	public static void initJTable(JTable jTable,String[][] result){
		//����ά������˽��result��9�������к�Ĭ�ϵı��ģ�Ͱ�
		((DefaultTableModel)jTable.getModel()).setDataVector(result,column);
		//�����и�
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
