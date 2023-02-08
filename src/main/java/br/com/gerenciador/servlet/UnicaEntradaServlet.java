package br.com.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.gerenciador.acao.Acao;

//Controller
//@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Controlador Generico
		System.out.println("Chamando entrada de parametro");
		String paramAcao = request.getParameter("acao");
		
//		HttpSession sessao = request.getSession();
//		boolean usuarioNaoLogado = (sessao.getAttribute("usuarioLogado")==null);
//		boolean acaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
//		//Autorizando acesso
//		if(acaoProtegida && usuarioNaoLogado) {
//			response.sendRedirect("entrada?acao=LoginForm");
//			//sai da execução
//			return;
//		}
		String nomeDaClasse = "br.com.gerenciador.acao." + paramAcao;
		
		//API Refletion
		String nome = "";
		try {
//			if(paramAcao != null || !"".equals(paramAcao)) {
//				// TODO implementar response ou thow exception
//				//throw new Exception("Parametro vazio ou nulo");
//				request.setAttribute("messageError", "Erro ao recuperar o parametro acao.");
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
		
//		String nome = null;
//		if(paramAcao.equals("ListaEmpresas")) {
//			ListaEmpresas acao = new ListaEmpresas();
//			nome = acao.executa(request,response);
//			
//		}else if(paramAcao.equals("RemoveEmpresa")) {
//			RemoveEmpresa acao = new RemoveEmpresa();
//			nome = acao.executa(request, response);
//			
//		}else if(paramAcao.equals("MostraEmpresa")) {
//			MostraEmpresa acao = new MostraEmpresa();
//			nome = acao.executa(request, response);
//			
//		}else if(paramAcao.equals("AlteraEmpresa")) {
//			AlteraEmpresa acao = new AlteraEmpresa();
//			nome = acao.executa(request, response);
//			
//		}else if(paramAcao.equals("NovaEmpresa")) {
//			NovaEmpresa acao = new NovaEmpresa();
//			nome = acao.executa(request, response);
//			
//		}else if(paramAcao.equals("NovaEmpresaForm")) {
//			NovaEmpresaForm acao = new NovaEmpresaForm();
//			nome = acao.executa(request, response);
//			
//		}
		
		
	}

}
