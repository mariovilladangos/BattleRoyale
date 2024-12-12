package com.utad.poo.battleroyale.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.utad.poo.battleroyale.general.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameMenu extends MenusBasic{
	
	public static final Integer FWIDTH = 1000;
	public static final Integer FHEIGHT = 600;
	private Integer nPlayers;
	
	private JFrame frame = new JFrame("Battle Royale");
	private DefaultListModel<String> listModelTerminal;
	private DefaultListModel<String> listModelLive;
	private DefaultListModel<String> listModelStats;
	
	private JList<String> terminalList;
	private JList<String> liveList;
	private JList<String> statsList;
	//private JScrollBar verticalBar;
	
	private JScrollPane terminal;
	private JScrollPane live;
	private JScrollPane stats;
	
	private JPanel sidePanel;
	
	private Integer pendingAction = 0;
	private Integer showStats = 0;
	
	public ButtonListener button1Listener = new ButtonListener();
	public ButtonListener button2Listener = new ButtonListener();
	public ButtonListener button3Listener = new ButtonListener();
	public StatsButtonListener statsListener = new StatsButtonListener();
	
	public GameMenu() {
		this(CharacterMenu.NPLAYERS);
	}
	public GameMenu(Integer nPlayers) {
		this.nPlayers = nPlayers;
		this.visualGameWindow();
	}
	
	
	public class ButtonListener implements ActionListener {
		
		public Integer action = 0;
		
		@Override
        public void actionPerformed(ActionEvent e) {
			if (this == button1Listener) {
				action = 1;
			}
			else if (this == button2Listener) {
				action = 2;
			}
			else if (this == button3Listener) {
				action = 3;
			}
			
			if (pendingAction == 0) pendingAction = action;
			action = 0;
        }
	}
	
	public class StatsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showStats = 1;
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
        this.terminalList = new JList<>(this.listModelTerminal);
        this.terminalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.terminal = new JScrollPane(this.terminalList);
        this.terminal.setBorder(BorderFactory.createTitledBorder("Eventos"));
        centerPanel.add(this.terminal, BorderLayout.CENTER);
        //this.verticalBar = this.terminal.getVerticalScrollBar();
		/*verticalBar.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if (pendingAction == 0) {
					Adjustable adjustable = e.getAdjustable();
				    adjustable.setValue(adjustable.getMaximum());
				}
			}
		});*/
        
        this.listModelLive = new DefaultListModel<>();
        this.liveList = new JList<>(this.listModelLive);
        this.liveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.live = new JScrollPane(this.liveList);
        this.live.setBorder(BorderFactory.createTitledBorder("Vivos (" + this.nPlayers + "/" + this.nPlayers + ")"));
        //centerPanel.add(this.live, BorderLayout.EAST);
        this.liveList.setVisibleRowCount(100000);
        
        this.listModelStats = new DefaultListModel<>();
        this.statsList = new JList<>(this.listModelStats);
        this.statsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.stats = new JScrollPane(this.statsList);
        this.stats.setBorder(BorderFactory.createTitledBorder("Stats"));
        //centerPanel.add(this.stats, BorderLayout.EAST);
        
        sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.add(this.live, BorderLayout.CENTER);
        sidePanel.add(this.stats, BorderLayout.SOUTH);
        sidePanel.setBorder(new EmptyBorder(0, 4, 0, 0));
        centerPanel.add(sidePanel, BorderLayout.EAST);
        
        this.frame.add(centerPanel, BorderLayout.CENTER);

        
        // PANEL ABAJO
        JPanel bottomPanel = new JPanel();
        //bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // * BOTONERA
        // * * BOTON  [▶️]
        JButton removeButton = new JButton("▶️");
        removeButton.addActionListener(this.button1Listener);
        
        // * * BOTON [▶️▶️]
        JButton saveButton = new JButton("▶️▶️");
        saveButton.addActionListener(this.button2Listener);
        
        // * * BOTON [☀️]
        JButton fillButton = new JButton("☀️");
        fillButton.addActionListener(this.button3Listener);

        // * * BOTON [Show Stats]
        JButton statsButton = new JButton("Show stats");
        statsButton.addActionListener(this.statsListener);
        
        // * AGRUPAR BOTONES BOTONERA
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 3, 5, 5));
        actionPanel.add(removeButton);
        actionPanel.add(saveButton);
        actionPanel.add(fillButton);
        
        
        bottomPanel.add(statsButton, BorderLayout.EAST);
        bottomPanel.add(actionPanel, BorderLayout.WEST);
        
        this.frame.add(bottomPanel, BorderLayout.SOUTH);
        this.frame.setVisible(true);
    }
	
	public Integer getPendingAction() {
		return this.pendingAction;
	}
	public void setPendingAction(Integer n) {
		this.pendingAction = n;
	}
	
	public Integer getShowStats() {
		return this.showStats;
	}
	public void setShowStats(Integer n) {
		this.showStats = n;
	}
	
	public void refresh() {
		this.terminalList.revalidate();
		this.terminalList.repaint();
		
		this.liveList.revalidate();
		this.liveList.repaint();
		
		this.statsList.revalidate();
		this.statsList.repaint();
	}
	
	public static void tryWaitSeconds(Integer time) {
		try {
			TimeUnit.MICROSECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// MODIFIERS
	public void addTerminalLine(String line) {
		this.listModelTerminal.addElement(line);
		this.terminalList.revalidate();
		this.terminalList.repaint();
	}
	
	public void addBoardLine(String line) {
		this.listModelLive.addElement(line);
		this.liveList.revalidate();
		this.liveList.repaint();
		tryWaitSeconds(100);
	}
	
	public void clearStatsLine() {
		this.listModelStats.clear();
	}
	
	public void addStatsLine(String line) {
		this.listModelStats.addElement(line);
		this.statsList.revalidate();
		this.statsList.repaint();
		tryWaitSeconds(100);
	}
	
	public void setLiveVisibility(Boolean n) {
		live.setVisible(false);
		listModelLive.addElement("a");
	}
	
	public void scrollDown() {
		//this.verticalBar.setAlignmentY(this.verticalBar.BOTTOM_ALIGNMENT);
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
