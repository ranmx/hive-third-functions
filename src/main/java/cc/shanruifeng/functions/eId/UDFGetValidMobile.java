package cc.shanruifeng.functions.eId;

import java.util.Arrays;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * Created by ranmx on 2018/2/8.
 */


@Description(name = "get_valid_mobile"
  , value = "_FUNC_(string) - If input is a valid mobile, reform it to 1**********, other wise return null"
  , extended = "Example:\n > select _FUNC_(string) from src;")

public class UDFGetValidMobile extends UDF {
    private Text result = new Text();

    public UDFGetValidMobile() {
    }

    public Text evaluate(Text mobileText) {
        if (mobileText == null) return null;
        String mobileString = mobileText.toString();
        String r = regulateMobile(mobileString);
        if (r == null) return null;
        result.set(r);
        return result;
    }

    private static String regulateMobile (String value) {
        String replaceValue = value.replaceAll("[+|-|*|#|&]", "").trim();
        int replaceLength = replaceValue.length();
        if (replaceValue.startsWith("1") && replaceValue.length() == 11) {
            if (replaceValue.contains("11111") || replaceValue.contains("00000")) {
                return null;
            }
            return replaceValue;
        }
        if (replaceLength > 11) {
            String subValue = replaceValue.substring(replaceLength - 11, replaceLength);
            if (subValue.startsWith("1")) {
                if (subValue.contains("11111") || subValue.contains("00000")) {
                    return null;
                }
                return subValue;
            } else {
                return null;
            }
        }
        return null;
    }
}
