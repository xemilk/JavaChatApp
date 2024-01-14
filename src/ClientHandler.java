import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable 
{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private Socket socket;
    private BufferedReader bReader;
    private BufferedWriter bWriter;
    private String clientUsername;

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.clientUsername = bReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("Server: " + clientUsername + " ist dem Chat beigetreten"
        }catch(IOException e){
            closeEverything(socket, bReader, bWriter);
        }
    }

    @Override
    public void run() {
        String msgFromClient;

        while(socket.isConnected()){
            try {
                msgFromClient = bReader.readLine();
                broadcastMessage(msgFromClient);
            } catch (Exception e) {
               closeEverything(socket, bReader, bWriter);
               break;
            }
        }
        
    }
    
}
