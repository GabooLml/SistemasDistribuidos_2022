import java.io.*;

public class GuardarMemoria {

    public void guardar(String registro) {
        try {
            FileWriter memoria = new FileWriter("memoria.txt", true);
            for (int i = 0; i < registro.length(); i++) {
                memoria.write(registro.charAt(i));
            }
            memoria.write("\n");
            memoria.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}