package swipe.maktub.swipeviewchoice.utils;

/**
 * Created by Tuấn Sơn on 18/7/2016.
 */
public class Utils {
    public static String formatValuesPoint(String values) {
        String result = "";
        if (values.length() > 3 && values.length() < 7) {
            // Examle 1,000 < 1,000,000
            String prefix = values.substring(values.length() - 3, values.length());
            String exPrefix = values.substring(0, values.length() - 3);
            result = String.valueOf(exPrefix) + "," + String.valueOf(prefix);
        } else if (values.length() > 6) {
            String s1 = values.substring(values.length() - 3, values.length());
            String s2 = values.substring(values.length() - 6, values.length() - 3);
            String s3 = values.substring(0, values.length() - 6);
            result = String.valueOf(s3) + "," + String.valueOf(s2) + "," + String.valueOf(s1);
        } else {
            result = values;
        }
        return result;
    }

}
