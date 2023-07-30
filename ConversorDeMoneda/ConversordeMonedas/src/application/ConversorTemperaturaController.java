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

public class ConversorTemperaturaController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField txtGrados;

    @FXML
    private Button btnConvertir;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegresar;

    @FXML
    private void initialize() {
        comboBox.getItems().addAll("Grados Celsius a Grados Fahrenheit", "Grados Celsius a Kelvin",
                "Grados Fahrenheit a Grados Celsius", "Kelvin a Grados Celsius", "Kelvin a Grados Fahrenheit");
    }

    @FXML
    private void handleConvertir() {
        String seleccion = comboBox.getValue();
        String gradosTexto = txtGrados.getText();
        if (seleccion == null) {
            mostrarAlerta("Información", "Debe seleccionar una opción de conversión.");
        } else if (gradosTexto.isEmpty()) {
            mostrarAlerta("Información", "Debe ingresar la cantidad de grados a convertir.");
        } else {
            try {
                double grados = Double.parseDouble(gradosTexto);
                double resultado = 0.0;

                switch (seleccion) {
                    case "Grados Celsius a Grados Fahrenheit":
                        resultado = (grados * 9 / 5) + 32;
                        break;
                    case "Grados Celsius a Kelvin":
                        resultado = grados + 273.15;
                        break;
                    case "Grados Fahrenheit a Grados Celsius":
                        resultado = (grados - 32) * 5 / 9;
                        break;
                    case "Kelvin a Grados Celsius":
                        resultado = grados - 273.15;
                        break;
                    case "Kelvin a Grados Fahrenheit":
                        resultado = (grados - 273.15) * 9 / 5 + 32;
                        break;
                }

                mostrarAlerta("Resultado", "Los grados convertidos son: " + resultado);
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
