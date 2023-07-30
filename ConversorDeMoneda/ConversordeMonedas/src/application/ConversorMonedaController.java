package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ConversorMonedaController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField txtCantidad;

    @FXML
    private Button btnConvertir;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegresar;

    @FXML
    private void initialize() {
        comboBox.getItems().addAll("Convertir Pesos Mexicanos a Dólar", "Convertir Pesos Mexicanos a Euros",
                "Convertir Pesos Mexicanos a Libras Esterlinas", "Convertir Pesos Mexicanos a Yen Japonés",
                "Convertir Pesos Mexicanos a Won sul-coreano", "Convertir de Dólar a Pesos Mexicanos",
                "Convertir de Euros a Pesos Mexicanos", "Convertir de Libras Esterlinas a Pesos Mexicanos",
                "Convertir de Yen Japonés a Pesos Mexicanos", "Convertir de Won sul-coreano a Pesos Mexicanos");
    }

    @FXML
    private void handleConvertir() {
        String seleccion = comboBox.getValue();
        String cantidadTexto = txtCantidad.getText();
        if (seleccion == null) {
            mostrarAlerta("Información", "Debe seleccionar una opción de conversión.");
        } else if (cantidadTexto.isEmpty()) {
            mostrarAlerta("Información", "Debe ingresar la cantidad de divisa a convertir.");
        } else {
            try {
                double cantidad = Double.parseDouble(cantidadTexto);
                double resultado = 0.0;

                switch (seleccion) {
                    case "Convertir Pesos Mexicanos a Dólar":
                        resultado = cantidad / 17.02;
                        break;
                    case "Convertir Pesos Mexicanos a Euros":
                        resultado = cantidad / 19.20;
                        break;
                    case "Convertir Pesos Mexicanos a Libras Esterlinas":
                        resultado = cantidad / 20.45;
                        break;
                    case "Convertir Pesos Mexicanos a Yen Japonés":
                        resultado = cantidad / 0.12;
                        break;
                    case "Convertir Pesos Mexicanos a Won sul-coreano":
                        resultado = cantidad / 0.013;
                        break;
                    case "Convertir de Dólar a Pesos Mexicanos":
                        resultado = cantidad * 17.02;
                        break;
                    case "Convertir de Euros a Pesos Mexicanos":
                        resultado = cantidad * 19.20;
                        break;
                    case "Convertir de Libras Esterlinas a Pesos Mexicanos":
                        resultado = cantidad * 20.45;
                        break;
                    case "Convertir de Yen Japonés a Pesos Mexicanos":
                        resultado = cantidad * 0.12;
                        break;
                    case "Convertir de Won sul-coreano a Pesos Mexicanos":
                        resultado = cantidad * 0.013;
                        break;
                }

                mostrarAlerta("Resultado", "La cantidad convertida es: " + resultado);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Ingrese una cantidad válida.");
            }
        }
    }

    @FXML
    private void handleRegresar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
            Parent root = loader.load();
            MenuPrincipalController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnRegresar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancelar() {
        mostrarAlerta("Menú", "Gracias, Vuelva Pronto");
        System.exit(0);
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
