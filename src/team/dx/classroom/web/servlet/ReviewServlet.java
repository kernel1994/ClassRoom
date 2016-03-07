package team.dx.classroom.web.servlet;

import team.dx.classroom.domain.Review;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.ReviewService;
import team.dx.classroom.utils.WebUtils;
import team.dx.classroom.web.student.servlet.StudentServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by kernel on 2015/12/3 12:59.
 */
public class ReviewServlet extends MethodInvokeServlet {

    private ReviewService rService = ObjectFactory.getInstance().createObject(ReviewService.class);

    @Override
    public int getSuffixLen() {

        return ".rev".length();
    }

    public void addCourseReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Review review = new Review();
        review.setId(WebUtils.getRandomUUID());
        review.setContent(request.getParameter("review_content"));
        review.setTime(new Date());
        String userID = StudentServlet.getUserId(request, response);
        User user = new User();
        user.setId(userID);
        review.setUser(user);

        String courseID = request.getParameter("courseID");

        rService.addCourseReview(courseID, review);

        response.sendRedirect("viewCourseIndex.stu?courseId=" + courseID);
    }
}
