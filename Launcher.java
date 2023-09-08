import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Launcher {
    public static void main(String[] args) {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        // Configura el puerto en el que se ejecutará Tomcat
        tomcat.setPort(8080);

        try {
            // Configura el contexto web para tu aplicación
            tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

            System.out.println("Iniciando servidor Tomcat...");
            tomcat.start();
            System.out.println("Servidor Tomcat iniciado.");

            // Mantén el proceso en ejecución para que el servidor siga funcionando
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
