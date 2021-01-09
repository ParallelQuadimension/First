package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import util.BookInfo;
import util.Date1;
import util.ReaderInfo;
/**
 * SQLOperation类，存放了所有的增删查改方法
 */
public class SQLOperation {
    static PreparedStatement ps = null;
    static Connection ct = null;
    static ResultSet rs = null;
    /**
     * 链接数据库
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
     * 管理员登录方法
     * @param name
     * @param password
     * @return
     */
    public int logCheck(String name, String password) {
        int i = 0;
        try {
            ArrayList<String> strArray = new ArrayList<String>();
            String sql = "select * FROM 管理员";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery(); //把数据库响应的查询结果存放在ResultSet类对象中供我们使用
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("登录名");
                strArray.add(s);
                s = rs.getString("密码");
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
     * 返回所有图书的书名
     * @return
     */
    public ArrayList addBookJudgement1() {
        ArrayList<String> strArray = new ArrayList<>();
        ArrayList<String> strArray1 = new ArrayList<>();
        try {
            String sql = "select 书名 from 图书信息";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("书名");
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
     * 返回所有读者的姓名
     * @return
     */
    public ArrayList addReaderJudgement1() {
        ArrayList<String> strArray = new ArrayList<>();
        ArrayList<String> strArray1 = new ArrayList<>();
        try {
            String sql = "select 姓名 from 读者信息";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("姓名");
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
     * 返回所有图书的编号
     * @return
     */
    public ArrayList addBookJudgement() {   //  查询图书的编号
        ArrayList<String> strArray = new ArrayList<>();
        ArrayList<String> strArray1 = new ArrayList<>();
        try {
            String sql = "select 编号 from 图书信息";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("编号");
                strArray.add(s);
                count++;
            }
            strArray.add(Integer.toString(count)); // 将count转换成字符串存入strArray
            for (; count >= 0; count--) {
                strArray1.add(strArray.get(count));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray1;
    }
    /**
     * 返回所有读者的编号
     * @return
     */
    public ArrayList addReaderJudgement() {
        ArrayList<String> strArray = new ArrayList<>();
        ArrayList<String> strArray1 = new ArrayList<>();
        try {
            String sql = "select 编号 from 读者信息";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("编号");
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
     * 保存读者信息
     * @param reader
     */
    public void saveReader(ReaderInfo reader) {
        try {
            String sql = "INSERT INTO 读者信息(编号,姓名,性别,系别,年级)VALUES(?,?,?,?,?)";
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
     * 保存图书信息
     * @param book
     */
    public void saveBook(BookInfo book) { //储存图书信息
        try {
            String sql = "INSERT INTO 图书信息(编号,书名,作者,出版社,出版日期,摘要,总量,现存量)VALUES(?,?,?,?,?,?,?,?)";
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
     * 通过书名查询图书编号
     * @param book
     * @return
     */
    public String searchBookByName(BookInfo book) {
        String s = null;
        try {
            String sql = "select 编号 FROM 图书信息 where 书名=?;";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, book.getName());
            rs = ps.executeQuery();
            while (rs.next()) {
                s = rs.getString("编号");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }
    /**
     * 通过姓名查找读者编号
     * @param reader
     * @return
     */
    public String searchReaderByName(ReaderInfo reader) {
        String s = null;
        try {
            String sql = "select 编号 FROM 读者信息 where 姓名=?;";
            PreparedStatement pts = ct.prepareStatement(sql);
            pts.setString(1, reader.getName());
            rs = pts.executeQuery();
            while (rs.next()) {
                s = rs.getString("编号");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }
    /**
     * 通过编号删除图书信息，删除成功返回1，失败返回0
     * @param book
     * @return
     */
    public int deleteBook(BookInfo book) {
        try {
            String sql = "delete FROM 图书信息 where 编号=?;";
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
     * 通过编号删除读者信息，删除成功返回1，失败返回0
     * @param reader
     * @return
     */
    public int deleteReader(ReaderInfo reader) {
        try {
            String sql = "delete FROM 读者信息 where 编号=?;";
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
     * 通过图书编号导出图书信息
     * @param book
     * @return
     */
    public ArrayList outputBook(BookInfo book) {
        ArrayList<String> strArray = new ArrayList<>();
        try {
            String sql = "select * FROM 图书信息 where 编号 =?;";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, book.getNumber());
            rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString("编号");
                strArray.add(s);
                s = rs.getString("书名");
                strArray.add(s);
                s = rs.getString("作者");
                strArray.add(s);
                s = rs.getString("出版社");
                strArray.add(s);
                s = rs.getString("出版日期");
                strArray.add(s);
                s = rs.getString("摘要");
                strArray.add(s);
                s = rs.getString("总量");
                strArray.add(s);
                s = rs.getString("现存量");
                strArray.add(s);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray;
    }
    /**
     * 根据编号导出读者信息
     * @param reader
     * @return
     */
    public ArrayList outputReader(ReaderInfo reader) {
        ArrayList<String> strArray = new ArrayList<>();
        try {
            String sql = "select * FROM 读者信息 where 编号=?;";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, reader.getNumber());
            rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString("编号");
                strArray.add(s);
                s = rs.getString("姓名");
                strArray.add(s);
                s = rs.getString("性别");
                strArray.add(s);
                s = rs.getString("系别");
                strArray.add(s);
                s = rs.getString("年级");
                strArray.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return strArray;
    }
    /**
     * 根据编号更新图书信息
     * @param book
     */
    public void inputBook(BookInfo book) { //导入图书信息
        try {
            String sql = "update 图书信息 set 书名=?,作者=?,出版社=?,出版日期=?,摘要=?,总量=?,现存量=? where 编号=?;";
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
     * 根据编号更新读者信息
     * @param reader
     */
    public void inputReader(ReaderInfo reader) {
        try {
            String sql = "update 读者信息 set 姓名=?,性别=?,系别=?,年级=?,where 编号=?;";
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
     * 用数组取出所有的图书信息
     * @return
     */
    public Object[][] allBook(){ //二位数组读取所有图书信息
        Object[][]ob =new Object[11000][8];
        int n=0;
        try {
            String sql="select * FROM 图书信息";
            PreparedStatement ps=ct.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                ob[n][0]=rs.getString("编号");
                ob[n][1]=rs.getString("书名");
                ob[n][2]=rs.getString("作者");
                ob[n][3]=rs.getString("出版社");
                ob[n][4]=rs.getString("出版日期");
                ob[n][5]=rs.getString("摘要");
                ob[n][6]=rs.getString("总量");
                ob[n][7]=rs.getString("现存量");
                n++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return ob;
    }
    /**
     * 用数组取出所有的读者信息
     * @return
     */
    public Object[][] allReader(){
        Object[][]ob =new Object[100][5];
        int n=0;
        try {
            String sql="select * FROM 读者信息";
            PreparedStatement ps=ct.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                ob[n][0]=rs.getString("编号");
                ob[n][1]=rs.getString("姓名");
                ob[n][2]=rs.getString("性别");
                ob[n][3]=rs.getString("系别");
                ob[n][4]=rs.getString("年级");
                n++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return ob;
    }
    /**
     * 插入图书借阅记录
     * @param bookNumber
     * @param readerNumber
     * @param borrowTime
     * @param deadline
     */
    public void insertBorrow(String bookNumber,String readerNumber,String borrowTime,String deadline){
        try{
            String sql="Insert into 图书借阅(图书编号,读者编号,借阅时间,应还时间) values(?,?,?,?)";
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
     * 根据图书编号盒读者编号查询借书记录,若存在就返回编号。
     * @param bookNumber
     * @param readerNumber
     * @return
     */
    public int reBorrowCheck(String bookNumber,String readerNumber){
        int i=0;
        ArrayList<String>strArray=new ArrayList<>();
        try {
            String sql="select * FROM 图书借阅 where 图书编号=? and 读者编号=?";
            PreparedStatement pts=ct.prepareStatement(sql);
            pts.setString(1,bookNumber);
            pts.setString(2,readerNumber);
            rs=pts.executeQuery();
            int count=0;
            while (rs.next()){
                String s=rs.getString("图书编号");
                strArray.add(s);
                s=rs.getString("读者编号");
                strArray.add(s);  //可加入后续数据
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
     * //根据读者编号盒现在时间查询归还日期，若逾期则返回正数
     * @param readerNumber
     * @param currentTime
     * @return
     */
    public int deadLineCheck(String readerNumber,long currentTime){
        int i=0;
        ArrayList<String>strArray=new ArrayList<>();
        try {
            String sql="select * FROM 图书借阅 where 读者编号=?";
            PreparedStatement ps= ct.prepareStatement(sql);
            ps.setString(1,readerNumber);
            rs=ps.executeQuery();
            int count=0;
            while (rs.next()){
                String s=rs.getString("应还时间");
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
     * 根据图书编号和读者编号查询借阅记录，存在返回0.否则返回1
     * @param bookNumber
     * @param readerNumber
     * @return
     */
    public int returnCheck(String bookNumber,String readerNumber){
        int i=0;
        ArrayList<String>strArray=new ArrayList<>();
        try {
            String sql="select * FROM 图书借阅 where 图书编号=? and 读者编号=?";
            PreparedStatement ps=ct.prepareStatement(sql);
            ps.setString(1,bookNumber);
            ps.setString(2,readerNumber);
            rs=ps.executeQuery();
            while (rs.next()){
                String s=rs.getString("图书编号");
                strArray.add(s);
                s=rs.getString("读者编号");
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
     * 根据图书编号和读者编号删除借阅信息，成功返回1，否则返回0
     * @param bookNumber
     * @param readerNumber
     * @return
     */
    public int deleteResult(String bookNumber,String readerNumber){
        try{
            String sql="delete FROM 图书借阅 where 图书编号=? and 读者编号=?";
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
     * 查询所有的借书记录
     * @return
     */
    public Object[][] borrowList() {
        Object[][] ob = new Object[100][5];
        int n = 0;
        try {
            String sql = "select * FROM 图书借阅";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ob[n][0] = rs.getString("图书编号");
                ob[n][1] = rs.getString("读者编号");
                String s =rs.getString("借阅时间");
                ob[n][2] = s;
                String  s1 = rs.getString("应还时间");
                ob[n][3] = s1;
                Date1 d=new Date1();
                String s2= d.borrowTimeInterface(System.currentTimeMillis());
                if (s1.compareTo(s2) < 0) { 
                    ob[n][4] = "已逾期";
                } else {
                    ob[n][4] = "未逾期";
                }
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ob;
    }
    /**
     * 通过编号查询读者借阅信息
     * @param readerNumber
     * @return
     */
    public Object[][] borrowListForReader(String readerNumber){
        Object[][] ob = new Object[100][4];
        int n = 0;
        try {
            String sql = "select * FROM 图书借阅 where 读者编号=?";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, readerNumber);
            rs = ps.executeQuery();
            while (rs.next()) {
                ob[n][0] = rs.getString("图书编号");
                String s = rs.getString("借阅时间");
                ob[n][1] = s;
                String s1 = rs.getString("应还时间");
                ob[n][2] = s1;
                Date1 d=new Date1();
                String s2= d.borrowTimeInterface(System.currentTimeMillis());
                if (s1.compareTo(s2) < 0) {
                    ob[n][3] ="已逾期";
                } else {
                    ob[n][3] ="未逾期";
                }
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ob;
    }

    /**
     * 根据图书编号查询相应图书借阅信息
     * @param bookNumber
     * @return
     */
    public Object[][] borrowListForBook(String bookNumber) {
        Object[][] ob = new Object[11000][8];
        int n = 0;
        try {
            String sql = "select * FROM 图书借阅 where 图书编号 =?";
            PreparedStatement ps = ct.prepareStatement(sql);
            ps.setString(1, bookNumber);
            rs = ps.executeQuery();
            while (rs.next()) {
                ob[n][0] = rs.getString("读者编号");
                String s = rs.getString("借阅时间");
                ob[n][1] = s;
                String s1= rs.getString("应还时间");
                ob[n][2] = s1; 
                Date1 d=new Date1();
                String s2= d.borrowTimeInterface(System.currentTimeMillis());
                if (s1.compareTo(s2) < 0) { 
                    ob[n][3] = "已逾期";
                } else {
                    ob[n][3] = "未逾期";
                }
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ob;
    }
    /**
     * 查询删除的图书是否已经借出，若已经借出则返回正数，否则返回0
     * @param bookNumber
     * @return
     */
    public int deleteBookCheck(String bookNumber) {
        int i = 0;
        ArrayList<String>strArray = new ArrayList<String>();
        try {
            String sql = "select *  FROM 图书借阅";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("图书编号");
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
     * 查询删除的读者是否已经借书，若已经借书则返回正数，否则返回0
     * @param readerNumber
     * @return
     */
    public int deleteReaderCheck(String readerNumber) {
        int i = 0;
        ArrayList<String>strArray = new ArrayList<String>();
        try {
            String sql = "select *  FROM 图书借阅";
            PreparedStatement ps = ct.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                String s = rs.getString("读者编号");
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

