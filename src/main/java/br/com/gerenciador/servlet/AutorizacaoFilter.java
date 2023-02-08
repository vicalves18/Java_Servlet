package br.com.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebFilter("/entrada")
public class AutorizacaoFilter extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = -4427312502781464482L;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		System.out.println("Chamando AutorizacaoFilter");
		
		//Apontando uma referencia mais especifica a partir de uma referencia generica
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		
		HttpSession sessao = request.getSession();
		boolean usuarioNaoLogado = (sessao.getAttribute("usuarioLogado")==null);
		boolean acaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
		//Autorizando acesso
		if(acaoProtegida && usuarioNaoLogado) {
			response.sendRedirect("entrada?acao=LoginForm");
			//sai da execução
			return;
		}
		
		chain.doFilter(request, response);
	}



}
