package team.dx.classroom.dao.impl;

import team.dx.classroom.dao.AnnouncementDAO;
import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.domain.Announcement;
import team.dx.classroom.domain.User;

import java.util.List;

/**
 * Created by kernel on 2016/7/16.
 */
public class AnnouncementDAOImpl extends BasicDAO<Announcement> implements AnnouncementDAO {
    @Override
    public List<Announcement> getAnnouncements(String condition, Object... args) {
        return getForList(condition, args);
    }

    @Override
    public void addAnnouncement(Announcement announcement) {
        String sql = "INSERT INTO announcement (id, user_id, course_id, time, content) VALUES (?, ?, ?, ?, ?)";

        String userID = null;
        User u = announcement.getUser();
        if (u == null) {
            userID = announcement.getUser_id();
        } else {
            userID = u.getId();
        }

        update(sql, announcement.getId(), userID, announcement.getCourse_id(), announcement.getTime(), announcement.getContent());
    }

    @Override
    public void deleteAnnouncement(String announcementID) {
        String sql = "DELETE FROM announcement WHERE id = ?";

        update(sql, announcementID);
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {

    }
}
