package com.zust.client.view;

import com.zust.client.manager.ManagerInfo;
import com.zust.client.manager.ManagerPanel;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.User;
import com.zust.common.tool.DesUtil;
import com.zust.common.tool.PicturePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

public class ChatPane {
	Integer fromId;
	JTabbedPane tp;
	String userName;
	User firstfriend;
	static JFrame frame;
	JPanel myTabbedPane;
	public ChatPane(User firstfriend,Integer fromId,String userName){
		this.firstfriend=firstfriend;
		this.fromId=fromId;
		this.userName=userName;
		createAndShowGUI();
	}

	public  void createAndShowGUI(){
		myTabbedPane=new JPanel();
		myTabbedPane.setLayout(new GridLayout(1, 1));
		//创建JTabbedPane
		tp = new JTabbedPane();
		addOnlineFriend(firstfriend);

		tp.setPreferredSize(new Dimension(600,500));
		myTabbedPane.add(tp);
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tp.setTabPlacement(JTabbedPane.LEFT);
// 往窗口添加myTabbedPane
		frame = new JFrame("聊天窗口");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 400;
		int height = 700;
		frame.setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
		ImageIcon logo=new ImageIcon(PicturePath.class.getResource("/image/logo.jpg"));
		logo.setImage(logo.getImage().getScaledInstance(25, 30,
				Image.SCALE_DEFAULT));
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(myTabbedPane);
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(WindowEvent e)
			{
				super.windowClosing(e);
				ManagerPanel.delete("chatPanel");
			}
		});

	}

	//左边每一个tab图标：
	private ImageIcon createImageIcon(String picSrc) {
		if(picSrc == null)
		{
			System.out.println("the image "+picSrc+" is not exist!");
			return null;
		}
		else{
			ImageIcon icon=new ImageIcon(PicturePath.class.getResource(picSrc));
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
				if(tp.getTabCount()==0){
					System.out.println("there is no friend on the chatPane!");
					ManagerPanel.delete("chatPanel");
					ChatPane.frame.dispose();
				}
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
		Boolean hasFriendFlag=false;
		if (tp!=null){
			if(tp.getTabCount()==0){
				tp.addTab(toUserName,myImageIcon,chatPanel);
				JPanel singleTabPanel=new SingleTabPanel(toUserName,myImageIcon,tp,0);
				tp.setTabComponentAt(0, singleTabPanel);
				singleTabPanel.setName(id.toString());
			}
			else{
				for(int i=0;i<tp.getTabCount();i++){
					if(String.valueOf(id).equals(tp.getTabComponentAt(i).getName())) {
						hasFriendFlag=true;
						break;
					}
				}
				if(!hasFriendFlag){
					tp.addTab(toUserName,myImageIcon,chatPanel);
					JPanel singleTabPanel=new SingleTabPanel(toUserName,myImageIcon,tp,tp.getTabCount()-1);
					tp.setTabComponentAt(tp.getTabCount()-1, singleTabPanel);
					singleTabPanel.setName(id.toString());
					System.out.println("userId="+id+" is online and added to panel!!!");

				}

			}
		}

	}
	public void receiveMsg(String message,Integer toId)
	{

		int count = 0;

		try {
			message = DesUtil.decrypt(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for(int i=0;i<tp.getTabCount();i++){
			if(String.valueOf(toId).equals(tp.getTabComponentAt(i).getName())){
				//聊天面板添加聊天信息：
				ChatPanel chatPanel=(ChatPanel)tp.getComponentAt(i);
				chatPanel.showMsg(message);
				SingleTabPanel singleTabPanel=(SingleTabPanel)tp.getTabComponentAt(i);
				singleTabPanel.changeMsgNum("add");
				count++;
				break;
			}
		}

		if(count == 0){
			addOnlineFriend(ManagerInfo.getUserMap().get(toId));
			//聊天面板添加聊天信息：
			ChatPanel chatPanel=(ChatPanel)tp.getComponentAt(tp.getTabCount()-1);
			chatPanel.showMsg(message);
			SingleTabPanel singleTabPanel=(SingleTabPanel)tp.getTabComponentAt(tp.getTabCount()-1);
			singleTabPanel.changeMsgNum("add");
		}

	}
}





