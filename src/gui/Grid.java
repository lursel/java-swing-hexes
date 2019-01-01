package gui;

import java.awt.*;
import javax.swing.JPanel;
import elements.*;

public class Grid extends JPanel 
{
	World world;
	private int rectWidth;
	private int rectHeight;
	private int HEXSIZE = 15;

	public Grid(World startWorld)
	{
		this.world = startWorld;
	}

	@Override
	public Dimension getPreferredSize()
	{
		Dimension d = new Dimension(world.getSizeX()*30 , world.getSizeY()*30 );
		return d;
	}

	/**
	 * Method for calculating the six corners (edges) of a Hexagon.<br>
	 * Takes the x/y coordinate of the centre, and the size of the Hex<br>
	 * For every corner, do the math and return the x,y coordinates<br>
	 * Returns the size corners (edges)
	 */
	public int[][] calculateHexEdges(int centrex, int centrey, int size)
	{
		int[] centre = { centrex, centrey };
		int[][] edges = new int[2][6];

		for (int corner = 0; corner < 6; corner++)
		{
			double angle_degree = 60 * corner + 30;
			double angle_radius = Math.PI / 180 * angle_degree;
			edges[0][corner] = (int) (centre[0] + size * Math.cos(angle_radius));
			edges[1][corner] = (int) (centre[1] + size * Math.sin(angle_radius));

		}
		return edges;
	}

	/**
	 * Method for calculating the pixel centre of a Hex.<br>
	 * Take x and y coordinate, and the size<br>
	 * Calculate height, width. From there, calculate the centre position.<br>
	 * (!) even-odd-even-odd row structure<br>
	 * Returns the new coordinates in double[2]
	 */
	public double[] calculatePixelHexCentre(int ax, int ay, int size)
	{
		double h = size * 2; //height
		double w = Math.sqrt(3) / 2 * h; //width
		double[] newcoords = new double[2];
		newcoords[0] = (w / 2) + ax * w;
		newcoords[1] = (h / 2) + ay * ((3.0 / 4.0) * h);
		if (ay % 2 == 1)
		{
			newcoords[0] = newcoords[0] + (w / 2);
		}
		return newcoords;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawHexGrid(g2d, world, HEXSIZE);
	}

	private void drawHexGrid(Graphics2D g, World world, int size)
	{
		for (int i = 0; i < world.getSizeX(); i++)
		{
			for (int j = 0; j < world.getSizeY(); j++)
			{
				double[] centres = calculatePixelHexCentre(i, j, size);
				int[][] edges = calculateHexEdges((int) centres[0], (int) centres[1], size);
				Polygon test = new Polygon(edges[0], edges[1], 6);
				g.setColor(Color.black);
				g.drawPolygon(test);
				g.setColor(Color.LIGHT_GRAY);
				
				
				if (world.getHex(i, j).hasAgent())
				{
						g.setColor(Color.orange);
				}
				g.fillPolygon(test);
			}
		}
	}

	public int getGridWidth()
	{
		return rectWidth;
	}

	public int getGridHeight()
	{
		return rectHeight;
	}

}
