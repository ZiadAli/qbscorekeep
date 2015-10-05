import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImage extends JPanel 
{
    private BufferedImage img;
    public BackgroundImage(String imageFile) 
    {
    	try 
    	{
            img = ImageIO.read(new File(imageFile));
        } 
    	catch (IOException e) 
    	{
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        // paint the background image and scale it to fill the entire space
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}