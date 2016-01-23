package br.com.oktolab.netflixoss.ws;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloRest {
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String hello() {
//		return "hellooooooo!!";
//	}
	
	// send to test -> {lsPerson:[{"id":3, "name":"Adriano"}]}
	@Path("to/group")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public HelloPerson helloGroup(GroupOfPersons group) {
		HelloPerson person = new HelloPerson();
		person.setName(group.getLsPerson().get(0).getName());
		return person;
    }
	
	// send to test -> {"id":3, "name":"Adriano"}
	@Path("to/person")
    @POST
    @Consumes(MediaType.APPLICATION_JSON) // TODO TEST GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloPerson helloToPerson(HelloPerson personParam) {
		HelloPerson person = new HelloPerson();
		person.setName(personParam.getName());
		return person;
    }
	
	// send to test -> {"id":3, "name":"Adriano"}
	@Path("toGet/person")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloPerson helloToPersonGet(@BeanParam HelloPerson personParam) {
		HelloPerson person = new HelloPerson();
		person.setName(personParam.getName());
		return person;
    }
	
	static class GroupOfPersons {
		private List<HelloPerson> lsPerson;

		public List<HelloPerson> getLsPerson() {
			return lsPerson;
		}

		public void setLsPerson(List<HelloPerson> lsPerson) {
			this.lsPerson = lsPerson;
		}

	}
	static class HelloPerson {
		
		private Integer id;
		private String name;
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
