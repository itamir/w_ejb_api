package br.ufrn.imd.material.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufrn.imd.material.dominio.Material;
import br.ufrn.imd.material.negocio.MaterialService;

@Stateless
@Path("/consulta")
public class MaterialResource {

	@EJB
	private MaterialService service;

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/materiais")
	public List<Material> listagem() {
		return service.listar();
	}
}
