package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents a variant of the Peg Solitaire game where the board is an equilateral
 * triangle. The objectives of this variant are the exact same as the objectives described
 * in the abstract class, but the difference is that in the triangle variant,
 * diagonal moves are allowed.
 * Additionally, this variant is different from the European or English version, as the coordinate
 * system is not a cartesian coordinate system. The coordinate system starts from the top row at
 * (0,0) and moving downwards diagonally, the row increases by 1 and column increases by 1 as you
 * move to the right.
 * Thus, given this coordinate system, vertical moves are not allowed, but horizontal moves
 * (left or right) and diagonally moves (upwards or downwards) are allowed.
 */
public class TriangleSolitaireModel extends SolitaireModel {

  /**
   * This constructor represents the default constructor where the dimension is initialized to 5.
   * The dimension for this model is the last row of marbles or the row with the greatest number of
   * marbles. The default position of the empty starting slot is initialized to (0,0).
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }


  /**
   * This constructor allows for the dimension to be changed. Valid dimensions are positive
   * (not including 0).
   * The row and column of the empty slot is set to (0,0) and not the middle of the board like
   * the other variants.
   *
   * @param dimension board's size
   * @throws IllegalArgumentException if the dimension is invalid
   */
  public TriangleSolitaireModel(int dimension) {
    super(dimension, 0, 0);
  }


  /**
   * This constructor allows the position of the empty slot to be changed.
   * The dimension is initialized to the default for the triangle solitaire game which is 5.
   *
   * @param row row number of empty starting slot
   * @param col column number of the empty starting slot
   * @throws IllegalArgumentException if row or column number is invalid (invalid range for board
   *                                  or invalid position)
   */
  public TriangleSolitaireModel(int row, int col) {
    super(5, row, col);
  }


  /**
   * This constructor allows the user to change the dimension and position of the starting
   * empty slot.
   *
   * @param dimension board's size
   * @param row       row number of starting empty slot
   * @param col       column number of starting empty slot
   * @throws IllegalArgumentException if dimension is non-positive or row and column invalid
   *                                  (invalid range for board or invalid position)
   */
  public TriangleSolitaireModel(int dimension, int row, int col) {
    super(dimension, row, col);
  }


  /**
   * Returns true if a position is invalid. This occurs when sCol or sRow is greater than the board
   * size. For the top of the board, the first row has the most invalids and the amount of invalids
   * decreasing as the row number decreases. Thus, whether the sRow is invalid is dependent on the
   * column number that's supplied or vice versa.
   *
   * @param sRow the row number of the slot
   * @param sCol the column number of the slot
   * @return true if invalid positions
   */
  @Override
  protected boolean isInvalidPosition(int sRow, int sCol) {
    if (sCol < this.boardSize) {
      int convertedCol = this.boardSize - sCol;
      return sRow < this.boardSize - convertedCol;
    }
    return false;
  }

  /**
   * Determines if the given dimension is invalid. Dimension is invalid if non-positive.
   *
   * @param dimension board's size
   * @return true if dimension is invalid
   */
  @Override
  protected boolean isInvalidDimension(int dimension) {
    return dimension <= 0;
  }

  /**
   * Returns the board size which is the dimension (dimension is the row with the greatest amount
   * of marbles which is located at the bottom most row).
   *
   * @return board size
   */
  @Override
  public int getBoardSize() {
    return this.dimension;
  }


  /**
   * Determines if there is a middle marble present between the from and to row if the move
   * performed is in the diagonal direction.
   *
   * @param fromRow the row number of the from slot
   * @param fromCol the column number of the from slot
   * @param toRow   the row number of the to slot
   * @param toCol   the column number of the to slot
   * @return true if the middle slot has a middle marble
   */
  @Override
  protected boolean hasDiagonalMiddleMarble(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow - toRow == 2 && fromCol - toCol == 2) {
      return this.getSlotAt(fromRow - 1, fromCol - 1).equals(SlotState.Marble);
    } else if (toRow - fromRow == 2 && toCol - fromCol == 2) {
      return this.getSlotAt(fromRow + 1, fromCol + 1).equals(SlotState.Marble);
    } else {
      return false;
    }
  }

  /**
   * Changes the middle marble to an empty slot if a diagonal move is made.
   *
   * @param fromRow the row number of the from slot
   * @param fromCol the column number of the from slot
   * @param toRow   the row number of the to slot
   * @param toCol   the column number of the to slot
   */
  @Override
  protected void updateDiagonalMiddleMarble(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow - toRow == 2 && fromCol - toCol == 2) {
      this.board.get(fromRow - 1).set(fromCol - 1, SlotState.Empty);
    } else if (toRow - fromRow == 2 && toCol - fromCol == 2) {
      this.board.get(fromRow + 1).set(fromCol + 1, SlotState.Empty);
    }
  }

  /**
   * Determines if there are any more valid diagonal moves can be made by going
   * through the entire board.
   *
   * @return true if there are more valid diagonal moves, false otherwise
   */
  @Override
  protected boolean noMoreDiagonalMoves() {
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        if (this.canBeMoved(j, i, j + 2, i + 2)
                || this.canBeMoved(j, i, j - 2, i - 2)) {
          return true;
        }
      }
    }
    return false;
  }


}

