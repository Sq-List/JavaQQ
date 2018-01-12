package com.zust.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditInfo extends JFrame{
	JLabel username;
	JTextField usernameText;
	JLabel gender;
	JTextField genderText;
	JLabel birthday;
	JTextField birthdayText;
	JLabel age;
	JTextField ageText;
	JLabel address;
	JTextField addressText;
	JLabel mobile;
	JTextField mobileText;
	JLabel email;
	JTextField emailText;
	JLabel signature;
	JTextArea signatureText;
	JButton saveBtn;
	JButton cancelBtn;
	
	public EditInfo() { 
		setLocation(500,100);  
		setSize(400,400);
		setVisible(true);  
		setLayout(null);
		setTitle("修改信息");
		setBackground(Color.white);
		
		username = new JLabel("姓  名");
		username.setBounds(30, 20, 40, 20);
		username.setFont(new Font("微软雅黑",0,15));
		usernameText = new JTextField();
		usernameText.setBounds(80, 20, 100, 20);
		
		gender = new JLabel("性  别");
		gender.setBounds(200,20,40,20);
		gender.setFont(new Font("微软雅黑",0,15));
		genderText = new JTextField();
		genderText.setBounds(250,20,100,20);
		
		birthday = new JLabel("生  日");
		birthday.setBounds(30, 55, 40, 20);
		birthday.setFont(new Font("微软雅黑",0,15));
		birthdayText = new JTextField();
		birthdayText.setBounds(80, 55, 100, 20);
		
		age = new JLabel("年  龄");
		age.setBounds(200,55,40,20);
		age.setFont(new Font("微软雅黑",0,15));
		ageText = new JTextField();
		ageText.setBounds(250,55,100,20);
		
		address = new JLabel("地  址");
		address.setBounds(30,90,40,20);
		address.setFont(new Font("微软雅黑",0,15));
		addressText = new JTextField();
		addressText.setBounds(80,90,270,20);
		
		mobile = new JLabel("手  机");
		mobile.setBounds(30,125,40,20);
		mobile.setFont(new Font("微软雅黑",0,15));
		mobileText = new JTextField();
		mobileText.setBounds(80,125,270,20);
		
		email = new JLabel("邮  箱");
		email.setBounds(30,160,40,20);
		email.setFont(new Font("微软雅黑",0,15));
		emailText = new JTextField();
		emailText.setBounds(80,160,270,20);
		
		signature = new JLabel("签  名");
		signature.setBounds(30,195,40,20);
		signature.setFont(new Font("微软雅黑",0,15));
		signatureText = new JTextArea();
		signatureText.setBounds(80,195,270,50);
		signatureText.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		saveBtn = new JButton("保存");
		saveBtn.setBounds(80,275,80,30);
		saveBtn.setBackground(new Color(59,68,92));
		saveBtn.setForeground(Color.white);
		saveBtn.setFont(new Font("微软雅黑",0,14));
		saveBtn.addActionListener(new ActionListener() {  
  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
                // System.exit(0);  
            	dispose();  
            }  
        });  
		
		cancelBtn = new JButton("取消");
		cancelBtn.setBounds(220,275,80,30);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.setFont(new Font("微软雅黑",0,14));
		cancelBtn.addActionListener(new ActionListener() {  
			  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
                // System.exit(0);  
            	dispose();  
            }  
        }); 
		
		add(username);
		add(usernameText);
		add(gender);
		add(genderText);
		add(birthday);
		add(birthdayText);
		add(age);
		add(ageText);
		add(address);
		add(addressText);
		add(mobile);
		add(mobileText);
		add(email);
		add(emailText);
		add(signature);
		add(signatureText);
		add(saveBtn);
		add(cancelBtn);
	}
}
