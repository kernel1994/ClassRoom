package team.dx.classroom.service;

import team.dx.classroom.domain.Announcement;

import java.util.List;

/**
 * Created by kernel on 2016/7/16.
 */
public interface AnnouncementService {
    List<Announcement> getAllAnnouncements();

    List<Announcement> getAnnouncement(String announcementID);

    void addAnnouncement(Announcement announcement);

    void deleteAnnouncement(String announcementID);

    void updateAnnouncement(Announcement announcement);
}
