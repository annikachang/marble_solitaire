
package cs3500.marblesolitaire.view;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;


/**
 * This class represents a view for the traditional English Solitaire Model and European Solitaire
 * Model. It supports the ability to show the board and the current state of it and any messages
 * wanted.
 */
public class MarbleSolitaireTextView extends SolitaireView {

  /**
   * Represents a constructor that takes in a model. Initializes the result view or appendable
   * to System.out.
   *
   * @param model the state of the model
   * @throws IllegalArgumentException if model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  /**
   * This constructor takes in a model and a result view and initializes both.
   *
   * @param model      the model
   * @param resultView the output of what's shown by the user
   * @throws IllegalArgumentException if one or more of the inputs are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable resultView) {
    super(model, resultView);
  }

  /**
   * Returns a string of the current state of the board. Only invalids that are on the left side
   * of the board are represented with a space.
   * @return the game state as a string
   */
  @Override
  public String toString() {
    ArrayList<String> rows = new ArrayList<String>();
    for (int i = 0; i < model.getBoardSize(); i++) {
      ArrayList<String> rowString = new ArrayList<String>();
      for (int j = 0; j < model.getBoardSize(); j++) {
        if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          rowString.add("O");
        } else if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          rowString.add("_");
        } else if ((model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Invalid)) &&
                (j < ((model.getBoardSize() - (((model.getBoardSize() + 2) * 1 / 3) - 1)) - 1))) {
          rowString.add(" ");
        }
      }
      rows.add(String.join(" ", rowString));
    }
    return String.join("\n", rows);
  }

}
