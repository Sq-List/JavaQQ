package com.zust.client.view;

import com.zust.common.bean.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatPane
{

	List<User> friends;
	JTabbedPane tp;

	public ChatPane(List<User> friends)
	{
		this.friends = friends;
		createAndShowGUI();
	}

	public void createAndShowGUI()
	{
		JPanel myTabbedPane = new JPanel();
		myTabbedPane.setLayout(new GridLayout(1, 1));
		//创建JTabbedPane
		tp = new JTabbedPane();
		for (int i = 0; i < friends.size(); i++)
		{
			User friend = friends.get(i);
			//创建标签显示的图标
			ImageIcon myImageIcon = createImageIcon(friend.getAvatarSrc());
			//创建一个标签下的聊天panel
			ChatPanel chatPanel = new ChatPanel(friend.getUserName(), tp);
			//指定标签名:userName，标签图标，显示窗口面板panel，和提示信息tip
			tp.addTab(friend.getUserName(), myImageIcon, chatPanel);
			JPanel singleTabPanel = new SingleTabPanel(friend.getUserName(), myImageIcon, tp, i);
			tp.setTabComponentAt(i, singleTabPanel);
			singleTabPanel.setName(friend.getId().toString());
		}
		//设置合适的显示尺寸，这个是必须的，因为如果所有的标签都；不指定适合的显示尺寸，系统无法判断初始显示尺寸大小；默认是使用最小化，并且对一个标签设计即可
		tp.setPreferredSize(new Dimension(500, 500));
		//将tabbedPanel添加到Jpanel中
		myTabbedPane.add(tp);
		//设置窗口过小时，标签的显示策略
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		//设置标签停放的位置，这里设置为左侧停放
		tp.setTabPlacement(JTabbedPane.LEFT);
		// 往窗口添加myTabbedPane
		JFrame frame = new JFrame("聊天窗口");
		ImageIcon logo = new ImageIcon("src/image/logo.jpg");
		logo.setImage(logo.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT));
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(myTabbedPane);
		frame.pack();
		frame.setVisible(true);
		for (int i = 0; i < tp.getTabCount(); i++)
		{
			System.out.println("name:" + tp.getTabComponentAt(i).getName());
			delteOfflineFriend(2);
		}

	}

	//左边每一个tab图标：
	private ImageIcon createImageIcon(String picSrc)
	{
		if (picSrc == null)
		{
			System.out.println("the image " + picSrc + " is not exist!");
			return null;
		}
		else
		{
			ImageIcon icon = new ImageIcon(picSrc);
			//设置icon的大小
			icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			return icon;
		}

	}

	//    好友下线：
	public void delteOfflineFriend(Integer deleteId)
	{
		//        if (deleteId!=null){
		for (int i = 0; i < tp.getTabCount(); i++)
		{
			Component component = tp.getTabComponentAt(i);
			System.out.println(component.getName() + " is offline!!!");
			if (component.getName().equals(deleteId.toString()))
			{
				tp.remove(component);
			}
		}
		//        }
	}

}



