package hangman;

import java.awt.*;
import javax.swing.UIManager.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;

public class main {
	
	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String[] array;
    private Map<String, String[]> wordMap = new HashMap<String, String[]>();
	

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
	/**
	 * Create the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(400, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		
		// Initialize array
		wordMap.put("bibleShort", new String[] {"word1","word2","word5"});
		wordMap.put("bibleLong", new String[] {"word1","word2","word5"});
		wordMap.put("foodShort", new String[] {"word1","word2","word5"});
		wordMap.put("foodLong", new String[] {"word1","word2","word5"});
		wordMap.put("sportShort", new String[] {"word1","word2","word5"});
		wordMap.put("sportLong", new String[] {"word1","word2","word5"});
		
		JButton btnBible = new JButton("Bible");
		btnBible.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnBible.setBounds(49, 162, 183, 80);
		btnBible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		contentPane.setLayout(null);
		frame.getContentPane().add(btnBible);
		
		JLabel theme = new JLabel("Choose Theme");
		theme.setHorizontalAlignment(SwingConstants.CENTER);
		theme.setBounds(245, 38, 302, 94);
		contentPane.add(theme);
		theme.setForeground(new Color(4, 50, 255));
		theme.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 36));
		
		JButton btnFood = new JButton("Food");
		btnFood.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnFood.setBounds(307, 162, 183, 80);
		contentPane.add(btnFood);
		
		JButton btnSports = new JButton("Sports");
		btnSports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSports.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnSports.setBounds(565, 162, 183, 80);
		contentPane.add(btnSports);
		
		JLabel word_legnth = new JLabel("Choose Length of Word");
		word_legnth.setHorizontalAlignment(SwingConstants.CENTER);
		word_legnth.setForeground(new Color(4, 50, 255));
		word_legnth.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 27));
		word_legnth.setBounds(208, 254, 370, 94);
		contentPane.add(word_legnth);
		
		JButton btnShort = new JButton("Short");
        btnShort.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnShort.setBounds(124, 353, 183, 80);
        btnShort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedWordLength("Short");
            }
        });
        contentPane.add(btnShort);
        
        JButton btnLong = new JButton("Long");
        btnLong.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnLong.setBounds(437, 353, 183, 80);
        btnLong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedWordLength("Long");
            }
        });
        contentPane.add(btnLong);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setForeground(new Color(254, 255, 255));
		btnPlay.setBackground(Color.GREEN);
        btnPlay.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnPlay.setBounds(307, 465, 183, 80);
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (array != null && array.length > 0) {
                    JFrame gameWindow = new JFrame("Game");
                    gameWindow.setSize(400, 400);
                    gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    gameWindow.setVisible(true);
                    // Add game logic here
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a theme and word length before playing.");
                }
            }
        });
        contentPane.add(btnPlay);
		}
        
        private void setSelectedTheme(String theme) {
            String[] shortWords = wordMap.get(theme + "Short");
            String[] longWords = wordMap.get(theme + "Long");
            if (shortWords != null && longWords != null) {
                // Theme selected, now wait for word length selection
                array = null;
            }
        }
        
        private void setSelectedWordLength(String length) {
            if (array != null) {
                array = wordMap.get(array + length);
            }
        
	}
}
