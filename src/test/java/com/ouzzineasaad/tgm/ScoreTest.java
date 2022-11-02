package test.java.com.ouzzineasaad.tgm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;

import main.java.com.ouzzineasaad.tgm.Score;

public class ScoreTest {
	
	@InjectMocks
	private Score score;
	
	@Test
	void updateTest() throws NoSuchFieldException, SecurityException {
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
		assertEquals(Score.getScore(), "Le joueur 2 a gagné le Set");
	}
	
	@Test
	void isThereAWinnerTest() throws NoSuchFieldException, SecurityException {
		Integer[] setScore = new Integer[] {0, 0};
		FieldSetter fieldSetter = new FieldSetter(score, Score.class.getDeclaredField("setScore"));
		fieldSetter.set(setScore);
		assertEquals(Score.getScore(), "0 - 0");
		Score.update(1);
		assertEquals(Score.isThereAWinner(), false);
		setScore[0] = 6;
		fieldSetter.set(setScore);
		assertEquals(Score.isThereAWinner(), true);
	}
	
	@Test
	void getSetScoreTest() throws NoSuchFieldException, SecurityException {
		Integer[] setScore1 = new Integer[] {3, 1};
	    new FieldSetter(score, Score.class.getDeclaredField("setScore")).set(setScore1);
		assertEquals("3 - 1", Score.getSetScore());
		
		Integer[] setScore2 = new Integer[] {6, 1};
	    new FieldSetter(score, Score.class.getDeclaredField("setScore")).set(setScore2);
		assertEquals("6 - 1" + "\n" + "Le joueur 1 a gagné la partie", Score.getSetScore());
	}
	
	@Test
	void isTieBreakTest() throws NoSuchFieldException, SecurityException {
		Integer[] setScore = new Integer[] {0, 0};
		FieldSetter fieldSetter = new FieldSetter(score, Score.class.getDeclaredField("setScore"));
		fieldSetter.set(setScore);
		assertEquals(Score.getScore(), "0 - 0");
		Score.update(1);
		assertEquals(Score.isTieBreak(), false);
		setScore[0] = 6;
		setScore[1] = 6;
		fieldSetter.set(setScore);
		assertEquals(Score.isTieBreak(), true);
	}
	
	@Test
	void getTieBreakScoreTest() throws NoSuchFieldException, SecurityException {
		Integer[] tieBreakScore1 = new Integer[] {3, 1};
	    new FieldSetter(score, Score.class.getDeclaredField("tieBreakScore")).set(tieBreakScore1);
		assertEquals(Score.getTieBreakScore(), "3 - 1");
		
		Integer[] tieBreakScore2 = new Integer[] {7, 1};
	    new FieldSetter(score, Score.class.getDeclaredField("tieBreakScore")).set(tieBreakScore2);
		assertEquals(Score.getTieBreakScore(), "7 - 1");
	}
	
	
	@BeforeEach
	public void setup() throws Exception {	    
	    MockitoAnnotations.initMocks(this);
	    
	    Integer[] pntSeqScore = new Integer[] {0, 0};
		new FieldSetter(score, Score.class.getDeclaredField("pntSeqScore")).set(pntSeqScore);
		
		Boolean[] winTieBreak = new Boolean[] {false, false};
	    new FieldSetter(score, Score.class.getDeclaredField("winTieBreak")).set(winTieBreak);
	}
}
