package userInterface;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import helper.ResourceParser;

public class AboutFrame extends JFrame{
	JTextArea textArea = new JTextArea();
	JScrollPane scroll;
	
	public AboutFrame(char c){
		getContentPane().setLayout(new GridLayout(1,2));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setResizable(false);
		
		textArea.setEditable(false);
		
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(0, 0, 434, 261);
		
		getContentPane().add(scroll);
		
		if (c == 'h') startOnHelp();
		else if (c == 'r') startOnRule();
	}
	
	private void startOnHelp(){
		try {
			textArea.setText(ResourceParser.parseHelp());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void startOnRule(){
		try {
			textArea.setText(ResourceParser.parseRule());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
