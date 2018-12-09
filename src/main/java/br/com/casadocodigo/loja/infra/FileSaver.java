package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // representa um componente que pode ser injetado
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	// Classe responsavel por pegar o caminho do arquivo
	public String write(String baseFolder, MultipartFile file) {
		try {
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);
			String pathName = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(pathName));

			return pathName;
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
