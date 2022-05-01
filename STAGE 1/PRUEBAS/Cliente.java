import java.text.*;
import java.util.*;

public class Cliente extends Thread {
    String name;

    Cliente(String name) {
        setName("Cliente");
    }

    public void run() {
        Date fecha = new Date();
        DateFormat  hourFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        System.out.println("[" + hourFormat.format(fecha) + "] " + getName() + "-" + getId());
    }
}