package hangman;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import javax.swing.border.*;

public class playClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel wordLabel;
	private JTextField textGuess;

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
		
		JLabel lblNewLabel = new JLabel("Hangman");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(283, 16, 257, 33);
		contentPane.add(lblNewLabel);
		
		wordLabel = new JLabel("Generated word will appear here");
        wordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordLabel.setBounds(241, 112, 341, 33);
        contentPane.add(wordLabel);
        
        textGuess = new JTextField();
        textGuess.setHorizontalAlignment(SwingConstants.CENTER);
        textGuess.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
        textGuess.setBounds(320, 238, 197, 68);
        contentPane.add(textGuess);
        textGuess.setColumns(10);
        }
	
	// Method to update the word label and set the limit characters for the text field
	public void updateWordLabel(String word) {
		wordLabel.setText(word);
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
	}
}
