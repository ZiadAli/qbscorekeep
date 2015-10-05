import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLDocument.Iterator;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class Gui2 extends JFrame
{
	private JButton button1, button2, button3, button4, addPlayer;
	private JToggleButton toggleButton1 = new JToggleButton();
	private JToggleButton toggleButton2 = new JToggleButton();
	private JPanel panel1 = new JPanel(), panel2 = new JPanel(), panel3 = new JPanel(), panel4 = new JPanel(), contentPanel = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	private boolean firstClick = true;
	private History historyPanel = new History();
	private TitleMenu titleMenu = new TitleMenu();
	private BackgroundImage backgroundImage = new BackgroundImage("/Users/Ziad/Documents/QuizBowlProgram/blurredbackground.jpg");
	public Player Ziad = new Player("Ziad", 12);
	public Player Vanessa = new Player("Vanessa", 12);
	public Player Elizabeth = new Player("Elizabeth", 12);
	public Player Jared = new Player("Jared", 12);
	public Player Grey = new Player("Grey", 10);
	public Player Tejas = new Player("Tejas", 9);
	public Player Jack = new Player("Jack", 11);
	public Player Stefano = new Player("Stefano", 11);
	public Player playerA1 = new Player("Player 1", 12);
	public Player playerA2 = new Player("Player 2", 12);
	public Player playerB1, playerB2, playerB3, playerB4;
	ArrayList<Player> unselectedPlayers = new ArrayList<Player>();
	public ArrayList<Player> aTeam = new ArrayList<Player>();
	public ArrayList<Player> bTeam = new ArrayList<Player>();
	GameCenter gameCenter = new GameCenter();
	int playerIndex = 0;
	actionListener al = new actionListener();
	StatsWriter statsWriter = new StatsWriter();
	ExcelWriter eWriter = new ExcelWriter();
	
	public Gui2()
	{		
		super("Card Layout Program"); //Names the program
		button1 = new JButton("Go to the second card"); //Names the buttons and instantiates them
		button2 = new JButton("Go back to first card");
		button3 = new JButton("Go to the third card");
		button4 = new JButton("Go back to first card");
		addPlayer = new JButton("Add Player");
		
		ImageIcon buttonIMG = new ImageIcon("/Users/Ziad/Documents/QuizBowlProgram/carter.jpg");
		Image img = buttonIMG.getImage();
		Image img2 = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon buttonIMG2 = new ImageIcon(img2);
		toggleButton1.setIcon(buttonIMG2);	
		toggleButton1.setVerticalTextPosition(SwingConstants.BOTTOM);
		toggleButton1.setHorizontalTextPosition(SwingConstants.CENTER);
		toggleButton1.setMargin(new Insets(1,1,1,1));
		toggleButton1.setBorder(new LineBorder(Color.WHITE,8));
		toggleButton1.setBorderPainted(false);

		toggleButton2.setVisible(false);
		
		historyPanel.setBackground(Color.CYAN); //Sets background colors of all of the panels
		backgroundImage.add(titleMenu);
		titleMenu.setBackground(Color.MAGENTA);
		panel1.setBackground(Color.PINK);
		panel2.setBackground(Color.ORANGE);
		panel3.setBackground(Color.GREEN);
		panel4.setBackground(Color.BLUE);
		
		button1.addActionListener(al); //Adds action listeners to all of the buttons
		button2.addActionListener(al);
		button3.addActionListener(al);
		button4.addActionListener(al);
		toggleButton1.addActionListener(al);
		toggleButton2.addActionListener(al);
		historyPanel.historyButton.addActionListener(al);
		addPlayer.addActionListener(al);
		titleMenu.tournamentButton.addActionListener(al);
		titleMenu.practiceButton.addActionListener(al);
		titleMenu.settingsButton.addActionListener(al);
		titleMenu.helpButton.addActionListener(al);
		gameCenter.addScore.addActionListener(al);
		Ziad.toggleButton.addActionListener(al);
		playerA1.toggleButton.addActionListener(al);
		playerA2.toggleButton.addActionListener(al);
		gameCenter.gameToggle.addActionListener(al);
		
		Ziad.setData("PTest5$D$5");
		//Elizabeth.setData("Test2$N$5");
		//Jared.setData("Test2$Y$5");
		
		aTeam.add(Ziad);
		aTeam.add(Elizabeth);
		aTeam.add(Vanessa);
		aTeam.add(Jared);
		bTeam.add(Jack);
		bTeam.add(Stefano);
		bTeam.add(Grey);
		bTeam.add(Tejas);
		
		Team ecgA = new Team(aTeam);
		Team ecgB = new Team(bTeam);
		ecgA.setTeamName("Early College at Guilford A");
		ecgB.setTeamName("Early College at Guilford B");
		
		Game testGame = new Game(ecgA, ecgB, 200, 300, "HSNCT", "Round 1", true, true);
		
		//Question rQuestion1 = new Question(1, "EHist", );
		
		Question question1 = new Question();
		question1.addData("2$15");
		question1.addData("4$10");
		question1.setTossupAbbrev("USHist");
		question1.setBonusAbbrev("USCE");
		Question question2 = new Question();
		question2.addData("3$-5");
		question2.addData("2$0");
		question2.addData("4$0");
		question2.addData("5$0");
		question2.setTossupAbbrev("USLit");
		Question question3 = new Question();
		question3.addData("4$10");
		question3.addData("5$0");
		question3.setTossupAbbrev("USLit");
		question3.setBonusAbbrev("BLit");
		Question question4 = new Question();
		question4.setTossupAbbrev("Bio");
		Question question5 = new Question();
		question5.setTossupAbbrev("AnHist");
		question5.addData("2$15");
		Question question6 = new Question();
		question6.setTossupAbbrev("EHist");
		question6.addData("2$10");
		question6.addData("3$-5");
		question6.addData("4$0");
		question6.addData("5$0");
		
		testGame.addQuestion(question1);
		testGame.addQuestion(question2);
		testGame.addQuestion(question3);
		testGame.addQuestion(question4);
		testGame.addQuestion(question5);
		testGame.addQuestion(question6);
		
		//eWriter.createWorkbook("ZiadTestWorkbook2"); //Already Created
		String eWriterFile = "//Users//Ziad//Documents//QuizBowlProgram//ZiadTestWorkbook2.xlsx";
		eWriter.getWorkbook(eWriterFile);
		eWriter.resetCategoriesStats();
		eWriter.masterPlayerPage("Master", 2, 3);
		eWriter.masterPlayerCategories("Master", 2, 15);
		eWriter.teamPage("Test5", ecgA, 5, 44);
		eWriter.playerPage("Test5", "Ziad", 5, 5);
		eWriter.playerPage("Test5", "Vanessa", 5, 18);
		eWriter.playerPage("Test5", "Elizabeth", 5, 31);
		
		eWriter.recordGame("Test3", testGame, 0, 0);
		eWriter.readScore("Test4", 2, 0, false);
		eWriter.readScore("Test3", 2, 0, false);
		eWriter.sortMasterPlayers("Master");
		eWriter.sortMasterPlayerCategory("Master");
		eWriter.closeWorkbook(eWriterFile);
		
		Ziad.setPlayerImage(new File("/Users/Ziad/Documents/QuizBowlProgram/homer.png"));
		Tejas.setPlayerImage(new File("/Users/Ziad/Documents/QuizBowlProgram/carter.jpg"));
		
		unselectedPlayers.add(playerA1);
		unselectedPlayers.add(playerA2);
		//unselectedPlayers.get(1).setPlayerName("Alfred");
		System.out.println(unselectedPlayers.get(0).getPlayerName());
		System.out.println(unselectedPlayers.get(1).getPlayerName());
		
		panel1.add(button1); //Adds buttons to respective panels
		panel1.add(button2);
		panel1.add(toggleButton1);
		panel1.add(toggleButton2);
		panel1.add(addPlayer);
		panel3.add(button3);
		panel4.add(button4);
		
		contentPanel.setLayout(cardLayout); //Sets layout of content panel, which holds all other panels, as card layout
		
		contentPanel.add(panel1, "Panel 1"); //Adds all panels to content panel and names them using a string
		contentPanel.add(panel2, "Panel 2");
		contentPanel.add(panel3, "Panel 3");
		contentPanel.add(panel4, "Panel 4");
		contentPanel.add(historyPanel, "History Panel");
		contentPanel.add(backgroundImage, "Title Menu");
		contentPanel.add(gameCenter, "Game Center");
		this.setContentPane(contentPanel); //Makes the content panel the content pane
		
		cardLayout.show(contentPanel, "Title Menu"); //Determines which "card" (panel) is showing upon load
	}
	
	public class actionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object src = e.getSource();
			if(src.equals(addPlayer))
			{

				String playerName = JOptionPane.showInputDialog("Name");
				String playerGrade = JOptionPane.showInputDialog("Grade");
				File fileSelected = null;
				//Player newPlayer = new Player("Ziad", 12);
				FileChooser chooser = new FileChooser();
				chooser.UIFunction();
				if(FileChooser.chooserReturn == JFileChooser.APPROVE_OPTION)
				{
					fileSelected = FileChooser.chooser.getSelectedFile();
				}
				if(playerIndex < 2)
				{
					System.out.println(playerName);
					System.out.println(playerGrade);
					System.out.println(fileSelected.toString());
					unselectedPlayers.get(playerIndex).setPlayerName(playerName);
					unselectedPlayers.get(playerIndex).setPlayerGrade(Integer.parseInt(playerGrade));
					unselectedPlayers.get(playerIndex).setPlayerImage(fileSelected);
					unselectedPlayers.get(playerIndex).toggleButton.addActionListener(al);
					System.out.println("Player " + playerIndex+1 + "'s name: " + unselectedPlayers.get(playerIndex).getPlayerName());
					aTeam.add(unselectedPlayers.get(playerIndex));
					//unselectedPlayers.remove(0);
					playerIndex++;
					System.out.println(playerIndex);
				}
				
			}
			if(src.equals(titleMenu.tournamentButton))
			{
				cardLayout.show(contentPanel, "Game Center");
				System.out.println("Tournament button pressed");
				gameCenter.addButtons(aTeam, bTeam);
			}
			if(src.equals(titleMenu.practiceButton))
			{
				cardLayout.show(contentPanel, "Panel 1");
			}
			if(src.equals(titleMenu.settingsButton))
			{
				
			}
			if(src.equals(titleMenu.helpButton))
			{
				
			}
			if(src.equals(gameCenter.gameToggle))
			{
				System.out.println("Game toggle pushed");
				if(gameCenter.gameToggle.isSelected())
				{
					System.out.println("Ziad Selected");
				}
				if(gameCenter.gameToggle.isSelected() == false)
				{
					System.out.println("Ziad unselected");
				}
			}
			/*if(src.equals(gameCenter.addScore))
			{
				System.out.println(Ziad.isSelected);
				for(int i=0; i< aTeam.size(); i++)
				{
					if(aTeam.get(i).isSelected)
					{
						aTeam.get(i).addScore();
						System.out.println("Score added");
					}
				}
				for(int i=0; i< aTeam.size(); i++)
				{
					System.out.print(aTeam.get(i).name + " ");
					System.out.println(aTeam.get(i).score);
				}
			}*/
			if(src.equals(toggleButton1))
			{
				toggleButton2.setSelected(false);
			}
			if(src.equals(toggleButton2))
			{
				toggleButton1.setSelected(false);
			}
			if(toggleButton1.isSelected() && src.equals(button1))
			{
				cardLayout.show(contentPanel, "History Panel");
			}
			if(toggleButton1.isSelected() && src.equals(button2))
			{
				cardLayout.show(contentPanel, "Panel 3");
			}
			else if(src.equals(button2))
			{
				cardLayout.show(contentPanel, "Title Menu");
			}
			if(src.equals(button3))
			{
				cardLayout.show(contentPanel, "Panel 4");
			}
			if(src.equals(button4))
			{
				cardLayout.show(contentPanel, "Panel 1");
			}
			if(src.equals(historyPanel.historyButton))
			{
				cardLayout.show(contentPanel, "Panel 1");
			}
		}
	}
}

