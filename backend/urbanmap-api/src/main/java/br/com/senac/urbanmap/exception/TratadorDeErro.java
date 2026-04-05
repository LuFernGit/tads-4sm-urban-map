package br.com.senac.urbanmap.exception;

import br.com.senac.urbanmap.dtos.ErroValidationDto;
import org.springframework.http.ResponseEntity;
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
    @ExceptionHandler(ErroServiceException.class)
    public ResponseEntity<String> tratarErrosService(ErroServiceException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
