import java.awt.Point;
import java.util.ArrayList;

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
			graph = new Graph();
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
		
		return easyGrid;
	}
	public Boolean isWall(Point point){
		if(this.SokobanObject[point.getX(), point.getY()].type.equals("Wall")) return true;
		return false;
	}
}