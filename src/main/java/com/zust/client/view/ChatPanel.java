package com.zust.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.*;

//右边聊天面板：
class ChatPanel extends JPanel {
    String userName;
    JTabbedPane tabbedPane;
    JTextArea showPanel;
    JTextArea editTextArea;
    DatagramSocket receiveSocket,sendSocket;
    DatagramPacket receivePacket,sendPacket;
    //创建一个JPanel，并为构造函数初始false，表示不适用双缓冲
    public ChatPanel(String userName,JTabbedPane tabbedPane){
        this.userName=userName;
        this.tabbedPane=tabbedPane;
        createChatPanel();
//        receiveMessage();
    }

    public void createChatPanel(){
        setLayout(new GridLayout(2,1));
        showPanel=new JTextArea();
        JScrollPane scroll=new JScrollPane(showPanel);
        showPanel.setEditable(false);
//        showPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        showPanel.setLineWrap(true);        //激活自动换行功能
        showPanel.setWrapStyleWord(true);            // 激活断行不断字功能
        add(scroll);//把label加入了panel面板

        JPanel jp1=new JPanel();
        editTextArea=new JTextArea(3,6);//new一个多行输入框，指定 行数和列数分别为3,6
        editTextArea.setPreferredSize(new Dimension(320,150));
        JPanel jp2=new JPanel(new FlowLayout());
        jp2.setPreferredSize(new Dimension(250,50));
        JButton sendBtn=new JButton("发送");
        editTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if(k == e.VK_ENTER)
                {
                    editTextArea.setText("");
                    sendMessage();//发送数据包
                }
            }
        });
        sendBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sendMessage();//发送数据
            }
        });

        JButton closeBtn=new JButton("关闭");
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tabbedPane.remove(tabbedPane.getSelectedIndex());
            }
        });
        jp2.add(sendBtn);
        jp2.add(closeBtn);
//        jp2.add(historyBtn);
        jp1.add(editTextArea,BorderLayout.CENTER);
        jp1.add(jp2,BorderLayout.SOUTH);
        add(jp1);
//        try//设置客户端发送端口
//        {
//            sendSocket = new DatagramSocket(3001);
//        }
//        catch(IOException e)
//        {
//            showPanel.append(e+ "\n");
//        }

    }


    public void receiveMessage()
    {
        try
        {
            receiveSocket = new DatagramSocket(2222);
//建立端口号为 3001的DatagramSocket
            while(true)
            {
                byte[] buf = new byte[500];
                receivePacket = new DatagramPacket(buf,buf.length);
//建立DatagramPacket对象
                receiveSocket.receive(receivePacket);
//接收数据抱包
                ByteArrayInputStream bin =
                        new ByteArrayInputStream(receivePacket.getData());
                BufferedReader reader =new BufferedReader(
                        new InputStreamReader(bin));
                showPanel.append("服务器：" +reader.readLine()+"\n");
            }
        }
        catch(Exception e)
        {
            showPanel.setText("");
            showPanel.append(e+"\n");
        }
    }
    public void sendMessage()
    {
        try
        {
            ByteArrayOutputStream bout= new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(bout);
            pout.print(editTextArea.getText());
            byte buf[]=bout.toByteArray();
            sendPacket=new DatagramPacket(buf,buf.length,
                    InetAddress.getByName("localhost"),3001);
//            sendSocket.send(sendPacket);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            showPanel.setForeground(Color.BLUE);
            showPanel.append(userName+"  "+date+":\r\n");
            showPanel.append("          "+editTextArea.getText()+"\r\n");
            editTextArea.setText("");
        }
        catch(IOException err)
        {
            showPanel.setText("");
            showPanel.append(err+"\n");
        }
    }
}
