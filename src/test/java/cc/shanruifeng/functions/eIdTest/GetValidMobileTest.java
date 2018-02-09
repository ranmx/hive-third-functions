package cc.shanruifeng.functions.eIdTest;

/**
 * Created by ranmx on 2018/2/8.
 */

import cc.shanruifeng.functions.eId.UDFGetValidMobile;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;


public class GetValidMobileTest {
    @Test
    public void appTest(){
        UDFGetValidMobile app = new UDFGetValidMobile();
        Text testID1 = new Text("13228059918");
        Text testID2 = new Text("008613228059918");
        Text testID3 = null;
        Text testID4 = new Text("+8613228059918");
        Text testID5 = new Text("008613218");
        Text testID6 = new Text("00861322805");
        Text testID7 = new Text("15921683335+00");
        Text testID8 = new Text("???8613501755773?");
        Text testID9 = new Text("8613501755773-");
        Text testID10 = new Text("135 0175 5773");



        assert(app.evaluate(testID1).equals(new Text("13228059918")));
        assert(app.evaluate(testID2).equals(new Text("13228059918")));
        assert(app.evaluate(testID3)==null);
        assert(app.evaluate(testID4).equals(new Text("13228059918")));
        assert(app.evaluate(testID5)==null);
        assert(app.evaluate(testID6)==null);
        assert(app.evaluate(testID7).equals(new Text("15921683335")));
        assert(app.evaluate(testID8).equals(new Text("13501755773")));
        assert(app.evaluate(testID9).equals(new Text("13501755773")));
        assert(app.evaluate(testID10).equals(new Text("13501755773")));
    }

}
