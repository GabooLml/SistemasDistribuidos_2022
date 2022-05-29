/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import javax.transaction.*;
import javax.transaction.xa.*;
import com.ibm.db2.jdbc.app.*;
/**
 *
 * @author gabri
 */
public class JTACommit {
    public static void main(java.lang.String[] args) {
        JTACommit test = new JTACommit();

        test.setup();
        test.run();
    }


    /**
     * Manejar la ejecución de limpieza anterior para que esta prueba pueda volver a empezar.
     */
    public void setup() {

        Connection c = null;
        Statement s = null;
        try {
            Class.forName("com.ibm.db2.jdbc.app.DB2Driver");
            c = DriverManager.getConnection("jdbc:db2:*local");
            s = c.createStatement();

            try {
                s.executeUpdate("DROP TABLE CUJOSQL.JTATABLE");
            } catch (SQLException e) {
                // Ignorar... no existe
            }

            s.executeUpdate("CREATE TABLE CUJOSQL.JTATABLE (COL1 CHAR (50))");
            s.close();
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }


    /** 
     * Esta prueba utiliza el soporte JTA para manejar transacciones.
     */
    public void run() {
        Connection c = null;

        try {
            Context ctx = new InitialContext();

            // Presuponer que el origen de datos se apoya en un UDBXADataSource.
            UDBXADataSource ds = (UDBXADataSource) ctx.lookup("XADataSource");

            // Desde el DataSource, obtener un objeto XAConnection que
            // contenga un XAResource y un objeto Connection.
            XAConnection  xaConn = ds.getXAConnection();
            XAResource    xaRes  = xaConn.getXAResource();
            Connection    c      = xaConn.getConnection();

            // Para transacciones XA, es necesario un identificador de transacción.
            // No se incluye una implementación de la interfaz XID con el 
            // controlador JDBC. En Transacciones con JTA hay una descripción de
            // esta interfaz para construir una clase para ella.                
            Xid xid = new XidImpl();

            // La conexión de XAResource puede usarse como cualquier otra 
            // conexión JDBC.
            Statement stmt = c.createStatement();

            // Hay que notificar al recurso XA antes de iniciar cualquier 
            // trabajo transaccional.
            xaRes.start(xid, XAResource.TMNOFLAGS);

            // Se realiza trabajo JDBC estándar.
            int count = 
              stmt.executeUpdate("INSERT INTO CUJOSQL.JTATABLE VALUES('JTA es bastante divertido.')");

            // Cuando el trabajo de transacción ha terminado, el recurso XA debe 
            // recibir notificación de nuevo.
            xaRes.end(xid, XAResource.TMSUCCESS);

            // La transacción representada por el ID de transacción se prepara
            // para el compromiso.
            int rc = xaRes.prepare(xid);

            // La transacción se compromete mediante el XAResource.
            // No se utiliza el objeto JDBC Connection para comprometer
            // la transacción al utilizar JTA.
            xaRes.commit(xid, false);


        } catch (Exception e) {
            System.out.println("Algo ha fallado.");
            e.printStackTrace();
        } finally {
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                System.out.println("Nota: excepción de limpieza.");
                e.printStackTrace();
            }
        }
    }
}
