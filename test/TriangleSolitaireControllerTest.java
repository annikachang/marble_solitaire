import org.junit.Before;
import org.junit.Test;


import java.io.StringReader;

import java.util.Arrays;


import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

/**
 * This class represents a testing class for the triangle marble solitaire controller.
 */
public class TriangleSolitaireControllerTest {
  private MarbleSolitaireModel defaultModel;
  private MarbleSolitaireView defaultView;
  private Appendable resultView;

  private MarbleSolitaireModel defaultModel2;
  private MarbleSolitaireView defaultView2;
  private Appendable resultView2;
  private MarbleSolitaireModel defaultModel3;
  private MarbleSolitaireView defaultView3;
  private Appendable resultView3;
  private MarbleSolitaireModel defaultModel4;
  private MarbleSolitaireView defaultView4;
  private Appendable resultView4;


  @Before
  public void testInit() {
    this.defaultModel = new TriangleSolitaireModel();
    this.resultView = new StringBuilder();
    this.defaultView = new TriangleSolitaireTextView(defaultModel, resultView);

    this.defaultModel2 = new TriangleSolitaireModel(3);
    this.resultView2 = new StringBuilder();
    this.defaultView2 = new TriangleSolitaireTextView(defaultModel2, resultView2);

    this.defaultModel3 = new TriangleSolitaireModel(4);
    this.resultView3 = new StringBuilder();
    this.defaultView3 = new TriangleSolitaireTextView(defaultModel3, resultView3);

    this.defaultModel4 = new TriangleSolitaireModel(7);
    this.resultView4 = new StringBuilder();
    this.defaultView4 = new TriangleSolitaireTextView(defaultModel4, resultView4);
  }


  // Tests for when the input in the controller constructor is null.
  @Test
  public void testNullInputsForConstructor() {
    try {
      new MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model, view and/or in is invalid.", e.getMessage());
    }
    try {
      Readable rd1 = new StringReader("3 3 1 1");
      new MarbleSolitaireControllerImpl(null, this.defaultView, rd1);
    } catch (IllegalArgumentException e) {
      assertEquals("Model, view and/or in is invalid.", e.getMessage());
    }
    try {
      Readable rd1 = new StringReader("3 1 1 1");
      new MarbleSolitaireControllerImpl(this.defaultModel, null, rd1);
    } catch (IllegalArgumentException e) {
      assertEquals("Model, view and/or in is invalid.", e.getMessage());
    }
  }

  // Tests that an illegal state exception is thrown when appendable or output is
  // not transmittable.
  @Test
  public void testPlayGameExceptionAppendable() {
    Readable inputs = new StringReader("3 3 1 1 q");
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable corruptedView = new TestingViewCorruptedClass();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, corruptedView);
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(model, view, inputs);
    try {
      controller1.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Output is not transmittable.",
              e.getMessage());
    }

    Readable inputs2 = new StringReader("-3 1 q");
    MarbleSolitaireModel model2 = new TriangleSolitaireModel();
    Appendable corruptedView2 = new TestingViewCorruptedClass();
    MarbleSolitaireView view2 = new TriangleSolitaireTextView(model2, corruptedView2);
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(model2, view2, inputs2);
    try {
      controller2.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Output is not transmittable.",
              e.getMessage());
    }
  }


  // Tests that an illegal state exception is thrown when readable is invalid.
  @Test
  public void testPlayGameExceptionReadable() {
    Readable corruptedInput = new TestingReadableCorruptedClass();
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, corruptedInput);
    try {
      controller1.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.",
              e.getMessage());
    }
  }

  // Tests that an exception is thrown when game is occurring and no more inputs are made.
  @Test
  public void testPlayGameNoMoves() {
    try {
      Readable noInputs = new StringReader("");
      MarbleSolitaireController controller1 = new
              MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, noInputs);
      controller1.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
    try {
      Readable oneInput = new StringReader("1");
      MarbleSolitaireController controller2 = new
              MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, oneInput);
      controller2.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
    try {
      Readable twoInputs = new StringReader("3 1");
      MarbleSolitaireController controller3 = new
              MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, twoInputs);
      controller3.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
    try {
      Readable threeInputs = new StringReader("3\n1\n1");
      MarbleSolitaireController controller4 = new
              MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, threeInputs);
      controller4.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
    try {
      Readable threeInputs = new StringReader("3 3\n1");
      MarbleSolitaireController controller4 = new
              MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, threeInputs);
      controller4.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
    try {
      Readable multipleInputs = new StringReader("3 1\n1 1 4 2\n2\n2");
      MarbleSolitaireController controller4 = new
              MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, multipleInputs);
      controller4.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
  }

  // Tests that when q or Q is the first input in game,
  // the screen displays accurately the Game quit screen.
  @Test
  public void testPlayGameQFirst() {
    Readable rd1 = new StringReader("q");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    // test the scene for the Game quit! screen
    assertEquals("Game quit!", this.resultView.toString().split("\n")[6]);
    assertEquals("State of game when quit:",
            this.resultView.toString().split("\n")[7]);
    assertEquals("Score: 14", this.resultView.toString().split("\n")[13]);
    String[] resultStringArr = this.resultView.toString().split("\n");
    String[] expectedArr = Arrays.copyOfRange(resultStringArr, 6, 14);
    assertEquals(
            "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O\n" +
                    "Score: 14", String.join("\n", expectedArr));

    Readable rd2 = new StringReader("Q");
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(this.defaultModel2,
            this.defaultView2, rd2);
    controller2.playGame();
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            4, 10);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "  _\n" +
            " O O\n" +
            "O O O\n" +
            "Score: 5", String.join("\n", expectedArr2));
  }

  // Tests for how the beginning of the board looks.
  @Test
  public void testBeginningGame() {
    // test for lower case q
    Readable rd1 = new StringReader("q");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            0, 6);
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14", String.join("\n", expectedArr));
    assertEquals(14, this.defaultModel.getScore());
    assertFalse(this.defaultModel.isGameOver());
  }

  // Tests for when q is pressed at different points of making the move.
  @Test
  public void testPlayGameQ() {
    // q is at fromCol
    Readable rd1 = new StringReader("3 q");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            6, 14);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14", String.join("\n", expectedArr));
    // q is at toCol
    Readable rd2 = new StringReader("3 1 1 q");
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(this.defaultModel2,
            this.defaultView2, rd2);
    controller2.playGame();
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            4, 10);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "  _\n" +
            " O O\n" +
            "O O O\n" +
            "Score: 5", String.join("\n", expectedArr2));
    // q is at fromRow
    Readable rd3 = new StringReader("3 3 1 1 q");
    MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(this.defaultModel3,
            this.defaultView3, rd3);
    controller3.playGame();
    String[] expectedArr3 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            10, 17);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "   O\n" +
            "  O _\n" +
            " O O _\n" +
            "O O O O\n" +
            "Score: 8", String.join("\n", expectedArr3));
    assertEquals(8, this.defaultModel3.getScore());
    // q is at toRow
    Readable rd4 = new StringReader("3 3 q 1");
    MarbleSolitaireController controller4 = new MarbleSolitaireControllerImpl(this.defaultModel4,
            this.defaultView4, rd4);
    controller4.playGame();
    String[] expectedArr4 = Arrays.copyOfRange(this.resultView4.toString().split("\n"),
            8, 18);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O\n" +
            "Score: 27", String.join("\n", expectedArr4));
    assertEquals(27, this.defaultModel4.getScore());
  }

  // Test that when q is pressed but more inputs are supplied,
  // any additional moves don't change the game.
  @Test
  public void testQPressedButMoreInputs() {
    Readable rd1 = new StringReader("3 q 3 1 1");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            6, 14);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14", String.join("\n", expectedArr));
    assertEquals(14, this.defaultModel.getScore());
    assertFalse(this.defaultModel.isGameOver());
  }

  // Tests that the quit screen is properly updated to reflect the moves and the score
  @Test
  public void testValidMovesAndQuit() {
    Readable rd1 = new StringReader("3 3 1 1 4 2 2 2 5 3 3 3 5 5 5 3 q");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            30, 38);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O O\n" +
            "  O _ O\n" +
            " O _ _ O\n" +
            "O O O _ _\n" +
            "Score: 10", String.join("\n", expectedArr));
    assertEquals(10, this.defaultModel.getScore());
  }

  // Tests that the appendable is changed by valid moves made in every direction
  // and that the score decreases by 1.
  @Test
  public void testValidMoves() {
    // tests for move upward diagonally
    Readable rd1 = new StringReader("3 3 1 1 3 q");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            6, 12);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13", String.join("\n", expectedArr));
    assertEquals(13, this.defaultModel.getScore());

    // tests for upward diagonally and the left horizontally
    Readable rd2 = new StringReader("3 1 1 1 3 3 3 1 q");
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(this.defaultModel2,
            this.defaultView2, rd2);
    controller2.playGame();
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            8, 12);
    assertEquals("  O\n" +
            " _ O\n" +
            "O _ _\n" +
            "Score: 3", String.join("\n", expectedArr2));
    assertEquals(3, this.defaultModel2.getScore());
    // tests for upward diagonally then move to the right horizontally
    Readable rd3 = new StringReader("3 3 1 1 3 1 3 3 q");
    MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(this.defaultModel3,
            this.defaultView3, rd3);
    controller3.playGame();
    String[] expectedArr3 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            10, 15);
    assertEquals("   O\n" +
            "  O _\n" +
            " _ _ O\n" +
            "O O O O\n" +
            "Score: 7", String.join("\n", expectedArr3));
    assertEquals(7, this.defaultModel3.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultModel3.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultModel3.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultModel3.getSlotAt(2, 1));

    // tests for upward diagonally twice then downward diagonally
    Readable rd4 = new StringReader("3 3 1 1 5 3 3 3 2 1 4 3 q");
    MarbleSolitaireController controller4 = new MarbleSolitaireControllerImpl(this.defaultModel4,
            this.defaultView4, rd4);
    controller4.playGame();
    String[] expectedArr4 = Arrays.copyOfRange(this.resultView4.toString().split("\n"),
            24, 32);
    assertEquals("      O\n" +
            "     _ _\n" +
            "    O _ O\n" +
            "   O O O O\n" +
            "  O O _ O O\n" +
            " O O O O O O\n" +
            "O O O O O O O\n" +
            "Score: 24", String.join("\n", expectedArr4));
    assertEquals(24, this.defaultModel4.getScore());
  }

  // Tests that the appendable is correct for the game over screen.
  @Test
  public void testPlayGameGameOver() {
    Readable rd1 = new StringReader("3 3 1 1 4 2 2 2 5 4 3 2 5 5 3 3 2 1 4 3 2 2 4 4 5 3 3 3 " +
            "4 4 2 2 1 1 3 3 5 1 5 3 3 1 5 1");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            66, 73);
    assertEquals("Game over!\n" +
            "    _\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "O _ O _ _\n" +
            "Score: 3", String.join("\n", expectedArr));
    assertEquals(3, this.defaultModel.getScore());
    assertTrue(this.defaultModel.isGameOver());
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   O O\n" +
            "  O _ _\n" +
            " O _ O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O _ _ O\n" +
            "O O O _ O\n" +
            "Score: 11\n" +
            "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O _ _ _\n" +
            "O O O _ _\n" +
            "Score: 10\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ O\n" +
            " O _ O _\n" +
            "O O O _ _\n" +
            "Score: 9\n" +
            "    O\n" +
            "   _ _\n" +
            "  O _ _\n" +
            " O _ O O\n" +
            "O O O _ _\n" +
            "Score: 8\n" +
            "    O\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O _ _ O\n" +
            "O O _ _ _\n" +
            "Score: 7\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O _ _ _\n" +
            "O O _ _ _\n" +
            "Score: 6\n" +
            "    _\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O _ _ _\n" +
            "O O _ _ _\n" +
            "Score: 5\n" +
            "    _\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O _ _ _\n" +
            "_ _ O _ _\n" +
            "Score: 4\n" +
            "Game over!\n" +
            "    _\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "O _ O _ _\n" +
            "Score: 3", this.resultView.toString());
  }

  // Tests that inputs after game over won't change the board or score or make more moves.
  @Test
  public void testGameOverMoreInputs() {
    // tests with more number inputs
    Readable rd1 = new StringReader("3 3 1 1 4 2 2 2 5 4 3 2 5 5 3 3 2 1 4 3 2 2 4 4 5 3 3 3" +
            "            4 4 2 2 1 1 3 3 5 1 5 3 3 1 5 1 5 1 3 1");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            66, 73);
    assertEquals("Game over!\n" +
            "    _\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "O _ O _ _\n" +
            "Score: 3", String.join("\n", expectedArr));
    assertEquals(3, this.defaultModel.getScore());
    assertTrue(this.defaultModel.isGameOver());
  }

  @Test
  public void testGameOverQAfter() {
    // test with q after game over
    Readable rd = new StringReader("3 3 1 1 4 2 2 2 5 4 3 2 5 5 3 3 2 1 4 3 2 2 4 4 5 3 3 3" +
            " 4 4 2 2 1 1 3 3 5 1 5 3 3 1 5 1 5 1 3 1 q q q");
    MarbleSolitaireController controller = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd);
    controller.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            66, 73);
    assertEquals("Game over!\n" +
            "    _\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "O _ O _ _\n" +
            "Score: 3", String.join("\n", expectedArr));
    assertFalse(this.resultView.toString().contains("Game quit!"));
    assertEquals(3, this.defaultModel.getScore());
    assertTrue(this.defaultModel.isGameOver());
  }

  @Test
  // Tests for invalid moves
  public void testInvalidMoves() {
    Readable rd1 = new StringReader("3 3 1 3 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Invalid move. Play again.",
            this.resultView.toString().split("\n")[6]);
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", this.defaultView.toString());
    assertEquals(14, this.defaultModel.getScore());

    Readable rd3 = new StringReader(" 3 1 1 1 1 1 3 1 q");
    MarbleSolitaireController controller3 = new
            MarbleSolitaireControllerImpl(this.defaultModel3, this.defaultView3, rd3);
    controller3.playGame();

    String[] expectedArr = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            5, 10);

    String[] expectedArr2 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            10, 16);

    assertEquals("Invalid move. Play again.",
            this.resultView3.toString().split("\n")[10]);

    // tests that the board remains the same after an invalid move.
    assertEquals("   O\n" +
            "  _ O\n" +
            " _ O O\n" +
            "O O O O\n" +
            "Score: 8", String.join("\n", expectedArr));

    assertEquals("   O\n" +
            "  _ O\n" +
            " _ O O\n" +
            "O O O O", this.defaultView3.toString());

    assertEquals("Invalid move. Play again.\n" +
            "   O\n" +
            "  _ O\n" +
            " _ O O\n" +
            "O O O O\n" +
            "Score: 8", String.join("\n", expectedArr2));
    assertEquals(8, this.defaultModel3.getScore());
    assertFalse(this.defaultModel3.isGameOver());
  }

  // Tests when inputs are greater than the board's size.
  @Test
  public void testInvalidMoveGreater() {
    Readable rd1 = new StringReader("6 1 6 3 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Invalid move. Play again.",
            this.resultView.toString().split("\n")[6]);
    assertEquals(14, this.defaultModel.getScore());

    Readable rd4 = new StringReader("8 8 6 6 q");
    MarbleSolitaireController controller4 = new
            MarbleSolitaireControllerImpl(this.defaultModel4, this.defaultView4, rd4);
    controller4.playGame();
    assertEquals("Invalid move. Play again.",
            this.resultView4.toString().split("\n")[8]);
    assertEquals(27, this.defaultModel4.getScore());
    assertFalse(this.defaultModel4.isGameOver());

  }


  // Tests that a valid move can be made after an invalid one.
  @Test
  public void testInvalidThenValid() {
    Readable rd1 = new StringReader("3 1 3 3 3 3 1 1 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Invalid move. Play again.",
            this.resultView.toString().split("\n")[6]);
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            13, 19);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13", String.join("\n", expectedArr));
    assertEquals(13, this.defaultModel.getScore());
  }

  // Tests that a re-enter move message is displayed when invalids are inputted.
  @Test
  public void testInvalidInputs() {
    Readable rd1 = new StringReader("3 3 1 1 a q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView.toString().split("\n")[12]);

    Readable rd2 = new StringReader("6 4 4 4 -1 q");
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(this.defaultModel2, this.defaultView2, rd2);
    controller2.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView2.toString().split("\n")[9]);

    Readable rd3 = new StringReader("4 6 4 4 4 0 q");
    MarbleSolitaireController controller3 = new
            MarbleSolitaireControllerImpl(this.defaultModel3, this.defaultView3, rd3);
    controller3.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView3.toString().split("\n")[11]);

  }

  // Tests that a move can still be made after invalid input is made.
  @Test
  public void testInvalidInputsThenMoves() {
    Readable rd1 = new StringReader("3 1 1 1 -1 5 1 3 1 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView.toString().split("\n")[12]);
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            13, 19);
    assertEquals("    O\n" +
                    "   _ O\n" +
                    "  O O O\n" +
                    " _ O O O\n" +
                    "_ O O O O\n" +
                    "Score: 12",
            String.join("\n", expectedArr));
    assertEquals(12, this.defaultModel.getScore());
  }

  // Tests that a move is still properly made even if an invalid is between the 4 move inputs.
  @Test
  public void testInvalidInputsBetweenMove() {
    Readable rd1 = new StringReader("3 3 -1 1 1 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView.toString().split("\n")[6]);
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            7, 13);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13", String.join("\n", expectedArr));

    Readable rd2 = new StringReader("3 z 3 1 1 l 3 1 3 t 3 q");
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(this.defaultModel2, this.defaultView2, rd2);
    controller2.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView2.toString().split("\n")[4]);
    assertEquals("Please re-enter that move again.",
            this.resultView2.toString().split("\n")[9]);
    assertEquals("Please re-enter that move again.",
            this.resultView2.toString().split("\n")[10]);
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            11, 15);
    assertEquals("  O\n" +
            " O _\n" +
            "_ _ O\n" +
            "Score: 3", String.join("\n", expectedArr2));

    Readable rd3 = new StringReader("3 e 0 w 3 1 1 q");
    MarbleSolitaireController controller3 = new
            MarbleSolitaireControllerImpl(this.defaultModel3, this.defaultView3, rd3);
    controller3.playGame();
    String[] expectedArr3 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            5, 8);
    assertEquals("Please re-enter that move again.\n" +
            "Please re-enter that move again.\n" +
            "Please re-enter that move again.", String.join("\n", expectedArr3));

    String[] expectedArr4 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            8, 13);
    assertEquals("   O\n" +
            "  O _\n" +
            " O O _\n" +
            "O O O O\n" +
            "Score: 8", String.join("\n", expectedArr4));
    assertEquals(8, this.defaultModel3.getScore());
  }

  // Tests that the correct inputs from the controller are being sent to the model.
  @Test
  public void testInputsFromController() {
    SolitaireModelMock mockModel = new SolitaireModelMock(new StringBuilder());
    Appendable resultView = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mockModel, resultView);
    Readable rd1 = new StringReader("3 3 1 1 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(mockModel, view, rd1);
    controller1.playGame();
    assertEquals("fromRow = 2, fromCol = 2, toRow = 0, toCol = 0\n",
            mockModel.log.toString());

    EnglishSolitaireModelMock mockModel2 = new EnglishSolitaireModelMock(new StringBuilder());
    Appendable resultView2 = new StringBuilder();
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(mockModel2, resultView2);
    Readable rd2 = new StringReader("3 1 1 1 3 3 3 1 q");
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(mockModel2, view2, rd2);
    controller2.playGame();
    assertEquals("fromRow = 2, fromCol = 0, toRow = 0, toCol = 0\n" +
            "fromRow = 2, fromCol = 2, toRow = 2, toCol = 0\n", mockModel2.log.toString());
  }


}
