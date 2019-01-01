package simulation;

import events.EventManager;
import gui.Frame;

public class SimController {

	static Simulation simulation;

	Frame frame;
	static EventManager emgr;
	static SimController controller;


	public static void main(String[] args) {
		simulation = new Simulation();
		Frame frame = new Frame(simulation.getWorld(), emgr);
		frame.open();
		simulation.addObserver(frame.getUserControls());
		simulation.run();
		frame.dispose();
		System.exit(0);
	} 
}
