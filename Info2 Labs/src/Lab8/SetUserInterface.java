package Lab8;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 * @edited Kim & Le Ha 3.12.2019
 */
public class SetUserInterface
implements ActionListener
{
	protected SetCalcEngine calc;
	protected boolean showingAuthor;
	protected JFrame frame;
	protected JTextField display;
	protected JLabel status;
	protected JPanel buttonPanel;
	protected JPanel contentPane;
	protected JPanel hexaPanel;
	protected ButtonGroup buttonG = new ButtonGroup();
	/**
	 * Create a user interface.
	 * @param engine The calculator engine.
	 */
	public SetUserInterface(SetCalcEngine engine)
	{
		calc = engine;
		showingAuthor = true;
		makeFrame();

		frame.setVisible(true);

	}

	/**
	 * Set the visibility of the interface.
	 * @param visible true if the interface is to be made visible, false otherwise.
	 */
	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	protected void makeFrame()
	{
		frame = new JFrame(calc.getTitle());

		contentPane = (JPanel)frame.getContentPane();
		contentPane.setLayout(new BorderLayout(0, 8));
		contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

		buttonPanel = new JPanel(new GridLayout(4, 5));
		addButton(buttonPanel, "7");
		addButton(buttonPanel, "8");
		addButton(buttonPanel, "9");
		addButton(buttonPanel, "Clear");
		addButton(buttonPanel, "?");

		addButton(buttonPanel, "4");
		addButton(buttonPanel, "5");
		addButton(buttonPanel, "6");
		addButton(buttonPanel, "+");
		addButton(buttonPanel, "-");

		addButton(buttonPanel, "1");
		addButton(buttonPanel, "2");
		addButton(buttonPanel, "3");
		addButton(buttonPanel, "size");
		addButton(buttonPanel, "X");
	
		
		addButton(buttonPanel, "0");
		addButton(buttonPanel, "{");
		addButton(buttonPanel, "}");
		addButton(buttonPanel, ",");
		addButton(buttonPanel, "=");

		contentPane.add(buttonPanel, BorderLayout.CENTER);
		
		JPanel checkboxPanel = new JPanel(new GridLayout(1,1));
		display = new JTextField();
		checkboxPanel.add(display);
		
/*
		addCheckbox(checkboxPanel, "DEC", false);
		addCheckbox(checkboxPanel, "HEX", true);
*/
		contentPane.add(checkboxPanel, BorderLayout.NORTH);

		status = new JLabel(calc.getAuthor());
		contentPane.add(status, BorderLayout.SOUTH);
		frame.pack();
	}

	/**
	 * Add a button to the button panel.
	 * @param panel The panel to receive the button.
	 * @param buttonText The text for the button.
	 */
	protected void addCheckbox(Container panel, String checkboxText, boolean selected)
	{
		JCheckBox checkbox = new JCheckBox(checkboxText, selected);
		checkbox.addActionListener(this);
		buttonG.add(checkbox);
		panel.add(checkbox);
	}

	protected void addButton(Container panel, String buttonText)
	{
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		panel.add(button);
	}

	/**
	 * An interface action has been performed.
	 * Find out what it was and handle it.
	 * @param event The event that has occurred.
	 */
	public void actionPerformed(ActionEvent event)
	{ 	 

		String command = event.getActionCommand();

		if (command.equals("+")) {
			calc.setOperator('+');
			
		}
		if (command.equals("-")) {
			calc.setOperator('-');
			
		}
		if (command.equals("X")) {
			calc.setOperator('X');
			
		}
		if (command.equals("size")) {
			calc.size();
		}
		else if(command.equals("=")) {
			calc.setDisplayValue(calc.getDisplayValue() + command);
			calc.calculateResult();
			calc.setDisplayValue(calc.getDisplayValue());
			
			
		}
		
		else if(command.equals("Clear")) {
			calc.clear();
			
		}
		else if(command.equals("?")) {
			showInfo();
		}
		else{
			// other numbers, letters, and operators will be added to the String
			calc.setDisplayValue(calc.getDisplayValue() + command);
			
		} 
		redisplay();
	}

	/**
	 * Update the interface display to show the current value of the 
	 * calculator.
	 */
	protected void redisplay() 
	{

		display.setText(calc.getDisplayValue());

	}
	
	/**
	 * Toggle the info display in the calculator's status area between the
	 * author and version information.
	 */
	protected void showInfo()
	{
		if(showingAuthor)
			status.setText(calc.getVersion());
		else
			status.setText(calc.getAuthor());

		showingAuthor = !showingAuthor;
	}

}
