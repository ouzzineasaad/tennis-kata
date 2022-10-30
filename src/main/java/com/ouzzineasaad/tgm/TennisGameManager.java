package main.java.com.ouzzineasaad.tgm;

import java.util.Scanner;

public class TennisGameManager {

    public static void main (String[] args){
    	
    	System.out.println("############## Bienvenue dans le Tennis Game Manager");
    	System.out.println("");
        System.out.println("Touchez Entrer pour commencer une partie !");
        System.out.println("");
        try (Scanner scan = new Scanner(System.in)) {
        	scan.nextLine();
        	Game game = new Game();
        	game.start();
        }

    }
}
