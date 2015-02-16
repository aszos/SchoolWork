package ps5;

/**
 * Represents the data of the pipeline.
 * 
 * @author jatlas
 */
public class PipelineSegment {
    private int length;
    private int inDiameter;
    private int outDiameter;

    public PipelineSegment(int length, int inDiameter, int outDiameter) {
        this.length = length;
        this.inDiameter = inDiameter;
        this.outDiameter = outDiameter;
    }

    public int getLength() {
        return length;
    }

    public int getInDiameter() {
        return inDiameter;
    }

    public int getOutDiameter() {
        return outDiameter;
    }
}
