package cc.shanruifeng.functions.eId;

import java.util.Arrays;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.io.Text;

/**
 * Created by ranmx on 2018/2/8.
 */


@Description(name = "get_valid_imei"
  , value = "_FUNC_(string) - If input is a valid imei, reform it to 15 bit. other wise return null"
  , extended = "Example:\n > select _FUNC_(string) from src;")

public class UDFGetValidImei {
    private Text result = new Text();

    public UDFGetValidImei() {
    }

    public Text evaluate(Text imeiText) {
        if (imeiText == null) return null;
        String imeiString = imeiText.toString().replaceAll("\\p{Punct}", "");
        try {
            int dxml = imeiString.length();
            String imeiRes;
            if (dxml == 14) {
                imeiRes = imeiString + getimei15(imeiString);
            }
            else if (dxml == 16) {
                imeiRes = imeiString.substring(0, 14) + getimei15(imeiString.substring(0, 14));
            }
            else if (dxml == 15 && imeiString.substring(14, 15).equals(getimei15(imeiString.substring(0, 14)))) {
                imeiRes = imeiString;
            }
            else return null;
            result.set(imeiRes);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private static String getimei15(String imei){
        if (imei.length() == 14) {
            char[] imeiChar=imei.toCharArray();
            int resultInt=0;
            for (int i = 0; i < imeiChar.length; i++) {
                int a=Integer.parseInt(String.valueOf(imeiChar[i]));
                i++;
                final int temp=Integer.parseInt(String.valueOf(imeiChar[i]))*2;
                final int b=temp<10?temp:temp-9;
                resultInt+=a+b;
            }
            resultInt%=10;
            resultInt=resultInt==0?0:10-resultInt;
            return resultInt + "";
        }else{
            return null;
        }
    }
}
