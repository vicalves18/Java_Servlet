package br.com.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//defindo url de requisição
@WebServlet(urlPatterns = "/oi")
public class OiMundo extends HttpServlet{
	
	/**
	 * Serival version.
	 */
	private static final long serialVersionUID = -7165912424383646076L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out =  resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Oi mundo!!");
		out.println("</body>");
		out.println("</html>");
		
		System.out.println("OiMundo foi chamado com sucesso!");
	}

}
