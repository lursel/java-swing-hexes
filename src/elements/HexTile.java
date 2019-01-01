package elements;

public class HexTile 
{
	private int[] position = new int[2]; 
	private boolean hasAgent;

	public HexTile(int newPosX, int newPosY)
	{
		position[0] = newPosX;
		position[1] = newPosY;
	}
	
	public boolean hasAgent()
	{
		return hasAgent;
	}


	public void removeAgent()
	{
		hasAgent = false;
	}

	
	public boolean addAgent()
	{
		if (this.hasAgent) return false;
		hasAgent = true;
		return true;
	}

	public int[] getPosition()
	{
		return position;
	}

	public void setPosition(int[] newPosition)
	{
		position[0] = newPosition[0];
		position[1] = newPosition[1];
	}


}