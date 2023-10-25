package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class CustomResponses {

    private int category_id;
    private String created;
    private int seller_id;
    private List<CustomResponses> responses;
    private String email;
    private String seller_name;
    private  String responseBody;

}
