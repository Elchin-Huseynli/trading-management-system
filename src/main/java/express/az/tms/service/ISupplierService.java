package express.az.tms.service;

import express.az.tms.model.dto.request.SupplierRequest;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.dto.response.SupplierResponse;
import java.util.List;


public interface ISupplierService {
    GlobalResponse<String> save(SupplierRequest supplierRequest);
    GlobalResponse<List<SupplierResponse>> findAll();
}
