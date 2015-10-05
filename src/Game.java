import java.util.*;

public class Game 
{
	public ArrayList<String> playerInfo = new ArrayList<String>();
	public ArrayList<Question> questions = new ArrayList<Question>();
	public ArrayList<Integer> playerIndices = new ArrayList<Integer>();
	public ArrayList<String> playerData = new ArrayList<String>();
	boolean tossupCatOn = true;
	boolean bonusCatOn = true;
	int team1Players = 0;
	int team2Players = 0;
	String team1Name = "";
	String team2Name = "";
	int team1Score = 0;
	int team2Score = 0;
	
	public Game(Team team1, Team team2, int team1Points, int team2Points, String tournamentName, String round, boolean tCatOn, boolean bCatOn)
	{
		tossupCatOn = tCatOn;
		bonusCatOn = bCatOn;
		team1Players = team1.players.size();
		team2Players = team2.players.size();
		team1Name = team1.getTeamName();
		team2Name = team2.getTeamName();
		team1Score = team1Points;
		team2Score = team2Points;
		
		playerInfo.add("#");
		if(tossupCatOn)
		{
			playerInfo.add("Tossup");
		}
		for(Player player : team1.players)
		{
			playerInfo.add(player.getPlayerName());
			playerData.add(player.getData());
			int playerIndex = playerInfo.indexOf(player.getPlayerName());
			playerIndices.add(playerIndex);
		}
		playerInfo.add("B Points");
		playerInfo.add("Score");
		if(bonusCatOn)
		{
			playerInfo.add("Bonus");
		}
		for(Player player : team2.players)
		{
			playerInfo.add(player.getPlayerName());
			playerData.add(player.getData());
			int playerIndex = playerInfo.indexOf(player.getPlayerName());
			playerIndices.add(playerIndex);
		}
		playerInfo.add("B Points");
		playerInfo.add("Score");
	}
	
	public void addQuestion(Question question)
	{
		questions.add(question);
	}
	
	public boolean isTCatEnabled()
	{
		return tossupCatOn;
	}
	
	public boolean isBCatEnabled()
	{
		return bonusCatOn;
	}
}
