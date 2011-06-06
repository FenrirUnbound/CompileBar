ReadMe
======

Overview
--------
Given an empty chessboard, how many moves will it take for a single
Knight-piece to visit every square at least once?

Self-Answer
-----------
Assumptions made:
1. The chessboard is a standard 8x8
2. The Knight-piece is positioned in one of the corners of the chessboard

Given the assumptions, the Knight-piece will take 63 moves (64 if you include
placing it on the board as 1) in order to traverse the board without revisiting 
a single square, as this program will depict.

Usage
-----
1. Compile the "Play.java" file

    - localhost$ javac Play.java

2. Run it either with the Knight-piece defaulted to the top-left corner

    - localhost$ java Play

3. Watch the Knight-piece traverse with each printed board, until it can't
 move any further without revisiting a square.

4. Alternatively, you can start the Knight-piece on a non-default space.

    - localhost$ java Play x_coordinate y_coordinate

Summary
-------
The "Constants" class holds all the global constants, such as the chessboard
dimensions.

The 'Knight' class possess all the functions of a Knight-piece. It tells what
legal moves it can make at its current position, or any position on the board.
It's also able to tell where it's located in the 2D space of the chessboard.

The 'Play' class handles all the user interactions with the program. Since it's
command line-based, it limits what the user can do (although it won't affect
the functionality of how this puzzle is solved). From the start of the 
puzzle-solving, it prints the board as it evolves, showing how the
Knight-piece travels through it while avoiding to revisit spaces.

The 'Position' class simply encapsulates a single set of 2D Cartesian
coordinates.

The 'Solver' class is the brains behind solving the puzzle. In short, it takes
the movelist of the Knight-piece and weighs each move by how many more moves
they can provide. Ties are assumed to be similar and the first coordinates we
come across will be accepted first. In its current state, if it runs into a 
dead-end (i.e., surrounds itself with previously visited spaces without first
visiting the entire board), the program won't try to continue on; it will
unfortunately give up.

