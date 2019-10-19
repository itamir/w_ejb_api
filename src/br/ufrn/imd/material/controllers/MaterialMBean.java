package br.ufrn.imd.material.controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.material.dominio.Material;
import br.ufrn.imd.material.exceptions.NegocioException;
import br.ufrn.imd.material.negocio.MaterialService;

@Named
@SessionScoped
public class MaterialMBean implements Serializable {

	private Material material;
	
	private DataModel<Material> materiaisModel;
	
	@Inject
	private UsuarioMBean usuarioMBean;
	
	@EJB
	private MaterialService materialService;
	
	public MaterialMBean() {
		material = new Material();
	}
	public String novoMaterial() {
		material = new Material();
		return "/pages/material/form.jsf";
	}	
	public String listarMateriais() {
		materiaisModel = new ListDataModel<Material>
		(materialService.listar());
		return "/pages/material/list.jsf";
	}
	public String cadastrarMaterial() {
		material.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		try {
			materialService.adicionar(material);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().
			addMessage("", msg);
		}
		material = new Material();
		return "/pages/material/form.jsf";
	}
	public String removerMaterial() {
		Material materialRemovido = materiaisModel.getRowData();
		materialService.remover(materialRemovido);
		return listarMateriais();
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public DataModel<Material> getMateriaisModel() {
		return materiaisModel;
	}

	public void setMateriaisModel(DataModel<Material> materiaisModel) {
		this.materiaisModel = materiaisModel;
	}

}
