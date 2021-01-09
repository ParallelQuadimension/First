package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.management.openmbean.OpenDataException;
import javax.swing.*;

import dao.SQLOperation;
import util.BookInfo;
/**
 * �黹ͼ�����
 */
public class ReturnBook extends JFrame implements ActionListener
        {
        SQLOperation op = new SQLOperation();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel bookNumber = new JLabel("ͼ����:");
        JTextField numberField = new JTextField();
        JLabel readerNumber = new JLabel("���߱�ţ�");
        JTextField readerNumberField = new JTextField();
        JButton cancel = new JButton("ȡ ��");
        JButton returnBook = new JButton("�� ��");
public ReturnBook() {
        c.add(p1, BorderLayout.NORTH);
        p1.setLayout(new GridLayout(3, 2, 20, 10));
        p1.add(bookNumber);
        p1.add(numberField);
        p1.add(readerNumber);
        p1.add(readerNumberField);
        p1.add(cancel);
        p1.add(returnBook);
        cancel.addActionListener(this);
        returnBook.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
        this.dispose();
        }
        if (e.getSource() == returnBook) {
            String bookNumber = numberField.getText();
            ArrayList<String>strArray = new ArrayList<String>();
            strArray=op.addBookJudgement(); //����ͼ����
            int n = 0;
            int replicate = 0;
            while (n< Integer.parseInt(strArray.get(0))) {
                n++;
                if (bookNumber.equals(strArray.get(n))) {
                    replicate++;
                }
            }//�ж�ͼ���Ƿ���ͬ
            String readerNumber = readerNumberField.getText();
            ArrayList<String>strArray1 = new ArrayList<String>();
            strArray1 = op.addReaderJudgement(); //�������߱��
            int n1 = 0;
            int replicate1 = 0;
            while (n1< Integer.parseInt(strArray1.get(0))) {
                n1++;
                if (readerNumber.equals(strArray1.get(n1))) {
                    replicate1++;
                }
            }//�ж϶��߱���Ƿ�����ͬ
            if (replicate == 0 || replicate1 == 0) {
                JOptionPane.showMessageDialog(null, "��������ȷ��ͼ���Ż���߱��!", "����", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (op.returnCheck(numberField.getText(), readerNumberField.getText()) == 1) // //�ж��Ƿ��н��ļ�¼
                {
                    JOptionPane.showMessageDialog(null, "û�н��ļ�¼", "Warning", JOptionPane.INFORMATION_MESSAGE); }
                else{
                    BookInfo book = new BookInfo(bookNumber);
                    ArrayList<String>s = new ArrayList<String>();
                    s = op.outputBook(book); //���ݱ�ŵ���ͼ����Ϣ
                    BookInfo book1 = new BookInfo(s.get(0), s.get(1), s.get(2), s.get(3), s.get(4), s.get(5), Integer.parseInt(s.get(6)), Integer.parseInt(s.get(7)) + 1);
                    op.inputBook(book1);//���ݱ�Ÿ���ͼ����Ϣ���ִ�����1��
                    if(op.deleteResult(numberField.getText(), readerNumberField.getText())==1) //�жϽ��ļ�¼�Ƿ�ɾ���ɹ�
                    {
                        JOptionPane.showMessageDialog(null, "�黹�ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();}
                    else{
                        JOptionPane.showMessageDialog(null, "�黹ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
}
        }
