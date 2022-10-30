package main.java.com.ouzzineasaad.tgm;

import java.util.Scanner;

/**
 * @author ouzzi
 * 
 * Tennis Game
 *
 */
public class Game {
	
	private int player;
	
	/**
	 * Start a new tennis game 
	 */
	public void start() {
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Score: " + Score.getScore());
			do {
				System.out.println("Taper 1 pour marquer le point pour joueur 1, ou taper 2 pour marquer le point"
						+ " pour joueur 2 : ");
				player = scan.nextInt();
				if (player != 1 && player != 2) {
					System.out.println("Seules les valeurs 1 ou 2 sont accepté");
				} else {
					Score.update(player);
					System.out.println("Score: " + Score.getScore());
				}
			} while (!isEnded());
		}
    }
	
	
	/**
	 * Check if the game is ended
	 * @return boolean : true if the game is ended, false otherwise 
	 */
	private boolean isEnded() {
		return Score.isThereAWinner();
	}

}
