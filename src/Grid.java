import java.util.*;
import java.io.*;

public class Grid {
	private SokobanObject[][] adj;
	
	Grid (int xSize,int ySize){
		adj = new SokobanObject[xSize][ySize];
	}
	
	public Grid makeLevel(String[] args,int xSize,int ySize){
		Scanner sc = null;
		try
		{
			sc = new Scanner(new FileReader(args[0]));
			String current = sc.next();
			grid = new Grid(6,3);
			jobList = new ArrayList<Job>();
			int cost;
			while(sc.hasNext()){
				if(current.equals("#")){
					sc.nextLine();
				}
				if(sc.hasNext()) current=sc.next();
			}
			//use A* on the created graph with the jobs
			graph.runJobs(jobList);
		}
		catch (FileNotFoundException e) {}
		finally
		{
			if (sc != null) sc.close();
		}
		Grid easyGrid = new Grid (x,y);
		for(i=0; i<wallList.size; i++){
			adj[wallList.get(i).Point.getX][wallList.get(i).Point.getY];
		}
		adj[2][1] = new Wall(easyGrid, new Point(1,1), playerImage);
		adj[2][4] = new Goal(easyGrid, new Point(),goalImage);
//		String[][] adj = new String[][]{
//		    {"WALL","WALL","WALL","WALL","WALL","WALL","WALL"},
//		    {"WALL","FLOOR","FLOOR","FLOOR","FLOOR","FLOOR", "WALL"},
//		    {"WALL","WALL","WALL","WALL","WALL","WALL","WALL"}
//		};
		
		return easyGrid;
		//return adj;
	}
	public Boolean isWall(int x, int y){
		if(this.adj[x, y].type.equals("Wall")) return true;
		return false;
	}
}