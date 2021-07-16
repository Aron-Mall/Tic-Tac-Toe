package tictactoe;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char[][] inputs = new char[3][3];
        boolean turn = true;

        initBoard(inputs);
        printBoard(inputs);

            while(!checkState(inputs).equalsIgnoreCase("x wins") && !checkState(inputs).equalsIgnoreCase("o wins") && !checkState(inputs).equalsIgnoreCase("draw")) {

                System.out.println("Enter the coordinates:");

                try {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();


                    //check the values are in range of the grid
                    if (x < 1 || x > 3 || y < 1 || y > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if (inputs[x - 1][y - 1] == 'X' || inputs[x - 1][y - 1] == 'O') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        inputs[x - 1][y - 1] = turn ? 'X' : 'O';
                        printBoard(inputs);

                        String message = checkState(inputs);

                        switch (message) {
                            case "X wins":
                            case "O wins":
                            case "Draw":
                                System.out.println(message);
                                break;
                        }

                        turn = !turn;
                    }
                } catch (Exception error) {
                    System.out.println("You should enter numbers!");
                }

            }

    }

    public static String checkState(char[][] array) {


        if (checkMismatch(array)){
            return "Impossible";
        }

        if(checkRowsFor('X',array) && checkRowsFor('O',array)) {
            return "Impossible";
        } else if(checkColumnsFor('X',array) && checkColumnsFor('O',array)) {
            return "Impossible";
        }

        
        if (checkRowsFor('X', array)) {
            return "X wins";
        } else if (checkRowsFor('O', array)) {
            return "O wins";
        } else if (checkColumnsFor('X', array)) {
            return "X wins";
        } else if (checkColumnsFor('O', array)) {
            return "O wins";
        } else if (checkDiagonalFor('X', array)) {
            return "X wins";
        }  else if (checkDiagonalFor('O', array)) {
            return "O wins";
        } else if (!checkHasEmptyCells(array)) {
            return "Draw";
        } else if(checkHasEmptyCells(array)){
            return "Game not finished";
        }

        return "Impossible";

    }

    public static boolean checkRowsFor(char c, char[][] array) {
        for (int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++) {
                char firstCol = array[i][0];
                char secondCol = array[i][1];
                char thirdCol = array[i][2];
                if(firstCol == c && secondCol == c && thirdCol == c){
                    return true;
                }
              }
        }
        return false;
    }

    public static boolean checkColumnsFor(char c, char[][] array) {
        for (int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++) {
                char firstRow = array[0][j];
                char secondRow = array[1][j];
                char thirdRow = array[2][j];
                if(firstRow == c && secondRow == c && thirdRow == c){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkDiagonalFor(char c, char [][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                char first = array[0][0];
                char second = array[1][1];
                char third = array[2][2];
                char firstRev = array[0][2];
                char secondRev = array[1][1];
                char thirdRev = array[2][0];

                if (first == c && second == c & third == c){
                    return true;
                } else if (firstRev == c && secondRev == c & thirdRev == c) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checkHasEmptyCells(char[][] array) {
        for (int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++) {

                if(array[i][j] == ' ' || array[i][j] == '_' ){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checkMismatch(char[][] array){
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++) {
                char c = array[i][j];
                if(c == 'X'){
                    xCount++;
                }else if (c == 'O'){
                    oCount++;
                }
            }
        }

        if(xCount - oCount >= 2){
            return true;
        } else if (oCount - xCount >= 2){
            return true;
        }

        return false;
    }

    public static void initBoard(char[][] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0 ; j < inputs[i].length; j++) {
                inputs[i][j] = ' ';
            }
        }
    }

    public static void printBoard(char[][] inputs) {
        System.out.println("---------");
        System.out.println("| " + inputs[0][0] +  " " + inputs[0][1]  + " " + inputs[0][2] + " |");
        System.out.println("| " + inputs[1][0] +  " " + inputs[1][1]  + " " + inputs[1][2] + " |");
        System.out.println("| " + inputs[2][0] +  " " + inputs[2][1]  + " " + inputs[2][2] + " |");
        System.out.println("---------");
    }


}
