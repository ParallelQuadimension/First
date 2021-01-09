package util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 ÈÕÆÚ¼ÆËã
 */
public class Date1 {
    public Date1(){}
    public static String borrowTimeInterface (long time) {
        SimpleDateFormat df = (SimpleDateFormat)DateFormat.getInstance();
        df.applyPattern("yyyy-MM-dd");
        String s = df.format(time);
        return s;
    }
}
