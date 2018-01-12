package com.zust.client.view;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame{

    private JTextField ulName;
    private JPasswordField ulPasswd;
    private JPasswordField cfmPasswd;

    private JLabel ground;
    private JLabel smallCon;

    private JLabel profilePic;
    private JLabel nameLable;
    private JLabel passwdLable;
    private JLabel loginMsg;
    private JLabel cfmLable;

    private JButton b1;

    /**
     * 初始化QQ注册页面
     * */
    public Register(){
        //设置注册窗口标题
        this.setTitle("注册");
        //采用指定的窗口装饰风格
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 400, 320);
        //设置窗体的图标
        Image img0 = new ImageIcon("qq/img/logo.png").getImage();
        this.setIconImage(img0);
        //窗体大小不能改变
        this.setResizable(false);
        //居中显示
        this.setLocationRelativeTo(null);
        //窗体显示
        this.setVisible(true);
    }
    /**
     * 窗体组件初始化
     * */
    public void init(){
        Font font = new Font("微软雅黑",Font.PLAIN,12);

        //创建容器
        Container container = this.getContentPane();

        //底层面板
        ground = new JLabel();
        ground.setBounds(0, 0, 400, 320);

        //设置背景色
        ImageIcon img1 = new ImageIcon("qq/img/background.jpeg");
        img1.setImage(img1.getImage().getScaledInstance(400,350,Image.SCALE_DEFAULT));
        ground.setIcon(img1);

        //qq头像设定
        profilePic = new JLabel();
        ImageIcon img2 = new ImageIcon("qq/img/logo.jpg");
        profilePic.setBounds(150, 10, 100, 100);
        img2.setImage(img2.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        profilePic.setIcon(img2);

        //账号和密码的外部容器
        smallCon = new JLabel();
        smallCon.setLayout(new GridLayout(3,2,5,5));
        smallCon.setBounds(100,150,200,70);

        //账号&密码
        nameLable = new JLabel("输入用户名");
        nameLable.setFont(font);
        nameLable.setForeground(Color.WHITE);

        ulName = new JTextField();
        ulName.setBorder(BorderFactory.createEmptyBorder());

        passwdLable= new JLabel("输入密码");
        passwdLable.setFont(font);
        passwdLable.setForeground(Color.WHITE);

        ulPasswd = new JPasswordField();
        ulPasswd.setBorder(BorderFactory.createEmptyBorder());

        cfmLable = new JLabel("确认密码");
        cfmLable.setFont(font);
        cfmLable.setForeground(Color.WHITE);

        cfmPasswd = new JPasswordField();
        cfmPasswd.setBorder(BorderFactory.createEmptyBorder());

        //注册按钮
        b1 = new JButton("注册");
        b1.setFont(font);
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(170, 250, 60, 20);
//        给按钮添加
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String cmd = e.getActionCommand();
//                if("登录".equals(cmd)){
//                    String username = ulName.getText();
//                    String userpassword = ulPasswd.getText();
//                    if(username.equals("tskk") && userpassword.equals("123456")){
//                        JOptionPane.showConfirmDialog(null, "登录成功");
//                    }else{
//                        JOptionPane.showConfirmDialog(null, "登录失败");
//                    }
//                }
//            }
//        });
        //登录
        loginMsg = new JLabel("已有账号？前往登录");
        loginMsg.setFont(font);
        loginMsg.setForeground(Color.WHITE);
        loginMsg.setBounds(20,270,200,20);
        loginMsg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //所有组件用容器装载
        ground.add(smallCon);
        ground.add(profilePic);
        smallCon.add(nameLable);
        smallCon.add(ulName);
        smallCon.add(passwdLable);
        smallCon.add(ulPasswd);
        smallCon.add(cfmLable);
        smallCon.add(cfmPasswd);
        ground.add(b1);
        ground.add(loginMsg);
        container.add(ground);
    }
    public static void main(String[] args) {
        new Register();
    }
}
