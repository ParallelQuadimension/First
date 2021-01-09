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
 图书检索界面
 */
public class BookRetrieval extends JFrame implements ActionListener {
    SQLOperation op=new SQLOperation();
    Container c=getContentPane();
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    JLabel bookNumber=new JLabel("书本编号:");
    JLabel bookName=new JLabel("书 名:");
    JTextField number=new JTextField();
    JTextField name=new JTextField();
    JButton back=new JButton("回 退");
    JButton addBook=new JButton("增 加");
    JButton deleteBook=new JButton("删 除");
    JButton editBook=new JButton("修 改");
    JButton search=new JButton("查 找");
    JButton borrowBook=new JButton("借 书");
    JButton returnBook=new JButton("还 书");
    Font font1=new Font("00",Font.BOLD,20); //设置字体
    Object[] s={"编号","书名","作者","出版社","出版时间","摘要","总量","现存量"};
    Object[][] ob=new Object[11000][8];
    JTable table=new JTable(ob,s); //s为基本属性，ob为行数据
    JScrollPane scrollPane=new JScrollPane(table); //给table增加滑轮
    public BookRetrieval(){
        ob=op.allBook(); //取出所有的书籍信息
        for (int i=0;i<5;i++){
            DefaultTableModel books=new DefaultTableModel(ob,s);   //s为基本属性，ob为行数据
            for (int n=0;n<11000;n++){
                for (int m=0;m<8;m++){
                    ob[n][m]=this.ob[n][m];   //将取出的数据存入
                }
                table.setModel(books);
                table.invalidate();    //配合DefaultTableModel使用显示表格
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
            strArray=op.addBookJudgement();//调用查询方法,获取了图书的所有编号
            String s1=number.getText(); //读取填写的编号
            String s2=name.getText(); //读取填写的书名
            int n=0;
            int replicate=0;
            while (n<Integer.parseInt(strArray.get(0))){
                n++;
                if (s1.equals(strArray.get(n))){
                    System.out.println("");
                    replicate++;
                }
            } //查询编号是否存在
            ArrayList<String> strArray1 = new ArrayList<>();
            strArray1=op.addBookJudgement1();//调用查询方法，获取图书的所有书名
            int n1=0;
            int replicate1=0;
            while (n1<Integer.parseInt(strArray1.get(0))){
                n1++;
                if (s1.equals(strArray1.get(n1))){
                    replicate1++;
                }
            }
            if (replicate==0&&replicate1==0){
                JOptionPane.showConfirmDialog(null,"请输入正确的书名或者编号","警告",JOptionPane.INFORMATION_MESSAGE);
            }else if (replicate!=0||replicate1!=0){
                if (replicate==0&&replicate1!=0){ //编号不正确，书名存在
                    BookInfo book=new BookInfo(s1,s2);
                    String s=op.searchBookByName(book); //通过书名找出书籍编号
                    BookDetails f=new BookDetails(s);  //调用书籍详情页面
                    f.setTitle("书籍详情");
                    f.setLocation(300,200);
                    f.setSize(480,470);
                    f.setVisible(true);
                }
                else if (replicate!=0) //编号正确
                     {
                         BookDetails f=new BookDetails(s1); //根据编号打开书籍详情页面
                         f.setTitle("书籍详情");
                         f.setLocation(300,200);
                         f.setSize(480,470);
                         f.setVisible(true);
                     }
            }
        }
        if (e.getSource()==back){
            this.dispose();
            MyFrame f=new MyFrame();
            f.setTitle("图 书 管 理 系 统");
            f.setLocation(300,200);
            f.setSize(580,300);
            f.setVisible(true);
        }
        if (e.getSource()==addBook){
            AddBook f=new AddBook();
            f.setTitle("增  加");
            f.setLocation(300,200);
            f.setSize(480,470);
            f.setVisible(true);
        }
        if (e.getSource()==deleteBook){
            DeleteBook f=new DeleteBook();
            f.setTitle("删   除");
            f.setLocation(300,200);
            f.setSize(300,200);
            f.setVisible(true);
        }
        if (e.getSource()==borrowBook){
            BorrowBook f=new BorrowBook();
            f.setTitle("借   阅");
            f.setLocation(300,200);
            f.setSize(300,200);
            f.setVisible(true);
        }
        if (e.getSource()==returnBook){
            ReturnBook f=new ReturnBook();
            f.setTitle("归   还");
            f.setLocation(300,200);
            f.setSize(480,470);
            f.setVisible(true);
        }
        if (e.getSource()==editBook){
            SelectEdit f=new SelectEdit();
            f.setTitle("修   改");
            f.setLocation(300,200);
            f.setSize(480,470);
            f.setVisible(true);
        }
    }
}
