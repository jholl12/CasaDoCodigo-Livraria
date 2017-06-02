package br.com.casadocodigo.livraria.application;

import java.io.FileNotFoundException;

import br.com.casadocodigo.livraria.io.Exportador;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;
import br.com.casadocodigo.livraria.repositorio.ProdutoDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

		//Recupera lista de produtos e adiciona na tabela
		ObservableList<Produto> produtos = new ProdutoDAO().lista();
		TableView<Produto> tabela = new TableView<>(produtos);

		//Cria coluna de NOME
		TableColumn colunaNome = new TableColumn("Nome");
		colunaNome.setMinWidth(180);
		colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));

		//Cria coluna de DESCRICAO
		TableColumn colunaDescricao = new TableColumn("Descrição");
		colunaDescricao.setMinWidth(230);
		colunaDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));

		//Cria coluna de VALOR
		TableColumn colunaValor = new TableColumn("Valor");
		colunaValor.setMinWidth(60);
		colunaValor.setCellValueFactory(new PropertyValueFactory("valor"));

		//Cria coluna de ISBN
		TableColumn colunaIsbn = new TableColumn("ISBN");
		colunaIsbn.setMinWidth(180);
		colunaIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));

		//Adiciona as colunas na tabela
		tabela.getColumns().addAll(colunaNome, colunaDescricao, colunaValor, colunaIsbn);

		//Adiciona tabela em uma caixa para alinhar
		VBox vbox = new VBox(tabela);
		vbox.setPadding(new Insets(70, 0, 0, 10));

		//Cria label
		Label label = new Label("Listagem de Livros");
		label.setFont(Font.font("Lucida Grande", FontPosture.REGULAR, 30));
		label.setPadding(new Insets(20, 0, 10, 10));
		
		//Cria o botão
		Button btnExportar = new Button("Exportar CSV");
		btnExportar.setLayoutX(575);
		btnExportar.setLayoutY(34);
		
		//Classe anônima dentro do parâmetro de ação do click
		btnExportar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				try {
					new Exportador().exportarCSV(produtos);
				} catch (FileNotFoundException e) {
					throw new RuntimeException("Erro ao exportar arquivo: " + e);
				}
			}
		});
		
		grupo.getChildren().addAll(label, vbox, btnExportar);

		primaryStage.setTitle("Sistema da livraria com Java FX");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
