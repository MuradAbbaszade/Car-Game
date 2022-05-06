import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		gameFrame.setResizable(false);
		gameFrame.setFocusable(false);
		gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gameFrame.setSize(500,600);
		
		Game game=new Game();
		game.setFocusable(true);
		game.requestFocus(true);
		game.addKeyListener(game);
		game.setFocusTraversalKeysEnabled(true);
		
		gameFrame.add(game);
		gameFrame.setVisible(true);
		
	}
}
