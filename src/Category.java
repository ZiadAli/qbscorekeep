
public class Category 
{
	boolean categoryEnabled = true;
	public String categoryName = "";
	String parentCategory = "";
	String categoryAbbreviation = "";
	
	
	public Category(String catName, String parentCat, String catAbbrev)
	{
		categoryName = catName;
		parentCategory = parentCat;
		categoryAbbreviation = catAbbrev;
		ExcelWriter.categoryList.add(this);
	}
	
	public String getCategoryName()
	{
		return categoryName;
	}
	
	public String getCategoryAbbrev()
	{
		return categoryAbbreviation;
	}
	
	public void enableCategory()
	{
		categoryEnabled = true;
	}
	
	public void disableCategory()
	{
		categoryEnabled = false;
	}
	
	public boolean isEnabled()
	{
		return categoryEnabled;
	}
}
