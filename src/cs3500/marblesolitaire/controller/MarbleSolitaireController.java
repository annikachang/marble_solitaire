package cs3500.marblesolitaire.controller;

/**
 * This interface represents the operations to be offered by the marble solitaire controller.
 * The operation supported allows input to be received and sent to the model or view to be
 * processed or shown respectively.
 */
public interface MarbleSolitaireController {

  /**
   * Takes in inputs supplied by the user and depending on the current state of the game
   * and the input supplied, sends the inputs to the model and the appropriate board and messages
   * to the view.
   */
  void playGame();
}
