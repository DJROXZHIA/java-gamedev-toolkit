package me.djroxzhiaJGE.ui;

import java.awt.Rectangle;

import me.djroxzhiaJGE.graphics.render.Renderer;
import me.djroxzhiaJGE.input.MouseManager;

public abstract class UIComponent {
	
	protected float x, y, height, width;
	protected boolean hovering = false;
	protected Rectangle bounds;
	protected MouseManager mouse;
	protected Renderer renderer;
	
	public UIComponent(float x, float y, float width, float height, MouseManager mouse, Renderer renderer)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
		this.mouse = mouse;
		this.renderer = renderer;
	}
	
	public abstract void tick();
	public abstract void render();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
}
