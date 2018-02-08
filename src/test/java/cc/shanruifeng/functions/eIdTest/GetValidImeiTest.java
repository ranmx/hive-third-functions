package cc.shanruifeng.functions.eIdTest;

/**
 * Created by ranmx on 2018/2/8.
 */


import cc.shanruifeng.functions.eId.UDFGetValidImei;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

public class GetValidImeiTest {
    @Test
    public void appTest() {
        UDFGetValidImei app = new UDFGetValidImei();

        Text testID1 = new Text("356926-03-417363-9");
        Text testID2 = new Text("353976054758077");
        Text testID3 = null;
        Text testID4 = new Text("11xx33445566");
        Text testID5 = new Text("353976054758078");
        Text testID6 = new Text("3539760547580778");
        Text testID7 = new Text("35397605475807778");
        Text testID8 = new Text("3539760547580");
        Text testID9 = new Text("35397605475807");

        assert (app.evaluate(testID1).equals(new Text("356926034173639")));
        assert (app.evaluate(testID2).equals(new Text("353976054758077")));
        assert (app.evaluate(testID3)==null);
        assert (app.evaluate(testID4)==null);
        assert (app.evaluate(testID5)==null);
        assert (app.evaluate(testID6).equals(new Text("353976054758077")));
        assert (app.evaluate(testID7)==null);
        assert (app.evaluate(testID8)==null);
        assert (app.evaluate(testID9).equals(new Text("353976054758077")));
    }
}
