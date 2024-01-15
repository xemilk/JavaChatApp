import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket; // Initialisierung einer ServerSocket Instanz
    private static final int SERVER_PORT = 50000;   // Festlegen der Portnummer

    public Server(ServerSocket serverSocket){ // Konstruktor des Servers
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);   // ServerSocket mit TCP-Port 80 erstellen
        Server server = new Server(serverSocket);
        server.startServer();                                   // Server starten
    }
        
    public void startServer(){
        try{                                                    
            while(!serverSocket.isClosed()){                                // Solange die Verbindung besteht
                Socket socket = serverSocket.accept();                      // Neue Verbindungen akzeptieren
                System.out.println("Ein neuer Client ist verbunden");   
                ClientHandler clientHandler = new ClientHandler(socket);    // Client-Handler für die Verbindung erstellen

                Thread thread = new Thread(clientHandler);           // Thread für den Client-Handler erstellen
                thread.start();                                      // Thread starten
            }
        }catch(IOException e){
            // Fehlerbehandlung bei Verbindungsproblemen
            e.printStackTrace();
        }
    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();  // ServerSocket schließen, wenn er existiert
            }
        }catch(IOException e){
            // Fehlerbehandlung beim Schließen
            e.printStackTrace();
        }
    }
}
