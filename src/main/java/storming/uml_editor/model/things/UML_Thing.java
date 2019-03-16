package storming.uml_editor.model.things;

import storming.uml_editor.model.UML_Element;

public abstract class UML_Thing extends UML_Element {

	// members variables
	private double xCoordinate = 0.0;
	private double yCoordinate = 0.0;
	private int width; 
	private int height;
	
	public void setX(double x) {
		this.xCoordinate = x;
	}
	
	public void setY(double y) {
		this.yCoordinate = y; 
	}
	
	public double getX() {
		return this.xCoordinate;
	}
	
	public double getY() {
		return this.yCoordinate; 
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h; 
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height; 
	}
	
	
	/**
	 * The default constructor for a UML Thing
	 */
	protected UML_Thing() {
		super();	
	}
	
	/**
	 * Constructs the thing with a name
	 * 
	 * @param name The name for the thing
	 */
	protected UML_Thing(String name) {
		super(name);
	}
}