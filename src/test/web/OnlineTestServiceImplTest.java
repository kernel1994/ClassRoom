package test.web;

import org.junit.Test;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.OnlineTestService;

/**
 * Created by kernel on 2016/8/13.
 */
public class OnlineTestServiceImplTest {

    OnlineTestService ots = ObjectFactory.getInstance().createObject(OnlineTestService.class);

    @Test
    public void testCreateCustomOnlineTest() throws Exception {
        ots.createCustomOnlineTest("6ae04e45-8dc2-42c0-bdad-2fa5985e036e",
                "G:\\Code_Java_IDEA\\ClassRoom\\out\\artifacts\\ClassRoom_war_exploded\\resource\\task\\homework\\",
                "G:\\Code_Java_IDEA\\ClassRoom\\out\\artifacts\\ClassRoom_war_exploded\\resource\\task\\homework_standard.xml",
                "0", "0", "", "0", "");
//        JDBCUtils2.commitTransaction();
    }
}