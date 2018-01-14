package com.zust.client.view;

import com.zust.client.UDP.ClientUDP;
import com.zust.client.manager.ManagerInfo;
import com.zust.client.manager.ManagerPanel;
import com.zust.common.bean.AddFriendRequestBean;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.DeleteFriendRequestBean;
import com.zust.common.bean.User;
import com.zust.common.tool.PicturePath;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
		int height = 700;
		setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
		setResizable(false);
		setTitle("FakeQQ");
		ImageIcon logo=new ImageIcon(PicturePath.class.getResource("/image/logo1.jpg"));
		logo.setImage(logo.getImage().getScaledInstance(25, 30,
				Image.SCALE_DEFAULT));
		setIconImage(logo.getImage());
		labelpic = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel(ManagerInfo.getUser().getUserName());
		label4 = new JLabel();
		button1 = new JButton("个人信息");
		button2 = new JButton("添加好友");
		button1.setForeground(Color.white);
		button1.setBackground(new Color(193, 199, 215));
		button1.setBorder(BorderFactory.createLineBorder(Color.white));
		button2.setForeground(Color.white);
		button2.setBackground(new Color(193, 199, 215));
		button2.setBorder(BorderFactory.createLineBorder(Color.white));
		label5 = new JLabel();
		label6 = new JLabel("made by niceteam",JLabel.CENTER);
		this.setLayout(null);
		labelpic.setBounds(50,50,70,70);
		//labelpic.setBorder(BorderFactory.createLineBorder(Color.black));
		ImageIcon imageIcon = new ImageIcon(PicturePath.getPicturePath(ManagerInfo.getUser().getAvatarSrc()));
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(70 ,70, Image.SCALE_DEFAULT));
		labelpic.setIcon(imageIcon);
		label2.setBounds(0,150,400,2);
		//label2.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));
		label3.setBounds(170,50, 150, 30);
		label3.setFont(new Font("宋体", 1, 18));
		label4.setBounds(0,200,400,2);
		//label4.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));
		button1.setBounds(150, 100, 95, 30);
		button2.setBounds(270, 100, 95, 30);
		label5.setBounds(0,630,400,2);
		//label5.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));
		label6.setBounds(130,630,140,40);

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInfoFrame userInfoFrame = new UserInfoFrame(ManagerInfo.getUser());
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchPanel searchPanel = (SearchPanel) ManagerPanel.get("searchPanel");
				if(searchPanel == null){
					searchPanel = new SearchPanel();
					ManagerPanel.add("searchPanel", searchPanel);
				}else {
					searchPanel.setVisible(true);
				}
			}
		});

		HashMap<Integer, User> hashMap = (HashMap<Integer, User>) ManagerInfo.getUserMap();
		if(hashMap != null){
			listModel = new DefaultListModel();
			Iterator iterator = hashMap.entrySet().iterator();
			ArrayList<Integer> idList = new ArrayList<Integer>();
			ArrayList<User> onlineUsers = new ArrayList<User>();
			ArrayList<User> offlineUsers = new ArrayList<User>();
			ArrayList<Icon> icons = new ArrayList<Icon>();
			while (iterator.hasNext()){
				HashMap.Entry<Integer, User> entry = (HashMap.Entry<Integer, User>) iterator.next();
				User user = entry.getValue();
				if(user.getStatus() == true){
					onlineUsers.add(user);
				}else{
					offlineUsers.add(user);
				}
			}
			for(int i = 0;i < onlineUsers.size();i++){
				User user = onlineUsers.get(i);
				listModel.add(i, user.getUserName());
				idList.add(user.getId());
				ImageIcon picIcon = new ImageIcon(PicturePath.getPicturePath(user.getAvatarSrc()));
				picIcon.setImage(picIcon.getImage().getScaledInstance(40 ,40, Image.SCALE_DEFAULT));
				icons.add(picIcon);
			}
			int count = onlineUsers.size();
			for(int i = 0;i < offlineUsers.size();i++){
				User user = offlineUsers.get(i);
				listModel.add(count, "不在线    " + user.getUserName());
				idList.add(user.getId());
				ImageIcon picIcon = new ImageIcon(PicturePath.getPicturePath(user.getAvatarSrc()));
				picIcon.setImage(picIcon.getImage().getScaledInstance(40 ,40, Image.SCALE_DEFAULT));
				icons.add(picIcon);
				count++;
			}
			jList = new JList(listModel);
			jList.setCellRenderer(new CellRenderer(icons));
			jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JList theList = (JList) e.getSource();
					int index = theList.locationToIndex(e.getPoint());
					if (e.getClickCount() == 2 && index < onlineUsers.size()) {
						User user = hashMap.get(idList.get(index));
						ChatPane chatPanel = (ChatPane) ManagerPanel.get("chatPanel");
						if(chatPanel == null){
							chatPanel = new ChatPane(user, ManagerInfo.getUser().getId(), ManagerInfo.getUser().getUserName());
							ManagerPanel.add("chatPanel", chatPanel);
						}else {
							chatPanel.addOnlineFriend(user);
						}
					}else if(e.getButton() == 3){
						JPopupMenu jPopupMenu = new JPopupMenu();
						if(index < onlineUsers.size()){
							JMenuItem jMenuItem1 = new JMenuItem("进行聊天");
							jMenuItem1.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseReleased(MouseEvent e) {
									User user = hashMap.get(idList.get(index));
									ChatPane chatPanel = (ChatPane) ManagerPanel.get("chatPanel");
									if(chatPanel == null){
										chatPanel = new ChatPane(user, ManagerInfo.getUser().getId(), ManagerInfo.getUser().getUserName());
										ManagerPanel.add("chatPanel", chatPanel);
									}else {
										chatPanel.addOnlineFriend(user);
									}
								}
							});
							jPopupMenu.add(jMenuItem1);
						}
						JMenuItem jMenuItem2 = new JMenuItem("删除好友");
						jMenuItem2.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								try{
									DataFormat dataFormat = new DataFormat();
									DeleteFriendRequestBean deleteFriendRequestBean = new DeleteFriendRequestBean();
									deleteFriendRequestBean.setAsker(ManagerInfo.getUser());
									deleteFriendRequestBean.setBedeleteder(hashMap.get(idList.get(index)));
									dataFormat.setFromId(ManagerInfo.getUser().getId());
									dataFormat.setToId(hashMap.get(idList.get(index)).getId());
									dataFormat.setType(1);
									dataFormat.setData(deleteFriendRequestBean);
									dataFormat.setTime(System.currentTimeMillis());
									ClientUDP.sendUdpMsg(dataFormat);
								}catch (Exception exception){
									exception.printStackTrace();
								}
							}
						});
						JMenuItem jMenuItem3 = new JMenuItem("查看信息");
						jMenuItem3.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								User user = hashMap.get(idList.get(index));
								UserInfoFrame userInfoFrame = new UserInfoFrame(user);
							}
						});
						jPopupMenu.add(jMenuItem3);
						jPopupMenu.add(jMenuItem2);
						jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
		}
		listScroller = new JScrollPane(jList);
		listScroller.setBounds(0, 200, 400, 432);

// setBackground(Color.blue);
		this.getContentPane().setBackground(Color.white);
		//this.getContentPane().setBackground(new Color(128, 159, 255));
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				try{
					DataFormat dataFormat = new DataFormat();
					dataFormat.setTime(System.currentTimeMillis());
					dataFormat.setType(DataFormat.QUIT);
					dataFormat.setToId(0);
					dataFormat.setFromId(ManagerInfo.getUser().getId());
					ClientUDP.sendUdpMsg(dataFormat);
				}catch (Exception exception){
					exception.printStackTrace();
				}
			}
		});
	}

	public void resetList(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				remove(listScroller);
				HashMap<Integer, User> hashMap = (HashMap<Integer, User>) ManagerInfo.getUserMap();
				if(hashMap != null){
					listModel = new DefaultListModel();
					Iterator iterator = hashMap.entrySet().iterator();
					ArrayList<Integer> idList = new ArrayList<Integer>();
					ArrayList<User> onlineUsers = new ArrayList<User>();
					ArrayList<User> offlineUsers = new ArrayList<User>();
					ArrayList<Icon> icons = new ArrayList<Icon>();
					while (iterator.hasNext()){
						HashMap.Entry<Integer, User> entry = (HashMap.Entry<Integer, User>) iterator.next();
						User user = entry.getValue();
						if(user.getStatus() == true){
							onlineUsers.add(user);
						}else{
							offlineUsers.add(user);
						}
					}
					for(int i = 0;i < onlineUsers.size();i++){
						User user = onlineUsers.get(i);
						listModel.add(i, user.getUserName());
						idList.add(user.getId());
						ImageIcon imageIcon = new ImageIcon(PicturePath.getPicturePath(user.getAvatarSrc()));
						imageIcon.setImage(imageIcon.getImage().getScaledInstance(40 ,40, Image.SCALE_DEFAULT));
						icons.add(imageIcon);
					}
					int count = onlineUsers.size();
					for(int i = 0;i < offlineUsers.size();i++){
						User user = offlineUsers.get(i);
						listModel.add(count, "不在线    " + user.getUserName());
						idList.add(user.getId());
						ImageIcon imageIcon = new ImageIcon(PicturePath.getPicturePath(user.getAvatarSrc()));
						imageIcon.setImage(imageIcon.getImage().getScaledInstance(40 ,40, Image.SCALE_DEFAULT));
						icons.add(imageIcon);
						count++;
					}
					jList = new JList(listModel);
					jList.setCellRenderer(new CellRenderer(icons));
					jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					jList.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							JList theList = (JList) e.getSource();
							int index = theList.locationToIndex(e.getPoint());
							if (e.getClickCount() == 2 && index < onlineUsers.size()) {
								User user = hashMap.get(idList.get(index));
								ChatPane chatPanel = (ChatPane) ManagerPanel.get("chatPanel");
								if(chatPanel == null){
									chatPanel = new ChatPane(user, ManagerInfo.getUser().getId(), ManagerInfo.getUser().getUserName());
									ManagerPanel.add("chatPanel", chatPanel);
								}else {
									chatPanel.addOnlineFriend(user);
								}
							}else if(e.getButton() == 3){
								JPopupMenu jPopupMenu = new JPopupMenu();
								if(index < onlineUsers.size()){
									JMenuItem jMenuItem1 = new JMenuItem("进行聊天");
									jMenuItem1.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseReleased(MouseEvent e) {
											User user = hashMap.get(idList.get(index));
											ChatPane chatPanel = (ChatPane) ManagerPanel.get("chatPanel");
											if(chatPanel == null){
												chatPanel = new ChatPane(user, ManagerInfo.getUser().getId(), ManagerInfo.getUser().getUserName());
												ManagerPanel.add("chatPanel", chatPanel);
											}else {
												chatPanel.addOnlineFriend(user);
											}
										}
									});
									jPopupMenu.add(jMenuItem1);
								}
								JMenuItem jMenuItem2 = new JMenuItem("删除好友");
								jMenuItem2.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										try{
											DataFormat dataFormat = new DataFormat();
											DeleteFriendRequestBean deleteFriendRequestBean = new DeleteFriendRequestBean();
											deleteFriendRequestBean.setAsker(ManagerInfo.getUser());
											deleteFriendRequestBean.setBedeleteder(hashMap.get(idList.get(index)));
											dataFormat.setFromId(ManagerInfo.getUser().getId());
											dataFormat.setToId(hashMap.get(idList.get(index)).getId());
											dataFormat.setType(1);
											dataFormat.setData(deleteFriendRequestBean);
											dataFormat.setTime(System.currentTimeMillis());
											ClientUDP.sendUdpMsg(dataFormat);
										}catch (Exception exception){
											exception.printStackTrace();
										}
									}
								});
								JMenuItem jMenuItem3 = new JMenuItem("查看信息");
								jMenuItem3.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										User user = hashMap.get(idList.get(index));
										UserInfoFrame userInfoFrame = new UserInfoFrame(user);
									}
								});
								jPopupMenu.add(jMenuItem3);
								jPopupMenu.add(jMenuItem2);
								jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
							}
						}
					});
				}else {
					jList = new JList();
				}
				listScroller = new JScrollPane(jList);
				listScroller.setBounds(0, 200, 400, 432);
				add(listScroller);
				listScroller.validate();
			}
		});
	}

	public void showMessage(String message){
		JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showFriendRequest(User user){
		try{
			int i = JOptionPane.showConfirmDialog(null,user.getUserName() + "请求加你为好友。","好友请求",JOptionPane.YES_NO_OPTION);
			AddFriendRequestBean addFriendRequestBean = new AddFriendRequestBean();
			addFriendRequestBean.setUser(ManagerInfo.getUser());
			if (i == 0){
				addFriendRequestBean.setType(1);
			}
			if (i == 1){
				addFriendRequestBean.setType(2);
			}
			DataFormat dataFormat = new DataFormat();
			dataFormat.setFromId(ManagerInfo.getUser().getId());
			dataFormat.setToId(user.getId());
			dataFormat.setType(0);
			dataFormat.setData(addFriendRequestBean);
			dataFormat.setTime(System.currentTimeMillis());
			ClientUDP.sendUdpMsg(dataFormat);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
