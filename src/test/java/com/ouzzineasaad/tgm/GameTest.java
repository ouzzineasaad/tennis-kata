package test.java.com.ouzzineasaad.tgm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.FieldSetter;

import main.java.com.ouzzineasaad.tgm.Game;
import main.java.com.ouzzineasaad.tgm.Score;

public class GameTest {

	@InjectMocks
	@Spy
	private Game game = new Game();

	@InjectMocks
	private Score score;

	@Test
	public void startTest() throws Exception {

		Mockito.doReturn(true).when(game).isEnded();

		String userInput = String.format("1", System.lineSeparator());
		ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(bais);

		String expected = "Game Score: 15 - 0";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		game.start();

		String[] lines = baos.toString().split(System.lineSeparator());
		String actual = lines[lines.length - 2];

		// checkout output
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void isThereAWinnerTest() throws NoSuchFieldException, SecurityException {
		Integer[] setScore = new Integer[] { 6, 0 };
		FieldSetter fieldSetter = new FieldSetter(score, Score.class.getDeclaredField("setScore"));
		fieldSetter.set(setScore);
		assertEquals(game.isEnded(), true);
	}

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

}
