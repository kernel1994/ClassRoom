package team.dx.classroom.service;

import team.dx.classroom.domain.Announcement;

import java.util.List;

/**
 * Created by kernel on 2016/7/16.
 */
public interface AnnouncementService {
    List<Announcement> getAllAnnouncements();

    List<Announcement> getAnnouncement(String announcementID);

    /**
     * 返回最新的几个公告
     * @param amount 需要的公告个数
     * @return 以时间倒序的公告列表
     */
    List<Announcement> getNewAnnouncements(int amount);

    /**
     * 获取某个学生的所选的课程的公告，可选返回个数
     * @param studentID 学生ID
     * @param limited 可选通知个数，0表示所有公告，正整数表示对应小于总公告范围的数量
     * @return 以时间倒序的公告列表
     */
    List<Announcement> getStudentCoursesAnnouncementsWithLimited(String studentID, int limited);

    void addAnnouncement(Announcement announcement);

    void deleteAnnouncement(String announcementID);

    void updateAnnouncement(Announcement announcement);

    List<Announcement> getCourseAnnouncements(String courseID);
}
