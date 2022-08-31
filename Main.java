
public class Main {

    private static final int DIMENSIONS = 9;

    public static void main(String[] args) {
        int[][] SudokuBoard = {
                {0,6,0,3,0,0,0,0,7},
                {8,1,5,0,0,2,0,0,0},
                {4,3,7,0,0,0,0,2,5},
                {0,5,8,4,0,0,0,0,3},
                {7,4,3,0,0,0,0,0,0},
                {0,0,1,5,8,3,4,0,6},
                {0,0,6,9,3,1,0,0,0},
                {0,0,4,6,0,0,5,9,1},
                {0,7,9,2,4,0,0,0,0},
        };

        if (Solver(SudokuBoard)){
            System.out.println("Solved!");
        } else {
            System.out.println("Invalid Board");
        }

        printSudokuBoard(SudokuBoard);

    }

    private static void printSudokuBoard(int[][] sudokuBoard) {
        for(int row = 0; row < DIMENSIONS; row++){
            for(int col = 0; col<DIMENSIONS; col++){
                System.out.print(sudokuBoard[row][col]);
            }
            System.out.println();
        }
    }

    private static boolean RowDupe(int[][] SudokuBoard, int number, int row){
        for(int i = 0;  i<DIMENSIONS; i++){
            if(SudokuBoard[row][i]==number){
                return true;
            }
        }
        return false;
    };

    private static boolean ColDupe(int[][] SudokuBoard, int number, int col){
        for(int i = 0;  i<DIMENSIONS; i++){
            if(SudokuBoard[i][col]==number){
                return true;
            }
        }
        return false;
    };

    private static boolean BoxDupe(int[][] SudokuBoard, int number, int row, int col){
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for(int i = boxCol; i < boxCol + 3; i++){
            for(int j = boxRow; j<boxRow +3; j++){
                if (SudokuBoard[j][i]==number){
                    return true;
                }
            }
        }
        return false;
    };

    private static boolean validPlacement(int[][] SudokuBoard, int number, int row, int col){
      return !RowDupe(SudokuBoard, number, row) &&
              !ColDupe(SudokuBoard, number, col) &&
              !BoxDupe(SudokuBoard, number, row, col);
    };

    private static boolean Solver(int[][] SudokuBoard){
        for(int row = 0; row<DIMENSIONS; row++){
            for(int col = 0; col<DIMENSIONS; col++){
                if(SudokuBoard[row][col]==0){
                    for(int attemptNum=1;attemptNum<=DIMENSIONS; attemptNum++ ){
                        if (validPlacement(SudokuBoard, attemptNum, row, col)){
                            SudokuBoard[row][col] = attemptNum;

                            if(Solver(SudokuBoard)){
                                return true;
                            }else{
                                SudokuBoard[row][col] = 0;
                            };


                        }
                    }
                    return false;
                }

            }
        }
        return true;
    };

}
