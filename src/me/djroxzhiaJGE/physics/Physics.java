package me.djroxzhiaJGE.physics;

import me.djroxzhiaJGE.entities.Entity;
import me.djroxzhiaJGE.entities.EntityManager;
import me.djroxzhiaJGE.math.Time;
import me.djroxzhiaJGE.math.Vector2;
import me.djroxzhiaJGE.tiles.Tile;
import me.djroxzhiaJGE.tiles.World;

public class Physics {
	
	private float gravity;
	
	private Time time;
	
	public Physics(float gravity, Time time)
	{
		this.gravity = gravity;
		this.time = time;
	}
	
	public void checkGravity(Entity e, World world)
	{
		float velocity = (float) (gravity * time.getDelta());
		int xl = (int)(e.getPos().getX() + e.getHitbox().x)/Tile.TILE_WIDTH;
		int xr = (int)(e.getPos().getX() - e.getHitbox().x + e.getHitbox().width)/Tile.TILE_WIDTH;
		int y = (int)((e.getPos().getY() + e.getHitbox().y + e.getHitbox().height + velocity)/Tile.TILE_HEIGHT);
		int yTile = (int)((e.getPos().getY() + e.getHitbox().y + e.getHitbox().height)/Tile.TILE_HEIGHT);
		
		if(y < e.getWorld().getWorldHeight())
		{
			if(!e.isJumping())
			{
				if(world.getTile(world.getTileID(xl, y)).isSolid() == false &&
					world.getTile(world.getTileID(xr, y)).isSolid() == false)
				{
					System.out.println("s");
					e.setVelocity(new Vector2(e.getVelocity().getX(), velocity));
					e.setFalling(true);
				} else if(world.getTile(world.getTileID(xl, y)).isSolid() == true ||
					world.getTile(world.getTileID(xr, y)).isSolid() == true)
				{
					if((yTile*Tile.TILE_HEIGHT) > e.getPos().getY())
					{
						System.out.println("2");
						e.setVelocity(new Vector2(e.getVelocity().getX(), (yTile*Tile.TILE_HEIGHT) - e.getPos().getY()));
						e.setFalling(true);
					} else
						System.out.println("3");
						e.setFalling(false);
				}
			}
		}
			
	}
	
	public boolean checkCollisionWithEntity(Entity entity, EntityManager m, float xOffset, float yOffset, boolean collideWithItem)
	{
		for(Entity e : m.getEntities())
		{
			if(e.equals(entity))
				continue;
			if(!collideWithItem)
				if(me.djroxzhiaJGE.entities.Item.class.isAssignableFrom(e.getClass()))
				{
					continue;
				}
			
			
			if(e.getHitbox(0.0f, 0.0f).intersects(entity.getHitbox(xOffset, yOffset)))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionWithEntity(Entity entity, EntityManager m, float xOffset, float yOffset, Entity target)
	{
		for(Entity e : m.getEntities())
		{
			if(e.equals(entity))
				continue;
			if(e.equals(target))
			{				
				if(e.getHitbox(0.0f, 0.0f).intersects(entity.getHitbox(xOffset, yOffset)))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void checkCollisionWithTileX(Entity e, World world)
	{
		int xl = (int)(e.getPos().getX() + e.getHitbox().x + e.getVelocity().getX())/Tile.TILE_WIDTH;
		int xr = (int)(e.getPos().getX() - e.getHitbox().x + e.getHitbox().width + e.getVelocity().getX())/Tile.TILE_WIDTH;
		int yd = (int)(e.getPos().getY() - e.getHitbox().y + e.getHitbox().height)/Tile.TILE_HEIGHT;
		int yu = (int)(e.getPos().getY() + e.getHitbox().y)/Tile.TILE_HEIGHT;
		
		if(e.getVelocity().getX() > 0)
		{
			int tu = world.getTileID(xr, yu);
			int td = world.getTileID(xr, yd);
			
			if(world.getTile(tu).isSolid() == false && world.getTile(td).isSolid() == false)
			{
				e.setxMove(e.getVelocity().getX());
			}else
			{
				return;
			}
		} else if(e.getVelocity().getX() < 0)
		{
			int tu = world.getTileID(xl, yu);
			int td = world.getTileID(xl, yd);
			if(world.getTile(tu).isSolid() == false && world.getTile(td).isSolid() == false)
			{
				e.setxMove(e.getVelocity().getX());
			} else
			{
				return;
			}
		}
	}
	
	public void checkCollisionWithTileY(Entity e, World world, boolean gravity)
	{
		int xl = (int)((e.getPos().getX()+1) + e.getHitbox().x)/Tile.TILE_WIDTH;
		int xr = (int)((e.getPos().getX()-1) - e.getHitbox().x + e.getHitbox().width)/Tile.TILE_WIDTH;
		int yu = (int)((e.getPos().getY()+1) + e.getHitbox().y + e.getVelocity().getY())/Tile.TILE_HEIGHT;
		int yd = (int)((e.getPos().getY()-1) - e.getHitbox().y + e.getHitbox().height)/Tile.TILE_HEIGHT;

		if(e.getVelocity().getY() > 0)
		{
			if(gravity)
				e.setyMove(e.getVelocity().getY());
			else
			{
				int tl = world.getTileID(xl, yd);
				int tr = world.getTileID(xr, yd);
				if(world.getTile(tl).isSolid() == false && world.getTile(tr).isSolid() == false)
				{
					e.setyMove(e.getVelocity().getY());
				} else
				{
					return;
				}
			}
				
		} else if(e.getVelocity().getY() < 0)
		{
			int tl = world.getTileID(xl, yu);
			int tr = world.getTileID(xr, yu);
			if(world.getTile(tl).isSolid() == false && world.getTile(tr).isSolid() == false)
			{
				e.setyMove(e.getVelocity().getY());
			} else
			{
				return;
			}
		}
	}
	
	public void jump(Entity e)
	{
		if(e.getMomentum().getY() > 0)
		{
			e.setVelocity(new Vector2(e.getVelocity().getX(), (float)(0-(7.5))));
			if(e.getMomentum().getY() > 1)
				e.setMomentum(new Vector2(e.getVelocity().getX(), (float) (e.getMomentum().getY() - 30)));
			else
				e.setMomentum(new Vector2(e.getMomentum().getX(), 0));
		} else 
			e.setJumping(false);
	}
	
	public float getGravity()
	{
		return gravity;
	}
	
	public void setGravity(float gravity)
	{
		this.gravity = gravity;
	}
	
	public Time getTime()
	{
		return time;
	}
}
