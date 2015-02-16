/**
 * A Pipeline represents a segment of pipe in some linear arrangement.
 * It has a length and diameters of the incoming and outgoing connectors.
 * It also can have a next Pipeline or the next Pipeline may be null which
 * indicates the end of the connected Pipeline.
 * 
 * This Pipeline class is considered to be self-referential 
 * (i.e. recursive) because it contains a reference to its own type 
 * as a property. This software pattern allows us to chain specific
 * logic recursively so that we rely on the structure of the data
 * itself rather than a general purpose data structure.
 */

/*
 * Created by Alexander Szostek
 * Created on March 14th, 2014
 * Problem Set #3
 * */

public class Pipeline {
    private int length;
    private int inDiameter;
    private int outDiameter;
    private Pipeline next;

    public Pipeline(int length, int inDiameter, int outDiameter)
    {
    	this(length, inDiameter, outDiameter, null);
    }
    
    public Pipeline(int length, int inDiameter, int outDiameter, Pipeline next)
    {
        this.length = length;
        this.inDiameter = inDiameter;
        this.outDiameter = outDiameter;
        this.next = next;
    }

    public Pipeline getNext() {
        return next;
    }
    
    public void setNext(Pipeline next) {
        this.next = next;
    }

    /**
     * Returns the total length of all Pipelines in the linked list.
     */
    public int getTotalLength() {
        if(this.next == null)
        {
        	return this.length;
        }
        else
        {
        	return this.length + this.next.getTotalLength();
        }
    }

    /**
     * Returns true if any Pipeline in the linked list has an
     * outDiameter that is not equal to its next Pipeline's inDiameter.
     */
    public boolean hasLeak() {
        if(this.next == null)
        {
        	return false;
        }
        else if(this.outDiameter != this.next.inDiameter)
        {
        	return true;
        }
        else
        {
        	return this.next.hasLeak();
        }
        
    }
    
    /**
     * Returns the maximum flow of any Pipeline in the linked list. The
     * maximum flow is equal to the minimum diameter, either incoming
     * or outgoing. 
     */
    public int getMaxFlow() 
    {
    	if(this.next == null)
    	{
    		return Math.min(this.inDiameter, this.outDiameter);
    	}
    	else
    	{
    		return Math.min(Math.min(this.inDiameter, this.outDiameter), this.next.getMaxFlow());
    	}
    }
    
    /**
     * Replaces the last pipeline in the linked list. This method
     * should not change the number of Pipelines in the list
     * (if there were 2 Pipelines in the list before then there
     * will be 2 in it after, but the last one is now replacement).
     * 
     * This method does not need to work when there is only 1
     * Pipeline.
     */
    public void replaceEnd(Pipeline replacement) 
    {
    	if(this.next.next != null)
    	{
    		this.next.replaceEnd(replacement);
    	}
    	else if(this.next.next == null)
    	{
    		this.next = replacement;
    	}
    }
}
