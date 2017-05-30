package br.com.casadocodigo.livraria.application;

import br.com.casadocodigo.livraria.modelo.RepositorioDeProdutos;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Group grupo = new Group();
		Scene cenario = new Scene(grupo, 690, 510);
		primaryStage.setScene(cenario);

		ObservableList<Produto> produtos = new RepositorioDeProdutos().lista();
		TableView tabela = new TableView(produtos);

		TableColumn colunaNome = new TableColumn("Nome");
		colunaNome.setMinWidth(180);
		colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));

		TableColumn colunaDescricao = new TableColumn("Descrição");
		colunaDescricao.setMinWidth(230);
		colunaDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));

		TableColumn colunaValor = new TableColumn("Valor");
		colunaValor.setMinWidth(60);
		colunaValor.setCellValueFactory(new PropertyValueFactory("valor"));

		TableColumn colunaIsbn = new TableColumn("ISBN");
		colunaIsbn.setMinWidth(180);
		colunaIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));

		tabela.getColumns().addAll(colunaNome, colunaDescricao, colunaValor, colunaIsbn);

		VBox vbox = new VBox(tabela);
		vbox.setPadding(new Insets(70, 0, 0, 10));

		Label label = new Label("Listagem de Livros");
		label.setFont(Font.font("Lucida Grande", FontPosture.REGULAR, 30));
		label.setPadding(new Insets(20, 0, 10, 10));
		
		Button btnExportar = new Button("Exportar CSV");
		btnExportar.setLayoutX(575);
		btnExportar.setLayoutY(25);
		
		grupo.getChildren().addAll(label, vbox, btnExportar);

		primaryStage.setTitle("Sistema da livraria com Java FX");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
