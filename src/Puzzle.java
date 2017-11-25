import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Puzzle implements Serializable{
	private static final long serialVersionUID = 663865483373657824L;
	private String puzzleID;
	private String puzzleName;
	private String puzzleDescription;
  	private String hint;
  	private ArrayList<Item> artifact = new ArrayList<Item>();
  	private ArrayList<Room> location = new ArrayList<Room>();
	
 	public Puzzle(String ID, String name, String desc, String hi)
 	{
 		this.puzzleID = ID;
	  	this.puzzleName = name;
		this.puzzleDescription = desc;
		this.hint = hi;
 	}
	
 	public String getPuzzleName()
 	{
 		return this.puzzleName;
 	}
	
 	public String getPuzzleID()
 	{
 		return this.puzzleID;
 	}
 	
 	public String getPuzzleDescription()
 	{
		return this.puzzleDescription;
	}
	
 	public String getHint()
 	{
 		return this.hint;	
 	}
	
 	public void addItem(Item item)
 	{		
 		artifact.add(item);
 	}
	
 	public void addLocation(Room room)
	{
 		location.add(room);
	}
 	
 	public int getLocation()
	{
		return location.get(0).getRoomID();		
	}
	
	public static ArrayList<Puzzle> readPuzzles(String filename)
	{
		ArrayList<Puzzle> pAL = null;
		
		try
		{
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			pAL =  (ArrayList<Puzzle>) ois.readObject();
			ois.close();
			fis.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ClassNotFoundException c){
			System.out.println("Class not found");
			c.printStackTrace();
		}
		return pAL;

  }

