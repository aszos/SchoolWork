public class LinkedNode<T> implements ListNode<Object>
{
	public ListNode next;
	private T value;
	
	public LinkedNode(ListNode next, T value)
	{
		this.next = next;
		this.value = value;
	}

	public T getValue() 
	{
		return value;
	}

	public ListNode<Object> getNext() 
	{
		return next;
	}	
}
