package br.com.sw2.comercial.service.txtfile;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class ChecarNovosPedidos extends Thread {
	
	public void run() {
		
		System.out.println("Thread iniciada");
		FileOperation fileOperation = new FileOperation();
		PedidoServiceFile service = new PedidoServiceFile();
		
		while(true) {
			
			DirectoryStream<Path> arquivos = fileOperation.recuperarArquivosPedido();
			
			for (Path arquivo : arquivos) {
				System.out.println("Tratar arquivo " + arquivo.getFileName().toString());
				service.tratarArquivo(arquivo);
			}
			
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				System.err.printf("Thread interrompida: %s.\n", e.getMessage());
			}
			
		}
	}
}
