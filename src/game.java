
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

    public class game {
        JFrame frame;
        JPanel mainPanel;
        Font buttonFont = new Font("sanserif", Font.BOLD, 55);
        static JButton buttonA1 = new JButton();
        static JButton buttonA2 = new JButton();
        static JButton buttonA3 = new JButton();
        static JButton buttonB1 = new JButton();
        static JButton buttonB2 = new JButton();
        static JButton buttonB3 = new JButton();
        static JButton buttonC1 = new JButton();
        static JButton buttonC2 = new JButton();
        static JButton buttonC3 = new JButton();
        static JTextField result = new JTextField();
        static Thread t;
        static ArrayList<Integer> Moves = new ArrayList<Integer>();
        static ArrayList<JButton> buttons = new ArrayList<JButton>();
        static int computerMove;
        static int [][] player;
        static int[][] computer;
        int countOfMovesPlayer = 0;
        int countOfMovesComputer = 0;
        static int countOfMoves = 0;
        int statusOfPlayer = 0;
        int statusOfComputer = 0;
        public synchronized static void setCountOfMoves() {
            countOfMoves = countOfMoves + 1;
        }
        public synchronized static int getCountOfMoves() {
            return countOfMoves;
        }
        public synchronized static void add (int t) {
            Moves.add(t);
        }
        public static void main(String[] args) {
            new game().buildGUI();
            player = new int[3][3];
            computer = new int[3][3];
            t = new Thread(new ComputerPlayer());
        }
        public void buildGUI() {
            frame = new JFrame("Крестики-нолики");
            mainPanel = new JPanel(new GridLayout(3,3));
            buttonA1.setActionCommand("1");
            buttonA1.addActionListener(new ButtonListener());
            buttons.add(buttonA1);
            mainPanel.add(buttonA1);
            buttonA2.setActionCommand("2");
            buttonA2.addActionListener(new ButtonListener());
            buttons.add(buttonA2);
            mainPanel.add(buttonA2);
            buttonA3.setActionCommand("3");
            buttonA3.addActionListener(new ButtonListener());
            buttons.add(buttonA3);
            mainPanel.add(buttonA3);
            buttonB1.setActionCommand("4");
            buttonB1.addActionListener(new ButtonListener());
            buttons.add(buttonB1);
            mainPanel.add(buttonB1);
            buttonB2.setActionCommand("5");
            buttonB2.addActionListener(new ButtonListener());
            buttons.add(buttonB2);
            mainPanel.add(buttonB2);
            buttonB3.setActionCommand("6");
            buttonB3.addActionListener(new ButtonListener());
            buttons.add(buttonB3);
            mainPanel.add(buttonB3);
            buttonC1.setActionCommand("7");
            buttonC1.addActionListener(new ButtonListener());
            buttons.add(buttonC1);
            mainPanel.add(buttonC1);
            buttonC2.setActionCommand("8");
            buttonC2.addActionListener(new ButtonListener());
            buttons.add(buttonC2);
            mainPanel.add(buttonC2);
            buttonC3.setActionCommand("9");
            buttonC3.addActionListener(new ButtonListener());
            buttons.add(buttonC3);
            mainPanel.add(buttonC3);
            result.setText("Ваш ход!");
            frame.getContentPane().add(mainPanel);
            frame.getContentPane().add(BorderLayout.SOUTH, result);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300,300);
            frame.setVisible(true);
        }
        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                setCountOfMoves();
                int actionCommand = Integer.parseInt(event.getActionCommand());


                if (getCountOfMoves() % 2 != 0) {
                    countOfMovesPlayer++;
                    switch (actionCommand) {
                        case 1: {
                            player[0][0] = 1;
                            buttonA1.setFont(buttonFont);
                            buttonA1.setText("X");
                            buttonA1.setEnabled(false);
                            break;
                        }
                        case 2: {
                            player[0][1] = 1;
                            buttonA2.setFont(buttonFont);
                            buttonA2.setText("X");
                            buttonA2.setEnabled(false);
                            break;
                        }
                        case 3: {
                            player[0][2] = 1;
                            buttonA3.setFont(buttonFont);
                            buttonA3.setText("X");
                            buttonA3.setEnabled(false);
                            break;
                        }
                        case 4: {
                            player[1][0] = 1;
                            buttonB1.setFont(buttonFont);
                            buttonB1.setText("X");
                            buttonB1.setEnabled(false);
                            break;
                        }
                        case 5: {
                            player[1][1] = 1;
                            buttonB2.setFont(buttonFont);
                            buttonB2.setText("X");
                            buttonB2.setEnabled(false);
                            break;
                        }
                        case 6: {
                            player[1][2] = 1;
                            buttonB3.setFont(buttonFont);
                            buttonB3.setText("X");
                            buttonB3.setEnabled(false);
                            break;
                        }
                        case 7:{
                            player[2][0] = 1;
                            buttonC1.setFont(buttonFont);
                            buttonC1.setText("X");
                            buttonC1.setEnabled(false);
                            break;
                        }
                        case 8: {
                            player[2][1] = 1;
                            buttonC2.setFont(buttonFont);
                            buttonC2.setText("X");
                            buttonC2.setEnabled(false);
                            break;
                        }
                        case 9: {
                            player[2][2] = 1;
                            buttonC3.setFont(buttonFont);
                            buttonC3.setText("X");
                            buttonC3.setEnabled(false);
                            break;
                        }
                    }
                    add(actionCommand);

                    if (countOfMovesPlayer >= 3) {
                        if (statusOfPlayer == 0) {
                            statusOfPlayer = check(player);
                        }
                    }
                }

                if (statusOfPlayer == 0 && countOfMovesPlayer < 5)t.run();
                if (statusOfPlayer == 1) {
                    result.setText("Поздравляем! Вы выиграли!");
                    for (JButton b : buttons) {
                        b.setEnabled(false);
                    }
                } else if (statusOfPlayer == 0 && countOfMovesPlayer >=5) {
                    result.setText("Боевая ничья!");
                    for (JButton b : buttons) {
                        b.setEnabled(false);
                    }
                }
            }
        }
        public int check(int[][] a) {
            int countRow = 0;
            int countColumn = 0;
            int countDiagonal1 = 0;
            int countDiagonal2 = 0;
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    if (a[i][j] == 1) countRow++;
                    if (a[j][i] == 1) countColumn++;
                }
                if (countRow == 3 || countColumn == 3) {
                    return 1;
                }
                countRow = 0;
                countColumn = 0;
            }
            for (int i = 0, j = 0; i < a.length ; i++) {
                if (a[i][j] == 1) countDiagonal1++;
                j++;
            }
            if (countDiagonal1 == 3) {
                return 1;
            }
            for (int i = 0, j = 2; i < a.length ; i++) {
                if (a[i][j] == 1) countDiagonal2++;
                j--;
            }
            if (countDiagonal2 == 3) {
                return 1;
            }
            return 0;
        }
    }
    class ComputerPlayer extends game implements Runnable {
        public void run() {
            setCountOfMoves();

            if (getCountOfMoves() % 2 == 0) {
                countOfMovesComputer++;
                computerMove =(int) (Math.random()*10);
                while(computerMove == 0 || Moves.contains(computerMove)) {
                    if (countOfMovesComputer >=5) break;
                    computerMove =(int) (Math.random()*10);
                }
                add(computerMove);

                switch (computerMove) {
                    case 1: {
                        computer[0][0] = 1;
                        buttonA1.setFont(buttonFont);
                        buttonA1.setText("O");
                        buttonA1.setEnabled(false);
                        break;
                    }
                    case 2: {
                        computer[0][1] = 1;
                        buttonA2.setFont(buttonFont);
                        buttonA2.setText("O");
                        buttonA2.setEnabled(false);
                        break;
                    }
                    case 3: {
                        computer[0][2] = 1;
                        buttonA3.setFont(buttonFont);
                        buttonA3.setText("O");
                        buttonA3.setEnabled(false);
                        break;
                    }
                    case 4: {
                        computer[1][0] = 1;
                        buttonB1.setFont(buttonFont);
                        buttonB1.setText("O");
                        buttonB1.setEnabled(false);
                        break;
                    }
                    case 5: {
                        computer[1][1] = 1;
                        buttonB2.setFont(buttonFont);
                        buttonB2.setText("O");
                        buttonB2.setEnabled(false);
                        break;
                    }
                    case 6: {
                        computer[1][2] = 1;
                        buttonB3.setFont(buttonFont);
                        buttonB3.setText("O");
                        buttonB3.setEnabled(false);
                        break;
                    }
                    case 7:{
                        computer[2][0] = 1;
                        buttonC1.setFont(buttonFont);
                        buttonC1.setText("O");
                        buttonC1.setEnabled(false);
                        break;
                    }
                    case 8: {
                        computer[2][1] = 1;
                        buttonC2.setFont(buttonFont);
                        buttonC2.setText("O");
                        buttonC2.setEnabled(false);
                        break;
                    }
                    case 9: {
                        computer[2][2] = 1;
                        buttonC3.setFont(buttonFont);
                        buttonC3.setText("O");
                        buttonC3.setEnabled(false);
                        break;
                    }

                }
                if (countOfMovesComputer >= 3) {
                    if (statusOfComputer == 0) {
                        statusOfComputer = check(computer);
                    }
                }
            }
            if (statusOfComputer == 1) {
                result.setText("Не унывайте. На этот раз сильнее компьтер.");
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
            } else if (statusOfComputer == 0 && countOfMovesComputer >=5) {
                result.setText("Боевая ничья!");
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
            }
        }
    }

