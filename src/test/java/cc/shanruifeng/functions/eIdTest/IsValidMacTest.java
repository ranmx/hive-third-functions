package cc.shanruifeng.functions.eIdTest;

/**
 * Created by ranmx on 2018/2/8.
 */


import cc.shanruifeng.functions.eId.UDFisValidImei;
import cc.shanruifeng.functions.eId.UDFisValidMac;
import org.apache.hadoop.io.Text;
import org.junit.Test;

public class IsValidMacTest {
    @Test
    public void appTest() {
        UDFisValidMac app = new UDFisValidMac();

        Text testID1 = new Text("04:00:00:00:00:00");
        Text testID2 = new Text("05:00:00:00:00:00");
        Text testID3 = null;
        Text testID4 = new Text("10xx33445566");
        Text testID5 = new Text("acbbccddeeff");
        Text testID6 = new Text("10-22-33-44-55-66");
        Text testID7 = new Text("10.22.33.44.55.66");
        Text testID8 = new Text("10 22 33 44 55 66");
        Text testID9 = new Text("00:00:00:00:00:00");
        Text testID10 = new Text("12 22 33 44 55 66");

        assert (app.evaluate(testID1).get());
        assert (!app.evaluate(testID2).get());
        assert (!app.evaluate(testID4).get());
        assert (!app.evaluate(testID5).get());
        assert (!app.evaluate(testID6).get());
        assert (!app.evaluate(testID7).get());
        assert (!app.evaluate(testID8).get());
        assert (app.evaluate(testID9).get());
    }
}
