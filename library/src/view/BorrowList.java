package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.SQLOperation;
/**
  �����б�
 */
public class BorrowList extends JFrame implements ActionListener
        {
        SQLOperation op = new SQLOperation();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JButton back=new JButton("�� ��");
        Object[] s = { "ͼ����", "���߱��", "����ʱ��", "�黹ʱ��","�� ��"};
        Object[][] ob = new Object[100][5];
        JTable table = new JTable(ob, s);
        JScrollPane scrollPane = new JScrollPane(table);
public BorrowList() {
        Object[][] ob1 = op.borrowList(); //�������еĽ����¼
        for (int i = 0; i< 5; i++) {
            DefaultTableModel list = new DefaultTableModel(ob, s);
            for (int n = 0; n< 100; n++) {
                for (int m = 0; m< 5; m++) {
                    ob[n][m] = ob1[n][m]; }
                table.setModel(list);
                table.invalidate();
            } }
        c.add(p1, BorderLayout.CENTER);
        c.add(p2,BorderLayout.SOUTH);
        scrollPane.setBounds(0, 0, 800, 300);
        p1.add(scrollPane);
        p2.add(back);
        table.setPreferredScrollableViewportSize(new
        Dimension(400, 400));
        back.addActionListener(this);
}
public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
        this.dispose();
        MyFrame f = new MyFrame(); //�ص�������
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("ͼ �� �� �� ϵ ͳ");
        f.setLocation(300, 200);
        f.setSize(580, 300);
        f.setVisible(true);
        }
}
        }




