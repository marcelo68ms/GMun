package br.com.sw2.comercial.service.excel;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class ChecarNovosPedidos extends Thread {
	
	public void run() {
		
		System.out.println("Thread iniciada");
		GerarOrcamentoExcel service = new GerarOrcamentoExcel();
		
		while(true) {
			
			DirectoryStream<Path> arquivos = service.recuperarArquivosPedido();
			
			for (Path arquivo : arquivos) {
				System.out.println("Tratar planilha " + arquivo.getFileName().toString());
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
