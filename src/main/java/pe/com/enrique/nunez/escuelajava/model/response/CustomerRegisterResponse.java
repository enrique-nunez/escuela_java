package pe.com.enrique.nunez.escuelajava.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CustomerRegisterResponse {
    private Integer customerId;
    private String customerName;
    private String customerAddress;
    private Integer documentType;
    private String documentNumber;
}
