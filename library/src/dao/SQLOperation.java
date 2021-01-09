package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import util.BookInfo;
import util.Date1;
import util.ReaderInfo;
/**
 * SQLOperation�࣬��������е���ɾ��ķ���
 */
public class SQLOperation {
    static PreparedStatement ps = null;
    static Connection ct = null;
    static ResultSet rs = null;
    /**
     * �������ݿ�
     */
    public SQLOperation() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?serverTimezone=GMT", "root", "yzzlovestardust.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * ����Ա��¼����
     * @param name
     * @param password
     * @return
     */
    public int logCheck(String name, String password) {
        int i = 0;
        try {
            ArrayList<String> strArray = new ArrayList<String>();
            String sql = "select * FROM ����Ա";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery(); //�����ݿ���Ӧ�Ĳ�ѯ��������ResultSet������й�����ʹ��
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("��¼��");
                strArray.add(s);
                s = rs.getString("����");
                strArray.add(s);
            }
            if (strArray.get(0).equals(name) &&
                    strArray.get(1).equals(password)) {
                i = 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.print(i);
        return i;
    }
    /**
     * ��������ͼ�������
     * @return
     */
    public ArrayList addBookJudgement1() {
        ArrayList<String> strArray = new ArrayList<>();
        ArrayList<String> strArray1 = new ArrayList<>();
        try {
            String sql = "select ���� from ͼ����Ϣ";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("����");
                strArray.add(s);
                count++;
            }
            strArray.add(Integer.toString(count));
            for (; count >= 0; count--) {
                strArray1.add(strArray.get(count));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray1;
    }

    /**
     * �������ж��ߵ�����
     * @return
     */
    public ArrayList addReaderJudgement1() {
        ArrayList<String> strArray = new ArrayList<>();
        ArrayList<String> strArray1 = new ArrayList<>();
        try {
            String sql = "select ���� from ������Ϣ";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("����");
                strArray.add(s);
                count++;
            }
            strArray.add(Integer.toString(count)); // ???
            for (; count >= 0; count--) {
                strArray1.add(strArray.get(count));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray1;
    }
    /**
     * ��������ͼ��ı��
     * @return
     */
    public ArrayList addBookJudgement() {   //  ��ѯͼ��ı��
        ArrayList<String> strArray = new ArrayList<>();
        ArrayList<String> strArray1 = new ArrayList<>();
        try {
            String sql = "select ��� from ͼ����Ϣ";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("���");
                strArray.add(s);
                count++;
            }
            strArray.add(Integer.toString(count)); // ��countת�����ַ�������strArray
            for (; count >= 0; count--) {
                strArray1.add(strArray.get(count));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray1;
    }
    /**
     * �������ж��ߵı��
     * @return
     */
    public ArrayList addReaderJudgement() {
        ArrayList<String> strArray = new ArrayList<>();
        ArrayList<String> strArray1 = new ArrayList<>();
        try {
            String sql = "select ��� from ������Ϣ";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("���");
                strArray.add(s);
                count++;
            }
            strArray.add(Integer.toString(count)); // ???
            for (; count >= 0; count--) {
                strArray1.add(strArray.get(count));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray1;
    }
    /**
     * ���������Ϣ
     * @param reader
     */
    public void saveReader(ReaderInfo reader) {
        try {
            String sql = "INSERT INTO ������Ϣ(���,����,�Ա�,ϵ��,�꼶)VALUES(?,?,?,?,?)";
            PreparedStatement pts = ct.prepareStatement(sql);
            pts.setString(1, reader.getNumber());
            pts.setString(2, reader.getName());
            pts.setString(3, reader.getSex());
            pts.setString(4, reader.getDpt());
            pts.setInt(5, reader.getGrade());
            pts.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * ����ͼ����Ϣ
     * @param book
     */
    public void saveBook(BookInfo book) { //����ͼ����Ϣ
        try {
            String sql = "INSERT INTO ͼ����Ϣ(���,����,����,������,��������,ժҪ,����,�ִ���)VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pts = ct.prepareStatement(sql);
            pts.setString(1, book.getNumber());
            pts.setString(2, book.getName());
            pts.setString(3, book.getAuthor());
            pts.setString(4, book.getPress());
            pts.setString(5, book.getPressTime());
            pts.setString(6, book.getBookAbstract());
            pts.setInt(7, book.getTotal());
            pts.setInt(8, book.getRemain());
            pts.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * ͨ��������ѯͼ����
     * @param book
     * @return
     */
    public String searchBookByName(BookInfo book) {
        String s = null;
        try {
            String sql = "select ��� FROM ͼ����Ϣ where ����=?;";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, book.getName());
            rs = ps.executeQuery();
            while (rs.next()) {
                s = rs.getString("���");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }
    /**
     * ͨ���������Ҷ��߱��
     * @param reader
     * @return
     */
    public String searchReaderByName(ReaderInfo reader) {
        String s = null;
        try {
            String sql = "select ��� FROM ������Ϣ where ����=?;";
            PreparedStatement pts = ct.prepareStatement(sql);
            pts.setString(1, reader.getName());
            rs = pts.executeQuery();
            while (rs.next()) {
                s = rs.getString("���");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }
    /**
     * ͨ�����ɾ��ͼ����Ϣ��ɾ���ɹ�����1��ʧ�ܷ���0
     * @param book
     * @return
     */
    public int deleteBook(BookInfo book) {
        try {
            String sql = "delete FROM ͼ����Ϣ where ���=?;";
            PreparedStatement pts = ct.prepareStatement(sql);
            pts.setString(1, book.getNumber());
            int count = pts.executeUpdate();
            if (count == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    /**
     * ͨ�����ɾ��������Ϣ��ɾ���ɹ�����1��ʧ�ܷ���0
     * @param reader
     * @return
     */
    public int deleteReader(ReaderInfo reader) {
        try {
            String sql = "delete FROM ������Ϣ where ���=?;";
            PreparedStatement pst = ct.prepareStatement(sql);
            pst.setString(1, reader.getNumber());
            int count = pst.executeUpdate();
            if (count == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    /**
     * ͨ��ͼ���ŵ���ͼ����Ϣ
     * @param book
     * @return
     */
    public ArrayList outputBook(BookInfo book) {
        ArrayList<String> strArray = new ArrayList<>();
        try {
            String sql = "select * FROM ͼ����Ϣ where ��� =?;";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, book.getNumber());
            rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString("���");
                strArray.add(s);
                s = rs.getString("����");
                strArray.add(s);
                s = rs.getString("����");
                strArray.add(s);
                s = rs.getString("������");
                strArray.add(s);
                s = rs.getString("��������");
                strArray.add(s);
                s = rs.getString("ժҪ");
                strArray.add(s);
                s = rs.getString("����");
                strArray.add(s);
                s = rs.getString("�ִ���");
                strArray.add(s);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray;
    }
    /**
     * ���ݱ�ŵ���������Ϣ
     * @param reader
     * @return
     */
    public ArrayList outputReader(ReaderInfo reader) {
        ArrayList<String> strArray = new ArrayList<>();
        try {
            String sql = "select * FROM ������Ϣ where ���=?;";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, reader.getNumber());
            rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString("���");
                strArray.add(s);
                s = rs.getString("����");
                strArray.add(s);
                s = rs.getString("�Ա�");
                strArray.add(s);
                s = rs.getString("ϵ��");
                strArray.add(s);
                s = rs.getString("�꼶");
                strArray.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray;
    }
    /**
     * ���ݱ�Ÿ���ͼ����Ϣ
     * @param book
     */
    public void inputBook(BookInfo book) { //����ͼ����Ϣ
        try {
            String sql = "update ͼ����Ϣ set ����=?,����=?,������=?,��������=?,ժҪ=?,����=?,�ִ���=? where ���=?;";
            PreparedStatement pts = ct.prepareStatement(sql);
            pts.setString(1, book.getName());
            pts.setString(2, book.getAuthor());
            pts.setString(3, book.getPress());
            pts.setString(4, book.getPressTime());
            pts.setString(5, book.getBookAbstract());
            pts.setInt(6, book.getTotal());
            pts.setInt(7, book.getRemain());
            pts.setString(8, book.getNumber());
            pts.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * ���ݱ�Ÿ��¶�����Ϣ
     * @param reader
     */
    public void inputReader(ReaderInfo reader) {
        try {
            String sql = "update ������Ϣ set ����=?,�Ա�=?,ϵ��=?,�꼶=?,where ���=?;";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, reader.getName());
            ps.setString(2, reader.getSex());
            ps.setString(3, reader.getDpt());
            ps.setInt(4, reader.getGrade());
            ps.setString(5, reader.getNumber());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * ������ȡ�����е�ͼ����Ϣ
     * @return
     */
    public Object[][] allBook(){ //��λ�����ȡ����ͼ����Ϣ
        Object[][]ob =new Object[11000][8];
        int n=0;
        try {
            String sql="select * FROM ͼ����Ϣ";
            PreparedStatement ps=ct.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                ob[n][0]=rs.getString("���");
                ob[n][1]=rs.getString("����");
                ob[n][2]=rs.getString("����");
                ob[n][3]=rs.getString("������");
                ob[n][4]=rs.getString("��������");
                ob[n][5]=rs.getString("ժҪ");
                ob[n][6]=rs.getString("����");
                ob[n][7]=rs.getString("�ִ���");
                n++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return ob;
    }
    /**
     * ������ȡ�����еĶ�����Ϣ
     * @return
     */
    public Object[][] allReader(){
        Object[][]ob =new Object[100][5];
        int n=0;
        try {
            String sql="select * FROM ������Ϣ";
            PreparedStatement ps=ct.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                ob[n][0]=rs.getString("���");
                ob[n][1]=rs.getString("����");
                ob[n][2]=rs.getString("�Ա�");
                ob[n][3]=rs.getString("ϵ��");
                ob[n][4]=rs.getString("�꼶");
                n++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return ob;
    }
    /**
     * ����ͼ����ļ�¼
     * @param bookNumber
     * @param readerNumber
     * @param borrowTime
     * @param deadline
     */
    public void insertBorrow(String bookNumber,String readerNumber,String borrowTime,String deadline){
        try{
            String sql="Insert into ͼ�����(ͼ����,���߱��,����ʱ��,Ӧ��ʱ��) values(?,?,?,?)";
            PreparedStatement ps=ct.prepareStatement(sql);
            ps.setString(1,bookNumber);
            ps.setString(2,readerNumber);
            ps.setString(3,borrowTime);
            ps.setString(4,deadline);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    /**
     * ����ͼ���źж��߱�Ų�ѯ�����¼,�����ھͷ��ر�š�
     * @param bookNumber
     * @param readerNumber
     * @return
     */
    public int reBorrowCheck(String bookNumber,String readerNumber){
        int i=0;
        ArrayList<String>strArray=new ArrayList<>();
        try {
            String sql="select * FROM ͼ����� where ͼ����=? and ���߱��=?";
            PreparedStatement pts=ct.prepareStatement(sql);
            pts.setString(1,bookNumber);
            pts.setString(2,readerNumber);
            rs=pts.executeQuery();
            int count=0;
            while (rs.next()){
                String s=rs.getString("ͼ����");
                strArray.add(s);
                s=rs.getString("���߱��");
                strArray.add(s);  //�ɼ����������
                count++;
            }
            System.out.println(strArray);
            if (count>=1)
            {i=1;}
        }catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }
    /**
     * //���ݶ��߱�ź�����ʱ���ѯ�黹���ڣ��������򷵻�����
     * @param readerNumber
     * @param currentTime
     * @return
     */
    public int deadLineCheck(String readerNumber,long currentTime){
        int i=0;
        ArrayList<String>strArray=new ArrayList<>();
        try {
            String sql="select * FROM ͼ����� where ���߱��=?";
            PreparedStatement ps= ct.prepareStatement(sql);
            ps.setString(1,readerNumber);
            rs=ps.executeQuery();
            int count=0;
            while (rs.next()){
                String s=rs.getString("Ӧ��ʱ��");
                strArray.add(s);
                count++;
            }
            for (;count>0;count--){
                if(Long.parseLong(strArray.get(count-1))<=currentTime){
                    i++;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return i;
    }
    /**
     * ����ͼ���źͶ��߱�Ų�ѯ���ļ�¼�����ڷ���0.���򷵻�1
     * @param bookNumber
     * @param readerNumber
     * @return
     */
    public int returnCheck(String bookNumber,String readerNumber){
        int i=0;
        ArrayList<String>strArray=new ArrayList<>();
        try {
            String sql="select * FROM ͼ����� where ͼ����=? and ���߱��=?";
            PreparedStatement ps=ct.prepareStatement(sql);
            ps.setString(1,bookNumber);
            ps.setString(2,readerNumber);
            rs=ps.executeQuery();
            while (rs.next()){
                String s=rs.getString("ͼ����");
                strArray.add(s);
                s=rs.getString("���߱��");
                strArray.add(s);
            }
            System.out.println(strArray);
            if (strArray.get(0)!=bookNumber||strArray.get(1)!=readerNumber){
                i=0;
            }
        }catch (Exception e){
            System.out.println(e);
            i=1;
        }
        System.out.println(i);
        return i;
    }
    /**
     * ����ͼ���źͶ��߱��ɾ��������Ϣ���ɹ�����1�����򷵻�0
     * @param bookNumber
     * @param readerNumber
     * @return
     */
    public int deleteResult(String bookNumber,String readerNumber){
        try{
            String sql="delete FROM ͼ����� where ͼ����=? and ���߱��=?";
            PreparedStatement pts=ct.prepareStatement(sql);
            pts.setString(1,bookNumber);
            pts.setString(2,readerNumber);
            int count = pts.executeUpdate();
            if (count==1){
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
    /**
     * ��ѯ���еĽ����¼
     * @return
     */
    public Object[][] borrowList() {
        Object[][] ob = new Object[100][5];
        int n = 0;
        try {
            String sql = "select * FROM ͼ�����";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ob[n][0] = rs.getString("ͼ����");
                ob[n][1] = rs.getString("���߱��");
                String s =rs.getString("����ʱ��");
                ob[n][2] = s;
                String  s1 = rs.getString("Ӧ��ʱ��");
                ob[n][3] = s1;
                Date1 d=new Date1();
                String s2= d.borrowTimeInterface(System.currentTimeMillis());
                if (s1.compareTo(s2) < 0) { 
                    ob[n][4] = "������";
                } else {
                    ob[n][4] = "δ����";
                }
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ob;
    }
    /**
     * ͨ����Ų�ѯ���߽�����Ϣ
     * @param readerNumber
     * @return
     */
    public Object[][] borrowListForReader(String readerNumber){
        Object[][] ob = new Object[100][4];
        int n = 0;
        try {
            String sql = "select * FROM ͼ����� where ���߱��=?";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, readerNumber);
            rs = ps.executeQuery();
            while (rs.next()) {
                ob[n][0] = rs.getString("ͼ����");
                String s = rs.getString("����ʱ��");
                ob[n][1] = s;
                String s1 = rs.getString("Ӧ��ʱ��");
                ob[n][2] = s1;
                Date1 d=new Date1();
                String s2= d.borrowTimeInterface(System.currentTimeMillis());
                if (s1.compareTo(s2) < 0) {
                    ob[n][3] ="������";
                } else {
                    ob[n][3] ="δ����";
                }
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ob;
    }

    /**
     * ����ͼ���Ų�ѯ��Ӧͼ�������Ϣ
     * @param bookNumber
     * @return
     */
    public Object[][] borrowListForBook(String bookNumber) {
        Object[][] ob = new Object[11000][8];
        int n = 0;
        try {
            String sql = "select * FROM ͼ����� where ͼ���� =?";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, bookNumber);
            rs = ps.executeQuery();
            while (rs.next()) {
                ob[n][0] = rs.getString("���߱��");
                String s = rs.getString("����ʱ��");
                ob[n][1] = s;
                String s1= rs.getString("Ӧ��ʱ��");
                ob[n][2] = s1; 
                Date1 d=new Date1();
                String s2= d.borrowTimeInterface(System.currentTimeMillis());
                if (s1.compareTo(s2) < 0) { 
                    ob[n][3] = "������";
                } else {
                    ob[n][3] = "δ����";
                }
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ob;
    }
    /**
     * ��ѯɾ����ͼ���Ƿ��Ѿ���������Ѿ�����򷵻����������򷵻�0
     * @param bookNumber
     * @return
     */
    public int deleteBookCheck(String bookNumber) {
        int i = 0;
        ArrayList<String>strArray = new ArrayList<String>();
        try {
            String sql = "select *  FROM ͼ�����";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("ͼ����");
                strArray.add(s);
                count++;
            }
            for (; count> 0; count--) {
                if (strArray.get(count - 1).equals(bookNumber))
                {
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }
    /**
     * ��ѯɾ���Ķ����Ƿ��Ѿ����飬���Ѿ������򷵻����������򷵻�0
     * @param readerNumber
     * @return
     */
    public int deleteReaderCheck(String readerNumber) {
        int i = 0;
        ArrayList<String>strArray = new ArrayList<String>();
        try {
            String sql = "select *  FROM ͼ�����";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("���߱��");
                strArray.add(s);
                count++;
            }
            for (; count> 0; count--) {
                if (strArray.get(count - 1).equals(readerNumber)) {
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }
}

