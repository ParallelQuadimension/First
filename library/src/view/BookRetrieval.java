package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.SQLOperation;
import util.BookInfo;
/**
 ͼ���������
 */
public class BookRetrieval extends JFrame implements ActionListener {
    SQLOperation op=new SQLOperation();
    Container c=getContentPane();
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    JLabel bookNumber=new JLabel("�鱾���:");
    JLabel bookName=new JLabel("�� ��:");
    JTextField number=new JTextField();
    JTextField name=new JTextField();
    JButton back=new JButton("�� ��");
    JButton addBook=new JButton("�� ��");
    JButton deleteBook=new JButton("ɾ ��");
    JButton editBook=new JButton("�� ��");
    JButton search=new JButton("�� ��");
    JButton borrowBook=new JButton("�� ��");
    JButton returnBook=new JButton("�� ��");
    Font font1=new Font("00",Font.BOLD,20); //��������
    Object[] s={"���","����","����","������","����ʱ��","ժҪ","����","�ִ���"};
    Object[][] ob=new Object[11000][8];
    JTable table=new JTable(ob,s); //sΪ�������ԣ�obΪ������
    JScrollPane scrollPane=new JScrollPane(table); //��table���ӻ���
    public BookRetrieval(){
        ob=op.allBook(); //ȡ�����е��鼮��Ϣ
        for (int i=0;i<5;i++){
            DefaultTableModel books=new DefaultTableModel(ob,s);   //sΪ�������ԣ�obΪ������
            for (int n=0;n<11000;n++){
                for (int m=0;m<8;m++){
                    ob[n][m]=this.ob[n][m];   //��ȡ�������ݴ���
                }
                table.setModel(books);
                table.invalidate();    //���DefaultTableModelʹ����ʾ���
            }
        }
        c.setBounds(100,100,400,400);
        c.add(p2,BorderLayout.NORTH);
        c.add(p1,BorderLayout.CENTER);
        c.add(p3,BorderLayout.SOUTH);
        search.setFont(font1);
        p2.setLayout(new GridLayout(2,4,20,10));
        p2.add(back);
        p2.add(addBook);
        p2.add(editBook);
        p2.add(deleteBook);
        p2.add(borrowBook);
        p2.add(returnBook);
        p1.setLayout(null);
        scrollPane.setBounds(0,0,800,300);
        p1.add(scrollPane);
        p3.setLayout(new GridLayout(5,1,0,0));
        p3.add(bookNumber);
        p3.add(number);
        p3.add(bookName);
        p3.add(name);
        p3.add(search);
        table.setPreferredScrollableViewportSize(new Dimension(400,300));
        addBook.addActionListener(this);
        search.addActionListener(this);
        back.addActionListener(this);
        deleteBook.addActionListener(this);
        editBook.addActionListener(this);
        borrowBook.addActionListener(this);
        returnBook.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search){
            ArrayList<String> strArray=new ArrayList<>();
            strArray=op.addBookJudgement();//���ò�ѯ����,��ȡ��ͼ������б��
            String s1=number.getText(); //��ȡ��д�ı��
            String s2=name.getText(); //��ȡ��д������
            int n=0;
            int replicate=0;
            while (n<Integer.parseInt(strArray.get(0))){
                n++;
                if (s1.equals(strArray.get(n))){
                    System.out.println("");
                    replicate++;
                }
            } //��ѯ����Ƿ����
            ArrayList<String> strArray1 = new ArrayList<>();
            strArray1=op.addBookJudgement1();//���ò�ѯ��������ȡͼ�����������
            int n1=0;
            int replicate1=0;
            while (n1<Integer.parseInt(strArray1.get(0))){
                n1++;
                if (s1.equals(strArray1.get(n1))){
                    replicate1++;
                }
            }
            if (replicate==0&&replicate1==0){
                JOptionPane.showConfirmDialog(null,"��������ȷ���������߱��","����",JOptionPane.INFORMATION_MESSAGE);
            }else if (replicate!=0||replicate1!=0){
                if (replicate==0&&replicate1!=0){ //��Ų���ȷ����������
                    BookInfo book=new BookInfo(s1,s2);
                    String s=op.searchBookByName(book); //ͨ�������ҳ��鼮���
                    BookDetails f=new BookDetails(s);  //�����鼮����ҳ��
                    f.setTitle("�鼮����");
                    f.setLocation(300,200);
                    f.setSize(480,470);
                    f.setVisible(true);
                }
                else if (replicate!=0) //�����ȷ
                     {
                         BookDetails f=new BookDetails(s1); //���ݱ�Ŵ��鼮����ҳ��
                         f.setTitle("�鼮����");
                         f.setLocation(300,200);
                         f.setSize(480,470);
                         f.setVisible(true);
                     }
            }
        }
        if (e.getSource()==back){
            this.dispose();
            MyFrame f=new MyFrame();
            f.setTitle("ͼ �� �� �� ϵ ͳ");
            f.setLocation(300,200);
            f.setSize(580,300);
            f.setVisible(true);
        }
        if (e.getSource()==addBook){
            AddBook f=new AddBook();
            f.setTitle("��  ��");
            f.setLocation(300,200);
            f.setSize(480,470);
            f.setVisible(true);
        }
        if (e.getSource()==deleteBook){
            DeleteBook f=new DeleteBook();
            f.setTitle("ɾ   ��");
            f.setLocation(300,200);
            f.setSize(300,200);
            f.setVisible(true);
        }
        if (e.getSource()==borrowBook){
            BorrowBook f=new BorrowBook();
            f.setTitle("��   ��");
            f.setLocation(300,200);
            f.setSize(300,200);
            f.setVisible(true);
        }
        if (e.getSource()==returnBook){
            ReturnBook f=new ReturnBook();
            f.setTitle("��   ��");
            f.setLocation(300,200);
            f.setSize(480,470);
            f.setVisible(true);
        }
        if (e.getSource()==editBook){
            SelectEdit f=new SelectEdit();
            f.setTitle("��   ��");
            f.setLocation(300,200);
            f.setSize(480,470);
            f.setVisible(true);
        }
    }
}
