package me.djroxzhiaJGE.ui;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.djroxzhiaJGE.graphics.Renderable;
import me.djroxzhiaJGE.graphics.render.Renderer;
import me.djroxzhiaJGE.input.MouseManager;

public class Button extends UIComponent {
	
	private BufferedImage textures[];
	private BufferedImage texture;
	private ComponentInteraction interactor;
	private Renderable btn1;
	private Renderable btn2;
	private Rectangle pos;
	private boolean isClicked = false;
	
	public Button(float x, float y, float width, float height, MouseManager mouse, Renderer renderer, BufferedImage[] textures, ComponentInteraction interactor) 
	{
		super(x, y, width, height, mouse, renderer);
		this.textures = textures;
		this.interactor = interactor;
		
		btn1 = new Renderable((int)x, (int)y, (int)width, (int)height, textures[0]);
		btn2 = new Renderable((int)x, (int)y, (int)width, (int)height, textures[1]);
	}
	
	public Button(float x, float y, float width, float height, MouseManager mouse, Renderer renderer, BufferedImage texture, ComponentInteraction interactor) 
	{
		super(x, y, width, height, mouse, renderer);
		this.texture = texture;
		textures = new BufferedImage[1];
		this.textures[0] = texture;
		this.interactor = interactor;
		
		btn1 = new Renderable((int)x, (int)y, (int)width, (int)height, texture);
		btn2 = null;
	}
	
	public Button(float x, float y, float width, float height, MouseManager mouse, Renderer renderer, ComponentInteraction interactor)
	{
		super(x, y, width, height, mouse, renderer);
		this.interactor = interactor;
		pos = new Rectangle((int)x, (int)y, (int)width, (int)height);
		btn1 = null;
		btn2 = null;
	}
	
	public void tick()
	{
		if(bounds.contains(mouse.getMouseX(), mouse.getMouseY()))
		{
			hovering = true;
			interactor.onhover();
		} else 
		{
			hovering = false;
		}
		if(bounds.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.isMouseLeftClick())
		{
			isClicked = true;
			interactor.onclick();
			
		} else
			isClicked = false;
	}
	
	public void render()
	{
		if(btn1!=null)
		{			
			btn1.setX(x);
			btn1.setY(y);
			btn1.setWidth(width);
			btn1.setHeight(height);
			btn1.setTexture(textures[0]);
		}
		if(btn2!=null)
		{			
			btn2.setX(x);
			btn2.setY(y);
			btn2.setWidth(width);
			btn2.setHeight(height);
			btn2.setTexture(textures[1]);
		}
		if(pos!=null)
		{
			pos.x = (int) x;
			pos.y = (int) y;
			pos.width = (int) width;
			pos.height = (int) height;
			bounds.x = (int) pos.x;
			bounds.y = (int) pos.y;
			bounds.width = (int) pos.width;
			bounds.height = (int) pos.height;
		}
		
		if(hovering == true)
		{			
			if(btn2 != null)
			{
				renderer.submit(btn2);
			}
			
			if(pos!= null)
			{
				renderer.submit(pos);
			}
			
			if(btn1 != null)
			{
				renderer.submit(btn1);
			}
		} else
		{	
			if(btn1 != null)
			{
				renderer.submit(btn1);
			}
			else if(pos!= null)
			{
				renderer.submit(pos);
			}
		}
			
	}
	
	public BufferedImage getTexture()
	{
		return texture;
	}
	
	public boolean isClicked()
	{
		return isClicked;
	}
	
	public void setClicked(boolean c)
	{
		this.isClicked = c;
	}
}
