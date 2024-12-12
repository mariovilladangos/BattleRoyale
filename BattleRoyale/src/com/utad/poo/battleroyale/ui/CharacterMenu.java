package com.utad.poo.battleroyale.ui;

import com.utad.poo.battleroyale.general.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class CharacterMenu extends MenusBasic {
	
	public static final Integer FWIDTH = 400;
	public static final Integer FHEIGHT = 600;
	public static final Integer NPLAYERS = 10;
	
	private List<Player> players = new ArrayList();
	private List<Player> botPlayers = new ArrayList();
	private JFrame frame = new JFrame("Menú de Personajes");
	private Boolean isReady = false;
	
	public CharacterMenu() {
		this.visualMenuWindow();
	}
	
    public List<Player> getPlayers() {
		return this.players;
	}
	public List<Player> getBotPlayers() {
		return this.botPlayers;
	}
	public boolean isLobbyReady() {
		return isReady;
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
        
        // PERSONAJES LISTOS
        JLabel lobbyFill = new JLabel("0/" + NPLAYERS);
        lobbyFill.setBorder(new EmptyBorder(0, 0, 0, 10));
        lobbyFill.setHorizontalAlignment(JLabel.RIGHT);
        this.frame.add(lobbyFill, BorderLayout.NORTH);
        
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

        // * NOMBRE
        JPanel namePanel = new JPanel(new BorderLayout());
        
        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBorder(new EmptyBorder(0, 0, 0, 6));
        
        JTextField nameField = new JTextField();
        		
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameField, BorderLayout.CENTER);
        bottomPanel.add(namePanel);

        // * CLASE Y ARMA
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 5, 5));
        
        // * * GRUPO CLASES
        ButtonGroup classGroup = new ButtonGroup();
        JRadioButton warriorButton = new JRadioButton("Guerrero");
        JRadioButton healerButton = new JRadioButton("Sanador");
        JRadioButton prisonerButton = new JRadioButton("Prisionero");
        classGroup.add(warriorButton);
        classGroup.add(healerButton);
        classGroup.add(prisonerButton);
        
        // * * GRUPO ARMAS
        ButtonGroup weaponGroup = new ButtonGroup();
        JRadioButton swordButton = new JRadioButton("Espada");
        JRadioButton spearButton = new JRadioButton("Lanza");
        JRadioButton claymoreButton = new JRadioButton("Claymore");
        weaponGroup.add(swordButton);
        weaponGroup.add(spearButton);
        weaponGroup.add(claymoreButton);
        
        buttonPanel.add(warriorButton);
        buttonPanel.add(healerButton);
        buttonPanel.add(prisonerButton);
        buttonPanel.add(swordButton);
        buttonPanel.add(spearButton);
        buttonPanel.add(claymoreButton);
        bottomPanel.add(buttonPanel);
        
        // * BOTONERA
        // * * BOTON  [-]
        JButton removeButton = new JButton("-");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (isReady == false) {
	                if (listModel.size() > 0) {
	                	if (players.size() > 0) {
		                	String line = listModel.get(listModel.size() - 1);
		                	Player player = players.getLast();
		                	String name = player.getName() + " (" + player.getClassType() + " : " + player.getWeapon().getWeaponType() + ")";
	                		if (name.equals(line)) players.remove(player);
	                		else botPlayers.remove(botPlayers.getLast());
	                	}
	                	else if (botPlayers.size() > 0) {
	                		botPlayers.remove(botPlayers.getLast());
	                	}
	                	else {
	                		JOptionPane.showMessageDialog(frame, "No hay personajes que eliminar", "Error", JOptionPane.ERROR_MESSAGE);
	                	}
	                	
	                	listModel.remove(listModel.size() - 1);
	                    lobbyFill.setText(listModel.size() + "/" + NPLAYERS);
	                } else {
	                    JOptionPane.showMessageDialog(frame, "No hay personajes que eliminar", "Error", JOptionPane.ERROR_MESSAGE);
	                }
            	}
            }
        });
        
        // * * BOTON [+]
        JButton saveButton = new JButton("+");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String name = nameField.getText().trim();
                String characterClass = null;
                Weapon weapon = null;

                if (warriorButton.isSelected()) {
                    characterClass = "Guerrero";
                } else if (healerButton.isSelected()) {
                    characterClass = "Sanador";
                } else if (prisonerButton.isSelected()) {
                    characterClass = "Prisionero";
                }

                if (swordButton.isSelected()) {
                	weapon = new Sword();
                } else if (spearButton.isSelected()) {
                	weapon = new Spear();
                } else if (claymoreButton.isSelected()) {
                	weapon = new Claymore();
                }
                
                if (listModel.size() < NPLAYERS) {
	                if (!name.isEmpty() && characterClass != null && weapon != null) {
		                name = name.replaceFirst("" + name.charAt(0), "" + name.toUpperCase().charAt(0));
		                
	                	Boolean validName = true;
	                	for(Player player: players) {
	                		if (name.toUpperCase().equals(player.getName().toUpperCase())) {
	                			validName = false;
	                			break;
	                		}
	                	}
	                	
	                	if (validName) {
		                	// Create player
		                	Player player;
		        			if (characterClass.equals("Warrior")) player = new Warrior(name, weapon);
		        			else if (characterClass.equals("Healer")) player = new Healer(name, weapon);
		        			else player = new Prisoner(name, weapon);
		        			
		        			players.add(player);
		                	
		        			// Update UI
		                    listModel.addElement(name + " (" + characterClass + " : " + weapon.getWeaponType() + ")");
		                    nameField.setText("");
		                    classGroup.clearSelection();
		                    weaponGroup.clearSelection();
		                    
		                    lobbyFill.setText(listModel.size() + "/" + NPLAYERS);
	                	}
	                	else JOptionPane.showMessageDialog(frame, "Por favor, no presente nombres repetidos", "Error", JOptionPane.ERROR_MESSAGE);
		                    
	                } else JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // * * BOTON [fill]
        JButton fillButton = new JButton("fill");
        fillButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e){
	        	Integer playersLeft = NPLAYERS - listModel.size();
				List<Player> bots = Bots.getBots(players, playersLeft);
				for (Player bot: bots) {
					botPlayers.add(bot);
					listModel.addElement(bot.getName() + " (" + bot.getClassType() + " : " + bot.getWeaponType() + ")");
				}
				lobbyFill.setText(listModel.size() + "/" + NPLAYERS);
        	}
        });
        
     // * * BOTON [play]
        JButton playButton = new JButton("play");
        playButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e){
	        	if(players.size() + botPlayers.size() >= NPLAYERS) {
	        		isReady = true;
	        	}
				else JOptionPane.showMessageDialog(frame, "El lobby no está lleno.", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        });
        
        // * AGRUPAR BOTONES BOTONERA
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 4, 5, 5));
        actionPanel.add(removeButton);
        actionPanel.add(saveButton);
        actionPanel.add(fillButton);
        actionPanel.add(playButton);
        bottomPanel.add(actionPanel);
        
        this.frame.add(bottomPanel, BorderLayout.SOUTH);
        this.frame.setVisible(true);
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