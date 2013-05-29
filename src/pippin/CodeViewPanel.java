package pippin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CodeViewPanel implements Observer
{
	private JTextField[] intFields  = new JTextField[Memory.DATA_SIZE], binaryFields =new JTextField[Memory.DATA_SIZE];
	private Machine machine;
	private int currentLocation;
	
	 public CodeViewPanel(Machine machine) {
	        this.machine = machine;
	        machine.addObserver(this);
	    }
	 
	 public JComponent createCodeDisplay()
	 {
		 JPanel returnPanel = new JPanel();
		 returnPanel.setLayout(new BorderLayout());
		 
		 returnPanel.add(new JLabel("Code Memory View", JLabel.CENTER), BorderLayout.PAGE_START);

		 JPanel panel = new JPanel();
		 panel.setLayout(new BorderLayout());

		 JPanel numPanel = new JPanel();
		 numPanel.setLayout(new GridLayout(0,1));

		 JPanel intPanel = new JPanel();
		 intPanel.setLayout(new GridLayout(0,1));

		 JPanel binaryPanel = new JPanel();
		 binaryPanel.setLayout(new GridLayout(0,1));

		 panel.add(numPanel,BorderLayout.LINE_START);
		 panel.add(intPanel,BorderLayout.CENTER);
		 panel.add(binaryPanel,BorderLayout.LINE_END);
		 
		 for(int i=0; i<intFields.length; i++)
		 {
			 numPanel.add(new JLabel(""+i+":", JLabel.RIGHT));
			 intFields[i] = new JTextField(10);
			 intPanel.add(intFields[i]);
			 binaryFields[i] = new JTextField(30);
			 binaryPanel.add(binaryFields[i]);
		 }
		 returnPanel.add(new JScrollPane(panel));
		 return returnPanel;
	 }
 
	@Override
	  public void update(Observable o, Object msg) {
        if ("New Program".equals(msg)) {
            for(int i = 0; i < Memory.CODE_SIZE; i++) {
                try {
                    CodeLine line = machine.getLine(i);
                    if(line != null) { // many CodeLines may be null so WE NEED THIS CHECK
                        String source = line.toString();
                        String binary = machine.getInstructionBinary(i);
                        intFields[i].setText(source);
                        binaryFields[i].setText(binary);
                    }
                } catch (CodeAccessException e) {
                	JOptionPane.showMessageDialog(null,
		                    e.getMessage(),
		                    "Code Failure", JOptionPane.WARNING_MESSAGE);
                }
            }
            currentLocation = 0;
            intFields[currentLocation].setBackground(Color.YELLOW);
            binaryFields[currentLocation].setBackground(Color.YELLOW);
        } else if ("Clear".equals(msg)) {
            for(int i = 0; i < Memory.CODE_SIZE; i++) {
                intFields[i].setText("");
                binaryFields[i].setText("");                
                intFields[i].setBackground(Color.WHITE);
                binaryFields[i].setBackground(Color.WHITE);
            }            
        } else {
        	intFields[currentLocation].setBackground(Color.WHITE);
			binaryFields[currentLocation].setBackground(Color.WHITE);
		    currentLocation = machine.getPC();
		    intFields[currentLocation].setBackground(Color.YELLOW);
			binaryFields[currentLocation].setBackground(Color.YELLOW);
        }
    }
	
}
