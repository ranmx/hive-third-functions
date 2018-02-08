package cc.shanruifeng.functions.card;

import cc.shanruifeng.functions.utils.CardUtils;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;

/**
 * @author ruifeng.shan
 * @date 2016-07-25
 * @time 20:15
 */
@Description(name = "is_valid_id_card"
        , value = "_FUNC_(string) - whether given china id card is valid or not."
        , extended = "Example:\n > select _FUNC_(string) from src;")
public class UDFChinaIdCardValid extends UDF {
    private BooleanWritable result = new BooleanWritable();

    public UDFChinaIdCardValid() {
    }

    public BooleanWritable evaluate(Text idCard) {
        if (idCard == null) {
            result.set(false);
            return result;
        }
        result.set(CardUtils.isValidIdCard(idCard.toString()));
        return result;
    }
}
