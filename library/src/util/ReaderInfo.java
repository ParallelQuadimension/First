package util;
/**
 *构造新的图书信息对象，可根据（）（编号）（编号，姓名）（所有属性）构造对象
 */
public class ReaderInfo {
    private String number,name,sex,dpt;
    private int grade;
    public ReaderInfo(){}
    public ReaderInfo(String number){
        this.number=number;
    }
    public ReaderInfo(String number,String name){
        this.number=number;
        this.name=name;
    }
    public ReaderInfo(String number,String name,String sex,String dpt,int grade){
        this.number=number;
        this.name=name;
        this.sex=sex;
        this.dpt=dpt;
        this.grade=grade;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getDpt() {
        return dpt;
    }

    public int getGrade() {
        return grade;
    }
}
