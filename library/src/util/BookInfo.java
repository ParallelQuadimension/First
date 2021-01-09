package util;
/**
 *�����µ�ͼ����Ϣ���󣬿ɸ��ݣ�������ţ�����ţ����������������ԣ��������
 */
public class BookInfo {
    private String number,name,author,press,pressTime,bookAbstract;
    private int total,remain;
    public BookInfo(){}
    public BookInfo(String number){
        this.number=number;
    }
    public BookInfo(String number,String name){
        this.number=number;
        this.name=name;
    }
    public BookInfo(String number,String name,String author,String press,String pressTime,String bookAbstract,int total,int remain){
        this.number=number;
        this.name=name;
        this.author=author;
        this.press=press;
        this.pressTime=pressTime;
        this.bookAbstract=bookAbstract;
        this.total=total;
        this.remain=remain;
    }
    public void setRemain(int i){
        this.remain=i;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPress() {
        return press;
    }

    public String getPressTime() {
        return pressTime;
    }

    public String getBookAbstract() {
        return bookAbstract;
    }

    public int getTotal() {
        return total;
    }

    public int getRemain() {
        return remain;
    }
}
