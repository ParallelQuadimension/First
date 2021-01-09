package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.SQLOperation;
import util.BookInfo;
/**
  *ͼ����ϸ��Ϣҳ��
 */
public class BookDetails extends JFrame implements ActionListener {
    SQLOperation op=new SQLOperation(); //�������ݿ����Ӷ���
    Container c=getContentPane(); //��õײ�����
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    JLabel bookNumber=new JLabel("�鱾 ���:");
    JLabel bookName=new JLabel("��  ��:");
    JLabel author=new JLabel("��  ��:");
    JLabel press=new JLabel("������:");
    JLabel pressTime=new JLabel("����ʱ��:");
    JLabel bookAbstract=new JLabel("ժҪ:");
    JLabel storage=new JLabel("�� ��:");
    JLabel remain=new JLabel("�ִ���:");
    JLabel numberField=new JLabel();
    JLabel nameField=new JLabel();
    JLabel authorField=new JLabel();
    JLabel pressField=new JLabel();
    JLabel pressTimeField=new JLabel();
    JLabel abstractField=new JLabel();
    JLabel storageField=new JLabel();
    JLabel remainField=new JLabel();
    JButton cancel=new JButton("ȡ  ��");
    JLabel details =new JLabel("�� �� �� ϸ"); //����ҳ����ز���
    Object[] s={"���� ���","��������","�黹����","����"};
    Object[][] ob=new Object[11000][4];
    JTable table=new JTable(ob,s); //����һ����ʾs��ob���ݵı��
    JScrollPane scrollPane=new JScrollPane(table); //���ù�������
    /**
     �鼮�軹���鹹�췽���������鼮��Ų���
     */
    public BookDetails (String number){
        BookInfo book=new BookInfo(number);
        ob=op.borrowListForBook(number); //���ҵ���Ŷ�Ӧ��ͼ����Ϣ��������������
        for(int i=0;i<5;i++){
            DefaultTableModel books=new DefaultTableModel(ob,s);//����һ��DefaultTableModel����ͨ����ob��s���ݵ�setDataVector��������ʼ���ñ�
            for (int n=0;n<7;n++){
                for (int m=0;m<4;m++){
                    ob[n][m]=this.ob[n][m];
                }
                table.setModel(books);
                table.invalidate();
            }
            ArrayList<String> strArray=new ArrayList<>();
            strArray=op.outputBook(book);  //���ݱ�ŵ���ͼ�������Ϣ
            numberField.setText(number);
            nameField.setText(strArray.get(1));
            authorField.setText(strArray.get(2));
            pressField.setText(strArray.get(3));
            pressTimeField.setText(strArray.get(4));
            abstractField.setText(strArray.get(5));
            storageField.setText(strArray.get(6));
            remainField.setText(strArray.get(7)); //������ͼ����Ϣ�����ı�
            c.add(p1,BorderLayout.NORTH);
            c.add(p2,BorderLayout.CENTER);
            c.add(p3,BorderLayout.SOUTH);
            p1.setLayout(new GridLayout(9,2,20,10));
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
            p1.add(remainField);
            p1.add(details);
            scrollPane.setBounds(0,0,800,300);
            p2.add(scrollPane);
            p3.add(cancel);
            table.setPreferredScrollableViewportSize(new Dimension(400,100));
            cancel.addActionListener(this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            this.dispose();
        }
    }
}
