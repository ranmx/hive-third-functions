package cc.shanruifeng.functions.eId;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by ranmx on 2018/2/28.
 */
@Description(name = "is_valid_mobile"
  , value = "_FUNC_(string) - whether given mobile is valid or not."
  , extended = "Example:\n > select _FUNC_(string) from src;")
public class UDFisValidMobile {
    private BooleanWritable result = new BooleanWritable();

    public UDFisValidMobile() {
    }

    public BooleanWritable evaluate(Text mobile) {
        String imeiStr = mobile.toString();
        Pattern p = Pattern.compile("^1[34578][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(imeiStr);
        result.set(m.find());
        return result;
    }

}
