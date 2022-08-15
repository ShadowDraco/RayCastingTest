package realRayCastingTest;

import java.awt.event.KeyEvent;

public class Level {

	Window window;
	Player player;
	KeyHandler playerKeys;

	double angle, x, y;
	boolean rotatingLeft, rotatingRight;

	Ray[] rays = new Ray[360];
	Wall[] walls = new Wall[5];

	CollisionHandler collisions;
	Camera camera;

	public Level(GraphicsHandler graphics) {
		
		this.window = graphics.window;
		
		player = new Player(100, 100);
		playerKeys = new KeyHandler(this);

		collisions = new CollisionHandler(this);
		this.camera = new Camera(player, window);
		
		walls[0] = new Wall(200, 100, 100, 90, 0, this);
		walls[1] = new Wall(100, 300, 100, 90, 45, this);
		walls[2] = new Wall(400, 500, 100, 90, 10, this);
		walls[3] = new Wall(600, 300, 100, 90, 130, this);
		walls[4] = new Wall(50, 50, 100, 90, 45, this);
	}

	public void startRotate(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			rotatingLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_E) {
			rotatingRight = true;
		}
	}

	public void stopRotate() {
		rotatingRight = false;
		rotatingLeft = false;
	}

	public void rotate() {

		if (rotatingLeft) {
			angle += 0.3;
		} else if (rotatingRight) {
			angle -= 0.3;
		}

		if (angle > 360 || angle < -360) {
			angle = 0;
		}

	}

	public void castRays() {
		for (int i = 0; i < rays.length; i++) {
			rays[i] = new Ray(i, player, window);
		}
		
		for (Ray ray : rays) {
			for (Wall wall : walls) {
				ray.findIntersection(wall);
			}
		}
	}
	
	public void updateWalls() {
		for (Wall wall : walls) {
			wall.update();
		}
	}

	public void unHighlightWalls() {
		for (Wall wall : walls) {
			wall.unHighlight();
		}
	}

	public void update() {
		unHighlightWalls();
		x = player.startX + player.worldX;
		y = player.startY + player.worldY;
		rotate();
		player.update();
		updateWalls();
		castRays();
		collisions.check(this);
		camera.update();
		
	}
}
