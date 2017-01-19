package uk.ac.cam.chtj2.oopjava.tick5;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class HelloActionWorld2 extends JFrame {
	
	HelloActionWorld2() {
		super("Hello Action");                   //create window & set title text
		final JLabel label;
		setDefaultCloseOperation(EXIT_ON_CLOSE); //close button on window quits app.
		//configure the layout of the pane associated with this window as a "BoxLayout"
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		label = new JLabel("Button unpressed");  //create graphical text label
		add(label);                              //associate "label" with window
		JButton button = new JButton("Press me");//create graphical button
		add(button);                             //associated "button" with window
		//create an instance of an anonymous inner class to hand the event
		button.addActionListener( new ActionListener() {
			int count = 0;
			public void actionPerformed(ActionEvent e) {
				label.setText("Button pressed " + ++count + " time(s)");
			}
		} );
		setSize(320,240);                        //set size of window
	}
	
	public static void main(String[] args) {
		HelloActionWorld2 hello = new HelloActionWorld2(); //create instance
		hello.setVisible(true);                          //display window to user
	}
}