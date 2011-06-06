import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Knight-piece in Chess.
 *
 * Performs the functions of a typical Knight-piece such as giving the
 * available number of moves and checking for movement validity. Also does
 * miscellaneous actions, such as giving its current position on the board.
 * 
 * @author Darren M.
 */
public class Knight {
    private int xPosition;  // X-coordinate of the Knight's chessboard position
    private int yPosition;  // Y-coordinate of the Knight's chessboard position

    /**
     * Constructor for the Knight-piece object, based on a pair of x- and
     * y-coordinates.
     *
     * @param startingX     Initial x-coordinate placement on the chessboard
     * @param startingY     Initial y-coordinate placement on the chessboard
     */
    public Knight( int startingX, int startingY ) {
        xPosition = startingX;
        yPosition = startingY;
    }

    /**
     * Constructor for the Knight-piece object, based on a Position.
     *
     * @param position      The position to set the Knight-piece on
     */
    public Knight( Position position ) {
        this( position.getXPosition(), position.getYPosition() );
    }

    /**
     * Check if a knight can move from the starting coordinates to the
     * ending coordinates.
     *
     * The Knight-piece can move 1 space on any axis, and 2 spaces on the
     * remaining axis. If the given coordinates are a valid spot to move to
     * under these constraints, the Knight-piece will take it.
     *
     * @param startingX     X-coordinate of the Knight-piece
     * @param startingY     Y-coordinate of the Knight-piece
     * @param xCoordinate   The destination position's x-coordinate
     * @param yCoordinate   The destination position's y-coordinate
     * @return      true, if the move is valid; false otherwise
     */
    private boolean checkMove( int startingX, int startingY, int xCoordinate,
                                int yCoordinate ) {
        //Check for minimum values
        if( (startingX < 0) || (startingY < 0) )
            return false;
        else if( (xCoordinate < 0) || (yCoordinate < 0) )
            return false;
        //Check for maximum values
        else if( startingX >= Constants.BOARD_WIDTH )
            return false;
        else if( startingY >= Constants.BOARD_HEIGHT )
            return false;
        else if( xCoordinate >= Constants.BOARD_WIDTH )
            return false;
        else if( yCoordinate >= Constants.BOARD_HEIGHT )
            return false;

        //Get the difference between the x-coordinates & y-coordinates
        int xDifference = startingX - xCoordinate;
        int yDifference = startingY - yCoordinate;

        //Compensate for negative values
        if( xDifference < 0 )
            xDifference *= -1;

        if( yDifference < 0 )
            yDifference *= -1;


        // A knight moves 1 in one axis...
        // ..and 2 in either direction on the remaining axis
        if( xDifference == 1 ) {
            if( yDifference == 2 )
                return true;
        }
        else if( xDifference == 2 ) {
            if( yDifference == 1 )
                return true;
        }

        return false;
    }

    /**
     * Check for the possible moves that can be made if the Knight-piece were
     * in the hypothetical coordinates.
     *
     * Calculate all 8 moves that the Knight-piece can make. If any of them are
     * valid, we store them in a List which is returned upon completion.
     *
     * @param xCoordinate   X-Coordinate where the Knight-piece might be placed
     * @param yCoordinate   Y-Coordinate where the Knight-piece might be placed
     * @return      A List of Positions which contains all the possible moves
     *              a Knight-piece can make from the requested position
     */
    public List<Position> getMovesFromPosition( int xCoordinate,
                                                int yCoordinate ) {
        // Allocate the result List
        List<Position> result = new ArrayList<Position>();

        int numberOfMoves = 8;  //The max amount of moves a Knight can make
        int xFinal = 0;     // x-coordinate of the hypothetical move
        int yFinal = 0;     // y-coordinate of the hypothetical move

        // Iterate through a Knight-peice's unique moveset
        for(int i = 0; i < numberOfMoves; i++) {
            switch( i ) {
                //North-West Movement
                case 0:
                    xFinal = xCoordinate - 1;
                    yFinal = yCoordinate + 2;
                    break;
                //North-East Movement
                case 1:
                    xFinal = xCoordinate + 1;
                    yFinal = yCoordinate + 2;
                    break;
                //East-North Movement
                case 2:
                    xFinal = xCoordinate + 2;
                    yFinal = yCoordinate + 1;
                    break;
                //East-South Movement
                case 3:
                    xFinal = xCoordinate + 2;
                    yFinal = yCoordinate - 1;
                    break;
                //South-East Movement
                case 4:
                    xFinal = xCoordinate + 1;
                    yFinal = yCoordinate - 2;
                    break;
                //South-West Movement
                case 5:
                    xFinal = xCoordinate - 1;
                    yFinal = yCoordinate - 2;
                    break;
                //West-South Movement
                case 6:
                    xFinal = xCoordinate - 2;
                    yFinal = yCoordinate - 1;
                    break;
                //West-North Movement
                case 7:
                    xFinal = xCoordinate - 2;
                    yFinal = yCoordinate + 1;
                    break;
            }

            // Check the move; save the coordinates if it's valid
            if( checkMove(xCoordinate, yCoordinate, xFinal, yFinal) ) {
                result.add( new Position(xFinal, yFinal) );
            }
        }

        return result;
    }

    /**
     * Check for the possible moves that can be made if the Knight-piece were
     * in the hypothetical coordinates.
     *
     * @param position      The position where the Knight-piece might be placed
     * @return      A List of Positions which contains all the possible moves
     *              a Knight-piece can make from the requested position
     */
    public List<Position> getMovesFromPosition( Position position ) {
        if( position != null ) {
            return getMovesFromPosition( position.getXPosition(),
                                        position.getYPosition() );
        }
        else
            return new ArrayList<Position>();
    }

    /**
     * Find the possible moves the Knight-piece can make from its
     * current position.
     *
     * @return      An array of int-pairs (x-coordinate followed by
     *                  y-coordinate), containing the coordinates that the
     *                  Knight-piece can move to.
     */
    public List<Position> getPossibleMoves() {
        return getMovesFromPosition( xPosition, yPosition );
    }

    /**
     * Find the knight-piece's current position on the board
     *
     * @return  The coordinates in an array, x-coordinate first and
     *          y-coordinate following.
     */
    public Position getPosition() {
        return new Position( xPosition, yPosition );
    }

    /**
     * Move the piece to the designated position.
     *
     *
     * @param xCoordinate   The x-coordinate to move to
     * @param yCoordinate   The y-coordinate to move to
     * @return              true: Knight-piece has successfully moved
     *                      false: The Knight-piece couldn't perform the request
     */
    public boolean move( int xCoordinate, int yCoordinate ) {
        // Check minimum boundaries
        if( (xCoordinate < 0) || (yCoordinate < 0) )
            return false;
        // Check maximum boundaries
        else if( (xCoordinate >= Constants.BOARD_WIDTH) )
            return false;
        else if( (yCoordinate >= Constants.BOARD_HEIGHT) )
            return false;

        // As long as the move is valid, we move there
        if( checkMove(xPosition, yPosition, xCoordinate, yCoordinate) ) {
            xPosition = xCoordinate;
            yPosition = yCoordinate;
            return true;
        }

        return false;
    }

    /**
     * Move the piece to the designated position.
     *
     *
     * @param xCoordinate   The x-coordinate to move to
     * @param yCoordinate   The y-coordinate to move to
     * @return              true: Knight-piece has successfully moved
     *                      false: The Knight-piece couldn't perform the request
     */
    public boolean move( Position position ) {
        if( position != null ) 
            return move( position.getXPosition(), position.getYPosition() );
        else
            return false;
    }
}
