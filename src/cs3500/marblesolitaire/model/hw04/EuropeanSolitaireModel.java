package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents a variant of the Peg Solitaire game known as the European Solitaire Model.
 * The objectives for this game is summarized in the abstract class and is very similar to the
 * English Solitaire game.
 * This model is different in that the board is an octagonal board.
 * The dimension that dictates the size of the board is side length, and it represents the amount
 * of marbles in the first row.
 * The board is goes by a regular Cartesian coordinate system.
 */
public class EuropeanSolitaireModel extends SolitaireModel {

  /**
   * This constructor is the default constructor where the side length is set to 3 and the
   * position of the empty starting cell is (3,3).
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * This constructor allows side length to be changed and the position of the empty slot is
   * automatically set to the center of the board.
   *
   * @param sideLength board's size
   * @throws IllegalArgumentException if side length is even or less than 3
   */
  public EuropeanSolitaireModel(int sideLength) {
    super(sideLength);
  }

  /**
   * This constructor allows for the position of the starting empty slot to be changed and the
   * side length is set to the default size which is 3.
   *
   * @param sRow row number of the starting empty slot
   * @param sCol column number of the starting empty slot
   * @throws IllegalArgumentException if row or column is invalid (invalid range for board or
   *                                  invalid position)
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * This constructor allows for the side length and position of the empty starting lot to be
   * changed.
   *
   * @param sideLength board's size
   * @param sRow       row number of the starting empty slot
   * @param sCol       column number of starting empty slot
   * @throws IllegalArgumentException if side length is even or less than 3 or
   *                                  row or column is invalid (invalid range for board or
   *                                  invalid position)
   */
  public EuropeanSolitaireModel(int sideLength, int sRow, int sCol) {
    super(sideLength, sRow, sCol);

  }

  /**
   * Determines if a position is invalid. For the left side of the board, both sCol and sRow
   * have to be less than the side length - 1. Additionally, sRow and sCol can't be greater than
   * the board size - side length. The calculations for sRow are also dependent on sCol.
   *
   * @param sRow the row number of the slot
   * @param sCol the column number of the slot
   * @return
   */
  @Override
  protected boolean isInvalidPosition(int sRow, int sCol) {
    boolean isValidRight = false;
    boolean isValidLeft = false;
    // for the left side of board
    if (sCol < this.dimension - 1) {
      isValidLeft = sRow < this.dimension - 1 - sCol || sRow > this.boardSize -
              this.dimension + sCol;
    }
    // for right side of board
    if (sCol > this.boardSize - this.dimension) {
      int convertedCol = this.boardSize - 1 - sCol;
      isValidRight = sRow < this.dimension - 1 - convertedCol || sRow > this.boardSize -
              this.dimension + convertedCol;
    }
    return isValidRight || isValidLeft;
  }
}

