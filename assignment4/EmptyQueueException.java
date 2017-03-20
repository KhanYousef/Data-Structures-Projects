package assignment4;

public class EmptyQueueException extends Exception{
	public EmptyQueueException(String message){
		super(message);
	}

}//exception for if the queue is empty and trying to be accessed
