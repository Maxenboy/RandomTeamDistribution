package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;

import Program.RandomTeams;

public class mainFrame extends JFrame {
	private KeyStroke ctrlz = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit
			.getDefaultToolkit().getMenuShortcutKeyMask());
	private RandomTeams randomteams;
	private JScrollPane scroll;
	private JButton generate, regret, gender;
	private JTextArea area;
	private CustomDialog teams;
	private static final long serialVersionUID = -5230500736967473638L;
	private JPanel panel;
	private JSplitPane splitpane;

	public mainFrame(String title) {
		super(title);
		setLayout(new BorderLayout());
		area = new JTextArea();
		area.setFont(area.getFont().deriveFont(20f));
		scroll = new JScrollPane(area);
		add(scroll, BorderLayout.CENTER);
		generate = new JButton("Generate");
		generate.setFont(new Font("Dialog", Font.BOLD, 20));
		regret = new JButton("Regret the amount of teams");
		gender= new JButton("Gender");
		panel = new JPanel();
		panel.add(gender);
		add(generate, BorderLayout.SOUTH);
		add(regret, BorderLayout.NORTH);
		add(panel,BorderLayout.EAST);
		teams = new CustomDialog(this, "# Teams",
				"Enter the number of participating teams");
		teams.setLocationRelativeTo(null);
		teams.setVisible(true);
		area.requestFocusInWindow();

		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!area.getText().equals("")&&!area.getText().equals(" ")) {
					if(!area.getText().startsWith(" ")){
					String[] s = area.getText().split("[[ +&&\n]||[ ]||[\n]||[\t]]");
					randomteams = new RandomTeams(s, Integer.parseInt(teams
							.getInput()));

					generateFrame frame = new generateFrame("Result",
							randomteams.generate());
					frame.setVisible(true);
					frame.setSize(600, 600);
					frame.setLocationRelativeTo(null);
					}else{
						JOptionPane.showMessageDialog(area,
								"You can't start with a space");
					}
				} else {
					JOptionPane.showMessageDialog(area,
							"You need to specify the contestants");

				}

			}

		});
		regret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teams.setVisible(true);

			}

		});
		gender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitpane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				
			}

		});
		ActionMap actionMap = new ActionMapUIResource();
		actionMap.put("action_print", new AbstractAction() {

			private static final long serialVersionUID = 5217184895784554242L;

			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
			}
		});

		InputMap keyMap = new ComponentInputMap(generate);
		keyMap.put(ctrlz, "action_print");

		SwingUtilities.replaceUIActionMap(generate, actionMap);
		SwingUtilities.replaceUIInputMap(generate,
				JComponent.WHEN_IN_FOCUSED_WINDOW, keyMap);

	}

}
