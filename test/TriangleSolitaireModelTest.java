import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * This class represents the testing class for the triangle solitaire model.
 */
public class TriangleSolitaireModelTest {
  MarbleSolitaireModel model1;
  MarbleSolitaireModel model2;
  MarbleSolitaireModel model3;
  MarbleSolitaireModel model4;
  MarbleSolitaireModel model5;

  @Before
  public void initTest() {
    this.model1 = new TriangleSolitaireModel();
    this.model2 = new TriangleSolitaireModel(7);
    this.model3 = new TriangleSolitaireModel(3, 2);
    this.model4 = new TriangleSolitaireModel(3, 0, 0);
    this.model5 = new TriangleSolitaireModel(4);
  }

  @Test
  public void testConstructor1() {
    assertEquals(5, this.model1.getBoardSize());
    assertEquals(14, this.model1.getScore());
    assertFalse(this.model1.isGameOver());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model1.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model1.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model1.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model1.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model1.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model1.getSlotAt(1, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(1,0));
  }

  @Test
  public void testConstructor2() {
    assertEquals(7, this.model2.getBoardSize());
    assertEquals(27, this.model2.getScore());
    assertFalse(this.model2.isGameOver());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(2,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model2.getSlotAt(3,4));
    assertEquals(4, this.model5.getBoardSize());
    assertEquals(9, this.model5.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(0,0));
  }

  @Test
  public void testConstructor3() {
    assertEquals(5, this.model3.getBoardSize());
    assertEquals(14, this.model3.getScore());
    assertFalse(this.model3.isGameOver());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(3,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model3.getSlotAt(0,0));
  }


  @Test
  public void testConstructor4() {
    assertEquals(3, this.model4.getBoardSize());
    assertEquals(5, this.model4.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(1,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(2,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(1,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(2,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model4.getSlotAt(2,1));
  }

  @Test
  public void testConstructor2InvalidDimension() {
    try {
      new TriangleSolitaireModel(-1);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(0);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
  }

  @Test
  public void testConstructor3InvalidRowAndCol() {
    try {
      new TriangleSolitaireModel(0,1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(-1,3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,3)", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(3,-1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3,-1)", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(4,5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (4,5)", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(5,2);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,2)", e.getMessage());
    }
  }

  @Test
  public void testConstructor4InvalidDimension() {
    try {
      new TriangleSolitaireModel(-9, 0,0);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(0, 0,0);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
  }

  @Test
  public void testConstructor4InvalidRowAndCol() {
    try {
      new TriangleSolitaireModel(3, 0,2);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,2)", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(5, 1,4);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,4)", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(5, -1,0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,0)", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(5, 3,-5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3,-5)", e.getMessage());
    }
    try {
      new TriangleSolitaireModel(5, 4,5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (4,5)", e.getMessage());
    }
  }


  @Test
  public void testMove() {
    // move up diagonally
    this.model1.move(2,0,0,0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(1,0));
    // move to the left horizontally
    this.model1.move(2,2,2,0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2,1));
    // move down diagonally
    this.model1.move(0,0,2,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(1,1));
    // move up diagonally
    this.model1.move(3,3,1,1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(1,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2,2));
    // move to the right horizontally
    this.model1.move(3,1,3,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3,2));
    // move up diagonally
    this.model1.move(3,0,1,0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(1,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2,0));
    // move up diagonally
    this.model1.move(4,4,2,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3,3));
    // move to the right horizontally
    this.model1.move(4,2,4,4);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,3));
    // move to the right horizontally
    this.model1.move(4, 0, 4,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,1));
    this.model1.move(2, 2, 0,0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,1));
    // move downward diagonally
    this.model1.move(0, 0, 2,0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4,1));
    assertTrue(this.model1.isGameOver());
    MarbleSolitaireView view = new TriangleSolitaireTextView(model1);
    assertEquals(3, this.model1.getScore());
  }

  @Test
  public void testInvalidMoveWhenDistanceNot2() {
    try {
      this.model1.move(0, 0, 3, 3);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(4, 0, 4, 3);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenFromNotMarble() {
    try {
      this.model1.move(0, 0, 2, 2);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    this.model1.move(2,0,0,0);
    try {
      this.model1.move(2, 0, 2, 2);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenToNotEmpty() {
    try {
      this.model1.move(2,2,4,4);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(4,3,4,1);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenNoMiddleDiagonal() {
    this.model1.move(2, 2, 0, 0);
    try {
      this.model1.move(0,0,2,2);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    this.model1.move(3, 1, 1, 1);
    try {
      this.model1.move(1,1,3,1);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(3,0,2,2);
      fail("Move is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenNoMiddle() {
    this.model1.move(2, 2, 0, 0);
    this.model1.move(3, 1, 1, 1);
    try {
      this.model1.move(2,0,2,2);
      fail("Move is valid");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    this.model1.move(4, 3, 2, 1);
    try {
      this.model1.move(3,0,3,2);
      fail("Move is valid");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenVertical() {
    try {
      this.model1.move(2,1,0,0);
      fail("Move is valid");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(4,1,3,3);
      fail("Move is valid");
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }


  @Test
  public void testInvalidMoveWhenInvalidPos() {
    // from slot is invalid
    try {
      this.model1.move(0,2,0,0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    // to slot is invalid
    try {
      this.model1.move(3,2,3,4);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenInvalidRange() {
    // from slot is invalid
    try {
      this.model2.move(4,-1,4,1);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    // to slot is invalid
    try {
      this.model2.move(3,3,5,5);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }


  @Test
  public void testGameOver() {
    assertFalse(this.model1.isGameOver());
    this.model1.move(2,2,0,0);
    this.model1.move(3,1,1,1);
    this.model1.move(4,3,2,1);
    this.model1.move(4,4,2,2);
    this.model1.move(1,0,3,2);
    this.model1.move(1,1,3,3);
    this.model1.move(3,0,1,0);
    this.model1.move(0,0,2,0);
    this.model1.move(3,3,3,1);
    this.model1.move(4,1,4,3);
    this.model1.move(2,0,4,2);
    this.model1.move(4,3,4,1);
    this.model1.move(4,0,4,2);
    assertTrue(this.model1.isGameOver());
    assertEquals(1, this.model1.getScore());
    MarbleSolitaireModel modelSizeTwo = new TriangleSolitaireModel(2);
    assertTrue(modelSizeTwo.isGameOver());
  }


  @Test
  public void testGetBoardSize() {
    assertEquals(5, this.model1.getBoardSize());
    assertEquals(7, this.model2.getBoardSize());
    assertEquals(5, this.model3.getBoardSize());
    assertEquals(3, this.model4.getBoardSize());
    assertEquals(4, this.model5.getBoardSize());
  }

  @Test
  public void testGetScore() {
    assertEquals(14, this.model1.getScore());
    assertEquals(27, this.model2.getScore());
    this.model2.move(2,2,0,0);
    assertEquals(26, this.model2.getScore());
    this.model2.move(4,2,2,2);
    assertEquals(25, this.model2.getScore());
    this.model2.move(4,0,4,2);
    assertEquals(24, this.model2.getScore());
    assertEquals(14, this.model3.getScore());
    assertEquals(5, this.model4.getScore());
    assertEquals(9, this.model5.getScore());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(0,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(0,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(0,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(0,4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(1,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(1,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(1,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(1,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(1,4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(2,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(2,4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model1.getSlotAt(3,4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4,4));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model2.getSlotAt(0,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(1,0));

  }

  @Test
  public void testGetSlotAtInvalid() {
    try {
      this.model1.getSlotAt(4,5);
      fail("Can get slot at this position.");
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      this.model1.getSlotAt(-1,0);
      fail("Can get slot at this position.");
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      this.model1.getSlotAt(5,0);
      fail("Can get slot at this position.");
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      this.model1.getSlotAt(4,-1);
      fail("Can get slot at this position.");
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      this.model1.getSlotAt(6,5);
      fail("Can get slot at this position.");
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
  }

}