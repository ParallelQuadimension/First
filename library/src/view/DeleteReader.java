package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import dao.SQLOperation;
import util.ReaderInfo;
/**
 *读者删除页面
 */
public class DeleteReader extends JFrame implements ActionListener {
        SQLOperation op = new SQLOperation();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel readerNumber = new JLabel("读者编号:");
        JTextField numberField = new JTextField();
        JButton cancel = new JButton("取 消");
        JButton delete = new JButton("删 除");
public DeleteReader() {
        c.add(p1, BorderLayout.NORTH);
        p1.setLayout(new GridLayout(2, 2, 20, 10));
        p1.add(readerNumber);
        p1.add(numberField);
        p1.add(cancel);
        p1.add(delete);
        cancel.addActionListener(this);
        delete.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
        this.dispose();
        }
        if (e.getSource() == delete) {
        ReaderInfo reader = new ReaderInfo(numberField.getText());
        this.dispose();
        if (op.deleteReaderCheck(numberField.getText()) != 0) {
        JOptionPane.showMessageDialog(null, "删除失败，请先归还图书", "警告", JOptionPane.INFORMATION_MESSAGE);
        } else {
        if (op.deleteReader(reader) == 1) {
        JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
        JOptionPane.showMessageDialog(null, "删除失败，该读者不存在", "警告", JOptionPane.INFORMATION_MESSAGE); } } }
}}


