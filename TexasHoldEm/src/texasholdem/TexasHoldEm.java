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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class TexasHoldEm extends JFrame implements ActionListener {
	private ImageIcon background;
	private Player player1, player2, AIplayer;
	private Game game;
	private Pot pot;
	
	private static TexasHoldEm board;
	private JButton betBtn,callBtn,dealBtn,foldBtn;
	private JLabel playerChipLabel, dealerChipLabel,potLabel;
	private JTextField betField;
	
	public TexasHoldEm(){
		super("Texas Hold Em");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,450);
		setLocationRelativeTo(null);
		setBackground(Color.GRAY);
		
		background = new ImageIcon("Cards/b.gif");
		Border sunkIn = BorderFactory.createBevelBorder(BevelBorder.LOWERED); 
		
		JLabel dealerLabel = new JLabel();
		JLabel playerLabel = new JLabel();
		JLabel communityLabel = new JLabel();
		
		playerChipLabel = new JLabel("Chips: ");
		dealerChipLabel = new JLabel("Chips: ");
		potLabel = new JLabel("Pot: ");
		
		JPanel btnPanel = new JPanel(); 
		btnPanel.setLayout(new GridLayout(1, 5));
		btnPanel.setBorder(sunkIn);
		
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
		
		JPanel communityPanel = new JPanel();
		communityLabel.setText("Community Cards");
		communityLabel.setHorizontalAlignment(JLabel.CENTER);
		communityLabel.setBounds(dealerLabel.getWidth() + 5, 5, communityPanel.getWidth(), 20);
		
		communityPanel.setBorder(sunkIn);
		communityPanel.setBounds(181, 30, 395, 107);
		
		JPanel playerPanel = new JPanel();
		playerLabel.setText("Player Cards");
		playerLabel.setHorizontalAlignment(JLabel.CENTER);
		playerLabel.setBounds(5, btnPanel.getBounds().y + btnPanel.getHeight() + 5, btnPanel.getWidth(), 25);
		playerPanel.setLayout(null);
		playerPanel.setBorder(sunkIn);
		
		playerPanel.setBounds(205, 217, 161, 107);
		
		JPanel dealerPanel = new JPanel();
		dealerPanel.setLayout(null);
		dealerPanel.setBorder(sunkIn);
		
		dealerPanel.setBounds(5, 30, 161, 107);
		
		dealerLabel.setText("Dealer Cards");
		dealerLabel.setHorizontalAlignment(JLabel.CENTER);
		dealerLabel.setBounds(5, 5, dealerPanel.getWidth(), 20);
		
		btnPanel.setBounds(5, dealerPanel.getHeight() + 10 + 30 + 5, dealerPanel.getWidth() + 15 + communityPanel.getWidth(), 30);
		
		add(dealerPanel);
		add(dealerLabel);
		add(communityPanel);
		add(communityLabel);
		add(btnPanel);
		add(playerPanel);
		add(playerLabel);
	}
	
	public static void raiseError(int option){
		JOptionPane.showMessageDialog(board, "Error placeholder ");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == callBtn){
			pot.call();
		}else if (e.getSource() == betBtn){
			pot.raise(Integer.parseInt(betField.getText()));
		}else if (e.getSource() == foldBtn){
			pot.fold();
		}else if (e.getSource() == dealBtn){
			if (player1 == null || player2 == null){
				player1 = new Player();
				player2 = new Player();
				Game game = new Game(player1, player2);
				game.newRound();
			}
			game.newRound();
		}
	}
	
	public static void main(String args[]){
		board = new TexasHoldEm();
		board.setVisible(true);
	}
}
