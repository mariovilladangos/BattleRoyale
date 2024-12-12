package com.utad.poo.battleroyale.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.utad.poo.battleroyale.general.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private List<String> tLines = new ArrayList();
	
	private DefaultListModel<String> listModelBoard;
	private List<String> bLines = new ArrayList();
	
	private DefaultListModel<String> listModelStats;
	private List<String> sLines = new ArrayList();
	
	private JList<String> terminalList;
	private JList<String> boardList;
	private JList<String> statsList;
	//private JScrollBar verticalBar;
	
	private JScrollPane terminal;
	private JScrollPane board;
	private JScrollPane stats;
	
	private JPanel sidePanel;
	
	private Integer pendingAction = 0;
	private Integer showStats = 0;
	
	public ButtonListener button1Listener = new ButtonListener();
	public ButtonListener button2Listener = new ButtonListener();
	public ButtonListener button3Listener = new ButtonListener();
	//public StatsButtonListener statsListener = new StatsButtonListener();
	
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
	/*
	public class StatsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showStats = 1;
		}
	}
	*/

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
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        // LISTA PERSONAJES
        this.listModelTerminal = new DefaultListModel<>();
        this.terminalList = new JList<>(this.listModelTerminal);
        this.terminalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.terminal = new JScrollPane(this.terminalList);
        this.terminal.setBorder(BorderFactory.createTitledBorder("Eventos"));
        centerPanel.add(this.terminal, BorderLayout.CENTER);
        
        this.listModelBoard = new DefaultListModel<>();
        this.boardList = new JList<>(this.listModelBoard);
        this.boardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.boardList.setFixedCellHeight(15);
        this.boardList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the index of the clicked item
                int index = boardList.locationToIndex(e.getPoint());

                // Check if the index is valid
                if (index != -1) {
                    // Get the clicked item
                    String item = boardList.getModel().getElementAt(index);
                    System.out.println("Clicked on: " + item);
                }
            }
        });
        
        this.board = new JScrollPane(this.boardList);
        this.board.setBorder(BorderFactory.createTitledBorder("Vivos (" + this.nPlayers + "/" + this.nPlayers + ")"));
        
        this.listModelStats = new DefaultListModel<>();
        this.statsList = new JList<>(this.listModelStats);
        this.statsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.stats = new JScrollPane(this.statsList);
        this.stats.setBorder(BorderFactory.createTitledBorder("Stats"));
        //centerPanel.add(this.stats, BorderLayout.EAST);
        
        sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.add(this.board, BorderLayout.CENTER);
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

        /*
        // * * BOTON [Show Stats]
        JButton statsButton = new JButton("Show stats");
        statsButton.addActionListener(this.statsListener);
        */
        
        // * AGRUPAR BOTONES BOTONERA
        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(new EmptyBorder(0, 6, 5, 6));
        actionPanel.setLayout(new GridLayout(1, 3, 5, 5));
        actionPanel.add(removeButton);
        actionPanel.add(saveButton);
        actionPanel.add(fillButton);
        
        
        //bottomPanel.add(statsButton, BorderLayout.EAST);
        bottomPanel.add(actionPanel, BorderLayout.CENTER);
        
        this.frame.add(actionPanel, BorderLayout.SOUTH);
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
	
	// MODIFIERS
	public void addTerminalLine(String line) {
		this.tLines.add(line);
	}
	public void printTerminalLines() {
		this.listModelTerminal.addAll(this.tLines);
		this.tLines.clear();
	}
	
	public void addBoardLine(String line) {
		this.bLines.add(line);
	}
	public void printBoardLines() {
		this.listModelBoard.clear();
		this.listModelBoard.addAll(this.bLines);
		this.bLines.clear();
	}
	
	public void addStatsLine(String line) {
		this.sLines.add(line);
	}
	public void printStatsLines() {
		this.listModelStats.clear();
		this.listModelStats.addAll(this.sLines);
		this.sLines.clear();
	}
	
	public void printAllLines() {
		this.printTerminalLines();
		this.printBoardLines();
		this.printStatsLines();
	}
	public void clearStatsLine() {
		this.listModelStats.clear();
	}
	
	public static void scrollToBottom(JScrollPane scrollPane) {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMaximum());
        });
    }
	
	// GETTERS
	public JScrollPane getTerminal() {
		return terminal;
	}
	public JScrollPane getBoard() {
		return board;
	}
	public JScrollPane getStats() {
		return stats;
	}
	public DefaultListModel<String> getListModelTerminal() {
		return listModelTerminal;
	}
	public DefaultListModel<String> getListModelBoard() {
		return listModelBoard;
	}
	
	
	// FRAME IMPLEMENTS
	
	public void show() {
		this.frame.setVisible(true);
	}
	
	public void hide() {
		this.frame.setVisible(false);
	}
	
	public void close() {
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	}
}
