package Lab5;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ExtendedCalculator extends Calculator {

	CalculatorMode mode;

	public ExtendedCalculator() {
		super();
		this.mode = CalculatorMode.DECIMAL;
		makeHexaPanel();
		changeButtonAction();

	}

	public static void main(String[] args) {
		ExtendedCalculator c = new ExtendedCalculator();
		c.show();
	}

	public void makeHexaPanel() {
		JPanel hexaButtonPanel = new JPanel(new GridLayout(4, 2));

		fillButtons(hexaButtonPanel, new String[] { "A", "B", "C", "D", "E", "F" });
		fillCheckbox(hexaButtonPanel, "hexa");

		gui.frame.add(hexaButtonPanel, BorderLayout.WEST);
		gui.frame.setSize(400, 300);

	}

	/**
	 * adds buttons with the given button labels to the container and adds a action
	 * to each button 
	 * 
	 * @param ctn
	 * @param buttons
	 */
	public void fillButtons(Container ctn, String[] buttons) {

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExtendedCalculator.this.numberPressed(e.getActionCommand());
				ExtendedCalculator.this.redisplay();
			}
		};

		for (String caption : buttons) {

			JButton button = new JButton(caption);
			button.addActionListener(action);
			button.setEnabled(this.mode.equals(CalculatorMode.HEXA));
			ctn.add(button);
		}

	}

	/**
	 * Adds the hexa checkbox to the given container.
	 * When the checkbox is clicked, the 
	 * 
	 * @param panel
	 * @param label
	 */
	public void fillCheckbox(Container panel, String label) {
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				ExtendedCalculator.this.setHexaMode(cb.isSelected());
			}
		};

		JCheckBox cb = new JCheckBox(label, false);
		cb.addActionListener(action);
		panel.add(cb);
	}

	/**
	 * This method adds a new action to each button, that triggers the redisplay method.
	 * In order to "override" the redisplay method that is getting triggerd by the UserInterface,
	 * we have to change the order
	 */
	public void changeButtonAction() {

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String command = e.getActionCommand();

		        if(command.equals("0") ||
		           command.equals("1") ||
		           command.equals("2") ||
		           command.equals("3") ||
		           command.equals("4") ||
		           command.equals("5") ||
		           command.equals("6") ||
		           command.equals("7") ||
		           command.equals("8") ||
		           command.equals("9")) {
		            ExtendedCalculator.this.numberPressed(command);
		        }
		        else if(command.equals("+")) {
		        	ExtendedCalculator.this.engine.plus();
		        }
		        else if(command.equals("-")) {
		        	ExtendedCalculator.this.engine.minus();
		        }
		        else if(command.equals("=")) {
		        	ExtendedCalculator.this.engine.equals();
		        }
		        else if(command.equals("C")) {
		        	ExtendedCalculator.this.engine.clear();
		        }

		        else if(command.equals("*")) {
		        	ExtendedCalculator.this.engine.multiply();
		        }
		        else if(command.equals("/")) {
		        	ExtendedCalculator.this.engine.divide();
		        }
		        
		        ExtendedCalculator.this.redisplay();
			}
		};

		JPanel contentPane = (JPanel) gui.frame.getContentPane();

		JPanel c = (JPanel) contentPane.getComponent(1);
		for (Component button : c.getComponents()) {
			if (button instanceof JButton) {
				ActionListener[] al = ((JButton) button).getActionListeners();
				((JButton) button).removeActionListener(al[0]);
				((JButton) button).addActionListener(action);
			}
		}
	}
	
	public int hexaToDecimal(String hexa) {
		return Integer.parseInt(hexa, 16);
	}

	public String decimalToHexa(int decimal) {
		return Integer.toHexString(decimal);
	}

	public void setHexaMode(boolean isHexa) {
		if (isHexa) {
			this.mode = CalculatorMode.HEXA;
		} else {
			this.mode = CalculatorMode.DECIMAL;
		}
		setHexaButtonsEnabled(isHexa);
		this.redisplay();;
	}

	public void setHexaButtonsEnabled(boolean isHexa) {

		JPanel contentPane = (JPanel) gui.frame.getContentPane();

		JPanel c = (JPanel) contentPane.getComponent(3);
		for (Component button : c.getComponents()) {
			if (button instanceof JButton) {
				button.setEnabled(isHexa);

			}
		}
	}

	public void numberPressed(String command) {
		switch (this.mode) {
		case HEXA:
			if(engine.buildingDisplayValue) {
				String combinedHexa = decimalToHexa(engine.displayValue) + command;
				engine.displayValue = hexaToDecimal(combinedHexa);
			}else {
				engine.displayValue = hexaToDecimal(command);
				engine.buildingDisplayValue = true;
			}
			break;
		case DECIMAL:
			this.engine.numberPressed(Integer.parseInt(command));
			break;
		default:
			break;
		}
	}
	
	public void redisplay() {
		switch (this.mode) {
		case HEXA:
			gui.display.setText("" + decimalToHexa(this.engine.getDisplayValue()));			
			break;
		case DECIMAL:
			gui.display.setText("" + this.engine.getDisplayValue());
			break;
			
		default:
			
			break;
			
		}
	}

}
