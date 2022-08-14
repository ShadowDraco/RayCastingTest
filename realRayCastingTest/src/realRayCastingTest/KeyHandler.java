package realRayCastingTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	Level level;
	
	/* If things don't work when switching levels try making the keyHandler have just the player as a paramer */
	public KeyHandler(Level level) {
		this.level = level;
	}
	
	
	public void keyTyped(KeyEvent e) {	}


	public void keyPressed(KeyEvent e) {
		level.player.setMovement(e);
		
		if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_E) {
			level.startRotate(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		level.player.stopMovement(e);
		if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_E) {
			level.stopRotate();
		}
	}

}