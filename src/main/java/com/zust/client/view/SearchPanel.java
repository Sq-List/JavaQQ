package com.zust.client.view;

import com.zust.client.UDP.ClientUDP;
import com.zust.client.manager.ManagerInfo;
import com.zust.common.bean.AddFriendRequestBean;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.SearchUserRequestBean;
import com.zust.common.bean.User;
import com.zust.common.tool.PicturePath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class SearchPanel extends JFrame {

	JLabel label1,label2,label3,label4,label5,label6;
	JButton button1,button2;
	JTextField textField;
	JPanel jPanel,jPanel1,jPanel2;
	JScrollPane listScroller;
	
	public SearchPanel(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 500;
		int height = 400;
		setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
		setResizable(false);
		setTitle("好友查询");
		label1 = new JLabel("搜索用户名或ID:");
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel("昵称");
		label5 = new JLabel("123456");
		label6 = new JLabel();
		textField = new JTextField();
		button1 = new JButton("搜索");
		button2 = new JButton("添加");
		jPanel = new JPanel();
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		this.setLayout(null);
		label1.setBounds(40, 10, 150, 40);
		textField.setBounds(150, 16, 150, 26);
		button1.setBounds(320, 16, 60, 26);
		label2.setBounds(0,60,500,1);
		label2.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.darkGray), BorderFactory.createEmptyBorder(1,1,1,1)));
		label3.setBounds(40, 17, 40, 40);
		label3.setIcon(new ImageIcon(PicturePath.getPicturePath("/image/1.png")));
		label4.setBounds(130, 23, 100, 30);
		label5.setBounds(230, 23, 100, 30);
		button2.setBounds(350, 25, 60, 26);
		label6.setBounds(0,70,500,1);
		label6.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				refreshList();
				try {
					DataFormat dataFormat = new DataFormat();
					dataFormat.setToId(0);
					dataFormat.setTime(System.currentTimeMillis());
					dataFormat.setType(2);
					SearchUserRequestBean searchUserRequestBean = new SearchUserRequestBean();
					searchUserRequestBean.setInfo(textField.getText());
					dataFormat.setData(searchUserRequestBean);
					dataFormat.setFromId(ManagerInfo.getUser().getId());
					ClientUDP.sendUdpMsg(dataFormat);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		jPanel.setLayout(null);
		jPanel.setPreferredSize(new Dimension(400,100));
		jPanel.add(label3);
		jPanel.add(label4);
		jPanel.add(label5);
		jPanel.add(button2);
		jPanel.add(label6);
		listScroller = new JScrollPane(jPanel);
		listScroller.setBounds(0, 60, 500, 340);


//		setBackground(Color.blue);
	    this.getContentPane().setBackground(Color.white);
	    this.getContentPane().setVisible(true);
	    add(label1);
	    add(textField);
	    add(button1);
	    add(label2);
	    add(listScroller);
		this.setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void refreshList(java.util.List<User> userList){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				remove(listScroller);
				JPanel jPaneltest = new JPanel();
				jPaneltest.setLayout(null);
				jPaneltest.setPreferredSize(new Dimension(400,370));
				for(int i = 0;i < userList.size();i++){
					User user = userList.get(i);
					JLabel jLabela, jLabelb, jLabelc, jLabeld;
					JButton jButton;
					jLabela = new JLabel();
					jLabelb = new JLabel(user.getUserName());
					jLabelc = new JLabel(user.getId().toString());
					jLabeld = new JLabel();
					jButton = new JButton("添加");
					jLabela.setBounds(40, 17+i*70, 40, 40);
					ImageIcon imageIcon = new ImageIcon(PicturePath.getPicturePath(user.getAvatarSrc()));
					imageIcon.setImage(imageIcon.getImage().getScaledInstance(40 ,40, Image.SCALE_DEFAULT));
					jLabela.setIcon(imageIcon);
					jLabelb.setBounds(130, 23+i*70, 100, 30);
					jLabelc.setBounds(230, 23+i*70, 100, 30);
					jButton.setBounds(350, 25+i*70, 60, 26);
					if(user.getStatus() == true){
						jButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try{
									DataFormat dataFormat = new DataFormat();
									dataFormat.setType(0);
									AddFriendRequestBean addFriendRequestBean = new AddFriendRequestBean();
									addFriendRequestBean.setType(0);
									addFriendRequestBean.setUser(user);
									dataFormat.setTime(System.currentTimeMillis());
									dataFormat.setFromId(ManagerInfo.getUser().getId());
									dataFormat.setToId(user.getId());
									dataFormat.setData(addFriendRequestBean);
									ClientUDP.sendUdpMsg(dataFormat);
								}catch (Exception exception){
									exception.printStackTrace();
								}
							}
						});
					}else{
						jButton.setText("不在线");
					}
					jLabeld.setBounds(0,70+i*70,500,1);
					jLabeld.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));
					jPaneltest.add(jLabela);
					jPaneltest.add(jLabelb);
					jPaneltest.add(jLabelc);
					jPaneltest.add(jButton);
					jPaneltest.add(jLabeld);
				}
				listScroller = new JScrollPane(jPaneltest);
				listScroller.setBounds(0, 60, 500, 340);
				add(listScroller);
				validate();
			}
		});
	}

}
