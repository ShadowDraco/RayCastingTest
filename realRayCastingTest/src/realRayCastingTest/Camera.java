package realRayCastingTest;

public class Camera {

	Player player;
	Window window;

	double x, y;
	double velX, velY, speedCap;
	int idleTimer;
	boolean playerIsIdle, centered;

	public Camera(Player player, Window window) {
		this.player = player;
		this.window = window;
		x = window.getWidth() / 2;
		y = window.getHeight() / 2;
		speedCap = player.speed;
	}

	public void move() {
		// adjust player to make the camera "move"
		player.x -= velX;
		player.y -= velY;
		player.worldX += velX;
		player.worldY += velY;
	}

	public void followPlayer() {
		centered = false;
		if (player.movingLeft) {
			if (velX > -speedCap) {
				velX -= 0.02;
			}
		}
		if (player.movingRight) {
			if (velX < speedCap) {
				velX += 0.02;
			}
		}
		if (player.movingUp) {
			if (velY > -speedCap) {
				velY -= 0.02;
			}
		}
		if (player.movingDown) {
			if (velY < speedCap) {
				velY += 0.02;
			}
		}

	}

	public void centerSelf() {
		if (x - player.cx < 2 & y - player.cy < 2) {
			centered = true;
		}
		
		if (!centered) {
			if (player.cx < x) {
				if (velX > -speedCap) {
					velX -= 0.02;
				}
			}
			if (player.cx > x) {
				if (velX < speedCap) {
					velX += 0.02;
				}
			}
			if (player.cy < y) {
				if (velY > -speedCap) {
					velY -= 0.02;
				}
			}
			if (player.cy > y) {
				if (velY < speedCap) {
					velY += 0.02;
				}
			}
		} else {
			velX = 0;
			velY = 0;
		}
	}

	public void checkPlayerIdle() {
		if (!player.movingLeft && !player.movingRight && !player.movingUp && !player.movingDown) {
			idleTimer += 1;
		} else {
			idleTimer = 0;
		}
		if (idleTimer > 60) {
			if (player.cy > window.getHeight()) {
				this.velY += speedCap;
			} 
			if (player.cy < 0) {
				this.velY -= speedCap;
			}
				
			playerIsIdle = true;
		} else {
			playerIsIdle = false;
		}
	}

	public void update() {
		checkPlayerIdle();
		move();
		if (!playerIsIdle) {
			followPlayer();
		} else {
			centerSelf();
		}
	}
}
