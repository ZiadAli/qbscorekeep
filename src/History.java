import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class History extends JPanel
{
	private JLabel historyLabel = new JLabel("History");
	public JButton historyButton = new JButton("History Button");
	
	public History()
	{
		this.add(historyLabel);
		this.add(historyButton);
	}
}
