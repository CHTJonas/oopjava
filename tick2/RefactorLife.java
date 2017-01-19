package uk.ac.cam.chtj2.oopjava.tick2;

import java.io.OutputStreamWriter;

import uk.ac.cam.acr31.life.World;

public class RefactorLife {
	static String worldType = null;
	
	public static void main(String[] args) throws java.io.IOException {
		try {
			if (args.length == 1) {
				if (args[0].equals("--array") || args[0].equals("--long")) throw new PatternFormatException("Please specify a pattern.");
			}
			if (args.length != 1 && args.length != 2) {
				System.out.println("RefactorLife [optional worldType] [pattern]");
				return;
			}
			worldType = args.length == 2 ? args[0] : "--array";
			Pattern p = new Pattern(args.length == 2 ? args[1] : args[0]);
			World world = null;
			
			if (worldType.equals("--array")) {
				world = new ArrayWorld(p.getHeight(), p.getWidth());
			} else if (worldType.equals("--long")) {
				world = new PackedWorld();
			} else {
				System.out.println("Unknown [worldType] option " + worldType + "  specified.");
				return;
			}
			
			p.initialise(world);
			play(world);
		} catch (PatternFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//public static void play(TestArrayWorld world) throws java.io.IOException {
	public static void play(World world) throws java.io.IOException {
		int userResponse = 0;
		while (userResponse != 'q') {
			world.print(new OutputStreamWriter(System.out));
			userResponse = System.in.read();
			if (worldType.equals("--array")) { world = (ArrayWorld) world.nextGeneration(0); }
			if (worldType.equals("--long")) { world = (PackedWorld) world.nextGeneration(0); }
		}
	}
}
//NAME:AUTHOR:WIDTH:HEIGHT:STARTCOL:STARTROW:CELLS