package team.dx.classroom.dao;

public interface ThirdPartyCommonDAO {

	/**
	 * 该方法封装了 INSERT/ DELETE/ UPDATE 操作
	 * 
	 * @param sql SQL语句
	 * @param args 填充SQL语句的占位符的数据
	 * */
	public abstract void update(String sql, Object... args);

	/**
	 * 对表courseware_review 进行 INSERT/ DELETE/ <del>UPDATE</del> 操作
	 * @param type 字符串 INSERT 或  DELETE 或 <del>UPDATE</del> (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 参数顺序是 课程ID 评论ID<br />
	 * delete 参数只需要 评论ID
	 * */
	public abstract void updateCoursewareReview(String type, Object... args);

	/**
	 * 对表role_privilege 进行 INSERT/ DELETE/ UPDATE 操作。其中update 操作只能修改privilege_id
	 * @param type 字符串 INSERT 或  DELETE 或 UPDATE (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要两个参数，顺序是 权限ID 角色ID<br />
	 * delete 需要两个参数，顺序是 权限ID 角色ID<br />
	 * update 需要两个参数，顺序是 权限ID 角色ID
	 * */
	public abstract void updateRolePrivilege(String type, Object... args);

	/**
	 * 对表student_course 进行 INSERT/ DELETE/ UPDATE 操作。其中update 操作只能修改分数
	 * @param type 字符串 INSERT 或  DELETE 或 UPDATE (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 3 个参数，顺序是 学生ID 课程ID 分数<br />
	 * delete 需要 2 个参数，顺序是 学生ID 课程ID<br />
	 * update 需要 3 个参数，顺序是 分数 学生ID 课程ID
	 * */
	public abstract void updateStudentCourse(String type, Object... args);

	/**
	 * 对表task_review 进行 INSERT/ DELETE/ <del>UPDATE</del> 操作
	 * @param type 字符串 INSERT 或  DELETE 或 <del>UPDATE</del> (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 2 个参数，顺序是 作业ID 评论ID<br />
	 * delete 需要 2 个参数，顺序是 作业ID 评论ID
	 * */
	public abstract void updateTaskReview(String type, Object... args);

	/**
	 * 对表user_role 进行 INSERT/ DELETE/ <del>UPDATE</del> 操作
	 * @param type 字符串 INSERT 或  DELETE 或 <del>UPDATE</del> (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 2 个参数，顺序是 用户ID 角色ID<br />
	 * delete 需要 2 个参数，顺序是 用户ID 角色ID
	 * */
	public abstract void updateUserRole(String type, Object... args);

	/**
	 * 对表user_task 进行 INSERT/ DELETE/ UPDATE 操作
	 * @param type 字符串 INSERT 或  DELETE 或 UPDATE (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 3 个参数，顺序是 学生ID 作业ID 分数<br />
	 * delete 需要 2 个参数，顺序是 学生ID 作业ID<br />
	 * update 需要 3 个参数，顺序是 分数 学生ID 作业ID
	 * */
	public abstract void updateUserTask(String type, Object... args);

	/**
	 * 对表task_review 进行 INSERT/ DELETE/ <del>UPDATE</del> 操作
	 * @param type 字符串 INSERT 或  DELETE 或 <del>UPDATE</del> (不区分大小写) 中的一个
	 * @param args 参数。其中：<br />insert 需要 2 个参数，顺序是 课程ID 评论ID<br />
	 * delete 需要 2 个参数，顺序是 课程ID 评论ID
	 * */
	public abstract void updateCourseReview(String type, Object... args);

}