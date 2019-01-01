package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import elements.World;
import events.EventManager;

public class Frame extends JFrame
{
	Grid grid;
	UserControls ui;


	public Frame(World world, EventManager event)
	{
		super("Hex!");	
		grid = new Grid(world);
		ui = new UserControls(grid, event);
		this.add(grid, BorderLayout.CENTER);
		this.add(ui, BorderLayout.NORTH);
		this.validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		grid.setBackground(Color.DARK_GRAY);
		ui.setBackground(Color.DARK_GRAY);
	}

	public UserControls getUserControls()
	{
		return ui;
	}

	public Grid getGrid()
	{
		return grid;
	}

	public void open()
	{
		setVisible(true);
		pack();

		Timer t = new Timer(5, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				grid.repaint();
			}
		});
		t.start();
	}
}
