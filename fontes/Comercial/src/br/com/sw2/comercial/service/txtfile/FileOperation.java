package br.com.sw2.comercial.service.txtfile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FileOperation {
	
	private static final String PATH = "d:\\SW2\\OdonTO-DO\\fontes\\Comercial\\src\\main\\resources\\";
	private static final String FORMAT_DATE = "yyyyMMddHHmmss";
	
	public void criarArquivo(String nome, List<String> linhas) {
		
		SimpleDateFormat formatoData = new SimpleDateFormat(FORMAT_DATE);
		Calendar data = Calendar.getInstance();
		
		try {
			Path txt = Paths.get(PATH + nome +  formatoData.format(data.getTime()) + ".txt");
			Files.write(txt, linhas, Charset.defaultCharset());
		} catch (IOException e) {
			System.err.printf("Erro na criacao do arquivo: %s.\n", e.getMessage());
		}
	}
	
	public List<String> lerArquivo(String nome) {
		
		List<String> linhas = null;
		
		try {
			Path txt = Paths.get(PATH + nome);
			linhas = Files.readAllLines(txt, Charset.defaultCharset());
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		return linhas;
		
	}
	
	public DirectoryStream<Path> recuperarArquivosPedido() {
		
		Path dir = Paths.get(PATH);
		String filtro = "Pedido*.txt";

		DirectoryStream<Path> stream = null;
		try {
			stream = Files.newDirectoryStream(dir, filtro);
		} catch (IOException e) {
			System.err.printf("Erro ao listar arquivos: %s.\n", e.getMessage());
		}

		return stream;
		
	}

	public void inlcuirNumPedidoArquivo(Path arquivo, String nrPedido) {
		try {
			String nomeAtual = arquivo.getFileName().toString();
			String novoNome = nomeAtual.replace("Pedido", "NrPedido" + nrPedido + "-");
			Files.move(arquivo, arquivo.resolveSibling(novoNome));
		} catch (IOException e) {
			System.err.printf("Erro ao renomear arquivo: %s.\n", e.getMessage());
		}
		
	}
	
	/*public WatchService getWatchService() {
		Path dir = Paths.get(PATH);
		WatchService watchService = null;
		
		try {
			watchService = FileSystems.getDefault().newWatchService();
			dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (IOException e) {
			System.err.printf("Erro ao criar observador de diretorio: %s.\n", e.getMessage());
		}
		
		return watchService;
		
	}*/

}
