import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JFrameView extends JFrame implements ActionListener {

  private JLabel echoLabel;
  private JTextField textField;
  private JButton echoButton;
  private JButton exitButton;
  public JFrameView() {
    super();

    setSize(300, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    echoLabel = new JLabel("Default");
    this.add(echoLabel);

    textField = new JTextField(10);
    this.add(textField);

    echoButton = new JButton("Echo");
    echoButton.setActionCommand("echo");
    echoButton.addActionListener(this);
    this.add(echoButton);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("exit");
    this.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
      case "exit":
        System.exit(0);
        return;
      case "echo":
        break;
    }
  }
}
