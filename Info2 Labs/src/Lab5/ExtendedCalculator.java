package Lab5;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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

		fillHexaButtons(hexaButtonPanel, new String[] { "A", "B", "C", "D", "E", "F" });
		fillModeOptions(hexaButtonPanel);

		gui.frame.add(hexaButtonPanel, BorderLayout.WEST);
		gui.frame.setSize(600, 300);

	}

	/**
	 * adds buttons with the given button labels to the container and adds a action
	 * to each button
	 * 
	 * @param ctn
	 * @param buttons
	 */
	public void fillHexaButtons(Container ctn, String[] buttons) {
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
	 * Adds the mode options to the given container. When the option is clicked, a
	 * custon ActionListener gets triggerd
	 * 
	 * @param panel
	 * @param label
	 */
	public void fillModeOptions(Container panel) {
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();

				if (cmd.equals(CalculatorMode.DECIMAL.toString())) {
					ExtendedCalculator.this.mode = CalculatorMode.DECIMAL;
				} else if (cmd.equals(CalculatorMode.HEXA.toString())) {
					ExtendedCalculator.this.mode = CalculatorMode.HEXA;
				} else if (cmd.equals(CalculatorMode.OCTAL.toString())) {
					ExtendedCalculator.this.mode = CalculatorMode.OCTAL;
				} else if (cmd.equals(CalculatorMode.BINARY.toString())) {
					ExtendedCalculator.this.mode = CalculatorMode.BINARY;
				}

				ExtendedCalculator.this.setHexaButtonsEnabled();
				ExtendedCalculator.this.setDecimalButtonsEnabled();
				ExtendedCalculator.this.redisplay();

			}
		};

		ButtonGroup g1 = new ButtonGroup();

		JRadioButton decimal = new JRadioButton(CalculatorMode.DECIMAL.toString(), true);
		decimal.addActionListener(action);

		JRadioButton hexa = new JRadioButton(CalculatorMode.HEXA.toString(), false);
		hexa.addActionListener(action);

		JRadioButton octa = new JRadioButton(CalculatorMode.OCTAL.toString(), false);
		octa.addActionListener(action);

		JRadioButton bin = new JRadioButton(CalculatorMode.BINARY.toString(), false);
		bin.addActionListener(action);

		panel.add(decimal);
		panel.add(hexa);
		panel.add(octa);
		panel.add(bin);

		g1.add(octa);
		g1.add(bin);
		g1.add(hexa);
		g1.add(decimal);
	}

	public boolean isDecimalNumber(String command) {
		if (command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3")
				|| command.equals("4") || command.equals("5") || command.equals("6") || command.equals("7")
				|| command.equals("8") || command.equals("9")) {
			return true;
		}
		return false;
	}

	/**
	 * This method adds a new action to each button, that triggers the redisplay
	 * method. In order to "override" the redisplay method that is getting triggerd
	 * by the UserInterface, we have to change the order
	 */
	public void changeButtonAction() {

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();

				if (isDecimalNumber(command)) {
					ExtendedCalculator.this.numberPressed(command);
				} else if (command.equals("+")) {
					ExtendedCalculator.this.engine.plus();
				} else if (command.equals("-")) {
					ExtendedCalculator.this.engine.minus();
				} else if (command.equals("=")) {
					ExtendedCalculator.this.engine.equals();
				} else if (command.equals("C")) {
					ExtendedCalculator.this.engine.clear();
				} else if (command.equals("*")) {
					ExtendedCalculator.this.engine.multiply();
				} else if (command.equals("/")) {
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

	public void setDecimalButtonsEnabled() {
		JPanel contentPane = (JPanel) gui.frame.getContentPane();

		JPanel c = (JPanel) contentPane.getComponent(1);
		for (int i = 0; i < c.getComponentCount(); i++) {
			JButton jb = (JButton) c.getComponent(i);
			if (isDecimalNumber(jb.getText())) {
				if (this.mode == CalculatorMode.BINARY) {
					if (jb.getText().equals("0") || jb.getText().equals("1")) {
						jb.setEnabled(true);
					} else {
						jb.setEnabled(false);
					}
				} else if (this.mode == CalculatorMode.OCTAL) {
					if (jb.getText().equals("8") || jb.getText().equals("9")) {
						jb.setEnabled(false);
					} else {
						jb.setEnabled(true);
					}
				} else {
					jb.setEnabled(true);
				}
			}
		}
	}

	public void setHexaButtonsEnabled() {
		JPanel contentPane = (JPanel) gui.frame.getContentPane();

		JPanel c = (JPanel) contentPane.getComponent(3);
		for (Component button : c.getComponents()) {
			if (button instanceof JButton) {
				button.setEnabled(this.mode.equals(CalculatorMode.HEXA));
			}
		}
	}

	public int hexaToDecimal(String hexa) {
		return Integer.parseInt(hexa, 16);
	}

	public String decimalToHexa(int decimal) {
		return Integer.toHexString(decimal);
	}

	public int octalToDecimal(String octal) {
		return Integer.parseInt(octal, 8);
	}

	public String decimalToOctal(int decimal) {
		return Integer.toOctalString(decimal);
	}

	public int binaryToDecimal(String binary) {
		return Integer.parseInt(binary, 2);
	}

	public String decimalToBinary(int decimal) {
		return Integer.toBinaryString(decimal);
	}

	public void numberPressed(String command) {
		switch (this.mode) {
		case DECIMAL:
			this.engine.numberPressed(Integer.parseInt(command));
			break;
		case HEXA:
			if (engine.buildingDisplayValue) {
				String combinedHexa = decimalToHexa(engine.displayValue) + command;
				engine.displayValue = hexaToDecimal(combinedHexa);
			} else {
				engine.displayValue = hexaToDecimal(command);
				engine.buildingDisplayValue = true;
			}
			break;
		case OCTAL:
			if (engine.buildingDisplayValue) {
				String combinedOctal = decimalToOctal(engine.displayValue) + command;
				engine.displayValue = octalToDecimal(combinedOctal);
			} else {
				engine.displayValue = octalToDecimal(command);
				engine.buildingDisplayValue = true;
			}
			break;
		case BINARY:
			if (engine.buildingDisplayValue) {
				String combinedBinary = decimalToBinary(engine.displayValue) + command;
				engine.displayValue = binaryToDecimal(combinedBinary);
			} else {
				engine.displayValue = binaryToDecimal(command);
				engine.buildingDisplayValue = true;
			}
			break;
		default:
			break;
		}
	}

	public void redisplay() {
		switch (this.mode) {
		case DECIMAL:
			gui.display.setText("" + this.engine.getDisplayValue());
			break;
		case HEXA:
			gui.display.setText("" + decimalToHexa(this.engine.getDisplayValue()).toUpperCase());
			break;
		case OCTAL:
			gui.display.setText("" + decimalToOctal(this.engine.getDisplayValue()));
			break;
		case BINARY:
			gui.display.setText("" + decimalToBinary(this.engine.getDisplayValue()));
			break;
		default:
			break;

		}
	}
	

}
