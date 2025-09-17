package me.djroxzhiaJGE.graphics;

public class Camera {

	private float xOffset, yOffset;
	private int worldWidth, worldHeight, tileWidth, tileHeight;
	
	public Camera(int tileWidth, int tileHeight)
	{
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.xOffset = 0.0f;
		this.yOffset = 0.0f;
	}
	
	public void checkBorders()
	{
		if(xOffset < 0)
		{
			xOffset = 0;
		} else if(xOffset > (worldWidth * tileWidth) - Window.width)
		{
			xOffset = (worldWidth * tileWidth) - Window.width;
		}
		
		if(yOffset < 0)
		{
			yOffset = 0;
		} else if(yOffset > (worldHeight * tileHeight) - Window.height)
		{
			yOffset = (worldHeight * tileHeight) - Window.height;
		}
	}
	
	public void center(float x, float y, float width, float height, boolean checkBorders)
	{
		xOffset = x - Window.width / 2 + width;
		yOffset = y - Window.height / 2 + height;
		if(checkBorders)
			checkBorders();
	}
	
	public void move(float xAmt, float yAmt, boolean checkBorders)
	{
		xOffset += xAmt;
		yOffset += yAmt;
		if(checkBorders)
			checkBorders();
	}

	public float getxOffset() 
	{
		return xOffset;
	}

	public void setxOffset(float xOffset) 
	{
		this.xOffset = xOffset;
	}

	public float getyOffset() 
	{
		return yOffset;
	}

	public void setyOffset(float yOffset) 
	{
		this.yOffset = yOffset;
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	public void setWorldWidth(int worldWidth) {
		this.worldWidth = worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}

	public void setWorldHeight(int worldHeight) {
		this.worldHeight = worldHeight;
	}
	
}
