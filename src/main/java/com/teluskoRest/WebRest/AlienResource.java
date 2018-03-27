package com.teluskoRest.WebRest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class AlienResource {
	
	AlienRepository arepo=new AlienRepository();
	
	@GET
	public List<Alien> getAliens() {
		
		return arepo.getAliens();
	}
	
	@GET
	@Path("alien/{name}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("name") String name) {
		
		return arepo.getAlien(name);
	}
	
	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a) {
		arepo.createAlien(a);
		return a;
	}
	
	@PUT
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien updateAlien(Alien a) {
		if(arepo.getAlien(a.getName()).equals(null)) {
		arepo.createAlien(a);
	}
	   else {
		arepo.updateAlien(a);
	     }
		return a;
	}
	
	@DELETE
	@Path("delete/{name}")
	public Alien deleteAlien(@PathParam("name")String name) {
		
		Alien a=arepo.getAlien(name);
		
		if(a.getName()==null) {
			System.out.println("EXCEPTION");
		}
		else {
			arepo.deleteAlien(name);
		}
		
		return a;
	}
}
