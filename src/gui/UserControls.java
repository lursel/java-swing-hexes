package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import events.EventManager;
import events.TickEnd;

@SuppressWarnings("serial")
public class UserControls extends JPanel implements Observer
{

	JButton exitButton = new JButton("Exit");
	private int tick = 0;
	private int realtick;
	EventManager eventMgr;
	JTextField showTick = new JTextField("Tick: " + tick);

	public UserControls(final Grid grid, EventManager event)
	{
		eventMgr = event;
		this.add(exitButton);
		this.add(showTick);
		
		showTick.setPreferredSize(new Dimension(80, 24));		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}});
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (arg instanceof TickEnd)
		{
			realtick++;
			showTick.setText("Tick: "+ realtick);
			TickEnd tickEndOtherName = (TickEnd) arg;
			tick = tickEndOtherName.getTick();
		}	
	}

}
