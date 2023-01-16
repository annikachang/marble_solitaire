package cs3500.marblesolitaire.view;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents a view for the traditional Triangle Solitaire Model.
 * It supports the ability to show the board and the current state of it and any messages
 * wanted.
 */
public class TriangleSolitaireTextView extends SolitaireView {

  /**
   * Represents a constructor that takes in a model. Initializes the result view or appendable
   * to System.out.
   *
   * @param model the state of the model
   * @throws IllegalArgumentException if model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  /**
   * This constructor takes in a model and a result view and initializes both.
   *
   * @param model      the model
   * @param resultView the output of what's shown by the user
   * @throws IllegalArgumentException if one or more of the inputs are null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable resultView) {
    super(model, resultView);
  }

  /**
   * Returns a string for the current board state. Invalids are not represented with a space
   * for the view as the last slot cannot be a space. Rows or marbles and/or empty slots which are
   * represented by "O"s and "-" respectively are shifted to the right with some number of spaces
   * so that an equilateral triangle can be formed in the view.
   *
   * @return the game state as a string
   */
  @Override
  public String toString() {
    ArrayList<String> rows = new ArrayList<String>();
    for (int i = 0; i < model.getBoardSize(); i++) {
      ArrayList<String> rowString = new ArrayList<String>();
      for (int j = 0; j < model.getBoardSize(); j++) {
        if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          if (j == 0) {
            rowString.add(" ".repeat(model.getBoardSize() - 1 - i) + "O");
          } else {
            rowString.add("O");
          }
        } else if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          if (j == 0) {
            rowString.add(" ".repeat(model.getBoardSize() - 1 - i) + "_");
          } else {
            rowString.add("_");
          }
        }
      }
      rows.add(String.join(" ", rowString));
    }
    return String.join("\n", rows);
  }

}
