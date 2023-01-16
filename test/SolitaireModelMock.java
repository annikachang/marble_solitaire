import java.util.Objects;

import cs3500.marblesolitaire.model.hw04.SolitaireModel;

/**
 * This class represents a mock class that extends Solitaire model. For testing purposes only.
 */
public class SolitaireModelMock extends SolitaireModel {
  final StringBuilder log;

  /**
   * This constructor takes in a log and records all inputs from the controller
   * specifically inputted into move.
   *
   * @param log StringBuilder which appends the inputs received by the model from the controller
   */
  public SolitaireModelMock(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }


  /**
   * Appends the inputs (fromRow, fromCol, toRow and toCol) received by the controller and sent
   * to the model. Log denotes which specific input it is by stating what input is for each row
   * or col.
   *
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
   * Always returns false as this class is just used for testing that the controller sends
   * correct inputs to the model.
   *
   * @param sRow row number of empty slot
   * @param sCol column number of empty slot
   * @return false regardless
   */
  @Override
  public boolean isInvalidPosition(int sRow, int sCol) {
    return false;
  }

}

