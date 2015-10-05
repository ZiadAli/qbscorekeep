import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameCenter extends JPanel
{
	Player currentPlayer = new Player();
	public JButton addScore = new JButton("Add Score");
	public JToggleButton gameToggle = new JToggleButton();
	
	public GameCenter()
	{
		this.add(addScore);
		this.add(gameToggle);
		String imageFileName2 = "C:\\Users\\Ziad\\Pictures\\blurredbackground.jpg";
		ImageIcon imageIcon1 = new ImageIcon(imageFileName2);
		Image image1 = imageIcon1.getImage();
		Image image2 = image1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon playerImage = new ImageIcon(image2);
		gameToggle.setIcon(playerImage);	
		gameToggle.setVerticalTextPosition(SwingConstants.BOTTOM);
		gameToggle.setHorizontalTextPosition(SwingConstants.CENTER);
		gameToggle.setMargin(new Insets(1,1,1,1));
		gameToggle.setBorder(new LineBorder(Color.WHITE,20));
		gameToggle.setBorderPainted(false);
		gameToggle.setVisible(true);
	}
	
	public GameCenter(ArrayList team1, ArrayList team2)
	{
		
	}
	
	public void addButtons(ArrayList team1, ArrayList team2)
	{
		Iterator<Player> iterator1 = team1.iterator();
		Iterator<Player> iterator2 = team2.iterator();
		while(iterator1.hasNext())
		{
			currentPlayer = iterator1.next();
			this.add(currentPlayer.toggleButton);
			System.out.println(currentPlayer.getPlayerName());
		}
		while(iterator2.hasNext())
		{
			currentPlayer = iterator2.next();
			this.add(currentPlayer.toggleButton);
			System.out.println(currentPlayer.getPlayerName());
		}
	}
}
