package me.djroxzhiaJGE.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.djroxzhiaJGE.graphics.Window;
import me.djroxzhiaJGE.input.KeyManager;
import me.djroxzhiaJGE.input.MouseManager;

public class Utils {
	
	public static BufferedImage loadImage(String path)
	{
		try
		{
			return ImageIO.read(new File(path));
		} catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static String loadFile(String path)
	{
		StringBuilder builder = new StringBuilder();
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
			{
				builder.append(line + "\n");
			}
			br.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static void keyboardInit(Window window, KeyManager keymanager)
	{
		window.getFrame().addKeyListener(keymanager);
	}
	
	public static void mouseInit(Window window, MouseManager mouseManager)
	{
		window.getFrame().addMouseListener(mouseManager);
		window.getFrame().addMouseMotionListener(mouseManager);
		window.getCanvas().addMouseListener(mouseManager);
		window.getCanvas().addMouseMotionListener(mouseManager);
		window.getCanvas().addMouseWheelListener(mouseManager);
	}
}
