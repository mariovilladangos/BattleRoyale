package com.utad.poo.battleroyale.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingApp {
	private JFrame jframe;
    public SwingApp(String[] args) {
        // initialization
    	
    	this.jframe = new TextViewerFrame("Text viewer", 400, 400);
    	
    }

    public void show() {
        	this.jframe.setVisible(true);// Show the gui.
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingApp(args).show();
            }
        });
    }
}