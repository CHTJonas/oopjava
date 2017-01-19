package uk.ac.cam.chtj2.oopjava.tick3;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.util.List;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;

public class LoaderLife {
	static String worldType = null;
	
	public static void main(String[] args) throws java.io.IOException {
		String webAddress = null;
		String filename = null;
		
		try {
			List<Pattern> pList = null;
			Pattern p = null;
			// Generally parse and check the user input
			switch (args.length) {
				case 1:
					if (args[0].equals("--array") || args[0].equals("--long") || args[0].equals("--aging"))
						throw new PatternFormatException("Please specify a pattern source.");
					if (args[0].startsWith("http://")) {
						webAddress = args[0];
						pList = PatternLoader.loadFromURL(args[0]);
					} else {
						filename = args[0];
						pList = PatternLoader.loadFromDisk(args[0]);
					}
					for(int i = 0 ; i < pList.size() ; i++) {
						if (pList.get(i) != null)
							System.out.println(i + "\t" + pList.get(i).getName() + "\t" + pList.get(i).getAuthor());
					}
					return;
				case 2:
					worldType = "--array";
					if (args[0].startsWith("http://")) {
						webAddress = args[0];
						pList = PatternLoader.loadFromURL(args[0]);
					} else {
						filename = args[0];
						pList = PatternLoader.loadFromDisk(args[0]);
					}
					break;
				case 3:
					worldType = args[0];
					if (args[1].startsWith("http://")) {
						webAddress = args[1];
						pList = PatternLoader.loadFromURL(args[1]);
					} else {
						filename = args[1];
						pList = PatternLoader.loadFromDisk(args[1]);
					}
					break;
				default:
					System.out.println("LoaderLife [optional worldType] [patternSource] [option patternIndex]");
					return;
			}
			// Get the specific pattern required
			if (args.length == 2) {
				try {p = pList.get(Integer.parseInt(args[1]));}
				catch (NumberFormatException w) {
					throw new PatternFormatException("Could not interpret patternIndex as an integer (given '" + args[1] + "').");
				} catch (IndexOutOfBoundsException w) {
					System.out.println("Chosen index number not present in list.");
					return;
				}
			} else if (args.length == 3) {
				try {p = pList.get(Integer.parseInt(args[2]));}
				catch (NumberFormatException w) {
					throw new PatternFormatException("Could not interpret patternIndex as an integer (given '" + args[2] + "').");
				} catch (IndexOutOfBoundsException w) {
					System.out.println("Chosen index number not present in list.");
					return;
				}
			} // If by this point (args.length != 3 && args.length != 2) then something has gone VERY wrong!
			
			World world = null;
			
			if (worldType.equals("--array")) {
				world = new ArrayWorld(p.getWidth(), p.getHeight());
			} else if (worldType.equals("--long")) {
				world = new PackedWorld();
			} else if (worldType.equals("--aging")) {
				world = new AgingWorld(p.getWidth(), p.getHeight());
			} else {
				System.out.println("Unknown [worldType] option " + worldType + "  specified.");
				return;
			}
			
			p.initialise(world);
			play(world);
		} catch (PatternFormatException e) {
			System.out.println(e.getMessage());
		} catch (UnknownHostException e) {
			System.out.println("An error occurred loading from URL: " + webAddress);
		} catch (FileNotFoundException e) {
			if (filename != null ) {
				System.out.println("An error occurred loading from file: " + filename);
			} else {
				System.out.println("An error occurred loading from URL: " + webAddress);
			}
		}
	}
	
	public static void play(World world) throws java.io.IOException {
		int userResponse = 0;
		WorldViewer viewer = new WorldViewer();
		while (userResponse != 'q') {
			world.print(new OutputStreamWriter(System.out));
			viewer.show(world);
			userResponse = System.in.read();
			if (worldType.equals("--array")) { world = (ArrayWorld) world.nextGeneration(0); }
			if (worldType.equals("--long")) { world = (PackedWorld) world.nextGeneration(0); }
			if (worldType.equals("--aging")) { world = (AgingWorld) world.nextGeneration(0); }
			//System.out.println();
		}
		viewer.close();
	}
}
//NAME:AUTHOR:WIDTH:HEIGHT:STARTCOL:STARTROW:CELLS