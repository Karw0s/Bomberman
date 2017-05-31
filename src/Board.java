import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Klasa reprezentuje graficzna plansze gry, 
 * dziedziczy po klasie JPanel i implementuje ActionListener
 * Zawiera wszystkie postacie i jest glowna klasa projektu
 */
public class Board extends JPanel implements ActionListener {

	
	private static final long serialVersionUID = 1L;

	// private Image background, tile;
	// private Image brick, brick2, bomb;

	private Timer timer;
	private int delay = 8;
	private boolean pause;
	private boolean inGame;
	private boolean isWin;
	private BoardTable plansza;
	private Player player = new Player();
	private Mob drzwi;
	private Mob potwor1;
	private Mob potwor2;
	private Mob potwor3;
	private Klawiatura input = new Klawiatura();
	private Bomb bomba = new Bomb();
	// private BoardDrawer;

	/**
	 * Ustawia poczatkowe wartosci gry
	 */
	public Board() {
		plansza = new BoardTable();

		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		addKeyListener(input); // !!! wazne

		inGame = true;
		pause = false;
		isWin = false;

		
		
		drzwi = new Mob(2,plansza);
		potwor1 = new Mob(1, plansza);
		potwor2 = new Mob(1, plansza);
		potwor3 = new Mob(1, plansza);
		
		timer = new Timer(delay, this);
		timer.start();
		
		/*
		 * try { background = ImageIO.read(new File("res/background.png")); tile
		 * = ImageIO.read(new File("res/plytka.png")); //brick =
		 * ImageIO.read(new File("res/sciana.png")); //brick2 = ImageIO.read(new
		 * File("res/sciana2.png")); //bomb = ImageIO.read(new
		 * File("res/bomba.png")); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

	}

	/* (non-Javadoc)
	 * Wywoluje metode rysujaca wszystkie obiekty
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		doDrawing(g2d);
		// g2d.drawImage(background, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Rysuje wszelkie elementy graficzne w grze
	 * @param g2d obiekt rysujacy elementy 2D
	 */
	private void doDrawing(Graphics2D g2d) {

		g2d.drawImage(Sprajt.background.getImage(), 0, 0, this);

		g2d.drawImage(Sprajt.drzwi.getImage(), drzwi.getX(),drzwi.getY(), this);
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 15; j++) {
				if (plansza.mapa[i][j] == 1)
					g2d.drawImage(Sprajt.tile.getImage(), j * 50 + 25, i * 50 + 25, this);
				else if (plansza.mapa[i][j] == 2)
					g2d.drawImage(Sprajt.brick.getImage(), j * 50 + 25, i * 50 + 25, this);
				else if (plansza.mapa[i][j] == 3)
					g2d.drawImage(Sprajt.bomba1.getImage(), j * 50 + 25, i * 50 + 25, this);
				else if (plansza.mapa[i][j] == 4)
					g2d.drawImage(Sprajt.ogien_srodek.getImage(), j * 50 + 25, i * 50 + 25, this);
				else if (plansza.mapa[i][j] == 5)
					g2d.drawImage(Sprajt.ogien_lewo.getImage(), j * 50 + 25, i * 50 + 25, this);
				else if (plansza.mapa[i][j] == 6)
					g2d.drawImage(Sprajt.ogien_prawo.getImage(), j * 50 + 25, i * 50 + 25, this);
				else if (plansza.mapa[i][j] == 7)
					g2d.drawImage(Sprajt.ogien_gora.getImage(), j * 50 + 25, i * 50 + 25, this);
				else if (plansza.mapa[i][j] == 8)
					g2d.drawImage(Sprajt.ogien_dol.getImage(), j * 50 + 25, i * 50 + 25, this);
				else if (plansza.mapa[i][j] == 9)
					g2d.drawImage(Sprajt.brick2.getImage(), j * 50 + 25, i * 50 + 25, this);
			}
		}

		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
		if (potwor1.alive)
			g2d.drawImage(potwor1.getImage(), potwor1.getX(), potwor1.getY(), this);
		if (potwor2.alive)
			g2d.drawImage(potwor2.getImage(), potwor2.getX(), potwor2.getY(), this);
		if (potwor3.alive)
			g2d.drawImage(potwor3.getImage(), potwor3.getX(), potwor3.getY(), this);

		if (player.alive == false) {
			g2d.drawImage(Sprajt.game_over.getImage(), 100, 150, this);
			inGame = false;
		}
		if (pause && inGame) {
			g2d.drawImage(Sprajt.pauza.getImage(), 100, 150, this);
		}
		if (!inGame && isWin)
			g2d.drawImage(Sprajt.win.getImage(), 100, 150, this);
	}

	/**
	 * Pozwala zapauzowac gre
	 */
	public void pauseGame() {
		if (!pause) {
			pause = true;
			repaint();
		} else {
			pause = false;
		}
	}

	/* (non-Javadoc)
	 * Glowna metoda gry wywolywana przez Timer obslugujaca 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (!pause) {
			if (inGame) { // aktualizacje i sprawdzanie kolizji

				input.update();

				if (input.left)
					player.move(-1, 0, plansza);
				if (input.right)
					player.move(1, 0, plansza);
				if (input.up)
					player.move(0, -1, plansza);
				if (input.down)
					player.move(0, 1, plansza);
				if (input.space) {
					if (!bomba.isTicking())
						bomba.setBomb(player.getX(), player.getY(), plansza);
				}

				if (bomba.isTicking()) {
					bomba.zmniejsz_zapalnik(plansza);
				}
				if (bomba.isBurning()) {
					bomba.clearFlame(plansza);
					Colision.flameColision(plansza, player);
					Colision.flameColision(plansza, potwor1);
					Colision.flameColision(plansza, potwor2);
					Colision.flameColision(plansza, potwor3);
				}

				potwor1.move(plansza);

				potwor2.move(plansza);

				potwor3.move(plansza);

				if (potwor1.alive)
					Colision.charactersColision(player, potwor1);
				if (potwor2.alive)
					Colision.charactersColision(player, potwor2);
				if (potwor3.alive)
					Colision.charactersColision(player, potwor3);

				if (!potwor1.alive && !potwor2.alive && !potwor3.alive && Colision.doorsenter(player, drzwi)) {
					inGame = false;
					isWin = true;
				}

			}

			repaint();

		}
	}
}