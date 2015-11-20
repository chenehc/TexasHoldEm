package userInterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.sun.glass.events.KeyEvent;

import controller.AIOpponent;
import controller.Game;
import controller.Pot;
import model.Player;


public class TexasHoldEm extends JFrame implements ActionListener  {
	private ImageIcon background;
	private Player player1 = null;
	private Player player2 = null;
	private AIOpponent AIplayer;
	private Game game;
	private Pot pot;
	
	private static TexasHoldEm board;
	private JMenuBar menuBar;
	private JMenu menu, aboutMenu;
	private JMenuItem newGameItem, saveItem, loadItem, exitItem, infoItem, ruleItem;

	private JPanel dealerPanel, communityPanel, btnPanel, playerPanel;
	private JButton betBtn, callBtn, dealBtn, foldBtn;
	private JLabel dealerLabel, communityLabel;
	private JLabel[] playerCards, dealerCards, communityCards;
	private JTextField betField;
	private JScrollPane scroll;
	private Border sunkIn = BorderFactory.createBevelBorder(BevelBorder.LOWERED); 
	
	private boolean dealt = false;
	private int communityCardCount = 0;
	private int communityWidth = 5;
	private JTextArea console;
	private JLabel lblConsole;
	
	//constructor
	public TexasHoldEm(){
		super("Texas Hold Em");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(590,430);
		setLocationRelativeTo(null);
		setBackground(Color.GRAY);
		setResizable(false);
		
		background = new ImageIcon("Cards/b.gif");
		
		player1 = new Player();
		player2 = new Player();

		game = new Game(player1, player2, this);
		pot = game.getPot();
		
		addMenu();
		addPanel();
		
		//add the components onto the frame
		setJMenuBar(menuBar);
		getContentPane().setLayout(null);
		getContentPane().add(dealerLabel);
		getContentPane().add(dealerPanel);
		getContentPane().add(communityLabel);
		getContentPane().add(communityPanel);
		getContentPane().add(btnPanel);
		getContentPane().add(playerPanel);
		getContentPane().add(scroll);
		
		JLabel lblPlayerCards = new JLabel("Player Cards");
		lblPlayerCards.setBounds(386, 193, 79, 14);
		getContentPane().add(lblPlayerCards);
		
		lblConsole = new JLabel("Console");
		lblConsole.setBounds(103, 193, 46, 14);
		getContentPane().add(lblConsole);
		log("");
		trackCommunity();
		
	}
	
	/**
	 * Method to initialize and setup the menu bar along with the menu items within
	 */
	private void addMenu(){
		menuBar = new JMenuBar();
		
		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(menu);
		
		aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(aboutMenu);
		
		newGameItem = new JMenuItem("New Game", KeyEvent.VK_N);
		newGameItem.addActionListener(this);
		menu.add(newGameItem);
		
		menu.addSeparator();
		
		saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		saveItem.addActionListener(this);
		menu.add(saveItem);
		
		loadItem = new JMenuItem("Load", KeyEvent.VK_L);
		loadItem.addActionListener(this);
		menu.add(loadItem);
		
		menu.addSeparator();
		exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		exitItem.addActionListener(this);
		menu.add(exitItem);
		
		infoItem = new JMenuItem("App Info", KeyEvent.VK_I);
		infoItem.addActionListener(this);
		aboutMenu.add(infoItem);
		
		ruleItem = new JMenuItem("Game Rules", KeyEvent.VK_R);
		ruleItem.addActionListener(this);
		aboutMenu.add(ruleItem);
	}
	
	/**
	 * Method that adds the components for player, community and dealer
	 */
	private void addPanel(){
		dealerLabel = new JLabel();
		communityLabel = new JLabel();
		
		//create panel to hold hold buttons
		btnPanel = new JPanel(); 
		btnPanel.setLayout(new GridLayout(1, 5));
		btnPanel.setBorder(sunkIn);
		
		//create buttons for the button panel
		dealBtn = new JButton("Deal");
		dealBtn.addActionListener(this);
		btnPanel.add(dealBtn);
		
		callBtn = new JButton("Call/Check");
		callBtn.addActionListener(this);
		btnPanel.add(callBtn);
		
		foldBtn = new JButton("Fold");
		foldBtn.addActionListener(this);
		btnPanel.add(foldBtn);
		
		betBtn = new JButton("Bet");
		betBtn.addActionListener(this);
		btnPanel.add(betBtn);
		
		betField = new JTextField("0");
		btnPanel.add(betField);
		
		//create a dealer panel to show dealer's card back
		dealerPanel = new JPanel();
		dealerPanel.setLayout(null);
		dealerPanel.setBorder(sunkIn);
		
		dealerPanel.setBounds(5, 30, 161, 107);
		
		dealerLabel.setText("Dealer Cards");
		dealerLabel.setHorizontalAlignment(JLabel.CENTER);
		dealerLabel.setBounds(5, 5, 161, 20);
		
		dealerCards = new JLabel[2];
		
		//create community panel that shows the community cards
		communityPanel = new JPanel();
		communityPanel.setBorder(sunkIn);
		communityPanel.setBounds(181, 30, 395, 107);
		
		communityLabel.setText("Community Cards");
		communityLabel.setHorizontalAlignment(JLabel.CENTER);
		communityLabel.setBounds(166, 5, 395, 20);

		btnPanel.setBounds(5, 152, 571, 30);

		//create a player panel that shows the player's cards
		playerPanel = new JPanel();
		playerPanel.setLayout(null);
		playerPanel.setBorder(sunkIn);
		playerPanel.setBounds(340, 217, 161, 107);
		
//		playerLabel.setText("Player Cards");
//		playerLabel.setHorizontalAlignment(JLabel.CENTER);
//		playerLabel.setBounds(5, btnPanel.getBounds().y + btnPanel.getHeight() + 5, btnPanel.getWidth(), 25);
	
		playerCards = new JLabel[2];

		communityCards = new JLabel[5];
		
		console = new JTextArea();
		console.setEditable(false);
		scroll = new JScrollPane(console);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		scroll.setBounds(10, 217, 234, 107);
		
		
	}
	
	/**
	 * Method to set icons for the player's cards
	 */
	private void createPlayerCards(){
		playerPanel.removeAll();
		ImageIcon playerFirstCardIcon = new ImageIcon(player1.getHand().get(0).getFileName());
		System.out.println(player1.getHand().get(0).getFileName());
		ImageIcon playerSecondCardIcon = new ImageIcon(player1.getHand().get(1).getFileName());
		playerCards[0] = new JLabel();
		playerCards[0].setIcon(playerFirstCardIcon);
		playerCards[1] = new JLabel();
		playerCards[1].setIcon(playerSecondCardIcon);
		playerCards[0].setBounds(5, 5, playerFirstCardIcon.getIconWidth(), playerFirstCardIcon.getIconHeight());
		playerCards[1].setBounds(playerFirstCardIcon.getIconWidth() + 10, 5, playerSecondCardIcon.getIconWidth(), playerSecondCardIcon.getIconHeight());
		playerPanel.add(playerCards[0]);
		playerPanel.add(playerCards[1]);
		playerPanel.updateUI();
	}
	
	/**
	 * Method that sets the dealer's cards' icons
	 */
	private void createDealerCards(){
		dealerPanel.removeAll();
		ImageIcon dealerFirstCardIcon = new ImageIcon("Cards/back.gif");
		ImageIcon dealerSecondCardIcon = new ImageIcon("Cards/back.gif");
		dealerCards[0] = new JLabel();
		dealerCards[0].setIcon(dealerFirstCardIcon);
		dealerCards[1] = new JLabel();
		dealerCards[1].setIcon(dealerSecondCardIcon);
		dealerCards[0].setBounds(5, 5, dealerFirstCardIcon.getIconWidth(), dealerFirstCardIcon.getIconHeight());
		dealerCards[1].setBounds(dealerFirstCardIcon.getIconWidth() + 10, 5, dealerSecondCardIcon.getIconWidth(), dealerSecondCardIcon.getIconHeight());
		dealerPanel.add(dealerCards[0]);
		dealerPanel.add(dealerCards[1]);
		dealerPanel.updateUI();
	}
	
	/** 
	 * Method to set the community cards' icons
	 */
	public void createCommunityCard(){
		communityCards[communityCardCount] = new JLabel();
		communityCards[communityCardCount].setVisible(false);
		ImageIcon communityCardIcon = new ImageIcon(player1.getHand().get(communityCardCount + 2).getFileName());
		communityCards[communityCardCount].setIcon(communityCardIcon);
		communityCards[communityCardCount].setBounds(communityWidth, 5, communityCardIcon.getIconWidth(), communityCardIcon.getIconHeight());
		communityPanel.add(communityCards[communityCardCount]);
		communityWidth += communityCards[communityCardCount].getWidth() + 5;
		communityCardCount++;
		communityPanel.updateUI();
	}
	
	/**
	 * This method is used to display errors that are relevant to the user
	 * @param option int - The type of error
	 */
	public static void raiseError(int option){
		switch (option){
		//0 represents raise error
		case 0:	JOptionPane.showMessageDialog(board, "Invalid input, try again", "Raise Error", JOptionPane.ERROR_MESSAGE); break;
		//1 represents
		case 1: JOptionPane.showMessageDialog(board, "Deal First", "Error", JOptionPane.ERROR_MESSAGE); break;	
		}
	}
	
	/**
	 * Method for outputting messages to the console
	 * @param s String - Text to be shown on console
	 */
	public void log(String s){
		Thread th = new Thread(){
			@Override
			public void run(){
				//TODO change after debugging
//				console.append(s + "\n");
				System.out.println(s + "\n");
				try{
					Thread.sleep(2000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				System.out.println("Player 1 Chips: " + player1.getChips()
								+"\nPlayer 2 Chips: " + player2.getChips()
								+"\nPot: " + pot.getPot()
								+"\n");
//				console.append("Player 1 Chips: " + player1.getChips()
//								+"\nPlayer 2 Chips: " + player2.getChips()
//								+"\nPot: " + pot.getPot()
//								+"\n");
			}
		};
		th.start();
	}
	
//	private void updateCommunity(){
//		communit
//	}
	
	/**
	 * Method resets the graphic components
	 */
	public void reset(){
		for (int i=0; i < 2; i++){
			dealerCards[i] = null;
			playerCards[i] = null;
		}
		
		for (int i=5; i < 5; i++){
			communityCards[i] = null;
		}
		
		communityPanel.removeAll();
		
		dealt = false;
		dealBtn.setEnabled(true);
	}
	
	/**
	 * This method reveals the community cards
	 */
	public void showCommunity(){
		int i = 0;
		while (communityCards[i] != null && i < 5){
			communityCards[i++].setVisible(true);
			if (i>=5) return;
		}
		
	}
	
	/**
	 * Method that keeps track of how many community cards are currently on the board
	 * When the card count reaches 5, end the round and evaluate both player's hands
	 */
	public void trackCommunity(){
		Thread th = new Thread(){
			@Override
			public void run(){
				while(true){
					if (communityCardCount == 5) game.evaluateRound();
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		th.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoItem){
			AboutFrame abtFrm= new AboutFrame('h');
			abtFrm.setVisible(true);
		}else if (e.getSource() == ruleItem){
			AboutFrame abtFrm = new AboutFrame('r');
			abtFrm.setVisible(true);
		}else if (e.getSource() == saveItem){
			//TODO
		}else if (e.getSource() == loadItem){
			//TODO
		}else if (e.getSource() == newGameItem){
			player1 = new Player();
			player2 = new Player();
			game = new Game(player1, player2, this);
			revalidate();
			repaint();
			//not tested yet
		}
		else{
			if (e.getSource() == dealBtn){
				game.deal();
				System.out.println(player1.getHand().toString());
				communityCardCount = 0;
				createPlayerCards();
				createDealerCards();
				createCommunityCard();
				createCommunityCard();
				dealt = true;
				dealBtn.setEnabled(false);
			}
			else if (!dealt) {
				TexasHoldEm.raiseError(1);
			}
			else{
				if (e.getSource() == callBtn){
					pot.call();
					communityPanel.updateUI();
					//				updateCommunity();
				}else if (e.getSource() == betBtn){
					pot.raise(Integer.parseInt(betField.getText()));
					communityPanel.updateUI();
					//				updateCommunity();
				}else if (e.getSource() == foldBtn){
					pot.fold();
					reset();
				}
			}
		}
	}
	
	public static void main(String args[]){
		board = new TexasHoldEm();
		board.setVisible(true);
	}
}
