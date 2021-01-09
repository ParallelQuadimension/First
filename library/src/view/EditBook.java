package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import dao.SQLOperation;
import util.BookInfo;
/**
 * 图书修改界面
 */
public class EditBook extends JFrame implements ActionListener {
        SQLOperation op = new SQLOperation();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel bookNumber = new JLabel("图 书 编 号 :");
        JLabel bookName = new JLabel("书 名:");
        JLabel author = new JLabel("作 者:");
        JLabel press = new JLabel("出版社:");
        JLabel pressTime = new JLabel("出版日期:");
        JLabel bookAbstract = new JLabel("摘 要:");
        JLabel storage = new JLabel("总 量:");
        JLabel remain = new JLabel("现 存 量:");
        JLabel remain1 = new JLabel("等待总量更新");
        JLabel numberField = new JLabel();
        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField pressField = new JTextField();
        JTextField pressTimeField = new JTextField();
        JTextField abstractField = new JTextField();
        JTextField storageField = new JTextField();
        JTextField temp1 = new JTextField();
        JTextField temp2 = new JTextField();
        JButton cancel = new JButton("取 消");
        JButton save = new JButton("保 存");
public EditBook(String number) {
        BookInfo book = new BookInfo(number);
        ArrayList<String>strArray = new ArrayList<String>();
        strArray = op.outputBook(book);
        numberField.setText(number);
        nameField.setText(strArray.get(1));
        authorField.setText(strArray.get(2));
        pressField.setText(strArray.get(3));
        pressTimeField.setText(strArray.get(4));
        abstractField.setText(strArray.get(5));
        storageField.setText(strArray.get(6));
        temp1.setText(strArray.get(6));
        temp2.setText(strArray.get(7));
        c.add(p1, BorderLayout.NORTH);
        p1.setLayout(new GridLayout(9, 2, 20, 10));
        p1.add(bookNumber);
        p1.add(numberField);
        p1.add(bookName);
        p1.add(nameField);
        p1.add(author);
        p1.add(authorField);
        p1.add(press);
        p1.add(pressField);
        p1.add(pressTime);
        p1.add(pressTimeField);
        p1.add(bookAbstract);
        p1.add(abstractField);
        p1.add(storage);
        p1.add(storageField);
        p1.add(remain);
        p1.add(remain1);
        p1.add(cancel);
        p1.add(save);
        cancel.addActionListener(this);
        save.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
        this.dispose();
        }
        if (e.getSource() == save) {
        int i = Integer.parseInt(storageField.getText())
        - Integer.parseInt(temp1.getText())
        + Integer.parseInt(temp2.getText());
        BookInfo book = new BookInfo(numberField.getText(),
        nameField.getText(), authorField.getText(),
        pressField.getText(),
        pressTimeField.getText(),
        abstractField.getText(),
        Integer.parseInt(storageField.getText()), i);
        op.inputBook(book);
        JOptionPane.showMessageDialog(null, "修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
        this.dispose(); }
}
}

