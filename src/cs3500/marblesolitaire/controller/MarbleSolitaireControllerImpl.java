package cs3500.marblesolitaire.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents a controller for the marble solitaire game and allows for the user to
 * make moves. Thus, it's able to receive inputs through the Readable and transmits these inputs
 * to the model and then also to the view where the board and score is updated due a performed move.
 * Additionally, only inputs that are valid like positive numbers and "q" or "Q" are sent to the
 * model.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable in;
  private boolean hasQBeenPressed;

  /**
   * This constructor takes in a model, view and input or readable. If any of these inputs are
   * null, an exception is thrown. If none are null, constructor initializes these inputs.
   * Also, the hasQBeenPressed field is initialized to false in the beginning of the game and will
   * be changed to true if "q" or "Q" has been pressed.
   *
   * @param model represents the model where inputs received by the controller is sent
   * @param view  represents the view where the inputs received and processed by the model are
   *              shown
   * @param in    represents the inputs that the user supplies
   * @throws IllegalArgumentException if any inputs supplied are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable in) {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Model, view and/or in is invalid.");
    }
    this.model = model;
    this.view = view;
    this.in = in;
    this.hasQBeenPressed = false;
  }

  /**
   * This method is a void method that takes in inputs from the user. If the game is not over or
   * q has not been pressed, the view is updated to have the current board and score. Valid inputs
   * are sent to the model and then this process is repeated until game is over or user inputs
   * quit.
   * If the user inputs quit before game ends, the view will be updated to include a quit screen
   * which includes the "Game quit!" message, the current state of the board before quit, and the
   * current score.
   * If the game ends and there are no more valid moves, then the view will be updated to include
   * a game over screen, which includes the "Game over!" message, the current state of the board
   * and the end score.
   *
   * @throws IllegalStateException if the output is not transmittable meaning the render board
   *                               or message in the view throws an IOException
   */
  @Override
  public void playGame() {
    Scanner scan = new Scanner(this.in);
    try {
      while (!this.model.isGameOver() && !this.hasQBeenPressed) {
        // Using the view, render the current state of the game and calls render message to place
        // an entered slash.
        this.view.renderBoard();
        this.view.renderMessage("\n");
        // Using the view, transmit "Score: N" , replacing N with the actual score.
        this.view.renderMessage("Score: " + this.model.getScore() + "\n");
        // creates a list of valid inputs
        ArrayList<String> validInputs = this.createInputList(scan);
        // when the input list size has enough inputs for move (4), sends the valid inputs to
        // move
        if (validInputs.size() == 4) {
          try {
            this.model.move(Integer.parseInt(validInputs.get(0)) - 1,
                    Integer.parseInt(validInputs.get(1)) - 1,
                    Integer.parseInt(validInputs.get(2)) - 1,
                    Integer.parseInt(validInputs.get(3)) - 1);
            // if move cannot be made, sends a message to the view informing the user
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("Invalid move. Play again.\n");
          }
        }
      }
      // sends the view the appropriate messages if quit in an input
      if (this.hasQBeenPressed) {
        this.view.renderMessage("Game quit!\n");
        this.view.renderMessage("State of game when quit:\n");
        this.view.renderBoard();
        this.view.renderMessage("\n");
        this.view.renderMessage("Score: " + this.model.getScore());
      }
      // sends the view the appropriate messages if game is over
      if (this.model.isGameOver()) {
        this.view.renderMessage("Game over!\n");
        this.view.renderBoard();
        this.view.renderMessage("\n");
        this.view.renderMessage("Score: " + this.model.getScore());
      }
    } catch (IOException e) {
      throw new IllegalStateException("Output is not transmittable.");
    }
  }

  /**
   * Determines if the supplied scanner input is a valid input. A valid input is represented by any
   * input that's a positive number (not 0) and "q" or "Q" (for game quit).
   * Controller tells the view to send a message to the user if the input is invalid.
   *
   * @param input the String input provided by the user
   * @return true if the String input is valid and meets the condition described above
   * @throws IllegalStateException if the output is not transmittable meaning the render board
   *                               or message in the view throws an IOException
   */
  private boolean isValidInput(String input) {
    try {
      try {
        // returns true if String input is q case-insensitive or a positive number
        if (input.equalsIgnoreCase("q") || Integer.parseInt(input) > 0) {
          return true;
        }
        // if input is negative, view renders a re-enter message
        if (Integer.parseInt(input) <= 0) {
          this.view.renderMessage("Please re-enter that move again.\n");
        }
        // if input is not a number in a String form and not q, view renders a re-enter message
      } catch (NumberFormatException e) {
        this.view.renderMessage("Please re-enter that move again.\n");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Output is not transmittable.");
    }
    return false;
  }


  /**
   * Creates the list of valid inputs by filtering through the String inputs from the scanner
   * which are chained together by either spaces or enters. If "q" or "Q" is an input,
   * hasQBeenPressed is changed to true.
   *
   * @param scan Scanner in the form of a String where the user input can be read
   * @return an ArrayList of valid inputs equal to or less than 4
   */
  private ArrayList<String> createInputList(Scanner scan) {
    ArrayList<String> validInputs = new ArrayList<String>();
    // adds valid inputs to the arraylist if size is less than 4
    while (validInputs.size() < 4) {
      // throws an illegal state exception if scanner has not next
      if (!scan.hasNext()) {
        throw new IllegalStateException("Invalid readable or inputs.");
      } else if (scan.hasNext()) {
        String input = scan.next();
        // determines if the input is valid
        if (this.isValidInput(input)) {
          // if input is q or Q, set hasQBeenPressed to true
          if (input.equalsIgnoreCase("q")) {
            this.hasQBeenPressed = true;
            break;
          } else {
            // else if input is a number and positive, then add it to the list
            validInputs.add(input);
          }
        }
      }
    }
    return validInputs;
  }

}





