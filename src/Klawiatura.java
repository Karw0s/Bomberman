import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  klasa sluzy do obslugi klawiatury - zawiera mape klawiszy
 *  i metody wykrywaj¹ce nacisniccie i odpuszczenie konkretnego przycisku
 *
 */
public class Klawiatura implements KeyListener {
	

	private boolean[] znaki = new boolean[120];
	public boolean up, down, left, right, space, enter;
	
	/**
	 * metoda aktualizjuje pola klawiatury odpowiadajace za wcisniecie waznych dla gry przyciskow
	 */
	public void update() {
		up = znaki[KeyEvent.VK_UP] || znaki[KeyEvent.VK_W];
		down = znaki[KeyEvent.VK_DOWN] || znaki[KeyEvent.VK_S];
		left = znaki[KeyEvent.VK_LEFT] || znaki[KeyEvent.VK_A];
		right = znaki[KeyEvent.VK_RIGHT] || znaki[KeyEvent.VK_D];
		space = znaki[KeyEvent.VK_SPACE];
		enter = znaki[KeyEvent.VK_ENTER];
		//right=true;
	}

	
	/** (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * metoda wykrywaj¹ca wcisniecie przycisku
	 */
	public void keyPressed(KeyEvent e) {
		znaki[e.getKeyCode()] = true;	
	}
	
	/** (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 * metoda wykrywaj¹ca odpuszczenie przycisku
	 */
	public void keyReleased(KeyEvent e) {
		znaki[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e){
		
	}

}
