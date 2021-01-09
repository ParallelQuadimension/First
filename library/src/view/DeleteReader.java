package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import dao.SQLOperation;
import util.ReaderInfo;
/**
 *����ɾ��ҳ��
 */
public class DeleteReader extends JFrame implements ActionListener {
        SQLOperation op = new SQLOperation();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel readerNumber = new JLabel("���߱��:");
        JTextField numberField = new JTextField();
        JButton cancel = new JButton("ȡ ��");
        JButton delete = new JButton("ɾ ��");
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
        JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ����ȹ黹ͼ��", "����", JOptionPane.INFORMATION_MESSAGE);
        } else {
        if (op.deleteReader(reader) == 1) {
        JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
        } else {
        JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ��ö��߲�����", "����", JOptionPane.INFORMATION_MESSAGE); } } }
}}


