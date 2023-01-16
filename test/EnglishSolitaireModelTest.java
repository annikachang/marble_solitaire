import org.junit.Before;
import org.junit.Test;


import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class represents the tests for the English Solitaire Model.
 */
public class EnglishSolitaireModelTest {
  private MarbleSolitaireModel model1;
  private EnglishSolitaireModel model2;
  private EnglishSolitaireModel model3;
  private EnglishSolitaireModel model4;
  private EnglishSolitaireModel model5;
  private EnglishSolitaireModel model6;

  private EnglishSolitaireModel model7;
  private EnglishSolitaireModel model8;


  @Before
  public void testInit() {
    model1 = new EnglishSolitaireModel();
    model2 = new EnglishSolitaireModel(3, 3);
    model3 = new EnglishSolitaireModel(5);
    model4 = new EnglishSolitaireModel(5, 6, 6);
    model5 = new EnglishSolitaireModel(0, 2);
    model6 = new EnglishSolitaireModel(3, 5);
    model7 = new EnglishSolitaireModel(5, 3);
    model8 = new EnglishSolitaireModel(7);
  }


  @Test
  public void testConstructor1() {
    assertFalse(model1.isGameOver());
    assertEquals(7, this.model1.getBoardSize());
    assertEquals(32, this.model1.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3,3));
  }

  @Test
  public void testConstructor2() {
    assertFalse(model2.isGameOver());
    assertEquals(7, this.model2.getBoardSize());
    assertEquals(32, this.model2.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model2.getSlotAt(3,3));
  }

  @Test
  public void testConstructor3() {
    assertFalse(model3.isGameOver());
    assertEquals(13, this.model3.getBoardSize());
    assertEquals(104, this.model3.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(6,6));
  }

  @Test
  public void testConstructor4() {
    assertFalse(model4.isGameOver());
    assertEquals(13, this.model4.getBoardSize());
    assertEquals(104, this.model4.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model3.getSlotAt(6,6));

    EnglishSolitaireModel model5 = new EnglishSolitaireModel(3,2,2);
    assertEquals(7, model5.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model5.getSlotAt(2,2));
  }

  @Test
  public void testInitInvalidArmThicknessOnly() {
    try {
      new EnglishSolitaireModel(1);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EnglishSolitaireModel(6);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EnglishSolitaireModel(-1);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
    try {
      new EnglishSolitaireModel(0);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.", e.getMessage());
    }
  }

  @Test
  public void testInitInvalidWithArmThickness() {
    try {
      new EnglishSolitaireModel(4, 5, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(-7, 5, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(1, 5, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(0, 5, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(20, 5, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Dimension supplied is an invalid value.",
              e.getMessage());
    }
  }

  @Test
  public void testInitInvalidWithRowColOnly() {
    try {
      new EnglishSolitaireModel(1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,0)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(0, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,5)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(6, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,1)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(6, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,5)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(-1, -6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,-6)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(8, 9);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (8,9)",
              e.getMessage());
    }
  }

  @Test
  public void testInitInvalidWithRowCol() {
    try {
      new EnglishSolitaireModel(3, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,0)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,0)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 0, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 0, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,5)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 0, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,6)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 1, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,5)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 1, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,6)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 6, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,0)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 5, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,0)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 5, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,1)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 6, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,1)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 5, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,5)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 5, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,6)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 5, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,1)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 6, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,1)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, -1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,0)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 0, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,-1)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, -2, -5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-2,-5)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 7, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,6)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(3, 6, 8);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,8)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(5, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,0)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(5, 0, 9);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,9)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(5, 9, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (9,0)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(5, 11, 10);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (11,10)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(5, -1, -10);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,-10)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(5, 0, -20);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,-20)",
              e.getMessage());
    }
    try {
      new EnglishSolitaireModel(5, -4, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-4,6)",
              e.getMessage());
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, model1.getBoardSize());
    assertEquals(7, model2.getBoardSize());
    assertEquals(13, model3.getBoardSize());
    assertEquals(13, model4.getBoardSize());
    assertEquals(7, model5.getBoardSize());
    assertEquals(7, model6.getBoardSize());
    assertEquals(7, model7.getBoardSize());
    assertEquals(19, model8.getBoardSize());
  }

  @Test
  public void testGetScoreWithArmThickness3() {
    assertEquals(32, model1.getScore());
    this.model1.move(5, 3, 3, 3);
    assertEquals(31, model1.getScore());
    this.model1.move(2, 3, 4, 3);
    assertEquals(30, model1.getScore());
    this.model1.move(0, 3, 2, 3);
    assertEquals(29, model1.getScore());
    this.model1.move(3, 1, 3, 3);
    assertEquals(28, model1.getScore());
    this.model1.move(3, 4, 3, 2);
    assertEquals(27, model1.getScore());
    this.model1.move(3, 6, 3, 4);
    assertEquals(26, model1.getScore());
    assertEquals(32, model2.getScore());
    this.model2.move(3, 1, 3, 3);
    this.model2.move(3, 4, 3, 2);
    this.model2.move(3, 6, 3, 4);
    this.model2.move(1, 3, 3, 3);
    this.model2.move(4, 3, 2, 3);
    this.model2.move(4, 1, 4, 3);
    this.model2.move(4, 4, 4, 2);
    this.model2.move(6, 3, 4, 3);
    this.model2.move(6, 4, 4, 4);
    this.model2.move(4, 3, 4, 1);
    assertEquals(22, model2.getScore());
  }

  @Test
  public void testGetScoreWithArmThickness5() {
    assertEquals(104, model3.getScore());
    this.model3.move(4, 6, 6, 6);
    assertEquals(103, model3.getScore());
    this.model3.move(4, 8, 4, 6);
    assertEquals(102, model3.getScore());
    this.model3.move(6, 7, 4, 7);
    assertEquals(101, model3.getScore());
    assertEquals(104, model4.getScore());
  }

  @Test
  public void testGetScoreWithArmThickness7() {
    assertEquals(216, model8.getScore());
    this.model8.move(11, 9, 9, 9);
    assertEquals(215, model8.getScore());
    this.model8.move(10, 7, 10, 9);
    assertEquals(214, model8.getScore());
    this.model8.move(10, 5, 10, 7);
    assertEquals(213, model8.getScore());
  }


  @Test
  public void testIsGameOver() {
    assertFalse(this.model1.isGameOver());
    this.model1.move(5, 3, 3, 3);
    this.model1.move(2, 3, 4, 3);
    this.model1.move(0, 3, 2, 3);
    this.model1.move(3, 1, 3, 3);
    this.model1.move(3, 4, 3, 2);
    this.model1.move(3, 6, 3, 4);
    assertTrue(this.model1.isGameOver());
    assertFalse(this.model3.isGameOver());
    assertFalse(this.model8.isGameOver());
  }


  @Test
  public void testMove() {
    this.model1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 3));
    this.model1.move(4, 1, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 2));
    this.model1.move(6, 2, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(5, 2));
    this.model1.move(3, 3, 5, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 3));
    this.model1.move(4, 5, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 4));
    this.model1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 3));
    this.model1.move(2, 1, 4, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 1));
    this.model1.move(2, 3, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 3));
    this.model1.move(2, 5, 2, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 4));
    this.model1.move(2, 2, 2, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 3));
    this.model1.move(4, 2, 4, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 3));
    this.model1.move(4, 0, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 1));
    this.model1.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 4));
    this.model1.move(3, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 2));
    this.model1.move(3, 0, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 1));
    this.model1.move(0, 3, 2, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(1, 3));
    this.model1.move(2, 3, 2, 5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 4));
    this.model1.move(2, 6, 2, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 5));
    this.model1.move(0, 2, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(1, 2));
    this.model1.move(4, 6, 2, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(2, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 6));
    this.model1.move(3, 2, 5, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 2));
    this.model1.move(1, 4, 3, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(1, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(2, 4));
    this.model1.move(6, 4, 6, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(6, 3));
    this.model1.move(6, 2, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(5, 2));
    this.model1.move(4, 4, 6, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.model1.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.model1.getSlotAt(5, 4));
    assertEquals(true, this.model1.isGameOver());
    assertEquals(7, this.model1.getScore());
  }

  @Test
  public void testInvalidMoveWhenInvalidSlot() {
    // tests for when from row is empty
    try {
      this.model1.move(3,3,5,3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    this.model1.move(5,3,3,3);
    // tests for when to row is a marble
    try {
      this.model1.move(1,3,3,3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMovesWhenInvalidPos() {
    try {
      this.model1.move(-1, 0, 1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(3, -1, 3, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(-2, 3, 0, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(0, 3, -2, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMovesWhenInvalidSlots() {
    try {
      this.model1.move(0, 0, 0, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(3, 5, 3, 7);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(3, 0, 3, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(3, 0, 3, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testInvalidMovesWhenDistanceNot2() {
    try {
      this.model1.move(3, 5, 3, 8);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(6, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.model1.move(5, 2, 3, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }


  @Test
  public void testInvalidMoves() {
    this.model1.move(3, 1, 3, 3);
    try {
      this.model1.move(3, 0, 3, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    this.model1.move(3, 4, 3, 2);
    try {
      this.model1.move(3, 2, 3, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testGetSlotAtArmThickness3() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(1, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(5, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 1));
  }

  @Test
  public void testGetSlotAtArmThickness5() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(2, 11));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(0, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(12, 12));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(12, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(1, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(6, 8));
  }

  @Test
  public void testInvalidGetSlotStates() {
    try {
      model1.getSlotAt(-1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      model1.getSlotAt(3, -6);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      model1.getSlotAt(6, 7);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      model3.getSlotAt(-1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      model1.getSlotAt(13, 12);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      model4.getSlotAt(5, 13);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      model1.getSlotAt(-1, 12);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
    try {
      model1.getSlotAt(6, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("Row and/or column is invalid.", e.getMessage());
    }
  }

}

