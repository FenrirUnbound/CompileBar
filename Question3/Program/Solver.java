import java.util.ArrayList;
import java.util.List;

/**
 * Solves the "Lonely Knight on a Chessboard" puzzle presented as Puzzle #1.
 *
 * Utilizing an internal chessboard, the Solver moves the Knight-piece around
 * the board, moving as much as possible without stepping into a previously
 * visited space. It prioritizes a move based on how many options (or number of
 * moves) each move individually possess, resorting to the move with the least
 * amount of options. 
 *
 * @author Darren M.
 */
public class Solver {
    private int[][] _visit;     // The board which tracks visits
    private Knight _knight;     // The Knight-piece
    private int numberOfMoves;  // Amount of moves committed

    /**
     * Solver drives the Knight-piece around the board, minimizing the amount
     * of moves it takes to visit every square on the board at least once
     *
     * @param startingX     X-coordinate of the Knight-piece's starting position
     * @param startingY     Y-coordinate of the Knight-piece's starting position
     */
    public Solver( int startingX, int startingY ) {
        _visit = new int[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];
        _knight = new Knight( startingX, startingY );
        numberOfMoves = 0;

        // Initialize the Visits board
        for(int i = 0; i < Constants.BOARD_WIDTH; i++) {
            for(int j = 0; j < Constants.BOARD_HEIGHT; j++) {
                _visit[i][j] = Constants.EMPTY_SLOT;
            }
        }

        // Mark the starting point as visited
        _visit[startingX][startingY] = numberOfMoves;
    }

    /**
     * Filters a Knight-piece's movelist for non-previously visited spaces.
     *
     * @param moveList  List of moves available to a Knight-piece to filter
     * @return      The list of moves which have not been performed before
     */
    private List<Position> filterForNonVisits( List<Position> moveList ) {
        List<Position> result = new ArrayList<Position>();

        while( moveList.size() > 0 ) {
            Position position = moveList.remove(0);

            int xCoordinate = position.getXPosition();
            int yCoordinate = position.getYPosition();

            // Absorb the Position if it hasn't been visited before
            if( _visit[xCoordinate][yCoordinate] == Constants.EMPTY_SLOT )
                result.add( position );
        }

        return result;
    }

    /**
     * Obtain the board marking the Knight-piece's visits
     *
     * @return      The 2D array representing the board
     */
    public int[][] getBoard() {
        return _visit;
    }

    /**
     * Get the knight's position on the board
     *
     * @return      The Knight-peice's position
     */
    public Position getKnightPosition() {
        return _knight.getPosition();
    }

    /**
     * Obtain the number of moves a Knight-piece can make from a given
     * position without re-visiting a square.
     * 
     * @param xPosition     X-coordinate of the position the Knight moves from
     * @param yPosition     Y-coordinate of the position the Knight moves from
     * @return      The number of moves a Knight-piece can make from the
     *              given position, minus the moves that would revisit a
     *              square.
     */
    private int getNumberOfMoves(int xPosition, int yPosition) {
        List<Position> moveList =
                _knight.getMovesFromPosition(xPosition, yPosition);

        //Filter for previous visited spaces
        List<Position> filteredList = filterForNonVisits( moveList );

        return filteredList.size();
    }

    /**
     * Moves the Knight-piece to the next viable, unvisited space on the board.
     *
     * The Knight-piece's moves are filtered for non-visited spaces. Each move
     * is weighed by how many moves they themselves have, where the move with
     * the least weight is taken. Ties are wrongfully assumed to be equal in
     * value and will choose the first move encountered.
     *
     * @return      The position where the Knight-piece moved to
     */
    public Position nextMove() {
        // Filter for non-visited spaces
        List<Position> moveList =
                filterForNonVisits( _knight.getPossibleMoves() );

        // No non-redundant moves available
        if( moveList.size() == 0 )
            return null;

        // Calculate weights
        int[] weights = new int[moveList.size()];
        for( int i = 0; i < moveList.size(); i++ ) {
            Position position = moveList.get(i);

            weights[i] = getNumberOfMoves( position.getXPosition(),
                                            position.getYPosition() );
        }

        // Find the smallest weight
        int smallestWeight = 1000;
        int index = 0;
        for( int i = 0; i < weights.length; i++ ) {
            if( weights[i] < smallestWeight ) {
                smallestWeight = weights[i];
                index = i;
            }
        }

        // Grab the chosen destination...
        Position destination = moveList.get(index);
        int xPosition = destination.getXPosition();
        int yPosition = destination.getYPosition();
        // ... and moves the piece
        _knight.move( destination );
        _visit[xPosition][yPosition] = ++numberOfMoves;

        return destination;
    }
}
