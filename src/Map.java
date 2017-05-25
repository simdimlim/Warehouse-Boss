import java.util.*;

public class Map {
	private Player player;
	private Player initialPlayer;
	private ArrayList<Wall> walls;
	private ArrayList<Box> initialBoxes;
	private ArrayList<Box> boxes;
	private ArrayList<Goal> goals;
	private ArrayList<WarehouseObject> map;
	private ArrayList<WarehouseObject> free;
	private int width;
	private int height;
	
	/**
	 * Constructor for the games map.
	 * Stores the entire level.
	 */
	public Map(){
		walls = new ArrayList<Wall>();
		boxes = new ArrayList<Box>();
		initialBoxes = new ArrayList<Box>();
		goals = new ArrayList<Goal>();
		map = new ArrayList<WarehouseObject>();
		free = new ArrayList<WarehouseObject>();		
	}
	
	/**
	 * Width and height functions
	 */
	public int getWidth(){
		return width;	
	}
	
	public void setWidth(int w){
		width = w;
		
	}
	
	public int getHeight(){
		return height;	
	}
	
	
	public void setHeight(int h){
		height = h;
		
	}
	
	/**
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
	
	/**
	 * Wall Functions
	 */
	public ArrayList<Wall> getWalls(){
		return walls;
	}
	
	public int wallSize(){
		return walls.size();
	}

	public void addToWalls(Wall w){
		walls.add(w);
	}
	
	/**
	 * Box Functions
	 */
	
	public int boxesSize(){
		return boxes.size();
	}
	
	public ArrayList<Box> getBoxes(){
		return boxes;
	}
	
	public void addToBoxes(Box b){
		boxes.add(b);
	}
	
	public void clearBoxes() {
		boxes.clear();
	}
	
	/**
	 * Initial Boxes functions
	 */
	public ArrayList<Box> getInitialBoxes(){
		return initialBoxes;
	}
	
	public int initialBoxesSize(){
		return initialBoxes.size();
	}
	
	public void addToInitialBoxes(Box b){
		initialBoxes.add(b);
	}
	
	public void clearInitialBoxes() {
		initialBoxes.clear();
	}

	
	/**
	 * Goal Functions
	 */
	public int goalsSize(){
		return goals.size();
	}
	
	public ArrayList<Goal> getGoals(){
		return goals;
	}
	
	public void clearGoals(){
		goals.clear();
	}
	
	public void addToGoals(Goal g){
		goals.add(g);
	}
	
	/**
	 * map functions
	 */
	public int entireMapSize(){
		return map.size();
	}
	
	public ArrayList<WarehouseObject> getMap(){
		return map;
	}
	
	public void addAllToMap(){
		map.addAll(walls);
		map.addAll(goals);
		map.addAll(boxes);
		addPlayerToMap(player);
	}
	
	public void addPlayerToMap(Player p){
		map.add(p);
	}
	
	public void clearMap(){
		map.clear();
	}
	
	public void restartMap() {
		map.clear();
		boxes.clear();
		
		for (Box b: initialBoxes) {
			boxes.add(b.clone());		
		}
		
		map.addAll(walls);
		map.addAll(goals);
		map.addAll(boxes);
		
		setPlayer(initialPlayer);
		map.add(player);
	}
	
	public void newMap(){
		map.clear();
		walls.clear();
		boxes.clear();
		free.clear();
		initialBoxes.clear();
	}
	
	/**
	 * Free Functions
	 */
	public int freeSize(){
		return free.size();
	}
	
	public ArrayList<WarehouseObject> getFree(){
		return free;
	}
	
	public WarehouseObject getRandomFree(){
		Random rng = new Random();
		WarehouseObject freeSpace = free.get(rng.nextInt(free.size()));
		
		return freeSpace;	
	}
	
	public void removeFree(WarehouseObject toBeRemoved) {
		free.remove(toBeRemoved);
	}
	
	public void addToFree(WarehouseObject f){
		free.add(f);
	}
	
	public boolean freeContains(WarehouseObject f) {
		return free.contains(f);
	}
	
	public void clearFree() {
		free.clear();
	}
}
