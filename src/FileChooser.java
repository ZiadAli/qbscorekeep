import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;


public class FileChooser 
{
	public static int chooserReturn;
	public static JFileChooser chooser = null;
	public FileChooser()
	{
		
	}
	public void UIFunction()
	{
	    LookAndFeel previousLF = UIManager.getLookAndFeel();
		try 
		{
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		    {
		    	System.out.println(info.getName());
		        if ("Windows".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			chooser = new JFileChooser();
			UIManager.setLookAndFeel(previousLF);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	    //Add whatever other settings you want to the method
		chooserReturn = chooser.showOpenDialog(null);
	}
}
