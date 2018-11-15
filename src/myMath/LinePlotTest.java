package myMath;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Drawable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
/**
 * class for draw the polynom on a graph
 * we can see that the extrem points color is red and we see there value 
 * @author Shayke Shok and Itay Grinblat
 */
public class LinePlotTest extends JFrame {
	
	
	public LinePlotTest(double xL,double xR, Polynom p) throws DataException, RootException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 400);

		Polynom pNigzeret=(Polynom) p.derivative();
		DataTable data = new DataTable(Double.class, Double.class);
		for (double x = xL; x <= xR; x += 0.1) {
			double y = p.f(x);
			data.add(x, y);
		}
		double eps=0.01;
		DataTable extreamPoints = new DataTable(Double.class, Double.class);
		Set<Double> set=p.extremPoints(xL, xR, eps);
		double yPoint=0;
		for (Double double1 : set) {
			yPoint=p.f(double1);
			extreamPoints.add(double1,yPoint);	
		}
		
		
		XYPlot plot = new XYPlot(data,extreamPoints);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
		Color blueColor = new Color(0.0f, 0.3f, 1.0f);
		Color blackColor = new Color(0, 0, 1);
		Color color = new Color(0, 215, 0);
		plot.getPointRenderers(data).get(0).setColor(blueColor);
		plot.getPointRenderers(extreamPoints).get(0).setColor(color.red);
		plot.getLineRenderers(data).get(0).setColor(blueColor);
		plot.getPointRenderers(extreamPoints).get(0).setValueVisible(true);
			
	}
}
