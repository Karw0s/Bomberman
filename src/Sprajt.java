import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Klasa zawiera sprajty czyli gotowe obrazki do gry jako pola statyczne
 *
 */
public class Sprajt {

	
	private Image tekstura;
	
	public static Sprajt background = new Sprajt("res/background.png");
	public static Sprajt tile = new Sprajt("res/plytka.png");
	public static Sprajt drzwi = new Sprajt("res/drzwi.png");
	
	public static Sprajt mob1_1 = new Sprajt("res/mob1.png");
	public static Sprajt mob1_2 = new Sprajt("res/mob2.png");
	public static Sprajt mob1_3 = new Sprajt("res/mob3.png");
	public static Sprajt mob2_1 = new Sprajt("res/mob7.png");
	public static Sprajt mob2_2 = new Sprajt("res/mob8.png");
	public static Sprajt mob2_3 = new Sprajt("res/mob9.png");
	
	public static Sprajt brick = new Sprajt("res/sciana.png");
	public static Sprajt brick2 = new Sprajt("res/sciana2.png");
	
	public static Sprajt p_przod1 = new Sprajt("res/przod1.png");
	public static Sprajt p_przod2 = new Sprajt("res/przod2.png");
	public static Sprajt p_przod3 = new Sprajt("res/przod1.png");
	public static Sprajt p_przod4 = new Sprajt("res/przod3.png");
	
	public static Sprajt p_lewo1 = new Sprajt("res/lewo1.png");
	public static Sprajt p_lewo2 = new Sprajt("res/lewo2.png");
	public static Sprajt p_lewo3 = new Sprajt("res/lewo1.png");
	public static Sprajt p_lewo4 = new Sprajt("res/lewo3.png");
	
	public static Sprajt p_prawo1 = new Sprajt("res/prawo1.png");
	public static Sprajt p_prawo2 = new Sprajt("res/prawo2.png");
	public static Sprajt p_prawo3 = new Sprajt("res/prawo1.png");
	public static Sprajt p_prawo4 = new Sprajt("res/prawo3.png");
	
	public static Sprajt p_tyl1 = new Sprajt("res/tyl1.png");
	public static Sprajt p_tyl2 = new Sprajt("res/tyl2.png");
	public static Sprajt p_tyl3 = new Sprajt("res/tyl1.png");
	public static Sprajt p_tyl4 = new Sprajt("res/tyl3.png");
	
	public static Sprajt bomba1 = new Sprajt("res/bomba.png");
	public static Sprajt ogien_srodek = new Sprajt("res/ogien_srodek.png");
	public static Sprajt ogien_gora = new Sprajt("res/ogien_gora.png");
	public static Sprajt ogien_dol = new Sprajt("res/ogien_dol.png");
	public static Sprajt ogien_lewo = new Sprajt("res/ogien_lewy.png");
	public static Sprajt ogien_prawo = new Sprajt("res/ogien_prawo.png");
	
	public static Sprajt pauza = new Sprajt("res/pause.png");
	public static Sprajt game_over = new Sprajt("res/game_over.png");
	public static Sprajt win = new Sprajt("res/you_win.png");

	public Sprajt(String path) {
		wczytaj(path);
	}
	
	/**
	 * Metoda wczytuje do pola Sprajta grafike
	 * @param path sciezka dostepu do pliku z grafika
	 */
	public void wczytaj(String path) {
		try {
			tekstura = ImageIO.read(new File(path));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * @return zwraca teksturê, czyli wczytany z pliku obrazek
	 */
	public Image getImage(){
		return tekstura;
	}
	
}