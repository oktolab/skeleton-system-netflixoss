package br.com.oktolab.netflixoss.command;

import br.com.oktolab.netflixoss.ws.dto.RecuperarUsuarioDTO;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class RecuperarUsuarioCommand extends HystrixCommand<RecuperarUsuarioDTO> {

	private static final String GROUP_NAME = "BotService";
	
	private RecuperarUsuarioDTO recuperarUsuarioDTO;
	
	public RecuperarUsuarioCommand(RecuperarUsuarioDTO dto) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP_NAME)));
		this.recuperarUsuarioDTO = dto;
	}

	@Override
	protected RecuperarUsuarioDTO run() throws Exception {
		System.out.println(recuperarUsuarioDTO);
		RecuperarUsuarioDTO usuarioDTO = new RecuperarUsuarioDTO();
		usuarioDTO.setId(23l);
		usuarioDTO.setName("Carolina");
		return usuarioDTO;
//		throw new RuntimeException();
	}

	@Override
	protected RecuperarUsuarioDTO getFallback() {
		return new RecuperarUsuarioDTO();
	}

}
