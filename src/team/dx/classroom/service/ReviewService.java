package team.dx.classroom.service;

import team.dx.classroom.domain.Review;

import java.util.List;

/**
 * Created by kernel on 2015/12/2 14:25.
 */
public interface ReviewService {

    /** 获取一门课程的所有评论 */
    List<Review> getCourseReviews(String courseID);

    /** 在一门课里添加一条评论 */
    void addCourseReview(String courseID, Review review);
}
