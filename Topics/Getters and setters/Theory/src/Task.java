// You can experiment here, it won’t be checked

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task extends JFrame {

  public Task() {
    super("Hello App");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 300);
    JLabel nameLabel = new JLabel();
    nameLabel.setText("Your Name");
    nameLabel.setBounds(40, 20, 100, 30);
    add(nameLabel);

    JTextField nameTextField = new JTextField();
    nameTextField.setBounds(160,20, 100,30);
    add(nameTextField);
    JPanel greenPanel = new JPanel();
    greenPanel.setBounds(40,150,220,70);
    greenPanel.setLayout(new BorderLayout());
    greenPanel.setBackground(Color.GREEN);
    add(greenPanel);
    JLabel helloLabel = new JLabel("Hello");
    helloLabel.setBounds(50,20, 100,30);
    helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
    helloLabel.setVerticalAlignment(SwingConstants.CENTER);

    Font font = new Font("Courier", Font.BOLD,12);
    helloLabel.setFont(font);
    helloLabel.setFont(helloLabel.getFont().deriveFont(16f));

    greenPanel.add(helloLabel); // adding label to the panel
    JButton acceptButton = new JButton("Accept");
    acceptButton.setBounds(100, 70, 100, 30);
    add(acceptButton);
    acceptButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String helloText = "Hello";
        String name = nameTextField.getText();
        if (name != null && name.trim().length() > 0) {
          helloText += String.format(", %s", name);
        }
        helloLabel.setText(helloText);
      }
    });

    setLocationRelativeTo(null);
    setLayout(null);
    setVisible(true);
  }

  public static void main(final String[] args) {
    new Task();
  }
}
