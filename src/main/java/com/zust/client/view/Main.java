package com.zust.client.view;

import com.zust.client.UDP.ClientUDP;
import com.zust.client.manager.ManagerInfo;
import com.zust.client.manager.ManagerPanel;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.DeleteFriendRequestBean;
import com.zust.common.bean.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		label5.setBounds(0,630,400,2);
		label5.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray), BorderFactory.createEmptyBorder(1,1,1,1)));
		label6.setBounds(130,630,140,40);

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

		listModel = new DefaultListModel();
		String[] data = { "好友1", "好友2", "好友3", "好友4", "好友5", "好友6", "好友7",
				"好友8", "好友9", "好友10", "好友11" , "好友12" , "好友13" , "好友14"
				, "好友15" , "好友16" , "不在线    好友17" , "不在线    好友18" };
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
					System.out.println(index);
				}else if(e.getButton() == 3){
					JPopupMenu jPopupMenu = new JPopupMenu();
					JMenuItem jMenuItem1 = new JMenuItem("进行聊天");
					JMenuItem jMenuItem2 = new JMenuItem("删除好友");
					jMenuItem1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							System.out.println("jinxingliaotian");
						}
					});
					jMenuItem2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							System.out.println("delete");
						}
					});
					jPopupMenu.add(jMenuItem1);
					jPopupMenu.add(jMenuItem2);
					jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		listScroller = new JScrollPane(jList);
		listScroller.setBounds(0, 200, 400, 432);

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				remove(listScroller);
				HashMap<Integer, User> hashMap = (HashMap<Integer, User>) ManagerInfo.getUserMap();
				listModel = new DefaultListModel();
				Iterator iterator = hashMap.entrySet().iterator();
				ArrayList<Integer> idList = new ArrayList<Integer>();
				ArrayList<User> onlineUsers = new ArrayList<User>();
				ArrayList<User> offlineUsers = new ArrayList<User>();
				while (iterator.hasNext()){
					HashMap.Entry<Integer, User> entry = (HashMap.Entry<Integer, User>) iterator.next();
					User user = entry.getValue();
					if(user.isStatus() == true){
						onlineUsers.add(user);
					}else{
						offlineUsers.add(user);
					}
				}
				for(int i = 0;i < onlineUsers.size();i++){
					User user = onlineUsers.get(i);
					listModel.add(i, user.getUserName());
					idList.add(user.getId());
				}
				int count = onlineUsers.size();
				for(int i = 0;i < offlineUsers.size();i++){
					User user = offlineUsers.get(i);
					listModel.add(count, "不在线    " + user.getUserName());
					idList.add(user.getId());
					count++;
				}
				jList = new JList(listModel);
				jList.setCellRenderer(new CellRenderer());
				jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				jList.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JList theList = (JList) e.getSource();
						int index = theList.locationToIndex(e.getPoint());
						if (e.getClickCount() == 2 && index < onlineUsers.size()) {
							if (index >= 0) {
								Object o = theList.getModel().getElementAt(index);
								System.out.println(o.toString());
							}
						}else if(e.getButton() == 3){
							JPopupMenu jPopupMenu = new JPopupMenu();
							if(index < onlineUsers.size()){
								JMenuItem jMenuItem1 = new JMenuItem("进行聊天");
								jMenuItem1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										System.out.println("jinxingliaotian");
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
										dataFormat.setToId(0);
										dataFormat.setType(1);
										dataFormat.setData(deleteFriendRequestBean);
										dataFormat.setTime(System.currentTimeMillis());
										ClientUDP.sendUdpMsg(dataFormat);
									}catch (Exception exception){
										exception.printStackTrace();
									}
								}
							});
							jPopupMenu.add(jMenuItem2);
							jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				});
				listScroller = new JScrollPane(jList);
				listScroller.setBounds(0, 200, 400, 632);
				add(listScroller);
				listScroller.validate();
			}
		});
	}

}
