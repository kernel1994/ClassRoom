package test.junit;

import org.junit.Test;
import team.dx.classroom.dao.HomeWorkDAO;
import team.dx.classroom.dao.impl.HomeWorkDAOImpl;
import team.dx.classroom.domain.HomeWork;

public class HomeWorkDAOImplTest {

    HomeWorkDAO h = new HomeWorkDAOImpl();

    @Test
    public void testGet() {

        /* 注意添加文件协议file:/// */
        HomeWork hw = h.get("file:///G:\\Code_Java_IDEA\\out\\artifacts\\ClassRoom_war_exploded\\resource\\task\\homework\\发_2.xml");
        System.out.println(hw);
    }
}
