package analysis;

import java.util.*;

class queueFullException extends RuntimeException{
	
	public queueFullException(String s){
		super(s);
	}
}

class emptyQueueException extends RuntimeException{
	
	public emptyQueueException(String s){
		super(s);
	}
}

public class myQueue<type> implements Iterable<type>{
	
	private List<type> queue;
	private int limit;
	
	public myQueue(int n){
		queue=new LinkedList<type>();
		limit=n;
	}
	
	public myQueue(){
		queue=new LinkedList<type>();
		limit=10;
	}
	
	public Iterator<type> iterator() {
		
		return queue.iterator();
	}
	
	public void add(type element) throws queueFullException{
		if(queue.size()==limit)
			throw new queueFullException("The queue is already full!!");
		else
			queue.add(element);
	}
	
	public type remove() throws emptyQueueException{
		if(queue.size()==0)
			throw new emptyQueueException("Queue is empty!!");
		else
		{
			type temp=queue.remove(0);
			return temp;
		}
	}
	
	public type element() throws emptyQueueException{
		if(queue.size()==0)
			throw new emptyQueueException("Queue is empty!!");
		else
		{
			type temp=queue.get(0);
			return temp;
		}
	}
}
