package application;

import java.sql.Array;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Veiculo_controller {

	@FXML ImageView img_add;
	@FXML ImageView editarVeiculo;
	@FXML ImageView deletarVeiculo;
	@FXML ImageView menu_registrar_gasto;
	@FXML ImageView menu_registrar_veiculos;
	@FXML ImageView menu_registrar_entrada;
	@FXML ImageView menu_registrar_resumo;
	@FXML ImageView menu_pacientes;
	@FXML TableView tbl_veiculos;
	@FXML TableColumn coluna_placa;
	@FXML TableColumn coluna_descricao;

	VeiculoDao dao;

	public void initialize(){

		dao = new VeiculoDao();

		coluna_placa.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("placa"));
        coluna_descricao.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("descricao"));
        ArrayList<Veiculo> lst_veiculos = dao.obterTodos();
        tbl_veiculos.setItems(FXCollections.observableArrayList(lst_veiculos));
        tbl_veiculos.setPadding(new Insets(20, 20, 20, 20));
        tbl_veiculos.setStyle("-fx-font: 16 verdana;");

		img_add.setPickOnBounds(true); // allows click on transparent areas
        img_add.setOnMouseClicked((MouseEvent e) -> {
        	Cadastra_veiculo controller = new Cadastra_veiculo();
        	Main.abrirTela("cadastra_veiculo",false, controller);
        });

        editarVeiculo.setPickOnBounds(true); // allows click on transparent areas
        editarVeiculo.setOnMouseClicked((MouseEvent e) -> {
            Veiculo v = (Veiculo)tbl_veiculos.getSelectionModel().getSelectedItem();

            Cadastra_veiculo controller = new Cadastra_veiculo();
            controller.veiculoAtualizar = v;

            Main.abrirTela("cadastra_veiculo", false, controller);
        });

        deletarVeiculo.setPickOnBounds(true); // allows click on transparent areas
        deletarVeiculo.setOnMouseClicked((MouseEvent e) -> {
            Veiculo v = (Veiculo)tbl_veiculos.getSelectionModel().getSelectedItem();

            dao.deletar(v);

            tbl_veiculos.getItems().remove(v);
        });

        menu_registrar_gasto.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_gasto.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_gastos", true);
        });

        menu_registrar_veiculos.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_veiculos.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_veiculos", true);
        });

        menu_registrar_entrada.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_entrada.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_entradas", true);
        });

        menu_registrar_resumo.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_resumo.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("chart_resumo", true);
        });

        menu_pacientes.setPickOnBounds(true); // allows click on transparent areas
        menu_pacientes.setOnMouseClicked((MouseEvent e) -> {
        	PacientesConsultaController controller = new PacientesConsultaController();
            Main.abrirTela("pacientes_com_consulta", true, controller);
        });
	}


}
