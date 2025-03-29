package TicTac;

import TicTac.Model.Board;
import TicTac.Model.Piece;
import TicTac.Model.Player;
import TicTac.Model.PlayingPieceO;
import TicTac.Model.PlayingPieceX;
import TicTac.Pair;


import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Scanner inputScanner = new Scanner(System.in);

    Deque<Player> players;
    Board gameBoard;


    public void initializeGame(){

        //creating 2 Players
        players = new LinkedList<>();
        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player1", crossPiece);

        PlayingPieceO noughtsPiece = new PlayingPieceO();
        Player player2 = new Player("Player2", noughtsPiece);

        players.add(player1);
        players.add(player2);

        //initializeBoard
        gameBoard = new Board(3);
    }
    public String startGame(){
        boolean noWinner = true;
        while (noWinner){
            Player playerTurn = players.removeFirst();

            gameBoard.printBoard();
            List<Pair<Integer,Integer>> freeSpace = gameBoard.freeCells();
            if(freeSpace.isEmpty()){
                noWinner = false;
                continue;
            }
            System.out.println("Player turn: " + playerTurn);
            Scanner scanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);
            boolean addPiece = gameBoard.addPiece(inputRow,inputColumn,playerTurn.piece);
            if(!addPiece){
                System.out.println("Incorrect piece!");
                players.add(playerTurn);
                continue;
            }
 players.addLast(playerTurn);
            boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.piece.getPiece());
            if(winner) {
                return playerTurn.name;
            }


        }
        return "Tie";

    }

    public boolean isThereWinner(int row, int column, Piece pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].getPiece() != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[i][column] == null || gameBoard.board[i][column].getPiece() != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.size;i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].getPiece() != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size;i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].getPiece() != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }


}
