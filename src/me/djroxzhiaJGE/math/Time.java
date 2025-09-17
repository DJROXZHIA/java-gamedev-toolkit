package me.djroxzhiaJGE.math;

public class Time {
	
	private int fps;
	private long timePerTick, last = System.nanoTime(), timer = System.currentTimeMillis(), now, updateTime;
	int ticks = 0;
	private double delta = 0.0;
	
	public Time(int fps)
	{
		this.fps = fps;
		timePerTick = 1000000000 / fps;
	}
	
	public boolean tick()
	{
		now = System.nanoTime();
		updateTime = now - last;
		last = now;
		
		delta = updateTime / (double) timePerTick;
		
		if(System.currentTimeMillis() - timer >= 1000)
		{
			System.out.println("FPS: " + ticks);
			ticks = 0;
			timer = System.currentTimeMillis();
		}
				
		ticks++;
		return true;
	}
	
	public void setFps(int fps)
	{
		this.fps = fps;
	}
	
	public int getFps()
	{
		return this.fps;
	}
	
	public double getDelta()
	{
		return delta;
	}
	
	public int getTicks()
	{
		return ticks;
	}
}
