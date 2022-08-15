package realRayCastingTest;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Wall {

	Level level;
	double startX, startY, x, y, w, h, startAngle, angleX, angleY;
	Color[] colors = new Color[4];
	Line2D[] lines = new Line2D[4];
	
	ArrayList<Ray> topIntersections = new ArrayList<>();
	ArrayList<Ray> leftIntersections = new ArrayList<>();
	ArrayList<Ray> rightIntersections = new ArrayList<>();
	ArrayList<Ray> bottomIntersections = new ArrayList<>();
	boolean[] intersected = new boolean[4];
	Line2D topLine;
	Line2D leftLine;
	Line2D rightLine;
	Line2D bottomLine;

	public Wall(double x, double y, double w, double h, double startAngle, Level level) {
		
		this.level = level;
		this.startAngle = startAngle;
		this.startX = x;
		this.startY = y;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		angleX = Math.sin(Math.toRadians(startAngle));
		angleY = Math.cos(Math.toRadians(startAngle));

		topLine = new Line2D.Double(x, y, x + w, y);
		leftLine = new Line2D.Double(x, y, x, y + h);
		rightLine = new Line2D.Double(x + w, y, x + w, y + h);
		bottomLine = new Line2D.Double(x, (y + h), x + w, y + h);

		lines[0] = topLine;
		lines[1] = leftLine;
		lines[2] = rightLine;
		lines[3] = bottomLine;

		colors[0] = Color.black;
		colors[1] = Color.blue;
		colors[2] = Color.green;
		colors[3] = Color.red;
	}

	public void checkIntersections(Ray ray, Line2D line) {
		
		for (int i = 0; i < lines.length; i++) {
			if (lines[i] == line) {
				switch(i) {
					case 0:
						topIntersections.add(ray);
						break;
					case 1:
						leftIntersections.add(ray);
						break;
					case 2:
						rightIntersections.add(ray);
						break;
					case 3:
						bottomIntersections.add(ray);
						break;
				}
				
				highlightIntersection(line);
			}
		}
	}
	
	public void highlightIntersection(Line2D line) {
		
		if (topIntersections.size() > 30) {
			intersected[0] = true;
		}
		if (leftIntersections.size() > 30) {
			intersected[1] = true;
		}
		if (rightIntersections.size() > 30) {
			intersected[2] = true;
		}
		if (bottomIntersections.size() > 30) {
			intersected[3] = true;
		}
	}
	
	public void unHighlight() {
		topIntersections.clear();
		leftIntersections.clear();
		rightIntersections.clear();
		bottomIntersections.clear();
		for (int i = 0; i < intersected.length; i++) {
			intersected[i] = false;
		}
	}

	public void update() {
		
		angleX = Math.sin(Math.toRadians(startAngle));
		angleY = Math.cos(Math.toRadians(startAngle));
		x = startX - level.x;
		y = startY - level.y;
		
		if (startAngle > 0) {
			topLine.setLine(x, y,                                x + w, y + (h) * angleY);
			leftLine.setLine(x, y,                                 x - w * angleX, y + h * angleY);
			rightLine.setLine(x + w, y + h * angleY,               x + w / 2 * angleX, y + h * 2 * angleY);
			bottomLine.setLine(x - w * angleX, y + (h+1) * angleY, x + w / 2 * angleX, y + (h) * 2 * angleY);
		} else {
			topLine.setLine(x, y, x + w, y);
			leftLine.setLine(x, y, x, y + h);
			rightLine.setLine(x + w, y, x + w, y + h);
			bottomLine.setLine(x, (y + h), x + w, y + h);
		}

	}

}
