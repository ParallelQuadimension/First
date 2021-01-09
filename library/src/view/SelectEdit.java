package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import dao.SQLOperation;
import util.BookInfo;
/**
 * 图书修改界面
 */
public class SelectEdit extends JFrame implements ActionListener
        {
        SQLOperation op = new SQLOperation();
        BookInfo book = new BookInfo();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel bookNumber = new JLabel("图书编号:");
        JTextField numberField = new JTextField();
        JButton cancel = new JButton("取 消");
        JButton select = new JButton("选 择");
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
                f.setTitle("修 改");
                f.setLocation(300, 200);
                f.setSize(300, 400);
                f.setVisible(true);
        } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "请输入正确的图书编号", "警告", JOptionPane.INFORMATION_MESSAGE);
        }
        }
}
        }




