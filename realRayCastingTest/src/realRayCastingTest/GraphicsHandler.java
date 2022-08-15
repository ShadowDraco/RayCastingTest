package realRayCastingTest;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class GraphicsHandler extends JPanel {

	private static final long serialVersionUID = 1L;

	GameManager game;
	Window window;
	Level level;

	public GraphicsHandler(GameManager game) {
		this.window = game.window;
	}

	public void drawPlayer(Graphics2D g2, AffineTransform old) {
		g2.setColor(Color.DARK_GRAY);
		// g2.fill(level.player.rect); <-- show bounding box
		g2.fill(level.player.ellipse);
		g2.setTransform(old);
	}

	public void drawWalls(Graphics2D g2, AffineTransform old) {
		for (int i = 0; i < level.walls.length; i++) {
			Wall wall = level.walls[i];

			for (int j = 0; j < wall.lines.length; j++) {

				g2.setColor(wall.colors[j]);
				if (wall.intersected[j] == true) {
					g2.setColor(Color.LIGHT_GRAY);
					g2.draw(wall.lines[j]);
					drawIntersectionRays(g2, old, j, wall);
				}
			}
			// g2.draw(wall.lines[j]);
		}
	}

	@SuppressWarnings("unused")
	public void drawRays(Graphics2D g2, AffineTransform old) {

		AlphaComposite overlap = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		AlphaComposite original = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);

		g2.setColor(Color.lightGray);
		for (Ray ray : level.rays) {
			if (ray.foundIntersection) {
				g2.setComposite(overlap);
				g2.draw(ray.line);
				g2.setComposite(original);
			}
		}
		g2.setTransform(old);
	}
	
	public void drawIntersectionRays(Graphics2D g2, AffineTransform old, int j, Wall wall) {
		AlphaComposite overlap = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		AlphaComposite original = AlphaComposite.getInstance(AlphaComposite.SRC_IN);
		g2.setComposite(overlap);
		if (j == 0) {
			for (Ray ray : wall.topIntersections) {
				g2.draw(ray.line);
			}
		}
		if (j == 1) {
			for (Ray ray : wall.leftIntersections) {
				g2.draw(ray.line);
			}
		}
		if (j == 3) {
			for (Ray ray : wall.rightIntersections) {
				g2.draw(ray.line);
			}
		}
		if (j == 3) {
			for (Ray ray : wall.bottomIntersections) {
				g2.draw(ray.line);
			}
		}
		g2.setComposite(original);
	}

	public void paintComponent(Graphics g) {
		if (level != null) {
			super.paintComponent(g);
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHints(rh);
			// The reset point for all transforms
			AffineTransform old = new AffineTransform(g2.getTransform());

			g2.fillRect(-1, -1, level.window.getWidth(), level.window.getHeight());
			//drawRays(g2, old);
			drawWalls(g2, old);
			drawPlayer(g2, old);
		}

	}
}
