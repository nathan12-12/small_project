package hangman;

import java.awt.*;
import javax.swing.UIManager.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class main2 {
	
	private JFrame frame;
	private JLabel theme, word_length;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    WordGenerator wg = new WordGenerator();
    private String word;
    private WordGenerator.themes selected;
    private JButton buttonSelect = null, btnShort, btnLong, buttonSelect2;
    private boolean buttonSelect3 = false;

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
					// If Windows might not needt this
				}
				
				try {
					main2 window = new main2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public main2() {
		initialize();
	}
	
	public void updateButtonSelect3(boolean buttonSelect3) {
		this.buttonSelect3 = true;
	}
	/**
	 * Create the frame.
	 */
	private void initialize() {
		// DEFAULT FRAME
		frame = new JFrame();
		frame.setTitle("Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(400, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		playClass2 playFrame = new playClass2();
		
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
				if (buttonSelect != null) {
		            buttonSelect.setBackground(null);
				}
		        btnBible.setBackground(Color.GRAY);
		        buttonSelect = btnBible;
				selected = WordGenerator.themes.BIBLE;
				updateButtonSelect3(true);
                updateVisibility();
                playFrame.setTheme("Bible");
                
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
				if (buttonSelect != null) {
		            buttonSelect.setBackground(null);
				}
		        btnFood.setBackground(Color.GRAY);
		        buttonSelect = btnFood;
				selected = WordGenerator.themes.FOOD;
				updateButtonSelect3(true);
                updateVisibility();
                playFrame.setTheme("Food");
			}
		});
		
		JButton btnSports = new JButton("Sports");
		btnSports.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnSports.setBounds(565, 162, 183, 80);
		contentPane.add(btnSports);
		btnSports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buttonSelect != null) {
		            buttonSelect.setBackground(null);
				}
		        btnSports.setBackground(Color.GRAY);
		        buttonSelect = btnSports;
				selected = WordGenerator.themes.SPORTS;
				updateButtonSelect3(true);
                updateVisibility();
                playFrame.setTheme("Sports");
			}
		});
		
		
        
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setForeground(new Color(254, 255, 255));
		btnPlay.setBackground(new Color(0, 143, 81));
        btnPlay.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnPlay.setBounds(307, 465, 183, 80);
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (buttonSelect != null && buttonSelect2 != null) {
            		playFrame.setVisible(true);
            	} else {
            		JOptionPane.showMessageDialog(frame, "Please select a theme and word length before playing.");
            	}
            }
        });
        btnPlay.setVisible(false);
        contentPane.add(btnPlay);
        
        
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
                    word = wg.newWord(selected, 0);
                    playFrame.updateWordLabel(word);
                    btnPlay.setVisible(true);
            	} else {
            		JOptionPane.showMessageDialog(frame, "Please select a theme first.");
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
                	word = wg.newWord(selected, 1);
                	playFrame.updateWordLabel(word);
                    btnPlay.setVisible(true);
            	} else {
            		JOptionPane.showMessageDialog(frame, "Please select a theme first.");
            	}
            }
        });
        contentPane.add(btnLong);
        
        
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
	
}
