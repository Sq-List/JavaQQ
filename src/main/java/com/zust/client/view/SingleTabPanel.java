package com.zust.client.view;

import com.zust.client.manager.ManagerPanel;
import com.zust.common.tool.PicturePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
//单个tab样式：
class SingleTabPanel extends JPanel {
	private JLabel userInfo;
	private CloseButton closebutton;
	private final JTabbedPane tabbedPane;
	private JLabel currentMsgNum;
	private  SingleTabPanel singleTabPanel;
	String userName;
	int messageNum=0;
	public SingleTabPanel(String userName,ImageIcon icon,JTabbedPane mytabbedPane,int index){

		this.userName=userName;
		tabbedPane=mytabbedPane;
		BorderLayout borderLayout=new BorderLayout();
		setFont(new Font("Helvetica", Font.PLAIN, 14));
		setLayout(borderLayout);
		setPreferredSize(new Dimension(150,50));
		userInfo=new JLabel(userName,icon,JLabel.LEFT );
		userInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		userInfo.setBackground(new Color(238,238,238));
		Font font = new Font("微软雅黑",Font.PLAIN,12);
		userInfo.setFont(font);
		add(userInfo,BorderLayout.WEST);

//        显示当前信息数量：
		currentMsgNum=new JLabel();
		currentMsgNum.setForeground(Color.red);
		add(currentMsgNum,BorderLayout.CENTER);

		closebutton=new CloseButton();
		closebutton.setPreferredSize(new Dimension(30,50));
		closebutton.setBackground(new Color(238,238,238));
		add(closebutton,BorderLayout.EAST);

		closebutton.setVisible(false);
		setBackground(new Color(200, 221, 242));
		setOpaque(false);
		singleTabPanel=this;
		singleTabPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				//信息确认以读取,置0
				changeMsgNum("delete");
				closebutton.setVisible(true);
				singleTabPanel.setBackground(new Color(200, 221, 242));
				userInfo.setBackground(new Color(200, 221, 242));
				closebutton.setBackground(new Color(200, 221, 242));
				tabbedPane.setSelectedIndex(tabbedPane.indexOfTabComponent(singleTabPanel));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				closebutton.setVisible(true);
				if(e.getSource()==tabbedPane.getTabComponentAt(tabbedPane.getSelectedIndex())){
					closebutton.setBackground(new Color(200, 221, 242));
				}else{
					closebutton.setBackground(new Color(238,238,238));
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				closebutton.setVisible(false);
			}
		});

	};
	//    改变当前信息数量：
	public  void changeMsgNum(String type){
		if(type.equals("add")){
			messageNum++;
			currentMsgNum.setText(String.valueOf(messageNum));
		}
		if(type.equals("delete")){
			messageNum=0;
			currentMsgNum.setText("");
		}
	}
	private class CloseButton extends JButton {
		private ImageIcon icon;
		public CloseButton(){
			icon=new ImageIcon(PicturePath.class.getResource("image/close.png"));
			icon.setImage(icon.getImage().getScaledInstance(15, 15,
					Image.SCALE_DEFAULT));
			setIcon(icon);
			setBorder(null);
			setBorderPainted(false);
			setFocusPainted(false);
			addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					tabbedPane.remove(tabbedPane.indexOfTabComponent(SingleTabPanel.this));
					System.out.println("detete friendId="+SingleTabPanel.this.getName()+" success!");
					if (tabbedPane.getTabCount()==0) {
						System.out.println("there is no friend on the chatPane!");
						ManagerPanel.delete("chatPanel");
						ChatPane.frame.dispose();
					}

				}
			});
		}
	}

}