import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private Socket socket;
    private BufferedReader bReader;
    private BufferedWriter bWriter;
    private String clientUsername;

    public boolean offMsg=false;

    // Konstruktor des ClientHandlers
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Der Benutzername des Clients wird vom BufferedReader gelesen
            this.clientUsername = bReader.readLine();

            // Der ClientHandler wird zur Liste der aktiven ClientHandler hinzugefügt
            clientHandlers.add(this);

            broadcastMessage(clientUsername+ " ist dem Chat beigetreten!",this, true);

        } catch (IOException e) {
            // Fehlerbehandlung bei der Initialisierung
            closeEverything(socket, bReader, bWriter);
        }
    }

    // Implementierung des Runnable-Interfaces
    @Override
    public void run() {
        String msgFromClient;

        // Schleife für die Verarbeitung eingehender Nachrichten vom Client
        while (socket.isConnected()) {
            try {
                // Nachrichten vom Client werden vom BufferedReader gelesen
                msgFromClient = bReader.readLine();

                // Die empfangene Nachricht wird an alle Clients gesendet
                broadcastMessage(msgFromClient, this, false);
            } catch (Exception e) {
                // Fehlerbehandlung bei der Kommunikation mit dem Client
                closeEverything(socket, bReader, bWriter);
                break;
            }
        }
    }

    // Methode zum Senden einer Nachricht an alle Clients
    private void broadcastMessage(String message, ClientHandler sender, boolean offServerMsg) {
        String prefix = "["+clientUsername+"] ";
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (clientHandler != sender) {
                    // Nachrichten werden an alle Clients außer dem Sender über deren BufferedWriter gesendet
                    if(offServerMsg){
                        prefix="[Server] ";
                    }
                        clientHandler.bWriter.write(prefix + message + "\n");
                        clientHandler.bWriter.flush();


                }
            } catch (IOException e) {
                // Fehlerbehandlung beim Senden der Nachricht
                closeEverything(clientHandler.socket, clientHandler.bReader, clientHandler.bWriter);
                clientHandlers.remove(clientHandler);
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
}
