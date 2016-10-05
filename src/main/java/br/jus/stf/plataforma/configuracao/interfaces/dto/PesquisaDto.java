package br.jus.stf.plataforma.configuracao.interfaces.dto;

import java.util.List;
import java.util.Map;

/**
 * @author lucas.rodrigues
 *
 */
public class PesquisaDto {

	private Long id;
	private String label;
	private String context;
	private boolean executable;
	private List<Map<String, Object>> criterias;
	
	public PesquisaDto(Long id, String descricao, String contexto, Boolean execucaoAutomatica, List<Map<String, Object>> criterio) {
		this.id = id;
		this.label = descricao;
		this.context = contexto;
		this.executable = Boolean.TRUE.equals(execucaoAutomatica);
		this.criterias = criterio;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getContext() {
		return context;
	}
	
	public boolean isExecutable() {
		return executable;
	}
	
	public List<Map<String, Object>> getCriterias() {
		return criterias;
	}
	
}
