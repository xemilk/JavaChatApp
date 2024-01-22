import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            // Verbindung zum Server herstellen
            Socket socket = new Socket("localhost", 50000);

            // BufferedReader und BufferedWriter für die Kommunikation mit dem Server erstellen
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Benutzername vom Benutzer eingeben
            System.out.print("Bitte Benutzernamen eingeben: ");
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            String username = userInputReader.readLine();

            // Benutzernamen an den Server senden
            writer.write(username + "\n");
            writer.flush();

            // Thread für die Nachrichtenverarbeitung starten
            Thread receiveThread = new Thread(new ReceiveMessages(reader));
            receiveThread.start();

            // Nachrichten vom Benutzer an den Server senden
            String message;
            while ((message = userInputReader.readLine()) != null) {
                writer.write(message + "\n");
                writer.flush();
            }

            // Ressourcen schließen
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Runnable-Klasse für das Empfangen von Nachrichten
    static class ReceiveMessages implements Runnable {

        private BufferedReader reader;

        public ReceiveMessages(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
              String message;
            try {
                while ((message = reader.readLine()) != null) {
                    // Nachrichten vom Server anzeigen
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
