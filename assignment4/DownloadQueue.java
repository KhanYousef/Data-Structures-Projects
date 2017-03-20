package assignment4;
import java.util.*;
public class DownloadQueue extends ArrayList{//used java API to implement the queue
	
	public DownloadQueue(){
		super();
	}
	
	public boolean enqueue(DownloadJob d){//adds d to the end of the queue
		super.add(d);
		return isEmpty();
	}
	
	public DownloadJob dequeue() throws EmptyQueueException{//takes the DownloadJob that is at the front of the queue,
		//saves the value, removes the DownloadJob from the queue, and returns the Value.
		if(isEmpty()){
			throw new EmptyQueueException("Queue cannot be empty");
		}
		return (DownloadJob) super.remove(0);
	}
	
	public DownloadJob peek() throws EmptyQueueException{//takes the DownloadJob that is at the front of the queue,
		//and returns that value to the caller. Does NOT remove that DownloadJob from the queue.
		DownloadJob peek;
		if(isEmpty()){
			throw new EmptyQueueException("Queue is empty");
		}
		else{
			peek = (DownloadJob) super.get(0);
		}
		return peek;
	}
	
	public boolean isEmpty(){//returns true if queue is empty, false otherwise.
		return super.isEmpty();
	}

}
