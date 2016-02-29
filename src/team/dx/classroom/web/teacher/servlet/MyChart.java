package team.dx.classroom.web.teacher.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;

import team.dx.classroom.dao.impl.CustomDaoImpl;
import team.dx.classroom.domain.User;
import team.dx.classroom.factory.ObjectFactory;
import team.dx.classroom.service.TaskService;
import team.dx.classroom.web.servlet.MethodInvokeServlet2;

public class MyChart extends MethodInvokeServlet2 {

	private static final long serialVersionUID = 1L;
	private TaskService ts = ObjectFactory.getInstance().createObject(
			TaskService.class);

	public void scoreChart(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			// 作业id
			String taskId = request.getParameter("taskid");
			// 课程id
			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			// 找出已经做了作业的所有学生
			// 提交已经批改的作业的所有学生
			List<User> noNeedMarkGradeStudents = ts
					.getNoNeedMarkGradeStudent(taskId);
			// 提交未批改的作业的所有学生
			List<User> needMarkGradeStudents = ts
					.getNeedMarkGradeStudent(taskId);

			// 找出还还没做了作业的所有学生
			List<User> notHaveFinishStudents = ts.getNotHaveFinishStudent(
					courseId, taskId);

			response.setContentType("image/jpeg");
			PieDataset dataset = createDataset(noNeedMarkGradeStudents.size(),
					needMarkGradeStudents.size(), notHaveFinishStudents.size());
			JFreeChart chart = createChart(dataset);
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart,
					400, 220);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message",
					"taskProgress异常!<br/>" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	private PieDataset createDataset(int p1, int p2, int p3) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("已批改", new Double(p1));// 已批改
		dataset.setValue("待批改", new Double(p2));
		dataset.setValue("未提交", new Double(p3));
		return dataset;
	}

	private JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart("作业完成情况", // chart title
				dataset, // data
				false, // no legend
				true, // tooltips
				false // no URL generation
				);

		// set a custom background for the chart
		chart.setBackgroundPaint(new GradientPaint(new Point(0, 0), new Color(
				20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

		// customise the title position and font
		TextTitle t = chart.getTitle();
		t.setHorizontalAlignment(HorizontalAlignment.LEFT);
		t.setPaint(new Color(240, 240, 240));
		t.setFont(new Font("宋体", Font.BOLD, 26));

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(null);
		plot.setInteriorGap(0.04);
		plot.setOutlineVisible(false);

		// use gradients and white borders for the section colours
		plot.setSectionPaint("已批改",
				createGradientPaint(new Color(200, 200, 255), Color.BLUE));
		plot.setSectionPaint("未提交",
				createGradientPaint(new Color(255, 200, 200), Color.RED));
		plot.setSectionPaint("待批改",
				createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
		plot.setBaseSectionOutlinePaint(Color.WHITE);
		plot.setSectionOutlinesVisible(true);
		plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));

		// customise the section label appearance
		plot.setLabelFont(new Font("宋体", Font.BOLD, 20));
		plot.setLabelLinkPaint(Color.WHITE);
		plot.setLabelLinkStroke(new BasicStroke(2.0f));
		plot.setLabelOutlineStroke(null);
		plot.setLabelPaint(Color.WHITE);
		plot.setLabelBackgroundPaint(null);

		return chart;

	}

	private static RadialGradientPaint createGradientPaint(Color c1, Color c2) {
		Point2D center = new Point2D.Float(0, 0);
		float radius = 200;
		float[] dist = { 0.0f, 1.0f };
		return new RadialGradientPaint(center, radius, dist, new Color[] { c1,
				c2 });
	}

	public void scoreChart2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomDaoImpl cDaoImpl = new CustomDaoImpl();

		try {
			// 作业id
			String taskId = request.getParameter("taskid");
			// 课程id
			String courseId = (String) request.getSession().getAttribute(
					"courseId");
			List<User> Students = ts.getAllStudents(courseId);
			int A = 0;
			int B = 0;
			int C = 0;
			int D = 0;
			String sql = "select score2 from user_task where user_id = ? and task_id = ?";
			for (User user : Students) {
				Integer score =(Integer) cDaoImpl.getScore(sql, user.getId(),
						taskId);
				if (score == null) {
					A++;
				} else {
					if (score == 0) {
						A++;
					} else if (score > 0 && score < 60) {
						B++;
					} else if (score >= 60 && score < 80) {
						C++;
					} else if (score >= 80 && score <= 100) {
						D++;
					}
				}
				
			}

			response.setContentType("image/jpeg");
			PieDataset dataset = createDataset(A,B,C,D);
			JFreeChart chart = createChart2(dataset);
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart,
					400, 220);

			// 的所有学生

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message",
					"taskProgress异常!<br/>" + e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	private static DefaultPieDataset createDataset(Integer A,Integer B,Integer C,Integer D) {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		localDefaultPieDataset.setValue("0", new Double(A));
		localDefaultPieDataset.setValue("0-60", new Double(B));
		localDefaultPieDataset.setValue("60-80", new Double(C));
		localDefaultPieDataset.setValue("80-", new Double(D));
		return localDefaultPieDataset;
	}

	private static JFreeChart createChart2(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart("score",
				paramPieDataset, true, true, false);
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setNoDataMessage("No data available");
		localPiePlot.setCircular(false);
		localPiePlot.setLabelGap(0.02D);
		localPiePlot.setExplodePercent("80-", 0.5D);
		localPiePlot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
		return localJFreeChart;
	}
}
