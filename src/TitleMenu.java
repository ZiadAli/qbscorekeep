import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleMenu extends JPanel
{
	private JLabel titleLabel = new JLabel("Quiz Bowl Scoreekeeper");
	public JButton tournamentButton = new JButton("Tournament");
	public JButton practiceButton = new JButton("Practice");
	public JButton settingsButton = new JButton("Settings");
	public JButton helpButton = new JButton("Help");
	private JLabel backgroundTitleMenu = new JLabel();
	private BackgroundImage backgroundImage = new BackgroundImage("/Users/Ziad/Documents/QuizBowlProgram/blurredbackground.jpg");
	
	public TitleMenu()
	{
		this.setOpaque(false);
		titleLabel.setFont(titleLabel.getFont().deriveFont(90.0f));
		this.add(titleLabel);
		this.add(tournamentButton);
		this.add(practiceButton);
		this.add(settingsButton);
		this.add(helpButton);
		
	}
}
