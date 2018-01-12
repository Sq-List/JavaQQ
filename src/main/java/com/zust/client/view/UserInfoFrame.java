package com.zust.client.view;

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
	JLabel introduce;
	JLabel introduceText;
	public UserInfoFrame() {
		setLayout(new BorderLayout());
		topContent = new JPanel(null);
		centerContent = new JPanel(null);
		
		setBounds(500,100,400,500);
		setTitle("个人信息");
		
		topContent.setPreferredSize(new Dimension(300, 160));;
		topContent.setBackground(new Color(59,68,92));
		centerContent.setBackground(Color.WHITE);
		
		circleImage = new JLabel(new ImageIcon("images/portrait1.png"));
		circleImage.setBounds(50,20,100,100);
		
		username = new JLabel("某某");
		username.setBounds(180,30,50,50);
		username.setFont(new Font("微软雅黑",0,16));
		username.setForeground(Color.white);
		
		signature = new JLabel("还没有签名");
		signature.setBounds(180,40,100,100);
		signature.setFont(new Font("微软雅黑",0,15));
		signature.setForeground(Color.white);
		
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
		
		accountIcon = new JLabel(new ImageIcon("images/account.png")); 
		accountIcon.setBounds(40,20,30,30);
		account = new JLabel("12333");
		account.setBounds(80,20,100,30);
		account.setFont(new Font("微软雅黑",0,15));
		
		genderIcon = new JLabel(new ImageIcon("images/gender.png")); 
		genderIcon.setBounds(40,55,30,30);
		gender = new JLabel("女");
		gender.setBounds(80,55,100,30);
		gender.setFont(new Font("微软雅黑",0,15));
		
		age = new JLabel("14岁"); 
		age.setBounds(130,55,100,30);
		age.setFont(new Font("微软雅黑",0,15));
		
		birthdayIcon = new JLabel(new ImageIcon("images/birthday.png"));
		birthdayIcon.setBounds(40,90,30,30);
		birthday = new JLabel("2005年10月8日"); 
		birthday.setBounds(80,90,200,30);
		birthday.setFont(new Font("微软雅黑",0,15));
		
		addressIcon = new JLabel(new ImageIcon("images/address.png"));
		addressIcon.setBounds(40,125,30,30);
		address = new JLabel("浙江省  杭州市  西湖区");
		address.setBounds(80,125,200,30);
		address.setFont(new Font("微软雅黑",0,15));
		
		introduce = new JLabel("自我介绍：");
		introduce.setBounds(50,160,80,30);
		introduce.setFont(new Font("微软雅黑",0,15));
		introduce.setForeground(new Color(141,141,143));
		introduceText = new JLabel("大家好！");
		introduceText.setBounds(130,160,200,30);
		introduceText.setFont(new Font("微软雅黑",0,15));
		
		topContent.add(circleImage);
		topContent.add(username);
		topContent.add(signature);
		topContent.add(editInfo);
		topContent.add(editPassword);
		centerContent.add(accountIcon);
		centerContent.add(account);
		centerContent.add(genderIcon);
		centerContent.add(gender);
		centerContent.add(age);
		centerContent.add(birthdayIcon);
		centerContent.add(birthday);
		centerContent.add(addressIcon);
		centerContent.add(address);
		centerContent.add(introduce);
		centerContent.add(introduceText);
		
		add(BorderLayout.NORTH,topContent);
		add(BorderLayout.CENTER, centerContent);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
