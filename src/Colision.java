/**
 * Klasa odpowiada za sprawdzanie kolizji.
 */

public class Colision {

	/**
	 * Metoda sprawdzajaca czy wystepuje kolizja osoby z plansza na ktorej
	 * rozgrywa sie gra
	 * 
	 * @param x
	 *            wsporzedna x osoby
	 * @param y
	 *            wsporzedna y osoby
	 * @param d
	 *            kierunek w ktorym porusza sie osoba
	 * @param plansza
	 *            aktualna plansza na ktorej jest rozgrywana gra
	 * @return zwraca true jesli nastapi kolizja, w przeciwnym przypadku zwraca
	 *         false
	 */
	public static boolean isColision(int x, int y, int d, BoardTable plansza) {

		int i = (x - 25) / 50;
		int j = (y - 25) / 50;

		if (x < 25 || x > 14 * 50 + 25 || y < 25 || y > 10 * 50 + 25) {
			// System.out.println("sciana kolizja x:"+x+" y: "+y+" i: "+i+" j:
			// "+j);
			return true;
		}

		if (d == 0) {
			if (y != 525)
				j++;
			if ((x + 25) % 50 >= 20 && (x + 25) % 50 <= 30)
				return true;
		} else if (d == 1) {
			if (x != 725)
				i++;
			if ((y + 25) % 50 >= 20 && (y + 25) % 50 <= 30) {
				// System.out.println("kolizja x:"+x+" y: "+y+" i: "+i+" j:
				// "+j);
				return true;
			}

		} else if (d == 2) {
			if ((y + 25) % 50 >= 20 && (y + 25) % 50 <= 30) {
				// System.out.println("kolizja x:"+x+" y: "+y+" i: "+i+" j:
				// "+j);
				return true;
			}

		} else if (d == 3) {
			if ((x + 25) % 50 >= 20 && (x + 25) % 50 <= 30) {
				// System.out.println("kolizja x:"+x+" y: "+y+" i: "+i+" j:
				// "+j);
				return true;
			}
		}
		// System.out.println("kolizja x:"+x+" y: "+y+" i: "+i+" j: "+j);

		if (plansza.mapa[j][i] == 1 || plansza.mapa[j][i] == 2 || plansza.mapa[j][i] == 9)
			return true;
		else
			return false;

	}

	/**
	 * Metoda sprawdzajaca kolizje osoby z ogniem bomby. Jesli zostanie wykryta
	 * kolizja osoba umiera.
	 * 
	 * @param plansza
	 *            aktualna plansza na ktorej jest rozgrywana gra
	 * @param character
	 *            osoba dla ktorej beda sprawdzane kolizje
	 */

	public static void flameColision(BoardTable plansza, Character character) {
		int tmp_x = character.getX();
		int tmp_y = character.getY();
		int i = (tmp_x - 25) / 50;
		int j = (tmp_y - 25) / 50;
		int d = character.getDirection();

		if (d == 1 || d == 2) {
			if ((tmp_x + 25) % 50 < 20)
				tmp_x = tmp_x - (tmp_x + 25) % 50;
			if ((tmp_x + 25) % 50 > 30)
				tmp_x = tmp_x + 50 - (tmp_x + 25) % 50;
			i = (tmp_x - 25) / 50;

		} else if (d == 3 || d == 0) {
			if ((tmp_y + 25) % 50 < 20)
				tmp_y = tmp_y - (tmp_y + 25) % 50;
			if ((tmp_y + 25) % 50 > 30)
				tmp_y = tmp_y + 50 - (tmp_y + 25) % 50;
			j = (tmp_y - 25) / 50;
		}

		if (plansza.mapa[j][i] == 4 || plansza.mapa[j][i] == 5 || plansza.mapa[j][i] == 6 || plansza.mapa[j][i] == 7
				|| plansza.mapa[j][i] == 8) {
			character.alive = false;
		}
	}

	/**
	 * Metoda sprawdza kolizje gracza z potworem. Jesli zajdzie kolizja gracz
	 * umiera.
	 * 
	 * @param player
	 *            postac gracza
	 * @param mob
	 *            potwor
	 */

	public static void charactersColision(Character player, Character mob) {

		int p_x = player.getX();
		int p_y = player.getY();
		int m_x = mob.getX();
		int m_y = mob.getY();

		if (p_x + 30 > m_x && p_x < m_x + 30 && p_y + 35 > m_y && p_y < m_y + 30) {
			player.alive = false;
			// System.out.println("umiera x: "+p_x+" y: "+p_y+" mob x:"+m_x+" y:
			// "+m_y);
		}
	}
	
	/**
	 * Metoda sprawdza czy postac weszla do drzwi i tym samym zakonczyla gre
	 * @param player postac gracza
	 * @param drzwi  drzwi czyli obiekt konczacy gre
	 * @return czy postac weszla do drzwi
	 */
	public static boolean doorsenter(Character player, Character drzwi) {

		int p_x = player.getX();
		int p_y = player.getY();
		int m_x = drzwi.getX();
		int m_y = drzwi.getY();

		if (p_x + 30 > m_x && p_x < m_x + 30 && p_y + 35 > m_y && p_y < m_y + 30) {
			return true;
		}
		return false;
	}
	
}