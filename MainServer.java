public class MainServer {
    
    public static void main(String[] args){
        Server s = new Server(2000);

        while(s.attivo()){
            s.attendi();
            while(s.connesso()){
                s.leggi();
                s.scrivi();
            }
            if(s.attivo())
                System.out.println("In attesa di un nuovo client...");
        }
        System.out.println("Arrivederci!");
    }


}
