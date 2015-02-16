package ps5;

import java.util.ArrayList;
import java.util.List;

/**
 * A Pipeline contains segments of pipe in a List arrangement.
 * Each segment has a length and diameters of the incoming and outgoing connectors.
 */
public class Pipeline {
    private List<PipelineSegment> segments;

    /**
     * Creates an empty list
     */
    public Pipeline() {
        this.segments = new ArrayList<PipelineSegment>();
    }
    
    /**
     * Adds a segment to the end of the List.
     * 
     * @param segment
     */
    public void addAtEnd(PipelineSegment segment) {
        this.segments.add(segment);
    }
    
    /**
     * Returns a sublist of the segments as a new Pipeline.
     * 
     * @param position
     * @return
     */
    public Pipeline getSubPipeline(int position) {
        Pipeline p = new Pipeline();
        p.segments.addAll(this.segments.subList(position, this.segments.size()));
        return p;
    }

    /**
     * Returns the total length of all PipelineSegments in the list.
     * 
     * If the list is empty it should return 0.
     */
    public int getTotalLength() 
    {        
        int total = 0;
        for(PipelineSegment current : segments)
        {
        	total += current.getLength();
        }
        return total;
    }

    /**
     * Returns true if any PipelineSegment in the list has an
     * outDiameter that is not equal to the next PipelineSegment's inDiameter.
     * 
     * If the list is empty it should return false.
     * 
     * Hint: you should use the size() and get() methods on List
     */
    public boolean hasLeak() 
    {
    	boolean hasLeak = false;
    	for(int i = 0; !hasLeak && i < segments.size() - 1; i++)
    	{
    		hasLeak = (segments.get(i).getOutDiameter() != segments.get(i+1).getInDiameter());
    	}
 	
        return hasLeak;
    }
    
    /**
     * Returns the maximum flow of any PipelineSegment in the list. The
     * maximum flow is equal to the minimum diameter, either incoming
     * or outgoing. 
     * 
     * If the list is empty it should return Integer.MAX_VALUE.
     */
    public int getMaxFlow() 
    {
    	int currentMax = Integer.MAX_VALUE;
    	
    	for(int i = 0; i < segments.size(); i++)
    	{
    		currentMax = Math.min(currentMax, Math.min(segments.get(i).getInDiameter() ,segments.get(i).getOutDiameter()));
    	}
    	
        return currentMax;
    }
    
    /**
     * Replaces the last PipelineSegment in the list. This method
     * should not change the size of the list
     * (if there were 2 PipelineSegments in the list before then there
     * will be 2 in it after, but the last one is now replacement).
     * 
     * This method WILL now work when there is only 1 PipelineSegment.
     * It does not need to work when the list has 0 size.
     * 
     * Hint: you should use the size() and set() methods on List
     */
    public void replaceEnd(PipelineSegment replacement) 
    {
        segments.set(segments.size() - 1, replacement);
    }
}
