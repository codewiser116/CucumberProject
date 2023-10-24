package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class CustomResponses {

    private int category_id;
    private String created;
    private int seller_id;

}
