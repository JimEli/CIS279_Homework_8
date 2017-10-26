/*************************************************************************
 * Title: GenericStack<E> Test Suite 
 * File: GenericStackTest.java
 * Author: James Eli
 * Date: 3/18/2017
 *
 * Multiple JUnit test cases for GenericStack<E>.java class.
 * 
 * Notes: 
 *   (1) Test suite expects GenericStack and myStackException classes to 
 *       be inner classes of EliJames_HW8. This was done to simplify 
 *       the submission process for the assignment. However, both classes 
 *       should be converted to individual class files.
 *   (2) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 *   (3) Uses JUnit4.
 *   (4) Under Eclipse, turn on Assertions via:
 *       Run>Run Configurations>Java Applications>EliJames_HW8
 *       Arguments Tab>VM Arguments, add "-ea"
 * 
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

// GenericStack test class.
public class GenericStackTest {
  // Stack used in all tests.
  EliJames_HW8.GenericStack<String> s = new EliJames_HW8().new GenericStack<>();

  @Test 
  public void testNewStackIsEmpty() {
    assertTrue( s.isEmpty() );
    assertEquals( s.getSize(), 0 );
  }

  @Test
  public void testPushesToEmptyStack() {
    int numberOfPushes = 12;

    for ( int i=0; i<numberOfPushes; i++ ) 
      s.push( "Phoenix" );
    assertFalse( s.isEmpty() );
    assertEquals( s.getSize(), numberOfPushes );
  }

  @Test
  public void testPushThenPop() throws EliJames_HW8.myStackException {
    String stackItem = "Flagstaff";

    s.push( stackItem );
    assertEquals( s.pop(), stackItem );
  }

  @Test
  public void testPushThenPeek() {
    String stackItem = "Kingman";

    s.push( stackItem );
    int size = s.getSize();
    assertEquals( s.peek(), stackItem );
    assertEquals( s.getSize(), size );
  }

  @Test
  public void testPoppingDownToEmpty() throws EliJames_HW8.myStackException {
    int numberOfPushes = (int)(Math.random()*20 + 1);

    for ( int i=0; i<numberOfPushes; i++ ) 
      s.push( "Tucson" );
    for ( int i=0; i<numberOfPushes; i++ )
      s.pop();
    assertTrue( s.isEmpty() );
    assertEquals( s.getSize(), 0 );
  }

  @Test
  public void testPeekIntoEmptyStack() {
    assertTrue( s.isEmpty() );
    assertTrue( s.peek() == null );
  }

  @Test( expected = EliJames_HW8.myStackException.class )
  public void testPopOnEmptyStack() throws EliJames_HW8.myStackException {
    assertTrue( s.isEmpty() );
    s.pop();
  }

  @Test( expected = IllegalArgumentException.class )
  public void testInitStackTooBig() throws IllegalArgumentException {
    s = new EliJames_HW8().new GenericStack<>( 101 );
  }

}