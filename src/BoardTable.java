import java.util.Random;

/**
 *  Obiekty tej klasy s¹ fizyczn¹ reprezentacj¹ mapy gry, czyli planszy i zawieraj¹ informacjê o u³o¿eniu obiektów takich jak ceg³a, gracz, bomba
 *  Konwencja: 0-pole puste, 1-p³ytka, 2-ceg³a, 3-bomba, 4-srodek ognia, 5-ogien lewo, 6-ogien prawo
 *  7-ogien gora, 8-ogien dol, 9-spalona cegla, -1 - drzwi
 */
public class BoardTable {
	
	Random generator=new Random();
	
	public short mapa[][]=new short[][]{
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
	
	/**
	 * Konstruktor wypelniajacy plansze domyslnymi wartosciami 
	 */
	public BoardTable(){
		
		generate();
		
	}
	
	private void generate(){
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 15; j++) {
				if(mapa[i][j]==0&&(generator.nextInt(50)<20)){
					mapa[i][j]=2;
					
				}
			}
		}
		mapa[0][0]=0;
		mapa[0][1]=0;
		mapa[1][0]=0;

	}
	
	

}