package br.com.senai.sollaris.domain.resources.handleExceptions;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.senai.sollaris.domain.resources.services.exceptions.DadosInvalidosException;
import br.com.senai.sollaris.domain.resources.services.exceptions.EmpresaFeignNaoEncontrada;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
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
	private LocalDateTime dataRequisicao;
	private List<Campo> campos;
	
	public RespostaException(HttpStatus status, List<Campo> campos) {
		this.titulo = "Campos inválidos, tente novamente";
		this.status = status.value();
		this.dataRequisicao = LocalDateTime.now();
		this.campos = campos;
	}

	public RespostaException(EmpresaFeignNaoEncontrada ex, HttpStatus status, HttpServletRequest requestPath) {
		this.titulo = ex.getMessage();
		this.Url = requestPath.getRequestURL();
		this.recurso = requestPath.getRequestURI();
		this.status = status.value();
		this.dataRequisicao = LocalDateTime.now();
	}

	public RespostaException(DadosInvalidosException ex, HttpStatus status, HttpServletRequest requestPath) {
		this.titulo = ex.getMessage();
		this.Url = requestPath.getRequestURL();
		this.recurso = requestPath.getRequestURI();
		this.status = status.value();
		this.dataRequisicao = LocalDateTime.now();
	}

	public RespostaException(ObjetoNaoEncontradoException ex, HttpStatus status, HttpServletRequest requestPath) {
		this.titulo = ex.getMessage();
		this.Url = requestPath.getRequestURL();
		this.recurso = requestPath.getRequestURI();
		this.status = status.value();
		this.dataRequisicao = LocalDateTime.now();
	}
}
