package team.dx.classroom.dao;

import team.dx.classroom.domain.Announcement;

import java.util.List;

/**
 * Created by kernel on 2016/7/16.
 */
public interface AnnouncementDAO {

    List<Announcement> getAnnouncements(String condition, Object ... args);

    void addAnnouncement(Announcement announcement);

    void deleteAnnouncement(String announcementID);

    void updateAnnouncement(Announcement announcement);
}
