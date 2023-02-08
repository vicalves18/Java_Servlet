package br.com.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.gerenciador.modelo.Banco;
import br.com.gerenciador.modelo.Empresa;


@WebServlet("/empresas")
public class ListaEmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Empresa> empresas = new Banco().getEmpresas();
	
		String valor = request.getHeader("Accept");
		
		if(valor.contains("xml")) {
			XStream xstream = new XStream();
			//Não exibir o core full name no xml(caminho do pacote e classe)
			xstream.alias("empresa", Empresa.class);
			String xml = xstream.toXML(empresas);
			
			//Tipo de conteudo de resposta
			response.setContentType("application/xml");
			//Transforma String em XML
			response.getWriter().print(xml);
		}else if(valor.contains("json")) {
			Gson gson = new Gson();
			String json = gson.toJson(empresas);
			
			//Tipo de conteudo de resposta
			response.setContentType("application/json");
			//Transforma String em JSON
			response.getWriter().print(json);
		}else {
			response.setContentType("application/json");
			response.getWriter().print("{'message':'no content'}");
		}

	}

}
