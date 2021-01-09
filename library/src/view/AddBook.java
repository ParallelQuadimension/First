package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import dao.SQLOperation;
import util.BookInfo;
/**
  增加书籍页面
 */
public class AddBook extends JFrame implements ActionListener {
    SQLOperation op=new SQLOperation(); //创建数据库链接对象
    Container c=getContentPane(); //获得底层容器
    JPanel p1=new JPanel();
    JLabel bookNumber=new JLabel("图 书 编 号:");
    JLabel bookName= new JLabel("书   名:");
    JLabel bookAuthor=new JLabel("作   者:");
    JLabel press= new JLabel("出 版 社:");
    JLabel pressTime=new JLabel("出 版 日 期:");
    JLabel bookAbstract=new JLabel("摘   要:");
    JLabel storage =new JLabel("总   量:");
    JLabel remain=new JLabel("现 存 量:");
    JLabel remain1=new JLabel("等待总量更新:"); // 设置各种标签
    JTextField numberField=new JTextField();
    JTextField nameField=new JTextField();
    JTextField authorField=new JTextField();
    JTextField pressField=new JTextField();
    JTextField pressTimeField=new JTextField();
    JTextField abstractField=new JTextField();
    JTextField storageField=new JTextField(); //设置标签对应的的文本框
    JButton cancel=new JButton("取  消");
    JButton confirm=new JButton("确  认");
    /**
     增加书籍页面的构造方法
     */
    public AddBook(){
        c.add(p1,BorderLayout.NORTH);//把p1放在入底部容器
        setBounds(100,100,400,400);
        p1.setLayout(new GridLayout(9,2,20,10));//设置P1为网格布局
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
        cancel.addActionListener(this); //添加点击事件
        confirm.addActionListener(this);//添加点击事件
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            this.dispose();  // 关闭当前窗口
        }
        if (e.getSource()==confirm){
            this.dispose();  //关闭当前窗口
            BookInfo book=new BookInfo(numberField.getText(),nameField.getText(), authorField.getText(),pressField.getText(),pressTimeField.getText(),abstractField.getText(),Integer.parseInt(storageField.getText()),Integer.parseInt(storageField.getText()));
            // 通过填写的文本数据得到新的书本对象
            ArrayList<String> strArray =new ArrayList<>();
            strArray=op.addBookJudgement(); //用String 数组 读取存在的书籍所有编号
            int n=0;
            int replicate=0;
            while(n<Integer.parseInt(strArray.get(0))){
                n++;
                if(numberField.getText().equals(strArray.get(n))){
                    replicate++;
                }
            } // 判断书籍是否已经存在
            if(replicate==0) {
                op.saveBook(book); //调用方法保存书籍（书本对象）;
                JOptionPane.showMessageDialog(null, "成功保存该本书", "提示", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "该书名(编号)已经存在了", "警告", JOptionPane.INFORMATION_MESSAGE);
            }
            }
        }
    }
