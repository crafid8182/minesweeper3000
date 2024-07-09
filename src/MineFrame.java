import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineFrame extends JFrame implements ActionListener {

	JFrame frame;
	JLabel[][] labels;
	public JButton[][] buttons;
	Grid myGrid = new Grid();

	public MineFrame() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Minesweeper 3000");

		// frame.pack();
		// frame.setSize(500, 500);
		// frame.setLocationRelativeTo(null);
		// frame.setVisible(true);

		// frame.setSize(500, 500);
		// frame.setResizable(false);

		// JPanel panels = new JPanel();
		// panels.setSize(50, 50);
		// panels.setBackground(Color.red);
		// frame.add(panels);

		// Grid myGrid = new Grid();

		// setLayout(new GridBagLayout());

		frame.setLayout(new GridLayout(myGrid.getNumRows(), myGrid.getNumColumns()));

		int rows = myGrid.getNumRows();
		int columns = myGrid.getNumColumns();

		buttons = new JButton[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				// frame.add(new JButton(Integer.toString(i) + " " + Integer.toString(j)));
				// buttons[i][j] = new JButton(Integer.toString(i) + " " + Integer.toString(j));
				buttons[i][j] = new JButton();

				buttons[i][j].setSize(100, 50);
				buttons[i][j].addActionListener(this);
				frame.add(buttons[i][j]);
			}
		}
		// System.out.println(myGrid.getNumRows());
		// System.out.println();

		/*
		 * labels = new JLabel[rows][columns];
		 * 
		 * for (int i=0; i<rows; i++) { for (int j=0; j<columns;j++){
		 * 
		 * //labels[i][j] = new JLabel(Integer.toString(myGrid.getCountAtLocation(i,
		 * j))); //labels[i][j].setText(Integer.toString(myGrid.getCountAtLocation(i,
		 * j))); //frame.add(labels[i][j]); labels[i][j] = new
		 * JLabel(Boolean.toString(myGrid.isBombAtLocation(i, j)));
		 * labels[i][j].setText(Boolean.toString(myGrid.isBombAtLocation(i, j)));
		 * frame.add(labels[i][j]); } }
		 */

		// frame.pack();
		// frame.setSize(500,500);
		frame.pack();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void removeButton() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Grid myGrid = new Grid();

		int rows = myGrid.getNumRows();
		int columns = myGrid.getNumColumns();

		int[][] counts = new int[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				counts[i][j] = myGrid.getCountAtLocation(i, j);

			}
		}

		boolean[][] bombs = new boolean[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				bombs[i][j] = myGrid.isBombAtLocation(i, j);

			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				if (e.getSource() == buttons[i][j]) {
					// System.out.println(i +""+ j);
					// System.out.println(myGrid.getCountAtLocation(i, j));

					buttons[i][j].setText(Integer.toString(counts[i][j]));
					// buttons[i][j].setText(Boolean.toString(myGrid.isBombAtLocation(i, j)) + "" +
					// Integer.toString(counts[i][j]));
					buttons[i][j].setEnabled(false);

					if (bombs[i][j]) {

						int ans = JOptionPane.showConfirmDialog(null, "Do you want to start over?", "LOSE",
								JOptionPane.YES_NO_OPTION);

						// yes is 0 no is 1

						if (ans == 0) {
							System.out.println();
							frame.dispose();
							new MineFrame();
						} else {
							dispose();
							frame.dispose();
						}

//						JFrame lose = new JFrame();
//						lose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//						lose.setTitle("LOSE");
//						
//						lose.pack();
//						lose.setSize(200, 100);
//						lose.setLocationRelativeTo(null);
//
//						
//						lose.setVisible(true);
//						//lose.setSize(200, 100);
//						
//						JLabel loser = new JLabel();
//						loser.setText("You Lost!");
//						loser.setHorizontalAlignment(JLabel.CENTER);
//						
//						lose.add(loser);

					}

					if (counts[i][j] == 0) {

						boolean notLeftSide = j > 0;
						boolean notRightSide = j < (myGrid.getNumColumns() - 1);

						if (i > 0) {
							buttons[i-1][j].setText(Integer.toString(counts[i-1][j]));
							buttons[i-1][j].setEnabled(false);
							// Above

							if (notLeftSide) {
								buttons[i-1][j-1].setText(Integer.toString(counts[i-1][j-1]));
								buttons[i-1][j-1].setEnabled(false);
								// Top Left
							}

							if (notRightSide) {
								buttons[i-1][j+1].setText(Integer.toString(counts[i-1][j+1]));
								buttons[i-1][j+1].setEnabled(false);
								// Top Right
							}
						}

						// Check for bombs adjacent
						if (notLeftSide) {
							buttons[i][j-1].setText(Integer.toString(counts[i][j-1]));
							buttons[i][j-1].setEnabled(false);
							// Left

						}

						if (notRightSide) {
							buttons[i][j+1].setText(Integer.toString(counts[i][j+1]));
							buttons[i][j+1].setEnabled(false);
							// Right
						}

						// Check for bombs underneath
						if (i < (myGrid.getNumRows() - 1)) {
							buttons[i+1][j].setText(Integer.toString(counts[i+1][j]));
							buttons[i+1][j].setEnabled(false);
							// Below

							if (notLeftSide) {
								buttons[i+1][j-1].setText(Integer.toString(counts[i+1][j-1]));
								buttons[i+1][j-1].setEnabled(false);
								// Bottom Left
							}

							if (notRightSide) {
								buttons[i+1][j+1].setText(Integer.toString(counts[i+1][j+1]));
								buttons[i+1][j+1].setEnabled(false);
								// Bottom Right
							}

						}
					}

					// frame.remove(buttons[i][j]);

				}

			}
		}

	}
}