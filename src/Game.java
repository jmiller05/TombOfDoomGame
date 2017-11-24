import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.xml.crypto.KeySelector.Purpose;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends Application
{
	
	//public ArrayList<Room>					roomAL;											// ArrayList
	// of
	// Rooms
	//private ArrayList<String>				roomNameAL;										// ArrayList
	// of
	// Room
	// Names
	//private ArrayList<String>				roomDescriptionAL;								// Array
	// List
	// of
	// room
	// descriptions
	// private ArrayList<Monster> monsterArray = new ArrayList<Monster>();
	private static HashMap<Integer, Item>	itemsInRooms	= new HashMap<Integer, Item>();
<<<<<<< HEAD
	public Player					gamePlayer;
	public Controller				gameController;
	public Stage					inventoryStage;
	public Stage                    encounterStage;
=======
	public Player							gamePlayer;
	public Controller						gameController;
	public Stage							inventoryStage;
>>>>>>> 932aa54e9f2e8a4c4c311d5c377672f5f8cfb7ac
	// private Random rand = new Random();
	// public ArrayList<Room> rooms;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		FXMLLoader inventoryLoader = new FXMLLoader();
		FXMLLoader monsterEncounterLoader = new FXMLLoader();
		
		gamePlayer = new Player(10, 10, 1);
		
		// gamePlayer.setCurrentRoom(gameController.getDungeonRooms().get(0));
		// System.out.println(gameController.getDungeonRooms());
		
		/*
		 * ArrayList<Room> rooms = this.populateRooms();
		 * 
		 * try{ FileOutputStream fos= new FileOutputStream("Rooms.dat");
		 * ObjectOutputStream oos= new ObjectOutputStream(fos);
		 * oos.writeObject(rooms); oos.close(); fos.close(); }catch(IOException
		 * ioe){ ioe.printStackTrace(); }
		 */
		
		/*
		 * try { FileInputStream fis = new FileInputStream("Rooms.dat");
		 * ObjectInputStream ois = new ObjectInputStream(fis); rooms =
		 * (ArrayList) ois.readObject(); ois.close(); fis.close(); }
		 * catch(IOException ioe){ ioe.printStackTrace(); return; }
		 * catch(ClassNotFoundException c){ System.out.println("Class not found"
		 * ); c.printStackTrace(); return; }
		 */
		
		// rooms = this.populateRooms();
		// populateMonsters();
		// addMonsterRooms();
		//
		// try{
		// FileOutputStream fos= new FileOutputStream("Monsters.dat");
		// ObjectOutputStream oos= new ObjectOutputStream(fos);
		// oos.writeObject(monsterArray);
		// oos.close();
		//
		// fos.close();
		// }catch(IOException ioe){
		// ioe.printStackTrace();
		// }
		
		Controller gameController = new Controller(gamePlayer, Room.readRooms("Rooms.dat"));
		
		loader.setController(gameController);
		inventoryLoader.setController(gameController);
		monsterEncounterLoader.setController(gameController);
		loader.setLocation(getClass().getResource("View.fxml"));
		inventoryLoader.setLocation(getClass().getResource("Inventory.fxml"));
		monsterEncounterLoader.setLocation(getClass().getResource("MonsterEncounter.fxml"));
		loadItemsInRooms();
		Parent root = loader.load();
		Parent inventory = inventoryLoader.load();
		Parent encounter = monsterEncounterLoader.load();
		inventoryStage = new Stage();
		inventoryStage.setScene(new Scene(inventory, 600, 400));
		
		encounterStage = new Stage();
		encounterStage.setScene(new Scene(encounter, 600, 500));
		// gameController.setInventoryView(inventory);
		// gameController.setInventoryLoader(inventoryLoader);
		gameController.setInventoryStage(inventoryStage);
		gameController.setEncounterStage(encounterStage);
		gameController.setMonsterArray(Monster.readMonsters("Monsters.dat"));
		gameController.setMonsterStats();
		// gameController.setItemsArray(Item.readItems("Items.dat"));
		primaryStage.setScene(new Scene(root, 1300, 750));
		primaryStage.show();
		
	}
	
	public Item getItemInRoom(int roomId)
	{
		return itemsInRooms.get(roomId);
	}
	
	// Write
	public static void saveItemsInRooms()
	{
		
		try
		{
			FileOutputStream fos = new FileOutputStream("ItemsInRooms.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(itemsInRooms);
			
			oos.close();
			
			fos.close();
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	// Read
	public static void loadItemsInRooms()
	{
		File f = new File("ItemsInRooms.dat");
		
		if (!f.exists())
		{
			populateRoomItems();
			saveItemsInRooms();
			
			return;
		}
		
		try
		{
			FileInputStream fis = new FileInputStream("ItemsInRooms.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			itemsInRooms = (HashMap<Integer, Item>) ois.readObject();
			
			ois.close();
			fis.close();
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		} catch (ClassNotFoundException c)
		{
			System.out.println("Class not found");
			c.printStackTrace();
		}
		
		return;
	}
	
	public static void main(String[] args)
	{
		
		launch(args);
	}
	
	public static void populateRoomItems()
	{
		itemsInRooms.put(0, new Armor("Amr_00", "Cloth Armor", "Rugged clothes from your village", 10, null));
		itemsInRooms.put(1, new Armor("Amr_01", "Leather Armor", "Armor crafted from deer skin", 20, null));
		itemsInRooms.put(17,
				new Armor("Amr_02", "Chainmail Armor", "Armor with many iron links woven together", 40, null));
		itemsInRooms.put(25, new Armor("Amr_03", "Plate Armor", "Armor forged from steel", 80, null));
		
		itemsInRooms.put(0, new Weapon("Wep_00", "Branch", "A tree branch", 3, null));
		itemsInRooms.put(5, new Weapon("Wep_01", "Iron Sword", "A sword forged from iron", 3, null));
		itemsInRooms.put(12, new Weapon("Wep_02", "Steel Sword", "A sword forged from steel", 3, null));
		itemsInRooms.put(20, new Weapon("Wep_03", "Tungsteel Sword", "A sword forged from tungsteel", 3, null));
		
		itemsInRooms.put(2, new Artifact("art_00", "Map", "Shows the map of the entire dungeon", null));
		itemsInRooms.put(20, new Artifact("art_01", "Potion Bottle", "Restores any lost HP", null));
		itemsInRooms.put(15,
				new Artifact("art_02", "Well of Life", "Restores any lost HP - Fills Potion Bottle", null));
		itemsInRooms.put(4, new Artifact("art_03", "Torch", "Emits a light to see through the darkness", null));
		itemsInRooms.put(16, new Artifact("art_04", "Emerald Rune", "A glowing green stone", null));
		itemsInRooms.put(17, new Artifact("art_05", "Sapphire Rune", "A glowing blue stone", null));
		itemsInRooms.put(9, new Artifact("art_06", "Golden Skull #1", "A golden skull", null));
		itemsInRooms.put(22, new Artifact("art_07", "Golden Skull #2", "A golden skull", null));
		itemsInRooms.put(29, new Artifact("art_08", "Golden Skull #3", "A golden skull", null));
		itemsInRooms.put(5, new Artifact("art_09", "Shiny Iron Key", "A key forged from iron that's shiny", null));
		itemsInRooms.put(18, new Artifact("art_10", "Golden Key", "A key casted from gold", null));
		itemsInRooms.put(20, new Artifact("art_11", "Dirty Key", "A dirty key", null));
	}
	
	/**
	 * @param itemID
	 */
	public void removeItemFromRoom(int roomId)
	{
		itemsInRooms.remove(roomId);
		
	}
	
	/*
	 * public void populateMonsters() { Monster m0 = new Monster("Mon_00",
	 * "Toxic Wolfspider",
	 * "This spider creeps in the caves of tombs and scurries around to keep you from knowing when he is going to attack. He stands tall with 8 grey and furry legs and is as tall as a wolf. As soon as you are still he slowly creep towards you and stick you with his sharp pincers and release his toxic venom."
	 * , 5, rand.nextInt(2) + 1); Monster m1 = new Monster("Mon_01",
	 * "Soul Serpent",
	 * "The soul stealing serpent will attack you to steal your soul with one of its bites."
	 * , 10, rand.nextInt(3) + 1); Monster m2 = new Monster("Mon_02",
	 * "Terror Tiger",
	 * "This ferocious tiger will terrorize you with razor sharp teeth and vibrant green eyes."
	 * , 15, rand.nextInt(3) + 1); Monster m3 = new Monster("Mon_03",
	 * "Tomb Phantom",
	 * "This tomb phantom haunts the tomb with his loud screeches and screams. This monster can make you deaf until next fight."
	 * , 20, rand.nextInt(4) + 1); Monster m4 = new Monster("Mon_04",
	 * "Bloodthirsty Centaur",
	 * "The bloodthirsty centaur is on the run for your flesh but most of all your fresh blood. He is all so powerful his enormous strong arms, red glowing eyes, and 8-foot stature."
	 * , 25, rand.nextInt(4) + 1); Monster m5 = new Monster("Mon_05",
	 * "Disemboweled Mummy",
	 * "This mummy is stands at an 8 foot towering height, is covered in cobwebs, and smells of road kill. He will chase you down and reach for any limb he can get so he can take your organs and feel alive once again."
	 * , 30, rand.nextInt(4) + 1); Monster m6 = new Monster("Mon_06",
	 * "The Insane Witch",
	 * "This witch will cast a spell on you and put you in another room. Her skin is a wrinkly as balled up piece of paper, she has long white hair."
	 * , 40, rand.nextInt(5) + 1); Monster m7 = new Monster("Mon_07",
	 * "Ballistic Beast",
	 * "This beast you will face is 7 foot tall, muscular, and very hairy. He will fight till the death with his large hands and sharp claws. His favorite move is ripping your heart from your chest and hearing it beat before you fall to your death."
	 * , 60, rand.nextInt(5) + 2);
	 * 
	 * monsterArray.add(m0); monsterArray.add(m1); monsterArray.add(m2);
	 * monsterArray.add(m3); monsterArray.add(m4); monsterArray.add(m5);
	 * monsterArray.add(m6); monsterArray.add(m7);
	 * 
	 * }
	 * 
	 * public void addMonsterRooms() {
	 * monsterArray.get(0).addLocation(rooms.get(4));
	 * monsterArray.get(0).addLocation(rooms.get(5));
	 * monsterArray.get(1).addLocation(rooms.get(11));
	 * monsterArray.get(1).addLocation(rooms.get(13));
	 * monsterArray.get(1).addLocation(rooms.get(17));
	 * 
	 * monsterArray.get(2).addLocation(rooms.get(12));
	 * monsterArray.get(2).addLocation(rooms.get(14));
	 * monsterArray.get(2).addLocation(rooms.get(16));
	 * 
	 * monsterArray.get(3).addLocation(rooms.get(22));
	 * monsterArray.get(3).addLocation(rooms.get(23));
	 * 
	 * monsterArray.get(4).addLocation(rooms.get(18));
	 * 
	 * monsterArray.get(5).addLocation(rooms.get(25));
	 * monsterArray.get(5).addLocation(rooms.get(26));
	 * monsterArray.get(5).addLocation(rooms.get(27));
	 * monsterArray.get(5).addLocation(rooms.get(28));
	 * 
	 * monsterArray.get(6).addLocation(rooms.get(20));
	 * 
	 * monsterArray.get(7).addLocation(rooms.get(29)); }
	 * 
	 * public ArrayList<Room> populateRooms() { roomAL = new ArrayList<Room>();
	 * roomNameAL = new ArrayList<String>(); roomDescriptionAL = new
	 * ArrayList<String>();
	 * 
	 * roomNameAL.add(0, "Cave Entrance"); roomNameAL.add(1, "Crumbling Room");
	 * roomNameAL.add(2, "Map Room"); roomNameAL.add(3, "Cavern");
	 * roomNameAL.add(4, "Spider Room"); roomNameAL.add(5, "Unknown Heroes Tomb"
	 * ); roomNameAL.add(6, "Cavern Pit"); roomNameAL.add(7, "Breached Cavern");
	 * roomNameAL.add(8, "Corridor of Water"); roomNameAL.add(9, "Cave Exit");
	 * roomNameAL.add(10, "Entrance to Lower Tombs"); roomNameAL.add(11,
	 * "Sapphire Studies"); roomNameAL.add(12, "Emerald Studies");
	 * roomNameAL.add(13, "Sapphire Storage Facility"); roomNameAL.add(14,
	 * "Emerald Storage Facility"); roomNameAL.add(15, "Ruby of Wonders");
	 * roomNameAL.add(16, "Emerald Mausoleum"); roomNameAL.add(17,
	 * "Sapphire Mausoleum"); roomNameAL.add(18, "Banquet Hall");
	 * roomNameAL.add(19, "Study Room"); roomNameAL.add(20, "Alchemist's Lab");
	 * roomNameAL.add(21, "Entrance to Upper Tomb"); roomNameAL.add(22,
	 * "Torture Chamber"); roomNameAL.add(23, "Holding Cells");
	 * roomNameAL.add(24, "Grand Hall"); roomNameAL.add(25, "Southwest Tomb");
	 * roomNameAL.add(26, "Southeast Tomb"); roomNameAL.add(27, "Northwest Tomb"
	 * ); roomNameAL.add(28, "Northeast Tomb"); roomNameAL.add(29,
	 * "Hall of Heroes"); roomNameAL.add(30, "Tomb of Doom Exit");
	 * 
	 * roomDescriptionAL.add(0,
	 * "After regaining consciousness, you begin to focus looking around the room to see where you have landed. The only light that emits into the cave is from the hole which was created from where you fell. The walls are covered in dead roots all extending towards the hole in the ceiling. There is a branch on the floor. You are able to see an opening to the North and another opening to the East."
	 * ); roomDescriptionAL.add(1,
	 * "Upon squeezing into this room, you notice that this room has not been disturbed in many years. The walls have mostly caved in, leaving piles of stone leading up to the mostly dirt walls. There isn't much space to move around, except for a few feet ahead of you."
	 * ); roomDescriptionAL.add(2,
	 * "As you enter this room, you barely make out a parchment that is laying on the ground, presumably a map that was dropped years ago from a possible adventurer. The room is dark and damp, but able to make out from what little light that shines through from the previous room you left. The walls are mostly compacted dirt with a few roots scattered across the room. The only doorway is to the West, from which you came from."
	 * ); roomDescriptionAL.add(3,
	 * "After entering this cavern, the light from where you entered emits just enough glow throughout the room reflecting off the naturally formed rocks with stalagmites hanging about 20 feet in the air from the ceiling. The jagged rocks around the perimeter of the room look to have been shaped with pickaxes long ago. The entire room is damp from humidity, working in your favor as it is helping reflect enough light to make out the dim room. You noticed a small man size hole directly to the East that you can see a flickering of light. There is another small hole to the North East big enough to crawl through, and another man sized opening to the North West."
	 * ); roomDescriptionAL.add(4,
	 * "Crawling through debris and into the room, you are able to see well in this room. There is a torch mounted on the wall, unknown as to how it is lit or how long it has been burning. The room contains many skeletal remains scattered throughout. Ranging through the walls of the room, there are large, thick silver webs that contain the skeletal remains of many different creatures, including humans. To the north there is a larger passage that is covered in more webs than you would like to push through and a small crawlable hole to the west. "
	 * ); roomDescriptionAL.add(5,
	 * "Entering this room, you see half rotten corpses and skeletal remains of humans throughout the room. The sight of the legion of corpses send chills down your spine, hoping you will not encounter the same fate as the other men in this room. The walls of the room contain large, thick silver webs that also possess the skeletal remains of many different creatures, including humans. Three of the giant webs look as if they might carry something useful. There is a small crawlable hole to the West and a passage covered in webs to the South."
	 * ); roomDescriptionAL.add(6,
	 * "Upon entering this room, you notice a large pit in the center of the room, unknown as to how far it goes down. You are unable to see the ceiling as the light is not emitting bright enough to see. The walls contain sources of precious metals as you can make out veins of copper and iron scattered along the perimeter. You can hear and feel a breeze of wind, believing that the draft could possibly be coming from the North. There is a naturally formed pathway around the pit to the North with a man-sized hole. There is also a formed pathway to the South with a hole barely large enough to squeeze through."
	 * ); roomDescriptionAL.add(7,
	 * "Upon entering this room, you notice that the entire floor is made from smooth stones that have been hewed by someone. The walls are also made from hewed stones stacked on top of one another. You immediately notice a stone door to the North which looks as if it hasn't been opened in many years and you can see where a key could be used. There is a heavy draft that you can hear and feel coming from behind this door."
	 * ); roomDescriptionAL.add(8,
	 * "Entering this room, you notice two pathways on each side of the room with a small ditch carved out into the ground in between the paths. The floor has hewed stones laid out on either sides of the ditch. The walls have hewed stones stacked laid perfectly by a mason. To the East is a metal bar door."
	 * ); roomDescriptionAL.add(9,
	 * "Inside this room, you immediately notice a giant stone stairwell spiraling up wide enough for two men to walk comfortably in the North East section of the room. The room contains a corpse of a man at the bottom of the stairwell wearing leather armor whose fate is unknown to you. It seems that the man has not been dead for very long, as the rest of the room contains dust mixed with dirt thick enough to leave footprints. The only doorway is to the West."
	 * ); roomDescriptionAL.add(10,
	 * "Entering into this room, you immediately notice that you are no longer in a damp cave. The walls, floor, and ceiling seen to be made out of dark marble that has been cut with precision. Beautiful stonework with mosaic of jewels are on each of the walls. There are sets of two unlit torches next to each door. You see a stone door to the East, containing sapphire gems above the door. To the North is a metal door with ruby gems above it. There is a door to the West containing emerald gemstones above it."
	 * ); roomDescriptionAL.add(11,
	 * "There is an unlit fire pit in center of room. Blue stonework and mosaic sapphire gems are along the walls. There are cobwebs throughout the corners of the room with dust covering most of the floor. You can see a trail through the dust where something has been sliding across the floor. There is a metal door to the North with rubies above the door. To the East is a metal door. To the West you see a stone door."
	 * ); roomDescriptionAL.add(12,
	 * "There is an unlit Fire Pit in center of the room. Emerald stonework and gemstone mosaics along the walls. There is a metal door to the West. To the North East corner of the room there is a metal door with rubies above the door. The East wall has a stone door with emerald gemstones above it. "
	 * ); roomDescriptionAL.add(13,
	 * "Wall covered in shattered glass (mosaic) reflecting blue light into the room. Seems to have been created with great care to torture to the mind, seeming to bend reality as the door closes behind you. The floor has shimmering blue glass scattered throughout the room. There is a metal door to the North. There is a metal door to the South West with sapphire gemstones decorated above the door."
	 * ); roomDescriptionAL.add(14,
	 * "Wall covered in shattered glass (mosaic) reflecting emerald light into the room. Black dust covers the walls and doors. Even the air is thick with dust. Bones are scattered across the room. There are broken urns in each corner of the room which have been covered in cobwebs. To the North is a metal door. To the South East is a metal door. "
	 * ); roomDescriptionAL.add(15,
	 * "As you enter this room, the walls and floor being to reflect bright red stonework with engravings faintly glowing red. Over the floor you can see vines which have extended from each corner of the room towards the center, forming a near perfect circle in the center of the room. In the center of the vines, there is a stone hole which has been filled with a glowing red liquid, unknown of its composition. To the North is a metal door behind an additional barred door, where above the door you see a red rune in the middle, but two missing runes on each side of it. To the South is a metal door. To the South West is a metal door with emeralds decorated above the door. To the South East contains sapphire gemstones above a metal door."
	 * ); roomDescriptionAL.add(16,
	 * "The walls and floor being to reflect bright red and stonework with engravings faintly glowing red. The room is mostly empty, except for a single large tomb made from stone with a plethora of carvings that have had almost been worn out, almost unable to see. The cover of the tomb seems to have been smashed, caving in onto of a resting corpse. To the East is a metal door behind an additional barred door. To the South, you see a metal door."
	 * ); roomDescriptionAL.add(17,
	 * "This room is covered in eggs as large as a man's fist, most of them have been entirely smashed. Upon examining, you notice that the eggs belong to some type of serpent, hopefully not having to encounter such a creature. the walls and floor being to reflect bright blue and stonework with engravings faintly glowing blue."
	 * ); roomDescriptionAL.add(18,
	 * "Upon entering this room, you see beautiful, ornate humanoid chameleon-like warrior statues lined throughout the east and west walls with unlit wall torches in between each of the statues. The walls and floor appear to be made out of limestone which have been decorated with silver engravings. Cobwebs are scattered throughout the room, prominently in the corners of the room. The ceiling contains three large metal chandeliers that are circular in shape with candles that have been lit, emitting a glow blue. There are many skeletal corpses with the skulls all missing laid out throughout the room. You can see 4 piles that are stacked perfectly 6 feet high of human skulls. There is a metal door to the South. To the North is a giant double metal door decorated in gold and silver. To the North West and North East are two wooden doors. To the South West and South East are two metal doors."
	 * ); roomDescriptionAL.add(19,
	 * "Upon entering this room, you notice the floor is made from poorly crafted wood. There is a makeshift bed in corner of room with the remains of two skeletal human bodies. There are bookcases filled with tattered tomes along the entire northern wall. You can see a small wooden table in the center of the room big enough to sit 6 at the table, each with a chair in the spots. To the East there is a wooden door. To the North contains a metal door."
	 * ); roomDescriptionAL.add(20,
	 * "The floor is weathered planks covered in dust and debris. In the corner of the room across from the door there is a massive alchemists desk littered with vials and parchment. There is a bookcase to the left of the desk with tomes and jars containing various organs in a murky liquid. There is a pallet bed of pillows and fur on the South wall with a lantern and a medicine bag on the floor next to it. To the West is a wooden door. To the North there is a metal door."
	 * ); roomDescriptionAL.add(21,
	 * "Directly to the north you see a double wide staircase made out of onyx with a red velvet carpet in the center leading to the top of the staircase. The walls are also made from onyx with red tapestries lining each side of the walls. These tapestries have an emblem of a skull without a jaw and a serpent intertwined through the eyeholes raising above the skull. To the south is a giant double metal door decorated in gold and silver. To the West and the East are two caged metal doors, able to see through the bars only enough to know something evil has happened in each of these rooms."
	 * ); roomDescriptionAL.add(22,
	 * "As you enter you note tables lining the wall to right they are covered in body parts and horrific tools for torture. Along the north wall there are mummified corpses shackled to the walls. In the center of the room there is a cage large enough to hold several men. The floor of this cage is covered in jagged pieces of slate angled to inflict pain on the caged. Spears are secured between every bar of the cage seemingly placed there to prod the inhabitants across the sharp rocks. The entire room is a muted gray that reinforces the utter despair of the room. In the back corner of the room is a pile of broken tables. One intact table still stands, black with aged blood and leather straps to secure the poor soul in place. There is a caged door to the East that you can see through."
	 * ); roomDescriptionAL.add(23,
	 * "Cages barely large enough for a small man line the North and East walls, and the length of the South wall to the left of another door. To your right is a wooden bin that contains more human remains, as if preparation for disposal. The corpses in these cages were not clothed but collared and chained to the floor. To the left of the door are two large cabinets made of dark wood. The door to one has fallen to the floor and you can see collars, chains, and iron pokers. There are two floor lamps down the length of the room with iron pokers resting inside. There is a caged door to the West that you can see through."
	 * ); roomDescriptionAL.add(24,
	 * "After climbing up the stairs, you see a vaulted ceiling made from pure white marble, staggering even higher to about 30 feet high. The floor seems to be one entire slab of the same marble, completely level and smooth throughout the entire room. Lined up perfectly to the ceiling on the west and east side of the room, right before the ceiling begins to stagger upwards are 8 large marble columns giving support to the ceiling. There are many fresh corpses of humans throughout the room for what seems like a legion of men recently deceased from a gruesome battle that had to have happened within the past year. The air is thick with the stench of decay. There are 4 arches with an opening wide enough for 3 man to walk through to the North East, North West, South West, and South East. There is a set of large double doors to the North with sliver and gold plating containing 3 unlit torches to the right side of the door. To the South is the large onyx stairwell leading to the Lower Tombs."
	 * ); roomDescriptionAL.add(25,
	 * "Tombs are carved into the white marble walls. The dead can be seen covered in tattered linen, some with their worldly possessions. There is a large yet simple sarcophagus beautifully constructed of the darkest onyx. There are floor lanterns in each corner of the tomb, and a wall torch next to the arch. At the foot of the sarcophagus there is a small bench to mourn the revered fallen. There is a large archway to the North and to the East."
	 * ); roomDescriptionAL.add(26,
	 * "Tombs are carved into the white marble walls. The dead can be seen covered in tattered linen, some with their worldly possessions (swords, books, etc). There is a large yet simple sarcophagus beautifully constructed of the darkest onyx. There are floor lanterns in each corner of the tomb, and a wall torch next to the arch. At the foot of the sarcophagus there is a small bench to mourn the revered fallen. There is a large archway to the North and to the West."
	 * ); roomDescriptionAL.add(27,
	 * "Tombs are carved into the white marble walls. The dead can be seen covered in tattered linen, some with their worldly possessions. There is a large yet simple sarcophagus beautifully constructed of the darkest onyx. There are floor lanterns in each corner of the tomb, and a wall torch next to the arch. At the foot of the sarcophagus there is a small bench to mourn the revered fallen. There is a large archway to the South and to the East."
	 * ); roomDescriptionAL.add(28,
	 * "Tombs are carved into the white marble walls. The dead can be seen covered in tattered linen, some with their worldly possessions. There is a large yet simple sarcophagus beautifully constructed of the darkest onyx. There are floor lanterns in each corner of the tomb, and a wall torch next to the arch. At the foot of the sarcophagus there is a small bench to mourn the revered fallen. There is a large archway to the South and to the East."
	 * ); roomDescriptionAL.add(29,
	 * "Monstrous room with hulking paintings depicting battles of the past. Large banners portraying an emblem of a skull without a jaw and a serpent intertwined through the eyeholes raising above the skull drape the columns that support the ceiling which is 20 feet high. In each corner of the room the fire in each of the enormous pits is ablaze. You can see 3 empty pedestals in the center of the room, with a round cutout in the center of each. To the South is a set of large double doors to the North with silver and gold plating. To the North you can see a large marble double door with light protruding through the cracks."
	 * ); roomDescriptionAL.add(30,
	 * "You beat the game and escaped the tomb! Welcome to the outside world!");
	 * 
	 * for (int i = 0; i <= 30; i++) { roomAL.add(i, new Room(i,
	 * roomNameAL.get(i), roomDescriptionAL.get(i))); }
	 * 
	 * // Level 1 Room.assignExits(new Exit(0), roomAL.get(0), "north",
	 * roomAL.get(3), "south"); Room.assignExits(new Exit(1), roomAL.get(0),
	 * "east", roomAL.get(2), "west"); Room.assignExits(new Exit(2),
	 * roomAL.get(3), "southeast", roomAL.get(4), "west"); Room.assignExits(new
	 * Exit(3), roomAL.get(3), "northeast", roomAL.get(5), "west");
	 * Room.assignExits(new Exit(4), roomAL.get(4), "north", roomAL.get(5),
	 * "south"); Room.assignExits(new Exit(5), roomAL.get(3), "west",
	 * roomAL.get(6), "east"); Room.assignExits(new Exit(6), roomAL.get(6),
	 * "south", roomAL.get(1), "north"); Room.assignExits(new Exit(7),
	 * roomAL.get(6), "north", roomAL.get(7), "south"); Room.assignExits(new
	 * Exit(8), roomAL.get(7), "north", roomAL.get(8), "south");
	 * roomAL.get(7).getNorthExit().lockExit();
	 * roomAL.get(7).getNorthExit().setLockDescription("The door is locked!");
	 * Room.assignExits(new Exit(9), roomAL.get(8), "east", roomAL.get(9),
	 * "west");
	 * 
	 * // Level 2 Room.assignExits(new Exit(10), roomAL.get(10), "north",
	 * roomAL.get(15), "south"); Room.assignExits(new Exit(11), roomAL.get(10),
	 * "west", roomAL.get(12), "east"); Room.assignExits(new Exit(12),
	 * roomAL.get(10), "east", roomAL.get(11), "west"); Room.assignExits(new
	 * Exit(13), roomAL.get(11), "north", roomAL.get(15), "southeast");
	 * Room.assignExits(new Exit(14), roomAL.get(11), "east", roomAL.get(13),
	 * "west"); Room.assignExits(new Exit(15), roomAL.get(12), "north",
	 * roomAL.get(15), "southwest"); Room.assignExits(new Exit(16),
	 * roomAL.get(12), "west", roomAL.get(14), "east"); Room.assignExits(new
	 * Exit(17), roomAL.get(13), "north", roomAL.get(17), "south");
	 * Room.assignExits(new Exit(18), roomAL.get(14), "north", roomAL.get(16),
	 * "south"); Room.assignExits(new Exit(19), roomAL.get(15), "north",
	 * roomAL.get(18), "south"); Room.assignExits(new Exit(20), roomAL.get(16),
	 * "east", roomAL.get(18), "southwest"); Room.assignExits(new Exit(21),
	 * roomAL.get(17), "west", roomAL.get(18), "southeast");
	 * Room.assignExits(new Exit(22), roomAL.get(18), "north", roomAL.get(21),
	 * "south"); Room.assignExits(new Exit(23), roomAL.get(18), "northwest",
	 * roomAL.get(19), "east"); Room.assignExits(new Exit(24), roomAL.get(18),
	 * "northeast", roomAL.get(20), "west"); Room.assignExits(new Exit(25),
	 * roomAL.get(19), "north", roomAL.get(22), "south"); Room.assignExits(new
	 * Exit(26), roomAL.get(20), "north", roomAL.get(23), "south");
	 * Room.assignExits(new Exit(27), roomAL.get(22), "east", roomAL.get(21),
	 * "west"); Room.assignExits(new Exit(28), roomAL.get(23), "west",
	 * roomAL.get(21), "east");
	 * 
	 * // Level 3 Room.assignExits(new Exit(29), roomAL.get(24), "north",
	 * roomAL.get(29), "south"); Room.assignExits(new Exit(30), roomAL.get(24),
	 * "southeast", roomAL.get(26), "west"); Room.assignExits(new Exit(31),
	 * roomAL.get(24), "northeast", roomAL.get(28), "west");
	 * Room.assignExits(new Exit(32), roomAL.get(24), "northwest",
	 * roomAL.get(27), "east"); Room.assignExits(new Exit(33), roomAL.get(24),
	 * "southwest", roomAL.get(25), "east"); Room.assignExits(new Exit(34),
	 * roomAL.get(25), "north", roomAL.get(27), "south"); Room.assignExits(new
	 * Exit(35), roomAL.get(26), "north", roomAL.get(28), "south");
	 * Room.assignExits(new Exit(36), roomAL.get(29), "north", roomAL.get(30),
	 * "south");
	 * 
	 * roomAL.get(0).setMapLocationImage(new Image("res/Room_00.jpg"));
	 * roomAL.get(1).setMapLocationImage(new Image("res/Room_01.jpg"));
	 * roomAL.get(2).setMapLocationImage(new Image("res/Room_02.jpg"));
	 * roomAL.get(3).setMapLocationImage(new Image("res/Room_03.jpg"));
	 * roomAL.get(4).setMapLocationImage(new Image("res/Room_04.jpg"));
	 * roomAL.get(5).setMapLocationImage(new Image("res/Room_05.jpg"));
	 * roomAL.get(6).setMapLocationImage(new Image("res/Room_06.jpg"));
	 * roomAL.get(7).setMapLocationImage(new Image("res/Room_07.jpg"));
	 * roomAL.get(8).setMapLocationImage(new Image("res/Room_08.jpg"));
	 * roomAL.get(9).setMapLocationImage(new Image("res/Room_09.jpg"));
	 * roomAL.get(10).setMapLocationImage(new Image("res/Room_10.jpg"));
	 * roomAL.get(11).setMapLocationImage(new Image("res/Room_11.jpg"));
	 * roomAL.get(12).setMapLocationImage(new Image("res/Room_12.jpg"));
	 * roomAL.get(13).setMapLocationImage(new Image("res/Room_13.jpg"));
	 * roomAL.get(14).setMapLocationImage(new Image("res/Room_14.jpg"));
	 * roomAL.get(15).setMapLocationImage(new Image("res/Room_15.jpg"));
	 * roomAL.get(16).setMapLocationImage(new Image("res/Room_16.jpg"));
	 * roomAL.get(17).setMapLocationImage(new Image("res/Room_17.jpg"));
	 * roomAL.get(18).setMapLocationImage(new Image("res/Room_18.jpg"));
	 * roomAL.get(19).setMapLocationImage(new Image("res/Room_19.jpg"));
	 * roomAL.get(20).setMapLocationImage(new Image("res/Room_20.jpg"));
	 * roomAL.get(21).setMapLocationImage(new Image("res/Room_21.jpg"));
	 * roomAL.get(22).setMapLocationImage(new Image("res/Room_22.jpg"));
	 * roomAL.get(23).setMapLocationImage(new Image("res/Room_23.jpg"));
	 * roomAL.get(24).setMapLocationImage(new Image("res/Room_24.jpg"));
	 * roomAL.get(25).setMapLocationImage(new Image("res/Room_25.jpg"));
	 * roomAL.get(26).setMapLocationImage(new Image("res/Room_26.jpg"));
	 * roomAL.get(27).setMapLocationImage(new Image("res/Room_27.jpg"));
	 * roomAL.get(28).setMapLocationImage(new Image("res/Room_28.jpg"));
	 * roomAL.get(29).setMapLocationImage(new Image("res/Room_29.jpg"));
	 * roomAL.get(30).setMapLocationImage(new Image("res/Room_30.jpg"));
	 * 
	 * return roomAL;
	 * 
	 * }
	 */
	
}
