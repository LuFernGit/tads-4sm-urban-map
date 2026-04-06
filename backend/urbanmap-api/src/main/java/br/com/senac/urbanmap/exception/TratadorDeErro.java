package br.com.senac.urbanmap.exception;

import br.com.senac.urbanmap.entities.dtos.ErroValidationDto;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class TratadorDeErro {

    // as exceções do validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidationDto>> tratadorErrosParametros(MethodArgumentNotValidException e) {
        List<FieldError> errosSemFormatacao = e.getFieldErrors();
        List<ErroValidationDto> errosComFormatacao = new ArrayList<>();
        for (FieldError erro : errosSemFormatacao) {
            errosComFormatacao.add(new ErroValidationDto(erro));
        }
        return ResponseEntity.badRequest().body(errosComFormatacao);
    }

    // as exceções dos services
    @ExceptionHandler(ErroUsuarioServiceException.class)
    public ResponseEntity<String> tratarErrosService(ErroUsuarioServiceException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    // usuario não encontrado no momento do login
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> tratarUsuarioNaoEncontrado(UsernameNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    // erro relacionado a criação de token
    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<String> tratarMensagemToken(JWTCreationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
