# Pima CC CIS279 Homework Assignment #8

Generic Stack 

This program is an attempt to implement as generic as possible, a simplistic array-based stack program. Our stack has public push, pop, peek, getSize, isEmpty and toString methods. If an attempt to push a new value onto the stack will exceed the current size of the array, a resize method will double the current size of the array.
 
The array is not down-sized as the stack shrinks, we rely upon java's garbage collection for this functionality (?).

Attempting to pop from an empty stack will throw a self-generated myStackException.

The constructor casting to (E[]) causes an unchecked compile warning. The warning occurs because the compiler is not certain that casting will succeed at runtime. 

Initial stack size can be set between 1 and 100, otherwise it defaults to 10. Passing the constructor a value that is less than 1 or greater than the upper limit (100) will trigger an IllegalArgumentException.
 
There are no upper bounds set on how large the stack size can grow.
 
Notes:
* The class only works with non-raw (parameterized) types.
* Compiled with java SE JDK 8, Update 121 (JDK 8u121).
* Add "-ea" as VM argument to turn on assertions.
* Add JUnit4 library to Project>Properties>Java Build Path to run JUint test suite inside the Eclipse workspace.
  
Submitted in partial fulfillment of the requirements of PCC CIS-279.
