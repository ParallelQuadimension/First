package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
/**
 * �����޸Ľ���
 */
public class SelectEditReader extends JFrame implements ActionListener {
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel readerNumber = new JLabel("���߱��:");
        JTextField numberField = new JTextField();
        JButton cancel = new JButton("ȡ ��");
        JButton select = new JButton("ѡ ��");
        public SelectEditReader() {
        c.add(p1, BorderLayout.NORTH);
        p1.setLayout(new GridLayout(2, 2, 20, 10));
        p1.add(readerNumber);
        p1.add(numberField);
        p1.add(cancel);
        p1.add(select);
        cancel.addActionListener(this);
        select.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
        this.dispose();
        }
        if (e.getSource() == select) {
        this.dispose();
        try {
        EditReader f = new EditReader(numberField.getText());
        f.setTitle("�� ��");
        f.setLocation(300, 200);
        f.setSize(300, 300);
        f.setVisible(true);
        } catch (Exception e2) {
        JOptionPane.showMessageDialog(null, "��������ȷ�Ķ��߱��", "����", JOptionPane.INFORMATION_MESSAGE);
        }
        }
        }
}

