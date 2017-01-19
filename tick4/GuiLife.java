package uk.ac.cam.chtj2.oopjava.tick4;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import uk.ac.cam.acr31.life.World;

public class GuiLife extends JFrame {
	
	PatternPanel patternPanel; // private?
	ControlPanel controlPanel;
	GamePanel gamePanel;
	
	public GuiLife() {
		super("GuiLife");
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JComponent optionsPanel = createOptionsPanel();
		add(optionsPanel, BorderLayout.WEST);
		JComponent gamePanel = createGamePanel();
		add(gamePanel, BorderLayout.CENTER);
}
	
	private JComponent createOptionsPanel() {
		Box result = Box.createVerticalBox();
		result.add(createSourcePanel());
		result.add(createPatternPanel());
		result.add(createControlPanel());
		return result;
	}
	
	private void addBorder(JComponent component, String title) {
		Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border tb = BorderFactory.createTitledBorder(etch, title);
		component.setBorder(tb);
	}
	
	private JComponent createGamePanel() {
		JPanel holder = new JPanel();
		addBorder(holder,Strings.PANEL_GAMEVIEW);
		GamePanel result = new GamePanel();
		holder.add(result);
		gamePanel = result;
		return new JScrollPane(holder);
	}
	
	private JComponent createSourcePanel() {
		SourcePanel result = new SourcePanel();
		addBorder(result, Strings.PANEL_SOURCE);
		return result;
	}
	
	private PatternPanel createPatternPanel() { //return type was JComponent
		PatternPanel result = new PatternPanel();
		addBorder(result, Strings.PANEL_PATTERN);
		patternPanel = result;
		return result;
	}

	private ControlPanel createControlPanel() { //return type was JComponent
		ControlPanel result = new ControlPanel();
		addBorder(result, Strings.PANEL_CONTROL);
		controlPanel = result;
		return result;
	}

	public static void main(String[] args) {
		GuiLife gui = new GuiLife();

	    try {
	    	String url="http://www.cl.cam.ac.uk/teaching/current/OOProg/life.txt";
	    	List<Pattern> list = PatternLoader.loadFromURL(url);
	    	gui.patternPanel.setPatterns(list);
	    	World w = gui.controlPanel.initialiseWorld(list.get(1));
	    	gui.gamePanel.display(w);
	    }
	    catch (IOException ioe) {
	       //TODO: don't leave empty exception handlers!
	    }
	    catch (PatternFormatException e) {
	    	
	    }
		gui.setVisible(true);
	}
}

//Create a new field called patternPanel of type PatternPanel inside the class GuiLife