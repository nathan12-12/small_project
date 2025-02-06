package hangman;

import java.awt.*;
import javax.swing.UIManager.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class main {
	
	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    WordGenerator wg= new WordGenerator();
    private String word;
    private WordGenerator.themes selected;
    private JButton buttonSelect = null;
    private JButton buttonSelect2 = null;

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
		// DEFAULT FRAME
		frame = new JFrame();
		frame.setTitle("Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(400, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		playClass playFrame = new playClass();
		
		JButton btnBible = new JButton("Bible");
		btnBible.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnBible.setBounds(49, 162, 183, 80);
		btnBible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buttonSelect != null)
		            buttonSelect.setBackground(null);
		        btnBible.setBackground(Color.GRAY);
		        buttonSelect = btnBible;
				selected = WordGenerator.themes.BIBLE;
			}
		});
		contentPane.setLayout(null);
		frame.getContentPane().add(btnBible);
		
		// TITLE
		JLabel theme = new JLabel("Choose Theme");
		theme.setHorizontalAlignment(SwingConstants.CENTER);
		theme.setBounds(245, 38, 302, 94);
		contentPane.add(theme);
		theme.setForeground(new Color(78, 143, 0));
		theme.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 36));
		
		JLabel word_legnth = new JLabel("Choose Length of Word");
		word_legnth.setHorizontalAlignment(SwingConstants.CENTER);
		word_legnth.setForeground(new Color(0, 142, 0));
		word_legnth.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 27));
		word_legnth.setBounds(208, 254, 370, 94);
		contentPane.add(word_legnth);
		
		
		// BUTTONS
		JButton btnFood = new JButton("Food");
		btnFood.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnFood.setBounds(307, 162, 183, 80);
		contentPane.add(btnFood);
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buttonSelect != null)
		            buttonSelect.setBackground(null);
		        btnFood.setBackground(Color.GRAY);
		        buttonSelect = btnFood;
				selected = WordGenerator.themes.FOOD;
			}
		});
		
		JButton btnSports = new JButton("Sports");
		btnSports.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnSports.setBounds(565, 162, 183, 80);
		contentPane.add(btnSports);
		btnSports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buttonSelect != null)
		            buttonSelect.setBackground(null);
		        btnSports.setBackground(Color.GRAY);
		        buttonSelect = btnSports;
				selected = WordGenerator.themes.SPORTS;
			}
		});
		
		JButton btnShort = new JButton("Short");
        btnShort.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnShort.setBounds(178, 353, 183, 80);
        btnShort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (buttonSelect2 != null)
		            buttonSelect2.setBackground(null);
		        btnShort.setBackground(Color.GRAY);
		        buttonSelect2 = btnShort;
                word = wg.newWord(selected, 0);
                playFrame.updateWordLabel(word);
            }
        });
        contentPane.add(btnShort);
        
        JButton btnLong = new JButton("Long");
        btnLong.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnLong.setBounds(445, 353, 183, 80);
        btnLong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (buttonSelect2 != null)
		            buttonSelect2.setBackground(null);
		        btnLong.setBackground(Color.GRAY);
		        buttonSelect2 = btnLong;
            	word = wg.newWord(selected, 1);
            	playFrame.updateWordLabel(word);
            }
        });
        contentPane.add(btnLong);
		
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
        contentPane.add(btnPlay);
        
        
        // MISC
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 142, 0));
        separator.setBounds(6, 254, 788, 30);
        contentPane.add(separator);
		}
	}
