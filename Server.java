import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server(int porta) {
        this.porta = porta;
    }
    
    public Socket attendi(){
        return new Socket();
    }

    public void scrivi(){}

    public void leggi(){}

    public void chiudi(){}

    public void termina(){}

}
