package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import dao.SQLOperation;
import util.BookInfo;
/**
 * ͼ���޸Ľ���
 */
public class SelectEdit extends JFrame implements ActionListener
        {
        SQLOperation op = new SQLOperation();
        BookInfo book = new BookInfo();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel bookNumber = new JLabel("ͼ����:");
        JTextField numberField = new JTextField();
        JButton cancel = new JButton("ȡ ��");
        JButton select = new JButton("ѡ ��");
public SelectEdit() {
        c.add(p1, BorderLayout.NORTH);
        p1.setLayout(new GridLayout(2, 2, 20, 10));
        p1.add(bookNumber);
        p1.add(numberField);
        p1.add(cancel);
        p1.add(select);
        cancel.addActionListener(this);
        select.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
        this.dispose();
        }
        if (e.getSource() == select) {
        this.dispose();
        try {
                EditBook f = new EditBook(numberField.getText());
                f.setTitle("�� ��");
                f.setLocation(300, 200);
                f.setSize(300, 400);
                f.setVisible(true);
        } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "��������ȷ��ͼ����", "����", JOptionPane.INFORMATION_MESSAGE);
        }
        }
}
        }




