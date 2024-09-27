package pe.com.enrique.nunez.escuelajava.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductResponse {
    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private Boolean status;
}
