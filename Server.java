import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server(int porta) {
        this.porta = porta;
        try {
            serverSocket = new ServerSocket(porta); // Istanzio un oggetto di tipo connection socket
            System.out.println("S1 - Il server è in ascolto");
        } catch (IOException ex) {
            System.err.println("Errore in fase di apertura della porta (fase di ascolto)");
            ex.printStackTrace();
        }

    }
    
    public Socket attendi(){
        try {
            clientSocket = serverSocket.accept(); // Il metodo bloccante accept() aspetta finché non arriva una richiesta
            System.out.println("S2 - Connessione stabilita con il client");
        } catch (IOException ex) {
            System.err.println("S2 - Errore nella connessione con il client (fase di connessione)");
            ex.printStackTrace();
        }
        return clientSocket;
    }

    public void scrivi(){}

    public void leggi(){}

    public void chiudi(){}

    public void termina(){}

}
