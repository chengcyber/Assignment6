/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

import acm.graphics.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		database = new NameSurferDataBase(NAMES_DATA_FILE);
		
//		// TEST
//		System.out.println(database.findEntry("A").getName());
//		for(int i = 0 ; i < NDECADES ; i++) {
//			System.out.println("" + database.findEntry("A").getRank(i));
//		}
		
		canvas = new NameSurferGraph();
		add(canvas);
		
		
		txtFld = new JTextField(10);
		txtFld.setActionCommand("Graphic");
		JButton btnGraphic = new JButton("Graphic");
		JButton btnClear = new JButton("Clear");
		
		
		add(new JLabel("Name: "), SOUTH);
		add(txtFld, SOUTH);
		add(btnGraphic, SOUTH);
		add(btnClear, SOUTH);
		
		txtFld.addActionListener(this);
		addActionListeners();
		
		
		//TEST
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		String cmd = e.getActionCommand();
		if (cmd.equals("Graphic")) {
			String text = txtFld.getText();
			
			/* ignore upper or lower case of input */
//			if (text.length() == 1) {
//				text.toUpperCase();
//			} else {
				text = text.toLowerCase();
				text = text.substring(0, 1).toUpperCase() + text.substring(1);
//			}
			
			NameSurferEntry entry = database.findEntry(text);
			if (entry!=null) {
				canvas.addEntry(entry);
				canvas.update();
			}
			// TEST
//			System.out.println(txtFld.getText());
			
		} else if (cmd.equals("Clear")) {
			canvas.clear();
			txtFld.setText("");
		}
	}
	
	
	private NameSurferGraph canvas;
	private NameSurferDataBase database;
	
	private JTextField txtFld;
}
