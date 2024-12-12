package com.utad.poo.battleroyale.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.utad.poo.battleroyale.general.Bots;
import com.utad.poo.battleroyale.general.Claymore;
import com.utad.poo.battleroyale.general.Healer;
import com.utad.poo.battleroyale.general.Player;
import com.utad.poo.battleroyale.general.Prisoner;
import com.utad.poo.battleroyale.general.Spear;
import com.utad.poo.battleroyale.general.Sword;
import com.utad.poo.battleroyale.general.Warrior;
import com.utad.poo.battleroyale.general.Weapon;

public class PodiumMenu extends MenusBasic {
	public static final Integer FWIDTH = 400;
	public static final Integer FHEIGHT = 600;
	public static final Integer NPLAYERS = 10;
	
	private List<Player> players = new ArrayList();
	private List<Player> botPlayers = new ArrayList();
	private JFrame frame = new JFrame("Menú de Personajes");
	private Integer option = 0;
	
	public PodiumMenu() {
		this.visualMenuWindow();
	}
	
	public Integer getOption() {
		return option;
	}
	public void setOption(Integer option) {
		 this.option = option;
	}

	public void visualMenuWindow(){
		
        // VENTANA PRINCIPAL
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.frame.setSize(FWIDTH, FHEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer sWidth = screenSize.width;
        Integer sHeight = screenSize.height;
        this.frame.setLocation(((sWidth / 2) - (FWIDTH / 2)), ((sHeight / 2) - (FHEIGHT / 2)));
        this.frame.setLayout(new BorderLayout());
        
        // LISTA PERSONAJES
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> characterList = new JList<>(listModel);
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(characterList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Personajes Guardados"));
        this.frame.add(scrollPane, BorderLayout.CENTER);

        // PANEL ABAJO
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // * BOTONERA
        // * * BOTON  [Close]
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	option = 1;
            }
        });
        
        // * * BOTON [Play again]
        JButton playButton = new JButton("Play Again");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	option = 2;
            }
        });
        
        // * * BOTON [Save]
        JButton saveButton = new JButton("Save Log");
        saveButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e){
	        	option = 3;
        	}
        });
        
        // * AGRUPAR BOTONES BOTONERA
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 4, 5, 5));
        actionPanel.add(closeButton);
        actionPanel.add(playButton);
        actionPanel.add(saveButton);
        bottomPanel.add(actionPanel);
        
        this.frame.add(bottomPanel, BorderLayout.SOUTH);
        this.frame.setVisible(true);
    }
	
	public String saveFileName() {
		return JOptionPane.showInputDialog(frame, "¿Como quieres guardar el archivo?", "Save Log", JOptionPane.PLAIN_MESSAGE);
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