package me.djroxzhiaJGE.ui.states;

import java.util.ArrayList;

import me.djroxzhiaJGE.graphics.render.Renderer;

public abstract class State {
	
	protected ArrayList<UILayer> ui;
	protected Renderer renderer;
	
	public State(Renderer renderer)
	{
		ui = new ArrayList<UILayer>();
		this.renderer = renderer;
	}
	
	public void addUI(UILayer layer)
	{
		ui.add(layer);
	}
	
	public ArrayList<UILayer> getUIs()
	{
		return ui;
	}
	
	public abstract void tick();
	public abstract void render();
}
