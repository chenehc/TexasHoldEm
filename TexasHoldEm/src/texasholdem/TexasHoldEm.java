package texasholdem;

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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.sun.glass.events.KeyEvent;


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
	private JLabel playerChipLabel, dealerChipLabel, potLabel, dealerLabel, communityLabel, playerLabel;
	private JLabel[] playerCards, dealerCards, communityCards;
	private JTextField betField;
	private Border sunkIn = BorderFactory.createBevelBorder(BevelBorder.LOWERED); 
	
	private boolean dealt = false;
	private int communityCardCount = 0;
	private int communityWidth = 5;
	
	//constructor
	public TexasHoldEm(){
		super("Texas Hold Em");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,450);
		setLocationRelativeTo(null);
		setBackground(Color.GRAY);
		
		background = new ImageIcon("Cards/b.gif");
		
		player1 = new Player();
		player2 = new Player();

		Game game = new Game(player1, player2);
		pot = game.getPot();
		
		addMenu();
		addPanel();
		
		//add the components onto the frame
		add(menuBar);
		add(dealerPanel);
		add(dealerLabel);
		add(communityPanel);
		add(communityLabel);
		add(btnPanel);
		add(playerPanel);
		add(playerLabel);
	}
	
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
	
	private void addPanel(){
		dealerLabel = new JLabel();
		playerLabel = new JLabel();
		communityLabel = new JLabel();
		
		playerChipLabel = new JLabel("Chips: ");
		dealerChipLabel = new JLabel("Chips: ");
		potLabel = new JLabel("Pot: ");
		
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
		
		betField = new JTextField("Enter bet amount: ");
		btnPanel.add(betField);
		
		//create community panel that shows the community cards
		communityPanel = new JPanel();
		communityLabel.setText("Community Cards");
		communityLabel.setHorizontalAlignment(JLabel.CENTER);
		communityLabel.setBounds(dealerLabel.getWidth() + 5, 5, communityPanel.getWidth(), 20);
		
		communityPanel.setBorder(sunkIn);
		communityPanel.setBounds(181, 30, 395, 107);
		
		communityCards = new JLabel[5];
		
		//create a player panel that shows the player's cards
		playerPanel = new JPanel();
		playerLabel.setText("Player Cards");
		playerLabel.setHorizontalAlignment(JLabel.CENTER);
		playerLabel.setBounds(5, btnPanel.getBounds().y + btnPanel.getHeight() + 5, btnPanel.getWidth(), 25);
		playerPanel.setLayout(null);
		playerPanel.setBorder(sunkIn);
		
		playerPanel.setBounds(205, 217, 161, 107);
		
		playerCards = new JLabel[2];
		
		//create a dealer panel to show dealer's card back
		dealerPanel = new JPanel();
		dealerPanel.setLayout(null);
		dealerPanel.setBorder(sunkIn);
		
		dealerPanel.setBounds(5, 30, 161, 107);
		
		dealerLabel.setText("Dealer Cards");
		dealerLabel.setHorizontalAlignment(JLabel.CENTER);
		dealerLabel.setBounds(5, 5, dealerPanel.getWidth(), 20);
		
		dealerCards = new JLabel[2];
		btnPanel.setBounds(5, dealerPanel.getHeight() + 10 + 30 + 5, dealerPanel.getWidth() + 15 + communityPanel.getWidth(), 30);
		
	}

	private void createPlayerCards(){
		ImageIcon playerFirstCardIcon = new ImageIcon(player1.getHand().get(0).getFileName());
		ImageIcon playerSecondCardIcon = new ImageIcon(player1.getHand().get(1).getFileName());
		playerCards[0] = new JLabel();
		playerCards[0].setIcon(playerFirstCardIcon);
		playerCards[1] = new JLabel();
		playerCards[1].setIcon(playerSecondCardIcon);
		playerCards[0].setBounds(5, 5, playerFirstCardIcon.getIconWidth(), playerFirstCardIcon.getIconHeight());
		playerCards[1].setBounds(playerFirstCardIcon.getIconWidth() + 5, 5, playerSecondCardIcon.getIconWidth(), playerSecondCardIcon.getIconHeight());
		playerPanel.add(playerCards[0]);
		playerPanel.add(playerCards[1]);
		playerPanel.updateUI();
	}
	
	private void createDealerCards(){
		ImageIcon dealerFirstCardIcon = new ImageIcon(player2.getHand().get(0).getFileName());
		ImageIcon dealerSecondCardIcon = new ImageIcon(player2.getHand().get(1).getFileName());
		dealerCards[0] = new JLabel();
		dealerCards[0].setIcon(dealerFirstCardIcon);
		dealerCards[1] = new JLabel();
		dealerCards[1].setIcon(dealerSecondCardIcon);
		dealerCards[0].setBounds(5, 5, dealerFirstCardIcon.getIconWidth(), dealerFirstCardIcon.getIconHeight());
		dealerCards[1].setBounds(dealerFirstCardIcon.getIconWidth() + 5, 5, dealerSecondCardIcon.getIconWidth(), dealerSecondCardIcon.getIconHeight());
		dealerPanel.add(dealerCards[0]);
		dealerPanel.add(dealerCards[1]);
		dealerPanel.updateUI();
	}
	
	private void createCommunityCard(){
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!dealt) TexasHoldEm.raiseError(1);
		else{
			if (e.getSource() == callBtn){
				pot.call();
			}else if (e.getSource() == betBtn){
				pot.raise(Integer.parseInt(betField.getText()));
			}else if (e.getSource() == foldBtn){
				pot.fold();
			}
		}
		if (e.getSource() == dealBtn){
			dealt = true;
			game.deal();
		}
	}
	
	public static void main(String args[]){
		board = new TexasHoldEm();
		board.setVisible(true);
	}
}
