package test.junit;

import org.junit.Test;
import team.dx.classroom.domain.Review;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.ReviewService;
import team.dx.classroom.utils.JDBCUtils2;
import team.dx.classroom.utils.WebUtils;

import java.util.Date;

/**
 * Created by kernel on 2015/12/3 22:11.
 */
public class ReviewServletTest {

    @Test
    public void add() {
        Review review = new Review();
        review.setId(WebUtils.getRandomUUID());
        review.setContent("review_content");
        review.setTime(new Date());
        String userID = "0";
        User user = new User();
        user.setId(userID);
        review.setUser(user);

        String courseID = "0";

        ReviewService rService = ObjectFactory.getInstance().createObject(ReviewService.class);
        rService.addCourseReview(courseID, review);
        JDBCUtils2.commitTransaction();
    }
}
