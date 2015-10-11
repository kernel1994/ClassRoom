package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.ReviewDAO;
import team.dx.classroom.domain.Review;

public class ReviewDAOImpl extends BasicDAO<Review> implements ReviewDAO {

	@Override
	public List<Review> getReviews(String condition, Object... args) {

		return getForList(condition, args);
	}

	@Override
	public void updateReview(Review review) {

		String sql = "UPDATE review SET user_id = ?, content = ?, time = ? WHERE id = ?";

		update(sql, review.getUser().getId(), review.getContent(), review.getTime(), review.getId());
	}

	@Override
	public void deleteReview(String id) {

		String sql = "DELETE FROM review WHERE id = ?";

		update(sql, id);
	}

	@Override
	public void addReview(Review review) {

		String sql = "INSERT INTO review (id, user_id, content, time) VALUES (?, ?, ?, ?)";

		update(sql, review.getId(), review.getUser().getId(), review.getContent(), review.getTime());
	}

}
