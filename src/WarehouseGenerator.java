import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;
/**
 * WarehouseGenerator is responsible for initialising the game map
 * with the initial positions of the player and boxes, as well as
 * generating the walls and goal locations for the level.
 * 
 * The levels are created by taking a prototype depending on what 
 * level the player is on and then randomly placing a set of templates
 * to create interesting levels.
 */
public class WarehouseGenerator {
	private WarehouseGame g;
	private WarehouseView view;
	private Map map;
	private int RNG_BOUND = 1000;
	private Direction up = Direction.UP;
	private Direction down = Direction.DOWN;
	private Direction left = Direction.LEFT;
	private Direction right = Direction.RIGHT;
	private int height;
	private int width;
    
	/**
	 * Constructor to generate warehouse levels.
	 * 
	 * @param g The warehouse game
	 */
	public WarehouseGenerator(WarehouseGame g) {
		this.g = g;
		view = g.getView();
		map = g.getGameMap();
		generateLevel();
		g.setHeight(height);
		g.setWidth(width);
	}
    
	/**
	 * Generate a level randomly.
	 */
	public void generateLevel() {
		int level = g.getLevel();
		map.clearMap();
		if (g.isTutorial()) {
			generateTutorial();
		} else if (level <= 5) {
			initialisePrototype1();
			placeTemplates(1);
			placePlayer();
			placeBoxes(3);
			placeGoals(3);
		}
		else if (level > 5 && level <= 10) {
			initialisePrototype2();
			placeTemplates(2);
			placePlayer();
			placeBoxes(4);
			placeGoals(4);
			view.scale();
		}
		else if (level > 10) {
			initialisePrototype3();
			placeTemplates(3);
			placePlayer();
			placeBoxes(5);
			placeGoals(5);
			view.scale();
		}
		
		map.addAllToMap();
		/*
		 * Only check up to level 10 as after level 10, levels are huge and runtime is massive.
		 */
		if (level <= 10 ) {
			AStarPathFinder astar = new AStarPathFinder(g.getGameMap(),15);
			int flag =0;
			for (int j=0;j<g.getGameMap().getGoals().size();j++){
				for (int i=0;i<g.getGameMap().getBoxes().size();i++){
					Box b = g.getGameMap().getBoxes().get(i);
					Path p = astar.findPath(b, b.x(), b.y(), g.getGameMap().getGoals().get(j).x(), g.getGameMap().getGoals().get(j).y());
					if(p != null) {
						flag++;
						break;
					}
					
				}
			}
			if(flag != g.getGameMap().getGoals().size()){
				newLevel();
			}
		}

		view.repaint();

		g.setSleeping(false);
	}
	
	/**
	 * Generates a tutorial level.
	 */
	public void generateTutorial() {
		map.clearMap();
		initialisePrototype1();
		Player p = new Player(2, 4);
		map.setInitialPlayer(p);
		map.setPlayer(p);
		Goal g = new Goal(6, 4);
		map.addToGoals(g);
		map.clearBoxes();
		Box b = new Box(3, 4);
		WarehouseObject arrow = new WarehouseObject(4,4);
		WarehouseObject arrow2 = new WarehouseObject(5,4);
		Image arrowImg = new ImageIcon(this.getClass().getResource("/images/tutorial_arrow.png")).getImage();
		arrow.setImage(arrowImg);
		arrow2.setImage(arrowImg);
		map.addToMap(arrow);
		map.addToMap(arrow2);
		map.addToBoxes(b);
		map.addToInitialBoxes(b.clone());
	}
	
	/**
	 * Generate a new level.
	 */
	public void newLevel() {
		map.newMap();
		generateLevel();
	}
	
	/**
	 * Restart the level.
	 */
	public void restartLevel() {
		if (g.isTutorial()) {
			generateTutorial();
			map.addAllToMap();
			g.setSleeping(false);
		} else {
			map.restartMap();
		}
		view.repaint();
	}
	
	/**
	 * Place templates randomly while generating the level.
	 * 
	 * @param prototype The prototype number
	 */
	public void placeTemplates(int prototype){
		Random rng = new Random();
		for(int i=0;i<3;i++){
			int x = rng.nextInt(4);
			if(x==0) placeTemplate1(prototype);
			else if(x==1) placeTemplate2(prototype);
			else if(x==2) placeTemplate3(prototype);
		}
	}
    
	/**
	 * Place the player in a randomly selected free space.
	 */
    public void placePlayer() {
		WarehouseObject freeSpace = map.getRandomFree();
		map.removeFree(freeSpace);
		Player p = new Player(freeSpace.x(), freeSpace.y());
		map.setInitialPlayer(p);
		map.setPlayer(p);
		
	}
	
    /**
     * Initialise the goal locations at random.
     * 
     * @param n The number of goals to place
     */
	public void placeGoals(int n) {
		map.clearGoals();
		int goalNum = 0;
		int i = 0;

		while (goalNum < n && i < RNG_BOUND) {
			WarehouseObject freeSpace = map.getRandomFree();
			if (g.hitWall(freeSpace, up) && g.hitWall(freeSpace, down)) {
				i++;
				continue;
			} else if (g.hitWall(freeSpace, right) && g.hitWall(freeSpace, left)) {
				i++;
				continue;
			} else {
				map.removeFree(freeSpace);
				Goal g = new Goal(freeSpace.x(), freeSpace.y());
				map.addToGoals(g);
				goalNum++;
				if (goalNum == n) {
					break;
				}
			}
			i++;
		}
		
		if (goalNum < n) {
			newLevel();
		}
	}
	
	/**
	 * Initialise the box locations
	 * 
	 * @param n The number of boxes to place.
	 */
	public void placeBoxes(int n) {
		int boxNum = 0;
		int i = 0;
		
		map.clearBoxes();
		map.clearInitialBoxes();
		
		while (boxNum < n && i < RNG_BOUND) {
			WarehouseObject freeSpace = map.getRandomFree();
			
			if (g.hitWall(freeSpace, up)) {
				i++;
				continue;
			} else if (g.hitWall(freeSpace, right)) {
				i++;
				continue;
			} else if (g.hitWall(freeSpace, down)) {
				i++;
				continue;
			} else if (g.hitWall(freeSpace, left)) {
				i++;
				continue;
			} else {
				map.removeFree(freeSpace);
				Box b = new Box(freeSpace.x(), freeSpace.y());
				map.addToBoxes(b);
				map.addToInitialBoxes(b.clone());
				boxNum++;
				if (boxNum == n) {
					break;
				}
			}
			i++;
		}
		
		// if the number of boxes place does not equal the number needed, generate a new level
		if (boxNum < n) {
			newLevel();
		}
	}
	
	/**
	 * Initialise the game map to store the walls of prototype 1.
	 */
	public void initialisePrototype1() {
		map.clearFree();
		
		String prototype =
		          "########\n"
				+ "#  ##  #\n"
		        + "#      #\n"
		        + "#  ##  #\n"
		        + "#      #\n"
		        + "#      #\n"
		        + "########\n"
		        + "########\n";
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < prototype.length(); i++) {
			char element = prototype.charAt(i);
			
			if (element == '\n') {
				y++;
				if (width < x) {
					width = x;
					map.setWidth(width);
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				map.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!map.freeContains(freeSpace)) {
					map.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
		map.setHeight(height);
	}
	
	
	/**
	 * Initialise the game map to store the walls of prototype 2.
	 */
	public void initialisePrototype2() {
		map.clearFree();
		
		String prototype =
		          "##########\n"
				+ "#  ##   ##\n"
		        + "#        #\n"
		        + "#  #  ####\n"
		        + "#        #\n"
		        + "#        #\n"
		        + "#        #\n"
		        + "#        #\n"
		        + "##########\n"
		        + "##########\n";
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < prototype.length(); i++) {
			char element = prototype.charAt(i);
			
			if (element == '\n') {
				y++;
				if (width < x) {
					width = x;
					map.setWidth(width);
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				map.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!map.freeContains(freeSpace)) {
					map.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
		map.setHeight(height);
	}
	
	/**
	 * Initialise the game map to store the walls of prototype 3.
	 */
	public void initialisePrototype3() {
		map.clearFree();
		
		String prototype =
		          "###############\n"
		  		+ "###############\n"
				+ "#  ##        ##\n"
		        + "#             #\n"
		        + "#  ##     #####\n"
		        + "#    ##       #\n"
		        + "#             #\n"
		        + "####   ###    #\n"
		        + "###    ###### #\n"
		        + "####          #\n"
		        + "####          #\n"
		        + "###           #\n"
		        + "##            #\n"
		        + "###############\n"
		        + "###############\n";
		
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < prototype.length(); i++) {
			char element = prototype.charAt(i);
			
			if (element == '\n') {
				y++;
				if (width < x) {
					width = x;
					map.setWidth(width);
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				map.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!map.freeContains(freeSpace)) {
					map.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
		map.setHeight(height);
	}
	
	/**
	 * Place template 1 randomly in the level.
	 * 
	 * @param prototype The prototype number
	 */
	public void placeTemplate1(int prototype) {
		String template1 = 
				  "  \n"
				+ "  \n"
		        + "  \n";
		int t1Width = 2;
		int t1Height = 3;
		int boundY = 0;
		
		Random rng = new Random();
		int rX = 0;
		int rY = 0;
		while (rX == 0) {
			rX = rng.nextInt(width-t1Width);
		}
		
		if (rX == 1 || rX == 5) {
			while (rY < 1 || rY > 4) {
				rY = rng.nextInt(height-t1Height);
			}
		} else if (rX > 1 && rX < 5) {
			rY = 4;
		} else {
			if (prototype == 2) {
				boundY = 3;
			} else if (prototype == 3) {
				boundY = 8;
			}
			while (rY < 1 || rY > boundY) {
				rY = rng.nextInt(height-t1Height);
			}
		}
		
		int initRX = rX;
		
		for (int i = 0; i < template1.length(); i++) {
			char element = template1.charAt(i);
			if (element == '\n') {
				rX = initRX;
				rY++;
			} else if (element == ' ') {
				for (Iterator<Wall> iterator = map.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!map.freeContains((WarehouseObject) wall)) {
				    		map.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
	
	/**
	 * Place template 2 randomly in the level.
	 * 
	 * @param prototype The prototype number
	 */
	public void placeTemplate2(int prototype) {
		String template2 = 
				  "  \n"
				+ "  \n";
		int t2Width = 2;
		int t2Height = 2;
		
		Random rng = new Random();
		int rX = 0;
		int rY = 0;
		int boundY = 0;
		
		while (rX == 0) {
			rX = rng.nextInt(width-t2Width);
		}
		
		if (rX == 1 || rX == 5) {
			while (rY < 1 || rY > 4) {
				rY = rng.nextInt(height-t2Height);
			}
		} else if (rX > 1 && rX < 5) {
			while (rY < 4 || rY > 5) {
				rY = rng.nextInt(height-t2Height);
			}
		} else {
			if (prototype == 2) {
				boundY = 3;
			} else if (prototype == 3) {
				boundY = 8;
			}
			while (rY < 1 || rY > boundY) {
				rY = rng.nextInt(height-t2Height);
			}
		}
		
		int initRX = rX;
		
		for (int i = 0; i < template2.length(); i++) {
			char element = template2.charAt(i);
			if (element == '\n') {
				rX = initRX;
				rY++;
			} else if (element == ' ') {
				for (Iterator<Wall> iterator = map.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!map.freeContains((WarehouseObject) wall)) {
				    		map.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
	
	/**
	 * Place template 3 randomly in the level.
	 * 
	 * @param prototype The prototype number
	 */
	public void placeTemplate3(int prototype) {
		String template3 =
				  "   \n"
				+ " # \n"
				+ "   \n";
		
		int t3Width = 3;
		int t3Height = 3;
		
		Random rng = new Random();
		int rX = 0;
		int rY = 0;
		int boundY = 0;
		
		while (rX == 0) {
			rX = rng.nextInt(width-t3Width);
		}
		
		if (rX == 1 || rX == 4) {
			while (rY < 1 || rY > 4) {
				rY = rng.nextInt(height-t3Height);
			}
		} else if (rX > 1 && rX < 5) {
			rY = 4;
		} else {
			if (prototype == 2) {
				boundY = 4;
			} else if (prototype == 3) {
				boundY = 8;
			}
			while (rY < 1 || rY > boundY) {
				rY = rng.nextInt(height-t3Height);
			}
		}
		
		int initRX = rX;
		
		for (int i = 0; i < template3.length(); i++) {
			char element = template3.charAt(i);
			if (element == '\n') {
				rX = initRX;
				rY++;
			} else if (element == ' ') {
				for (Iterator<Wall> iterator = map.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!map.freeContains((WarehouseObject) wall)) {
				    		map.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			} else if (element == '#') {
				for (Iterator<WarehouseObject> iterator = map.getFree().iterator(); iterator.hasNext();) {
				    WarehouseObject freeObj = iterator.next();
				    if (freeObj.x() == rX && freeObj.y() == rY) {
				    	map.addToWalls(new Wall(rX, rY));
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
    
}
