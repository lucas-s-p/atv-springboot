package biblioteca.biblioteca.Dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class LivroPostPutDto {
    private  String isbn; 
    private String titulo;
    
    @JsonFormat (pattern = "HH:mm:ss")
    private  LocalTime ano; 
    
    private String edicao; 
    private  Long  idAutor; 

    
}
