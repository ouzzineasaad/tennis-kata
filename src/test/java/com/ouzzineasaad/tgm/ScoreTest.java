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
		assertEquals(Score.getSetScore(), "3 - 1");
		
		Integer[] setScore2 = new Integer[] {6, 1};
	    new FieldSetter(score, Score.class.getDeclaredField("setScore")).set(setScore2);
		assertEquals(Score.getSetScore(), "6 - 1" + "\n" 
				+ "Le joueur 1 a gagné la partie");
	}
	
	
	@BeforeEach
	public void setup() throws Exception {	    
	    MockitoAnnotations.initMocks(this);
	    
	    Integer[] pntSeqScore = new Integer[] {0, 0};
		FieldSetter fieldSetter = new FieldSetter(score, Score.class.getDeclaredField("pntSeqScore"));
		fieldSetter.set(pntSeqScore);
	}
}
