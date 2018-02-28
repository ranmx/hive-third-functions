package cc.shanruifeng.functions.eId;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by ranmx on 2018/2/28.
 */
@Description(name = "is_valid_mac"
  , value = "_FUNC_(string) - whether given mac is valid or not."
  , extended = "Example:\n > select _FUNC_(string) from src;")
public class UDFisValidMac extends UDF {
    private BooleanWritable result = new BooleanWritable();

    public UDFisValidMac() {
    }

    public BooleanWritable evaluate(Text mac) {
        String macStr = mac.toString();
        Pattern p = Pattern.compile("^[0-9a-f][048c](:[0-9a-f]{2}){5}$"); // 验证mac号
        Matcher m = p.matcher(macStr);
        result.set(m.find());
        return result;
    }

}
