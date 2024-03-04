import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

    // Festlegung des Keys für die Verschlüsselung
    private static final int KRYPTO_SCHLUESSEL = 3;
    //public static String pUsername = "";


    public static void main(String[] args) {
        try {
            // Verbindung zum Server herstellen
            Socket socket = new Socket("localhost", 50000);

            // BufferedReader und BufferedWriter für die Kommunikation mit dem Server erstellen
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Benutzernamen vom Benutzer eingeben
           System.out.print("Bitte Benutzernamen eingeben: ");
           BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            String username = userInputReader.readLine();

            // Benutzernamen an den Server senden
            outputWriter.write(encryptMessage(username) + "\n");
            outputWriter.flush();

            // Chat-Fenster erstellen und anzeigen
            ChattingScreen chattingScreen = new ChattingScreen();
            chattingScreen.setTitle(username);

            // Thread für den Nachrichtenempfang starten
            Thread receiveThread = new Thread(new MessageReceiver(inputReader, chattingScreen));
            receiveThread.start();

            // Schleife für das Senden von Nachrichten
            while (true) {
                // Nachricht vom Benutzer eingeben
                String message = chattingScreen.waitForMessageToSend();
                // Nachricht an den Server senden
                outputWriter.write(encryptMessage(message) + "\n");
                outputWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to encrypt message
    private static String encryptMessage(String message) {
        return ClientHandler.cryptoMsg(message, KRYPTO_SCHLUESSEL, false);
    }

    // Method to decrypt message
    private static String decryptMessage(String message) {
        return ClientHandler.cryptoMsg(message, KRYPTO_SCHLUESSEL, true);
    }

    // Runnable for receiving messages
    static class MessageReceiver implements Runnable {
        private BufferedReader inputReader;
        private ChattingScreen chattingScreen;

        public MessageReceiver(BufferedReader inputReader, ChattingScreen chattingScreen) {
            this.inputReader = inputReader;
            this.chattingScreen = chattingScreen;
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = inputReader.readLine()) != null) {
                    // Decrypt the message and display it on the chatting screen
                    String decryptedMessage = decryptMessage(message);
                    chattingScreen.displayReceivedMessage(decryptedMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
