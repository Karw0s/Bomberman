import java.awt.Image;

/**
 * Klasa reprezentuje postacie czyli potwory (Mob) i gracza (Player)
 * Jest nadklasa wobec dwoch wyzej wymienionych
 * Przechowuje glownie informacje o polozeniu i stanie postaci
 *
 */
public abstract class Character {

	protected int x;
	protected int y;
	protected int i;
	protected int d; // direction - kierunek postaci d = 1 prawo d= 2 lewo d=3 gora d=4 dol
	protected int counter; // counter - liczy kiedy zmiana sprite
	public boolean alive;

	/**
	 * @return wczytana tekstura postaci
	 */
	public abstract Image getImage();
	
	/**
	 * @return polozenie x postaci
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return polozenie x postaci
	 */
	public int getY() {
		return y;
	}
	/**
	 * @return aktualny kierunek poruszania sie postaci
	 */
	public int getDirection(){
		return d;
	}

	/**
	 * Metoda porusza postacia, najpierw sprawdzajac kolizje
	 * @param xp przesuniecie w osi x
	 * @param yp przesuniecie w osi y
	 * @param plansza ulozenie obiektow na planszy
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

