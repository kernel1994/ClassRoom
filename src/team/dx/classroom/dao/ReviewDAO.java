package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Review;

public interface ReviewDAO {

	public List<Review> getReviews(String condition, Object ... args);
	
	public void updateReview(Review review);
	
	public void deleteReview(String id);
	
	public void addReview(Review review);
}
