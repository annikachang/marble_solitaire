import org.junit.Before;
import org.junit.Test;


import java.io.StringReader;

import java.util.Arrays;


import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

/**
 * This class represents a testing class for the European Marble solitaire controller.
 */
public class EuropeanSolitaireControllerTest {
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
    this.defaultModel = new EuropeanSolitaireModel();
    this.resultView = new StringBuilder();
    this.defaultView = new MarbleSolitaireTextView(defaultModel, resultView);

    this.defaultModel2 = new EuropeanSolitaireModel(3);
    this.resultView2 = new StringBuilder();
    this.defaultView2 = new MarbleSolitaireTextView(defaultModel2, resultView2);

    this.defaultModel3 = new EuropeanSolitaireModel(3, 3, 3);
    this.resultView3 = new StringBuilder();
    this.defaultView3 = new MarbleSolitaireTextView(defaultModel3, resultView3);

    this.defaultModel4 = new EuropeanSolitaireModel(5);
    this.resultView4 = new StringBuilder();
    this.defaultView4 = new MarbleSolitaireTextView(defaultModel4, resultView4);
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
      Readable rd1 = new StringReader("4 2 4 4");
      new MarbleSolitaireControllerImpl(null, this.defaultView, rd1);
    } catch (IllegalArgumentException e) {
      assertEquals("Model, view and/or in is invalid.", e.getMessage());
    }
    try {
      Readable rd1 = new StringReader("4 2 4 4");
      new MarbleSolitaireControllerImpl(this.defaultModel, null, rd1);
    } catch (IllegalArgumentException e) {
      assertEquals("Model, view and/or in is invalid.", e.getMessage());
    }
  }

  // Tests that an illegal state exception is thrown when appendable or output is
  // not transmittable.
  @Test
  public void testPlayGameExceptionAppendable() {
    Readable inputs = new StringReader("4 2 4 4 q");
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable corruptedView = new TestingViewCorruptedClass();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, corruptedView);
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(model, view, inputs);
    try {
      controller1.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Output is not transmittable.",
              e.getMessage());
    }

    Readable inputs2 = new StringReader("-1 0 q");
    MarbleSolitaireModel model2 = new EuropeanSolitaireModel();
    Appendable corruptedView2 = new TestingViewCorruptedClass();
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(model2, corruptedView2);
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
      Readable twoInputs = new StringReader("1 2");
      MarbleSolitaireController controller3 = new
              MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, twoInputs);
      controller3.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
    try {
      Readable threeInputs = new StringReader("1\n2\n3");
      MarbleSolitaireController controller4 = new
              MarbleSolitaireControllerImpl(this.defaultModel3, this.defaultView3, threeInputs);
      controller4.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
    try {
      Readable threeInputs = new StringReader("4 6\n4");
      MarbleSolitaireController controller4 = new
              MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, threeInputs);
      controller4.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Invalid readable or inputs.", e.getMessage());
    }
    try {
      Readable multipleInputs = new StringReader("4 6\n4 4 4 3\n4\n5");
      MarbleSolitaireController controller4 = new
              MarbleSolitaireControllerImpl(this.defaultModel2, this.defaultView2, multipleInputs);
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
    assertEquals("Game quit!", this.resultView.toString().split("\n")[8]);
    assertEquals("State of game when quit:",
            this.resultView.toString().split("\n")[9]);
    assertEquals("Score: 36", this.resultView.toString().split("\n")[17]);
    String[] resultStringArr = this.resultView.toString().split("\n");
    String[] expectedArr = Arrays.copyOfRange(resultStringArr, 8, 18);
    assertEquals(
            "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O\n" +
                    "Score: 36", String.join("\n", expectedArr));

    Readable rd2 = new StringReader("Q");
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(this.defaultModel2,
            this.defaultView2, rd2);
    controller2.playGame();
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            8, 18);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36", String.join("\n", expectedArr2));
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
            0, 8);
    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O\n" +
                    "Score: 36", String.join("\n", expectedArr));
    assertEquals(36, this.defaultModel.getScore());
    assertFalse(this.defaultModel.isGameOver());
  }

  // Tests for when q is pressed at different points of making the move.
  @Test
  public void testPlayGameQ() {
    // q is at fromCol
    Readable rd1 = new StringReader("4 q");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            8, 18);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36", String.join("\n", expectedArr));
    // q is at toCol
    Readable rd2 = new StringReader("2 4 4 q");
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(this.defaultModel2,
            this.defaultView2, rd2);
    controller2.playGame();
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            8, 18);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36", String.join("\n", expectedArr2));
    // q is at fromRow
    Readable rd3 = new StringReader("2 4 4 4 q");
    MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(this.defaultModel3,
            this.defaultView3, rd3);
    controller3.playGame();
    String[] expectedArr3 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            16, 26);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35", String.join("\n", expectedArr3));
    assertEquals(35, this.defaultModel3.getScore());
    // q is at toRow
    Readable rd4 = new StringReader("4 6 q 4");
    MarbleSolitaireController controller4 = new MarbleSolitaireControllerImpl(this.defaultModel4,
            this.defaultView4, rd4);
    controller4.playGame();
    String[] expectedArr4 = Arrays.copyOfRange(this.resultView4.toString().split("\n"),
            14, 30);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n" +
            "Score: 128", String.join("\n", expectedArr4));
    assertEquals(128, this.defaultModel4.getScore());
  }

  // Test that when q is pressed but more inputs are supplied,
  // any additional moves don't change the game.
  @Test
  public void testQPressedButMoreInputs() {
    Readable rd1 = new StringReader("4 q 6 4 4");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            8, 18);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36", String.join("\n", expectedArr));
    assertEquals(36, this.defaultModel.getScore());
    assertFalse(this.defaultModel.isGameOver());
  }

  // Tests that the quit screen is properly updated to reflect the moves and the score
  @Test
  public void testValidMovesAndQuit() {
    Readable rd1 = new StringReader("4 6 4 4 4 3 4 5 6 4 4 4 4 1 4 3 3 4 5 4 q");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            48, 58);
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O _ O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 31", String.join("\n", expectedArr));
    assertEquals(31, this.defaultModel.getScore());
  }

  // Tests that the appendable is changed by valid moves made in every direction
  // and that the score decreases by 1.
  @Test
  public void testValidMoves() {
    // tests for move to the right
    Readable rd1 = new StringReader("4 6 4 4 4 q");
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(this.defaultModel,
            this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            8, 16);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35", String.join("\n", expectedArr));
    assertEquals(35, this.defaultModel.getScore());

    // tests for up and down move
    Readable rd2 = new StringReader("6 4 4 4 3 4 5 4 q");
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(this.defaultModel2,
            this.defaultView2, rd2);
    controller2.playGame();
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            16, 24);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 34", String.join("\n", expectedArr2));
    assertEquals(34, this.defaultModel2.getScore());
    // tests for left move
    Readable rd3 = new StringReader("4 2 4 4 q");
    MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(this.defaultModel3,
            this.defaultView3, rd3);
    controller3.playGame();
    String[] expectedArr3 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            8, 16);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35", String.join("\n", expectedArr3));
    assertEquals(35, this.defaultModel3.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultModel3.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.defaultModel3.getSlotAt(3, 1));
  }


  // Tests that the appendable is correct for the game over screen.
  @Test
  public void testPlayGameGameOver() {
    Readable rd1 = new StringReader("4 2 4 4 6 3 4 3 5 5 5 3 2 2 4 2 2 4 2 2 3 4 3 2 3 1 3 3 " +
            "5 2 5 4 5 4 3 4 4 2 4 4 5 7 5 5 3 6 5 6 7 4 5 4 5 5 5 3 4 5 4 3 6 6 4 6 2 6 2 4 7 " +
            "5 5 5 2 4 4 4 4 3 6 3 6 2 6 4 3 7 5 7 5 1 3 1");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            184, 193);
    assertEquals("Game over!\n" +
            "    O O O\n" +
            "  O _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ O\n" +
            "  _ _ O _ _\n" +
            "    O _ _\n" +
            "Score: 13", String.join("\n", expectedArr));
    assertEquals(13, this.defaultModel.getScore());
    assertTrue(this.defaultModel.isGameOver());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O _ _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 33\n" +
            "    O O O\n" +
            "  _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "O O _ _ O O O\n" +
            "O O O O O O O\n" +
            "O O O _ _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O _ _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O _ O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O O O O O\n" +
            "O O O _ O O O\n" +
            "O _ _ _ _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O O O O O\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O O O O O\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O _ _\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O O O _ O\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O O _\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O O O _ O\n" +
            "O _ _ O O _ O\n" +
            "O _ _ O O O _\n" +
            "  O _ _ O O\n" +
            "    O _ O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O O O _ O\n" +
            "O _ _ O O _ O\n" +
            "O _ O _ _ O _\n" +
            "  O _ _ O O\n" +
            "    O _ O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O O O _ O\n" +
            "O _ O _ _ _ O\n" +
            "O _ O _ _ O _\n" +
            "  O _ _ O O\n" +
            "    O _ O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "_ _ O O O _ O\n" +
            "O _ O _ _ O O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ O _\n" +
            "    O _ O\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "  O _ O _ _\n" +
            "_ _ O O O _ O\n" +
            "O _ O _ _ O O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ O _\n" +
            "    O _ O\n" +
            "Score: 19\n" +
            "    O O O\n" +
            "  O _ O _ _\n" +
            "_ _ O O O _ O\n" +
            "O _ O _ _ O O\n" +
            "O _ O _ O _ _\n" +
            "  O _ _ _ _\n" +
            "    O _ _\n" +
            "Score: 18\n" +
            "    O O O\n" +
            "  O _ _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "O _ O O _ O O\n" +
            "O _ O _ O _ _\n" +
            "  O _ _ _ _\n" +
            "    O _ _\n" +
            "Score: 17\n" +
            "    O O O\n" +
            "  O _ _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ _\n" +
            "  O O _ _ _\n" +
            "    O _ _\n" +
            "Score: 16\n" +
            "    O O O\n" +
            "  O _ _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ _\n" +
            "  _ _ O _ _\n" +
            "    O _ _\n" +
            "Score: 15\n" +
            "    O O O\n" +
            "  O _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "O _ _ O _ O _\n" +
            "O _ _ _ O _ O\n" +
            "  _ _ O _ _\n" +
            "    O _ _\n" +
            "Score: 14\n" +
            "Game over!\n" +
            "    O O O\n" +
            "  O _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ O\n" +
            "  _ _ O _ _\n" +
            "    O _ _\n" +
            "Score: 13", this.resultView.toString());
  }

  // Tests that inputs after game over won't change the board or score or make more moves.
  @Test
  public void testGameOverMoreInputs() {
    // tests with more number inputs
    Readable rd1 = new StringReader("4 2 4 4 6 3 4 3 5 5 5 3 2 2 4 2 2 4 2 2 3 4 3 2 3 1 3 3 " +
            "5 2 5 4 5 4 3 4 4 2 4 4 5 7 5 5 3 6 5 6 7 4 5 4 5 5 5 3 4 5 4 3 6 6 4 6 2 6 2 4 7 " +
            "5 5 5 2 4 4 4 4 3 6 3 6 2 6 4 3 7 5 7 5 1 3 1 2 4 2 2 3 1 2 3");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            184, 193);
    assertEquals("Game over!\n" +
            "    O O O\n" +
            "  O _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ O\n" +
            "  _ _ O _ _\n" +
            "    O _ _\n" +
            "Score: 13", String.join("\n", expectedArr));
    assertEquals(13, this.defaultModel.getScore());
    assertTrue(this.defaultModel.isGameOver());

    // test with q after game over
    Readable rd2 = new StringReader("4 2 4 4 6 3 4 3 5 5 5 3 2 2 4 2 2 4 2 2 3 4 3 2 3 1 3 3 " +
            "5 2 5 4 5 4 3 4 4 2 4 4 5 7 5 5 3 6 5 6 7 4 5 4 5 5 5 3 4 5 4 3 6 6 4 6 2 6 2 4 7 " +
            "5 5 5 2 4 4 4 4 3 6 3 6 2 6 4 3 7 5 7 5 1 3 1 q q" );
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(this.defaultModel2, this.defaultView2, rd2);
    controller2.playGame();
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            184, 193);
    assertEquals("Game over!\n" +
            "    O O O\n" +
            "  O _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ O\n" +
            "  _ _ O _ _\n" +
            "    O _ _\n" +
            "Score: 13", String.join("\n", expectedArr2));
    assertFalse(this.resultView2.toString().contains("Game quit!"));
    assertEquals(13, this.defaultModel2.getScore());
    assertTrue(this.defaultModel2.isGameOver());
  }

  @Test
  // Tests for invalid moves
  public void testInvalidMoves() {
    Readable rd1 = new StringReader("2 6 4 4 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Invalid move. Play again.",
            this.resultView.toString().split("\n")[8]);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.defaultView.toString());
    assertEquals(36, this.defaultModel.getScore());

    Readable rd2 = new StringReader("4 2 4 4 3 2 5 3 q");
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(this.defaultModel2, this.defaultView2, rd2);
    controller2.playGame();

    String[] expectedArr = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            8, 16);

    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            16, 25);

    assertEquals("Invalid move. Play again.",
            this.resultView2.toString().split("\n")[16]);

    // tests that the board remains the same after an invalid move.
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35", String.join("\n", expectedArr));

    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.defaultView2.toString());
    assertEquals("Invalid move. Play again.\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35", String.join("\n", expectedArr2));
    assertEquals(35, this.defaultModel2.getScore());
    assertFalse(this.defaultModel2.isGameOver());
  }

  // Tests when inputs are greater than the board's size.
  @Test
  public void testInvalidMoveGreater() {
    Readable rd1 = new StringReader("8 6 8 4 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Invalid move. Play again.",
            this.resultView.toString().split("\n")[8]);
    assertEquals(36, this.defaultModel.getScore());

    Readable rd4 = new StringReader("6 14 8 14 q");

    MarbleSolitaireController controller4 = new
            MarbleSolitaireControllerImpl(this.defaultModel4, this.defaultView4, rd4);
    controller4.playGame();
    assertEquals("Invalid move. Play again.",
            this.resultView4.toString().split("\n")[14]);
    assertEquals(128, this.defaultModel4.getScore());
    assertFalse(this.defaultModel4.isGameOver());

  }

  // Tests that a valid move can be made after an invalid one.
  @Test
  public void testInvalidThenValid() {
    Readable rd1 = new StringReader("6 4 4 4 6 3 4 5 5 2 5 4 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Invalid move. Play again.",
            this.resultView.toString().split("\n")[16]);
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            25, 33);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 34", String.join("\n", expectedArr));
    assertEquals(34, this.defaultModel.getScore());
  }

  // Tests that a re-enter move message is displayed when invalids are inputted.
  @Test
  public void testInvalidInputs() {
    Readable rd1 = new StringReader("2 4 4 4 a q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView.toString().split("\n")[16]);

    Readable rd2 = new StringReader("6 4 4 4 -1 q");
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(this.defaultModel2, this.defaultView2, rd2);
    controller2.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView2.toString().split("\n")[16]);

    Readable rd3 = new StringReader("4 6 4 4 4 0 q");
    MarbleSolitaireController controller3 = new
            MarbleSolitaireControllerImpl(this.defaultModel3, this.defaultView3, rd3);
    controller3.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView3.toString().split("\n")[16]);

  }

  // Tests that a move can still be made after invalid input is made.
  @Test
  public void testInvalidInputsThenMoves() {
    Readable rd1 = new StringReader("6 4 4 4 -1 3 4 5 4 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView.toString().split("\n")[16]);
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            17, 25);
    assertEquals("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O _ O O\n" +
                    "    O O O\n" +
                    "Score: 34",
            String.join("\n", expectedArr));
    assertEquals(34, this.defaultModel.getScore());
  }

  // Tests that a move is still properly made even if an invalid is between the 4 move inputs.
  @Test
  public void testInvalidInputsBetweenMove() {
    Readable rd1 = new StringReader("6 4 -1 4 4 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(this.defaultModel, this.defaultView, rd1);
    controller1.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView.toString().split("\n")[8]);
    String[] expectedArr = Arrays.copyOfRange(this.resultView.toString().split("\n"),
            9, 17);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 35", String.join("\n", expectedArr));

    Readable rd2 = new StringReader("2 4 4 4 5 4 3 z 4 q");
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(this.defaultModel2, this.defaultView2, rd2);
    controller2.playGame();
    assertEquals("Please re-enter that move again.",
            this.resultView2.toString().split("\n")[16]);
    String[] expectedArr2 = Arrays.copyOfRange(this.resultView2.toString().split("\n"),
            17, 25);
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34", String.join("\n", expectedArr2));

    Readable rd3 = new StringReader("4 k 0 l 2 4 4 q");
    MarbleSolitaireController controller3 = new
            MarbleSolitaireControllerImpl(this.defaultModel3, this.defaultView3, rd3);
    controller3.playGame();
    String[] expectedArr3 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            8, 11);
    assertEquals("Please re-enter that move again.\n" +
            "Please re-enter that move again.\n" +
            "Please re-enter that move again.", String.join("\n", expectedArr3));

    String[] expectedArr4 = Arrays.copyOfRange(this.resultView3.toString().split("\n"),
            11, 19);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35", String.join("\n", expectedArr4));
    assertEquals(35, this.defaultModel3.getScore());
  }

  // Tests that the correct inputs from the controller are being sent to the model.
  @Test
  public void testInputsFromController() {
    SolitaireModelMock mockModel = new SolitaireModelMock(new StringBuilder());
    Appendable resultView = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mockModel, resultView);
    Readable rd1 = new StringReader("4 6 4 4 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(mockModel, view, rd1);
    controller1.playGame();
    assertEquals("fromRow = 3, fromCol = 5, toRow = 3, toCol = 3\n",
            mockModel.log.toString());

    EnglishSolitaireModelMock mockModel2 = new EnglishSolitaireModelMock(new StringBuilder());
    Appendable resultView2 = new StringBuilder();
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(mockModel2, resultView2);
    Readable rd2 = new StringReader("4 2 4 4 2 3 4 3 q");
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(mockModel2, view2, rd2);
    controller2.playGame();
    assertEquals("fromRow = 3, fromCol = 1, toRow = 3, toCol = 3\n" +
            "fromRow = 1, fromCol = 2, toRow = 3, toCol = 2\n", mockModel2.log.toString());
  }

  // Tests that the correct inputs from the controller are being sent to the model now that
  // the mock is extended to the Solitaire model.
  @Test
  public void testInputsFromControllerNewMock() {
    SolitaireModelMock mockModel = new SolitaireModelMock(new StringBuilder());
    Appendable resultView = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mockModel, resultView);
    Readable rd1 = new StringReader("2 4 4 4 q");
    MarbleSolitaireController controller1 = new
            MarbleSolitaireControllerImpl(mockModel, view, rd1);
    controller1.playGame();
    assertEquals("fromRow = 1, fromCol = 3, toRow = 3, toCol = 3\n",
            mockModel.log.toString());

    SolitaireModelMock mockModel2 = new SolitaireModelMock(new StringBuilder());
    Appendable resultView2 = new StringBuilder();
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(mockModel2, resultView2);
    Readable rd2 = new StringReader("4 6 4 4 6 5 4 5 q");
    MarbleSolitaireController controller2 = new
            MarbleSolitaireControllerImpl(mockModel2, view2, rd2);
    controller2.playGame();
    assertEquals("fromRow = 3, fromCol = 5, toRow = 3, toCol = 3\n" +
            "fromRow = 5, fromCol = 4, toRow = 3, toCol = 4\n", mockModel2.log.toString());
  }


}


