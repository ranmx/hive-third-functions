package cc.shanruifeng.functions.eIdTest;

/**
 * Created by ranmx on 2018/2/8.
 */

import cc.shanruifeng.functions.eId.UDFGetValidMac;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

public class GetValidMacTest {
    @Test
    public void appTest (){
        UDFGetValidMac app = new UDFGetValidMac();

        Text testID1 = new Text("102233445566");
        Text testID2 = new Text("10223344556677");
        Text testID3 = null;
        Text testID4 = new Text("10xx33445566");
        Text testID5 = new Text("acbbccddeeff");
        Text testID6 = new Text("10-22-33-44-55-66");
        Text testID7 = new Text("10.22.33.44.55.66");
        Text testID8 = new Text("10 22 33 44 55 66");
        Text testID9 = new Text("00:00:00:00:00:00");
        Text testID10 = new Text("12 22 33 44 55 66");

        assert(app.evaluate(testID1).equals(new Text("10:22:33:44:55:66")));
        assert(app.evaluate(testID2)==null);
        assert(app.evaluate(testID3)==null);
        assert(app.evaluate(testID4)==null);
        assert(app.evaluate(testID5).equals(new Text("ac:bb:cc:dd:ee:ff")));
        assert(app.evaluate(testID6).equals(new Text("10:22:33:44:55:66")));
        assert(app.evaluate(testID7).equals(new Text("10:22:33:44:55:66")));
        assert(app.evaluate(testID8).equals(new Text("10:22:33:44:55:66")));
        assert(app.evaluate(testID9).equals(new Text("00:00:00:00:00:00")));
        assert(app.evaluate(testID10)==null);
    }

}
