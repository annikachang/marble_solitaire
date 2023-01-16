import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the testing class for the European Solitaire View.
 */
public class EuropeanSolitaireViewTest {
  private MarbleSolitaireModel model ;
  private MarbleSolitaireModel model2;
  private MarbleSolitaireModel model3;
  private MarbleSolitaireView view1;
  private MarbleSolitaireView view2;
  private MarbleSolitaireView view3;
  private Appendable resultView1;

  @Before
  public void initTest() {
    this.model = new EuropeanSolitaireModel();
    this.model2 = new EuropeanSolitaireModel(5, 2,2);
    this.model3 = new EuropeanSolitaireModel(7);
    this.resultView1 = new StringBuilder();
    this.view1 = new MarbleSolitaireTextView(model, resultView1);
    this.view2 = new MarbleSolitaireTextView(model2);
    this.view3 = new MarbleSolitaireTextView(model3);
  }

  // Tests that an exception is thrown in the first constructor when model is null.
  @Test
  public void testConstructorModelNull() {
    try {
      new MarbleSolitaireTextView(null);
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
      new MarbleSolitaireTextView(model, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
    try {
      new MarbleSolitaireTextView(null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
  }

  // test toString method on different sized boards.
  @Test
  public void testToString() {
    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O", view1.toString());
    assertEquals(
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    _ O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O", view2.toString());
    assertEquals(
            "            O O O O O O O\n" +
                    "          O O O O O O O O O\n" +
                    "        O O O O O O O O O O O\n" +
                    "      O O O O O O O O O O O O O\n" +
                    "    O O O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O _ O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O O O O O O O\n" +
                    "    O O O O O O O O O O O O O O O\n" +
                    "      O O O O O O O O O O O O O\n" +
                    "        O O O O O O O O O O O\n" +
                    "          O O O O O O O O O\n" +
                    "            O O O O O O O", view3.toString());

  }

  // Tests that the board updates when a move is performed in all directions.
  @Test
  public void testToStringMove() {
    assertEquals(
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    _ O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O", view2.toString());
    this.model2.move(2,4,2,2);
    assertEquals(
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    O _ _ O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O", this.view2.toString());
    this.model2.move(4,4,2,4);
    assertEquals(
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    O _ O O O O O O O\n" +
                    "  O O O _ O O O O O O O\n" +
                    "O O O O _ O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O", this.view2.toString());
    this.model2.move(4,6, 4,4);
    assertEquals(
            "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O _ O O O O O O O\n" +
            "  O O O _ O O O O O O O\n" +
            "O O O O O _ _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.view2.toString());
    this.model2.move(2,5,4,5);
    assertEquals(
            "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O _ O _ O O O O O\n" +
            "  O O O _ _ O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.view2.toString());

  }

  @Test
  public void testRenderBoardIOException() {
    try {
      new MarbleSolitaireTextView(model, new TestingViewCorruptedClass()).renderBoard();
    } catch (IOException e) {
      assertEquals(null, e.getMessage());
    }
  }

  // tests that the board renders correctly with different board sizes and a move performed.
  @Test
  public void testRenderBoard() throws IOException {
    view1.renderBoard();
    assertEquals(
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", resultView1.toString());
    this.model.move(3,1,3,3);
    view1.renderBoard();
    String[] resultStringArr = this.resultView1.toString().split("\n");
    String[] expectedArr = Arrays.copyOfRange(resultStringArr, 6, 13);
    assertEquals(
            "    O O O    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O", String.join("\n", expectedArr));
    Appendable resultView2 = new StringBuilder();
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(model3, resultView2);
    view2.renderBoard();
    assertEquals("            O O O O O O O\n" +
            "          O O O O O O O O O\n" +
            "        O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O\n" +
            "          O O O O O O O O O\n" +
            "            O O O O O O O", resultView2.toString());
  }

  // tests that an io exception is thrown for render message when given a corrupted appendable
  @Test
  public void testRenderMessageIOException() {
    try {
      new MarbleSolitaireTextView(model, new TestingViewCorruptedClass())
              .renderMessage("Score: ");
    } catch (IOException e) {
      assertEquals(null, e.getMessage());
    }
  }

  // tests that render message renders the message supplied in the appendable.
  @Test
  public void testRenderMessage() throws IOException {
    view1.renderMessage("Score: 10.");
    assertEquals("Score: 10.", this.resultView1.toString());
    view1.renderMessage("\n");
    view1.renderMessage("Welcome to the game!");
    assertEquals("Score: 10.\nWelcome to the game!", this.resultView1.toString());
  }



}
