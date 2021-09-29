package com.teste.noticias.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

public class Elemento {

	private Map<String, Element> elementosCabecalho = new HashMap();

	private Map<Integer, Element> elementosTexto = new HashMap();

	private Map<String, String> textoElementosCabecalho = new HashMap();

	private Map<Integer, String> textoElementosTexto = new HashMap();

	public Map<String, Element> getElementosCabecalho() {
		return elementosCabecalho;
	}

	public void setElementosCabecalho(String tag, Element elemento) {
		this.elementosCabecalho.put(tag, elemento);
	}

	public Map<Integer, Element> getElementosTexto() {
		return elementosTexto;
	}

	public void setElementosTexto(Integer ordem, Element elemento) {
		this.elementosTexto.put(ordem, elemento);
	}

	public void setTextoElementosCabecalho(String tag, String texto) {
		this.textoElementosCabecalho.put(tag, texto);
	}

	public void setTextoElementosTexto(Integer ordem, String texto) {
		this.textoElementosTexto.put(ordem, texto);
	}

	public Map<String, String> getTextoElementosCabecalho() {
		return textoElementosCabecalho;
	}

	public Map<Integer, String> getTextoElementosTexto() {
		return textoElementosTexto;
	}
	
}
