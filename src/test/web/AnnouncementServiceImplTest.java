package test.web;

import org.junit.Test;
import team.dx.classroom.domain.Announcement;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.AnnouncementService;
import team.dx.classroom.utils.JDBCUtils2;
import team.dx.classroom.utils.WebUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by kernel on 2016/7/16.
 */
public class AnnouncementServiceImplTest {

    AnnouncementService anns = ObjectFactory.getInstance().createObject(AnnouncementService.class);

    @Test
    public void testGetAnnouncement() throws Exception {
        List<Announcement> ass = anns.getAnnouncement("ccadc301-3be0-499b-933e-935eda5154e3");
        for (Announcement a : ass) {
            System.out.println(a);
        }
    }

    @Test
    public void testGetAnnouncements() throws Exception {
        List<Announcement> ass = anns.getAllAnnouncements();
        for (Announcement a : ass) {
            System.out.println(a);
        }
    }

    @Test
    public void testAddAnnouncement() throws Exception {
        Announcement a = new Announcement();
        a.setId(WebUtils.getRandomUUID());
        a.setCourse_id("d236b5cd-6533-45b2-925f-b96fae1ce6bf");
        a.setTime(new Date());
        a.setContent("明天不上课3");
//        User u = new User();
//        u.setId("da2e8098-9b26-4e47-8ddd-1e5dab5e6294");
//        a.setUser(u);
        a.setUser_id("da2e8098-9b26-4e47-8ddd-1e5dab5e6294");

        anns.addAnnouncement(a);
        JDBCUtils2.commitTransaction();
    }

    @Test
    public void testDelteAnnouncement() throws Exception {
        anns.deleteAnnouncement("ccadc301-3be0-499b-933e-935eda5154e3");
        JDBCUtils2.commitTransaction();
    }
}