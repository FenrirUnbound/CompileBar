import java.util.Scanner;

/**
 * Plays the "Lonely Knight-piece" puzzle solver.
 *
 * Using the Solver class as the back-end logic, this class interacts with the
 * user utilizing the command line. It can be run by defaulting the
 * Knight-piece to the (0,0) coordinate. Alternatively, it can be run by
 * passing in the x-coordinate and the y-coordinate separately as arguments.
 *
 * @author Darren M.
 */
public class Play {
    private static final int INPUT_OFFSET = 1;  //Offset for 0-index counting

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Takes user input
        Solver solver = new Solver( 0, 0 );     // Moves the Knight on the board
        int numberOfMoves = 0;                  // Number of moves made
        boolean hasNextMove = true;             // Flag if another move exists

        // Change the starting location of the Knight-peice
        if( args.length == 2 ) {
            int xPosition = 0;
            int yPosition = 0;

            xPosition = (new Integer(args[0]));
            yPosition = (new Integer(args[1]));

            // Positions are non-negative
            if( xPosition < 0 )
                xPosition *= -1;
            if( yPosition < 0 )
                yPosition *= -1;

            // Board is zero-index based; offset arguments by 1
            if( xPosition > 0 )
                xPosition -= INPUT_OFFSET;
            if( yPosition > 0 )
                yPosition -= INPUT_OFFSET;

            /* Positions greater than the board dimensions scaled down to the
             * board's highest dimensions
             */
            if( xPosition > Constants.BOARD_WIDTH )
                xPosition = Constants.BOARD_WIDTH - INPUT_OFFSET;
            if( yPosition > Constants.BOARD_HEIGHT )
                yPosition = Constants.BOARD_HEIGHT - INPUT_OFFSET;

            System.out.println("Custom starting location:  " + "(" +
                                xPosition + "," + yPosition + ")" );
            solver = new Solver(xPosition, yPosition);
        }
        // Wrong amount of arguments; print usage and break
        else if (args.length != 0 ) {
            System.out.println("usage:        java Play");
            System.out.println("              java Play starting_x_position " +
                                "starting_y_position");
            return;
        }

        // Print the intial board
        printBoard( solver.getBoard(), numberOfMoves++ );

        while( hasNextMove ) {
            System.out.print("Hit enter to continue ");
            System.out.print("(^D to exit): ");

            // Kick out of the program upon receiving EOF
            if(! input.hasNextLine() ) {
                System.out.println();
                return;
            }
            // clear the input
            else {
                System.out.println("\n\n");
                input.nextLine();
            }

            // Make the next move and verify it was made; break otherwise
            Position nextMove = solver.nextMove();
            if( nextMove == null ) {
                hasNextMove = false;
                System.out.println("Out of Moves!");

                // Offset numberOfMoves to not increment for not really moving
                numberOfMoves--;
            }

            printBoard( solver.getBoard(), numberOfMoves++ );
        }
    }

    /**
     * Print the chessboard.
     *
     * Prints the Knight-piece's move history on each space, while depicting
     * its current location as a double asterik (i.e., "**").
     *
     * @param board         2D array containing the move history
     * @param moveNumber    Number of moves made so far
     */
    private static void printBoard( int[][] board, int moveNumber ) {
        for( int i = 0; i < Constants.BOARD_WIDTH; i++ ) {
            // Print each space...
            for( int j = 0; j < Constants.BOARD_HEIGHT; j++ ) {
                System.out.print("[");

                // No activity in this slot, so print a blank...
                if( board[i][j] == Constants.EMPTY_SLOT )
                    System.out.print("  ");
                // ...print "**" for the Knight-piece's current position
                else if( board[i][j] == moveNumber )
                    System.out.print("**");
                // Format the output for single-digit numbers
                else if( board[i][j] < 10 )
                    System.out.print("0" + board[i][j] );
                else
                    System.out.print( board[i][j] );

                System.out.print("]");
            }
            
            // break the line
            System.out.println();
        }
        
        System.out.println("\nMove number:  " + moveNumber);
    }
}
