package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import dao.SQLOperation;
import util.BookInfo;
import util.Date1;
/**
 ͼ�����ҳ��
 */
public class BorrowBook extends  JFrame implements ActionListener {
    SQLOperation op=new SQLOperation();
    Container c=getContentPane(); // �ײ�����
    JPanel p1=new JPanel();
    JLabel bookNumber=new JLabel("ͼ����");
    JTextField numberField=new JTextField();
    JLabel readerNumber=new JLabel("���߱��");
    JTextField readerNumberField= new JTextField();
    JButton cancel=new JButton("ȡ ��");
    JButton borrow=new JButton("�� ��");
    public BorrowBook(){
        c.add(p1,BorderLayout.NORTH);
        p1.setLayout(new GridLayout(3,2,20,10));
        p1.add(bookNumber);
        p1.add(numberField);
        p1.add(readerNumber);
        p1.add(readerNumberField);
        p1.add(cancel);
        p1.add(borrow);
        cancel.addActionListener(this);
        borrow.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            this.dispose();
        }
        if (e.getSource()==borrow){
            String bookNumber=numberField.getText();
            ArrayList<String > strArray=new ArrayList<String>();
            strArray=op.addBookJudgement(); //���ò�ѯ����������������
            int n=0;
            int replicate=0;
            if (n<Integer.parseInt(strArray.get(0))){
                n++;
                if (bookNumber.equals(strArray.get(n))){
                    replicate++;
                }
            }
            String readerNumber=readerNumberField.getText();
            ArrayList<String > strArray1=new ArrayList<>();
            strArray1=op.addReaderJudgement(); //��ѯ���߱��,��������
            int n1=0;
            int replicate1=0;
            if (n1<Integer.parseInt(strArray1.get(0))){
                n1++;
                if (readerNumber.equals(strArray1.get(n))){
                    replicate1++;
                }
            }
            if (replicate==0||replicate1==0){
                JOptionPane.showConfirmDialog(null,"��������ȷ���鼮��źͶ��߱��","����",JOptionPane.INFORMATION_MESSAGE);
            }else {
                BookInfo book=new BookInfo(bookNumber); //����һ���µı�Ŷ���
                ArrayList<String > s=new ArrayList<>();
                s=op.outputBook(book); //������Ŷ�Ӧ���鼮
                if (Integer.parseInt(s.get(7))>0) // �ж��鼮���ִ�������0
                {
                    int s1=op.reBorrowCheck(numberField.getText(),readerNumberField.getText()); //���÷��������鼮��źͶ��߱�Ų�ѯͼ����Ϣ
                    if (s1 == 1) {
                        JOptionPane.showMessageDialog(null, "���Ѿ������Ȿ��", "ʧ��", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        long currentTime = System.currentTimeMillis();
                        if (op.deadLineCheck(readerNumberField.getText(), currentTime) != 0) {
                            JOptionPane.showMessageDialog(null, "���Ѿ������ˣ��뾡��黹", "ʧ��", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                        	Date1 d=new Date1();
                            BookInfo book1 = new BookInfo(s.get(0), s.get(1), s.get(2), s.get(3), s.get(4), s.get(5), Integer.parseInt(s.get(6)), Integer.parseInt(s.get(7)) - 1);
                            op.inputBook(book1);
                            String borrowTime = d.borrowTimeInterface(System.currentTimeMillis());
                            String deadline = d.borrowTimeInterface(System.currentTimeMillis() + 2592000000l);
                            op.insertBorrow(numberField.getText(), readerNumberField.getText(), borrowTime, deadline); //��������¼
                            JOptionPane.showMessageDialog(null, "����ɹ�������30����Ķ�ʱ��", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "�Ȿ���Ѿ������!", "ʧ��", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            this.dispose();
        }
    }
}