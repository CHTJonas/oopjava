package uk.ac.cam.chtj2.oopjava.tick4;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SourcePanel extends JPanel {
	public SourcePanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JRadioButton none = new JRadioButton(Strings.BUTTON_SOURCE_NONE, true);
		JRadioButton file = new JRadioButton(Strings.BUTTON_SOURCE_FILE, true);
		JRadioButton library = new JRadioButton(Strings.BUTTON_SOURCE_LIBRARY, true);
		//add RadioButtons to this JPanel
		add(none);
		add(file);
		add(library);
		//create a ButtonGroup containing all four buttons
		//Only one Button in a ButtonGroup can be selected at once
		ButtonGroup group = new ButtonGroup();
		group.add(none);
		group.add(file);
		group.add(library);
	}
}