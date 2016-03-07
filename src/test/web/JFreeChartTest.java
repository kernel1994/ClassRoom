package test.web;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

public class JFreeChartTest extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static {
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                true));
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 600, 300);
	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Samsung", new Double(27.8));
        dataset.setValue("Others", new Double(55.3));
        dataset.setValue("Nokia", new Double(16.8));
        dataset.setValue("Apple", new Double(17.1));
        return dataset;
    }
	
	 private JFreeChart createChart(PieDataset dataset) {

	        JFreeChart chart = ChartFactory.createPieChart(
	            "Smart Phones Manufactured / Q3 2011",  // chart title
	            dataset,            // data
	            false,              // no legend
	            true,               // tooltips
	            false               // no URL generation
	        );

	        // set a custom background for the chart
	        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0), 
	                new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

	        // customise the title position and font
	        TextTitle t = chart.getTitle();
	        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
	        t.setPaint(new Color(240, 240, 240));
	        t.setFont(new Font("Arial", Font.BOLD, 26));

	        PiePlot plot = (PiePlot) chart.getPlot();
	        plot.setBackgroundPaint(null);
	        plot.setInteriorGap(0.04);
	        plot.setOutlineVisible(false);

	        // use gradients and white borders for the section colours
	        plot.setSectionPaint("Others", createGradientPaint(new Color(200, 200, 255), Color.BLUE));
	        plot.setSectionPaint("Samsung", createGradientPaint(new Color(255, 200, 200), Color.RED));
	        plot.setSectionPaint("Apple", createGradientPaint(new Color(200, 255, 200), Color.GREEN));
	        plot.setSectionPaint("Nokia", createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
	        plot.setBaseSectionOutlinePaint(Color.WHITE);
	        plot.setSectionOutlinesVisible(true);
	        plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));

	        // customise the section label appearance
	        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
	        plot.setLabelLinkPaint(Color.WHITE);
	        plot.setLabelLinkStroke(new BasicStroke(2.0f));
	        plot.setLabelOutlineStroke(null);
	        plot.setLabelPaint(Color.WHITE);
	        plot.setLabelBackgroundPaint(null);
	        
	        // add a subtitle giving the data source
	        TextTitle source = new TextTitle("Source: http://www.bbc.co.uk/news/business-15489523", 
	                new Font("Courier New", Font.PLAIN, 12));
	        source.setPaint(Color.WHITE);
	        source.setPosition(RectangleEdge.BOTTOM);
	        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	        chart.addSubtitle(source);
	        return chart;

	    }
	 
	 private static RadialGradientPaint createGradientPaint(Color c1, Color c2) {
	        Point2D center = new Point2D.Float(0, 0);
	        float radius = 200;
	        float[] dist = {0.0f, 1.0f};
	        return new RadialGradientPaint(center, radius, dist,
	                new Color[] {c1, c2});
	    }
}
