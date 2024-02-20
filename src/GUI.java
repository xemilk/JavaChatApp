import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {
    private JTextField textField;
    private JTextArea chatArea;
    private JButton sendButton;
    private FileReader fileReader;
    BufferedReader bufferedReader = null;

    public GUI() {

   
        setTitle("Local ChatApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Bestandteile des Fensters
        textField = new JTextField(30);
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        sendButton = new JButton("Senden");
        sendButton.addActionListener(this);

        
        // Chat-Bereich
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        contentPane.add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
  

       try {
            fileReader = new FileReader("chatlog.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                chatArea.append(line + "\n"); // Füge jede Zeile der Datei zur JTextArea hinzu
            }
        } catch (IOException e) {
          e.printStackTrace();}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            String message = textField.getText();
            chatArea.append("[user] " + message + "\n");
            textField.setText("");
        }
    }

   
}
