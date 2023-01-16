package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.SolitaireModel;

/**
 * This class represents a traditional English model board for Peg Solitaire with four arms of pegs.
 * The objectives for this game is summarized in the abstract class and is very similar to the
 * European Solitaire game.
 * The dimension that dictates the board size is arm thickens for the English model. Arm thickness
 * is the width or height (in terms of marbles) of one of the four arms.
 * The board is goes by a regular Cartesian coordinate system.
 */
public class EnglishSolitaireModel extends SolitaireModel {

  /**
   * Default constructor that takes in no fields and initializes the game to an arm thickness of 3.
   * This constructor represents a default game for specifically English Solitaire as the peg
   * size is 3, the empty slot is (3,3) and score starts at 32.
   */
  public EnglishSolitaireModel() {
    super();
  }

  /**
   * Constructor where center empty slot can be changed and the arm thickness is 3 which is the
   * default. Score, however, remains at 32 at the start of the game as it's based on the arm
   * thickness.
   *
   * @param sRow row number of the empty slot
   * @param sCol column number of the empty slot
   * @throws IllegalArgumentException if row or col or empty slot is in an invalid position
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * Constructor where arm thickness can be changed and the empty slot's position is centered
   * accordingly based on arm thickness. Score initially depends on arm thickness.
   *
   * @param armThickness width of each of the four arms
   * @throws IllegalArgumentException if arm thickness supplied is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness);
  }

  /**
   * Constructor where arm thickness and the position of the empty slot can be changed.
   * Score and board size is dependent on the changed arm thickness.
   *
   * @param armThickness width of each of the four arms
   * @param sRow         row number of the empty slot
   * @param sCol         column number of the empty slot
   * @throws IllegalArgumentException if arm thickness is not a positive odd number
   * @throws IllegalArgumentException if row or col or empty slot is in an invalid position
   */

  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  /**
   * Determines if position is an invalid position based on the arm thickness and board size.
   * For invalid positions on the left of the board, slot positions are invalid when less than arm
   * thickness - 1. For invalid positions on the right of the board, slot positions are invalid
   * when greater than board size - arm thickness.
   *
   * @param sRow the row number of the slot
   * @param sCol the column number of the slot
   * @return true if invalid position, false otherwise
   */

  protected boolean isInvalidPosition(int sRow, int sCol) {
    int startingBoundOfMarbles = this.dimension - 1;
    int endingBoundOfMarbles = this.boardSize - this.dimension;
    return (sCol < startingBoundOfMarbles || sCol > endingBoundOfMarbles) &&
            (sRow < startingBoundOfMarbles || sRow > endingBoundOfMarbles);
  }


}

