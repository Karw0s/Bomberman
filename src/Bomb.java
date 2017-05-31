import java.awt.Image;

/**
 * Klasa odpowiedzialna za obsluge bomby
 */
public class Bomb {

	private Sprajt sprite = Sprajt.bomba1;
	private int zapalnik;
	private int czas_ognia;
	private boolean ticking;
	private boolean burning;
	private boolean bombset;
	private int x;
	private int y;

	public Bomb() {
		zapalnik = 180;
		czas_ognia = 120;
		ticking = false;
		burning = false;
		bombset = true;
	}

	/**
	 * Metoda stawia bombe tam gdzie stoi gracz. Blokuje stawianie nastepnej
	 * bomby. Zmienia parametr ticking na true.
	 * 
	 * @param player_x
	 *            wspolrzedna x gracza
	 * @param player_y
	 *            wspolrzedna y gracza
	 * @param plansza
	 *            plansza na ktorej jest rozgrywana gra
	 */
	public void setBomb(int player_x, int player_y, BoardTable plansza) {
		if (bombset) {
			bombset = false;
			x = (player_x - 25) / 50;
			y = (player_y - 25) / 50;

			if ((player_x + 25) % 50 >= 20 && (player_x + 25) % 50 <= 30) {
				x++;
			}
			if ((player_y + 25) % 50 >= 20 && (player_y + 25) % 50 <= 30) {
				y++;
			}
			if ((player_x + 25) % 50 > 30) {
				x++;
			}
			if ((player_y + 25) % 50 > 30) {
				y++;
			}
			plansza.mapa[y][x] = 3;
			ticking = true;
		}
	}

	/**
	 * Metoda zwraca czy bomba tyka.
	 * 
	 * @return wartosc ticking czy bomba tyka
	 */
	public boolean isTicking() {
		return ticking;
	}

	/**
	 * Metoda zwraca czy bomba pali.
	 * 
	 * @return wartosc burning czy bomba wybucha i pali
	 */
	public boolean isBurning() {
		return burning;
	}

	/**
	 * Metoda zmiejsza czas bomby do wybuchu.
	 * 
	 * @param plansza
	 *            plansza na ktorej rozgrywa sie gra
	 * 
	 * @see blow
	 */
	public void zmniejsz_zapalnik(BoardTable plansza) {
		zapalnik--;
		if (zapalnik == 0) {
			ticking = false;
			zapalnik = 180;
			blow(plansza);
		}
	}

	/**
	 * Metoda obslugujaca wybuch bomby. Zmienia kolor scian, ktore beda
	 * niszczone.
	 * 
	 * @param plansza
	 *            plansza na ktorej rozgrywa sie gra
	 */
	private void blow(BoardTable plansza) {
		burning = true;
		plansza.mapa[y][x] = 4;
		if (x > 0) { // lewo
			if (plansza.mapa[y][x - 1] != 1 && plansza.mapa[y][x - 1] != 2) {
				plansza.mapa[y][x - 1] = 5;
			}
			if (plansza.mapa[y][x - 1] == 2) {
				plansza.mapa[y][x - 1] = 9;
			}
		}
		if (x < 14) { // prawo
			if (plansza.mapa[y][x + 1] != 1 && plansza.mapa[y][x + 1] != 2) {
				plansza.mapa[y][x + 1] = 6;
			}
			if (plansza.mapa[y][x + 1] == 2) {
				plansza.mapa[y][x + 1] = 9;
			}
		}
		if (y > 0) { // gora

			if (plansza.mapa[y - 1][x] != 1 && plansza.mapa[y - 1][x] != 2) {
				plansza.mapa[y - 1][x] = 7;
			}
			if (plansza.mapa[y - 1][x] == 2) {
				plansza.mapa[y - 1][x] = 9;
			}
		}
		if (y < 10) { // dol
			if (plansza.mapa[y + 1][x] != 1 && plansza.mapa[y + 1][x] != 2) {
				plansza.mapa[y + 1][x] = 8;
			}
			if (plansza.mapa[y + 1][x] == 2) {
				plansza.mapa[y + 1][x] = 9;
			}
		}
	}

	/**
	 * Metoda czyszczaca ogien z planszy i usuwa zmiszczone sciany.
	 * 
	 * @param plansza
	 *            plansza na ktorej rozgrywa sie gra
	 */
	public void clearFlame(BoardTable plansza) {
		czas_ognia--;
		if (czas_ognia == 0) {
			burning = false;
			czas_ognia = 120;
			plansza.mapa[y][x] = 0;
			if (x > 0) { // lewo
				if (plansza.mapa[y][x - 1] != 1) {
					plansza.mapa[y][x - 1] = 0;
				}
			}
			if (x < 14) { // prawo
				if (plansza.mapa[y][x + 1] != 1) {
					plansza.mapa[y][x + 1] = 0;
				}
			}
			if (y > 0) { // gora

				if (plansza.mapa[y - 1][x] != 1) {
					plansza.mapa[y - 1][x] = 0;
				}
			}
			if (y < 10) { // dol
				if (plansza.mapa[y + 1][x] != 1) {
					plansza.mapa[y + 1][x] = 0;
				}
			}
			bombset = true;
		}
	}

	/**
	 * @return wczytana tekstura bomby
	 */
	public Image getImage() {
		return sprite.getImage();
	}
}