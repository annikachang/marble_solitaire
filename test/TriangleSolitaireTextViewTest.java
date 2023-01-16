import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the testing class for the triangle solitaire text view.
 */
public class TriangleSolitaireTextViewTest {
  private MarbleSolitaireModel model ;
  private MarbleSolitaireModel model2;
  private MarbleSolitaireModel model3;
  private MarbleSolitaireModel model4;
  private MarbleSolitaireView view1;
  private MarbleSolitaireView view2;
  private MarbleSolitaireView view3;
  private MarbleSolitaireView view4;
  private Appendable resultView1;
  private Appendable resultView2;
  private Appendable resultView3;
  private Appendable resultView4;


  @Before
  public void initTest() {
    this.model = new TriangleSolitaireModel();
    this.model2 = new TriangleSolitaireModel(5, 2,2);
    this.model3 = new TriangleSolitaireModel(7);
    this.model4 = new TriangleSolitaireModel(8, 4,4);
    this.resultView1 = new StringBuilder();
    this.resultView2 = new StringBuilder();
    this.resultView3 = new StringBuilder();
    this.resultView4 = new StringBuilder();
    this.view1 = new TriangleSolitaireTextView(model, resultView1);
    this.view2 = new TriangleSolitaireTextView(model2, resultView2);
    this.view3 = new TriangleSolitaireTextView(model3, resultView3);
    this.view4 = new TriangleSolitaireTextView(model4, resultView4);
  }

  // Tests that an exception is thrown in the first constructor when model is null.
  @Test
  public void testConstructorModelNull() {
    try {
      new TriangleSolitaireTextView(null).toString();
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
  }

  // Tests that the constructor throws an illegal argument exception when the model or
  // appendable is null.
  @Test
  public void testConstructor2ModelNull() {
    try {
      new TriangleSolitaireTextView(null, new StringBuilder());
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
    try {
      new TriangleSolitaireTextView(model2, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
    try {
      new TriangleSolitaireTextView(null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model or view supplied is invalid.", e.getMessage());
    }
  }

  // tests that toString displays properly given different board dimensions
  @Test
  public void testToString() {
    assertEquals(
            "    O\n" +
                    "   O O\n" +
                    "  O O _\n" +
                    " O O O O\n" +
                    "O O O O O", view2.toString());
    assertEquals(
            "      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view3.toString());
    assertEquals(
            "       O\n" +
            "      O O\n" +
            "     O O O\n" +
            "    O O O O\n" +
            "   O O O O _\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O", view4.toString());
  }


  // tests toString after a series of moves
  @Test
  public void testToStringMoves() {
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O", view1.toString());
    this.model.move(2,2,0,0);
    assertEquals(
            "    O\n" +
                    "   O _\n" +
                    "  O O _\n" +
                    " O O O O\n" +
                    "O O O O O", view1.toString());
    this.model.move(2,0,2,2);
    assertEquals(
            "    O\n" +
                    "   O _\n" +
                    "  _ _ O\n" +
                    " O O O O\n" +
                    "O O O O O", view1.toString());
  }

  @Test
  public void testRenderBoardException() {
    try {
      new TriangleSolitaireTextView(model2, new TestingViewCorruptedClass()).renderBoard();
    } catch (IOException e) {
      assertEquals(null, e.getMessage());
    }
  }

  @Test
  public void testRenderBoard() throws IOException {
    view1.renderBoard();
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O", resultView1.toString());
    this.model.move(2, 0, 0, 0);
    view1.renderBoard();
    String[] resultStringArr = resultView1.toString().split("\n");
    String[] expectedArr = Arrays.copyOfRange(resultStringArr, 4, 9);
    assertEquals(
            "O O O O O    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O",  String.join("\n", expectedArr));
    view3.renderBoard();
    assertEquals("      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", resultView3.toString());
    this.model3.move(2, 2,0, 0);
    view3.renderBoard();
    String[] resultStringArr3 = this.resultView3.toString().split("\n");
    String[] expectedArr3 = Arrays.copyOfRange(resultStringArr3, 6, 13);
    assertEquals("O O O O O O O      O\n" +
            "     O _\n" +
            "    O O _\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", String.join("\n", expectedArr3));
    view4.renderBoard();
    assertEquals("       O\n" +
            "      O O\n" +
            "     O O O\n" +
            "    O O O O\n" +
            "   O O O O _\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O", resultView4.toString());
    this.model4.move(4, 2,4, 4);
    view4.renderBoard();
    String[] resultStringArr4 = this.resultView4.toString().split("\n");
    String[] expectedArr4 = Arrays.copyOfRange(resultStringArr4, 7, 15);
    assertEquals(
            "O O O O O O O O       O\n" +
                    "      O O\n" +
                    "     O O O\n" +
                    "    O O O O\n" +
                    "   O O _ _ O\n" +
                    "  O O O O O O\n" +
                    " O O O O O O O\n" +
                    "O O O O O O O O", String.join("\n", expectedArr4));
  }

  @Test
  public void testRenderMessageException() {
    try {
      new MarbleSolitaireTextView(model, new TestingViewCorruptedClass())
              .renderMessage("Score: ");
    } catch (IOException e) {
      assertEquals(null, e.getMessage());
    }
  }

  @Test
  public void testRenderMessage() throws IOException {
    view2.renderMessage("This is the triangular peg solitaire game.");
    assertEquals("This is the triangular peg solitaire game.",
            this.resultView2.toString());
    view2.renderMessage("\n");
    view2.renderMessage("Game over!");
    assertEquals("This is the triangular peg solitaire game.\nGame over!",
            this.resultView2.toString());
  }

}