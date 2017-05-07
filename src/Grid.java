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
			currX = 0;
			currY = 0;
			grid = new Grid(6,3);
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
//		String[][] tmp = new String[][]{
//		    {"WALL","WALL","WALL","WALL","WALL","WALL","WALL"},
//		    {"WALL","FLOOR","FLOOR","FLOOR","FLOOR","FLOOR", "WALL"},
//		    {"WALL","WALL","WALL","WALL","WALL","WALL","WALL"}
//		};
//		for (int n = 0; n<7;n++){
//			for (int m =0; m<3;m++){
//				if (tmp[n][m].equals("WALL")){
//					adj[n][m] = new Wall ();
//				}
//				else if (tmp[n][m].equals("FLOOR")){
//					adj[n][m] = new Floor();
//				}
//			}
//		}
//		adj[1][1] = new Wall(easyGrid, new Point(1,1), playerImage);
//		adj[1][5] = new Goal(easyGrid, new Point(),goalImage);
		
		return easyGrid;
	}
	public Boolean isWall(int x, int y){
		if(this.adj[x,y].type.equals("Wall")) return true;
		return false;
	}
}