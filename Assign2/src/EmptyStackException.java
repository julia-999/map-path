/**
 * Exception class for an empty stack (extends Exception)
 * @author Julia Anantchenko
 */
public class EmptyStackException extends Exception{

	/**
	 * Constructor for the EmptyStackException class
	 * @param message to be printed
	 */
	public EmptyStackException(String message) {
		
		// calls the super class to print the message
		super(message);
	}
}
