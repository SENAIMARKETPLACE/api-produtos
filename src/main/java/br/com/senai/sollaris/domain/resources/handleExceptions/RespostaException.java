package br.com.senai.sollaris.domain.resources.handleExceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespostaException {
	private String titulo;
	private StringBuffer Url;
	private Integer status;
	private String recurso;
	private String dataRequisicao;
	private List<Campo> campos;
	
	public RespostaException(HttpStatus status, List<Campo> campos) {
		this.titulo = "Campos inválidos, tente novamente";
		this.status = status.value();
		this.dataRequisicao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		this.campos = campos;
	}

	public RespostaException(String message, int status, HttpServletRequest requestPath) {
		this.titulo = message;
		this.Url = requestPath.getRequestURL();
		this.recurso = requestPath.getRequestURI();
		this.status = status;
		this.dataRequisicao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}
