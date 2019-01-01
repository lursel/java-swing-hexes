package elements;

import java.util.Observable;

public class World extends Observable 
{

	private int sizeX;
	private int sizeY;
	HexTile theWorld[][];

	public World(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		theWorld = new HexTile[sizeX][sizeY];

		for (int x = 0; x < sizeX; x++) 
		{
			for (int y = 0; y < sizeY; y++) 
			{
				theWorld[x][y] = new HexTile(x, y);
			}
		}

	}

	public HexTile getHex(int x, int y)
	{
		if (x >= sizeX || x < 0)
		{
			return null;
		}
		if (y >= sizeY || y < 0)
		{
			return null;
		}
		return theWorld[x][y];
	}

	public void removeUnitFromTile(int x, int y)
	{
		if (x >= sizeX || x < 0)
		{
			return;
		}
		if (y >= sizeY || y < 0)
		{
			return;
		}
		theWorld[x][y].removeAgent();
	}

	public boolean placeUnitOnTile(int x, int y)
	{
		if (x >= sizeX || x < 0)
		{
			return false;
		}
		if (y >= sizeY || y < 0)
		{
			return false;
		}
		if (theWorld[x][y].hasAgent())
		{
			return false;
		}
		return theWorld[x][y].addAgent();

	}

	public int getSizeX()
	{
		return sizeX;
	}

	public int getSizeY()
	{
		return sizeY;
	}
}