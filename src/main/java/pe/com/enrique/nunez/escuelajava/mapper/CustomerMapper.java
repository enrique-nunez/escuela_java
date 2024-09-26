package pe.com.enrique.nunez.escuelajava.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pe.com.enrique.nunez.escuelajava.model.request.CustomerRegisterRequest;
import pe.com.enrique.nunez.escuelajava.persistence.entity.Customer;


@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customerId", ignore = true)
    Customer customerToCustomerRequest(CustomerRegisterRequest registerRequest);
}
