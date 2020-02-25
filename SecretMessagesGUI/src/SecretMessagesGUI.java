import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;
	public String encode(String message,int keyVal) {
		String output="";
		char key=(char)keyVal;
		for(int x=0;x<message.length();x++) {
			char input=message.charAt(x);
			if(input>='A' && input<='Z') {
				input+=key;
				if(input>'Z')
					input-=26;
				if(input<'A')
					input+=26;
			}
			else if(input>='a' && input<='z'){
				input+=key;
				if(input>'z')
					input-=26;
				if(input<'a')
					input+=26;
			}
			else if(input>='0' && input<='9') {
				input+=(keyVal%10);
				if(input>'9')
					input-=10;
				if(input<'0')
					input+=10;
			}
			output+=input;
		}
		return output;
	}
	public SecretMessagesGUI() {
		setTitle("Pramita's Secret Message App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setLineWrap(true);
		txtIn.setWrapStyleWord(true);
		txtIn.setBounds(12, 17, 563, 140);
		getContentPane().add(txtIn);
		
		txtOut = new JTextArea();
		txtOut.setLineWrap(true);
		txtOut.setWrapStyleWord(true);
		txtOut.setBounds(12, 217, 563, 140);
		getContentPane().add(txtOut);
		
		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("3");
		txtKey.setBounds(316, 172, 54, 19);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setBounds(265, 174, 37, 15);
		getContentPane().add(lblKey);
		
		JButton btnEncodedecode = new JButton("Encode/Decode");
		btnEncodedecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message=txtIn.getText();
					int key=Integer.parseInt(txtKey.getText());
					String output=encode(message,key);
					txtOut.setText(output);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter whole number value for the encryption key.");
					txtKey.requestFocus();
					txtKey.selectAll();
				}
			}
		});
		btnEncodedecode.setBounds(384, 169, 183, 22);
		getContentPane().add(btnEncodedecode);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				txtKey.setText(""+slider.getValue());
				String message=txtIn.getText();
				int key=slider.getValue();
				String output=encode(message,key);
				txtOut.setText(output);
			}
		});
		slider.setValue(3);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setMaximum(26);
		slider.setMinimum(-26);
		slider.setPaintLabels(true);
		slider.setBackground(new Color(238, 238, 238));
		slider.setBounds(22, 169, 240, 48);
		getContentPane().add(slider);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecretMessagesGUI theApp=new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600,400));
		theApp.setVisible(true);

	}
}
