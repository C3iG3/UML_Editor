package storming.uml_editor.model.things;

import storming.uml_editor.model.UML_Element;


public abstract class UML_Thing extends UML_Element {
	
	/**
	 * Defines the origin of all UML_Thing(s) as X and Y 
	 * @author bryan
	 *
	 * @param <X>
	 * @param <Y>
	 * 
	 */
	 private class Origin<X, Y> {
		private final X xCoordinate; 
		private final Y yCoordinate; 
		
		public Origin(X xCoordinate, Y yCoordinate) {
			this.xCoordinate = xCoordinate;
			this.yCoordinate = yCoordinate;
		}
		
		protected final X getX() {
			return xCoordinate; 
		}
		
		protected final Y getY() {
			return yCoordinate;
		}	
	}
	
	 //TODO Need to be able to get x and y coordinates from UML_Controller
	 private Origin<?, ?> UML_ThingOrigin = null;

	 public Origin<?, ?> getXCoordinate() {
		return (Origin<?, ?>) UML_ThingOrigin.getX();
	 }
	 
	 public Origin<?, ?> getYCoordinate() {
			return (Origin<?, ?>) UML_ThingOrigin.getY();
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
