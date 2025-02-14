package hangman;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import javax.swing.border.*;

public class playClass2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel title;
	private JLabel spaces[];
	private JTextField charGuess;
	private JButton btnCheck;
	private int lettersLeft;
	private String theWord;
	private JTextField guessedWord;
	private char pastLetters[]={};
	private JLabel gallows;
	private int failAttempts=0; //this will be used as the index of the gallowImg
	private String gallowImg [] = {"/hangman/Images/gallows1.png", "/hangman/Images/gallows2.png", "/hangman/Images/gallows3.png", 
			"/hangman/Images/gallows4.png", "/hangman/Images/gallows5.png", "/hangman/Images/gallows6.png", 
			"/hangman/Images/gallows7.png" };
	private String theme = "Hangman";
	
	
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
				try {
					playClass2 frame = new playClass2();
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
	public playClass2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 44, 44));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel guessWordPanel = new JPanel();
		guessWordPanel.setVisible(false);
		guessWordPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		guessWordPanel.setBounds(112, 400, 653, 163);
		contentPane.add(guessWordPanel);
		
		
		//button for entering word
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnNewButton.setBounds(321, 75, 186, 62);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String guess = guessedWord.getText();
				if (guess.length()==theWord.length()){
					checkWord(guessedWord.getText());
					guessWordPanel.setVisible(false);
					guessedWord.setText("");
				}
				else
					JOptionPane.showMessageDialog(btnCheck, "Your word isn't the same length");
			}
		});
		guessWordPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Word");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 23));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 10, 186, 60);
		guessWordPanel.add(lblNewLabel_1);
		guessWordPanel.add(btnNewButton);
		
		guessedWord = new JTextField();
		guessedWord.setFont(new Font("Dialog", Font.PLAIN, 23));
		guessedWord.setBounds(37, 84, 220, 45);
		guessWordPanel.add(guessedWord);
		guessedWord.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("X");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guessWordPanel.setVisible(false);
				guessedWord.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton_1.setBounds(585, 10, 47, 45);
		guessWordPanel.add(btnNewButton_1);
		
		title = new JLabel(theme);
		title.setForeground(new Color(235, 255, 217));
		title.setFont(new Font("Dialog", Font.BOLD, 27));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(283, 16, 257, 33);
		contentPane.add(title);
        
        
        // Answer Box
        charGuess = new JTextField();
        charGuess.setHorizontalAlignment(SwingConstants.CENTER);
        charGuess.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
        charGuess.setBounds(373, 474, 91, 68);
        contentPane.add(charGuess);
        charGuess.setColumns(10);
        
		charGuess.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		    	char c = e.getKeyChar();
		    	if (Character.isAlphabetic(c)) {
		    		boolean isNew = true;
		    		for (int i = 0; i<pastLetters.length; i++) {
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
        btnCheck.setBounds(568, 459, 197, 94);
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	guessWordPanel.setVisible(true);
            	guessedWord.grabFocus();
            }
        });
        contentPane.add(btnCheck);
        
        JLabel lblNewLabel = new JLabel("Enter letter:");
        lblNewLabel.setForeground(new Color(235, 255, 217));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 23));
        lblNewLabel.setBounds(222, 472, 152, 68);
        contentPane.add(lblNewLabel);
        
        gallows = new JLabel("");
        gallows.setIcon(new ImageIcon(playClass2.class.getResource(gallowImg[failAttempts])));
        gallows.setBounds(42, 45, 240, 287);
        contentPane.add(gallows);
        
	}
	
	// Method to update the word label and set the limit characters for the text field
	public void updateWordLabel(String word) {
		title.setText(theme);
		System.out.println(word);
		theWord = word;
		lettersLeft=word.length();
		
		spaces = new JLabel[lettersLeft];
		for(int i = 0; i < lettersLeft; i ++) {
			spaces[i] = new JLabel("__");
			spaces[i].setForeground(new Color(235, 255, 217));
			spaces[i].setFont(new Font("Dialog", Font.BOLD, 27));
		    spaces[i].setHorizontalAlignment(SwingConstants.CENTER);
		    spaces[i].setBounds(100 + (i * 70), 350, 70, 67);
		    contentPane.add(spaces[i]);
			}
		}
	
		//Method to check word
		private void checkWord(String guess) {
			if (theWord.equalsIgnoreCase(guess)) {
				for (int j = 0; j < theWord.length(); j++) {
                    spaces[j].setText(String.valueOf(guess.charAt(j)));
				}
				JOptionPane.showMessageDialog(btnCheck, "Correct: The word was " + theWord);
		}
			else {
				failAttempts++;
	            if(failAttempts >= 6) {
            		gallows.setIcon(new ImageIcon(playClass2.class.getResource(gallowImg[6])));
	            	JOptionPane.showMessageDialog(btnCheck, "YOU LOSE");
	            }
	            else
	            	gallows.setIcon(new ImageIcon(playClass2.class.getResource(gallowImg[failAttempts])));
			}
			
		}
	 

		 // Method to check for a letter
		private void checkLetter(char guessedChar) {
			boolean success = false;
			guessedChar=Character.toLowerCase(guessedChar);
            for (int i = 0; i < theWord.length(); i++) {
                if (guessedChar == theWord.charAt(i)) {
                	--lettersLeft;
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
	            failAttempts++;
	            if(failAttempts >= 6) {
            		gallows.setIcon(new ImageIcon(playClass2.class.getResource(gallowImg[6])));
	            	JOptionPane.showMessageDialog(btnCheck, "YOU LOSE");
	            }
	            else
	            	gallows.setIcon(new ImageIcon(playClass2.class.getResource(gallowImg[failAttempts])));
				
			}
			// Create a Timer to change the color back after 360 milliseconds
            Timer timer = new Timer(360, new ActionListener() {
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
		private char[] addLetter(char arr[], char l){
			int n =arr.length;
		    char newarr[] = new char[ n+ 1];
		    for (int i = 0; i < n; i++)
		    	newarr[i] = arr[i];
		    newarr[n] = l;
		    return newarr;
		}
		
		public void setTheme(String newTheme) {
			theme=newTheme;
		}
	}