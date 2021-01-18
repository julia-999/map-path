/**
 * Creates a Path object for the map that finds the path 
 * @author Julia Anantchenko
 */
public class Path {

	/** References the object representing the city map where your program will try to find a path from the starting cell to the destination cell */
	private Map cityMap;

	/**
	 * Constructor for the class Path
	 * @param theMap: the Map object being used
	 */
	public Path (Map theMap) {
		cityMap = theMap;
	}

	/**
	 * Finds a path to the destination
	 */
	public void findPath() {
		// creates a stack for the MapCell objects that will make up the path
		ArrayStack<MapCell> stack = new ArrayStack<MapCell>(10, 3, 2);

		// gets the start and pushes it into the stack, marking it as in stack
		MapCell start = cityMap.getStart();
		start.markInStack();
		stack.push(start);

		// try-catch to mkae sure exceptions when dealing with the stack are caught
		try {
			
			// while loop that continues until the destination is reached or the stack is empty
			while (!stack.isEmpty() && !stack.peek().isDestination()) {
				
				// gets the top MapCell object
				MapCell current = stack.peek();
				
				// if it's the destination then it adds it to the stack and exits the loop
				if (current.isDestination()) {
					current.markInStack();
					stack.push(current);
					break;
				}
				
				// otherwise finds the next cell using the nextCell method
				else {
					MapCell next = nextCell(current);
					
					// if there is a next cell, marks and puts it in the stack
					if (next != null) {
						next.markInStack();
						stack.push(next);
					}
					
					// otherwise removes and unmarks the topmost item in the stack
					else {
						stack.pop().markOutStack();
					}
				}
			}
			
			// if the destination has been found prints appropriate message, otherwise says it has not been found
			if (!stack.isEmpty() && stack.peek().isDestination())
				System.out.println("Path found containing " + stack.size() + " cells.");
			else
				System.out.println("Path not found.");
			
		// catches exception if there is an empty stack
		} catch (EmptyStackException e) {
			System.out.println("Empty stack");;
		}
	}

	/**
	 * Finds the next best cell to use
	 * @param cell: the cell from which the next cell will be decided
	 * @return the next best cell
	 */
	private MapCell nextCell(MapCell cell) {
		
		// checks if it's the destination
		for (int i = 0; i < 4; i++) {
			// creates new cells based on the 0-3 indexes in the for loop
			MapCell next = cell.getNeighbour(i);
			
			// checks the new cell and original cell for conditions that would make it eligible to be a path
			if (next != null && !next.isMarked() && (cell.isStart() || cell.isIntersection() || (cell.isNorthRoad() && i==0) || (cell.isEastRoad() && i==1) || (cell.isSouthRoad() && i==2) || (cell.isWestRoad() && i==3)) && next.isDestination())
				return next;
		}

		// checks if it's an intersection
		for (int i = 0; i < 4; i++) {
			// creates new cells based on the 0-3 indexes in the for loop
			MapCell next = cell.getNeighbour(i);

			// checks the new cell and original cell for conditions that would make it eligible to be a path
			if (next != null && !next.isMarked() && (cell.isStart() || cell.isIntersection() || (cell.isNorthRoad() && i==0) || (cell.isEastRoad() && i==1) || (cell.isSouthRoad() && i==2) || (cell.isWestRoad() && i==3)) && next.isIntersection()) {
				return next;
			}
		}

		// checks if it's north, east, west or south roads
		for (int i = 0; i < 4; i++) {
			// creates new cells based on the 0-3 indexes in the for loop
			MapCell next = cell.getNeighbour(i);

			// checks the new cell and original cell for conditions that would make it eligible to be a path
			if (next != null && !next.isMarked()) {

				// cells that are either the start or an intersection
				if (cell.isStart() || cell.isIntersection()) {
					if (i==0 && next.isNorthRoad())
						return next;
					if (i==1 && next.isEastRoad())
						return next;
					if (i==2 && next.isSouthRoad())
						return next;
					if (i==3 && next.isWestRoad())
						return next;
				}

				// cells that are north, east, south or west roads
				if (cell.isNorthRoad() && i==0 && next.isNorthRoad())
					return next;
				if (cell.isEastRoad() && i==1 && next.isEastRoad()) 
					return next;
				if (cell.isSouthRoad() && i==2 && next.isSouthRoad()) 
					return next;
				if (cell.isWestRoad() && i==3 && next.isWestRoad()) 
					return next;
			}
		}
		// returns null if no cell is a possible option
		return null;
	}
}
