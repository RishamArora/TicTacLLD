package TicTac.Model;


import TicTac.Model.PlayingPiece;

import TicTac.Pair;
import java.util.ArrayList;
import java.util.List;



public class Board {
    public int size;
    public PlayingPiece [][] board;
    public Board(int size){
        this.size = size;
        board = new PlayingPiece[size][size];
    }
    public boolean addPiece(int x, int y,PlayingPiece val){
        if(board[x][y] != null){
            return false;
        }
        board[x][y] = val;
        return true;
    }

public List<Pair<Integer, Integer>> freeCells(){
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for(int i =0;i<size;i++){
            for(int j =0;j<size;j++){
                if(board[i][j] == null){
                    list.add(new Pair<>(i,j));
                }
            }
        }
        return list;
}


    public void printBoard(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j] != null){
                    System.out.print(board[i][j].piece+" ");
                }else{
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

}