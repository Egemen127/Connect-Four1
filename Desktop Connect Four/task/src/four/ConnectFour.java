package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConnectFour extends JFrame {
    ArrayList<JButton> buttonList = new ArrayList<>();

    public ConnectFour() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(6,7));
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        final boolean[] xTurn = {true};
        JButton reset = new JButton("Reset");
        reset.setName("ButtonReset");
        reset.addActionListener(e -> {
            buttonList.forEach(btn -> {
                btn.setEnabled(true);
                btn.setText(" ");
                btn.setBackground(Color.ORANGE);
                xTurn[0] = true;
            });
        });
        jp.add(reset);
        setTitle("Connect Four");

        for (int i = 6; i > 0; i--) {
            for (char c = 'A'; c <= 'G'; c++) {
                JButton b = new JButton(" ");
                b.setName("Button" + c + String.valueOf(i));
                b.setFocusPainted(false);
                b.setBackground(Color.ORANGE);
                buttonList.add(b);
                b.addActionListener(e ->{
                        int row = Integer.parseInt(b.getName().substring(7,8));
                        char col = b.getName().charAt(6);
                        char finalCol = col;
                        Object[] column = buttonList.stream()
                                .filter(h -> finalCol == h.getName().charAt(6)).toArray();
                        for (int j = column.length-1; j >= 0; j--) {
                            JButton button = (JButton) column[j];
                            if(button.getText().equals(" ")) {
                                col = button.getName().charAt(6);
                                row = Integer.parseInt(button.getName().substring(7,8));
                                if(xTurn[0]) {
                                    button.setText("X");
                                    checkWinner("X", row, col);
                                } else {
                                    button.setText("O");
                                    checkWinner("O", row, col);
                                }
                                xTurn[0] = !xTurn[0];
                                break;
                            }
                        }


                        }
                );
                jp1.add(b);

            }
            add(jp1);
            add(jp);


        }
        revalidate();
        repaint();
    }

    private void checkWinner(String o, int row, char col) {
        // Checking rows
        ArrayList<JButton> rowButton = this.buttonList.stream()
                .filter(e -> Integer.parseInt(e.getName().substring(7,8)) == row)
                .collect(Collectors.toCollection(ArrayList::new));
        int cnt = 0;
        for (char j = 'A'; j <='D'; j++){
            char finalJ = j;
            for (int i = 0; i < 4; i++) {
                int finalI = i;
                JButton b = (JButton) rowButton.stream().filter(e -> e.getName().
                        charAt(6) == (char) (finalJ + finalI)).toArray()[0];
                if (b.getText().equals(o)) {
                    cnt++;
                }
            }
            if (cnt == 4) {
            rowButton.stream().filter(i -> i.getName().charAt(6) >= finalJ && i.getName().charAt(6) <= finalJ + 3).forEach(e -> e.setBackground(Color.GREEN));
            System.out.println(o + " Wins");
            buttonList.forEach(e -> e.setEnabled(false));
            } else {
            cnt = 0;
            }
        }
        //Checking columns
        ArrayList<JButton> columnButton = this.buttonList.stream()
                .filter(e -> e.getName().charAt(6) == col)
                .collect(Collectors.toCollection(ArrayList::new));
        int colCnt = 0;
        for (int j = 1; j <= 3; j++){
            int finalJ = j;
            for (int i = 0; i < 4; i++) {
                int finalI = i;
                JButton b = (JButton) columnButton.stream().filter(e -> Integer.parseInt(e.getName().
                        substring(7,8)) == finalJ + finalI).toArray()[0];
                if (b.getText().equals(o)) {
                    colCnt++;
                }
            }
            if (colCnt == 4) {
                columnButton.stream().filter(i -> Integer.parseInt(i.getName().substring(7,8)) >= finalJ && Integer.parseInt(i.getName().substring(7,8)) <= finalJ + 3).forEach(e -> e.setBackground(Color.GREEN));
                System.out.println(o + " Wins");
                buttonList.forEach(e -> e.setEnabled(false));
            } else {
                colCnt = 0;
            }
        }
        //Checking Diagonals
        ArrayList<JButton> diagonal = buttonList.stream()
                .filter(i -> Math.abs(row - Integer.parseInt(i.getName().substring(7,8))) == Math.abs(col - i.getName().charAt(6)) && (row - Integer.parseInt(i.getName().substring(7,8)) <= 4))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<JButton> diagonal1 = diagonal.stream()
                .filter(i -> (col <= i.getName().charAt(6) && row <= Integer.parseInt(i.getName().substring(7,8)))
                        || (col >= i.getName().charAt(6) && row >= Integer.parseInt(i.getName().substring(7,8))))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<JButton> diagonal2 = diagonal.stream()
                .filter(i -> (col >= i.getName().charAt(6) && row <= Integer.parseInt(i.getName().substring(7,8)))
                        || (col <= i.getName().charAt(6) && row >= Integer.parseInt(i.getName().substring(7,8))))
                .collect(Collectors.toCollection(ArrayList::new));
        while(diagonal1.size() >= 4) {
            int dCnt = 0;
            for (int i = 0; i < 4; i++) {

                if (o.equals(diagonal1.get(i).getText())){
                    dCnt++;
                }
            }
            if (dCnt == 4) {
                System.out.println(o + " Wins");
                buttonList.forEach(e-> e.setEnabled(false));
                for(int j = 0; j < 4; j++) {
                    diagonal1.get(j).setBackground(Color.GREEN);
                }
                break;
            } else {
                diagonal1.remove(0);
                dCnt = 0;
            }
        }
        while(diagonal2.size() >= 4) {
            int dCnt = 0;
            for (int i = 0; i < 4; i++) {
                if (o.equals(diagonal2.get(i).getText())){
                    dCnt++;
                }
            }
            if (dCnt == 4) {
                System.out.println(o + " Wins");
                buttonList.forEach(e-> e.setEnabled(false));
                for(int j = 0; j < 4; j++) {
                    diagonal2.get(j).setBackground(Color.GREEN);
                }
                break;
            } else {
                diagonal2.remove(0);
                dCnt = 0;
            }
        }
    }
}