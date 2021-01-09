package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import dao.SQLOperation;
import util.ReaderInfo;
/**
 * 读者修改页面
 */
public class EditReader extends JFrame implements ActionListener {
        SQLOperation op = new SQLOperation();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel readerNumber = new JLabel("读者编号:");
        JLabel readerName = new JLabel("读者姓名:");
        JLabel sex = new JLabel("性 别:");
        JLabel dpt = new JLabel("系 别:");
        JLabel grade = new JLabel("年 级:");
        JLabel numberField = new JLabel();
        JTextField nameField = new JTextField();
        JTextField sexField = new JTextField();
        JTextField dptField = new JTextField();
        JTextField gradeField = new JTextField();
        JButton cancel = new JButton("取 消");
        JButton save = new JButton("保 存");
public EditReader(String number){
    numberField.setText(number);
    ReaderInfo reader = new ReaderInfo(number);
    ArrayList<String>strArray = new ArrayList<String>();
    strArray = op.outputReader(reader);
    System.out.println(strArray);
    numberField.setText(number);
    nameField.setText(strArray.get(1));
    sexField.setText(strArray.get(2));
    dptField.setText(strArray.get(3));
    gradeField.setText(strArray.get(4));
    c.add(p1, BorderLayout.NORTH);
    p1.setLayout(new GridLayout(6, 2, 20, 10));
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
    p1.add(save);
    cancel.addActionListener(this);
    save.addActionListener(this);
}
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
        this.dispose();
        }
        if (e.getSource() == save) {
        ReaderInfo reader = new ReaderInfo(numberField.getText(), nameField.getText(), sexField.getText(), dptField.getText(), Integer.parseInt(gradeField.getText()));
        if (!sexField.getText().equals("男") && !sexField.getText().equals("nv")) {
        JOptionPane.showMessageDialog(null, "在性别中，只能输入男或女", "警告", JOptionPane.INFORMATION_MESSAGE);
        } else {
        op.inputReader(reader);JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        } } }
}