package main.java.com.ouzzineasaad.tgm;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ouzzi
 * 
 *         Score calculate methods
 */
public class Score {

	// init point sequence score to 0 - 0
	private static Integer[] pntSeqScore = new Integer[] { 0, 0 };

	// init point sequence score to 0 - 0
	private static Integer[] setScore = new Integer[] { 0, 0 };

	private static final String[] possibleScores = new String[] { "0", "15", "30", "40", "ADVANTAGE", "WIN" };

	private static Integer[] tieBreakScore = new Integer[] { 0, 0 };

	private static Boolean[] winTieBreak = new Boolean[] { false, false };

	/**
	 * Get the current score
	 * 
	 * @return String score : exemple 40 - 15
	 */
	public static String getScore() {
		for (int i = 0; i < 2; i++) {
			if ("WIN".equals(possibleScores[pntSeqScore[i]])) {
				setScore[i] = setScore[i] + 1;
				resetGameScore();
				String scoreMessage = "Le joueur " + (i + 1) + " a gagné le Set";
				if (isTieBreak()) {
					scoreMessage += ".\nDébut du Tie Break";
				}
				return scoreMessage;
			} else if (pntSeqScore[i] == 4) {
				return "Avantage pour le joueur " + (i + 1);
			}
		}
		if (pntSeqScore[0] == pntSeqScore[1] && pntSeqScore[0] == 3) {
			return "DEUCE 40 - 40";
		} else {
			return possibleScores[pntSeqScore[0]] + " - " + possibleScores[pntSeqScore[1]];
		}
	}

	/**
	 * Get the current Set score
	 * 
	 * @return String score : exemple 4 - 6
	 */
	public static String getSetScore() {
		for (int i = 0; i < 2; i++) {
			int opponent = i == 1 ? 0 : 1;
			if (winTieBreak[i] || 6 == setScore[i] && (setScore[i] - setScore[opponent]) > 1) {
				return setScore[0] + " - " + setScore[1] + "\n" + "Le joueur " + (i + 1) + " a gagné la partie";
			}
		}
		return setScore[0] + " - " + setScore[1];
	}

	/**
	 * Update player score
	 * 
	 * @param player: 1 or 2 corresponding to the player who scored the point
	 */
	public static void update(int player) {
		if (isTieBreak()) {
			tieBreakScore[player - 1] = tieBreakScore[player - 1] + 1;
		} else if (pntSeqScore[player - 1] > 2) {
			updateReached40(player);
		} else {
			pntSeqScore[player - 1] = pntSeqScore[player - 1] + 1;
		}
	}

	/**
	 * Score reached 40 operations
	 * 
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
		for (int i = 0; i < 2; i++) {
			int opponent = i == 1 ? 0 : 1;
			if (winTieBreak[i] || setScore[i] == 6 && setScore[i] - setScore[opponent] > 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Rest Game score after win an Set
	 */
	private static void resetGameScore() {
		for (int i = 0; i < 2; i++) {
			pntSeqScore[i] = 0;
		}
	}

	/**
	 * Get the current Tie Break score
	 * 
	 * @return String score : exemple 4 - 6
	 */
	public static String getTieBreakScore() {
		for (int i = 0; i < 2; i++) {
			int opponent = i == 1 ? 0 : 1;
			if (tieBreakScore[i] >= 7 && (tieBreakScore[i] - tieBreakScore[opponent]) > 1) {
				setScore[i] = setScore[i] + 1;
				winTieBreak[i] = true;
			}
		}
		return tieBreakScore[0] + " - " + tieBreakScore[1];
	}

	/**
	 * Check if Tie Break is activated
	 * 
	 * @return boolean
	 */
	public static boolean isTieBreak() {
		return 6 == setScore[0] && 6 == setScore[1];
	}
}
