package me.djroxzhiaJGE.tiles;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import me.djroxzhiaJGE.graphics.Camera;
import me.djroxzhiaJGE.graphics.Window;
import me.djroxzhiaJGE.graphics.render.Renderer;
import me.djroxzhiaJGE.input.KeyManager;
import me.djroxzhiaJGE.input.MouseManager;
import me.djroxzhiaJGE.ui.Button;
import me.djroxzhiaJGE.ui.ComponentInteraction;

public class TileEditor {
	
	private World world;
	private Camera camera;
	
	private Renderer renderer;
	private MouseManager mouse;
	private KeyManager keyboard;
	
	private int[][] tiles;
	private Tile[] tileColl;
	
	private int x, y;
	private boolean minmizeSelector = false;
	
	private Button[] button;
	private boolean buttonClick = false;
	private int selectedTile;
	
	public TileEditor(World world, Renderer renderer, Camera camera, MouseManager mouse, KeyManager keyboard)
	{
		this.world = world;
		this.renderer = renderer;
		this.camera = camera;
		this.mouse = mouse;
		this.keyboard = keyboard;
		tiles = world.getTileLocation();
		tileColl = world.getTileColl();
		this.x = 100;
		this.y = 100;
		
		button = new Button[tileColl.length];
		for(int i = 0; i < button.length; i++)
		{
			Tile t = tileColl[i];
			BufferedImage[] tex = new BufferedImage[2];
			tex[0] = tileColl[i].getTexture();
			tex[1] = tileColl[i].getTexture();
			
			button[i] = new Button((float)i*x, (float)Window.height - 70, 64.0f, 64.0f, mouse,
					renderer, tex, new ComponentInteraction() 
					{
						@Override
						public void onclick()
						{
							selectedTile = t.getID();
						}
						@Override
						public void onhover()
						{
							
						}
					});
		}
	}
	
	public void tick()
	{
		getInput();
		if(minmizeSelector == false)
		{
			for(Button btn : button)
			{
				btn.tick();
				if(!buttonClick && btn.isClicked())
				{
					buttonClick = true;
				}
			}
		}
		
		if(mouse.isMouseLeftClick() && !buttonClick)
		{
			int mouseX = (int) ((mouse.getMouseX() + camera.getxOffset()) / Tile.TILE_WIDTH);
			int mouseY = (int) ((mouse.getMouseY() + camera.getyOffset()) / Tile.TILE_HEIGHT);
			tiles[mouseX][mouseY] = selectedTile;
		}
		buttonClick = false;
	}
	
	public void render()
	{
		for(int x = 0; x < world.getWorldWidth(); x++)
		{
			for(int y = 0; y < world.getWorldHeight(); y++)
			{
				tileColl[tiles[x][y]].render(x * Tile.TILE_WIDTH - camera.getxOffset(), y * Tile.TILE_HEIGHT - camera.getyOffset());
			}
		}
		
		if(minmizeSelector == false)
			renderer.submit(new Rectangle(0, Window.height - 100, Window.width, Window.height));
		
		if(minmizeSelector == false)
		{
			for(Button btn : button)
			{
				btn.render();
			}
		}
	}
	
	public void getInput()
	{
		if(keyboard.getKey(KeyEvent.VK_W))
		{
			y -= 15;
		} else if(keyboard.getKey(KeyEvent.VK_S))
		{
			y += 15;
		}
		if(keyboard.getKey(KeyEvent.VK_A))
		{
			x -= 15;
		} else if(keyboard.getKey(KeyEvent.VK_D))
		{
			x += 15;
		}
		camera.center(x, y, 0, 0, true);
		
		if(keyboard.getJustPressed(KeyEvent.VK_E))
		{
			System.out.println("s");
			if(!minmizeSelector)
				minmizeSelector = true;
			else
				minmizeSelector = false;
		}
		if(keyboard.getJustPressed(KeyEvent.VK_F))
		{
			try {
				saveWorld();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void saveWorld() throws IOException
	{
		StringBuilder builder = new StringBuilder();
		builder.append(world.getWorldWidth() + " " + world.getWorldHeight() + "\n");
		builder.append(world.getSpawnX() + " " + world.getSpawnY() + "\n");
		for(int x = 0; x < world.getWorldWidth(); x++)
		{
			for(int y = 0; y < world.getWorldHeight(); y++)
			{
				builder.append(tiles[x][y] + " ");
			}
		}
		FileOutputStream writer = new FileOutputStream(new File(world.path));
		String level = builder.toString();
		writer.write(level.getBytes());
		writer.flush();
		writer.close();
		System.out.println("saved");
	}
}
