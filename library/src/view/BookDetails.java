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
  *图书详细信息页面
 */
public class BookDetails extends JFrame implements ActionListener {
    SQLOperation op=new SQLOperation(); //创建数据库链接对象
    Container c=getContentPane(); //获得底层容器
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    JLabel bookNumber=new JLabel("书本 编号:");
    JLabel bookName=new JLabel("书  名:");
    JLabel author=new JLabel("作  者:");
    JLabel press=new JLabel("出版社:");
    JLabel pressTime=new JLabel("出版时间:");
    JLabel bookAbstract=new JLabel("摘要:");
    JLabel storage=new JLabel("总 量:");
    JLabel remain=new JLabel("现存量:");
    JLabel numberField=new JLabel();
    JLabel nameField=new JLabel();
    JLabel authorField=new JLabel();
    JLabel pressField=new JLabel();
    JLabel pressTimeField=new JLabel();
    JLabel abstractField=new JLabel();
    JLabel storageField=new JLabel();
    JLabel remainField=new JLabel();
    JButton cancel=new JButton("取  消");
    JLabel details =new JLabel("借 还 明 细"); //设置页面相关部件
    Object[] s={"读者 编号","借书日期","归还日期","逾期"};
    Object[][] ob=new Object[11000][4];
    JTable table=new JTable(ob,s); //建立一个显示s和ob数据的表格
    JScrollPane scrollPane=new JScrollPane(table); //设置滚动界面
    /**
     书籍借还详情构造方法，根据书籍编号查找
     */
    public BookDetails (String number){
        BookInfo book=new BookInfo(number);
        ob=op.borrowListForBook(number); //查找到编号对应的图书信息，并存入数组中
        for(int i=0;i<5;i++){
            DefaultTableModel books=new DefaultTableModel(ob,s);//构造一个DefaultTableModel，并通过将ob和s传递到setDataVector方法来初始化该表。
            for (int n=0;n<7;n++){
                for (int m=0;m<4;m++){
                    ob[n][m]=this.ob[n][m];
                }
                table.setModel(books);
                table.invalidate();
            }
            ArrayList<String> strArray=new ArrayList<>();
            strArray=op.outputBook(book);  //根据编号导出图书借阅信息
            numberField.setText(number);
            nameField.setText(strArray.get(1));
            authorField.setText(strArray.get(2));
            pressField.setText(strArray.get(3));
            pressTimeField.setText(strArray.get(4));
            abstractField.setText(strArray.get(5));
            storageField.setText(strArray.get(6));
            remainField.setText(strArray.get(7)); //将导出图书信息填入文本
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
