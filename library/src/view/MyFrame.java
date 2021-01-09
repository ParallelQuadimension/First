package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Font;
/**
 * 主界面
 */
public class MyFrame extends JFrame implements ActionListener {
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JLabel welcome = new JLabel("欢迎使用图书管理系统");
        JLabel space = new JLabel("                      ");
        JLabel space1 = new JLabel("                      ");
        JButton book = new JButton("图 书 详 情");
        JButton reader = new JButton("读 者 详 情");
        JButton list = new JButton("借 阅 列 表");
        Font font1 = new Font("00", Font.BOLD, 20);
        Font font2 = new Font("00", Font.ITALIC, 20);
public MyFrame() {
        c.add(p1, BorderLayout.CENTER);
        c.add(p2, BorderLayout.EAST);
        c.add(p3, BorderLayout.WEST);
        p2.add(space);
        p3.add(space1);
        p1.setLayout(new GridLayout(4, 1, 50, 50));
        p1.add(welcome);
        p1.add(book);
        p1.add(reader);
        p1.add(list);
        welcome.setFont(font2);
        book.setFont(font1);
        reader.setFont(font1);
        list.setFont(font1);
        list.addActionListener(this);
        book.addActionListener(this);
        reader.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == book) {
        this.dispose();
        BookRetrieval page1 = new BookRetrieval();
        page1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        page1.setTitle("图 书 详 情");
        page1.setLocation(200, 100);
        page1.setSize(815, 600);
        page1.setVisible(true);
        }
        if (e.getSource() == reader) {
        this.dispose();
        ReaderRetrieval page2 = new ReaderRetrieval();
        page2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        page2.setTitle("读 者 详 情");
        page2.setLocation(200, 100);
        page2.setSize(615, 600);
        page2.setVisible(true);
        }
        if (e.getSource() == list) {
        this.dispose();
        BorrowList page2 = new BorrowList();
        page2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        page2.setTitle("借 阅 列 表");
        page2.setLocation(200, 100);
        page2.setSize(495, 515);
        page2.setVisible(true);
        } }}

