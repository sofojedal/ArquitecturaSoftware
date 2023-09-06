package Controlador;
import Modelo.*;
import Vista.*;
public class CSVController {
    private CSVModel model;
    private CSVView view;

    public CSVController(CSVModel model, CSVView view) {
        this.model = model;
        this.view = view;
    }
}
