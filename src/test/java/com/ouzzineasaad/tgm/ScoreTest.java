package test.java.com.ouzzineasaad.tgm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.ouzzineasaad.tgm.Score;

public class ScoreTest {

	@Test
	void updateTest() {
		assertEquals(Score.getScore(), "0 - 0");
		Score.update(1);
		assertEquals(Score.getScore(), "15 - 0");
		Score.update(1);
		Score.update(1);
		Score.update(2);
		Score.update(2);
		Score.update(2);
		assertEquals(Score.getScore(), "DEUCE 40 - 40");
		Score.update(2);
		assertEquals(Score.getScore(), "Avantage pour le joueur 2");
		Score.update(2);
		assertEquals(Score.getScore(), "Le joueur 2 a gagné la partie");
	}
	
	@Test
	void isThereAWinnerTest() {
		assertEquals(Score.getScore(), "0 - 0");
		Score.update(1);
		assertEquals(Score.isThereAWinner(), false);
		Score.update(1);
		Score.update(1);
		Score.update(1);
		assertEquals(Score.isThereAWinner(), true);
	}
	
	@BeforeEach
	public void setup() throws Exception {
	    Field pntSeqScore = Score.class.getDeclaredField("pntSeqScore");
	    pntSeqScore.setAccessible(true); //to overcome the visibility issue
	    pntSeqScore.set(null, new Integer[] {0, 0}); //null since it's static
	}
}
