package com.capgemini.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PeticionesRespuestasLogging implements Filter {

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		log.info("Filtro inicializado :{}", this);
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		log.info("Peticion  {} : {}", req.getMethod(), req.getRequestURI());
		chain.doFilter(request, response);
		log.info("Respuesta :{}", res.getStatus()+"  "+res.getContentType());
	}

	@Override
	public void destroy() {
		log.warn("Filtro finalziado :{}", this);
	}
}