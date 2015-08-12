package br.com.oktolab.netflixoss.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.oktolab.netflixoss.command.RecuperarUsuarioCommand;
import br.com.oktolab.netflixoss.nettyrest.provider.annotation.BeanParam;
import br.com.oktolab.netflixoss.ws.dto.RecuperarUsuarioDTO;

/**
 * 
 Observable.from(someSource)  
    .map(data -> manipulate(data))
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(data -> doSomething(data));
 *
 *https://gist.github.com/benjchristensen/a0350776a595fd6e3810#file-parallelexecution-java-L78
 *
 */
@Path("/recuperarUsuario")
public class RecuperarUsuarioRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RecuperarUsuarioDTO recuperarUsuario(@BeanParam RecuperarUsuarioDTO dto) {
		RecuperarUsuarioCommand usuarioCommand = new RecuperarUsuarioCommand(dto);
		RecuperarUsuarioDTO usuario = usuarioCommand.execute();
		return usuario;
	}
}
