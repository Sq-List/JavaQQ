package com.zust.client.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame
{

	JLabel labelpic,label2,label3,label4,label5,label6;
	JButton button1,button2;
	JTextField textField;
	JList jList;
	DefaultListModel listModel;
	JScrollPane listScroller;

	public Main(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 400;
		int height = 900;
		setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
		setResizable(false);
		setTitle("FakeQQ");
		labelpic = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel("用户昵称哈哈哈");
		label4 = new JLabel();
		button1 = new JButton("搜索好友");
		button2 = new JButton("添加好友");
		label5 = new JLabel();
		label6 = new JLabel("made by niceteam",JLabel.CENTER);
		this.setLayout(null);
		labelpic.setBounds(50,50,70,70);
		labelpic.setBorder(BorderFactory.createLineBorder(Color.black));
		label2.setBounds(0,150,400,2);
		label2.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));
		label3.setBounds(170, 70, 150, 30);
		label3.setFont(new Font("宋体", 1, 18));
		label4.setBounds(0,200,400,2);
		label4.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));
		button1.setBounds(70, 160, 95, 30);
		button2.setBounds(235, 160, 95, 30);
		label5.setBounds(0,830,400,2);
		label5.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));
		label6.setBounds(130,830,140,40);

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetList();
			}
		});

		listModel = new DefaultListModel();
		String[] data = { "好友1", "好友2", "好友3", "好友4", "好友5", "好友6", "好友7",
				"好友8", "好友9", "好友10", "好友11" , "好友12" , "好友13" , "好友14"
				, "好友15" , "好友16" , "好友17" , "好友18" };
		for(int i=0;i<data.length;i++){
			listModel.add(i, data[i]);
		}
		jList = new JList(listModel);
		jList.setCellRenderer(new CellRenderer());
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList theList = (JList) e.getSource();
				if (e.getClickCount() == 2) {
					int index = theList.locationToIndex(e.getPoint());
					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
						System.out.println(o.toString());
					}
				}
			}
		});
		listScroller = new JScrollPane(jList);
		listScroller.setBounds(0, 200, 400, 632);

// setBackground(Color.blue);
		this.getContentPane().setBackground(Color.white);
		this.getContentPane().setVisible(true);

		add(listScroller);
		add(label2);
		add(labelpic);
		add(label3);
		add(label4);
		add(button1);
		add(button2);
		add(label5);
		add(label6);
		this.setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void resetList(){
		remove(listScroller);
		listModel = new DefaultListModel();
		String[] data = { "好友1", "好友2", "好友3", "好友4", "好友5"};
		for(int i=0;i<data.length;i++){
			listModel.add(i, data[i]);
		}
		jList = new JList(listModel);
		jList.setCellRenderer(new CellRenderer());
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList theList = (JList) e.getSource();
				if (e.getClickCount() == 2) {
					int index = theList.locationToIndex(e.getPoint());
					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
						System.out.println(o.toString());
					}
				}
			}
		});
		listScroller = new JScrollPane(jList);
		listScroller.setBounds(0, 200, 400, 632);
		add(listScroller);
		listScroller.validate();
	}

}
