package hangman;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import javax.swing.border.*;

public class playClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel wordLabel;

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
        wordLabel.setBounds(254, 100, 341, 33);
        contentPane.add(wordLabel);
        }
	// Method to update the word label	
	public void updateWordLabel(String word) {
		wordLabel.setText(word);
	}

	/*public void paint(Graphics g){
        g.drawOval(40, 40, 60, 60); //FOR CIRCLE
        g.drawRect(80, 30, 200, 200); // FOR SQUARE
        g.drawRect(200, 100, 100, 200); // FOR RECT
   }
   */
}
