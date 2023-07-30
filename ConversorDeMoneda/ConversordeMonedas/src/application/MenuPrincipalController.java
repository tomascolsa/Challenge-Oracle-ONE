package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class MenuPrincipalController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private void initialize() {
        comboBox.getItems().addAll("Conversor de Moneda", "Conversor de Temperatura");
    }

    @FXML
    private void handleAceptar(ActionEvent event) {
        String seleccion = comboBox.getValue();
        if (seleccion == null) {
            mostrarAlerta("Información", "Debe seleccionar una opción de conversión.");
        } else {
            try {
                FXMLLoader loader;
                if (seleccion.equals("Conversor de Moneda")) {
                    loader = new FXMLLoader(getClass().getResource("ConversorMoneda.fxml"));
                } else {
                    loader = new FXMLLoader(getClass().getResource("ConversorTemperatura.fxml"));
                }

                AnchorPane pane = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(pane));
                stage.setTitle("Conversor");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleCancelar(ActionEvent event) {
        mostrarAlerta(null, "Gracias, Vuelva Pronto");
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
