package team.dx.classroom.service.impl;

import team.dx.classroom.dao.ReviewDAO;
import team.dx.classroom.dao.ThirdPartyCommonDAO;
import team.dx.classroom.dao.UserDAO;
import team.dx.classroom.domain.Review;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.ReviewService;

import java.util.List;

/**
 * Created by kernel on 2015/12/2 14:33.
 */
public class ReviewServiceImpl implements ReviewService {

    private ReviewDAO rDAO = ObjectFactory.getInstance().createObject(ReviewDAO.class);
    private UserDAO uDAO = ObjectFactory.getInstance().createObject(UserDAO.class);
    private ThirdPartyCommonDAO tpDAO = ObjectFactory.getInstance().createObject(ThirdPartyCommonDAO.class);

    @Override
    public List<Review> getCourseReviews(String courseID) {

        String sqlR = "select * from review as r " +
                "where r.id " +
                "in (select review_id " +
                "from course_review as cr " +
                "where cr.course_id = ?)";
        List<Review> reviews = rDAO.getReviews(sqlR, courseID);

        String sqlU = "select * from user where id = ?";
        for (Review r : reviews) {
            User u = uDAO.getUser(sqlU, r.getUser_id());
            r.setUser(u);
        }

        return reviews;
    }

    @Override
    public void addCourseReview(String courseID, Review review) {

        rDAO.addReview(review);
        tpDAO.updateCourseReview("insert", courseID, review.getId());
    }
}
