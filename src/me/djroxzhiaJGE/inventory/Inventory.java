package me.djroxzhiaJGE.inventory;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.djroxzhiaJGE.entities.Item;
import me.djroxzhiaJGE.graphics.Renderable;
import me.djroxzhiaJGE.graphics.render.Renderer;
import me.djroxzhiaJGE.input.KeyManager;
import me.djroxzhiaJGE.input.MouseManager;
import me.djroxzhiaJGE.math.Vector2;
import me.djroxzhiaJGE.ui.states.UILayer;

public class Inventory {

	private ArrayList<Item> items;
	private Item[][] itemInInv;
	private Item selectedItem;
	private int size, sizeLimit;
	private int boxPerRow, boxPerColumn;
	
	private float boxHeight, boxWidth, margin, marginY;
	private UILayer invUI;
	private Rectangle invRect;
	private BufferedImage boxTexture;
	
	private MouseManager mouse;
	private KeyManager keyboard;
	private boolean isActive = false;
	
	public Inventory(Renderer renderer, BufferedImage background, float x, float y, float width, float height, float boxWidth, float boxHeight, float margin, float marginY, MouseManager mouse, KeyManager keyboard, Rectangle invRect, int boxPerRow, int boxPerColumn, BufferedImage boxTexture)
	{
		items = new ArrayList<Item>();
		this.sizeLimit = boxPerRow * boxPerColumn;
		size = 0;
		invUI = new UILayer(renderer, background, x, y, width, height);
		this.boxHeight = boxHeight;
		this.boxWidth = boxWidth;
		this.margin = margin;
		this.marginY = marginY;
		
		this.keyboard = keyboard;
		this.mouse = mouse;
		this.invRect = invRect;
		this.boxTexture = boxTexture;
		this.boxPerRow = boxPerRow;
		this.boxPerColumn = boxPerColumn;
		
		itemInInv = new Item[boxPerRow][boxPerColumn];
		
		setupUi();
	}
	
	public void render()
	{
		if(isActive)
		{
			invUI.render();
		
			for(int x = 0; x < boxPerRow; x++)
			{
				for(int y = 0; y < boxPerColumn; y++)
				{
					if(itemInInv[x][y] != null)
					{
						itemInInv[x][y].render((x * boxWidth) + 8, (y * boxHeight) + 8, 16, 16);
					}
				}
			}
		}
	}
	
	public UILayer getInvUI()
	{
		return invUI;
	}
	
	public void add(Item i)
	{
		if(size < sizeLimit)
		{
			items.add(i);
		
			for(int x = 0; x < boxPerRow; x++)
			{
				for(int y = 0; y < boxPerColumn; y++)
				{
					if(itemInInv[x][y] != null)
					{						
						if(itemInInv[x][y].getId() == i.getId())
						{
							itemInInv[x][y].setCount(itemInInv[x][y].getCount() + i.getCount());
							i.setDead(true);
							return;
						}
					}
				}
			}
			
			for(int x = 0; x < boxPerRow; x++)
			{
				for(int y = 0; y < boxPerColumn; y++)
				{
					if(itemInInv[x][y] == null)
					{
						itemInInv[x][y] = i;
						i.setDead(true);
					}
				}
			}
		}
	}
	
	public void remove(Item i)
	{
		items.remove(i);
	}
	
	public void clear()
	{
		items.clear();
	}
	
	private void setupUi()
	{
		for(int x = 0; x < boxPerRow; x++)
		{
			for(int y = 0; y < boxPerColumn; y++)
			{
				invUI.add(new Renderable(invRect.x + (x * (boxWidth + margin)), invRect.y + margin + (y * (boxHeight + marginY)), boxWidth, boxHeight, boxTexture));
			}
		}
		
		for(int x = 0; x < boxPerRow; x++)
		{
			for(int y = 0; y < boxPerColumn; y++)
			{
				itemInInv[x][y] = null;
			}
		}
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}
}
