import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    // Festlegung des Keys für die Verschlüsselung
    private static final int KRYPTO_SCHLUESSEL = 3;

    public static void main(String[] args) {
        try {

            // Verbindung zum Server herstellen
            Socket socket = new Socket("localhost", 50000);

            // BufferedReader und BufferedcAusgang für die Kommunikation mit dem Server erstellen
            BufferedReader cEingang = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter cAusgang = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Benutzername vom Benutzer eingeben
            System.out.print("Bitte Benutzernamen eingeben: ");
            BufferedReader benutzerEingabe = new BufferedReader(new InputStreamReader(System.in));
            String username = benutzerEingabe.readLine();
            //  an den Server senden
            cAusgang.write(encryptMessage(username) + "\n");
            cAusgang.flush();

            // Thread für die Nachrichtenverarbeitung starten
            Thread receiveThread = new Thread(new NachrichtenEmpfang(cEingang));
            receiveThread.start();

            // Nachrichten vom Benutzer verschlüsseln, an den Server senden
            String benutzerNachricht;
            while ((benutzerNachricht = benutzerEingabe.readLine()) != null) {
                cAusgang.write(encryptMessage(benutzerNachricht) + "\n");
                cAusgang.flush();
            }

            // Ressourcen schließen
            cEingang.close();
            cAusgang.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Runnable-Klasse für das Empfangen von Nachrichten
    static class NachrichtenEmpfang implements Runnable {
        private BufferedReader quelle;

        public NachrichtenEmpfang(BufferedReader quelle) {
            this.quelle =quelle;
        }

        @Override
        public void run() {
            String nachricht;
            try {
                while ((nachricht = quelle.readLine()) != null) {
                    // Nachrichten vom Server anzeigen
                    System.out.println(decryptMessage(nachricht));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
}
