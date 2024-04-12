public class MainClient {
    
    public static void main(String args[]){
        Client c = new Client("nome", "rosso");
        c.connetti("127.0.0.1", 2000);

        while(c.connesso()){
            c.scrivi();
            c.leggi();
        }
    }

}
