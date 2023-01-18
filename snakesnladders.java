import java.util.Random;
import java.util.Scanner;

// -----------------------------------------------------
// Assignment 0
// Question: 1
// Written by: Noa Chayer 40223439
// -----------------------------------------------------

public class snakesnladders {

    public static int flipDice(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public static void play(int player_count){
        System.out.println("Now deciding player order...");

        int attempt_count = 1;
        boolean p1_first;
        while(true){
            int player1_roll = flipDice();
            int player2_roll = flipDice();

            System.out.println("Player 1 has rolled a " + player1_roll);
            System.out.println("Player 2 has rolled a " + player2_roll);

            if (player1_roll == player2_roll){
                attempt_count++;
                System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to break the tie");
            }
            else if(player1_roll > player2_roll){
                System.out.println("Reached final decision on order of playing: Player 1 then Player 2. It took " + attempt_count + " attempts before a decision could be made");
                p1_first = true;
                break;
            } else {
                System.out.println("Reached final decision on order of playing: Player 2 then Player 1. It took " + attempt_count + " attempts before a decision could be made");
                p1_first = false;
                break;
            }
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Snakes and Ladders. Pleaser enter the number of players:\n");

        int player_count;
        player_count = input.nextInt();

        if (player_count > 2){
            System.out.println("Initialization was attempted for " + player_count + " players; however, this is only expected for an extended version the game. Value will be set to 2");
            player_count = 2;
        } else if (player_count < 2){
            System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
            System.exit(0);
        }

        play(player_count);
    }

}
