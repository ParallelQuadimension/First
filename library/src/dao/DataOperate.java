package dao;
import java.util.Random;
import util.BookInfo;
import util.Date1;
/**
 * ����鼮��Ϣ
 * @author DELL
 *
 */
public class DataOperate {
    public static void main(String[] args) {
        Addsj();	}

    private static void Addsj() {
        int n=0;
        SQLOperation o=new SQLOperation();
        while(n<1000) {
            n++;
            int remain=Addtotal();
            BookInfo book=new BookInfo(Addbh(),name(),Addauthor(),Addpress(),Addpresstime(),"����",remain,remain);
            o.saveBook(book);
        }
        System.out.println("������");
    }

    private static int Addtotal() {
        // TODO Auto-generated method stub
        int index=getNum(0,30);
        return index;
    }
    private static String  Addpresstime() {
        Random rndYear=new Random();
        int year=rndYear.nextInt(18)+2000;  //����[2000,2017]����������
        Random rndMonth=new Random();
        int month=rndMonth.nextInt(12)+1;   //����[1,12]����������
        Random rndDay=new Random();
        int Day=rndDay.nextInt(30)+1;       //����[1,30)����������
        return year+"-"+month+"-"+Day;


    }

    private static String Addpress() {
        String press[]= {"�������������","����������","����������","�Ϻ����������","ѧϰ������","�����ͼ������","�Ϻ���ѧ����������","�й��������ó�����","�����ʵ������","���տ�ѧ����������","���Ͽ�ѧ����������","���ӹ�ҵ������","�й�ʱ�����ó�����","�й��Ͷ���ᱣ�ϳ�����","�й���ٿ�ȫ�������"};
        int index=getNum(0,14);
        return press[index];
    }
    //��������
    private static String firstName="��Ǯ��������֣��������������������������ʩ�ſײ��ϻ���κ�ս���л������ˮ��������˸��ɷ�����³Τ������ﻨ������Ԭ��ۺ��ʷ�Ʒ����Ѧ�׺����������ޱϺ�����������ʱ��Ƥ���뿵����Ԫ������ƽ�ƺ�������Ҧ��տ����ë����ױ���갼Ʒ��ɴ�̸��é���ܼ�������ף������������ϯ����ǿ��·¦Σ��ͯ�չ�÷ʢ�ֵ�����������Ĳ��﷮���������֧�¾̹�¬Ī�������Ѹɽ�Ӧ�������ڵ��������������ʯ�޼�ť�������ϻ���½��������춻���κ�ӷ����ഢ���������ɾ��θ����ڽ��͹�����ɽ�ȳ������ȫۭ�����������������ﱩ�����������������ղ����Ҷ��˾��۬�輻��ӡ�ް׻���̨�Ӷ����̼���׿�����ɳ����������ܲ�˫��ݷ����̷�����̼������Ƚ��۪Ӻȴ�ɣ���ţ��ͨ�����༽ۣ����ũ�±�ׯ�̲����ֳ�Ľ����ϰ�°���������������θ����߾Ӻⲽ�����������Ŀܹ�»�ڶ�Ź�����εԽ��¡ʦ�������˹��������������Ǽ��Ŀ�����ɳؿ������ᳲ�������󽭺�����Ȩ�ָ��滸����ٹ˾���Ϲ�ŷ���ĺ�������˶��������ʸ�ξ�ٹ����̨��ұ���������������̫����������������ԯ�������������Ľ����������˾ͽ˾������˾���붽�ӳ�����ľ����������������ṫ���ذμй��׸����������ַ���۳Ϳ�նθɰ��ﶫ�����ź��ӹ麣����΢����˧�ÿ�������������������������Ĳ��٦�����Ϲ�ī�������갮��١�����Ը��ټ�����";
    //����Ů������
    private static String girl="���Ӣ���������Ⱦ���������֥��Ƽ�����ҷ���ʴ��������÷���������滷ѩ�ٰ���ϼ����ݺ�����𷲼Ѽ�������������Ҷ�������������ɺɯ������ٻ�������ӱ¶������������Ǻɵ���ü������ޱݼ���Է�ܰ�������԰��ӽ�������ع���ѱ�ˬ������ϣ����Ʈ�����������������������ܿ�ƺ������˿ɼ���Ӱ��֦˼�� ";
    //������������
    private static String boy="ΰ�����㿡��ǿ��ƽ�����Ļ�������������־��������ɽ�ʲ���������Ԫȫ��ʤѧ��ŷ���������ɱ�˳���ӽ��β��ɿ��ǹ���ﰲ����ï�����м�ͱ벩���Ⱦ�����׳��˼Ⱥ���İ�����ܹ����ƺ���������ԣ���ܽ���������ǫ�����֮�ֺ��ʲ����������������ά�������������󳿳�ʿ�Խ��������׵���ʱ̩ʢ��衾��ڲ�����ŷ纽��";

    public static String Addauthor() { //������������
        int index=getNum(0, firstName.length()-1);//���ȡ�����ַ����е�����λ��
        String first=firstName.substring(index, index+1);//��ȡ��λ�õ�����
        int sex=getNum(0,1);//���ȡ�Ա�����1Ϊ������0ΪŮ��
        String str=boy;//��������Ϊ�����ַ���
        int length=boy.length();//��ȡ�����ַ����ĳ���
        if(sex==0){//��������ȡΪ0�������ָ�ΪŮ��
            str=girl;
            length=girl.length();
        }
        index=getNum(0,length-1);//�����ȡ���ֵ�λ��
        String second=str.substring(index, index+1);//��ȡ��λ���е�����
        int hasThird=getNum(0,1);//�����ȡ�����Ƿ��е�������
        String third="";//Ĭ��û�е�������
        if(hasThird==1){//��������ȡΪ1�����е�������
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return (first+second+third);//��������
    }
    private static String f="�й�������ȫ����ҹ����ʮ�˸������л����񹲺͹�������ɷ������ʷ��ò�����ɫ���ᷡ�������ߵ����ر���������ã�����б�ķ�������µ����Ϻ��춼���´����˸ɲ��ӵܷ�������ǳ����ѹ������������ɹ��˼ʹ�ϵ�ķۺ�ɫս̨�����д�������ֻ�������Σ����ԵΣ�ս���";
    private static String s="������������԰��ͼ�ö������ӱ����¶����Ĵ��濴ͼʶ�ֲ��װ��������ҩҩ��ѧ����������;�����˼��ѧϰ��������ҩҵ��ӵ�����߽����ϵر�Ե��ȫ���������ȫ�������������������ĺ����߳��¶���ʧ�ľ��˶���˹ս����ͳ�������ճ̸��˵����� ";
    private static String t="���洫���������²ᷨʫ�Ե伮ƪ���������ʷ��ͨ��������������";
    public static String name() { //��������
        int index=getNum(0, f.length()-1);//���ȡ�����ַ����е�����λ��
        String first=f.substring(index, index+1);//��ȡ��λ�õ�����
        int index1=getNum(0, s.length()-1);//���ȡ�����ַ����е�����λ��
        String second=s.substring(index1, index1+1);//��ȡ��λ�õ�����
        int index2=getNum(0, t.length()-1);//���ȡ�����ַ����е�����λ��
        String third=t.substring(index2, index2+1);//��ȡ��λ�õ�����
        return (first+second+third);//��������
    }
    public static int getNum(int start,int end) {//������ط���ָ����Χ�������
        //Math.random()�������0.0��1.0֮�����
        return (int)(Math.random()*(end-start+1)+start);
    }
    private static  String Addbh() { //���ر��
        // TODO Auto-generated method stub
        String bh=(String.valueOf(getNum(1,11000)));//���ȡ��5λ
        if(bh.length()==1) {
            bh="0000"+bh;
        }else if(bh.length()==2) {
            bh="000"+bh;
        } else if(bh.length()==3) {
            bh="00"+bh;
        } else if(bh.length()==4) {
            bh="0"+bh;
        }else {
            bh=bh;
        }
        return bh;
    }
}