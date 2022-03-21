package Application;
import java.util.EmptyStackException;

/**
    A class of stacks whose entries are stored in a chain of nodes.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain
  
   public LinkedStack()
   {
      topNode = null;
   } // end default constructor
  

   /** 
   * Adds a value to the top of the stack.
   * @param newEntry New value to be placed on the stack.
   */
   public void push(T newEntry)
   {
      Node newNode = new Node(newEntry, topNode);
      topNode = newNode;
   } // end push

   
   /** 
    * Removes a value from the top of the stack.
    * @return top, The top value of the stack.
    */
   public T pop()
   {
      T top = peek();  // Might throw EmptyStackException

      // Assertion: topNode != null
      topNode = topNode.getNextNode();

      return top;
   } // end pop

   
   /** 
    * Looks at the top value of the stack.
    * @return T, top value of the stack.
    * @throws EmptyStackException() if stack is empty.
    */
   public T peek()
   {
      if (isEmpty()){
         throw new EmptyStackException();
      }
      else{
         return topNode.getData();
      }
   } // end peek

   
   /** 
    * Determines if stack is empty or not.
    * @return isEmpty(), true if stack is empty.
    */
   public boolean isEmpty()
   {
      return topNode == null;
   } // end isEmpty

   /** 
    * Removes all values from a stack.
    */
   public void clear()
   {
      topNode = null;
   } // end clear
//  . . .

	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
} // end LinkedStack
