package com.utad.poo.battleroyale.ui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterMenu {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Menú de Personajes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Panel para la lista de personajes guardados
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> characterList = new JList<>(listModel);
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(characterList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Personajes Guardados"));
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para agregar un nuevo personaje
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Campo de texto para el nombre del personaje
        JPanel namePanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameField, BorderLayout.CENTER);
        bottomPanel.add(namePanel);

        // Grupo de botones para clase y arma
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 5, 5));

        ButtonGroup classGroup = new ButtonGroup();
        JRadioButton warriorButton = new JRadioButton("Guerrero");
        JRadioButton healerButton = new JRadioButton("Sanador");
        JRadioButton prisonerButton = new JRadioButton("Prisionero");
        classGroup.add(warriorButton);
        classGroup.add(healerButton);
        classGroup.add(prisonerButton);

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

        // Botón para guardar el personaje
        JButton saveButton = new JButton("+");
        JButton removeButton = new JButton("-");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String name = nameField.getText().trim();
                String characterClass = null;
                String weapon = null;

                if (warriorButton.isSelected()) {
                    characterClass = "Guerrero";
                } else if (healerButton.isSelected()) {
                    characterClass = "Sanador";
                } else if (prisonerButton.isSelected()) {
                    characterClass = "Prisionero";
                }

                if (swordButton.isSelected()) {
                    weapon = "Espada";
                } else if (spearButton.isSelected()) {
                    weapon = "Lanza";
                } else if (claymoreButton.isSelected()) {
                    weapon = "Claymore";
                }

                if (!name.isEmpty() && characterClass != null && weapon != null) {
                    listModel.addElement(name + " - " + characterClass + " - " + weapon);
                    nameField.setText("");
                    classGroup.clearSelection();
                    weaponGroup.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 2, 5, 5));
        actionPanel.add(saveButton);
        actionPanel.add(removeButton);
        bottomPanel.add(actionPanel);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}