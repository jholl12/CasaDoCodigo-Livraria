package br.com.casadocodigo.livraria.application;

import java.io.FileNotFoundException;

import br.com.casadocodigo.livraria.dao.ProdutoDAO;
import br.com.casadocodigo.livraria.io.Exportador;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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

		// Recupera lista de produtos e adiciona na tabela
		ObservableList<Produto> produtos = new ProdutoDAO().lista();
		TableView<Produto> tabela = new TableView<>(produtos);

		
		//======================
		//Cria colunas na tabela
		//======================
		
		//NOME
		TableColumn colunaNome = new TableColumn("Nome");
		colunaNome.setMinWidth(180);
		colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));

		//DESCRICAO
		TableColumn colunaDescricao = new TableColumn("Descrição");
		colunaDescricao.setMinWidth(230);
		colunaDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));

		//VALOR
		TableColumn colunaValor = new TableColumn("Valor");
		colunaValor.setMinWidth(60);
		colunaValor.setCellValueFactory(new PropertyValueFactory("valor"));

		//ISBN
		TableColumn colunaIsbn = new TableColumn("ISBN");
		colunaIsbn.setMinWidth(180);
		colunaIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));

		// Adiciona as colunas na tabela
		tabela.getColumns().addAll(colunaNome, colunaDescricao, colunaValor, colunaIsbn);

		// Adiciona tabela em uma caixa para alinhar
		VBox vbox = new VBox(tabela);
		vbox.setPadding(new Insets(70, 0, 0, 10));

		//===================
		//Cria labels da tela
		//===================
		
		//Listagem de Livros
		Label listagemLivrosLabel = new Label("Listagem de Livros");
		listagemLivrosLabel.setFont(Font.font("Lucida Grande", FontPosture.REGULAR, 30));
		listagemLivrosLabel.setPadding(new Insets(20, 0, 10, 10));

		//Progresso ao exportar
		Label progressoExportar = new Label();
		progressoExportar.setLayoutX(495);
		progressoExportar.setLayoutY(38);
		
		//===================
		//Cria botões da tela
		//===================
		
		//Exportar
		Button btnExportar = new Button("Exportar CSV");
		btnExportar.setLayoutX(575);
		btnExportar.setLayoutY(34);
		
		//================
		//Ações dos botões
		//================
		
		// Classe anônima dentro do parâmetro de ação do click EXPORTAR
		btnExportar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Task<Void> task = new Task<Void>(){
					@Override
					protected Void call() throws Exception {
						Thread.sleep(20000);
						new Exportador().exportarCSV(produtos);
						return null;
					}					
				};
				
				task.setOnRunning(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						progressoExportar.setText("Exportando...");
					}
				});
				
				task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						progressoExportar.setText("Concluído!");
					}
				});
				
				new Thread(task).start();
			}
		});

		grupo.getChildren().addAll(listagemLivrosLabel, progressoExportar, vbox, btnExportar);

		primaryStage.setTitle("Sistema da livraria com Java FX");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
