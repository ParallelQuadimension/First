package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import dao.SQLOperation;
import util.ReaderInfo;
/**
 ���Ӷ���ҳ��
 */
public class AddReader extends JFrame implements ActionListener
{
    SQLOperation op=new SQLOperation();
    Container c=getContentPane(); //��õײ�����
    JPanel p1=new JPanel();
    JLabel readerNumber =new JLabel("�� �� �� ��:");
    JLabel readerName =new JLabel("�� �� �� ��:");
    JLabel sex =new JLabel("��   �� :");
    JLabel dpt =new JLabel("ϵ   ��:");
    JLabel grade =new JLabel("��   ��:"); //����������Ϣ��ǩ
    JTextField numberField =new JTextField();
    JTextField nameField =new JTextField();
    JTextField sexField =new JTextField();
    JTextField dptField =new JTextField();
    JTextField gradeField =new JTextField(); //���������ı���
    JButton cancel=new JButton("ȡ  ��");
    JButton confirm=new JButton("ȷ  ��");
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
        p1.add(confirm);   //��ǩ�����ı������ң��ֲ���P1��
        cancel.addActionListener(this); //��ӵ���¼�
        confirm.addActionListener(this);//��ӵ���¼�
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            this.dispose();  // �رյ�ǰ����
        }
        if (e.getSource()==confirm){
            this.dispose();  //�رյ�ǰ����
            ReaderInfo reader = new ReaderInfo(numberField.getText(),nameField.getText(),sexField.getText(),dptField.getText(),Integer.parseInt(gradeField.getText()));
            ArrayList<String>strArray=new ArrayList<>();
            strArray=op.addReaderJudgement();  //���÷�����ѯ���еĶ��߱�ţ���������
            int n=0;
            int replicate=0;
            while(n<Integer.parseInt(strArray.get(0))){
                n++;
                if(numberField.getText().equals(strArray.get(n))){
                    replicate++;
                }
            } // �ж����������Ƿ����
            if(replicate==0) {
                if(!sexField.getText().equals("��")&&!sexField.getText().equals("Ů")){
                        JOptionPane.showMessageDialog(null, "���Ա��ı���ֻ����д�л���Ů", "����", JOptionPane.INFORMATION_MESSAGE);}
                // �����Ա��ж�
                else{
                    op.saveReader(reader);
                        JOptionPane.showMessageDialog(null, "������߳ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);}
            }
            else {
                    JOptionPane.showMessageDialog(null, "�ö��ߣ���ţ��Ѿ�����", "����", JOptionPane.INFORMATION_MESSAGE); }
        }
    }
}
