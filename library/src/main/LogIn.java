package main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.management.openmbean.OpenDataException;
import javax.swing.*;
import dao.SQLOperation;
import view.MyFrame;
import java.awt.Font;
/**
 * ��¼����
 */
public class LogIn {
    public static void main(String[] args) {
        My f = new My();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("�� ¼");
        f.setLocation(300, 200);
        f.setSize(300, 150);
        f.setVisible(true);
    }
}
class My extends JFrame implements ActionListener {
        SQLOperation op = new SQLOperation();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JLabel userName = new JLabel("    ����ԱID :");
        JLabel password = new JLabel("    ����     :");
        JTextField nameField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton confirm = new JButton("ȷ  ��");
public   My() {
        c.add(p1, BorderLayout.CENTER);
        c.add(p2, BorderLayout.SOUTH);
        p1.setLayout(new GridLayout(2, 2, 50, 10));
        p1.add(userName);
        p1.add(nameField);
        p1.add(password);
        p1.add(passField);
        p2.add(confirm);
        confirm.addActionListener(this);
        }

@Override
 public void actionPerformed(ActionEvent e) {
    if (e.getSource() == confirm) {
         String name = nameField.getText();
         String pass = passField.getText();
         int i = op.logCheck(name, pass);
         if (i == 1) {
         this.dispose();
         MyFrame f = new MyFrame();
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         f.setTitle("ͼ �� �� �� ϵ ͳ");
         f.setLocation(300, 200);
         f.setSize(580, 300);
         f.setVisible(true);
         } else {
         JOptionPane.showMessageDialog(null, "��������ȷ���û�����������", "����", JOptionPane.INFORMATION_MESSAGE);
         }
    }
}
}
