package GUI;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;

public class generateFrame extends JFrame {
	private KeyStroke ctrlp = KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit
			.getDefaultToolkit().getMenuShortcutKeyMask());
	private JButton print;
	private JTextPane area;
	private static final long serialVersionUID = -5230500736967473638L;

	public generateFrame(String title, String text) {
		super(title);
		setLayout(new BorderLayout());
		area = new JTextPane();
		area.setEditable(false);
		area.setFont(area.getFont().deriveFont(20f));
		add(new JScrollPane(area), BorderLayout.CENTER);
		area.setText(text);
		area.setSelectionStart(0);
		area.setSelectionEnd(0);
		print = new JButton("Print");
		add(print, BorderLayout.SOUTH);

		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					area.print();
				} catch (PrinterException e1) {
					JOptionPane.showMessageDialog(area, e1);
				}

			}

		});
		ActionMap actionMap = new ActionMapUIResource();
		actionMap.put("action_print", new AbstractAction() {

			private static final long serialVersionUID = 5217184895784554242L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					area.print();
				} catch (PrinterException e1) {
					JOptionPane.showMessageDialog(area, e1);
				}
			}
		});

		InputMap keyMap = new ComponentInputMap(print);
		keyMap.put(ctrlp, "action_print");

		SwingUtilities.replaceUIActionMap(print, actionMap);
		SwingUtilities.replaceUIInputMap(print,
				JComponent.WHEN_IN_FOCUSED_WINDOW, keyMap);

	}

}
