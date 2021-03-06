import java.util.ArrayList;
import java.util.Collections;

public  class AStarPathFinder implements PathFinder {
	/** The set of nodes that have been searched through */
	private ArrayList closed = new ArrayList();
	/** The set of nodes that we do not yet consider fully searched */
	private SortedList open = new SortedList();
	
	/** The map being searched */
	private Map map;
	/** The maximum depth of search we're willing to accept before giving up */
	private int maxSearchDistance;
	
	/** The complete set of nodes across the map */
	private Node[][] nodes;
	/** The heuristic we're applying to determine which nodes to search first */
	private AStarHeuristic heuristic;
	
	/**
	 * Create a path finder with the default heuristic - closest to target.
	 * 
	 * @param map The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diagonal movement
	 */
	public AStarPathFinder(Map map, int maxSearchDistance) {
		this(map, maxSearchDistance, new AStarHeuristic());
	}

	/**
	 * Create a path finder 
	 * 
	 * @param heuristic The heuristic used to determine the search order of the map
	 * @param map The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diagonal movement
	 */
	public AStarPathFinder(Map map, int maxSearchDistance, AStarHeuristic heuristic) {
		this.heuristic = heuristic;
		this.map = map;
		this.maxSearchDistance = maxSearchDistance;
		

		nodes = new Node[map.getWidth()][map.getHeight()];
		
		for (WarehouseObject wo: map.getMap()){
			nodes[wo.x()][wo.y()] = new Node(wo.x(), wo.y(), map.getType(wo));
		}
		
		for (WarehouseObject wo: map.getFree()){
			nodes[wo.x()][wo.y()] = new Node(wo.x(), wo.y(), map.getType(wo));
		}

		map.cornerListCreator(map.getFree(), map.getWalls());
	}

	/**
	 * @see PathFinder#findPath(Mover, int, int, int, int)
	 */
	public Path findPath(WarehouseObject box, int sx, int sy, int tx, int ty) {
	
		//	System.out.println("--"+sx+"--"+sy+"--"+tx+"--"+ty);
		// initial state for A*. The closed group is empty. Only the starting
		// tile is in the open list and it'e're already there
		nodes[sx][sy].cost = 0;
		nodes[sx][sy].depth = 0;
		closed.clear();
		open.clear();
		open.add(nodes[sx][sy]);
		
		nodes[tx][ty].parent = null;
		
		// while we haven't exceeded our max search depth
		int maxDepth = 0;
		while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
			// pull out the first node in our open list, this is determined to 

			// be the most likely to be the next step based on our heuristic

			Node current = getFirstInOpen();
//			if ((current == nodes[tx][ty]) || (!canPush(current))) {
//				break;
//			}
			if (current == nodes[tx][ty]) {
				break;
			}
			
			removeFromOpen(current);
			addToClosed(current);
			
			// search through all the neighbors of the current node evaluating
			// them as next steps
			for (int x=-1;x<2;x++) {
				for (int y=-1;y<2;y++) {
					// not a neighbor, its the current tile

					if ((x == 0) && (y == 0)) {
						continue;
					}
					
					// if we're not allowing diagonal movement then only 
					// one of x or y can be set

					if ((x != 0) && (y != 0)) {
						continue;
					}
					
					
					// determine the location of the neighbor and evaluate it

					int xp = x + current.x;
					int yp = y + current.y;
					
					if (isValidLocation(box,sx,sy,xp,yp)) {
						// the cost to get to this node is cost the current plus the movement
						// cost to reach this node. Note that the heuristic value is only used
						// in the sorted open list

						float nextStepCost = current.cost + getMovementCost(box, current.x, current.y, xp, yp);
						Node neighbour = nodes[xp][yp];
						map.pathFinderVisited(xp, yp);
						// if the new cost we've determined for this node is lower than 

						// it has been previously makes sure the node hasn'e've
						// determined that there might have been a better path to get to

						// this node so it needs to be re-evaluated

						if (nextStepCost < neighbour.cost) {
							if (inOpenList(neighbour)) {
								removeFromOpen(neighbour);
							}
							if (inClosedList(neighbour)) {
								removeFromClosed(neighbour);
							}
						}
						
						// if the node hasn't already been processed and discarded then
						// reset it's cost to our current cost and add it as a next possible
						// step (i.e. to the open list)

						if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
							neighbour.cost = nextStepCost;
							neighbour.heuristic = getHeuristicCost(box, xp, yp, tx, ty);
							maxDepth = Math.max(maxDepth, neighbour.setParent(current));
							addToOpen(neighbour);
						}
					}
				}
			}
		}

		// since we'e've run out of search 
		// there was no path. Just return null

		if (nodes[tx][ty].parent == null) {
			return null;
		}
		
		// At this point we've definitely found a path so we can uses the parent

		// references of the nodes to find out way from the target location back

		// to the start recording the nodes on the way.

		Path path = new Path();
		Node target = nodes[tx][ty];
		while (target != nodes[sx][sy]) {
			path.prependStep(target.x, target.y);
			target = target.parent;
		}
		path.prependStep(sx,sy);
		
		// thats it, we have our path 

		return path;
	}

	/**
	 * Get the first element from the open list. This is the next
	 * one to be searched.
	 * 
	 * @return The first element in the open list
	 */
	protected Node getFirstInOpen() {
		return (Node) open.first();
	}
	
	/**
	 * Add a node to the open list
	 * 
	 * @param node The node to be added to the open list
	 */
	protected void addToOpen(Node node) {
		open.add(node);
	}
	
	/**
	 * Check if a node is in the open list
	 * 
	 * @param node The node to check for
	 * @return True if the node given is in the open list
	 */
	protected boolean inOpenList(Node node) {
		return open.contains(node);
	}
	
	/**
	 * Remove a node from the open list
	 * 
	 * @param node The node to remove from the open list
	 */
	protected void removeFromOpen(Node node) {
		open.remove(node);
	}
	
	/**
	 * Add a node to the closed list
	 * 
	 * @param node The node to add to the closed list
	 */
	protected void addToClosed(Node node) {
		closed.add(node);
	}
	
	/**
	 * Check if the node supplied is in the closed list
	 * 
	 * @param node The node to search for
	 * @return True if the node specified is in the closed list
	 */
	protected boolean inClosedList(Node node) {
		return closed.contains(node);
	}
	
	/**
	 * Remove a node from the closed list
	 * 
	 * @param node The node to remove from the closed list
	 */
	protected void removeFromClosed(Node node) {
		closed.remove(node);
	}
	
	/**
	 * Check if a given location is valid for the supplied mover
	 * 
	 * @param mover The mover that would hold a given location
	 * @param sx The starting x coordinate
	 * @param sy The starting y coordinate
	 * @param x The x coordinate of the location to check
	 * @param y The y coordinate of the location to check
	 * @return True if the location is valid for the given mover
	 */
	protected boolean isValidLocation(WarehouseObject box, int sx, int sy, int x, int y) {
		if((x < 0) || (y < 0) || (x >= map.getWidth()) || (y >= map.getHeight())){
			return false;
		}
		
		
		//check if box is put into corner
		for (Wall corner : map.getCorners()){
			for (WarehouseObject g : map.getGoals()){
				if ((corner.x() == x && corner.y() == y) && (x != g.x() && y!= g.y()) ){
					return false;
				}
			}
		}
		
		return true;
	}
	protected boolean canPush(Node current){
		for (Wall w : map.getWalls()){
			if (current.parentDirection == 'X'){
				return true;
			} else if(current.parentDirection == 'R'){
				if ( (w.x() == (current.parent.x-1)) && (w.y() == (current.parent.y))) return false; 
			} else if(current.parentDirection == 'L'){
				if (( w.x() == (current.parent.x+1))&& (w.y() == (current.parent.y))) return false;
			} else if(current.parentDirection == 'U'){
				if (( w.y() == (current.parent.y-1)) && (w.x() == (current.parent.x))) return false;	
			} else if(current.parentDirection == 'D'){
				if ((w.y() == (current.parent.y+1)) && (w.x() == (current.parent.x))) return false;
			}
		}
		return true;
	}
	
	/**
	 * Get the cost to move through a given location
	 * 
	 * @param mover The entity that is being moved
	 * @param sx The x coordinate of the tile whose cost is being determined
	 * @param sy The y coordiante of the tile whose cost is being determined
	 * @param tx The x coordinate of the target location
	 * @param ty The y coordinate of the target location
	 * @return The cost of movement through the given tile
	 */
	public float getMovementCost(WarehouseObject box, int sx, int sy, int tx, int ty) {
		return map.getCost(box, sx, sy, tx, ty);
	}

	/**
	 * Get the heuristic cost for the given location. This determines in which 
	 * order the locations are processed.
	 * 
	 * @param mover The entity that is being moved
	 * @param x The x coordinate of the tile whose cost is being determined
	 * @param y The y coordiante of the tile whose cost is being determined
	 * @param tx The x coordinate of the target location
	 * @param ty The y coordinate of the target location
	 * @return The heuristic cost assigned to the tile
	 */
	public float getHeuristicCost(WarehouseObject box, int x, int y, int tx, int ty) {
		return heuristic.getCost(map,box, x, y, tx, ty);
	}
	
	/**
	 * A simple sorted list
	 */
	private class SortedList {
		/** The list of elements */
		private ArrayList list = new ArrayList();
		
		/**
		 * Retrieve the first element from the list
		 *  
		 * @return The first element from the list
		 */
		public Object first() {
			return list.get(0);
		}
		
		/**
		 * Empty the list
		 */
		public void clear() {
			list.clear();
		}
		
		/**
		 * Add an element to the list - causes sorting
		 * 
		 * @param o The element to add
		 */
		public void add(Object o) {
			list.add(o);
			Collections.sort(list);
		}
		
		/**
		 * Remove an element from the list
		 * 
		 * @param o The element to remove
		 */
		public void remove(Object o) {
			list.remove(o);
		}
	
		/**
		 * Get the number of elements in the list
		 * 
		 * @return The number of element in the list
 		 */
		public int size() {
			return list.size();
		}
		
		/**
		 * Check if an element is in the list
		 * 
		 * @param o The element to search for
		 * @return True if the element is in the list
		 */
		public boolean contains(Object o) {
			return list.contains(o);
		}
	}
	
	/**
	 * A single node in the search graph
	 */
	private class Node implements Comparable {
		/** The x coordinate of the node */
		private int x;
		/** The y coordinate of the node */
		private int y;
		/** The path cost for this node */
		private float cost;
		/** The parent of this node, how we reached it in the search */
		private Node parent;
		/** The heuristic cost of this node */
		private float heuristic;
		/** The search depth of this node */
		private int depth;
		
		private char type;
		private char parentDirection;
		
		/**
		 * Create a new node
		 * 
		 * @param x The x coordinate of the node
		 * @param y The y coordinate of the node
		 */
		public Node(int x, int y, char type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
		/**
		 * Set the parent of this node
		 * 
		 * @param parent The parent node which lead us to this node
		 * @return The depth we have no reached in searching
		 */
		public int setParent(Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;
			this.setParentDirection(this);
			return depth;
		}
		
		public char setParentDirection(Node n){
			int px =  n.parent.x - n.x;
			int py =  n.parent.y - n.y;
			
			if (px > 0 ){
				return 'L';
			} else if (px < 0 ){
				return 'R';
			} else if (py > 0 ){
				return 'U';
			} else if (py < 0 ){
				return 'D';
			}
			
			return 'X';
		}
		
		/**
		 * @see Comparable#compareTo(Object)
		 */
		public int compareTo(Object other) {
			Node o = (Node) other;
			
			float f = heuristic + cost;
			float of = o.heuristic + o.cost;
			
			if (f < of) {
				return -1;
			} else if (f > of) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}