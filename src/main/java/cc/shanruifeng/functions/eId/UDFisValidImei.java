package cc.shanruifeng.functions.eId;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by ranmx on 2018/2/28.
 */

@Description(name = "is_valid_imei"
  , value = "_FUNC_(string) - whether given imei is valid or not."
  , extended = "Example:\n > select _FUNC_(string) from src;")
public class UDFisValidImei extends UDF {
    private BooleanWritable result = new BooleanWritable();

    public UDFisValidImei() {
    }

    public BooleanWritable evaluate(Text imei) {
        if (imei.getLength() != 15) {
            result.set(false);
            return result;
        }
        String imeiStr = imei.toString();

        if (!imeiStr.substring(14,15).equals(UDFGetValidImei.getimei15(imei.toString().substring(0, 14)))){
            result.set(false);
            return result;
        }
        result.set(true);
        return result;
    }
}
