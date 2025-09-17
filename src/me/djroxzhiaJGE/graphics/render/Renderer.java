package me.djroxzhiaJGE.graphics.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import me.djroxzhiaJGE.graphics.Renderable;
import me.djroxzhiaJGE.graphics.Window;
import me.djroxzhiaJGE.ui.Label;

public class Renderer {
	
	private BufferStrategy bs;
	private Graphics g;
	private Window window;
	private ArrayList<Renderable> renderables;
	private ArrayList<Rectangle> rectangles;
	private ArrayList<Label> labels;
	private Color defaultColor;
	private Font defaultFont;
	
	public Renderer(Window window)
	{
		this.window = window;
		renderables = new ArrayList<Renderable>();
		rectangles = new ArrayList<Rectangle>();
		labels = new ArrayList<Label>();
		
		defaultFont = new Font("Copperplate Gothic", 0, 22);
		defaultColor = Color.white;
	}
	
	public void submit(Renderable renderable)
	{
		renderables.add(renderable);
	}
	public void submit(Rectangle rectangle)
	{
		rectangles.add(rectangle);
	}
	public void submit(Label label)
	{
		labels.add(label);
	}

	public void flush()
	{	
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) 
		{
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, Window.width, Window.height);
		for(Rectangle rect : rectangles)
		{
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
		
		for(Renderable obj : renderables)
		{
			g.drawImage(obj.getTexture(), (int)obj.getX(), (int)obj.getY(), (int)obj.getWidth(), (int)obj.getHeight(), null);
		}

		for(Label label : labels)
		{
			if(label.getFont() != null)
				g.setFont(label.getFont());
			else
				g.setFont(defaultFont);
			if(label.getColor() != null)
				g.setColor(label.getColor());
			else
				g.setColor(defaultColor);
			
			g.drawString(label.getString(), (int)label.getX(), (int)label.getY());
		}
		
		labels.clear();
		rectangles.clear();
		renderables.clear();
		
		bs.show();	
		g.dispose(); 
	}
}
