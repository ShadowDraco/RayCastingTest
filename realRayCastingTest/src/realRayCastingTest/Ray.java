package realRayCastingTest;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Ray {
	
	Player player;
	Line2D line;
	double startAngle, angleX, angleY;
	Point2D intersection;
	boolean foundIntersection;
	
	public Ray(double startAngle, Player player, Window window) {
		this.player = player;
		this.startAngle = startAngle;
		angleX = Math.sin(Math.toRadians(startAngle));
		angleY = Math.cos(Math.toRadians(startAngle));
		
		line = new Line2D.Double(player.cx, player.cy, (player.cx + window.getWidth())  * angleX, (player.cy + window.getHeight()) * angleY);
	}
	
	public void findIntersection(Wall wall) {
		for (Line2D wallLine : wall.lines) {
			if (line.intersectsLine(wallLine)) {
				setNewEndPoint(wallLine, wall);
			}
		}
	}
	
	public void setNewEndPoint(Line2D wallLine, Wall wall) {
		double x1 = wallLine.getX1();
		double x2 = wallLine.getX2();
		double y1 = wallLine.getY1();
		double y2 = wallLine.getY2();
		
		double x3 = line.getX1();
		double x4 = line.getX2();
		double y3 = line.getY1() + angleX;
		double y4 = line.getY2() + angleY;
		
		double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		
		if (den == 0) {
			return;
		}
		
		double t = ((x1-x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
		double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
		
		if (t > 0 && t < 1 && u > 0) {
			double ptx = x1 + t * (x2 - x1);
			double pty = y1 + t * (y2 - y1);
			
			line.setLine(player.cx, player.cy, ptx, pty);
			foundIntersection = true;
			wall.highlightIntersection(wallLine);
		}
		
	}
}
