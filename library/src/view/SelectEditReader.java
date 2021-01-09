package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
/**
 * 读者修改界面
 */
public class SelectEditReader extends JFrame implements ActionListener {
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JLabel readerNumber = new JLabel("读者编号:");
        JTextField numberField = new JTextField();
        JButton cancel = new JButton("取 消");
        JButton select = new JButton("选 择");
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
        f.setTitle("修 改");
        f.setLocation(300, 200);
        f.setSize(300, 300);
        f.setVisible(true);
        } catch (Exception e2) {
        JOptionPane.showMessageDialog(null, "请输入正确的读者编号", "警告", JOptionPane.INFORMATION_MESSAGE);
        }
        }
        }
}

