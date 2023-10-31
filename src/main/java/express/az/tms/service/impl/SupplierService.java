package express.az.tms.service.impl;

import express.az.tms.exception.ApplicationException;
import express.az.tms.mapper.SupplierMapper;
import express.az.tms.model.constants.Constants;
import express.az.tms.model.dto.request.SupplierRequest;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.dto.response.SupplierResponse;
import express.az.tms.model.entity.Supplier;
import express.az.tms.model.enums.Exceptions;
import express.az.tms.repository.SupplierRepository;
import express.az.tms.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;


    @Override
    public GlobalResponse<String> save(SupplierRequest supplierRequest) {
        Supplier supplier = supplierMapper.supplierRequestDtoToSupplier(supplierRequest);
        supplierRepository.save(supplier);

        return GlobalResponse.of(Constants.CREATED_SUCCESSFULLY, HttpStatus.CREATED);

    }


    @Override
    public GlobalResponse<List<SupplierResponse>> findAll() {
        List<Supplier> supplierList = supplierRepository.findAll();
        supplierList.stream()
                .findAny()
                .orElseThrow(() -> new ApplicationException(Exceptions.SUPPLIER_NOT_FOUND));

        List<SupplierResponse> supplierResponses = supplierMapper.supplierListToSupplierResponseDtoList(supplierList);

        return GlobalResponse.of(Constants.SHOW_SUCCESSFULLY, supplierResponses);

    }

}
