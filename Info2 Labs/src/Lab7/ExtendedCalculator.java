package Lab7;

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


	public ExtendedCalculator() {
		super();
		this.engine.mode = CalculatorMode.DECIMAL;
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
			button.setEnabled(this.engine.mode.equals(CalculatorMode.HEXA));
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
					ExtendedCalculator.this.engine.mode = CalculatorMode.DECIMAL;
				} else if (cmd.equals(CalculatorMode.HEXA.toString())) {
					ExtendedCalculator.this.engine.mode = CalculatorMode.HEXA;
				} else if (cmd.equals(CalculatorMode.OCTAL.toString())) {
					ExtendedCalculator.this.engine.mode = CalculatorMode.OCTAL;
				} else if (cmd.equals(CalculatorMode.BINARY.toString())) {
					ExtendedCalculator.this.engine.mode = CalculatorMode.BINARY;
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

				if (ExtendedCalculator.this.isDecimalNumber(command)) {
					ExtendedCalculator.this.numberPressed(command);
				} else {
					try {
						switch (command) {
						case "+":
							ExtendedCalculator.this.engine.operatorPressed('+');
							break;
						case "-":
							ExtendedCalculator.this.engine.operatorPressed('-');
							break;
						case "=":
							ExtendedCalculator.this.engine.operatorPressed('=');
							break;
						case "C":
							ExtendedCalculator.this.engine.clear();
							break;
						case "*":
							ExtendedCalculator.this.engine.operatorPressed('*');
							break;
						case "/":
							ExtendedCalculator.this.engine.operatorPressed('/');
							break;
						case "(":
							ExtendedCalculator.this.engine.bracketPressed('(');
							break;
						case ")":
							ExtendedCalculator.this.engine.bracketPressed(')');
							break;
						default:
							ExtendedCalculator.this.engine.keySequenceError();
							break;
						}
					} catch (Exception e2) {
						System.out.println("err");
						ExtendedCalculator.this.engine.keySequenceError();
					}
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
				if (this.engine.mode == CalculatorMode.BINARY) {
					if (jb.getText().equals("0") || jb.getText().equals("1")) {
						jb.setEnabled(true);
					} else {
						jb.setEnabled(false);
					}
				} else if (this.engine.mode == CalculatorMode.OCTAL) {
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
				button.setEnabled(this.engine.mode.equals(CalculatorMode.HEXA));
			}
		}
	}


	//translates decimal infix 
	public String parseInfix(String infix, int radix) {
		//System.out.println("infix: "+infix);
		char[] chars = infix.toCharArray();
		String result = "";
		
		for(int i = 0; i<chars.length;i++) {
			//check if char is digit
			
			
			if(this.engine.p.isOperand(chars[i])) {
				String number = String.valueOf(chars[i]);
				
				//check if also next char is digit -> for hexa a-f
				if(i<chars.length-1 &&  this.engine.p.isOperand(chars[i+1])) {
					number += String.valueOf(chars[i+1]);
					i++;
				}
				
				switch (radix) {
				case 16:
					result += Integer.toHexString(Integer.parseInt(number));
					break;
				case 8:
					result += Integer.toOctalString(Integer.parseInt(number));
					break;
				case 2:
					result += Integer.toBinaryString(Integer.parseInt(number));
					break;

				default:
					break;
				}
				
				
			}else {
				result += chars[i];
			}
		}
		return result;
	}

	public void numberPressed(String command) {
		switch (this.engine.mode) {
		case DECIMAL:
			this.engine.numberPressed(Integer.parseInt(command));
			break;
		case HEXA:
			this.engine.numberPressed(Integer.parseInt(command, 16));
			break;

		case OCTAL:
			this.engine.numberPressed(Integer.parseInt(command, 8));
			break;
		case BINARY:
			this.engine.numberPressed(Integer.parseInt(command, 2));
			break;
		default:
			break;
		}
	}

	public void redisplay() {
		switch (this.engine.mode) {
		case DECIMAL:
			gui.display.setText("" + this.engine.getDisplayValue());
			break;
		case HEXA:
			gui.display.setText("" + this.parseInfix(this.engine.getDisplayValue(), 16));
			break;
		case OCTAL:
			gui.display.setText("" + this.parseInfix(this.engine.getDisplayValue(), 8));
			break;
		case BINARY:
			gui.display.setText("" + this.parseInfix(this.engine.getDisplayValue(), 2));
			break;
		default:
			break;

		}
	}

}
