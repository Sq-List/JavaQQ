package com.zust.client.view;

import com.zust.common.tool.PicturePath;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
//    private static final long serialVersionUID = -6788045638380819221L;

    private JTextField ulName;
    private JPasswordField ulPasswd;

    private JLabel ground;
    private JLabel smallCon;

    private JLabel profilePic;
    private JLabel nameLable;
    private JLabel passwdLable;
    private JLabel reg;

    private JButton b1;
    /**
     * 初始化QQ登录页面
     * */
    public Login(){
        //设置登录窗口标题
        this.setTitle("登录");
        //采用指定的窗口装饰风格
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 400, 320);
        //设置窗体的图标
        Image img0 = new ImageIcon(PicturePath.getPicturePath("/image/logo.png")).getImage();
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
        ImageIcon img1 = new ImageIcon(PicturePath.getPicturePath("/image/background.jpeg"));
        img1.setImage(img1.getImage().getScaledInstance(400,350,Image.SCALE_DEFAULT));
        ground.setIcon(img1);

        //qq头像设定
        profilePic = new JLabel();
        ImageIcon img2 = new ImageIcon(PicturePath.getPicturePath("/image/logo.jpg"));
        profilePic.setBounds(150, 10, 100, 100);
        img2.setImage(img2.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        profilePic.setIcon(img2);

        //账号和密码的外部容器
        smallCon = new JLabel();
        smallCon.setLayout(new GridLayout(2,2,5,5));
        smallCon.setBounds(100,150,200,50);

        //账号&密码
        nameLable = new JLabel("输入账号");
        nameLable.setFont(font);
        nameLable.setForeground(Color.WHITE);
        ulName = new JTextField();
        ulName.setBorder(BorderFactory.createEmptyBorder());
        passwdLable= new JLabel("输入密码");
        passwdLable.setFont(font);
        passwdLable.setForeground(Color.WHITE);
        ulPasswd = new JPasswordField();
        ulPasswd.setBorder(BorderFactory.createEmptyBorder());

        //登录按钮
        b1 = new JButton("登录");
        b1.setFont(font);
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(170, 230, 60, 20);
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
        //注册
        reg = new JLabel("未有账号？点击注册");
        reg.setFont(font);
        reg.setForeground(Color.WHITE);
        reg.setBounds(20,270,200,20);
        reg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //所有组件用容器装载
        ground.add(smallCon);
        ground.add(profilePic);
        smallCon.add(nameLable);
        smallCon.add(ulName);
        smallCon.add(passwdLable);
        smallCon.add(ulPasswd);
        ground.add(b1);
        ground.add(reg);
        container.add(ground);
    }
    public static void main(String[] args) {
        new Login();
    }
}
