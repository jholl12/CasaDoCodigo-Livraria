package br.com.casadocodigo.livraria.application;

import br.com.casadocodigo.livraria.dao.ProdutoDAO;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;
import br.com.casadocodigo.livraria.threads.Actions;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe responsável pela view de listagem de todos os livros cadastradas na
 * base de dados
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class Main extends Application {

	/**
	 * Responsável por construir e startar a view de listagem de livros
	 * @author Jhonata Santos
	 */
	@Override
	public void start(Stage primaryStage) {
		Group grupo = new Group();
		Scene cenario = new Scene(grupo, 690, 510);
		primaryStage.setScene(cenario);

		// Referencia o arquivo de CSS
		cenario.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// Recupera lista de produtos e adiciona na tabela
		ObservableList<Produto> produtos = new ProdutoDAO().listarProdutos();
		TableView<Produto> tabela = new TableView<>(produtos);

		// ======================
		// Cria colunas na tabela
		// ======================

		// NOME
		TableColumn colunaNome = new TableColumn("Nome");
		colunaNome.setMinWidth(180);
		colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));

		// DESCRICAO
		TableColumn colunaDescricao = new TableColumn("Descrição");
		colunaDescricao.setMinWidth(230);
		colunaDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));

		// VALOR
		TableColumn colunaValor = new TableColumn("Valor");
		colunaValor.setMinWidth(60);
		colunaValor.setCellValueFactory(new PropertyValueFactory("valor"));

		// ISBN
		TableColumn colunaIsbn = new TableColumn("ISBN");
		colunaIsbn.setMinWidth(180);
		colunaIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));

		// Adiciona as colunas na tabela
		tabela.getColumns().addAll(colunaNome, colunaDescricao, colunaValor, colunaIsbn);

		// Adiciona tabela em uma caixa para alinhar
		VBox vbox = new VBox(tabela);
		vbox.setId("vbox");

		// ===================
		// Cria labels da tela
		// ===================

		// Listagem de Livros
		Label labelListagemLivros = new Label("Listagem de Livros");
		Label labelProgressoExportar = new Label();
		Label labelSomaProdutos = new Label(
				String.format("Você tem R$%.2f em estoque," + "com um total de %d produtos.",
						new ProdutoDAO().somarValorProdutos(), produtos.size()));

		labelListagemLivros.setId("label-listagem-livros");
		labelProgressoExportar.setId("label-progresso-exportar");
		labelSomaProdutos.setId("label-soma-produtos");

		// ===================
		// Cria botões da tela
		// ===================

		Button btnExportar = new Button("Exportar CSV");

		// ================
		// Ações dos botões
		// ================

		//Realiza a ação de exportar em uma nova thread
		btnExportar.setOnAction(new Actions().threadAction(produtos, labelProgressoExportar));

		grupo.getChildren().addAll(labelListagemLivros, labelProgressoExportar, labelSomaProdutos, vbox, btnExportar);

		primaryStage.setTitle("Sistema da livraria com Java FX");
		primaryStage.show();
	}

	/**
	 * Executa a aplicação
	 * @param args
	 * @author Jhonata Santos
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
