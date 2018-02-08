package cc.shanruifeng.functions.eId;

import cc.shanruifeng.functions.utils.CardUtils;
import java.util.Arrays;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by ranmx on 2018/2/8.
 */


@Description(name = "get_valid_mac"
  , value = "_FUNC_(string) - If input is a valid mac, reform it to ff:ff:ff:ff:ff:ff, other wise return null"
  , extended = "Example:\n > select _FUNC_(string) from src;")

public class UDFGetValidMac extends UDF{
    private Text result = new Text();

    public UDFGetValidMac() {
    }

    public Text evaluate(Text macText) {
        if (macText == null) return null;
        String macString = macText.toString();
        String r = reFormMac(matchMacFromString(macString));
        if (r == null) return null;
        result.set(r);
        return result;
    }

    private String matchMacFromString(String value){
        java.util.List<String> realMac =  Arrays.asList("0", "4", "8", "c");

        if(value==null){
            return null;
        } else if (value.replaceAll("[\\p{Punct}\\s:]", "").matches("[0-9a-fA-F]{12}")){
            String mac = value.replaceAll("[\\p{Punct}\\s]", "");
            if (realMac.contains(mac.substring(1, 2).toLowerCase())){
                return mac;
            }
            return null;
        }
        else{
            return null;
        }
    }

    private String reFormMac(String mac) {
        if (mac == null) return null;
        StringBuilder buffer = new StringBuilder();
        char[] macArray = mac.toCharArray();
        for (int i = 0; i < macArray.length; i++) {
            if (i > 0 && (i) % 2 == 0) {
                buffer.append(":");
            }
            buffer.append(macArray[i]);
        }
            return buffer.toString().toLowerCase();
    }
}
