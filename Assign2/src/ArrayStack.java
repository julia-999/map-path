/**
 * Creates an ArrayStack object (implements the provided ArrayStackADT abstract class)
 * @author Julia Anantchenko
 * @param <T>: a type of object
 */
public class ArrayStack<T> implements ArrayStackADT<T> {

	/** Array for the data items of the stack. */
	private T[] stack;

	/** The position of the last data item in the stack. */
	private int top;

	/** The initial size of the array stack */
	private int initialCapacity;

	/** When array stack is full and a new item must be added to it, the size of the array will increase by this amount*/
	private int sizeIncrease;

	/**  If after removing a data item from array stack the number of data items in it is less than one fourth of the size of the array, and the size of the array is larger than initialCapacity, then the size of the array will decrease by this amount*/
	private int sizeDecrease;

	/**
	 * Constructor for ArrayStack
	 * @param initialCap: the initial capacity of the stack
	 * @param sizeInc: How much to increase by
	 * @param sizeDec: How much to decrease by
	 */
	public ArrayStack(int initialCap, int sizeInc, int sizeDec) {
		
		top = -1; // means stack is empty
		stack = (T[])(new Object[initialCap]);
		initialCapacity = initialCap;
		sizeIncrease = sizeInc;
		sizeDecrease = sizeDec;
	}

	/**
	 * Adds an item to the top of the stack
	 */
	@Override
	public void push(T dataItem) {
		
		// if the stack is full increases the capacity
		if (length() == size())
			expandCapacity();
		
		// makes the stack bigger
		top++;
		
		// adds the item
		stack[top] = dataItem;
	}

	/**
	 * Removes and returns the top item from the stack
	 */
	@Override
	public T pop() throws EmptyStackException {
		
		// throws an exception if the stack is empty
		if (top == -1)
			throw new EmptyStackException("Empty stack");

		// saves the top item then removes it from the top
		T topItem = stack[top];
		stack[top] = null;
		top--;

		// reduces the array if it is small enough
		if (size() < (length()/4) && length() > initialCapacity)
			reduceCapacity();
		
		// returns the item that was removed
		return topItem;
	}

	/**
	 * Returns the top item
	 */
	@Override
	public T peek() throws EmptyStackException {
		
		// throws an exception if the stack is empty
		if (top == -1)
			throw new EmptyStackException("Empty stack");
		
		// returns the top item
		return stack[top];
	}

	/**
	 * Checks if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		
		// checks if empty
		return (top == -1);
	}

	/**
	 * Sees the amount of items in the stack
	 * @return the amount of items the stack has
	 */
	@Override
	public int size() {
		
		// returns the amount of items in the stack
		return top+1;
	}

	/**
	 * Checks the length of the stack
	 * @return the length of the stack
	 */
	public int length() {
		
		// returns the length of the stack
		return stack.length;
	}

	/**
	 * Returns the contents of the stack in a readable String
	 * @return the String
	 */
	public String toString() {
		String str = "Stack: ";

		// goes through stack contents in a for loop
		for (int i = 0; i < top; i++) {
			str += stack[i] + ", ";
		}

		// adds last item
		if (!isEmpty())
			str += stack[top];
		else
			str += "Empty";

		// returns the string
		return str;
	}

	/**
	 * Expands the capacity of the array
	 */
	private void expandCapacity() {
		// creates a new array
		T[] newArray = (T[])(new Object[length()+sizeIncrease]);

		// fills the new array with the old data
		for (int i = 0; i < length(); i++) {
			newArray[i] = stack[i];
		}

		// replaces the original array with the new array
		stack = newArray;
	}

	/**
	 * Reduces the capacity of the array
	 */
	private void reduceCapacity() {
		// creates a new array
		T[] newArray = (T[])(new Object[length()-sizeDecrease]);

		// fills the new array with the old data
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = stack[i];
		}

		// replaces the original array with the new array
		stack = newArray;
	}

}
