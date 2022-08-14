package realRayCastingTest;

import java.util.ArrayList;


public class GameManager {
	
	GraphicsHandler graphics;
	Window window;
	int level = 0;
	
	ArrayList<Level> levels = new ArrayList<Level>();
	
	public GameManager() {
		// game window, and give it the keyListener
		window = new Window(this);
		graphics = new GraphicsHandler(this);


		createNewLevel();
		window.add(graphics);
		window.addKeyListener(levels.get(level).playerKeys);
		window.setVisible(true);
		
	}
	
	public void createNewLevel() {
		levels.add(new Level(graphics));
		graphics.level = levels.get(levels.size() - 1);
	}

	public void update() {
		levels.get(0).update();
		graphics.repaint();
	}
}

