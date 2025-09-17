package me.djroxzhiaJGE.entities;

import java.awt.image.BufferedImage;

import me.djroxzhiaJGE.graphics.render.Renderer;
import me.djroxzhiaJGE.physics.Physics;
import me.djroxzhiaJGE.tiles.World;

public abstract class Item extends Entity 
{

	private int id;
	private int count;
	
	public Item(float x, float y, float width, float height, float weight, BufferedImage texture, Renderer renderer, World world, Physics physics, int id) 
	{
		super(x, y, width, height, weight, texture, renderer, world, physics);
		this.id = id;
	}

	@Override
	public abstract void tick();

	public abstract void render(float x, float y, float width, float height);

	@Override
	public abstract void render();

	@Override
	public abstract void hurt(float damage);
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
}
