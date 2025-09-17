package me.djroxzhiaJGE.ui;

import java.awt.Color;
import java.awt.Font;

import me.djroxzhiaJGE.graphics.render.Renderer;

public class Label extends UIComponent {

	private String str;
	private Color color;
	private Font font;
	
	public Label(String str, float x, float y, float width, float height, Renderer renderer) 
	{
		super(x, y, width, height, null, renderer);
		this.str = str;
		color = null;
		font = null;
	}
	
	public Label(String str, float x, float y, float width, float height, Color color, Font font, Renderer renderer)
	{
		super(x, y, width, height, null, renderer);
		this.str = str;
		this.color = color;
		this.font = font;
	}

	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render() 
	{
		renderer.submit(this);
	}
	
	public String getString()
	{
		return str;
	}
	
	public void setString(String str)
	{
		this.str = str;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Font getFont()
	{
		return font;
	}
	
	public void setFont(Font font)
	{
		this.font = font;
	}
}
