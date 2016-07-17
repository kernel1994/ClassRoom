package team.dx.classroom.service.impl;

import team.dx.classroom.dao.AnnouncementDAO;
import team.dx.classroom.dao.CourseDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.Announcement;
import team.dx.classroom.domain.Course;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.AnnouncementService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kernel on 2016/7/16.
 */
public class AnnouncementServiceImpl implements AnnouncementService {

    private AnnouncementDAO aDAO = ObjectFactory.getInstance().createObject(AnnouncementDAO.class);
    private UserDAO uDAO = ObjectFactory.getInstance().createObject(UserDAO.class);
    private CourseDAO cDAO = ObjectFactory.getInstance().createObject(CourseDAO.class);

//    private CourseService cService = ObjectFactory.getInstance().createObject(CourseService.class);

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

    @Override
    public List<Announcement> getNewAnnouncements(int amount) {
        List<Announcement> allAnnouncements = getAllAnnouncements();

        if (amount <= 0) {
            return null;
        }

        if (amount > allAnnouncements.size()) {
            amount = allAnnouncements.size() - 1;
        }

        return allAnnouncements.subList(0, amount);
    }

    @Override
    public List<Announcement> getStudentCoursesAnnouncementsWithLimited(String studentID, int limited) {
        List<Announcement> allAnnouncements = getAllAnnouncements();
        List<Announcement> studentCoursesAnnouncements = new ArrayList<>();

        List<Course> allCourses = cDAO.getStudentCourses(studentID);
        for (Course c : allCourses) {
            for (Announcement a : allAnnouncements) {
                if (a.getCourse_id().equals(c.getId())) {
                    studentCoursesAnnouncements.add(a);
                }
            }
        }

        if (studentCoursesAnnouncements.size() == 0) {
            return null;
        }

        if (limited == 0 || limited > studentCoursesAnnouncements.size()) {
            limited = studentCoursesAnnouncements.size();
        }

        return studentCoursesAnnouncements.subList(0, limited);
    }

    private List<Announcement> getAnnouncementComm(String sql, Object ... args) {
        List<Announcement> announcements = aDAO.getAnnouncements(sql, args);

        for (Announcement a : announcements) {
            User u = uDAO.getUser("SELECT * FROM user WHERE id = ?", a.getUser_id());
            String courseName = cDAO.getCourse("SELECT * FROM course WHERE id = ?", a.getCourse_id()).getName();

            a.setUser(u);
            a.setCourseName(courseName);
        }

        // 以时间倒序排序 List
//        Collections.sort(announcements, (o1, o2) -> o1.getTime().before(o2.getTime()) ? 1 : -1);

        Collections.sort(announcements, new DateComparator());

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

    @Override
    public List<Announcement> getCourseAnnouncements(String courseID) {
        List<Announcement> announcements = aDAO.getCourseAnnouncements(courseID);

        Collections.sort(announcements, new DateComparator());

        return announcements;
    }
}

class DateComparator implements Comparator<Announcement> {

    @Override
    public int compare(Announcement o1, Announcement o2) {
        if (o1.getTime().before(o2.getTime())) {
            return 1;
        } else {
            return -1;
        }
    }
}
