import java.util.Iterator;


public class PolymorphicListIterator<T> implements Iterator<T>
{
	PolymorphicList<?> l;
	ListNode<?> current;
	
	public PolymorphicListIterator(PolymorphicList<?> l)
	{
		this.l = l;
		current = l.head;
	}
	
	public boolean hasNext() 
	{
		return (current instanceof LinkedNode);
	}

	public T next() 
	{
		T temp = (T) current.getValue();
		current = current.getNext();
		return temp;
	}

	public void remove() 
	{
		throw new UnsupportedOperationException();
	}

}
