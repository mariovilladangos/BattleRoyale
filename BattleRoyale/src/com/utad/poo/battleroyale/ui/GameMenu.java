package com.utad.poo.battleroyale.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.utad.poo.battleroyale.general.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GameMenu extends MenusBasic{
	
	public static final Integer FWIDTH = 1000;
	public static final Integer FHEIGHT = 600;
	private Integer nPlayers;
	
	private JFrame frame = new JFrame("Battle Royale");
	private DefaultListModel<String> listModelTerminal;
	private DefaultListModel<String> listModelLive;
	private JScrollPane terminal;
	private JScrollPane live;
	private Integer pendingAction = 0;
	public ButtonListener buttonListener = new ButtonListener();
	
	public GameMenu() {
		this(CharacterMenu.NPLAYERS);
	}
	public GameMenu(Integer nPlayers) {
		this.nPlayers = nPlayers;
		this.visualGameWindow();
	}
	
	
	public class ButtonListener implements ActionListener {
		
		public Integer pendingAction = 0;
		@Override
        public void actionPerformed(ActionEvent e) {
			if (this.pendingAction == 0)
			this.pendingAction = action;
        }
	}

	public void visualGameWindow(){
		
        // VENTANA PRINCIPAL
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.frame.setSize(FWIDTH, FHEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer sWidth = screenSize.width;
        Integer sHeight = screenSize.height;
        this.frame.setLocation(((sWidth / 2) - (FWIDTH / 2)), ((sHeight / 2) - (FHEIGHT / 2)));
        this.frame.setLayout(new BorderLayout());
     
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        
        // LISTA PERSONAJES
        this.listModelTerminal = new DefaultListModel<>();
        JList<String> terminalList = new JList<>(this.listModelTerminal);
        terminalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.terminal = new JScrollPane(terminalList);
        this.terminal.setBorder(BorderFactory.createTitledBorder("Eventos"));
        centerPanel.add(this.terminal, BorderLayout.CENTER);
        
        this.listModelLive = new DefaultListModel<>();
        JList<String> liveList = new JList<>(this.listModelLive);
        liveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.live = new JScrollPane(liveList);
        this.live.setBorder(BorderFactory.createTitledBorder("Vivos (" + this.nPlayers + "/" + this.nPlayers + ")"));
        centerPanel.add(this.live, BorderLayout.EAST);
        
        this.frame.add(centerPanel, BorderLayout.CENTER);

        
        // PANEL ABAJO
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // * BOTONERA
        // * * BOTON  [▶️]
        JButton removeButton = new JButton("▶️");
        removeButton.addActionListener(this.buttonListener);
        		/*new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	performAction(1);
            }
        });*/
        
        // * * BOTON [▶️▶️]
        JButton saveButton = new JButton("▶️▶️");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	performAction(2);
            }
        });
        
        // * * BOTON [☀️]
        JButton fillButton = new JButton("☀️");
        fillButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e){
	        	performAction(3);
        	}
        });
        
        // * AGRUPAR BOTONES BOTONERA
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 3, 5, 5));
        actionPanel.add(removeButton);
        actionPanel.add(saveButton);
        actionPanel.add(fillButton);
        bottomPanel.add(actionPanel);
        
        this.frame.add(bottomPanel, BorderLayout.SOUTH);
        this.frame.setVisible(true);
    }
	
	public Integer listenAction() {
		Integer action = this.pendingAction;
		this.pendingAction = 0;
		return action;
	}
	
	// MODIFIERS
	public void addTerminalLine(String line) {
		this.listModelTerminal.addElement(line);
	}
	
	public void addBoardLine(String line) {
		this.listModelLive.addElement(line);
	}
	
	
	
	// FRAME IMPLEMENTS
	
	public void show() {
		this.frame.setVisible(true);
	}
	
	public DefaultListModel<String> getListModelTerminal() {
		return listModelTerminal;
	}
	public DefaultListModel<String> getListModelLive() {
		return listModelLive;
	}
	public void hide() {
		this.frame.setVisible(false);
	}
	
	public void close() {
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	}
}
