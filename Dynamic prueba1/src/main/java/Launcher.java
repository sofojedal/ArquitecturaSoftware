import Controlador.*;
import Modelo.*;
import Vista.*;
import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CSVModel model = new CSVModel("C:\\Users\\Personal\\Downloads\\prueba.csv"); // Cambiar a la ruta correcta del archivo CSV
            CSVView view = new CSVView(model);
            CSVController controller = new CSVController(model, view);

            view.setVisible(true);
        });
    }
}
