package Application;

/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
import java.util.Arrays;
import java.util.EmptyStackException;
public final class ResizableArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   public ResizableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
   public ResizableArrayStack(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      integrityOK = true;
  } // end constructor
  
//  < Implementations of the stack operations go here. >
   public void push(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      stack[topIndex + 1] = newEntry;
      topIndex++;
   } // end push

   public T pop()
   {
      checkIntegrity();
      if (isEmpty()){
         throw new EmptyStackException();
      }
      else
      {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      } // end if
   } // end pop

   public T peek()
   {
      checkIntegrity();
      if (isEmpty()){
         throw new EmptyStackException();
      }
      else{
         return stack[topIndex];
      }
   } // end peek

   public boolean isEmpty()
   {
      return topIndex < 0;
   } // end isEmpty

   public void clear(){
      checkIntegrity();

      //Remove references to the objects in the stack,
      //but do not deallocate the array
      while (topIndex > -1){
         stack[topIndex] = null;
         topIndex--;
      }//end while
      //Assertion: topIndex is -1
   }//end clear
//  < Implementations of the private methods go here; checkCapacity and checkIntegrity

   private void ensureCapacity(){
      if (topIndex >= stack.length - 1)//If array is full, double its size
      {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      }//end if
   }//end ensureCapacity

   private void checkCapacity(int capacity)
      {
         if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose " +
                                            "capacity exeeds allowed " +
                                            "maximum of " + MAX_CAPACITY);
      } // end checkCapacity

   private void checkIntegrity()
      {
         if (!integrityOK)
            throw new SecurityException("ResizableArrayStack object is corrupt.");
      } // end checkIntegrity
//    are analogous to those in Chapter 2. >
//  . . .
} // end ArrayStack
