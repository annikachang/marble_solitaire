package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This abstract class represents a view for all the variant of the Peg Solitaire game that this
 * program currently supports (English Solitaire Model, European Solitaire Model and
 * Triangle Solitaire Model).
 * It supports the ability to show the board and the current state of it and any messages wanted.
 */
public abstract class SolitaireView implements MarbleSolitaireView {
  MarbleSolitaireModelState model;
  Appendable resultView;

  /**
   * This constructor takes in a model and a result view and initializes both.
   * @param model the model
   * @param resultView the output of what's shown by the user
   * @throws IllegalArgumentException if one or more of the inputs are null
   */
  public SolitaireView(MarbleSolitaireModelState model, Appendable resultView) {
    if (model == null || resultView == null) {
      throw new IllegalArgumentException("Model or view supplied is invalid.");
    }
    this.model = model;
    this.resultView = resultView;
  }

  /**
   * Represents a constructor that takes in a model. Initializes the result view or appendable
   * to System.out.
   *
   * @param model the state of the model
   * @throws IllegalArgumentException if model is null
   */
  public SolitaireView(MarbleSolitaireModelState model) {
    this(model, System.out);
  }

  public abstract String toString();

  public void renderBoard() throws IOException {
    this.resultView.append(this.toString());
  }

  public void renderMessage(String message) throws IOException {
    this.resultView.append(message);
  }
}
