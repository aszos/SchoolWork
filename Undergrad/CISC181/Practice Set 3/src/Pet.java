import edu.udel.jatlas.gameframework.Position;

/*
 * Created by Alexander Szostek
 * Created on March 14th, 2014
 * Problem Set #3
 * */

public abstract class Pet
{
	Position position;
	boolean tagged;
	Pet friend;
	abstract int getMovementPattern();
		
	protected Pet(Position position, boolean tagged)
	{
		this.position = position;
		this.tagged = tagged;
	}
	
	Position getPosition()
	{
		return this.position;
	}
	
	void setFriend(Pet friend)
	{
		this.friend = friend;
	}
	
	void tag() {
        tagged = !tagged;
    }
    
    boolean isTagged() 
    {
        return tagged;
    }
	
	void move()
	{
	 int moveColumn, moveRow;
        if (tagged) {
            moveColumn = friend.getPosition().getColumn()-position.getColumn();
            moveRow = friend.getPosition().getRow()-position.getRow();
            moveColumn = (int)Math.signum(moveColumn); 
            moveRow = (int)Math.signum(moveRow); 
        }
        else {
            moveColumn = getMovementPattern();
            moveRow = getMovementPattern();
        }
        position.setColumn(position.getColumn()+moveColumn);
        position.setRow(position.getRow()+moveRow);
        if (position.equals(friend.getPosition())) {
            tag();
            friend.tag();
        }				
	}
}

