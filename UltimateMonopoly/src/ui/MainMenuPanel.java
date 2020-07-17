package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import domain.MonopolyGameController;

public class MainMenuPanel extends JPanel implements ActionListener {

	private final String[] numberOfPlayersArr = { "2", "3", "4" };
	private final String[] numberOfDiceArr = {"3", "4"};
	private final String[] gameTypeArr = { "Player vs Player", "Player vs AI" };
	private final String[] boardTypeArr = {"Ultimate Monopoly"};
	private final String[] startingMoneyArr = {"1600", "3200", "4800", "6400"};
	private final String[] goSquareEarnArr = {"100", "200", "300", "400"};
	private final String[] numberOfHouseArr = {"0", "41", "81", "121", "Inf" };
	private final String[] numberOfHotelArr = {"0", "16", "31", "46", "Inf" };
	
	private MonopolyGameController controller;
	private MonopolyFrame parent;

	private ArrayList<String> usernames = new ArrayList<String>();
	private Image logoImage;
	private Image backgroundImage;
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton quitGameButton;
	private JButton continueButton;
	private JButton mainMenuButton;
	private JComboBox<String> numberOfPlayersList;
	private JComboBox<String> numberOfDiceList;
	private JComboBox<String> startingMoneyList;
	private JComboBox<String> goSquareList;
	private JComboBox<String> numberOfHouseList;
	private JComboBox<String> numberOfHotelList;
	private JComboBox<String> boardList;
	private JComboBox<String> gameTypeList;
	private JRadioButton defaultButton;
	private JRadioButton customButton;
	private ButtonGroup buttonGroup;
	private JCheckBox communityChestButton;
	private JCheckBox luckCardsButton;
	private JCheckBox goToJailButton;
	private JCheckBox payTaxButton;
	private JCheckBox auctionButton;
	private JCheckBox mortgageButton;
	private JCheckBox tradeButton;
	private JCheckBox doubleTheRentButton;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;
	private JLabel label11;
	private JTextField inputUsernameTextField = new JTextField();
	private DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
	
	private final Font defaultFont = new Font("Tahoma", Font.BOLD, 12);
	private final Font labelFont = new Font("Tahoma", Font.BOLD, 15);

	private int panelWidth;
	private int panelHeight;
	private int numberOfPlayers = -1;
	private int currentPlayerInputIndex = 1;

	private boolean settingsPanel = false;
	private boolean invalidFileName = false;
	private boolean numberOfPlayersSet = false;
	private boolean finalized = false;

	public MainMenuPanel(int panelWidth, int panelHeight) {
		setLayout(null);
		setSize(panelWidth, panelHeight);
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		setBackground(Color.ORANGE);
		setVisible(true);
		initButtons();
		try {
			logoImage = ImageIO.read(new File("gui_images/logo.png"));
			backgroundImage = ImageIO.read(new File("gui_images/background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(logoImage, getWidth() / 2 - logoImage.getWidth(this) / 2, getHeight() / 10, this);
		if (settingsPanel) {

			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.PLAIN, 25));

			if (!numberOfPlayersSet) {
				g.drawString("Welcome to Monopoly New Game Setup!", panelWidth / 2 - 225, panelHeight / 2 - 125);
			} else {
				g.setFont(new Font("Tahoma", Font.PLAIN, 20));
				g.drawString("Naming Rules:", panelWidth / 2 - 225, panelHeight / 2 + 100);
				g.drawString("_Player name must be unique.", panelWidth / 2 - 215, panelHeight / 2 + 125);
				g.drawString("_Player name must not be left empty.", panelWidth / 2 - 215, panelHeight / 2 + 150);
				g.drawString("_Player name must have at most 10 characters.", panelWidth / 2 - 215, panelHeight / 2 + 175);
				g.drawString("Number Of Players Set To " + numberOfPlayers + "!", panelWidth / 2 + 300,
						panelHeight / 2 - 200);
				for (int i = 0; i < numberOfPlayers; i++) {
					g.drawString(usernames.get(i), panelWidth / 2 + 300, panelHeight / 2 - 200 + (1 + i) * 30);
				}

				if (!finalized) {
					g.drawString("Please enter name for Player " + currentPlayerInputIndex + ":", panelWidth / 2 - 150,
							panelHeight / 2 - 45);
				} else {
					g.drawString("Please enter name for Game:", panelWidth / 2 - 150, panelHeight / 2 - 45);
					if (invalidFileName) {
						g.setColor(Color.red);
						g.drawString("Invalid Game Name!", panelWidth / 2 - 75, panelHeight / 2 + 80);
						g.setColor(new Color(59, 89, 182));
					}

				}
			}
		} else {

		}

	}

	public void initAncestor() {
		parent = (MonopolyFrame) SwingUtilities.getWindowAncestor(this);
		controller = parent.controller;
	}

	public void initButtons() {

		int buttonWidth = panelWidth / 6;
		int buttonHeight = panelHeight / 18;
				
		newGameButton = new JButton("New Game");

		newGameButton.setBounds(panelWidth / 2 - buttonWidth / 2, panelHeight / 2 - buttonHeight / 2 - 75, buttonWidth,
				buttonHeight);
		newGameButton.setActionCommand("newGame");
		newGameButton.addActionListener(this);
		newGameButton.setBackground(new Color(59, 89, 182));
		newGameButton.setForeground(Color.WHITE);
		newGameButton.setFocusPainted(false);
		newGameButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		newGameButton.setOpaque(true);
		newGameButton.setBorderPainted(false);
		loadGameButton = new JButton("Load Game");
		loadGameButton.setBounds(panelWidth / 2 - buttonWidth / 2, panelHeight / 2 - buttonHeight / 2 + 25, buttonWidth,
				buttonHeight);
		loadGameButton.setActionCommand("loadGame");
		loadGameButton.addActionListener(this);
		loadGameButton.setBackground(new Color(59, 89, 182));
		loadGameButton.setForeground(Color.WHITE);
		loadGameButton.setFocusPainted(false);
		loadGameButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		loadGameButton.setOpaque(true);
		loadGameButton.setBorderPainted(false);
		quitGameButton = new JButton("Quit Game");
		quitGameButton.setBounds(panelWidth / 2 - buttonWidth / 2, panelHeight / 2 - buttonHeight / 2 + 125,
				buttonWidth, buttonHeight);
		quitGameButton.setActionCommand("quitGame");
		quitGameButton.addActionListener(this);
		quitGameButton.setBackground(new Color(59, 89, 182));
		quitGameButton.setForeground(Color.WHITE);
		quitGameButton.setFocusPainted(false);
		quitGameButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		quitGameButton.setOpaque(true);
		quitGameButton.setBorderPainted(false);
		add(newGameButton);
		add(loadGameButton);
		add(quitGameButton);

	}

	public void createNewGamePanel() {
		removeAll();
		int buttonWidth = 150;
		int buttonHeight = 30;

		int centerWidth = panelWidth / 2;
		int centerHeight = panelHeight / 2;
		
		continueButton = new JButton("Continue!");
		continueButton.setBounds(panelWidth / 2 - buttonWidth / 2, panelHeight / 2 - buttonHeight / 2 + 350, buttonWidth,
				buttonHeight);
		continueButton.setActionCommand("setNumberOfPlayers");
		continueButton.addActionListener(this);
		continueButton.setBackground(new Color(59, 89, 182));
		continueButton.setForeground(Color.WHITE);
		continueButton.setFocusPainted(false);
		continueButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		continueButton.setOpaque(true);
		continueButton.setBorderPainted(false);
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setBounds((int) (getWidth() - buttonWidth), 0, buttonWidth, buttonHeight);
		mainMenuButton.setActionCommand("mainMenu");
		mainMenuButton.addActionListener(this);
		mainMenuButton.setBackground(Color.red);
		mainMenuButton.setForeground(Color.WHITE);
		mainMenuButton.setFocusPainted(false);
		mainMenuButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		mainMenuButton.setOpaque(true);
		mainMenuButton.setBorderPainted(false);
		
		numberOfPlayersList = new JComboBox<String>(numberOfPlayersArr);
		numberOfPlayersList.setSize(30, 20);
		numberOfPlayersList.setLocation(centerWidth - 150, centerHeight - 50);
		numberOfPlayersList.setFont(defaultFont);
		numberOfPlayersList.setRenderer(listRenderer);
		label1 = new JLabel("Number of Players ");
		label1.setFont(labelFont);
		label1.setSize(150, 20);
		label1.setLocation(centerWidth - 300, centerHeight - 50);
		
		boardList = new JComboBox<String>(boardTypeArr);
		boardList.setSelectedIndex(0);
		boardList.setSize(150,20);
		boardList.setLocation(centerWidth + 215, centerHeight);
		boardList.setFont(defaultFont);
		boardList.setRenderer(listRenderer);
		label2 = new JLabel("Board Type ");
		label2.setFont(labelFont);
		label2.setSize(100, 20);
		label2.setLocation(centerWidth + 120, centerHeight);
		
		numberOfDiceList = new JComboBox<String>(numberOfDiceArr);
		numberOfDiceList.setSelectedIndex(0);
		numberOfDiceList.setSize(30, 20);
		numberOfDiceList.setLocation(centerWidth - 150, centerHeight);
		numberOfDiceList.setFont(defaultFont);
		numberOfDiceList.setRenderer(listRenderer);
		label4 = new JLabel("Number of Dice ");
		label4.setFont(labelFont);
		label4.setSize(150, 20);
		label4.setLocation(centerWidth - 300, centerHeight);
		
		gameTypeList = new JComboBox<String>(gameTypeArr);
		gameTypeList.setSelectedIndex(0);
		gameTypeList.setSize(150,20);
		gameTypeList.setLocation(centerWidth + 215, centerHeight - 50);
		gameTypeList.setFont(defaultFont);
		gameTypeList.setRenderer(listRenderer);
		label3 = new JLabel("Game Type ");
		label3.setFont(labelFont);
		label3.setSize(100, 20);
		label3.setLocation(centerWidth + 120, centerHeight - 50);
		
		startingMoneyList = new JComboBox<String>(startingMoneyArr);
		startingMoneyList.setSelectedIndex(1);
		startingMoneyList.setSize(50, 20); 
		startingMoneyList.setLocation(centerWidth - 175, centerHeight + 50);
		startingMoneyList.setFont(defaultFont);
		startingMoneyList.setRenderer(listRenderer);
		label5 = new JLabel("Starting Money ");
		label5.setFont(labelFont);
		label5.setSize(150, 20);
		label5.setLocation(centerWidth - 300, centerHeight + 50);
		
		goSquareList = new JComboBox<String>(goSquareEarnArr);
		goSquareList.setSelectedIndex(1);
		goSquareList.setSize(40, 20);
		goSquareList.setLocation(centerWidth + 325, centerHeight + 50);
		goSquareList.setFont(defaultFont);
		goSquareList.setRenderer(listRenderer);
		label6 = new JLabel("Reaching GO Square, Earn ");
		label6.setFont(labelFont);
		label6.setSize(250, 20);
		label6.setLocation(centerWidth + 120, centerHeight + 50);
		
		numberOfHouseList = new JComboBox<String>(numberOfHouseArr);
		numberOfHouseList.setSelectedIndex(2);
		numberOfHouseList.setSize(30, 20);
		numberOfHouseList.setLocation(centerWidth - 130, centerHeight + 100);
		numberOfHouseList.setFont(defaultFont);
		numberOfHouseList.setRenderer(listRenderer);
		label7 = new JLabel("Maximum Number of House");
		label7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label7.setSize(170, 20);
		label7.setLocation(centerWidth - 300, centerHeight + 100);
		
		numberOfHotelList = new JComboBox<String>(numberOfHotelArr);
		numberOfHotelList.setSelectedIndex(2);
		numberOfHotelList.setSize(30, 20);
		numberOfHotelList.setLocation(centerWidth + 300, centerHeight + 100);
		numberOfHotelList.setFont(defaultFont);
		numberOfHotelList.setRenderer(listRenderer);
		label8 = new JLabel("Maximum Number of Hotel");
		label8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label8.setSize(170, 20);
		label8.setLocation(centerWidth + 120, centerHeight + 100);
		
		defaultButton = new JRadioButton("Default");
		defaultButton.setSize(100, 20);
		defaultButton.setFont(labelFont);
		defaultButton.setOpaque(false);
		defaultButton.setLocation(centerWidth - 35, centerHeight - 100);
		defaultButton.setActionCommand("optionsLocked");
		defaultButton.addActionListener(this);
		customButton = new JRadioButton("Custom", true);
		customButton.setSize(100, 20);
		customButton.setOpaque(false);
		customButton.setFont(labelFont);
		customButton.setLocation(centerWidth + 65, centerHeight - 100);
		customButton.setActionCommand("optionsUnlocked");
		customButton.addActionListener(this);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(defaultButton);
		buttonGroup.add(customButton);
		label11 = new JLabel("Game");
		label11.setFont(labelFont);
		label11.setSize(50, 20);
		label11.setLocation(centerWidth - 100, centerHeight - 100);
		
		communityChestButton = new JCheckBox("Community Chest", true);
		communityChestButton.setSize(175, 20);
		communityChestButton.setFont(labelFont);
		communityChestButton.setOpaque(false);
		communityChestButton.setLocation(centerWidth - 290, centerHeight + 175);
		luckCardsButton = new JCheckBox("Luck Cards", true);
		luckCardsButton.setSize(175, 20);
		luckCardsButton.setFont(labelFont);
		luckCardsButton.setOpaque(false);
		luckCardsButton.setLocation(centerWidth - 290, centerHeight + 200);
		goToJailButton = new JCheckBox("Go to Jail", true);
		goToJailButton.setSize(175, 20);
		goToJailButton.setFont(labelFont);
		goToJailButton.setOpaque(false);
		goToJailButton.setLocation(centerWidth - 290, centerHeight + 225);
		payTaxButton = new JCheckBox("Pay Tax", true);
		payTaxButton.setSize(175, 20);
		payTaxButton.setFont(labelFont);
		payTaxButton.setOpaque(false);
		payTaxButton.setLocation(centerWidth - 290, centerHeight + 250);
		label9 = new JLabel("Enable Special Squares: ");
		label9.setFont(labelFont);
		label9.setSize(200, 20);
		label9.setLocation(centerWidth - 300, centerHeight + 150);
		
		auctionButton = new JCheckBox("Auction", true);
		auctionButton.setSize(150, 20);
		auctionButton.setFont(labelFont);
		auctionButton.setOpaque(false);
		auctionButton.setLocation(centerWidth + 130, centerHeight + 175);
		mortgageButton = new JCheckBox("Mortgage", true);
		mortgageButton.setSize(150, 20);
		mortgageButton.setFont(labelFont);
		mortgageButton.setOpaque(false);
		mortgageButton.setLocation(centerWidth + 130, centerHeight + 200);
		tradeButton = new JCheckBox("Trade", true);
		tradeButton.setSize(200, 20);
		tradeButton.setFont(labelFont);
		tradeButton.setOpaque(false);
		tradeButton.setLocation(centerWidth + 130, centerHeight + 225);
		doubleTheRentButton = new JCheckBox("Double Rent for Holding Same Color Regions", true);
		doubleTheRentButton.setSize(400, 20);
		doubleTheRentButton.setFont(labelFont);
		doubleTheRentButton.setOpaque(false);
		doubleTheRentButton.setLocation(centerWidth + 130, centerHeight + 250);
		label10 = new JLabel("Enable Features: ");
		label10.setFont(labelFont);
		label10.setSize(200, 20);
		label10.setLocation(centerWidth + 120, centerHeight + 150);
		
		add(defaultButton); add(customButton); add(label11);
		add(numberOfPlayersList); add(label1); 
		add(gameTypeList); add(label3); 
		add(numberOfDiceList); add(label4); 
		add(boardList); add(label2);
		add(startingMoneyList); add(label5); 
		add(goSquareList); add(label6); 
		add(numberOfHouseList); add(label7); 
		add(numberOfHotelList); add(label8); 
		add(communityChestButton); add(luckCardsButton); add(goToJailButton); add(payTaxButton);
		add(label9);
		add(auctionButton); add(mortgageButton); add(tradeButton); add(doubleTheRentButton);
		add(label10);
		add(continueButton);
		add(mainMenuButton);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "newGame":
			settingsPanel = true;
			createNewGamePanel();
			break;
		case "loadGame":
			loadGameSequence();
			break;
		case "quitGame":
			controller.quitGame();
			break;
		case "optionsLocked":
			lockOptions();
			break;
		case "optionsUnlocked":
			unlockOptions();
			break;
		case "setNumberOfPlayers":
			clearNewGamePanel();
			numberOfPlayersSet = true;
			numberOfPlayers = (Integer.parseInt((String) numberOfPlayersList.getSelectedItem()));
			initUsernameInput();
			repaint();
			break;
		case "continueNameInput":
			if (nameCheck(inputUsernameTextField.getText())) {
				usernames.set(currentPlayerInputIndex - 1, inputUsernameTextField.getText());
				inputUsernameTextField.setText("");
				currentPlayerInputIndex++;
				if (currentPlayerInputIndex == numberOfPlayers + 1) {
					finalized = true;
					remove(continueButton);
					JButton startButton = new JButton("Start!");
					int buttonWidth = 150;
					int buttonHeight = 30;
					startButton.setBounds(panelWidth / 2 - buttonWidth / 2,
							panelHeight / 2 - buttonHeight / 2 + buttonHeight, buttonWidth, buttonHeight);
					startButton.setActionCommand("start");
					startButton.addActionListener(this);
					startButton.setBackground(new Color(59, 89, 182));
					startButton.setForeground(Color.WHITE);
					startButton.setFocusPainted(false);
					startButton.setFont(new Font("Tahoma", Font.BOLD, 12));
					startButton.setOpaque(true);
					startButton.setBorderPainted(false);
					add(startButton);
				}
			}
			inputUsernameTextField.requestFocus();
			repaint();
			break;
		case "start":
			if (tryToStartGame()) {
				parent.loadGame("InitialGameState.null");
				parent.switchToGamePanel(usernames, inputUsernameTextField.getText());
			} else {
				invalidFileName = true;
				repaint();
			}
			break;
		case "mainMenu":
			parent.switchToMainMenuPanel();
			controller.reset();
			break;
		}

	}
	
	public void clearNewGamePanel() {
		remove(defaultButton); remove(customButton); remove(label11);
		remove(continueButton);
		remove(numberOfPlayersList); remove(label1);
		remove(boardList); remove(label2);
		remove(gameTypeList); remove(label3);
		remove(numberOfDiceList); remove(label4);
		remove(startingMoneyList); remove(label5);
		remove(goSquareList); remove(label6);
		remove(numberOfHouseList); remove(label7);
		remove(numberOfHotelList); remove(label8);
		remove(communityChestButton); remove(luckCardsButton); remove(goToJailButton); remove(payTaxButton);
		remove(label9);
		remove(auctionButton); remove(mortgageButton); remove(tradeButton); remove(doubleTheRentButton);
		remove(label10);
	}
	
	public void lockOptions() {
		boardList.setSelectedIndex(0); boardList.setEnabled(false);
		numberOfDiceList.setSelectedIndex(0); numberOfDiceList.setEnabled(false);
		startingMoneyList.setSelectedIndex(1); startingMoneyList.setEnabled(false);
		goSquareList.setSelectedIndex(1); goSquareList.setEnabled(false);
		numberOfHouseList.setSelectedIndex(2); numberOfHouseList.setEnabled(false);
		numberOfHotelList.setSelectedIndex(2); numberOfHotelList.setEnabled(false);
		
		communityChestButton.setSelected(true); communityChestButton.setEnabled(false);
		luckCardsButton.setSelected(true); luckCardsButton.setEnabled(false);
		goToJailButton.setSelected(true); goToJailButton.setEnabled(false);
		payTaxButton.setSelected(true); payTaxButton.setEnabled(false);
		
		auctionButton.setSelected(true); auctionButton.setEnabled(false);
		mortgageButton.setSelected(true); mortgageButton.setEnabled(false);
		tradeButton.setSelected(true); tradeButton.setEnabled(false);
		doubleTheRentButton.setSelected(true); doubleTheRentButton.setEnabled(false);
	}
	
	public void unlockOptions() {
		boardList.setEnabled(true); numberOfDiceList.setEnabled(true); startingMoneyList.setEnabled(true);
		goSquareList.setEnabled(true); numberOfHouseList.setEnabled(true); numberOfHotelList.setEnabled(true);
		communityChestButton.setEnabled(true); luckCardsButton.setEnabled(true);
		goToJailButton.setEnabled(true); payTaxButton.setEnabled(true);
		auctionButton.setEnabled(true); mortgageButton.setEnabled(true);
		tradeButton.setEnabled(true); doubleTheRentButton.setEnabled(true);
	}

	public void initUsernames() {
		for (int i = 0; i < numberOfPlayers; i++) {
			usernames.add("waiting for input..");
		}
	}

	public void initUsernameInput() {
		int buttonWidth = 150;
		int buttonHeight = 30;
		initUsernames();
		inputUsernameTextField.setBounds(panelWidth / 2 - buttonWidth / 2, panelHeight / 2 - buttonHeight / 2 - 15,
				buttonWidth, buttonHeight);
		inputUsernameTextField.setBackground(new Color(59, 89, 182));
		inputUsernameTextField.setForeground(Color.WHITE);
		inputUsernameTextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(inputUsernameTextField);

		continueButton = new JButton("Continue!");
		continueButton.setBounds(panelWidth / 2 - buttonWidth / 2, panelHeight / 2 - buttonHeight / 2 + buttonHeight,
				buttonWidth, buttonHeight);
		continueButton.setActionCommand("continueNameInput");
		continueButton.addActionListener(this);
		continueButton.setBackground(new Color(59, 89, 182));
		continueButton.setForeground(Color.WHITE);
		continueButton.setFocusPainted(false);
		continueButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		continueButton.setOpaque(true);
		continueButton.setBorderPainted(false);
		add(continueButton);

		inputUsernameTextField.requestFocus();

	}

	public boolean nameCheck(String name) {
		if (!usernames.contains(name) && name != null && !name.toLowerCase().equals("-") && !name.contains(" ")
				&& name.length() != 0 && name.length() <= 10)
			return true;

		return false;

	}

	public void loadGameSequence() {
		JFileChooser fileChooser = new JFileChooser("./GameStates");
		fileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if (f.isDirectory()) {
					return true;
				} else {
					if (f.getAbsolutePath().toLowerCase().endsWith("null")
							&& !f.getName().equals("InitialGameState.null"))
						return true;
				}

				return false;
			}
		});
		int choice = fileChooser.showOpenDialog(null);

		if (choice == fileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile.getAbsolutePath().toLowerCase().endsWith("null")) {
				parent.loadGame(selectedFile.getName());
				parent.switchToGamePanel(null,
						selectedFile.getName().substring(0, selectedFile.getName().lastIndexOf(".")));
				parent.update("game", "highlightSquare", parent.controller.currentPlayerIndex());
			} else {
				JOptionPane.showMessageDialog(parent, "Please do not try to hack us :(", "Not Cool Bro!",
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	public boolean tryToStartGame() {
		String gamename = inputUsernameTextField.getText();
		if (gamename == null) {
			return false;
		}

		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		boolean hasSpecialChar = p.matcher(gamename).find();

		if (hasSpecialChar || gamename.length() == 0 || gamename.length() > 10) {
			return false;
		}

		return parent.controller.isDuplicateGameName(gamename);
	}
}
