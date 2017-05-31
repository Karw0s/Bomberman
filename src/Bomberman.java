

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * Glowna klasa gry dziedziczaca po JFrame, implementujaca ActionListener
 * odpowiada za utworzenie panelu gry, rozwijanego menu i obsluge przyciskow w menu
 */
public class Bomberman extends JFrame implements ActionListener {

	private final int width = 805;
	private final int height = 648;
	
	private Board board = new Board();
	
	private JMenuBar menuBar;
	private JMenu menuPlik, menuPomoc;
	private JMenuItem mNowaGra, mPauza, mWyjscie, mOProgr;

	/**
	 * Ustawia wszelkie komponenty obiektu Bomberman
	 */
	public Bomberman() {

		
		add(board, BorderLayout.CENTER);
		
		setSize(width, height);
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setResizable(false);
		setVisible(true);
		
		menuBar = new JMenuBar();
		menuPlik = new JMenu("Plik");

		mNowaGra = new JMenuItem("Nowa Gra");
		mNowaGra.setAccelerator(KeyStroke.getKeyStroke("F2"));
		mPauza = new JMenuItem("Pauza");
		mPauza.setAccelerator(KeyStroke.getKeyStroke("P"));
		mWyjscie = new JMenuItem("Wyjscie");

		menuPlik.add(mNowaGra);
		menuPlik.add(mPauza);
		menuPlik.addSeparator(); // rozdzielanie pozycji w menu
		menuPlik.add(mWyjscie);
		
		mNowaGra.addActionListener(this);
		mPauza.addActionListener(this);
		mWyjscie.addActionListener(this);

		menuPomoc = new JMenu("Pomoc");

		mOProgr = new JMenuItem("O Programie");
		mOProgr.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mOProgr.addActionListener(this);

		menuPomoc.add(mOProgr);

		setJMenuBar(menuBar);
		menuBar.add(menuPlik);
		menuBar.add(menuPomoc);
		
	}
	
	
	/* (non-Javadoc)
	 * Obsluguje zdarzenia
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object zrodlo = e.getSource();
		if (zrodlo == mWyjscie) {
			dispose();
		}else if(zrodlo == mPauza){
			board.pauseGame();
		}else if(zrodlo == mOProgr){
			JOptionPane.showMessageDialog(this, "Program stworzyli: \nMicha³ Karwowski\nCezary Baranowski\nKarolina Kowalik\nWersja 1.0");
		}
		else if(zrodlo==mNowaGra)
		{
			dispose();
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					Bomberman game = new Bomberman();
				}
			});
			
		}
		
	}

	/** 
	 * Glowna metoda gry uruchamiajaca ja w nowym watku kontroli zdarzen
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Bomberman game = new Bomberman();
			}
		});

	}

}