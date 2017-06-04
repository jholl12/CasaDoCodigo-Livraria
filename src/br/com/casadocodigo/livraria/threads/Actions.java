package br.com.casadocodigo.livraria.threads;

import br.com.casadocodigo.livraria.io.Exportador;
import br.com.casadocodigo.livraria.modelo.produtos.Produto;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

/**
 * Classe responsável por realizar ações de botões em uma nova thread com
 * classes anônimas
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class Actions {

	/**
	 * Realiza a ação de exportar produtos em csv em uma nova thread utilizando
	 * classes anônimas na implementação
	 * 
	 * @param produtos que serão exportados
	 * @param labelProgressoExportar para atualizar conforme o andamento da exportação
	 * @return valor do tipo EventHandles para o método setOnAction
	 * @author Jhonata Santos
	 */
	public EventHandler<ActionEvent> threadAction(ObservableList<Produto> produtos, Label labelProgressoExportar) {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Task<Void> task = new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						Thread.sleep(20000);
						new Exportador().exportarCSV(produtos);
						return null;
					}
				};

				// O manipulador de eventos onRunning é chamado sempre
				// que o estado Tarefa transita para o estado EXECUTANDO.
				task.setOnRunning(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						labelProgressoExportar.setText("Exportando...");
					}
				});

				// O manipulador de eventos onSucceeded é chamado sempre
				// que o estado da Tarefa transita para o estado SUCEDIDO.
				task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						labelProgressoExportar.setText("Concluído!");
					}
				});

				// Cria uma nova thread e inicializa
				new Thread(task).start();
			}
		};
	}

}
