package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This abstract class represents the commonalities and shared functionalities among all the Marble
 * Solitaire games. This class currently supports the English Solitaire Model, European Solitaire
 * Model and Triangle Solitaire Model (Further game details for each specific board is written in
 * their respective classes). All these games follow the same rules and objectives as the
 * aim of all these variants is to remove all the marbles by jumping over marbles into empty slots
 * to remove the marble in the middle that's jumped over. The game is won when only one marble is
 * left on the board.
 */
public abstract class SolitaireModel implements MarbleSolitaireModel {
  protected final int dimension;
  protected final int boardSize;
  protected int currentScore;
  protected final int sRow;
  protected final int sCol;
  protected List<List<SlotState>> board;

  /**
   * This constructor represents the abstract constructor for all variants of the peg solitaire
   * game. It allows for the dimension which dictates the size of each board and the position
   * of the empty slot in the beginning of the game to be changed given a row number and column
   * number.
   *
   * @param dimension board's size
   * @param sRow      row number of empty starting slot
   * @param sCol      column number of empty starting slot
   * @throws IllegalArgumentException if dimension or position of empty slot is invalid
   */
  public SolitaireModel(int dimension, int sRow, int sCol) {
    if (this.isInvalidDimension(dimension)) {
      throw new IllegalArgumentException("Dimension supplied is an invalid value.");
    }
    this.dimension = dimension;
    this.boardSize = this.getBoardSize();
    if (this.isInvalidPosition(sRow, sCol) || this.isInValidRange(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + Integer.toString(sRow)
              + "," + Integer.toString(sCol) + ")");
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = this.makeBoard();
    this.currentScore = this.getScore();
  }

  /**
   * This constructor represents the default constructor where the board's dimension is initialized
   * to 3 and the position of the starting empty slot is in the middle of the board at (3,3).
   */
  public SolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * This constructor allows for the user to customize the position of the starting empty slot
   * given a row number and column number. The board size or dimension of the board remains
   * at the default which is 3.
   *
   * @param sRow row number of empty starting slot
   * @param sCol column number of empty starting slot
   * @throws IllegalArgumentException if row or column number are invalid positions or out of
   *                                  range for the default board size
   */
  public SolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * This constructor allows for the user to change the dimension of the board and the row and
   * column position of the empty slot is set to a default position as it's always set to the
   * middle of the board.
   *
   * @param dimension board's size
   * @throws IllegalArgumentException if dimension is invalid
   */
  public SolitaireModel(int dimension) {
    this(dimension, (int) Math.floor(1.5 * dimension - 1), (int) Math.floor(1.5 * dimension - 1));
  }

  /**
   * Creates a 2D Arraylist of Slot state for the board. Places the one empty slot based on the
   * sCol and sRow. Places the invalids based on the whether the isInvalidPosition returns true.
   * Places a marble for everywhere else not already filled.
   *
   * @return a board of appropriate slot states
   */

  protected List<List<SlotState>> makeBoard() {
    List<List<SlotState>> resultBoard = new ArrayList<>();
    for (int i = 0; i < this.boardSize; i++) {
      ArrayList<SlotState> row = new ArrayList<SlotState>();
      for (int j = 0; j < this.boardSize; j++) {
        if (j == sCol && i == sRow) {
          row.add(SlotState.Empty);
        } else if (isInvalidPosition(i, j)) {
          row.add(SlotState.Invalid);
        } else {
          row.add(SlotState.Marble);
        }
      }
      resultBoard.add(row);
    }
    return resultBoard;
  }

  /**
   * Determines if position is an invalid position based on the arm thickness and board size.
   * Invalid positions are changed for each variant of the peg solitaire model, so further
   * implementation details are located in each class.
   *
   * @param sRow the row number of the slot
   * @param sCol the column number of the slot
   * @return true if invalid position, false otherwise
   */
  protected abstract boolean isInvalidPosition(int sRow, int sCol);


  /**
   * Determines if the dimension or board size is invalid. The dimension supplied is valid when
   * the number is odd and greater than 3.
   * @param dimension board's size
   * @return true if dimension meets the criteria
   */
  protected boolean isInvalidDimension(int dimension) {
    return dimension < 3 || dimension % 2 == 0;
  }

  /**
   * Returns the current score of the game by adding 1 to the score for every marble on the board
   * currently.
   * @return the current score
   */
  public int getScore() {
    int currentScore = 0;
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        if (this.getSlotAt(j, i).equals(SlotState.Marble)) {
          currentScore = currentScore + 1;
        }
      }
    }
    return currentScore;
  }

  /**
   * Performs a move based on the supplied inputs. Moves can be made if the from slot is a marble
   * and the to slot is empty. Additionally, slots have to be 2 away for each other and the move
   * can only be made if the middle slot is a marble. Any slots mentioned have to also be not
   * invalid slots. Each time a valid move is made, the from marble and middle marble becomes
   * empty and the to becomes a marble.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if move is invalid based on conditions mentioned above
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    if (!canBeMoved(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Move cannot be made.");
    }
    if (this.hasDiagonalMiddleMarble(fromRow, fromCol, toRow, toCol)) {
      this.updateDiagonalMiddleMarble(fromRow, fromCol, toRow, toCol);
    } else {
      this.updateMiddleSlot(fromRow, fromCol, toRow, toCol);
    }
    this.board.get(fromRow).set(fromCol, SlotState.Empty);
    this.board.get(toRow).set(toCol, SlotState.Marble);
  }

  /**
   * Determines whether a move can be made given the following conditions.
   * All inputs have to be within the valid range and not invalid positions on the board.
   * The from slot has to be a marble and the to slot has to be empty.
   * The distance has to be equal to 2.
   * A middle marble needs to be present between the from and the to slot.
   *
   * @param fromRow the row number of the from slot
   * @param fromCol the column number of the from slot
   * @param toRow   the row number of the to slot
   * @param toCol   the column number of the to slot
   * @return true if all the conditions are met and a valid move can be made, false otherwise
   */
  protected boolean canBeMoved(int fromRow, int fromCol, int toRow, int toCol) {
    boolean validPositions = (!this.isInValidRange(fromRow, fromCol)
            && !this.isInValidRange(toRow, toCol)
            && this.getSlotAt(fromRow, fromCol).equals(SlotState.Marble)
            && this.getSlotAt(toRow, toCol).equals(SlotState.Empty)
            && this.getDistance(fromRow, fromCol, toRow, toCol) == 2);
    return validPositions && (this.hasMiddleMarble(fromRow, fromCol, toRow, toCol)
            || this.hasDiagonalMiddleMarble(fromRow, fromCol, toRow, toCol));
  }

  /**
   * Determines if there is a middle marble if the move inputs are diagonal. Returns false for the
   * abstract class as the majority of variants don't allow for diagonal moves.
   * @param fromRow the row number of the from slot
   * @param fromCol the column number of the from slot
   * @param toRow   the row number of the to slot
   * @param toCol   the column number of the to slot
   * @return true if diagonal moves are allowed and there is a middle marble
   */
  protected boolean hasDiagonalMiddleMarble(int fromRow, int fromCol, int toRow, int toCol) {
    return false;
  }

  /**
   * Determines if there is a middle marble present between the from slot and to slot.
   * This is only when the inputs are for horizontal and vertical moves.
   *
   * @param fromRow the row number of the from slot
   * @param fromCol the column number of the from slot
   * @param toRow   the row number of the to slot
   * @param toCol   the column number of the to slot
   * @return true if there's a middle marble, false otherwise
   */
  protected boolean hasMiddleMarble(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromCol == toCol) {
      if (fromRow > toRow) {
        return this.getSlotAt(fromRow - 1, fromCol).equals(SlotState.Marble);
      } else {
        return this.getSlotAt(fromRow + 1, fromCol).equals(SlotState.Marble);
      }
    } else if (fromRow == toRow) {
      if (fromCol > toCol) {
        return this.getSlotAt(fromRow, fromCol - 1).equals(SlotState.Marble);
      } else {
        return this.getSlotAt(fromRow, fromCol + 1).equals(SlotState.Marble);
      }
    } else {
      return false;
    }
  }

  /**
   * Changes the middle position of the board to an empty slot when a valid move is made.
   * This is exclusively for moves that are made horizontally or vertically.
   *
   * @param fromRow the row number of the from slot
   * @param fromCol the column number of the from slot
   * @param toRow   the row number of the to slot
   * @param toCol   the column number of the to slot
   */
  protected void updateMiddleSlot(int fromRow, int fromCol, int toRow, int toCol) {
    if (toCol > fromCol) {
      this.board.get(fromRow).set(fromCol + 1, SlotState.Empty);
    } else if (toCol < fromCol) {
      this.board.get(fromRow).set(fromCol - 1, SlotState.Empty);
    } else if (toRow > fromRow) {
      this.board.get(fromRow + 1).set(fromCol, SlotState.Empty);
    } else {
      this.board.get(fromRow - 1).set(fromCol, SlotState.Empty);
    }
  }

  /**
   * Changes the middle marble to an empty slot when diagonal moves are performed.
   * For the majority of variants supported by this class, no diagonal moves are allowed, so does
   * nothing. But for variants that allow for diagonal moves and that move is valid,
   * the middle marble is removed.
   * @param fromRow the row number of the from slot
   * @param fromCol the column number of the from slot
   * @param toRow   the row number of the to slot
   * @param toCol   the column number of the to slot
   */
  protected void updateDiagonalMiddleMarble(int fromRow, int fromCol, int toRow, int toCol) {
    return;
  }

  /**
   * Determines if the position of the slot states are positive and less than the boardSize - 1.
   * If the position is within the valid range, this slot position returns true and is valid.
   *
   * @param row the row number of the slot
   * @param col the column number of the slot
   * @return true if the position of the slot is within the range, false otherwise
   */

  protected boolean isInValidRange(int row, int col) {
    return row < 0 || col < 0 || row > this.boardSize - 1 || col > this.boardSize - 1;
  }

  /**
   * Calculates the distance between the two points by using the distance formula.
   *
   * @param fromRow the row number of the from slot
   * @param fromCol the column number of the from slot
   * @param toRow   the row number of the to slot
   * @param toCol   the column number of the to slot
   * @return the distance between the from and to positions as an int
   */
  protected int getDistance(int fromRow, int fromCol, int toRow, int toCol) {
    return (int) Math.sqrt(Math.pow(toRow - fromRow, 2) + Math.pow(toCol - fromCol, 2));
  }

  @Override
  public SlotState getSlotAt(int row, int col) {
    if (this.isInValidRange(row, col)) {
      throw new IllegalArgumentException("Row and/or column is invalid.");
    }
    return this.board.get(row).get(col);
  }

  @Override
  public int getBoardSize() {
    return 2 * this.dimension + (this.dimension - 2);
  }

  /**
   * Determines if there are any more diagonal moves that can be made on the board.
   * For the majority of variants of this game, no diagonal moves are allowed so this returns false.
   * However, if diagonal moves are allowed for a variant, this method will determine if any more
   * diagonal moves can be played
   * @return true if diagonal moves are allowed and there are more valid diagonal moves
   */
  protected boolean noMoreDiagonalMoves() {
    return false;
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        if (this.canBeMoved(j, i, j + 2, i) || this.canBeMoved(j, i, j, i + 2) ||
                this.canBeMoved(j, i, j - 2, i) || this.canBeMoved(j, i, j, i - 2) ||
                this.noMoreDiagonalMoves()) {
          return false;
        }
      }
    }
    return true;
  }
}



