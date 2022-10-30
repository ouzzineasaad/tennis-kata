package main.java.com.ouzzineasaad.tgm;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ouzzi
 * 
 * Score calculate methods
 */
public class Score {
	
	// init point sequence score to 0 - 0
	private static Integer[] pntSeqScore = new Integer[] {0, 0};
	
	private static final String[] possibleScores = new String[]{"0", "15", "30", "40", "ADVANTAGE", "WIN"};

	
	/**
	 * Get the current score
	 * 
	 * @return String score : exemple 40 - 15
	 */
	public static String getScore() {
		for (int i = 0; i < 2; i++) {
			if ("WIN".equals(possibleScores[pntSeqScore[i]])) {
				return "Le joueur " + (i+1) + " a gagné la partie";
			} else if (pntSeqScore[i] == 4) {
				return "Avantage pour le joueur " + (i+1);
			}
		}
		if (pntSeqScore[0] == pntSeqScore[1] && pntSeqScore[0] == 3){
			return "DEUCE 40 - 40";
		} else {			
			return possibleScores[pntSeqScore[0]] + " - " + possibleScores[pntSeqScore[1]];
		}
    }
	

	/**
	 * Update player score
	 * 
	 * @param player: 1 or 2 corresponding to the player who scored the point
	 */
	public static void update (int player) {
		if (pntSeqScore[player - 1] > 2) {
			updateReached40(player);
		} else {			
			pntSeqScore[player - 1] = pntSeqScore[player - 1] + 1;
		}
	}
	
	
	/**
	 * Score reached 40 operations
	 * @param player win the point
	 */
	private static void updateReached40(int player) {
		int opponent = player == 1 ? 2 : 1;
		
		Set<Integer> distinct = Arrays.stream(pntSeqScore).collect(Collectors.toSet());
		if (distinct.size() == 1) { // DEUCE => ADVANTAGE
			pntSeqScore[player - 1] = pntSeqScore[player - 1] + 1;
		} else if (pntSeqScore[opponent - 1] == 4) { // => DEUCE
			pntSeqScore[opponent - 1] = pntSeqScore[opponent - 1] - 1;
		} else if (pntSeqScore[player - 1] == 4) { // ADVANTAGE => WIN
			pntSeqScore[player - 1] = pntSeqScore[player - 1] + 1;
		} else { // => WIN
			pntSeqScore[player - 1] = pntSeqScore[player - 1] + 2;
		}
	}


	/**
	 * Check if there is a winner
	 * 
	 * @return boolean
	 */
	public static boolean isThereAWinner() {
		return "WIN".equals(possibleScores[pntSeqScore[0]]) || "WIN".equals(possibleScores[pntSeqScore[1]]);
	}
}
