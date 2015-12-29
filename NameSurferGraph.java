/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
		entryList = new ArrayList<NameSurferEntry>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		entryList.clear();
		removeAll();
		update();
		
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entryList.add(entry);
		
		// TEST
//		System.out.println(entryList.get(0).getName());
//		System.out.println(entryList.get(0).getRank(3));
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		display(entryList);
	}
	
	
	private void display(ArrayList list) {
		removeAll();
		setup();
		if (!entryList.isEmpty()) {
			for(int i = 0; i < entryList.size(); i++) {
				for(int j = 0; j < NDECADES; j++) {
					GLabel lbl = new GLabel(entryList.get(i).getName(),
							(double)j * getWidth() / NDECADES, 
							(getHeight() - GRAPH_MARGIN_SIZE - (double)entryList.get(i).getRank(j) / MAX_RANK * (getHeight() - 2 * GRAPH_MARGIN_SIZE)));
					
					
					add(lbl);
					if (j!=0) {
						add(new GLine(lbl.getX(), lbl.getY(), lastLabel.getX(), lastLabel.getY()));
					}
					lastLabel = lbl;
				}
			}
		}
		
//		add(new GLabel("123", 1 * getWidth() / NDECADES, 200.0 / MAX_RANK * getHeight()));
//		add(new GLabel("" + getWidth()/NDECADES , 100 ,100));
//		add(new GLabel("" + 2 * getWidth()/NDECADES , 100 ,120));
//		add(new GLabel("" + 10 * getWidth()/NDECADES , 100 ,140));
//		add(new GLabel("" + 200.0 / MAX_RANK * getHeight()),50,50);
	}
	
	/* draw the initial table */
	private void setup() {
		
		/* decade lines and numbers */
		for(int i = 0; i < NDECADES; i++) {
			add(new GLine( i * this.getWidth() / NDECADES, 0 ,
					i * this.getWidth() / NDECADES, this.getHeight() ));
			add(new GLabel("" + (10 * i + START_DECADE),
					i * this.getWidth() / NDECADES, this.getHeight()));
		}
		
		/* top and bottom line */
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE,
				getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
		
		
		
		
//		add(new GLabel("" + getWidth() ), 100, 100);
	}
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private ArrayList<NameSurferEntry> entryList;
	private GLabel lastLabel;
}
