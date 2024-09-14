import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    JButton[][] buttons = new JButton[3][3];
    char currentPlayer = 'X';

    public TicTacToe() {

	// Layout
        setTitle("Tic-Tac-Toe Game");
        setSize(300, 300);
        setLayout(null);

        // Buttons
        buttons[0][0] = new JButton("");
        buttons[0][0].setBounds(0, 0, 100, 100);
        add(buttons[0][0]);

        buttons[0][1] = new JButton("");
        buttons[0][1].setBounds(100, 0, 100, 100);
        add(buttons[0][1]);

        buttons[0][2] = new JButton("");
        buttons[0][2].setBounds(200, 0, 100, 100);
        add(buttons[0][2]);

        buttons[1][0] = new JButton("");
        buttons[1][0].setBounds(0, 100, 100, 100);
        add(buttons[1][0]);

        buttons[1][1] = new JButton("");
        buttons[1][1].setBounds(100, 100, 100, 100);
        add(buttons[1][1]);

        buttons[1][2] = new JButton("");
        buttons[1][2].setBounds(200, 100, 100, 100);
        add(buttons[1][2]);

        buttons[2][0] = new JButton("");
        buttons[2][0].setBounds(0, 200, 100, 100);
        add(buttons[2][0]);

        buttons[2][1] = new JButton("");
        buttons[2][1].setBounds(100, 200, 100, 100);
        add(buttons[2][1]);

        buttons[2][2] = new JButton("");
        buttons[2][2].setBounds(200, 200, 100, 100);
        add(buttons[2][2]);

        // Set common font and add action listeners
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().equals("")) {
            clickedButton.setText(String.valueOf(currentPlayer));
            if (checkWinner()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().equals("")) return true;

            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                !buttons[0][i].getText().equals("")) return true;
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) return true;

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) return true;

        return false;
    }


    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.setVisible(true);
    }
}

