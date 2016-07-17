package team.dx.classroom.service.impl;

import team.dx.classroom.dao.AnnouncementDAO;
import team.dx.classroom.dao.CourseDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.Announcement;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.AnnouncementService;

import java.util.List;

/**
 * Created by kernel on 2016/7/16.
 */
public class AnnouncementServiceImpl implements AnnouncementService {

    private AnnouncementDAO aDAO = ObjectFactory.getInstance().createObject(AnnouncementDAO.class);
    private UserDAO uDAO = ObjectFactory.getInstance().createObject(UserDAO.class);
    private CourseDAO cDAO = ObjectFactory.getInstance().createObject(CourseDAO.class);

    @Override
    public List<Announcement> getAllAnnouncements() {
        String sql = "SELECT * FROM announcement";

        return getAnnouncementComm(sql);
    }

    @Override
    public List<Announcement> getAnnouncement(String announcementID) {
        String sql = "SELECT * FROM announcement WHERE id = ?";

        return getAnnouncementComm(sql, announcementID);
    }

    private List<Announcement> getAnnouncementComm(String sql, Object ... args)
    {
        List<Announcement> announcements = aDAO.getAnnouncements(sql, args);

        for (Announcement a : announcements) {
            User u = uDAO.getUser("SELECT * FROM user WHERE id = ?", a.getUser_id());
            String courseName = cDAO.getCourse("SELECT * FROM course WHERE id = ?", a.getCourse_id()).getName();

            a.setUser(u);
            a.setCourseName(courseName);
        }

        return announcements;
    }

    @Override
    public void addAnnouncement(Announcement announcement) {

        aDAO.addAnnouncement(announcement);
    }

    @Override
    public void deleteAnnouncement(String announcementID) {

        aDAO.deleteAnnouncement(announcementID);
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {

        aDAO.updateAnnouncement(announcement);
    }
}
