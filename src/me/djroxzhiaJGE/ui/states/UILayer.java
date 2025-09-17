package me.djroxzhiaJGE.ui.states;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.djroxzhiaJGE.graphics.Renderable;
import me.djroxzhiaJGE.graphics.render.Renderer;
import me.djroxzhiaJGE.ui.UIComponent;

public class UILayer {
	
	private ArrayList<UIComponent> components;
	private ArrayList<Renderable> renderables;
	
	private BufferedImage background;
	float x, y, width, height;
	
	private Renderer renderer;
	private Renderable layer;
	
	public UILayer(Renderer renderer)
	{
		this.renderer = renderer;
		components = new ArrayList<UIComponent>();
		renderables = new ArrayList<Renderable>();
	}
	
	public UILayer(Renderer renderer, BufferedImage background, float x, float y, float width, float height)
	{
		this.background = background;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.renderer = renderer;
		layer = new Renderable(x, y, width, height, background);
		components = new ArrayList<UIComponent>();
		renderables = new ArrayList<Renderable>();
	}
	
	public void add(UIComponent component)
	{
		components.add(component);
	}
	
	public void add(Renderable r)
	{
		renderables.add(r);
	}
	
	public void remove(UIComponent component)
	{
		components.remove(component);
	}
	
	public void remove(Renderable r)
	{
		renderables.remove(r);
	}
	
	public Renderable getRenderable(int index)
	{
		return renderables.get(index);
	}
	
	public UIComponent getComponent(int index)
	{
		return components.get(index);
	}
	
	public void clearUI()
	{
		components.clear();
	}
	
	public void clearRenderables()
	{
		renderables.clear();
	}
	
	public void tick()
	{
		for(UIComponent component : components)
		{
			component.tick();
		}
	}
	
	public void render()
	{
		if(layer != null)
		{
			layer.setX(x);
			layer.setY(y);
			layer.setWidth(width);
			layer.setHeight(height);
			layer.setTexture(background);
			renderer.submit(layer);
		}
		
		for(UIComponent component : components)
		{
			component.render();
		}
		
		for(Renderable r : renderables)
		{
			renderer.submit(r);
		}
	}
	
	public void setLayer(Renderable layer)
	{
		this.layer = layer;
		x = layer.getX();
		y = layer.getY();
		width = layer.getWidth();
		height = layer.getHeight();
		background = layer.getTexture();
	}
	
	public Renderable getLayer()
	{
		return layer;
	}

	public ArrayList<UIComponent> getComponents() {
		return components;
	}

	public ArrayList<Renderable> getRenderables() {
		return renderables;
	}
}
