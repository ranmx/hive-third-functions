package cc.shanruifeng.functions.eId;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        String replaceValue = value.replaceAll("[\\p{Punct}\\s]", "").trim();
        Pattern p = Pattern.compile("1[34578][0-9]{9}"); // 验证手机号
        Matcher m = p.matcher(replaceValue);
        if (m.find()){
            return m.group(0);
        }else{
            return null;
        }
    }
}
