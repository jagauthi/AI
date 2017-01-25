package HW1;

import java.util.TreeSet;

import javax.swing.JFrame;

public class Viz extends JFrame
{
	View view;
	public Viz() throws Exception
	{
		view = new View(this);
		view.addMouseListener(view);
		this.setTitle("Puzzle");
		this.setSize(482, 505);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void getNewState(GameState newState)
	{
		view.getNewState(newState);
	}
}