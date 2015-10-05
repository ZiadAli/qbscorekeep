import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;

public class Main 
{
	public static void main (String[] args)
	{
		try 
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		    {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} 
		catch (Exception e) 
		{
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		Gui2 g = new Gui2();
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.setSize(400,400);
		g.setVisible(true);
		g.setResizable(true);
	}
}

