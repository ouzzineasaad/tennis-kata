package test.java.com.ouzzineasaad.tgm;

import static org.mockito.Mockito.doNothing;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;

import main.java.com.ouzzineasaad.tgm.Game;
import main.java.com.ouzzineasaad.tgm.TennisGameManager;

public class TennisGameManagerTest {
	
	@Mock
	private Game game;
	
	@InjectMocks
	private TennisGameManager tennisGameManager;
	
	@Test
	public void mainTest() {
	    
	    String userInput = String.format(System.lineSeparator());
	    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
	    System.setIn(bais);

	    String expected = "Touchez Entrer pour commencer une partie !";
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream printStream = new PrintStream(baos);
	    System.setOut(printStream);

	    TennisGameManager.main(null); // call the main method

	    String[] lines = baos.toString().split(System.lineSeparator());
	    String actual = lines[lines.length-1];
	    
	    doNothing().when(game).start();

	    // checkout output
	    Assertions.assertEquals(expected,actual);
	    
	}
	
	@BeforeEach
	public void setup() throws NoSuchFieldException, SecurityException {	    
	    MockitoAnnotations.initMocks(this);
	    FieldSetter fieldSetter = new FieldSetter(tennisGameManager, TennisGameManager.class.getDeclaredField("game"));
		fieldSetter.set(game);
	}

}
