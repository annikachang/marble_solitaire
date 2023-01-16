import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents a mock for the english solitaire model class for solely testing purposes.
 * It implements the same methods and most are stub methods except the move which returns a log
 * of the inputs supplied by the controller to check if the inputs to the model are supplied
 * correctly.
 */
public class EnglishSolitaireModelMock implements MarbleSolitaireModel {
  final StringBuilder log;

  /**
   * This constructor takes in a log and records all inputs from the controller
   * specifically inputted into move.
   * @param log StringBuilder which appends the inputs received by the model from the controller
   */
  public EnglishSolitaireModelMock(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * Appends the inputs (fromRow, fromCol, toRow and toCol) received by the controller and sent
   * to the model. Log denotes which specific input it is by stating what input is for each row
   * or col.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException when move is unable to be made
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(String.format("fromRow = %d, fromCol = %d, toRow = %d, toCol = %d\n",
            fromRow, fromCol, toRow, toCol));
  }

  /**
   * Always returns false for game over.
   * @return false no matter what
   */
  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Always returns 0 for board size regardless of actual board size.
   * @return 0
   */
  @Override
  public int getBoardSize() {
    return 0;
  }

  /**
   * Always returns null for the slot state regardless of the inputs provided.
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return null
   * @throws IllegalArgumentException if slot inputs are outside the scope of the board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  /**
   * Always returns 0 for the score regardless of state of the game.
   * @return 0
   */
  @Override
  public int getScore() {
    return 0;
  }
}
