package cc.shanruifeng.functions.cardTest;

/**
 * Created by ranmx on 2018/2/8.
 */

import cc.shanruifeng.functions.card.UDFChinaIdCardValid;

import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;


public class CardValidTest {
    @Test
    public void appTest(){
        UDFChinaIdCardValid app = new UDFChinaIdCardValid();

        Text testID1 = new Text("123456789012345678");
        Text testID2 = new Text("430524199001286477");
        Text testID3 = null;
        Text testID4 = new Text("51190219890610659X");
        Text testID5 = new Text("131002198709184441");
        Text testID6 = new Text("131002870918444");
        Text testID7 = new Text("4305241990012864");
        Text testID8 = new Text("helloworld");

        Assert.assertTrue(app.evaluate(testID2).get());
        Assert.assertTrue(app.evaluate(testID4).get());
        Assert.assertTrue(app.evaluate(testID5).get());
        Assert.assertTrue(app.evaluate(testID6).get());

        Assert.assertFalse(app.evaluate(testID1).get());
        Assert.assertFalse(app.evaluate(testID3).get());
        Assert.assertFalse(app.evaluate(testID7).get());
        Assert.assertFalse(app.evaluate(testID8).get());
    }

}
