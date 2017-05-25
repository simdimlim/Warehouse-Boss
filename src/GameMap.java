import java.util.ArrayList;
import java.util.Random;

public class GameMap {
	private Player player;
	private Player initialPlayer;
	private ArrayList<Wall> Walls;
	private ArrayList<Box> InitialBoxes;
	private ArrayList<Box> Boxes;
	private ArrayList<Goal> Goals;
	private ArrayList<WarehouseObject> EntireMap;
	private ArrayList<WarehouseObject> Free;
	
	/**
	 * Constructor for the games map.
	 * Stores the entire level.
	 */
	public GameMap(){
		Walls = new ArrayList<Wall>();
		Boxes = new ArrayList<Box>();
		InitialBoxes = new ArrayList<Box>();
		Goals = new ArrayList<Goal>();
		EntireMap = new ArrayList<WarehouseObject>();
		Free = new ArrayList<WarehouseObject>();		
	}
	
	/*
	 * Player functions
	 */
	public void setPlayer(Player p){
		player = p.clone();
	}
	
	public void setInitialPlayer(Player p){
		initialPlayer = p.clone();
	}
	
	public Player getPlayer(){
		return player;
	}
	
	/*
	 * Wall Functions
	 */
	public ArrayList<Wall> getWalls(){
		return Walls;
	}
	
	public int WallSize(){
		return Walls.size();
	}

	public void addToWalls(Wall w){
		Walls.add(w);
	}
	
	/*
	 * Box Functions
	 */
	
	public int BoxesSize(){
		return Boxes.size();
	}
	
	public ArrayList<Box> getBoxes(){
		return Boxes;
	}
	
	public void addToBoxes(Box b){
		Boxes.add(b);
	}
	
	/*
	 * Initial Boxes functions
	 */
	public ArrayList<Box> getInitialBoxes(){
		return InitialBoxes;
	}
	
	public int InitialBoxesSize(){
		return InitialBoxes.size();
	}
	
	public void addToInitialBoxes(Box b){
		InitialBoxes.add(b);
	}

	
	/*
	 * Goal Functions
	 */
	public int GoalsSize(){
		return Goals.size();
	}
	
	public ArrayList<Goal> getGoals(){
		return Goals;
	}
	
	public void clearGoals(){
		Goals.clear();
	}
	
	public void addToGoals(Goal g){
		Goals.add(g);
	}
	
	/*
	 * EntireMap functions
	 */
	public int EntireMapSize(){
		return EntireMap.size();
	}
	
	public ArrayList<WarehouseObject> getEntireMap(){
		return EntireMap;
	}
	
	public void allAllToMap(){
		EntireMap.addAll(Walls);
		EntireMap.addAll(Goals);
		EntireMap.addAll(Boxes);
		addPlayerToMap(player);
	}
	
	public void addPlayerToMap(Player p){
		EntireMap.add(p);
	}
	
	public void clearMap(){
		EntireMap.clear();
	}
	
	public void restartMap(){
		EntireMap.clear();
		Boxes.clear();
		
		for (Box b: InitialBoxes){
			Boxes.add(b.clone());		
		}
		
		EntireMap.addAll(Walls);
		EntireMap.addAll(Goals);
		EntireMap.addAll(Boxes);
		
		setPlayer(initialPlayer);
		EntireMap.add(player);
	}
	
	public void newMap(){
		EntireMap.clear();
		Walls.clear();
		Boxes.clear();
		Free.clear();
		InitialBoxes.clear();
	}
	
	/*
	 * Free Functions
	 */
	public int FreeSize(){
		return Free.size();
	}
	
	public ArrayList<WarehouseObject> getFree(){
		return Free;
	}
	
	public WarehouseObject getRandomFree(){
		Random rng = new Random();
		WarehouseObject freeSpace = Free.get(rng.nextInt(Free.size()));
		
		return freeSpace;	
	}
	
	public void removeFree(WarehouseObject toBeRemoved){
		Free.remove(toBeRemoved);
	}
	
	public void addToFree(WarehouseObject f){
		Free.add(f);
	}
	
	public boolean freeContains(WarehouseObject f){
		return Free.contains(f);
	}

}
