package br.ufrn.imd.material.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.ufrn.imd.material.dominio.Material;
import br.ufrn.imd.material.exceptions.NegocioException;
import br.ufrn.imd.material.repositorios.MaterialRepositorio;

@Stateless
public class MaterialService {

	@Inject
	private MaterialRepositorio materialRepositorio;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Material adicionar(Material material) 
			throws NegocioException {
		//verificar se o material existe
		Material materialBd = materialRepositorio.buscarMaterial
				(material.getCodigo());
		if(materialBd == null || material.getId() > 0) 
			materialRepositorio.adicionar(material);
		else
			throw new 
			NegocioException("Material existente.");
		return material;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Material material) {
		materialRepositorio.remover(material);
	}
	
	public List<Material> listar() {
		return materialRepositorio.listarMateriais();
	}
	
}
