package br.com.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//Monitorando a aplicação
//@WebFilter("/entrada")
public class MonitoramentoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Chamando MonitoramentoFilter");

		// devolve o milisegundos antes da execução da lógica
		long antes = System.currentTimeMillis();
		// Define o nome da aplicação da ação que a aplicação está executando
		String acao = request.getParameter("acao");
		// executa a acao
		chain.doFilter(request, response);

		long depois = System.currentTimeMillis();

		System.out.println("Tempo de execução da ação: " + acao + " -> " + (depois - antes));
	}

}
