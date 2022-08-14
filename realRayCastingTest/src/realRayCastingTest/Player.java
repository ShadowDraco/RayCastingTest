package realRayCastingTest;

import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Player  {
	
	double startX, startY;
	double worldX, worldY, x, y, cx, cy, velX, velY;
	double speed = 1.2;
	boolean movingLeft, movingRight, movingUp, movingDown, moving;
	Rectangle2D rect;
	Ellipse2D ellipse;
	
	public Player(double x, double y) {
		startX = x;
		startY = y;
		this.x = x;
		this.y = y;
		rect = new Rectangle2D.Double(x, y, 20, 20);
		ellipse = new Ellipse2D.Double();
	}
	
	public void setMovement(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			movingUp = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			movingDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			movingLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			movingRight = true;
		}
	}
	public void stopMovement(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			movingUp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			movingDown = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			movingLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			movingRight = false;
		}
	}
	
	public void addVelocity() {
		
	}
	
	public void move() {
		if (movingLeft) {
			x -= speed;
			worldX -= speed;
		}
		if (movingRight) {
			x += speed;
			worldX += speed;
		}
		if (movingUp) {
			y -= speed;
			worldY -= speed;
		}
		if (movingDown) {
			y += speed;
			worldY += speed;
		}
		
	}
	
	public void update() {
		cx = rect.getCenterX();
		cy = rect.getCenterY();
		move();
		rect.setRect(x, y, 20, 20);
		ellipse.setFrame(rect);
		
	}

}