package br.com.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.gerenciador.acao.Acao;


//@WebFilter("/entrada")
public class ControladorFilter extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = -4427312502781464482L;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		System.out.println("Chamando ControladorFilter");
		
		//Apontando uma referencia mais especifica a partir de uma referencia generica
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		
		String nomeDaClasse = "br.com.gerenciador.acao." + paramAcao;
		
		
		//API Refletion
		String nome = "";
		try {
//			if(paramAcao != null || !"".equals(paramAcao)) {
//				// TODO implementar response ou thow exception
//				throw new ServletException("Parametro vazio ou nulo");
//				//request.setAttribute("messageError", "Erro ao recuperar o parametro acao.");
//				//response.sendRedirect("error.jsp");
//			}
			
			Class classe = Class.forName(nomeDaClasse);//Carrega a classe com o nome passado
			Acao acao = (Acao) classe.newInstance(); //Instanciando o objeto
			nome = acao.executa(request,response);
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			//Pega a execeção original do Servlet
			throw new ServletException(e);
		}
		

		//Quebra a String a partir do caractere definido 
		String[] caminho =  nome.split(":");
		
		//String antes do :
		if(caminho[0].equals("forward")) {
			//String depois do :
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/"+caminho[1]);
			rd.forward(request, response);
		}else{
			response.sendRedirect(caminho[1]);
		}
		
	}


}
