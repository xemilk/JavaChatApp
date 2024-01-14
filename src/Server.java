import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        Server server = new Server(serverSocket);
        server.startServer();
    }
        
    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Ein neuer Client ist Verbunden");
                ClientHandler clientHandler = new clientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            
            }
            
        }catch(IOException e){

        }
        
    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
