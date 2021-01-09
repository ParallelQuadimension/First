package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import dao.SQLOperation;
import util.BookInfo;
/**
  �����鼮ҳ��
 */
public class AddBook extends JFrame implements ActionListener {
    SQLOperation op=new SQLOperation(); //�������ݿ����Ӷ���
    Container c=getContentPane(); //��õײ�����
    JPanel p1=new JPanel();
    JLabel bookNumber=new JLabel("ͼ �� �� ��:");
    JLabel bookName= new JLabel("��   ��:");
    JLabel bookAuthor=new JLabel("��   ��:");
    JLabel press= new JLabel("�� �� ��:");
    JLabel pressTime=new JLabel("�� �� �� ��:");
    JLabel bookAbstract=new JLabel("ժ   Ҫ:");
    JLabel storage =new JLabel("��   ��:");
    JLabel remain=new JLabel("�� �� ��:");
    JLabel remain1=new JLabel("�ȴ���������:"); // ���ø��ֱ�ǩ
    JTextField numberField=new JTextField();
    JTextField nameField=new JTextField();
    JTextField authorField=new JTextField();
    JTextField pressField=new JTextField();
    JTextField pressTimeField=new JTextField();
    JTextField abstractField=new JTextField();
    JTextField storageField=new JTextField(); //���ñ�ǩ��Ӧ�ĵ��ı���
    JButton cancel=new JButton("ȡ  ��");
    JButton confirm=new JButton("ȷ  ��");
    /**
     �����鼮ҳ��Ĺ��췽��
     */
    public AddBook(){
        c.add(p1,BorderLayout.NORTH);//��p1������ײ�����
        setBounds(100,100,400,400);
        p1.setLayout(new GridLayout(9,2,20,10));//����P1Ϊ���񲼾�
        p1.add(bookNumber);
        p1.add(numberField);
        p1.add(bookName);
        p1.add(nameField);
        p1.add(bookAuthor);
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
        p1.add(confirm);
        cancel.addActionListener(this); //��ӵ���¼�
        confirm.addActionListener(this);//��ӵ���¼�
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            this.dispose();  // �رյ�ǰ����
        }
        if (e.getSource()==confirm){
            this.dispose();  //�رյ�ǰ����
            BookInfo book=new BookInfo(numberField.getText(),nameField.getText(), authorField.getText(),pressField.getText(),pressTimeField.getText(),abstractField.getText(),Integer.parseInt(storageField.getText()),Integer.parseInt(storageField.getText()));
            // ͨ����д���ı����ݵõ��µ��鱾����
            ArrayList<String> strArray =new ArrayList<>();
            strArray=op.addBookJudgement(); //��String ���� ��ȡ���ڵ��鼮���б��
            int n=0;
            int replicate=0;
            while(n<Integer.parseInt(strArray.get(0))){
                n++;
                if(numberField.getText().equals(strArray.get(n))){
                    replicate++;
                }
            } // �ж��鼮�Ƿ��Ѿ�����
            if(replicate==0) {
                op.saveBook(book); //���÷��������鼮���鱾����;
                JOptionPane.showMessageDialog(null, "�ɹ�����ñ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "������(���)�Ѿ�������", "����", JOptionPane.INFORMATION_MESSAGE);
            }
            }
        }
    }
