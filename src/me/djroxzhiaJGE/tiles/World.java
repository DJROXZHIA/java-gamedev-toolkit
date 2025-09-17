package me.djroxzhiaJGE.tiles;

import me.djroxzhiaJGE.graphics.Camera;
import me.djroxzhiaJGE.utils.Utils;

public abstract class World {
	
	protected int worldWidth, worldHeight, spawnX, spawnY;
	protected String path;
	protected int tiles[][];
	
	protected Camera camera;
	
	protected Tile[] tileColl;
	
	public World(String path, Camera camera)
	{
		this.camera = camera;
		this.path = path;
		loadWorld(path);
	}
	
	public abstract void tick();
	public abstract void render();
	
	protected void loadWorld(String path)
	{
		String file = Utils.loadFile(path);
		String tokens[] = file.split("\\s+");
		worldWidth = Integer.parseInt(tokens[0]);
		worldHeight = Integer.parseInt(tokens[1]);
		spawnX = Integer.parseInt(tokens[2]);
		spawnY = Integer.parseInt(tokens[3]);
		
		camera.setWorldHeight(worldHeight);
		camera.setWorldWidth(worldWidth);
		
		tiles = new int[worldWidth][worldHeight];
		for(int x = 0; x < worldWidth; x++)
		{
			for(int y = 0; y < worldHeight; y++)
			{
				try
				{
					if(tokens[(x + y * worldHeight) + 4] != null)
						tiles[x][y] = Integer.parseInt(tokens[(x + y * worldHeight) + 4]);
					else
						tiles[x][y] = 0;
				} catch(ArrayIndexOutOfBoundsException e)
				{
					tiles[x][y] = 0;
				}
			}
		}
	}
	
	public int getWorldWidth()
	{
		return worldWidth;
	}
	
	public int getWorldHeight()
	{
		return worldHeight;
	}
	
	public int getTileID(int x, int y)
	{
		try
		{			
			return tiles[x][y];
		} catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public Tile getTile(int id)
	{
		try
		{			
			return tileColl[id];
		}  catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		return tileColl[0];
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
	
	public int[][] getTileLocation()
	{
		return tiles;
	}
	
	public Tile[] getTileColl()
	{
		return tileColl;
	}
}
