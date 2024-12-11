package com.utad.poo.battleroyale.ui;
import com.utad.poo.battleroyale.general.Bots;
import com.utad.poo.battleroyale.general.Claymore;
import com.utad.poo.battleroyale.general.Healer;
import com.utad.poo.battleroyale.general.Player;
import com.utad.poo.battleroyale.general.Prisoner;
import com.utad.poo.battleroyale.general.Spear;
import com.utad.poo.battleroyale.general.Sword;
import com.utad.poo.battleroyale.general.Warrior;
import com.utad.poo.battleroyale.general.Weapon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CharacterMenu {
	
	public static final Integer FWIDTH = 400;
	public static final Integer FHEIGHT = 600;
	public static final Integer NPLAYERS = 10;
	
	private List<Player> players = new ArrayList();
	private List<Player> botPlayers = new ArrayList();
	
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
		if (this.players.size() + this.botPlayers.size() >= NPLAYERS) return true;
		else return false;
	}



	public void visualMenuWindow(){
    	// VARIABLES
    	
    	
        // VENTANA PRINCIPAL
        JFrame frame = new JFrame("Menú de Personajes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(FWIDTH, FHEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer sWidth = screenSize.width;
        Integer sHeight = screenSize.height;
        frame.setLocation(((sWidth / 2) - (FWIDTH / 2)), ((sHeight / 2) - (FHEIGHT / 2)));
        frame.setLayout(new BorderLayout());
        
        // PERSONAJES LISTOS
        JLabel lobbyFill = new JLabel("0/" + NPLAYERS);
        lobbyFill.setBorder(new EmptyBorder(0, 0, 0, 10));
        lobbyFill.setHorizontalAlignment(JLabel.RIGHT);
        frame.add(lobbyFill, BorderLayout.NORTH);
        
        // LISTA PERSONAJES
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> characterList = new JList<>(listModel);
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(characterList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Personajes Guardados"));
        frame.add(scrollPane, BorderLayout.CENTER);

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
            	if (listModel.size() < NPLAYERS) {
	                if (listModel.size() > 0) {
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
	                    
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
                }
            }
        });
        
        // * * BOTON [fill]
        JButton fillButton = new JButton("fill");
        fillButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e){
	        	Integer playersLeft = NPLAYERS - listModel.size();
				List<Player> bots = Bots.getBots(playersLeft);
				for (Player bot: bots) {
					botPlayers.add(bot);
					listModel.addElement(bot.getName() + " (" + bot.getClassType() + " : " + bot.getWeaponType() + ")");
				}
				lobbyFill.setText(listModel.size() + "/" + NPLAYERS);
        	}
        });
        
        // * AGRUPAR BOTONES BOTONERA
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 3, 5, 5));
        actionPanel.add(removeButton);
        actionPanel.add(saveButton);
        actionPanel.add(fillButton);
        bottomPanel.add(actionPanel);
        
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        

        if (listModel.size() >= NPLAYERS) {/* Empieza */};
    }
}