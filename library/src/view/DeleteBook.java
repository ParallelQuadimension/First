package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import dao.SQLOperation;
import util.BookInfo;
/**
 * 图书删除页面
 */
public class DeleteBook extends JFrame implements ActionListener {
    SQLOperation op = new SQLOperation();
    Container c = getContentPane();
    JPanel p1 = new JPanel();
    JLabel bookNumber = new JLabel("图书编号:");
    JTextField numberField = new JTextField();
    JButton cancel = new JButton("取 消");
    JButton delete = new JButton("删 除");
    /**
     删除方法，根据书籍编号删除图书
     */
    public DeleteBook() {
        c.add(p1, BorderLayout.NORTH);
        p1.setLayout(new GridLayout(2, 2, 20, 10));
        p1.add(bookNumber);
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
        BookInfo book = new BookInfo(numberField.getText());
        this.dispose();
        if (op.deleteBookCheck(numberField.getText()) != 0) {
            JOptionPane.showMessageDialog(null, "删除失败，这本书已经借出", "警告", JOptionPane.INFORMATION_MESSAGE);
        } else {
        if (op.deleteBook(book) == 1) {
        JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "删除失败，本书不存在", "警告", JOptionPane.INFORMATION_MESSAGE);
        } } } }
}

