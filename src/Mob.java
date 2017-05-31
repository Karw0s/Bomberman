import java.awt.Image;
import java.util.Random;

/**
 * Klasa dziedziczy z klasy Character i reprezentuje potwora
 */
public class Mob extends Character {

	private int k;
	private Sprajt sprite[][] = new Sprajt[][] { { Sprajt.mob1_1, Sprajt.mob1_2, Sprajt.mob1_3 },
			{ Sprajt.mob2_1, Sprajt.mob2_2, Sprajt.mob2_3 } };
	Random wybor=new Random();

	/**
	 * ustawia poczatkowe domyslne wartosci ustaiwenia i stanu potwora
	 * 
	 * @param x
	 *            wspolrzedna poczatkowa x na planszy
	 * @param y
	 *            wspolrzedna poczatkowa y na planszy
	 */
	public Mob(int type,BoardTable plansza) {

		boolean wylosowane = false;
		int x=0;
		int y=0;
		if(type == 2){
			do{
				x=wybor.nextInt(15);
				y=wybor.nextInt(11);
				if (plansza.mapa[y][x]==2)
					wylosowane = true;
			}while(plansza.mapa[y][x]!=2 && !wylosowane);
			//System.out.println("drzwi x:"+x+"y:"+y);
			
		}else if(type == 1){
			do{
				x=wybor.nextInt(14)+1;
				y=wybor.nextInt(10)+1;
				if (plansza.mapa[y][x]==0  && (x!=0 && y!=0) && (x!=0 && y!=1) && (x!=1 && y!=0))
					wylosowane = true;
			}while(plansza.mapa[y][x]!=0 && !wylosowane);
		}
		
		i = wybor.nextInt(2);
		this.x = x * 50 + 25;
		this.y = y * 50 + 25;
		d = 0;
		k = 0;
		counter = 0;
		alive = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Character#getImage()
	 */
	public Image getImage() {
		return sprite[i][0].getImage();
	}

	/**
	 * Metoda porusza postacia, najpierw sprawdzajac kolizje
	 * 
	 * @param plansza
	 *            ulozenie obiektow na planszy
	 */
	public void move(BoardTable plansza) {

		if (k == 0) {
			k = wybor.nextInt(300);
			d = k % 4;
		}
		k--;
		switch (d) {
		case 0:
			if ((x + 25) % 50 < 20)
				x = x - (x + 25) % 50;
			if ((x + 25) % 50 > 30)
				x = x + 50 - (x + 25) % 50;
			if (!Colision.isColision(x, y + 1, 0, plansza))
				y += 1;
			else
				k = 0;
			break;
		case 1:
			if ((y + 25) % 50 < 20)
				y = y - (y + 25) % 50;
			if ((y + 25) % 50 > 30)
				y = y + 50 - (y + 25) % 50;
			if (!Colision.isColision(x + 1, y, 1, plansza))
				x += 1;
			else
				k = 0;
			break;
		case 2:
			if ((y + 25) % 50 < 20)
				y = y - (y + 25) % 50;
			if ((y + 25) % 50 > 30)
				y = y + 50 - (y + 25) % 50;
			if (!Colision.isColision(x - 1, y, 2, plansza))
				x -= 1;
			else
				k = 0;
			break;
		case 3:
			if ((x + 25) % 50 < 20)
				x = x - (x + 25) % 50;
			if ((x + 25) % 50 > 30)
				x = x + 50 - (x + 25) % 50;
			if (!Colision.isColision(x, y - 1, 3, plansza))
				y -= 1;
			else
				k = 0;
			break;
		}

	}

}