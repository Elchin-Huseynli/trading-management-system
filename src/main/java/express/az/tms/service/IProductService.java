package express.az.tms.service;

import express.az.tms.model.dto.request.ProductRequest;
import express.az.tms.model.dto.request.ProductUpdateRequest;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.dto.response.ProductResponse;
import java.util.List;


public interface IProductService {
    GlobalResponse<String> save(ProductRequest productRequest);
    GlobalResponse<List<ProductResponse>> findAll();
    GlobalResponse<ProductResponse> findById(Long id);
    GlobalResponse<ProductResponse> update(Long id, ProductUpdateRequest updateRequest);
    GlobalResponse<ProductResponse> delete(Long id);
}
