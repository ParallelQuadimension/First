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
 图书借阅页面
 */
public class BorrowBook extends  JFrame implements ActionListener {
    SQLOperation op=new SQLOperation();
    Container c=getContentPane(); // 底层容器
    JPanel p1=new JPanel();
    JLabel bookNumber=new JLabel("图书编号");
    JTextField numberField=new JTextField();
    JLabel readerNumber=new JLabel("读者编号");
    JTextField readerNumberField= new JTextField();
    JButton cancel=new JButton("取 消");
    JButton borrow=new JButton("借 阅");
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
            strArray=op.addBookJudgement(); //调用查询书名法，存入数组
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
            strArray1=op.addReaderJudgement(); //查询读者编号,存入数组
            int n1=0;
            int replicate1=0;
            if (n1<Integer.parseInt(strArray1.get(0))){
                n1++;
                if (readerNumber.equals(strArray1.get(n))){
                    replicate1++;
                }
            }
            if (replicate==0||replicate1==0){
                JOptionPane.showConfirmDialog(null,"请填入正确的书籍编号和读者编号","警告",JOptionPane.INFORMATION_MESSAGE);
            }else {
                BookInfo book=new BookInfo(bookNumber); //创造一个新的编号对象
                ArrayList<String > s=new ArrayList<>();
                s=op.outputBook(book); //导出编号对应的书籍
                if (Integer.parseInt(s.get(7))>0) // 判断书籍的现存量大于0
                {
                    int s1=op.reBorrowCheck(numberField.getText(),readerNumberField.getText()); //调用方法根据书籍编号和读者编号查询图书信息
                    if (s1 == 1) {
                        JOptionPane.showMessageDialog(null, "你已经借了这本书", "失败", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        long currentTime = System.currentTimeMillis();
                        if (op.deadLineCheck(readerNumberField.getText(), currentTime) != 0) {
                            JOptionPane.showMessageDialog(null, "你已经逾期了，请尽快归还", "失败", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                        	Date1 d=new Date1();
                            BookInfo book1 = new BookInfo(s.get(0), s.get(1), s.get(2), s.get(3), s.get(4), s.get(5), Integer.parseInt(s.get(6)), Integer.parseInt(s.get(7)) - 1);
                            op.inputBook(book1);
                            String borrowTime = d.borrowTimeInterface(System.currentTimeMillis());
                            String deadline = d.borrowTimeInterface(System.currentTimeMillis() + 2592000000l);
                            op.insertBorrow(numberField.getText(), readerNumberField.getText(), borrowTime, deadline); //插入借书记录
                            JOptionPane.showMessageDialog(null, "借书成功，你有30天的阅读时间", "成功", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "这本书已经借出了!", "失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            this.dispose();
        }
    }
}