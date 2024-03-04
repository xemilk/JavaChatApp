
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class ChattingScreen extends javax.swing.JFrame {


  private int serverPort;
  public String text;
  public String messageText;
  public boolean newMessage = false;




  public ChattingScreen(){
    initComponents();
    String path = "chat_ico.png";
    setIconImage(new ImageIcon(path).getImage());
setVisible(true);
  }

  public boolean isDisposed() {
    return !isVisible(); // If not visible, it's disposed
  }

  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    backgroundPane = new javax.swing.JLayeredPane();
    sendButton = new javax.swing.JButton();
    inputTextfield = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextPane1 = new javax.swing.JTextPane();
     text = inputTextfield.getText();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Username");
    setAlwaysOnTop(true);
    setBackground(new java.awt.Color(94, 136, 252));
    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    setFont(new java.awt.Font("JetBrainsMono NFP", 0, 10)); // NOI18N
    setMinimumSize(new java.awt.Dimension(350, 500));
    setName("LogInFrame"); // NOI18N
    setSize(new java.awt.Dimension(350, 500));

    backgroundPane.setBackground(java.awt.Color.white);
    backgroundPane.setInheritsPopupMenu(true);
    backgroundPane.setMinimumSize(new java.awt.Dimension(350, 500));
    backgroundPane.setOpaque(true);
    backgroundPane.setPreferredSize(new java.awt.Dimension(350, 500));

    sendButton.setBackground(new java.awt.Color(94, 136, 252));
    sendButton.setFont(new java.awt.Font("JetBrainsMonoNL NFP", 1, 12)); // NOI18N
    sendButton.setForeground(new java.awt.Color(255, 255, 255));
    sendButton.setText("Senden");
    sendButton.setAutoscrolls(true);
    sendButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 136, 252), 5, true));
    sendButton.setBorderPainted(false);
    sendButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    sendButton.setDoubleBuffered(true);
    sendButton.setName(""); // NOI18N
    sendButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        sendButtonActionPerformed(evt);
      }
    });
    sendButton.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        sendButtonKeyTyped(evt);
      }
    });

    inputTextfield.setFont(new java.awt.Font("JetBrainsMono NFP Light", 0, 12)); // NOI18N
    inputTextfield.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 136, 252), 1, true));
    inputTextfield.setMargin(new java.awt.Insets(2, 6, 2, 0));
    inputTextfield.setOpaque(true);
    inputTextfield.setPreferredSize(new java.awt.Dimension(20, 20));
    inputTextfield.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        inputTextfieldActionPerformed(evt);
      }
    });
    inputTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        inputTextfieldKeyPressed(evt);
      }

      public void keyTyped(java.awt.event.KeyEvent evt) {
        inputTextfieldKeyTyped(evt);
      }
    });

    jLabel3.setIcon(
        new javax.swing.ImageIcon("chat_small.png")); // NOI18N
    jLabel3.setAutoscrolls(true);

    jScrollPane1.setBorder(null);
    jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.setToolTipText("");
    jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    jScrollPane1.setAutoscrolls(true);
    jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

    jTextPane1.setEditable(false);
    jTextPane1.setBackground(new java.awt.Color(255, 255, 255));
    jTextPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(94, 136, 252), 2, true));
    jTextPane1.setFont(new java.awt.Font("JetBrainsMono NFP", 0, 12)); // NOI18N
    jTextPane1.setCaretColor(new java.awt.Color(153, 204, 255));
    jTextPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    jTextPane1.setDoubleBuffered(true);
    jTextPane1.setDragEnabled(true);
    jTextPane1.setFocusTraversalPolicyProvider(true);
    jTextPane1.setMinimumSize(new java.awt.Dimension(338, 435));
    jTextPane1.setName(""); // NOI18N
    jScrollPane1.setViewportView(jTextPane1);

    backgroundPane.setLayer(sendButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
    backgroundPane.setLayer(inputTextfield, javax.swing.JLayeredPane.DEFAULT_LAYER);
    backgroundPane.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
    backgroundPane.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout backgroundPaneLayout = new javax.swing.GroupLayout(backgroundPane);
    backgroundPane.setLayout(backgroundPaneLayout);
    backgroundPaneLayout.setHorizontalGroup(
        backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPaneLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap()));
    backgroundPaneLayout.setVerticalGroup(
        backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPaneLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addGroup(backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPaneLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputTextfield, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(backgroundPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(9, 9, 9)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE));

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>

  private void inputTextfieldActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

   private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // Methode für das Aktionsevent des Senden-Buttons
    if (inputTextfield.getText().length() >= 1) {
        messageText = inputTextfield.getText(); // Speichern der eingegebenen Nachricht
        setText(inputTextfield.getText());
        inputTextfield.setText(""); // Zurücksetzen des Textfelds nach dem Senden der Nachricht
    }
}

public String getMessageText() {
  return messageText;
}

public void setMessageText(String messageText) {
  this.messageText = messageText;
}

public void displayReceivedMessage(String message) {
  jTextPane1.setText(jTextPane1.getText() + message + "\n");
}

public String waitForMessageToSend() {
  while (messageText == null || messageText.isEmpty()) {
      try {
          Thread.sleep(100); // Warten auf eine Nachricht, die gesendet werden soll
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
  String messageToSend = messageText;
  messageText = null; // Zurücksetzen der Nachricht
  return messageToSend;
}

  private void sendButtonKeyTyped(java.awt.event.KeyEvent evt) {
    // TODO add your handling code here:
  }

  private void inputTextfieldKeyPressed(java.awt.event.KeyEvent evt) {
    // TODO add your handling code here:

  }

  private void inputTextfieldKeyTyped(java.awt.event.KeyEvent evt) {
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
      String text = inputTextfield.getText();
      jTextPane1.setText(jTextPane1.getText() + text + "\n");
      inputTextfield.setText("");
    }
    // TODO add your handling code here:
  }



  public static void main(String args[]) {

  }



  public void setText(String message){
    jTextPane1.setText(jTextPane1.getText() + message + "\n");
    inputTextfield.setText("");
    messageText = message;
  }

  public String getText() {
    if (newMessage) { // Ändern Sie hier von newMessage = true zu newMessage
        newMessage = false; // Setzen Sie newMessage auf false, nachdem die Nachricht gelesen wurde
        return inputTextfield.getText();
    }
    return null;
}


  // Variables declaration - do not modify
  private javax.swing.JLayeredPane backgroundPane;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JTextPane jTextPane1;
  private javax.swing.JButton sendButton;
  private javax.swing.JTextField inputTextfield;
  // End of variables declaration
}
