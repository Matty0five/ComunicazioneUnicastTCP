import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;
    private BufferedReader lettore;             // stream per la lettura dal client
    private BufferedWriter scrittore;           // stream per la scrittura verso il client
    private boolean attivo = true;              // determina lo stato di attività del server
    private boolean connesso = true;            // determina lo stato della connessione col client
    private Scanner lettoreMessaggio;          // buffer per l'input da tastiera

    public Server(int porta) {
        this.porta = porta;
        this.lettoreMessaggio = new Scanner(System.in);
        try {
            serverSocket = new ServerSocket(this.porta); // Istanzio un oggetto di tipo connection socket
            System.out.println("S1 - Il server è in ascolto");
            attivo = true;
        } catch (BindException ex) {
            System.err.println("S2 - Porta già in uso");
            ex.printStackTrace() ;
        } catch (IOException ex) {
            System.err.println("Errore in fase di apertura della porta (fase di ascolto)");
            ex.printStackTrace();
        }
        
    }
    
    public Socket attendi(){
        try {
            clientSocket = serverSocket.accept(); // Il metodo bloccante accept() aspetta finché non arriva una richiesta
            System.out.println("S2 - Connessione stabilita con il client");
            connesso = true;
            
            if(clientSocket != null){
                scrittore = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                lettore = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   
            }
        } catch (IOException ex) {
            System.err.println("S2 - Errore nella connessione con il client (fase di connessione)");
            ex.printStackTrace();
        }
        return clientSocket;
    }

    public void scrivi(){
        if(attivo && connesso){
            String messaggio;              // il messaggio da mandare al server

            try{
                System.out.println("Risposta per il client: ");
                messaggio = lettoreMessaggio.nextLine();
                
                // scrittura della risposta per il client
                scrittore = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                scrittore.write(messaggio);
                scrittore.newLine();
                scrittore.flush();
                
                if(messaggio.equalsIgnoreCase("chiudi")){
                    connesso = false;
                    // non metto il chiudi() perché verrà gestito dal client
                }

                if(messaggio.equalsIgnoreCase("termina")){
                    attivo = false;
                    termina();
                }

            }catch(IOException ex){
                ex.printStackTrace();
            }
            
        }

    }

    public void leggi(){
        if(attivo && !clientSocket.isClosed()){
            String messaggioRicevuto = null;        // messaggio ricevuto dal client

            try{
                // lettura del messaggio inviato dal client
                messaggioRicevuto = lettore.readLine();

                if(!messaggioRicevuto.equals("chiudi")){
                    System.out.println("Messaggio dal client: " + messaggioRicevuto);
                }else{
                    System.out.println("Connessione chiusa da parte del client.");
                    connesso = false;
                }

            }catch(IOException ex){
                ex.printStackTrace();
            }
        }else{
            connesso = false;
        }
    }

    public void chiudi() {
        try {
            if(serverSocket != null){
                // Chiudo il socket
                this.clientSocket.close();
            }
            System.out.println("Client disconnesso.");
        } catch (IOException ex) {
            System.err.println("Errore");
            System.err.println(ex.getMessage());
        }
    }

    public void termina() {
        try {
            if(serverSocket != null && clientSocket.isClosed()){
                //close
                serverSocket.close();
                System.out.println("Server terminato con successo");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean connesso(){
        return connesso;
    }

    public boolean attivo(){
        return attivo;
    }
}
