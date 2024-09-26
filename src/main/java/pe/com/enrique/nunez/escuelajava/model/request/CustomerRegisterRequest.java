package pe.com.enrique.nunez.escuelajava.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerRegisterRequest {
    private String customerName;
    private String customerAddress;
    private Integer documentType;
    private String documentNumber;
}
