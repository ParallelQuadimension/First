package util;
/**
 *�����µ�ͼ����Ϣ���󣬿ɸ��ݣ�������ţ�����ţ����������������ԣ��������
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
