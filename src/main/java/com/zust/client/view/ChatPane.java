package com.zust.client.view;

import com.zust.common.bean.DataFormat;
import com.zust.common.bean.User;
import com.zust.common.tool.PicturePath;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ChatPane {
	Integer fromId;
	List<User> friends;
	JTabbedPane tp;
	String userName;
	User firstFriend;
	public ChatPane(User firstfriend,Integer fromId,String userName){
		this.firstFriend=firstFriend;
		this.fromId=fromId;
		this.userName=userName;
		createAndShowGUI();
	}

	public  void createAndShowGUI(){
		JPanel myTabbedPane=new JPanel();
		myTabbedPane.setLayout(new GridLayout(1, 1));
		//创建JTabbedPane
		tp = new JTabbedPane();
		addOnlineFriend(firstFriend);

		tp.setPreferredSize(new Dimension(500,500));
		myTabbedPane.add(tp);
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tp.setTabPlacement(JTabbedPane.LEFT);
// 往窗口添加myTabbedPane
		JFrame frame = new JFrame("聊天窗口");
		ImageIcon logo=new ImageIcon(PicturePath.getPicturePath("/image/logo.jpg"));
		logo.setImage(logo.getImage().getScaledInstance(25, 30,
				Image.SCALE_DEFAULT));
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(myTabbedPane);
		frame.pack();
		frame.setVisible(true);

	}

	//左边每一个tab图标：
	private ImageIcon createImageIcon(String picSrc) {
		if(picSrc == null)
		{
			System.out.println("the image "+picSrc+" is not exist!");
			return null;
		}
		else{
			ImageIcon icon=new ImageIcon(picSrc);
			//设置icon的大小
			icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			return icon;
		}

	}
	//    好友下线：
	public void delteOfflineFriend(Integer deleteId){
		for (int i=0;i<tp.getTabCount();i++){
			Component component=tp.getTabComponentAt(i);

			if (component.getName().equals(deleteId.toString())){
				tp.remove(tp.indexOfTabComponent(component));
				System.out.println(component.getName()+" is offline!!!");
			}
		}
	}
	//    好友上线：
	public void addOnlineFriend(User friend){
		Integer id=friend.getId();
		String avatarSrc=friend.getAvatarSrc();
		String toUserName=friend.getUserName();
		ImageIcon myImageIcon = createImageIcon(avatarSrc);
		ChatPanel chatPanel= new ChatPanel(userName,toUserName,tp,fromId,id);
		tp.addTab(toUserName,myImageIcon,chatPanel);
		if(tp.getTabCount()==1){
			JPanel singleTabPanel=new SingleTabPanel(toUserName,myImageIcon,tp,0);
			tp.setTabComponentAt(0, singleTabPanel);
			singleTabPanel.setName(id.toString());
		}
		else{
			JPanel singleTabPanel=new SingleTabPanel(toUserName,myImageIcon,tp,tp.getTabCount()-1);
			tp.setTabComponentAt(tp.getTabCount()-1, singleTabPanel);
			singleTabPanel.setName(id.toString());
			System.out.println("userId="+id+" is online!!!");
		}

	}
	public void receiveMsg(String message,Integer toId)
	{

		for(int i=0;i<tp.getTabCount();i++){
			if(String.valueOf(toId).equals(tp.getTabComponentAt(i).getName())){
				//聊天面板添加聊天信息：
				ChatPanel chatPanel=(ChatPanel)tp.getComponentAt(i);
				chatPanel.showMsg(message);
				SingleTabPanel singleTabPanel=(SingleTabPanel)tp.getTabComponentAt(i);
				singleTabPanel.changeMsgNum("add");
			}
		}


	}
}



