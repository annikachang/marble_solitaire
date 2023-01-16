import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;


import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;


/**
 * This class represents a testing class for the marble solitaire text view class.
 */
public class MarbleSolitaireTextViewTest {
  private MarbleSolitaireModel model1;
  private MarbleSolitaireModel model2;
  private MarbleSolitaireModel model3;
  private MarbleSolitaireView view1;
  private Appendable resultView1;

  @Before
  public void testInit() {
    model1 = new EnglishSolitaireModel();
    model2 = new EnglishSolitaireModel(3, 3);
    model3 = new EnglishSolitaireModel(5);
    resultView1 = new StringBuilder();
    view1 = new MarbleSolitaireTextView(model1, resultView1);
  }

  // Tests that an exception is thrown in the first constructor when model is null.
  @Test
  public void testConstructorModelNull() {
    try {
      new MarbleSolitaireTextView(null).toString();
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
  }

  // Tests that the constructor throws an illegal argument exception when the model or
  // appendable is null.
  @Test
  public void testConstructor2ModelNull() {
    try {
      new MarbleSolitaireTextView(null, new StringBuilder());
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
    try {
      new MarbleSolitaireTextView(model1, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
    try {
      new MarbleSolitaireTextView(null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
  }

  // Tests that render board throws an IO exception when given an invalid appendable.
  @Test
  public void testRenderBoardException() {
    try {
      new MarbleSolitaireTextView(model1, new TestingViewCorruptedClass()).renderBoard();
    } catch (IOException e) {
      assertEquals(null, e.getMessage());
    }
  }

  @Test
  public void testRenderBoard() throws IOException {
    view1.renderBoard();
    assertEquals(
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O", resultView1.toString());
    this.model1.move(3, 5, 3, 3);
    view1.renderBoard();
    String[] resultStringArr = this.resultView1.toString().split("\n");
    String[] expectedArr = Arrays.copyOfRange(resultStringArr, 6, 13);
    assertEquals(
            "    O O O    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O _ _ O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O", String.join("\n", String.join("\n",
                    expectedArr)));

    Appendable resultView3 = new StringBuilder();
    MarbleSolitaireView view3 = new MarbleSolitaireTextView(model3, resultView3);
    view3.renderBoard();
    assertEquals(
            "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O", resultView3.toString());
    this.model3.move(4, 6, 6, 6);
    view3.renderBoard();
    String[] resultStringArr2 = resultView3.toString().split("\n");
    String[] expectedArr2 = Arrays.copyOfRange(resultStringArr2, 12, 25);
    assertEquals("        O O O O O        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", String.join("\n", expectedArr2));

  }

  // Tests that an IOException is thrown when given an invalid appendable
  @Test
  public void testRenderMessageException() {
    try {
      new MarbleSolitaireTextView(model1, new TestingViewCorruptedClass())
              .renderMessage("Score: ");
    } catch (IOException e) {
      assertEquals(null, e.getMessage());
    }
  }

  // Test that render message outputs the correct message supplied
  @Test
  public void testRenderMessage() throws IOException {
    view1.renderMessage("Welcome to the game!");
    assertEquals("Welcome to the game!", this.resultView1.toString());
    view1.renderMessage("\n");
    view1.renderMessage("Game over!");
    assertEquals("Welcome to the game!\nGame over!", this.resultView1.toString());
  }


  @Test
  public void testToString() {
    MarbleSolitaireView board1 = new MarbleSolitaireTextView(model1);
    MarbleSolitaireView board2 = new MarbleSolitaireTextView(model2);
    MarbleSolitaireView board3 = new MarbleSolitaireTextView(model3);
    assertEquals(
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O", board1.toString());
    assertEquals(
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O", board2.toString());
    assertEquals(
            "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O", board3.toString());
    this.model1.move(3, 5, 3, 3);
    assertEquals(

            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O _ _ O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O", board1.toString());
    this.model1.move(1, 4, 3, 4);
    assertEquals(
            "    O O O\n" +
                    "    O O _\n" +
                    "O O O O _ O O\n" +
                    "O O O O O _ O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O", board1.toString());

  }

  // tests what is outputted when model view and models are not correctly paired
  @Test
  public void testBadModelInputs() {
    Appendable badAppend  = new StringBuilder();
    MarbleSolitaireView badView1 = new MarbleSolitaireTextView(new TriangleSolitaireModel(),
            badAppend);
    assertEquals(
            "_    \n" +
            "O O  \n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O O O", badView1.toString());

    Appendable badAppend2  = new StringBuilder();
    MarbleSolitaireView badView2 = new TriangleSolitaireTextView(new EnglishSolitaireModel(),
            badAppend2);
    assertEquals(
            "O O O\n" +
                    "O O O\n" +
                    "    O O O O O O O\n" +
                    "   O O O _ O O O\n" +
                    "  O O O O O O O\n" +
                    "O O O\n" +
                    "O O O", badView2.toString());

    Appendable badAppend3  = new StringBuilder();
    MarbleSolitaireView badView3 = new TriangleSolitaireTextView(new EuropeanSolitaireModel(),
            badAppend3);
    assertEquals(
            "O O O\n" +
                    "O O O O O\n" +
                    "    O O O O O O O\n" +
                    "   O O O _ O O O\n" +
                    "  O O O O O O O\n" +
                    "O O O O O\n" +
                    "O O O", badView3.toString());
  }

}