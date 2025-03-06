package hangman;

import java.awt.*;
import javax.swing.UIManager.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class main {
	
	private JFrame frame;
	private JLabel theme, word_length;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    WordGenerator wg = new WordGenerator();
    private String word, themes, newTheme;
    private WordGenerator.themes selected;
    private JButton buttonSelect = null, btnShort, btnLong, buttonSelect2, btnPlay;
    private boolean buttonSelect3 = false;
    private playClass playFrame;
    private int length;
    public static Image icon = Toolkit.getDefaultToolkit().getImage("hangman/Images/icons8-hangman-32.png");
	/*
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
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public main() {
		initialize();
	}
	
	public void updateButtonSelect3(boolean buttonSelect3) {
		this.buttonSelect3 = true;
	}
	
	// Create a new playFrame instance
    private void createNewGame() {
        if (playFrame != null) {
            playFrame.dispose(); // Dispose of the old frame
        }
        playFrame = new playClass(this);
        playFrame.setVisible(false);
        if (word != null) {
            playFrame.updateWordLabel(word, themes);
        }
    }
    
	private void initialize() {
		// DEFAULT FRAME
		playFrame = new playClass(this);
		frame = new JFrame();
		frame.setTitle("Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(400, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		playFrame.setVisible(false);
		frame.setIconImage(icon);
		
		// TITLE
		theme = new JLabel("Choose Theme");
		theme.setHorizontalAlignment(SwingConstants.CENTER);
		theme.setBounds(245, 38, 302, 94);
		contentPane.add(theme);
		theme.setForeground(new Color(78, 143, 0));
		theme.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 36));
		
		word_length = new JLabel("Choose Length of Word");
		word_length.setVisible(false);
		if(buttonSelect3 == true)
        	word_length.setVisible(true);
		word_length.setHorizontalAlignment(SwingConstants.CENTER);
		word_length.setForeground(new Color(0, 142, 0));
		word_length.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 27));
		word_length.setBounds(208, 254, 370, 94);
		contentPane.add(word_length);
		
		
		// BUTTONS
		JButton btnBible = new JButton("Bible");
		btnBible.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnBible.setBounds(49, 162, 183, 80);
		btnBible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themes = "BIBLE";
				updateTheme(themes);
				if (buttonSelect != null) {
		            buttonSelect.setBackground(null);
				}
		        btnBible.setBackground(Color.GRAY);
		        buttonSelect = btnBible;
		        if (buttonSelect2 != null) {
		            word = wg.newWord(selected, length);
		            playFrame.updateWordLabel(word, themes);
		        }
				updateButtonSelect3(true);
                updateVisibility();
			}
		});
		contentPane.setLayout(null);
		frame.getContentPane().add(btnBible);
		
		JButton btnFood = new JButton("Food");
		btnFood.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnFood.setBounds(307, 162, 183, 80);
		contentPane.add(btnFood);
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themes = "FOOD";
				updateTheme(themes);
				if (buttonSelect != null) {
		            buttonSelect.setBackground(null);
				}
		        btnFood.setBackground(Color.GRAY);
		        buttonSelect = btnFood;
		        if (buttonSelect2 != null) {
		            word = wg.newWord(selected, length);
		            playFrame.updateWordLabel(word, themes);
		        }
				updateButtonSelect3(true);
                updateVisibility();
			}
		});
		
		JButton btnSports = new JButton("Sports");
		btnSports.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnSports.setBounds(565, 162, 183, 80);
		contentPane.add(btnSports);
		btnSports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themes = "SPORTS";
				updateTheme(themes);
				if (buttonSelect != null) {
		            buttonSelect.setBackground(null);
				}
		        btnSports.setBackground(Color.GRAY);
		        buttonSelect = btnSports;
		        if (buttonSelect2 != null) {
		            word = wg.newWord(selected, length);
		            playFrame.updateWordLabel(word, themes);
		        }
				updateButtonSelect3(true);
                updateVisibility();
			}
		});
        
        
        btnShort = new JButton("Short");
		btnShort.setVisible(false);
		if(buttonSelect3 == true)
        	btnShort.setVisible(true);
        btnShort.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnShort.setBounds(178, 353, 183, 80);
        btnShort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (buttonSelect != null) {
                	if (buttonSelect2 != null)
    		            buttonSelect2.setBackground(null);
                	
    		        btnShort.setBackground(Color.GRAY);
    		        buttonSelect2 = btnShort;
    		        length = 0;
                    word = wg.newWord(selected, length);
                    playFrame.updateWordLabel(word, themes);
                    btnPlay.setVisible(true);
            	}
            }
        });
        contentPane.add(btnShort);
        
        btnLong = new JButton("Long");
        btnLong.setVisible(false);
        if(buttonSelect3 == true)
        	btnLong.setVisible(true);
        btnLong.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnLong.setBounds(445, 353, 183, 80);
        btnLong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (buttonSelect != null) {
            		if (buttonSelect2 != null)
    		            buttonSelect2.setBackground(null);
    		        btnLong.setBackground(Color.GRAY);
    		        buttonSelect2 = btnLong;
    		        length = 1;
                	word = wg.newWord(selected, length);
                	playFrame.updateWordLabel(word, themes);
                    btnPlay.setVisible(true);
            	}
            }
        });
        contentPane.add(btnLong);
        
        btnPlay = new JButton("Play");
		btnPlay.setForeground(new Color(254, 255, 255));
		btnPlay.setBackground(new Color(0, 143, 81));
        btnPlay.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnPlay.setBounds(307, 465, 183, 80);
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (buttonSelect != null && buttonSelect2 != null) {
            		playFrame = new playClass(main.this);
            		createNewGame(); // Create a new game instance
            		playFrame.updateWordLabel(word, newTheme);
            		word = wg.newWord(selected, length); // Create another random word
            		playFrame.setVisible(true);
                    frame.setVisible(false);
            	}
            }
        });
        btnPlay.setVisible(false);
        contentPane.add(btnPlay);
        
        
        // MISC
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 142, 0));
        separator.setBounds(6, 254, 788, 30);
        contentPane.add(separator);
        
	}
	
	private void updateVisibility() {
        btnShort.setVisible(buttonSelect3);
        btnLong.setVisible(buttonSelect3);
        word_length.setVisible(buttonSelect3);
    }
	public void showMainMenu() {
        frame.setVisible(true);
    }
	
	private void updateTheme(String themes) {
		newTheme = themes;
		selected = WordGenerator.themes.valueOf(newTheme);
	}
}