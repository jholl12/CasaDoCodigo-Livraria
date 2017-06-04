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

		// ===================
		// Cria colunas na tabela
		// ===================
		TableColumn colunaNome = criarColuna("Nome", 180, "nome");
		TableColumn colunaDescricao = criarColuna("Descrição", 230, "descricao"); 
		TableColumn colunaValor = criarColuna("Valor", 60, "valor"); 
		TableColumn colunaIsbn = criarColuna("ISBN", 180, "isbn"); 
		
		// Adiciona as colunas na tabela
		tabela.getColumns().addAll(colunaNome, colunaDescricao, colunaValor, colunaIsbn);

		// Adiciona tabela em uma caixa para alinhar
		VBox vbox = new VBox(tabela);
		vbox.setId("vbox");

		// ===================
		// Cria labels da tela
		// ===================
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
	
	/**
	 * Cria um nova coluna para a TableView 
	 * 
	 * @param titulo
	 * @param largura
	 * @param atributo
	 * @return uma coluna nova para a tabela
	 * @author Jhonata Santos
	 */
	private TableColumn<Produto, String> criarColuna(String titulo, int largura, String atributo) {
		TableColumn<Produto, String> column = new TableColumn<Produto, String>(titulo);
		column.setMinWidth(largura);
		column.setCellValueFactory(new PropertyValueFactory<Produto, String>(atributo));
		return column;
	}
}
