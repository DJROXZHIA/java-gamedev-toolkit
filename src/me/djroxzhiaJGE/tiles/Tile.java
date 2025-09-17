package me.djroxzhiaJGE.tiles;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import me.djroxzhiaJGE.graphics.render.Renderer;

public abstract class Tile {
	
	public static int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	
	protected BufferedImage texture;
	
	protected HashMap<String, Float> floatMems = new HashMap<String, Float>();
	protected HashMap<String, Boolean> boolMems = new HashMap<String, Boolean>();
	
	protected boolean isSolid;
	protected boolean damageable;
	
	protected int id;
	
	protected Renderer renderer;
	
	public Tile(BufferedImage texture, boolean isSolid, boolean damageable, int id, Renderer renderer)
	{
		this.texture = texture;
		this.isSolid = isSolid;
		this.damageable = damageable;
		this.renderer = renderer;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(float x, float y);
	
	public void addFloatVar(String name, float value)
	{
		floatMems.put(name, value);
	}
	
	public void addBoolVar(String name, boolean value)
	{
		boolMems.put(name, value);
	}
	
	public float getFloatVar(String name)
	{
		return floatMems.get(name);
	}
	
	public boolean getBoolVar(String name)
	{
		return boolMems.get(name);
	}
	
	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public boolean isDamageable() {
		return damageable;
	}

	public void setDamageable(boolean damageable) {
		this.damageable = damageable;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	public int getID()
	{
		return id;
	}
}
