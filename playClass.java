package hangman;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import javax.swing.border.*;

public class playClass extends JFrame {
	
	private main mainMenu;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, guessWordPanel;
	private JLabel wordLabel, lifes, remaining, title, emptySpaces, howManyLeft, total, remains, enter_word;
	private JLabel spaces[];
	private JTextField charGuess;
	private JButton btnCheck;
	private int livesLeft = 6, lettersLeft;
	private String theWord, left;
	private JTextField guessedWord;
	private char pastLetters[]={};
	private Timer timer;
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
					// If Windows might not need this
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public playClass(main mainMenu) {
		this.mainMenu = mainMenu;
		timer = new Timer(360, new ActionListener() { // Initialize ONCE
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Reset colors and clear text fields
                guessedWord.setOpaque(false);
                guessedWord.setBackground(null);
                charGuess.setBackground(null);
                charGuess.setText("");
                guessedWord.setText("");
                guessWordPanel.setVisible(false);
            }
        });
        timer.setRepeats(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		guessWordPanel = new JPanel();
		guessWordPanel.setVisible(false);
		guessWordPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		guessWordPanel.setBounds(71, 271, 644, 238);
		contentPane.add(guessWordPanel);
		
		
		//button for entering word
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnNewButton.setBounds(215, 168, 186, 62);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String guess = guessedWord.getText();
				if (guess.length()==theWord.length()){
					checkWord(guess);
                    guessedWord.setOpaque(true);
                    guessedWord.setBackground(new Color(255, 38, 0));
                    guessedWord.repaint();
                    timer.restart();
				}
				else {
					JOptionPane.showMessageDialog(btnCheck, "Your word isn't the same length");
				}
			}
		});
		guessWordPanel.setLayout(null);
		
		enter_word = new JLabel("Enter Word");
		enter_word.setFont(new Font("Dialog", Font.PLAIN, 23));
		enter_word.setHorizontalAlignment(SwingConstants.CENTER);
		enter_word.setBounds(215, 24, 186, 60);
		guessWordPanel.add(enter_word);
		guessWordPanel.add(btnNewButton);
		
		guessedWord = new JTextField();
		guessedWord.setFont(new Font("Dialog", Font.PLAIN, 23));
		guessedWord.setBounds(198, 97, 220, 45);
		guessWordPanel.add(guessedWord);
		guessedWord.setColumns(10);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guessWordPanel.setVisible(false);
				guessedWord.setText("");
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnClose.setBounds(585, 10, 47, 45);
		guessWordPanel.add(btnClose);
		
		title = new JLabel("Hangman");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(283, 16, 257, 33);
		contentPane.add(title);
		
		wordLabel = new JLabel("Generated word will appear here");
        wordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordLabel.setBounds(222, 112, 341, 33);
        contentPane.add(wordLabel);
        
        lifes = new JLabel("Life(s):");
        lifes.setHorizontalAlignment(SwingConstants.CENTER);
        lifes.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        lifes.setBounds(40, 111, 146, 33);
        contentPane.add(lifes);
        
        remaining = new JLabel("6");
        remaining.setHorizontalAlignment(SwingConstants.CENTER);
        remaining.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        remaining.setBounds(151, 111, 58, 33);
        contentPane.add(remaining);
        
        emptySpaces = new JLabel("Empty");
        emptySpaces.setHorizontalAlignment(SwingConstants.CENTER);
        emptySpaces.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        emptySpaces.setBounds(40, 358, 91, 33);
        contentPane.add(emptySpaces);
        
        howManyLeft = new JLabel("Empty");
        howManyLeft.setHorizontalAlignment(SwingConstants.CENTER);
        howManyLeft.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        howManyLeft.setBounds(40, 459, 91, 33);
        contentPane.add(howManyLeft);
        
        total = new JLabel("Total Letters:");
        total.setHorizontalAlignment(SwingConstants.CENTER);
        total.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        total.setBounds(40, 324, 166, 33);
        contentPane.add(total);
        
        remains = new JLabel("How many left:");
        remains.setHorizontalAlignment(SwingConstants.CENTER);
        remains.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        remains.setBounds(40, 426, 180, 33);
        contentPane.add(remains);
        
        
        // Answer Box
        charGuess = new JTextField();
        charGuess.setHorizontalAlignment(SwingConstants.CENTER);
        charGuess.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
        charGuess.setBounds(371, 271, 91, 68);
        contentPane.add(charGuess);
        charGuess.setColumns(10);
        
		charGuess.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		    	char c = e.getKeyChar();
		    	if (Character.isAlphabetic(c)) {
		    		boolean isNew = true;
		    		System.out.println("test 1");
		    		for (int i = 0; i<pastLetters.length; i++) {
			    		System.out.println("test 2");
		    			if (c==pastLetters[i]) {
		    				isNew=false;
		    				JOptionPane.showMessageDialog(btnCheck, "You've already entered that letter");
		    				e.consume(); 
		    				break;
		    			}
		    		}
		    		if (isNew) {
		    			checkLetter(c);
		    			pastLetters = addLetter(pastLetters, c);
		    		}

		    	}
		    	else
		            e.consume(); 
		    }  
		});
        
        
        // Button
        btnCheck = new JButton("Guess Word");
		btnCheck.setForeground(new Color(254, 255, 255));
		btnCheck.setBackground(new Color(0, 143, 81));
        btnCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnCheck.setBounds(567, 395, 197, 94);
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	guessWordPanel.setVisible(true);
            	guessedWord.grabFocus();
            }
        });
        contentPane.add(btnCheck);
        
        JLabel enter_letter = new JLabel("Enter letter:");
        enter_letter.setHorizontalAlignment(SwingConstants.CENTER);
        enter_letter.setFont(new Font("Dialog", Font.PLAIN, 23));
        enter_letter.setBounds(220, 269, 152, 68);
        contentPane.add(enter_letter);
        
	}
	
	// Method to update the word label and set the limit characters for the text field
	public void updateWordLabel(String word) {
		wordLabel.setText(word);
		theWord = word;
		int limit = word.length();
		
		spaces = new JLabel[limit];
		for(int i = 0; i < limit; i ++) {
			spaces[i] = new JLabel("____");
		    spaces[i].setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		    spaces[i].setHorizontalAlignment(SwingConstants.CENTER);
		    spaces[i].setBounds(200 + (i * 50), 192, 50, 67);
		    contentPane.add(spaces[i]);
			}
		
		emptySpaces.setText(String.valueOf(theWord.length()));
		lettersLeft = theWord.length();
		}
	
		//Method to check word
	public void checkWord(String guess) {
			if (theWord.equalsIgnoreCase(guess)) {
				howManyLeft.setText("0");
				for (int j = 0; j < theWord.length(); j++) {
                    spaces[j].setText(String.valueOf(guess.charAt(j)));
				}
				JOptionPane.showMessageDialog(btnCheck, "Correct: The word was " + theWord);
			}
			else {
				guessedWord.setOpaque(true);
	            guessedWord.setBackground(new Color(255, 38, 0));
	            guessedWord.repaint();
	            timer = new Timer(360, new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent evt) {
	                    // Revert the label color to its original color
	                    guessedWord.setOpaque(false);
	                    guessedWord.setBackground(null);
	                    guessedWord.repaint();
	                    guessWordPanel.setVisible(true);
	                }
	            });
	            timer.setRepeats(false);
	            timer.start();
				 --livesLeft;
	            left = String.valueOf(livesLeft);
	            remaining.setText(left);
	            if(livesLeft == 0)
	                gameOver();
	            else if(livesLeft <= 0 && livesLeft >-10)
	            	JOptionPane.showMessageDialog(btnCheck, "STOP IT YOU'RE DEAD");
			}
			
		}
		 // Method to check for a letter
	public void checkLetter(char guessedChar) {
			boolean success = false;
			guessedChar=Character.toLowerCase(guessedChar);
            for (int i = 0; i < theWord.length(); i++) {
                if (guessedChar == theWord.charAt(i)) {
                	--lettersLeft;
                	howManyLeft.setText(String.valueOf(lettersLeft));
                    spaces[i].setText(String.valueOf(guessedChar));
                    success = true;
                }
            }
			if (lettersLeft == 0) {
				JOptionPane.showMessageDialog(btnCheck, "Correct: The word was " + theWord);
			}
			else if(!success) {
				// Change the button color to red
	            charGuess.setBackground(new Color(255, 38, 0));
	            --livesLeft;
	            left = String.valueOf(livesLeft);
	            remaining.setText(left);
	            if(livesLeft == 0) {
	            	JOptionPane.showMessageDialog(btnCheck, "DIE");
	            	gameOver();
	            }
				
			}
			// Create a Timer to change the color back after 360 milliseconds
            timer = new Timer(360, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // Revert the button color to its original color
                    charGuess.setBackground(null);
                    //Clear the text
                    charGuess.setText("");
                }
            });
            timer.setRepeats(false);
            timer.start();
			
		}
		
		//method to add letters to array
	public static char[] addLetter(char arr[], char l){
		int n =arr.length;
		char newarr[] = new char[n + 1];
		for (int i = 0; i < n; i++)
		  	newarr[i] = arr[i];
		newarr[n] = l;
		return newarr;
	}
	private void gameOver() {
		int option = JOptionPane.showConfirmDialog(btnCheck, "You ran out of lives! The word was " + theWord +
				". Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			lettersLeft = theWord.length(); // Reset lettersLeft
        	howManyLeft.setText(String.valueOf(lettersLeft));
        	pastLetters = new char[0]; // Clear past letters
        	charGuess.setText(""); // Clear the guess field
        	guessWordPanel.setVisible(false);
        	guessedWord.setText("");	        for (int i = 0; i < spaces.length; i++) {
	             contentPane.remove(spaces[i]); //Remove the old JLabels from contentPane
	        }
	        spaces = null;
	        revalidate(); // Refresh the contentPane
	        repaint();
	        mainMenu.showMainMenu();
	        this.setVisible(false);
		}
	    else
	    	System.exit(0);
		}
	}