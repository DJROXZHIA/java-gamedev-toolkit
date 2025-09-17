package me.djroxzhiaJGE.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import me.djroxzhiaJGE.graphics.Renderable;
import me.djroxzhiaJGE.graphics.render.Renderer;
import me.djroxzhiaJGE.math.Vector2;
import me.djroxzhiaJGE.physics.Physics;
import me.djroxzhiaJGE.tiles.World;

public abstract class Entity {
	
	protected HashMap<String, Float> floatMems = new HashMap<String, Float>();
	protected HashMap<String, Boolean> boolMems = new HashMap<String, Boolean>();
	
	protected Vector2 pos, velocity, momentum, facingDirection;
	protected float width, height, weight, xMove, yMove, attackCooldown;
	protected int health;
	protected boolean isJumping, isDead, isFalling;
	protected Rectangle hitbox;
	protected BufferedImage texture;
	protected Renderable renderable;
	protected Renderer renderer;
	protected World world;
	protected Physics physics;
	
	public Entity(float x, float y, float width, float height, float weight, BufferedImage texture, Renderer renderer, World world, Physics physics)
	{
		pos = new Vector2(x, y);
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.texture = texture;
		this.renderer = renderer;
		this.world = world;
		this.physics = physics;
		
		isJumping = false;
		
		renderable = new Renderable(x, y, width, height, texture);
		hitbox = new Rectangle();
		
		velocity = new Vector2();
		xMove = 0;
		yMove = 0;
		momentum = new Vector2();
		
		facingDirection = Vector2.DOWN;
	}
	
	public abstract void tick();
	public abstract void render();
	public abstract void hurt(float damage);
	
	protected void move(EntityManager m, boolean gravity, boolean collideWithEnemy)
	{	
		physics.checkCollisionWithTileX(this, world);
		physics.checkCollisionWithTileY(this, world, gravity);
		

		if(!physics.checkCollisionWithEntity(this, m, 0, yMove, collideWithEnemy))
			pos.setY(pos.getY() + yMove);
		if(!physics.checkCollisionWithEntity(this, m, xMove, 0, collideWithEnemy))
			pos.setX(pos.getX() + xMove);
	}
	
	protected boolean checkForAttacks(EntityManager m, float damage, float distance)
	{
		boolean hasAttacked = false;
		for(Entity e : m.getEntities())
		{
			if(e.equals(this))
				continue;
			if(facingDirection == Vector2.UP)
			{
				if(e.getHitbox(0, 0).intersects(getHitbox(0, 0-distance)))
				{
					e.hurt(damage);
					hasAttacked = true;
				}
			} else if(facingDirection == Vector2.LEFT)
			{
				if(e.getHitbox(0, 0).intersects(getHitbox(0-distance, 0)))
				{
					e.hurt(damage);
					hasAttacked = true;
				}
			} else if(facingDirection == Vector2.RIGHT)
			{
				if(e.getHitbox(0, 0).intersects(getHitbox(distance, 0)))
				{					
					e.hurt(damage);
					hasAttacked = true;
				}
			} else if(facingDirection == Vector2.DOWN)
			{
				if(e.getHitbox(0, 0).intersects(getHitbox(0, distance)))
				{
					e.hurt(damage);
					hasAttacked = true;
				}
			}
		}
		return hasAttacked;
	}
	
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

	public Vector2 getPos()
	{
		return pos;
	}
	
	public void setPos(Vector2 pos)
	{
		this.pos = pos;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	public void setVelocity(Vector2 velocity)
	{
		this.velocity = velocity;
	}

	public float getxMove()
	{
		return xMove;
	}
	
	public float getyMove()
	{
		return yMove;
	}
	
	public void setxMove(float x)
	{
		this.xMove = x;
	}
	
	public void setyMove(float y)
	{
		this.yMove = y;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public Rectangle getHitbox(float xOffset, float yOffset) {
		return new Rectangle((int)(pos.getX() + hitbox.x + xOffset), (int)(pos.getY() + hitbox.y + yOffset), hitbox.width, hitbox.height);
	}
	
	public Rectangle getHitbox()
	{
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public Renderable getRenderable() {
		return renderable;
	}

	public void setRenderable(Renderable renderable) {
		this.renderable = renderable;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	public Vector2 getMomentum()
	{
		return momentum;
	}
	
	public void setMomentum(Vector2 momentum)
	{
		this.momentum = momentum;
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public void setWorld(World world)
	{
		this.world = world;
	}
	
	public void setDead(boolean isDead)
	{
		this.isDead = isDead;
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	
	public boolean isFalling()
	{
		return isFalling;
	}
	
	public void setFalling(boolean fall)
	{
		isFalling = fall;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
}
