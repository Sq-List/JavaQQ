package com.zust.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditPassword extends JFrame{
	JLabel oldPsw;
	JTextField oldPswText;
	JLabel newPsw;
	JTextField newPswText;
	JLabel againPsw;
	JTextField againPswText;
	JButton saveBtn;
	JButton cancelBtn;
	
	public EditPassword() {
		setLocation(500,300);  
		setSize(400,280);
		setVisible(true);  
		setLayout(null);
		setTitle("修改密码");
		this.getContentPane().setBackground(Color.white);
		
		oldPsw = new JLabel("原密码");
		oldPsw.setBounds(30,20,60,20);
		oldPsw.setFont(new Font("微软雅黑",0,15));
		oldPswText = new JTextField();
		oldPswText.setBounds(100,20,250,20);
		
		newPsw = new JLabel("新密码");
		newPsw.setBounds(30,60,60,20);
		newPsw.setFont(new Font("微软雅黑",0,15));
		newPswText = new JTextField();
		newPswText.setBounds(100,60,250,20);

		againPsw = new JLabel("确认密码");
		againPsw.setBounds(30,100,60,20);
		againPsw.setFont(new Font("微软雅黑",0,15));
		againPswText = new JTextField();
		againPswText.setBounds(100,100,250,20);
		
		saveBtn = new JButton("保存");
		saveBtn.setBounds(80,150,80,30);
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
		cancelBtn.setBounds(220,150,80,30);
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
		
		add(oldPsw);
		add(oldPswText);
		add(newPsw);
		add(newPswText);
		add(againPsw);
		add(againPswText);
		add(saveBtn);
		add(cancelBtn);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
