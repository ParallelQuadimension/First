package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import dao.SQLOperation;
import util.ReaderInfo;
/**
 增加读者页面
 */
public class AddReader extends JFrame implements ActionListener
{
    SQLOperation op=new SQLOperation();
    Container c=getContentPane(); //获得底层容器
    JPanel p1=new JPanel();
    JLabel readerNumber =new JLabel("读 者 编 号:");
    JLabel readerName =new JLabel("读 者 姓 名:");
    JLabel sex =new JLabel("性   别 :");
    JLabel dpt =new JLabel("系   别:");
    JLabel grade =new JLabel("年   级:"); //创建读者信息标签
    JTextField numberField =new JTextField();
    JTextField nameField =new JTextField();
    JTextField sexField =new JTextField();
    JTextField dptField =new JTextField();
    JTextField gradeField =new JTextField(); //创建属性文本框
    JButton cancel=new JButton("取  消");
    JButton confirm=new JButton("确  认");
    public AddReader(){
        c.add(p1,BorderLayout.NORTH);
        setBounds(100,100,400,400);
        p1.setLayout(new GridLayout(6,2,20,10));
        p1.add(readerNumber);
        p1.add(numberField);
        p1.add(readerName);
        p1.add(nameField);
        p1.add(sex);
        p1.add(sexField);
        p1.add(dpt);
        p1.add(dptField);
        p1.add(grade);
        p1.add(gradeField);
        p1.add(cancel);
        p1.add(confirm);   //标签在左，文本框在右，分布在P1上
        cancel.addActionListener(this); //添加点击事件
        confirm.addActionListener(this);//添加点击事件
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            this.dispose();  // 关闭当前窗口
        }
        if (e.getSource()==confirm){
            this.dispose();  //关闭当前窗口
            ReaderInfo reader = new ReaderInfo(numberField.getText(),nameField.getText(),sexField.getText(),dptField.getText(),Integer.parseInt(gradeField.getText()));
            ArrayList<String>strArray=new ArrayList<>();
            strArray=op.addReaderJudgement();  //调用方法查询所有的读者编号，存入数组
            int n=0;
            int replicate=0;
            while(n<Integer.parseInt(strArray.get(0))){
                n++;
                if(numberField.getText().equals(strArray.get(n))){
                    replicate++;
                }
            } // 判断新增读者是否存在
            if(replicate==0) {
                if(!sexField.getText().equals("男")&&!sexField.getText().equals("女")){
                        JOptionPane.showMessageDialog(null, "在性别文本中只能填写男或者女", "警告", JOptionPane.INFORMATION_MESSAGE);}
                // 增加性别判断
                else{
                    op.saveReader(reader);
                        JOptionPane.showMessageDialog(null, "加入读者成功", "提示", JOptionPane.INFORMATION_MESSAGE);}
            }
            else {
                    JOptionPane.showMessageDialog(null, "该读者（编号）已经存在", "警告", JOptionPane.INFORMATION_MESSAGE); }
        }
    }
}
