import java.util.Iterator;
import java.util.Random;

public class WarehouseGenerator {
	private int[] items = new int[]{1,2,3};
    private Random rand = new Random();
	private WarehouseGame wg;
	private WarehouseView sv;
	private int level;
	private GameMap entireMap;
	private int RNG_BOUND = 1000;
	private Direction up = Direction.UP;
	private Direction down = Direction.DOWN;
	private Direction left = Direction.LEFT;
	private Direction right = Direction.RIGHT;
	private int height;
	private int width;
    
	public WarehouseGenerator(WarehouseGame wg) {
		this.wg = wg;
		level = wg.getLevel();
		sv = wg.getSView();
		entireMap = wg.getGameMap();
		generateLevel();
		wg.setHeight(height);
		wg.setWidth(width);
	}
    
	public void generateLevel() {
		if (level < 5) {
			initialisePrototype1();
			placeTemplates(1);
			placePlayer();
			placeBoxes(3);
			placeGoals(3);
		}
		else if (level >= 5 && level < 10) {
			initialisePrototype2();
			placeTemplates(2);
			placePlayer();
			placeBoxes(4);
			placeGoals(4);
			sv.scale();
		}
		else if (level >= 10) {
			initialisePrototype3();
			placeTemplates(3);
			placePlayer();
			placeBoxes(5);
			placeGoals(5);
			sv.scale();
		}
		
		entireMap.clearMap();
		entireMap.addAllToMap();
		
		sv.repaint();
		wg.setSleeping(false);
	}
	
	public void newLevel() {
		entireMap.newMap();
		generateLevel();
	}
	
	public void restartLevel() {
		entireMap.restartMap();
		sv.repaint();
	}
	
	public void placeTemplates(int prototype){
		for(int i=0;i<3;i++){
			int x = getRandArrayElement(); 
			if(x==1) placeTemplate1(prototype);
			else if(x==2) placeTemplate2(prototype);
			else if(x==3) placeTemplate3(prototype);
		}
	}

    
    public int getRandArrayElement(){
        return items[rand.nextInt(items.length)];
    }
    
    public void placePlayer() {
		WarehouseObject freeSpace = entireMap.getRandomFree();
		entireMap.removeFree(freeSpace);
		Player p = new Player(freeSpace.x(), freeSpace.y());
		entireMap.setInitialPlayer(p);
		entireMap.setPlayer(p);
	}
	
	public void placeGoals(int n) {
		entireMap.clearGoals();
		
		int goalNum = 0;
		int i = 0;

		while (goalNum < n && i < RNG_BOUND) {
			WarehouseObject freeSpace = entireMap.getRandomFree();
			if (wg.hitWall(freeSpace, up) && wg.hitWall(freeSpace, down)) {
			
			} else if (wg.hitWall(freeSpace, right) && wg.hitWall(freeSpace, left)) {
				
			} else {
				entireMap.removeFree(freeSpace);
				Goal g = new Goal(freeSpace.x(), freeSpace.y());
				entireMap.addToGoals(g);
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
	
	public void placeBoxes(int n) {
		int boxNum = 0;
		int i = 0;
		
		entireMap.clearBoxes();
		
		while (boxNum < n && i < RNG_BOUND) {
			WarehouseObject freeSpace = entireMap.getRandomFree();
			
			if (wg.hitWall(freeSpace, up)) {
				
			} else if (wg.hitWall(freeSpace, right)) {
				
			} else if (wg.hitWall(freeSpace, down)) {
				
			} else if (wg.hitWall(freeSpace, left)) {
				
			} else {
				entireMap.removeFree(freeSpace);
				Box b = new Box(freeSpace.x(), freeSpace.y());
				entireMap.addToBoxes(b);
				entireMap.addToInitialBoxes(b.clone());
				boxNum++;
				if (boxNum == n) {
					break;
				}
			}
			i++;
		}
		
		if (boxNum < n) {
			newLevel();
		}
	}
	
	public void initialisePrototype1() {
		String prototype =
		          "########\n"
				+ "#  ##  #\n"
		        + "#      #\n"
		        + "#  ##  #\n"
		        + "#      #\n"
		        + "########\n"
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
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				entireMap.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!entireMap.freeContains(freeSpace)) {
					entireMap.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
	}
	
	public void initialisePrototype2() {
		String prototype =
		          "##########\n"
				+ "#  ##   ##\n"
		        + "#        #\n"
		        + "#  #  ####\n"
		        + "#        #\n"
		        + "##########\n"
		        + "##########\n"
		        + "##########\n"
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
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				entireMap.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!entireMap.freeContains(freeSpace)) {
					entireMap.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
	}
	
	public void initialisePrototype3() {
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
		        + "###############\n"
		        + "###############\n"
		        + "###############\n"
		        + "###############\n"
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
				}
				x = 0;
				continue;
			} else if (element == '#') {
				Wall wall = new Wall(x, y);
				entireMap.addToWalls(wall);
			} else if (element == ' ') {
				WarehouseObject freeSpace = new WarehouseObject(x, y);
				if (!entireMap.freeContains(freeSpace)) {
					entireMap.addToFree(freeSpace);
				}
			}
			x++;
		}
		height = y;
	}
	
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
				for (Iterator<Wall> iterator = entireMap.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!entireMap.freeContains((WarehouseObject) wall)) {
				    		entireMap.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
	
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
				for (Iterator<Wall> iterator = entireMap.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!entireMap.freeContains((WarehouseObject) wall)) {
				    		entireMap.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
	
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
				for (Iterator<Wall> iterator = entireMap.getWalls().iterator(); iterator.hasNext();) {
				    Wall wall = (Wall) iterator.next();
				    if (wall.x() == rX && wall.y() == rY) {
				    	if (!entireMap.freeContains((WarehouseObject) wall)) {
				    		entireMap.addToFree((WarehouseObject) wall);
						}
				    	iterator.remove();
				    }
				}
				rX++;
			} else if (element == '#') {
				entireMap.addToWalls(new Wall(rX, rY));
				for (Iterator<WarehouseObject> iterator = entireMap.getFree().iterator(); iterator.hasNext();) {
				    WarehouseObject freeObj = iterator.next();
				    if (freeObj.x() == rX && freeObj.y() == rY) {
				    	iterator.remove();
				    }
				}
				rX++;
			}
		}
	}
    
}
