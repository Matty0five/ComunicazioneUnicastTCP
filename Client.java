import java.io.IOException;
import java.net.*;

public class Client {
    private String nome;
    private String colore;
    private Socket s;

    public Client(String nomeDefault, String coloreDefault) {
        this.nome = nomeDefault;
        this.colore = coloreDefault;
    }

    public void connetti(String nomeServer, int portaServer){
        try {
            s = new Socket(nomeServer, portaServer);
            System.out.println("C1 - Connessione con il server instaurata (fase di connessione con il server)");
        } catch (UnknownHostException ex) {
            System.err.println("C1 - Host non risolto");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("C1 - Errore con la connessione con il server");
            ex.printStackTrace();
        }
    }

    public void scrivi(){}

    public void leggi(){}

    public void chiudi(){}
    
}
