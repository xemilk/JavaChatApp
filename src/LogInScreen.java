import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LogInScreen extends javax.swing.JFrame {

  public boolean loginVerified = false;



    private javax.swing.JLayeredPane backgroundPane;
    private javax.swing.JLabel logo;
    private javax.swing.JButton joinServerButton;
    private javax.swing.JPasswordField textfieldServerAdress;
    private javax.swing.JLabel labelServer;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JTextField textfieldUsername;
    public String username;
    public String portNum;
    public boolean verified = false;

    Client logInClient;

    private static Client mainClient;


    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            LogInScreen logInScreen = new LogInScreen();
           logInScreen.setVisible(true);
        }
    });
    }

public boolean isVerified(){
    if(verified==true){
        return true;
    }
    else return false;
}
    public LogInScreen() {

        // Konstruktion der Elemente
        backgroundPane = new javax.swing.JLayeredPane();
        textfieldServerAdress = new javax.swing.JPasswordField();
        labelUsername = new javax.swing.JLabel();
        labelServer = new javax.swing.JLabel();
        joinServerButton = new javax.swing.JButton();
        textfieldUsername = new javax.swing.JTextField();
        logo = new javax.swing.JLabel();


        // initialisierung des Fensters
        initComponents();
        this.setVisible(true);
    }





    private void initComponents() {

      // Fenstereinstellungen
        String path = "chat_ico.png";
        setIconImage(new ImageIcon(path).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LocalChat");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(94, 136, 252));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("JetBrainsMono NFP", 0, 10));
        setMaximumSize(null);
        setName("LogInFrame");
        setResizable(false);
        setSize(new java.awt.Dimension(350, 500));


        // Hintergrundfläche
        backgroundPane.setBackground(java.awt.Color.white);
        backgroundPane.setOpaque(true);
        backgroundPane.setPreferredSize(new java.awt.Dimension(350, 500));
        backgroundPane.setLayer(textfieldServerAdress, javax.swing.JLayeredPane.DEFAULT_LAYER);
        backgroundPane.setLayer(labelUsername, javax.swing.JLayeredPane.DEFAULT_LAYER);
        backgroundPane.setLayer(labelServer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        backgroundPane.setLayer(joinServerButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        backgroundPane.setLayer(textfieldUsername, javax.swing.JLayeredPane.DEFAULT_LAYER);
        backgroundPane.setLayer(logo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        // Beschriftungen
        labelServer.setBackground(new java.awt.Color(94, 136, 252));
        labelServer.setFont(new java.awt.Font("JetBrainsMonoNL NFP", 0, 12)); // NOI18N
        labelServer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelServer.setText("Server-Adresse");

        labelUsername.setFont(new java.awt.Font("JetBrainsMonoNL NFP", 0, 12)); // NOI18N
        labelUsername.setText("Benutzername");

        // Button
        joinServerButton.setFont(new java.awt.Font("JetBrainsMonoNL NFP", 0, 12)); // NOI18N
        joinServerButton.setText("Beitreten");
        joinServerButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        joinServerButton.setBorderPainted(false);
        joinServerButton.setContentAreaFilled(false);
        joinServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        joinServerButton.setEnabled(false);

        joinServerButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              joinServerButtonActionPerformed(evt);
          }
        });

        // Textfelder
        textfieldUsername.setFont(new java.awt.Font("JetBrainsMono NFP Light", 0, 12));
        textfieldUsername.setPreferredSize(new java.awt.Dimension(20, 20));
        textfieldServerAdress.setFont(new java.awt.Font("JetBrainsMonoNL NFP Light", 0, 12));

        textfieldServerAdress.addKeyListener(new java.awt.event.KeyAdapter() {
          public void keyTyped(java.awt.event.KeyEvent evt) {
             textfieldServerAdressKeyTyped(evt);
          }
      });
        // Logo
        logo.setIcon(new javax.swing.ImageIcon("chat.png")); // NOI18N


        // Ebenen und Positionen der Elemente
        javax.swing.GroupLayout backgroundPaneLayout = new javax.swing.GroupLayout(backgroundPane);
        backgroundPane.setLayout(backgroundPaneLayout);
        backgroundPaneLayout.setHorizontalGroup(
            backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPaneLayout.createSequentialGroup()
                .addGroup(backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPaneLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textfieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldServerAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPaneLayout.createSequentialGroup()
                                .addComponent(joinServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPaneLayout.createSequentialGroup()
                                .addComponent(labelServer, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPaneLayout.createSequentialGroup()
                                .addComponent(labelUsername)
                                .addGap(61, 61, 61))))
                    .addGroup(backgroundPaneLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(logo)))
                .addContainerGap(72, Short.MAX_VALUE))
        );


        backgroundPaneLayout.setVerticalGroup(
            backgroundPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPaneLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(logo)
                .addGap(42, 42, 42)
                .addComponent(labelUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(labelServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfieldServerAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(joinServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        // Layout-Einstellungen
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void joinServerButtonActionPerformed(java.awt.event.ActionEvent evt) {


        if (username != ""  && portNum != "") {
         //   verified = true;
            this.isVerified();
           // portNum = getServerAdressfromTextField();
          //  username = getUsernamefromTextField();

          this.dispose(); // Close the LogInScreen
          System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "Falsche Portnummer");
        }
    }

    public String getUsernamefromTextField(){
      return textfieldUsername.getText();
    }

    public String getServerAdressfromTextField(){

      return textfieldServerAdress.getPassword().toString();
    }


    private void textfieldServerAdressKeyTyped(java.awt.event.KeyEvent evt) {
         joinServerButton.setEnabled(true);
    }












}

