/**
 * A Position class that represents a point on a 2D Cartesian coordinate
 * system.
 *
 * Able to tell its position, as well as set its position on the coordinate
 * system to a new location based on individual x-coordinates, y-coordinates,
 * or a pair of both.
 *
 * @author Darren M.
 */
public class Position {
    private int xPosition;  // The x-coordinate of the position
    private int yPosition;  // The y-coordinate of the position

    /**
     * A position of an x- and y-coordinate pair on a Cartesian coordinate
     * system.
     *
     * @param xCoordinate   The x-coordinate of the position
     * @param yCoordinate   The y-coordinate of the position
     */
    public Position( int xCoordinate, int yCoordinate ) {
        xPosition = xCoordinate;
        yPosition = yCoordinate;
    }

    /**
     *  Obtain the x-position of the Position
     *
     * @return      The x-position of this Position
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Get the y-position of the Position
     *
     * @return      The y-position of this Position
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Move the position to the desired coordinates.
     *
     * @param xCoordinate   The x-coordinate to move this Position to
     * @param yCoordiante   The y-coordinate to move this Position to
     */
    public void moveToPosition( int xCoordinate, int yCoordinate ) {
        setXPosition( xCoordinate );
        setYPosition( yCoordinate );
    }

    /**
     * Set the x-coordinate to the desired position
     *
     * @param xCoordinate   The x-coordinate to move this Position to
     */
    public void setXPosition( int xCoordinate ) {
        xPosition = xCoordinate;
    }

    /**
     * Set the y-coordinate to the desired position
     *
     * @param yCoordinate   The y-coordainte to move this Position to
     */
    public void setYPosition( int yCoordinate ) {
        yPosition = yCoordinate;
    }
}
