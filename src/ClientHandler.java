import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    // Festlegung des Keys für die Verschlüsselung
    private static final int KRYPTO_SCHLUESSEL = 3;

    public static ArrayList<ClientHandler> clientHandlerListe = new ArrayList<>(); // Liste mit allen clientHandlerListe

    private Socket benutzerSocket;
    private BufferedReader chEingang;
    private BufferedWriter chAusgang;
    private String clientBenutzername;


    // Konstruktor des clientHandlerListe
    public ClientHandler(Socket socket) {
        try {
            this.benutzerSocket = socket; // Übergabe Parameter
            this.chAusgang = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // BufferedWriter und Reader für Input und Output
            this.chEingang = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Der Benutzername des Clients wird vom BufferedReader gelesen
            this.clientBenutzername = chEingang.readLine();



            // Der ClientHandler wird zur Liste der aktiven ClientHandler hinzugefügt
            clientHandlerListe.add(this);

            broadcastMessage(cryptoMsg(clientBenutzername, KRYPTO_SCHLUESSEL, true) + " ist dem Chat beigetreten!", this, true); // Beitritt kenzeichnen

        } catch (IOException e) {
            // Fehlerbehandlung bei der Initialisierung
            broadcastMessage(cryptoMsg(clientBenutzername, KRYPTO_SCHLUESSEL, true) + " hat den Chat verlassen!", this, true); // Verlassen kennzeichen
            closeEverything(socket, chEingang, chAusgang);

        }
    }

    // Implementierung des Runnable-Interfaces
    @Override
    public void run() {
        String msgFromClient;

        // Schleife für die Verarbeitung eingehender Nachrichten vom Client
        while (benutzerSocket.isConnected()) {
            try {
                // Nachrichten vom Client werden vom BufferedReader gelesen
                msgFromClient = chEingang.readLine();


                // Die empfangene Nachricht wird an alle Clients gesendet
                broadcastMessage(msgFromClient, this, false);

            } catch (Exception e) {
                // Fehlerbehandlung bei der Kommunikation mit dem Client
                broadcastMessage(cryptoMsg(clientBenutzername, KRYPTO_SCHLUESSEL, true) + " hat den Chat verlassen!", this, true);
                closeEverything(benutzerSocket, chEingang, chAusgang);
                break;
            }
        }
    }



    // Methode zum Senden einer Nachricht an alle Clients
    private void broadcastMessage(String message, ClientHandler sender, boolean serverMsg) {
        String prefix = "[" + clientBenutzername + "] ";
        for (ClientHandler clientHandler : clientHandlerListe) {
            try {
                if (clientHandler != sender) {
                    // Nachrichten werden an alle Clients außer dem Sender über deren BufferedWriter gesendet
                    if (serverMsg) {
                        clientHandler.chAusgang.write(cryptoMsg("[Server] " + message + "\n", KRYPTO_SCHLUESSEL, false));
                    }else{
                        clientHandler.chAusgang.write(prefix + message + "\n");
                    }
                    clientHandler.chAusgang.flush();
                }
            } catch (IOException e) {
                // Fehlerbehandlung beim Senden der Nachricht
                broadcastMessage(clientBenutzername + cryptoMsg( " hat den Chat verlassen!", KRYPTO_SCHLUESSEL, false), this, true);
                closeEverything(clientHandler.benutzerSocket, clientHandler.chEingang, clientHandler.chAusgang);
                clientHandlerListe.remove(clientHandler);
            }
        }
    }

    // Methode zum Schließen von Socket und Streams
    private void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer) {
        try {
            // Alle Ressourcen werden geschlossen
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            // Fehlerbehandlung beim Schließen der Ressourcen
            e.printStackTrace();
        }
    }

    public static String cryptoMsg(String msg, int key, boolean decrypt) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < msg.length(); ++i) {
            char c = msg.charAt(i);
            if (Character.isLetter(c)) {
                char basis;
                if (Character.isLowerCase(c)) {
                    basis = 'a';
                } else {
                    basis = 'A';
                }
                int verschiebung = decrypt ? (26 - key) % 26 : key;
                char verschoben = (char) (basis + (c - basis + verschiebung) % 26);
                res.append(verschoben);
            } else {
                res.append(c); // Non-letters remain unchanged
            }
        }

        return res.toString();
    }
}



