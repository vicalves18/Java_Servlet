package br.com.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Implementando Design Patterns - Comand
public interface Acao {
	String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
