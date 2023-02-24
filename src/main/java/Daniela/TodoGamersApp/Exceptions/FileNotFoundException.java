package Daniela.TodoGamersApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@RequestMapping("/error")
public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileNotFoundException(String mensaje) {
		super(mensaje);
	}

	public FileNotFoundException(String mensaje, Throwable excepcion) {
		super(mensaje, excepcion);
	}

}