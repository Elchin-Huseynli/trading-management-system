package express.az.tms.mapper;

import express.az.tms.model.dto.request.SupplierRequest;
import express.az.tms.model.dto.response.SupplierResponse;
import express.az.tms.model.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;


@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mapping(target = "id", ignore = true)
    Supplier supplierRequestDtoToSupplier(SupplierRequest supplierRequest);
    SupplierResponse supplierToSupplierResponseDto(Supplier supplier);
    List<SupplierResponse> supplierListToSupplierResponseDtoList(List<Supplier> supplierList);

}
