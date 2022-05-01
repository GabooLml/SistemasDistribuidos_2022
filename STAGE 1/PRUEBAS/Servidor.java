import java.text.*;
import java.util.*;

public class Servidor extends Thread {
    String name;

    Servidor(String name) {
        setName("Servidor");
    }

    public void run() {
        Date fecha = new Date();
        DateFormat  hourFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        System.out.println("[" + hourFormat.format(fecha) + "] " + getName() + "-" + getId());
    }
}