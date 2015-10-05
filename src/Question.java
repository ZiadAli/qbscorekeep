import java.util.ArrayList;
import java.util.HashMap;

public class Question 
{
	int qNumber = 0;
	String tossupAbbrev = "";
	String bonusAbbrev = "";
	ArrayList<String> questionData = new ArrayList<String>();
	
	public Question()
	{

	}
	
	public Question(int questionNumber, String categoryAbbreviation, String bonusAbbreviation, ArrayList<Integer> questionData2)
	{
		qNumber = questionNumber;
		tossupAbbrev = categoryAbbreviation;
		bonusAbbrev = bonusAbbreviation;
	}
	
	public void addData(String data)
	{
		questionData.add(data);
	}
	
	public void setQuestionNumber(int questionNumber)
	{
		qNumber = questionNumber;
	}
	
	public void setTossupAbbrev (String categoryAbbreviation)
	{
		tossupAbbrev = categoryAbbreviation;
	}
	
	public void setBonusAbbrev(String bonusAbbreviation)
	{
		bonusAbbrev = bonusAbbreviation;
	}
	
	
}
