import java.util.Iterator;


public class PolymorphicList<T> implements Iterable<T>
{
	ListNode head;
	int size;

	public PolymorphicList()
	{
		ListNode head = new EmptyNode();
	}
	
	public void add(T i) 
	{
		LinkedNode<T> a = new LinkedNode<T>(head, i);
		head = a;
		size++;
	}

	public boolean contains(T i) 
	{
		return recursiveContains(i, head);
	}
	
	public boolean recursiveContains(T i, ListNode next)
	{
		return next != null && (i == next.getValue() || recursiveContains(i, next.getNext()));
	}

	public int size() 
	{
		return size;
	}

	public Iterator iterator() 
	{
		return new PolymorphicListIterator<Object>(this);
	}
}
