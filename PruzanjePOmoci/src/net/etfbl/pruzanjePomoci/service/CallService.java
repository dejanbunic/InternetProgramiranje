package net.etfbl.pruzanjePomoci.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.etfbl.pruzanjePomoci.dao.CallDAO;
import net.etfbl.pruzanjePomoci.dto.Call;

@Path("call")
public class CallService {
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Call> getAll() {
		return CallDAO.getCalls();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCall(@PathParam("id") int id) {
		if (CallDAO.deleteCall(id)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}

	@POST
	@Path("/{id}/fake")
	@Produces(MediaType.APPLICATION_JSON)
	public Response fakeCall(@PathParam("id") int id) {
		if (CallDAO.fakeCall(id)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}

	@POST
	@Path("/{id}/block")
	@Produces(MediaType.APPLICATION_JSON)
	public Response blockCall(@PathParam("id") int id) {
		if (CallDAO.blockCall(id)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
}
