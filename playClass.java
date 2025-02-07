package hangman;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import javax.swing.border.*;

public class playClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel wordLabel, lifes, remaining, title, emptySpaces, howManyLeft, total, remains;
	private JLabel spaces[];
	private JTextField textGuess;
	private JButton btnCheck;
	private int starting = 6, lettersLeft;
	private String theWord, left;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				    // If Nimbus is not available, you can set the GUI to another look and feel.
					// If Windows might not needt this
				}
				try {
					playClass frame = new playClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public playClass() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		title = new JLabel("Hangman");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(283, 16, 257, 33);
		contentPane.add(title);
		
		wordLabel = new JLabel("Generated word will appear here");
        wordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordLabel.setBounds(241, 112, 341, 33);
        contentPane.add(wordLabel);
        
        lifes = new JLabel("Life(s):");
        lifes.setHorizontalAlignment(SwingConstants.CENTER);
        lifes.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        lifes.setBounds(314, 351, 146, 33);
        contentPane.add(lifes);
        
        remaining = new JLabel("6");
        remaining.setHorizontalAlignment(SwingConstants.CENTER);
        remaining.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        remaining.setBounds(426, 351, 58, 33);
        contentPane.add(remaining);
        
        textGuess = new JTextField();
        textGuess.setHorizontalAlignment(SwingConstants.CENTER);
        textGuess.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
        textGuess.setBounds(320, 271, 197, 68);
        contentPane.add(textGuess);
        textGuess.setColumns(10);
        
        btnCheck = new JButton("Check");
		btnCheck.setForeground(new Color(254, 255, 255));
		btnCheck.setBackground(new Color(0, 143, 81));
        btnCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnCheck.setBounds(320, 391, 197, 94);
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	check();
            }
        });
        contentPane.add(btnCheck);
        
        emptySpaces = new JLabel("Empty");
        emptySpaces.setHorizontalAlignment(SwingConstants.CENTER);
        emptySpaces.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        emptySpaces.setBounds(555, 351, 91, 33);
        contentPane.add(emptySpaces);
        
        howManyLeft = new JLabel("Empty");
        howManyLeft.setHorizontalAlignment(SwingConstants.CENTER);
        howManyLeft.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        howManyLeft.setBounds(555, 452, 91, 33);
        contentPane.add(howManyLeft);
        
        total = new JLabel("Total Letters:");
        total.setHorizontalAlignment(SwingConstants.CENTER);
        total.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        total.setBounds(555, 317, 166, 33);
        contentPane.add(total);
        
        remains = new JLabel("How many left:");
        remains.setHorizontalAlignment(SwingConstants.CENTER);
        remains.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        remains.setBounds(555, 419, 180, 33);
        contentPane.add(remains);
	}
	
	// Method to update the word label and set the limit characters for the text field
	public void updateWordLabel(String word) {
		wordLabel.setText(word);
		theWord = word;
		int limit = word.length();
		for (KeyListener remove : textGuess.getKeyListeners()) {
	        textGuess.removeKeyListener(remove);
	    }
		textGuess.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (textGuess.getText().length() >= limit)
		            e.consume(); 
		    }  
		});
		spaces = new JLabel[limit];
		for (KeyListener remove : textGuess.getKeyListeners()) {
	        textGuess.removeKeyListener(remove);
	    }
		for(int i = 0; i < limit; i ++) {
			spaces[i] = new JLabel("____");
		    spaces[i].setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		    spaces[i].setHorizontalAlignment(SwingConstants.CENTER);
		    spaces[i].setBounds(50 + (i * 90), 192, 108, 67);
		    contentPane.add(spaces[i]);
			}
		emptySpaces.setText(String.valueOf(theWord.length()));
		lettersLeft = theWord.length();
		}
	
	    // Method to check the answer
		public void check() {
			boolean success = false;
			for (int i = 0; i < textGuess.getText().length(); i++) {
	            char guessedChar = textGuess.getText().charAt(i);
	            for (int j = 0; j < theWord.length(); j++) {
	                if (guessedChar == theWord.charAt(j)) {
	                	--lettersLeft;
	                	howManyLeft.setText(String.valueOf(lettersLeft));
	                    spaces[j].setText(String.valueOf(guessedChar));
	                    success = true;
	                }
	            }
	        }
			
			if (textGuess.getText().equalsIgnoreCase(theWord) || lettersLeft == 0) {
				JOptionPane.showMessageDialog(btnCheck, "Correct");
			}
			else {
				if(!success) {
					// Change the button color to red
		            textGuess.setBackground(new Color(255, 38, 0));
		            
		            // Create a Timer to change the color back after 500 milliseconds
		            Timer timer = new Timer(360, new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent evt) {
		                    // Revert the button color to its original color
		                    textGuess.setBackground(null);
		                }
		            });
		            timer.setRepeats(false);
		            timer.start();
		            --starting;
		            left = String.valueOf(starting);
		            remaining.setText(left);
		            if(starting == 0)
		            	JOptionPane.showMessageDialog(btnCheck, "DIE");
		            else if(starting <= 0)
		            	JOptionPane.showMessageDialog(btnCheck, "STOP IT YOU'RE DEAD");
				}
			}
		}
	}