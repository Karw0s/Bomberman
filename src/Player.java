import java.awt.Image;

/**
 * Klasa dziedziczy z klasy Character i reprezentuje gracza
 */
public class Player extends Character {

	private Sprajt sprite[][] = { { Sprajt.p_przod1, Sprajt.p_przod2, Sprajt.p_przod3, Sprajt.p_przod4 }, // dol
			{ Sprajt.p_prawo1, Sprajt.p_prawo2, Sprajt.p_prawo3, Sprajt.p_prawo4 },
			{ Sprajt.p_lewo1, Sprajt.p_lewo2, Sprajt.p_lewo3, Sprajt.p_lewo4 },
			{ Sprajt.p_tyl1, Sprajt.p_tyl2, Sprajt.p_tyl3, Sprajt.p_tyl4 } // gora
	};

	/**
	 * ustawia poczatkowe domyslne wartosci ustaiwenia i stanu gracza 
	 */
	public Player() {

		i = 0;
		x = 25;
		y = 25;
		d = 0;
		counter = 0;
		alive = true;
	}

	/* (non-Javadoc)
	 * @see Character#getImage()
	 */
	public Image getImage() {
		return sprite[d][i].getImage();
	}

	/* (non-Javadoc)
	 * @see Character#getX()
	 */
	public int getX() {
		return x;
	}

	
	/* (non-Javadoc)
	 * @see Character#getY()
	 */
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see Character#getDirection()
	 */
	public int getDirection(){
		return d;
	}

	/* (non-Javadoc)
	 * @see Character#move(int, int, BoardTable)
	 */
	public void move(int xp, int yp, BoardTable plansza) {

		if ((xp == 0) && (yp > 0)) {
			d = 0;
			if ((x + 25) % 50 < 20)
				x = x - (x + 25) % 50;
			if ((x + 25) % 50 > 30)
				x = x + 50 - (x + 25) % 50;
		}
		if ((xp == 0) && (yp < 0)) {
			d = 3;
			if ((x + 25) % 50 < 20)
				x = x - (x + 25) % 50;
			if ((x + 25) % 50 > 30)
				x = x + 50 - (x + 25) % 50;
		}
		if ((xp > 0) && (yp == 0)) {
			d = 1;
			if ((y + 25) % 50 < 20)
				y = y - (y + 25) % 50;
			if ((y + 25) % 50 > 30)
				y = y + 50 - (y + 25) % 50;
		}
		if ((xp < 0) && (yp == 0)) {
			d = 2;
			if ((y + 25) % 50 < 20)
				y = y - (y + 25) % 50;
			if ((y + 25) % 50 > 30)
				y = y + 50 - (y + 25) % 50;
		}

		if (!((xp != 0) && (yp != 0))) {
			if (!Colision.isColision(x + xp, y + yp, d, plansza)) {
				x += xp;
				y += yp;
				counter++;
			}
		}

		getImage();

		if (counter % 10 == 0) {
			counter = 0;
			i++;
			i = i % 4;
		}
	}

}