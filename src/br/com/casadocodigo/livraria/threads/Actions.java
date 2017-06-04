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
 * Classe respons�vel por realizar a��es de bot�es em uma nova thread com
 * classes an�nimas
 * 
 * @author Jhonata Santos
 * @version 1.0
 */
public class Actions {

	/**
	 * Realiza a a��o de exportar produtos em csv em uma nova thread utilizando
	 * classes an�nimas na implementa��o
	 * 
	 * @param produtos que ser�o exportados
	 * @param labelProgressoExportar para atualizar conforme o andamento da exporta��o
	 * @return valor do tipo EventHandles para o m�todo setOnAction
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

				// O manipulador de eventos onRunning � chamado sempre
				// que o estado Tarefa transita para o estado EXECUTANDO.
				task.setOnRunning(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						labelProgressoExportar.setText("Exportando...");
					}
				});

				// O manipulador de eventos onSucceeded � chamado sempre
				// que o estado da Tarefa transita para o estado SUCEDIDO.
				task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						labelProgressoExportar.setText("Conclu�do!");
					}
				});

				// Cria uma nova thread e inicializa
				new Thread(task).start();
			}
		};
	}

}
