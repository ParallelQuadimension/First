package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.SQLOperation;
import util.ReaderInfo;
/**
 * 读者借阅详情界面
 */
public class ReaderDetails extends JFrame implements ActionListener {
    SQLOperation op = new SQLOperation();
    Container c = getContentPane();
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JLabel readerNumber = new JLabel("读者编号:");
    JLabel readerName = new JLabel("读者姓名:");
    JLabel sex = new JLabel("性 别:");
    JLabel dpt = new JLabel("系 别:");
    JLabel grade = new JLabel("年 级:");
    JLabel numberField = new JLabel();
    JLabel nameField = new JLabel();
    JLabel sexField = new JLabel();
    JLabel dptField = new JLabel();
    JLabel gradeField = new JLabel();
    JButton cancel = new JButton("取 消");
    JLabel details = new JLabel("借阅和归还详情"); 
    Object[] s = { "图书编号", "借阅日期", "归还日期","逾 期"};
    Object[][] ob1 = new Object[20][4];
    JTable table = new JTable(ob1, s);
    JScrollPane scrollPane = new JScrollPane(table);
public ReaderDetails(String number) {
        ReaderInfo reader = new ReaderInfo(number); 
        ob1 = op.borrowListForReader(number); //查询读者借阅信息
        for (int i = 0; i< 5; i++) {
        DefaultTableModel books = new DefaultTableModel(ob1, s);
        for (int n = 0; n< 20; n++) { 
            for (int m = 0; m< 4; m++) { 
                ob1[n][m] = this.ob1[n][m]; }
            table.setModel(books);
            table.invalidate();
        }}
        ArrayList<String> strArray =new ArrayList <String>();
        strArray = op.outputReader(reader);  //查询读者信息
        numberField.setText(number);
        nameField.setText(strArray.get(1));
        sexField.setText(strArray.get(2));
        dptField.setText(strArray.get(3));
        gradeField.setText(strArray.get(4));
        c.add(p1, BorderLayout.NORTH);
        c.add(p2, BorderLayout.CENTER);
        c.add(p3, BorderLayout.SOUTH);
        p1.setLayout(new GridLayout(6, 2, 20, 10));
        p1.add(readerNumber);
        p1.add(numberField);
        p1.add(readerName);
        p1.add(nameField);
        p1.add(sex);
        p1.add(sexField);
        p1.add(dpt);
        p1.add(dptField);
        p1.add(grade);
        p1.add(gradeField);
        p1.add(details);
        scrollPane.setBounds(0, 0, 800, 300);
        p2.add(scrollPane);
        p3.add(cancel);
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        cancel.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            this.dispose();
        }
}
}
