package simulation;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import elements.*;
import events.*;
import gui.Grid;

public class Simulation extends Observable implements Runnable {
	private int sizeX = 10;
	private int sizeY = 10;
	private int maxTicks = 50;
	private int numAgents = 13;
	ArrayList<Agent> allAgents;
	World world;
	Random prng;
	Grid grid;
	Integer tick;

	public Simulation() {
		allAgents = new ArrayList<Agent>(numAgents);
		world = new World(sizeX, sizeY);
		populateMap();
		grid = new Grid(world);
	}

	public void populateMap() {
		for (int i = 0; i < numAgents; i++) {
			Random prng = new Random();
			int x = prng.nextInt(sizeX);
			int y = prng.nextInt(sizeY);
			allAgents.add(new Agent(x, y));
			Agent agentPara = allAgents.get(i);

			HexTile currTile = world.getHex(agentPara.getX(), agentPara.getY());
			if (currTile.hasAgent()) {
				boolean hasFoundTile = false;
				while (!hasFoundTile) {
					x = prng.nextInt(world.getSizeX());
					y = prng.nextInt(world.getSizeY()); 

					HexTile findRightTile = world.getHex(x, y); 
					if (!findRightTile.hasAgent()) 
					{
						allAgents.get(i).setPosition(x, y); 
						hasFoundTile = true;
					}
				}
			}
			world.placeUnitOnTile(agentPara.getX(), agentPara.getY());
		}

	}

	public void run() {
		for (tick = 0; tick < maxTicks; tick++) {
			invokeAgentActions();
			fireEvent(new TickEnd(tick));
		}
	}

	public void invokeAgentActions() {
		fireEvent(new TickStart());
		for (int i = 0; i < numAgents; i++) {
			Random r = new Random();
			int[] newPos = { -1, -1 };
			boolean placedSuccessfully = false;
			while (!placedSuccessfully) {
				newPos[0] = r.nextInt(world.getSizeX());
				newPos[1] = r.nextInt(world.getSizeY());
				placedSuccessfully = world.placeUnitOnTile(newPos[0], newPos[1]);
			}
			world.removeUnitFromTile(allAgents.get(i).getX(), allAgents.get(i).getY());
			allAgents.get(i).setPosition(newPos[0], newPos[1]);
			try {
				Thread.sleep(200); //slow down to see the movement
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void fireEvent(Object event) {
		setChanged();
		notifyObservers(event);
	}

	public World getWorld() {
		return world;
	}

}