import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents the testing class for the European Solitaire Model.
 */
public class EuropeanSolitaireModelTest {
  private MarbleSolitaireModel model;
  private MarbleSolitaireModel model2;
  private MarbleSolitaireModel model3;
  private MarbleSolitaireModel model4;
  private MarbleSolitaireModel model5;


  @Before
  public void initTest() {
    this.model = new EuropeanSolitaireModel();
    this.model2 = new EuropeanSolitaireModel(5);
    this.model3 = new EuropeanSolitaireModel(7);
    this.model4 = new EuropeanSolitaireModel(2, 1);
    this.model5 = new EuropeanSolitaireModel(3, 4, 3);
  }

  @Test
  public void testConstructor1() {
    assertEquals(7, this.model.getBoardSize());
    assertEquals(36, this.model.getScore());
    assertFalse(this.model.isGameOver());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(1, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(5, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model.getSlotAt(1, 5));
  }

  @Test
  public void testConstructor2() {
    assertEquals(13, model2.getBoardSize());
    assertEquals(128, model2.getScore());
    assertFalse(model2.isGameOver());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model2.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model2.getSlotAt(12, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.model2.getSlotAt(12, 12));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model2.getSlotAt(3, 12));
    assertEquals(19, this.model3.getBoardSize());
  }

  @Test
  public void testConstructor3() {
    assertEquals(7, this.model4.getBoardSize());
    assertEquals(36, this.model4.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model4.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model4.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model4.getSlotAt(6, 0));
  }

  @Test
  public void testConstructor4() {
    assertEquals(7, this.model5.getBoardSize());
    assertEquals(36, this.model5.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model5.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model5.getSlotAt(6, 6));
    assertFalse(this.model5.isGameOver());
  }

  @Test
  public void testConstructor2InvalidDimension() {
    try {
      new EuropeanSolitaireModel(-1);
      fail("Dimension is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(0);
      fail("Dimension is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(1);
      fail("Dimension is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(6);
      fail("Dimension is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
  }

  @Test
  public void testConstructor3InvalidRowAndCol() {
    try {
      new EuropeanSolitaireModel(-1, 3);
      fail("Row and/or col is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,3)", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(3, -9);
      fail("Row and/or col is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3,-9)", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(0, 1);
      fail("Row and/or col is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)", e.getMessage());
    }
  }

  @Test
  public void testConstructor4InvalidDimension() {
    try {
      new EuropeanSolitaireModel(0, 3, 3);
      fail("Dimension is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(-1, 2, 4);
      fail("Dimension is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(8, 5, 8);
      fail("Dimension is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(1, 2, 1);
      fail("Dimension is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
  }

  @Test
  public void testConstructor4InvalidRowAndCol() {
    try {
      new EuropeanSolitaireModel(3, 0, 0);
      fail("Row and/or col is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,0)", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(5, -1, 5);
      fail("Row and/or col is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,5)", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(3, 2, -3);
      fail("Row and/or col is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2,-3)", e.getMessage());
    }
    try {
      new EuropeanSolitaireModel(3, 6, 5);
      fail("Row and/or col is valid.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,5)", e.getMessage());
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, this.model.getBoardSize());
    assertEquals(13, this.model2.getBoardSize());
    assertEquals(19, this.model3.getBoardSize());
    assertEquals(7, this.model4.getBoardSize());
    assertEquals(7, this.model5.getBoardSize());
  }

  // tests get score with different side lengths for boards.
  @Test
  public void testGetScore() {
    assertEquals(36, this.model.getScore());
    assertEquals(128, this.model2.getScore());
    assertEquals(276, this.model3.getScore());
    assertEquals(36, this.model4.getScore());
    assertEquals(36, this.model5.getScore());
  }

  // tests that get score decreases when valid moves are performed.
  @Test
  public void testGetScoreWithMoves() {
    assertEquals(36, this.model.getScore());
    this.model.move(3, 1, 3, 3);
    assertEquals(35, this.model.getScore());
    this.model.move(5, 2, 3, 2);
    assertEquals(34, this.model.getScore());
    this.model.move(4, 4, 4, 2);
    assertEquals(33, this.model.getScore());
    this.model.move(1, 1, 3, 1);
    assertEquals(32, this.model.getScore());
  }

  @Test
  public void testGameOver() {
    assertFalse(this.model.isGameOver());
    assertFalse(this.model2.isGameOver());
    this.model.move(3, 1, 3, 3);
    this.model.move(5, 2, 3, 2);
    this.model.move(4, 4, 4, 2);
    this.model.move(1, 1, 3, 1);
    this.model.move(1, 3, 1, 1);
    this.model.move(2, 3, 2, 1);
    this.model.move(2, 0, 2, 2);
    this.model.move(4, 1, 4, 3);
    this.model.move(4, 3, 2, 3);
    this.model.move(3, 1, 3, 3);
    this.model.move(4, 6, 4, 4);
    this.model.move(2, 5, 4, 5);
    this.model.move(6, 3, 4, 3);
    this.model.move(4, 4, 4, 2);
    this.model.move(3, 4, 3, 2);
    this.model.move(5, 5, 3, 5);
    this.model.move(1, 5, 1, 3);
    this.model.move(6, 4, 4, 4);
    this.model.move(1, 3, 3, 3);
    this.model.move(3, 2, 5, 2);
    this.model.move(5, 1, 5, 3);
    this.model.move(2, 6, 4, 6);
    this.model.move(4, 0, 2, 0);
    assertTrue(this.model.isGameOver());
    assertEquals(13, this.model.getScore());
  }

  // test that move works in all directions.
  @Test
  public void testMove() {
    // move to the left
    this.model2.move(6, 4, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(6, 4));
    // move to the bottom
    this.model2.move(4, 5, 6, 5);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(5, 5));
    // move to the right
    this.model2.move(6, 2, 6, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(6, 3));
    // move to the top
    this.model2.move(8, 3, 6, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(6, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(8, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(7, 3));

    this.model2.move(10, 3, 8, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(8, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(10, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(9, 3));
  }

  @Test
  public void testInvalidMoveWhenDistanceNot2() {
    try {
      this.model.move(3, 0, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model.move(3, 0, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenFromNotMarble() {
    this.model.move(1, 3, 3, 3);
    this.model.move(4, 3, 2, 3);
    this.model.move(6, 3, 4, 3);
    try {
      this.model.move(5, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model.move(1, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenToNotEmpty() {
    try {
      this.model.move(5,1,5,3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model.move(4,6,4,4);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenNoMiddle() {
    this.model.move(1, 3, 3, 3);
    this.model.move(4, 3, 2, 3);
    this.model.move(6, 3, 4, 3);
    try {
      this.model.move(4,3,6,3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenDiagonal() {
    this.model.move(1, 3, 3, 3);
    this.model.move(4, 3, 2, 3);
    this.model.move(6, 3, 4, 3);
    try {
      this.model.move(1,5,3,3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model.move(4,5,3,3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenInvalidPos() {
    // from slot is invalid
    try {
      this.model2.move(1,1,3,2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    // to slot is invalid
    try {
      this.model2.move(10,9,10,11);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMoveWhenInvalidRange() {
    // from slot is invalid
    try {
      this.model2.move(5,-1,5,1);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    // to slot is invalid
    try {
      this.model2.move(6,11,6,13);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  // test get slot for different board dimensions.
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(6,6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(6,5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(9,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(9,10));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model2.getSlotAt(2,9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.model2.getSlotAt(2,1));
  }

  // test for when supplied inputs are invalid.
  @Test
  public void testGetSlotAtInvalid() {
    try {
      this.model.getSlotAt(-1,0);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      this.model.getSlotAt(3,-2);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      this.model.getSlotAt(7,5);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      this.model.getSlotAt(4,10);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      this.model2.getSlotAt(11,13);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
  }

}