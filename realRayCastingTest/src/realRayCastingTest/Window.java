package realRayCastingTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Window extends JFrame implements ActionListener{

	private static final long serialVersionUID = 6776268747438575326L;
	
	GameManager game;
	Timer time = new Timer(5, this);
	int framesPassed = 0;
	
	GraphicsHandler graphics;
	
	public Window(GameManager game) {
		this.game = game;
		time.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setFocusable(true);

		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
			game.update();
	}

}
