/*************************************************************************
 * Title: Generic Stack 
 * File: EliJames_HW8.java
 * Author: James Eli
 * Date: 3/2/2017
 *
 * This program is an attempt to implement as generic as possible, a 
 * simplistic array-based stack program. Our stack has public push, pop, 
 * peek, getSize, isEmpty and toString methods. If an attempt to push a new 
 * value onto the stack will exceed the current size of the array, a resize 
 * method will double the current size of the array. 
 * 
 * The array is not down-sized as the stack shrinks, we rely upon java's 
 * garbage collection for this functionality (?).
 * 
 * Attempting to pop from an empty stack will throw a self-generated
 * myStackException.
 * 
 * The constructor casting to (E[]) causes an unchecked compile warning. 
 * The warning occurs because the compiler is not certain that casting will 
 * succeed at runtime. 
 * 
 * Initial stack size can be set between 1 and 100, otherwise it defaults 
 * to 10. Passing the constructor a value that is less than 1 or greater 
 * than the upper limit (100) will trigger an IllegalArgumentException. 
 * 
 * There are no upper bounds set on how large the stack size can grow.
 * 
 * Notes:
 *  (1) The class only works with non-raw (parameterized) types.
 *  (2) Compiled with java SE JDK 8, Update 121 (JDK 8u121).
 *  (3) Add "-ea" as VM argument to turn on assertions.
 *  (4) Add JUnit4 library to Project>Properties>Java Build Path to run
 *  JUint test suite inside the Eclipse workspace.
 *  
 * Submitted in partial fulfillment of the requirements of PCC CIS-279.
 *************************************************************************
 * Change Log:
 *   02/02/2017: Initial release. JME
 *   03/18/2017: Corrected index used in peek() method. JME
 *   03/18/2017: Generally improved the code. JME
 *   03/18/2017: Added JUnit tests. JME
 *   03/19/2017: Added validation to constructor. JME
 *   04/13/2017: Added pre/post test println statements. JME
 *************************************************************************/
public class EliJames_HW8 {
  public class GenericStack<E> {
    public final static int INITIAL_STACK_SIZE = 10;        // Default initial stack size.
    public final static int INITIAL_STACK_SIZE_LIMIT = 100; // Upper bound of initial stack.
    private E[] stack; // Array of elements (the stack).
    private int size;  // Number of elements on stack.

    public GenericStack() {
      this( INITIAL_STACK_SIZE );
    }

    @SuppressWarnings( "unchecked" ) // Suppressing warning due to (E[]) cast of Object.
    public GenericStack( int initSize ) {
      if ( initSize > 0 && initSize < INITIAL_STACK_SIZE_LIMIT )	
        stack = (E[]) new Object[initSize];
      else
    	throw new IllegalArgumentException();
      size = 0;
    }
    
    public boolean isEmpty() {
      return (size == 0);
    }

    public int getSize() { 
      return size; 
    }

    public synchronized E peek() {
      if ( this.isEmpty() )
        return null;
      else
        return stack[size-1];
    }
  
    public E push( E e ) {
      if ( stack.length == size ) 
        stack = resizeStack( stack );
      stack[size++] = e;
      return e;
    }

    public synchronized E pop() throws myStackException {
      if ( this.isEmpty() ) 
        throw new myStackException( "GenericStack<E> pop(), stack empty." );
      E e = stack[--size];
      //stack[size] = null; // Free object. Will this facilitate garbage collection? 
      return e;
    }

    private E[] resizeStack( E[] current ) {
      @SuppressWarnings( "unchecked" ) // Warning due to (E[]) cast of Object.
      E[] copy = (E[]) new Object[current.length*2];
      int i=0;
      for ( E e : current )
        copy[i++] = e;
      return copy;
    }
	  
    @Override
    public String toString() {
      StringBuffer sBuffer = new StringBuffer( "[ " );
      for ( int i=0; i<size; i++ )
        sBuffer.append( stack[i] != null ? stack[i].toString() + " " : "" );
      return sBuffer.append( "]" ).toString();
    } 

  } // End of GenericStack<E> class.

  // Our special stack exception.
  // I could have used the existing EmptyStackException, but what's the fun in that?
  class myStackException extends Exception {
    private static final long serialVersionUID = 1L; // Default ID. 

    public myStackException( String msg ) {
      super( msg );
    }
  } // End of myStackException class.
  
  /*********************************************************************
   * Main method used to test GenericStack<E> class.
   *********************************************************************/
  public static void main( String[] args ) {
    // The following example creates a stack to hold strings and adds three strings to the stack.
    GenericStack<String> stack1 = new EliJames_HW8().new GenericStack<>();

    // Note: due to type erasure, this asserts true!
    assert new EliJames_HW8().new GenericStack<String>().getClass() == new EliJames_HW8().new GenericStack<Integer>().getClass();

    // Announce stack driver test.
    System.out.println( "Testing GenericStack<E> class." );
    
    stack1.push( "London" );
    stack1.push( "Paris" );
    stack1.push( "Berlin" );
    System.out.println( stack1 );
    assert( stack1.getSize() == 3 ) : "Stack #1 wrong size error!";

    // This example creates a stack of integers, adds three integers to the stack, and removes them testing for an empty stack exception.
    GenericStack<Integer> stack2 = new EliJames_HW8().new GenericStack<>();
    stack2.push( 1 ); // Autoboxing.
    stack2.push( 2 );
    stack2.push( 3 );
    System.out.println( stack2 );
    assert( stack2.peek() == 3 ) : "Peek fail!";
    assert( stack2.getSize() == 3 ) : "Stack #2 wrong size error!";

    // Attempt to pop beyond empty stack.
    try { 
      System.out.println( "Pop: " + stack2.pop() + ", " + stack2.pop() + ", " + stack2.pop() );
      System.out.println( stack2.pop() ); // Throws exception without printing.
    } catch ( myStackException ex ) {
      // We should catch our myStackException here.
      assert( stack2.isEmpty() ) : "Stack not empty error.";
      System.out.println( "Exception caught, empty stack: " + stack2 );
    }
    
    // Test stack resizing.
    for ( int i=0; i<GenericStack.INITIAL_STACK_SIZE*2; i++ )
      stack2.push( i );
    assert( stack2.getSize() == GenericStack.INITIAL_STACK_SIZE*2 ) : "Stack resize test fail";

    // Announce stack driver test complete.
    System.out.println( "Test complete." );
    
}

  // Prevent instantiation.
  EliJames_HW8() { }
  
} // End of EliJames_HW8 class.
