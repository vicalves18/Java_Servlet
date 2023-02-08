package br.com.gerenciador.cliente;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

public class ClienteWebService {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		//Pega o conteudo do Post,executa, retorna o conteudo e devolve como String
		String conteudo = Request.Post("http://localhost:8080/gerenciador/empresas")
			.addHeader("Accept","application/json")
			.execute().returnContent()
			.asString();
		
		System.out.println(conteudo);
	}

}
