package main.java.com.ouzzineasaad.tgm;

/**
 * @author ouzzi
 * 
 * Score calculate methods
 */
public class Score {
	
	// init point sequence score to 0 - 0
	private static Integer[] pntSeqScore = new Integer[] {0, 0};
	
	private static final String[] possibleScores = new String[]{"0", "15", "30", "40", "WIN"};

	
	/**
	 * Get the current score
	 * 
	 * @return String score : exemple 40 - 15
	 */
	public static String getScore() {
		if ("WIN".equals(possibleScores[pntSeqScore[0]])) {
			return "Le joueur 1 a gagné la partie";
		} else if ( "WIN".equals(possibleScores[pntSeqScore[1]])) {
			return "Le joueur 2 a gagné la partie";
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
		pntSeqScore[player - 1] = pntSeqScore[player - 1] + 1;
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
