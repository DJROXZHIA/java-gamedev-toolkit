package me.djroxzhiaJGE.math;

public class Vector2 {

	public final static Vector2 UP = new Vector2(0, -1);
	public final static Vector2 DOWN = new Vector2(0, 1);
	public final static Vector2 LEFT = new Vector2(-1, 0);
	public final static Vector2 RIGHT = new Vector2(1, 0);
	
	private float x, y;
	
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2(float v)
	{
		this.x = v;
		this.y = v;
	}
	
	public Vector2()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(Vector2 vector)
	{
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public Vector2 subtract(Vector2 a, Vector2 b)
	{
		return new Vector2(a.x - b.x, a.y - b.y);
	}
	
	public Vector2 add(Vector2 a, Vector2 b)
	{
		return new Vector2(a.x + b.x, a.y + b.y);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public boolean equalto(Vector2 vec)
	{
		if(x == vec.x && y == vec.y)
			return true;
		else
			return false;
	}
}
