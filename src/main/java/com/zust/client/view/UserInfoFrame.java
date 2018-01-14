package com.zust.client.view;

import com.zust.client.manager.ManagerInfo;
import com.zust.common.bean.User;
import com.zust.common.tool.PicturePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class UserInfoFrame extends JFrame{
	JPanel topContent;
	JPanel centerContent;
	JLabel circleImage;
	JLabel username;
	JLabel signature;
	JLabel editPassword;
	JLabel editInfo;
	JLabel accountIcon;
	JLabel account;
	JLabel genderIcon;
	JLabel gender;
	JLabel age;
	JLabel birthdayIcon;
	JLabel birthday;
	JLabel addressIcon;
	JLabel address;
//	JLabel introduce;
//	JLabel introduceText;
	public UserInfoFrame(User user) {
		setLayout(new BorderLayout());
		topContent = new JPanel(null);
		centerContent = new JPanel(null);
		
		setBounds(500,100,400,500);
		setTitle("个人信息");
		
		topContent.setPreferredSize(new Dimension(300, 160));;
		topContent.setBackground(new Color(59,68,92));
		centerContent.setBackground(Color.WHITE);
		
		circleImage = new JLabel(new ImageIcon(PicturePath.getPicturePath(user.getAvatarSrc())));
		circleImage.setBounds(50,20,100,100);
		
		username = new JLabel(user.getUserName());
		username.setBounds(180,30,50,50);
		username.setFont(new Font("微软雅黑",0,16));
		username.setForeground(Color.white);
		
		signature = new JLabel(user.getIntro());
		signature.setBounds(180,40,150,100);
		signature.setFont(new Font("微软雅黑",0,15));
		signature.setForeground(Color.white);

		if (ManagerInfo.getUser() == user)
		{
			editInfo = new JLabel("编辑资料");
			editInfo.setBounds(310,10,70,20);
			editInfo.setFont(new Font("微软雅黑",0,15));
			editInfo.setForeground(Color.white);
			editInfo.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					EditInfo editInfo = new EditInfo();
				}
				public void mouseEntered(MouseEvent e) {
					// 处理鼠标移入
				}
				public void mouseExited(MouseEvent e) {
					// 处理鼠标离开
				}
				public void mousePressed(MouseEvent e) {
					// 处理鼠标按下
				}
				public void mouseReleased(MouseEvent e) {
					// 处理鼠标释放
				}
			});

			editPassword = new JLabel("修改密码");
			editPassword.setBounds(70,120,70,20);
			editPassword.setFont(new Font("微软雅黑",0,15));
			editPassword.setForeground(Color.white);
			editPassword.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					EditPassword editpsw = new EditPassword();
				}
				public void mouseEntered(MouseEvent e) {
					// 处理鼠标移入
				}
				public void mouseExited(MouseEvent e) {
					// 处理鼠标离开
				}
				public void mousePressed(MouseEvent e) {
					// 处理鼠标按下
				}
				public void mouseReleased(MouseEvent e) {
					// 处理鼠标释放
				}
			});
		}
		
		accountIcon = new JLabel(new ImageIcon(PicturePath.getPicturePath("/image/account.png")));
		accountIcon.setBounds(40,20,30,30);
		account = new JLabel(user.getId() + "");
		account.setBounds(80,20,100,30);
		account.setFont(new Font("微软雅黑",0,15));
		
		genderIcon = new JLabel(new ImageIcon(PicturePath.getPicturePath("/image/gender.png")));
		genderIcon.setBounds(40,55,30,30);
		gender = new JLabel(user.getGender());
		gender.setBounds(80,55,100,30);
		gender.setFont(new Font("微软雅黑",0,15));
		
		age = new JLabel(user.getAge() + "");
		age.setBounds(130,55,100,30);
		age.setFont(new Font("微软雅黑",0,15));
		
		birthdayIcon = new JLabel(new ImageIcon(PicturePath.getPicturePath("/image/birthday.png")));
		birthdayIcon.setBounds(40,90,30,30);
		birthday = new JLabel(user.getBirthday());
		birthday.setBounds(80,90,200,30);
		birthday.setFont(new Font("微软雅黑",0,15));
		
		addressIcon = new JLabel(new ImageIcon(PicturePath.getPicturePath("/image/address.png")));
		addressIcon.setBounds(40,125,30,30);
		address = new JLabel(user.getAddress());
		address.setBounds(80,125,200,30);
		address.setFont(new Font("微软雅黑",0,15));
		
		ImageIcon logo=new ImageIcon(PicturePath.class.getResource("/image/logo1.jpg"));
		logo.setImage(logo.getImage().getScaledInstance(25, 30,
				Image.SCALE_DEFAULT));
		setIconImage(logo.getImage());
//		introduce = new JLabel("自我介绍：");
//		introduce.setBounds(50,160,80,30);
//		introduce.setFont(new Font("微软雅黑",0,15));
//		introduce.setForeground(new Color(141,141,143));
//		introduceText = new JLabel("大家好！");
//		introduceText.setBounds(130,160,200,30);
//		introduceText.setFont(new Font("微软雅黑",0,15));
		
		topContent.add(circleImage);
		topContent.add(username);
		topContent.add(signature);
		if (ManagerInfo.getUser() == user)
		{
			topContent.add(editInfo);
			topContent.add(editPassword);
		}
		centerContent.add(accountIcon);
		centerContent.add(account);
		centerContent.add(genderIcon);
		centerContent.add(gender);
		centerContent.add(age);
		centerContent.add(birthdayIcon);
		centerContent.add(birthday);
		centerContent.add(addressIcon);
		centerContent.add(address);
//		centerContent.add(introduce);
//		centerContent.add(introduceText);
		
		add(BorderLayout.NORTH,topContent);
		add(BorderLayout.CENTER, centerContent);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
}
