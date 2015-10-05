import java.util.*;

public class Team 
{
	ArrayList<Player> players = new ArrayList<Player>();
	String name = "";
	
	public Team(ArrayList<Player> players2)
	{
		players = players2;
	}
	
	public void setTeamName(String nameSet)
	{
		name = nameSet;
	}
	
	public String getTeamName()
	{
		return name;
	}
}
