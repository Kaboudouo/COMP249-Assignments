import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

// -----------------------------------------------------
// Assignment 1
// Question 1 Part 1
// Written by: Noa Chayer 40223439
// -----------------------------------------------------

class Player{
    public int position;
    public int currentRoll;
    public String name;

    public Player(String _name){
        position = 0;
        currentRoll = 0;
        name = _name;
    }
}

class Tiles{
    // Tile Number of Each Event (Corresponds to Fig.1 Board)
    private int[] snakeHeads =  {16, 48, 64, 79, 93, 95, 97, 98};
    private int[] snakeFoots =  {6,  30, 60, 19, 68, 24, 76, 78};
    private int[] ladderFoots = {1,  4,  9,  21, 28, 36, 51, 71, 80};
    private int[] ladderHeads = {38, 14, 31, 42, 84, 44, 67, 91, 100};

    public Player currentPlayer;
    public int tileNumber;
    public int snakeHead;
    public int snakeFoot;
    public int ladderHead;
    public int ladderFoot;


    public Tiles (int _tileNumber){
        currentPlayer = null;
        tileNumber = _tileNumber;

        for (int i = 0; i < snakeHeads.length; i++){
            if (snakeHeads[i] == tileNumber){
                snakeHead = snakeHeads[i];
                snakeFoot = snakeFoots[i];
            }
        }

        for (int i = 0; i < ladderHeads.length; i++){
            if (ladderFoots[i] == tileNumber){
                ladderHead = ladderHeads[i];
                ladderFoot = ladderFoots[i];
            }
        }
    }

}

class LadderAndSnake {
    public static int playerCount;
    public Tiles[][] tiles = new Tiles[10][10];
    public ArrayList<Player> players;
    public ArrayList<Player> orderedPlayers;
    public int turnCount = 0;

    // Sets Number For Each Tile
    private void initializeBoard(){
        int tileNum;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                tileNum = (i+1) + j*10;
                tiles[i][j] = new Tiles(tileNum);
            }
        }
    }

    private int[] tileIdx(int _tileNum){
        int[] idx = new int[2];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (tiles[i][j].tileNumber == _tileNum){
                    idx[0] = i;
                    idx[1] = j;
                }
            }
        }
        return idx;
    }

    private void movePlayer(Player player){

        int lastPos = player.position;
        
        int i = tileIdx(lastPos)[0];
        int j = tileIdx(lastPos)[1];

        // Clears Tile Player Before Moving
        tiles[i][j].currentPlayer = null;

        player.position += player.currentRoll;

        if (player.position > 100){
            player.position = 200 - player.position;
            System.out.println(player.name + " overshot it.");
        }

        i = tileIdx(player.position)[0];
        j = tileIdx(player.position)[1];

        if (player.position == tiles[i][j].snakeHead){
            player.position = tiles[i][j].snakeFoot;
            System.out.print(player.name + " has rolled a " + player.currentRoll + " but landed on a snake!;");
            System.out.println(" Ssssslides from " + tiles[i][j].snakeHead + " to " + player.position +  ".");
        } else if (player.position == tiles[i][j].ladderFoot){
            player.position = tiles[i][j].ladderHead;
            System.out.print(player.name + " has rolled a " + player.currentRoll + " and found a ladder!");
            System.out.println(" Climbes from " + tiles[i][j].ladderFoot + " to " + player.position + ".");
        } else{
            System.out.println(player.name + " has rolled a " + player.currentRoll + ". Went from " + lastPos + " to " + player.position + ".");  
        }

        i = tileIdx(player.position)[0];
        j = tileIdx(player.position)[1];

        if (tiles[i][j].currentPlayer == null || tiles[i][j].currentPlayer == player){
            tiles[i][j].currentPlayer = player;
        } else{
            System.out.println( player.name + " just kicked " + tiles[i][j].currentPlayer.name + " out! That must hurt...");
            tiles[i][j].currentPlayer.position = 0;
            tiles[i][j].currentPlayer = player;
        }
    }

    //Handles roll priority and player order
    private void checkRoll(){
        while(true){
            int high = 0;
            int repeatHigh = 0;
            Player leadPlayer = new Player("Error Destroyer");
    
            // Checks highest dice roll and looks for ties
            for (Player player : players){
                if (high < player.currentRoll){
                    leadPlayer = player;
                    high = player.currentRoll;
                    repeatHigh = 0;
                    continue;
                } else if (high == player.currentRoll){
                    repeatHigh++;
                }
            }
    
            // If a tie occurs for the highest roll, returns and re-rolls.
            if (repeatHigh > 0){
                int textIteration = 0;
                System.out.print("A " + Integer.toString(repeatHigh + 1) + " way tie has been achieved between ");
                for (int i = 0; i < players.size(); i++){
                    if (players.get(i).currentRoll == high){
                        if(repeatHigh == textIteration){
                            System.out.print("and " + players.get(i).name + ".");
                            System.out.print(" Attempting to break the tie.\n\n");
                        } else{
                            System.out.print(players.get(i).name + ", ");
                            textIteration++;
                        }
                    }
                }
                return;
            } else{
                // If no ties for highest roll, player is next in order, continue for remaining players.
                orderedPlayers.add(leadPlayer);
                players.remove(leadPlayer);
                if (players.size() == 1){
                    orderedPlayers.add(players.get(0));
                    break;
                }
                continue;
            }
        }
    }

    public static int flipDice(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    // Game Engine
    public void play(){
        System.out.println(tiles[3][6].tileNumber);
        // Initialize players
        for (int i = 0; i < playerCount; i++){
            players.add(new Player("Player_" + Integer.toString(i+1)));
        }

        // Player Order Sorting Logic
        System.out.println("Now deciding player order...");
        int attempt_count = 0;
        while(orderedPlayers.size() < playerCount - 1){
            for (Player player : players){
                player.currentRoll = flipDice();
                System.out.println(player.name + " has rolled a " + Integer.toString(player.currentRoll) + ".");
            }
            checkRoll();
            attempt_count++;
        }
        System.out.print("Reached final decision on order of playing: ");
        for (Player player : orderedPlayers){
            System.out.print(player.name);
            if (player != orderedPlayers.get(playerCount-1)){
                System.out.print(", then ");
            }
        }
        System.out.println(". It took " + attempt_count + " attempts before a decision could be made.");

        // Game Logic
        System.out.println("\n------------------------------------\n     The Game Will Now Start     \n------------------------------------");
        while(true){
            turnCount++;
            System.out.println("\n---------------Turn " + turnCount + "---------------");
            for (Player player : orderedPlayers){
                player.currentRoll = flipDice();
                movePlayer(player);
            }

            if(tiles[9][9].currentPlayer != null){
                System.out.println(tiles[9][9].currentPlayer.name + " has reached the 100th square!");
                System.out.println("\nCongratulations " + tiles[9][9].currentPlayer.name + "!\n-----------------      -------------\nThank you for playing Kab's amazing Snakes&Ladders.");
                System.exit(0);
            }
        }
    }

    // Default Constructor
    public LadderAndSnake(){
        playerCount = 2;
        players = new ArrayList<Player>(playerCount);
        orderedPlayers = new ArrayList<Player>(playerCount);
        initializeBoard();
    }

    // Player Count Specified Constructor
    public LadderAndSnake(int _playerCount){
        playerCount = _playerCount;
        players = new ArrayList<Player>(playerCount);
        orderedPlayers = new ArrayList<Player>(playerCount);
        initializeBoard();
    }
}

public class PlayLadderAndSnake {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to Snakes and Ladders. Pleaser enter the number of players: ");

        int player_count;

        if (!input.hasNextInt()){
            System.out.println("Error. Please input a valid integer. Game will now exit.");
            System.exit(0);
        }
        player_count = input.nextInt();

        input.close();

        System.out.println("\n");

        // Comment this block for more than 2 players
        if (player_count > 2){
            System.out.println("Initialization was attempted for " + player_count + " players; however, this is only expected for an extended version the game. Value will be set to 2");
            player_count = 2;
        } else if (player_count < 2){
            System.out.println("Error: Cannot execute the game with less than 2 players! Will exit.");
            System.exit(0);
        }

        LadderAndSnake GameBoard = new LadderAndSnake(player_count);
        GameBoard.play();
    }

}
