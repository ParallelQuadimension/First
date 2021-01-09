package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.SQLOperation;
import util.ReaderInfo;
/**
 * 读者信息详情界面
 */
class ReaderRetrieval extends JFrame implements ActionListener {
        SQLOperation op = new SQLOperation();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JLabel readerNumber = new JLabel("读者编号:");
        JLabel readerName = new JLabel("读者姓名:");
        JTextField number = new JTextField();
        JTextField name = new JTextField();
        JButton back = new JButton("回 退");
        JButton addReader = new JButton("增 加");
        JButton deleteReader = new JButton("删 除");
        JButton editReader = new JButton("修 改");
        JButton search = new JButton("查 找");
        Object[] s = { "编 号", "姓 名", "性 别", "系 别", "年 级" };
        Object[][] ob = new Object[20][5];
        JTable table = new JTable(ob, s);
        JScrollPane scrollPane = new JScrollPane(table);
        Font font1 = new Font("00", Font.BOLD, 20);
public ReaderRetrieval() {
        ob=op.allReader(); //导出所有的读者信息
        for (int i = 0; i< 5; i++) {
        DefaultTableModel readers = new DefaultTableModel(ob, s);
        for (int n = 0; n< 20; n++) {
            for (int m = 0; m< 5; m++) {
                ob[n][m] = this.ob[n][m]; }
            table.setModel(readers);
            table.invalidate();
        } }
        search.setFont(font1);
        c.add(p4, BorderLayout.NORTH);
        c.add(p1, BorderLayout.CENTER);
        c.add(p5, BorderLayout.SOUTH);
        p4.setLayout(new GridLayout(2, 4, 20, 10));
        p4.add(back);
        p4.add(addReader);
        p4.add(editReader);
        p4.add(deleteReader);
        p1.setLayout(null);
        scrollPane.setBounds(0, 0, 600, 300);
        p1.add(scrollPane);
        p5.setLayout(new GridLayout(5, 1, 0, 0));
        p5.add(readerNumber);
        p5.add(number);
        p5.add(readerName);
        p5.add(name);
        p5.add(search);
        addReader.addActionListener(this);
        search.addActionListener(this);
        back.addActionListener(this);
        deleteReader.addActionListener(this);
        editReader.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
        ArrayList<String>strArray = new ArrayList<String>();
        strArray = op.addReaderJudgement(); //导出读者编号
        String s1 = number.getText();
        String s2 = name.getText();
        int n = 0;
        int replicate = 0;
        while (n< Integer.parseInt(strArray.get(0))) {
            n++;
            if (s1.equals(strArray.get(n))) {
                replicate++;
            }
        } //查询编号是否存在
        ArrayList<String>strArray1 = new ArrayList<String>();
        strArray1 = op.addReaderJudgement1(); //导出读者姓名
        int n1 = 0;
        int replicate1 = 0;
        while (n1< Integer.parseInt(strArray1.get(0))) {
            n1++;
            if (s2.equals(strArray1.get(n1))) {
                replicate1++;
        } } //查询姓名是否存在
        if (replicate == 0 &&replicate1 == 0) {
        JOptionPane.showMessageDialog(null, "请输入正确的姓名或者编号", "警告", JOptionPane.INFORMATION_MESSAGE);
        } else if (replicate != 0 || replicate1 != 0) {
            if (replicate == 0 &&replicate1 != 0) {
                ReaderInfo reader = new ReaderInfo(s1, s2);
                String s = op.searchReaderByName(reader);
                ReaderDetails f = new ReaderDetails(s);
                f.setTitle("读 者 详 情");
                f.setLocation(300, 200);
                f.setSize(480, 470);
                f.setVisible(true);
        } else if (replicate != 0) {
                ReaderDetails f = new ReaderDetails(s1);
                 f.setTitle("读 者 详 情");
                 f.setLocation(300, 200);
                 f.setSize(480, 470);
                 f.setVisible(true);
            }
        } }
        if (e.getSource() == back) {
        this.dispose();
        MyFrame f = new MyFrame();
        f.setTitle("图 书 管 理 系 统");
        f.setLocation(300, 200);
        f.setSize(580, 300);
        f.setVisible(true);
        }
        if (e.getSource() == addReader) {
        AddReader f = new AddReader();
        f.setTitle("增 加");
        f.setLocation(300, 200);
        f.setSize(300, 300);
        f.setVisible(true);
        }
        if (e.getSource() == deleteReader) {
        DeleteReader f = new DeleteReader();
        f.setTitle("删 除");
        f.setLocation(300, 200);
        f.setSize(300, 200);
        f.setVisible(true);
        }
        if (e.getSource() == editReader) {
        SelectEditReader f = new SelectEditReader();
        f.setTitle("修 改");
        f.setLocation(300, 200);
        f.setSize(300, 200);
        f.setVisible(true); }
}}
