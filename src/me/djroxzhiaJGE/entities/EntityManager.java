package me.djroxzhiaJGE.entities;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager {
	
	private ArrayList<Entity> entities;
	
	public EntityManager()
	{
		entities = new ArrayList<Entity>();
	}
	
	public void tick()
	{
		Iterator<Entity> i = entities.iterator();
		while(i.hasNext())
		{
			Entity e = i.next();
			e.tick();
			if(e.isDead())
			{
				i.remove();
			}
		}
	}
	
	public void render()
	{
		for(Entity e : entities)
		{
			e.render();
		}
	}
	
	public void add(Entity e)
	{
		entities.add(e);
	}
	
	public void remove(Entity e)
	{
		entities.remove(e);
	}
	
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
	
	public void clear()
	{
		entities.clear();
	}
}
