package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * This class represents the entry point of the program for the Peg Solitaire Game. Thus, it stores
 * the main method for the program and the user can specify the different variants of the board
 * that the program currently supports in the main's inputs. This game currently support an English,
 * European and Triangular version of the game. Additionally, after a board is selected, the user
 * can customize the desired size and spot of the empty slot in the beginning of game.
 */
public final class MarbleSolitaire {

  /**
   * This main method first initializes the model, view and controller to null and the readable
   * which allows for user input to an input stream reader. If the first String in the supplied
   * String array matches one of the board versions supported, the size, position of the empty slot
   * will be set to the default. Choices for the board choice are "english", "european" and
   * "triangular".
   * The user can then decide to customize the size and position of the empty slot by writing -size
   * followed by the desired number or -hole followed by the row then column of the desired empty
   * slot position. If only -size is customized and not -hole, the hole will be automatically
   * set to the center of the board.
   * Assuming all the inputs supplied are valid for this specific board, the controller will be
   * initialized so that the user can provide inputs and play the game.
   *
   * @param args characteristics of the board that will change the board accordingly
   * @throws IllegalArgumentException if none of the inputs match the board versions that are
   *                                  currently supported
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = null;
    MarbleSolitaireView view = null;
    Readable rd = new InputStreamReader(System.in);
    MarbleSolitaireController controller = null;
    switch (args[0]) {
      case "english":
        int[] valueEng = MarbleSolitaire.getBoardValues(3, 3, 3,
                args, args[0]);
        model = new EnglishSolitaireModel(valueEng[0], valueEng[1], valueEng[2]);
        view = new MarbleSolitaireTextView(model);
        break;
      case "european":
        int[] valueEur = MarbleSolitaire.getBoardValues(3, 3, 3,
                args, args[0]);
        model = new EuropeanSolitaireModel(valueEur[0], valueEur[1], valueEur[2]);
        view = new MarbleSolitaireTextView(model);
        break;
      case "triangular":
        int[] valueTri = MarbleSolitaire.getBoardValues(5, 0, 0,
                args, args[0]);
        model = new TriangleSolitaireModel(valueTri[0], valueTri[1], valueTri[2]);
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        throw new IllegalArgumentException("Invalid peg solitaire board.");
    }
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
  }

  /**
   * Creates an array of size 3 with the desired size, row number of empty slot, and column number
   * of empty slot in that order specifically. If the -size or -hole is not inputted by the user,
   * default values for each board will be assumed and given to the created array.
   *
   * @param defaultSize default size of the board
   * @param defaultRow  default row number of the starting empty cell
   * @param defaultCol  default column number of the starting empty cell
   * @param args        list of board changes inputted by the user in the main
   * @param boardChoice the type of Marble Solitaire game wanted in a String form
   * @return an array of ints with size, row of empty slot and column of empty slot
   */
  private static int[] getBoardValues(int defaultSize, int defaultRow, int defaultCol,
                                      String[] args, String boardChoice) {
    int[] result = {defaultSize, defaultRow, defaultCol};
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-size") && boardChoice.equals("triangular")) {
        int dimension = Integer.parseInt(args[i + 1]);
        result[0] = dimension;
        result[1] = 0;
        result[2] = 0;
      } else if (args[i].equals("-size")) {
        int dimension = Integer.parseInt(args[i + 1]);
        result[0] = dimension;
        result[1] = (int) Math.floor(3 / 2 * dimension - 1);
        result[2] = (int) Math.floor(3 / 2 * dimension - 1);
      } else if (args[i].equals("-hole")) {
        result[1] = Integer.parseInt(args[i + 1]);
        result[2] = Integer.parseInt(args[i + 2]);
      }
    }
    return result;
  }
}

