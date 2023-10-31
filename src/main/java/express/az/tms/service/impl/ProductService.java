package express.az.tms.service.impl;

import express.az.tms.exception.ApplicationException;
import express.az.tms.mapper.ProductMapper;
import express.az.tms.model.constants.Constants;
import express.az.tms.model.dto.request.ProductRequest;
import express.az.tms.model.dto.request.ProductUpdateRequest;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.dto.response.ProductResponse;
import express.az.tms.model.entity.Product;
import express.az.tms.model.enums.Exceptions;
import express.az.tms.repository.CategoryRepository;
import express.az.tms.repository.ProductRepository;
import express.az.tms.repository.SupplierRepository;
import express.az.tms.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;


    @Override
    public GlobalResponse<String> save(ProductRequest productRequest) {
        Product product = productMapper.productRequestDtoToProduct(productRequest);
        productRepository.save(product);

        return GlobalResponse.of(Constants.CREATED_SUCCESSFULLY, HttpStatus.CREATED);
    }


    @Override
    public GlobalResponse<List<ProductResponse>> findAll() {
        List<Product> productList = productRepository.findAll();
        productList.stream()
                .findAny()
                .orElseThrow(() -> new ApplicationException(Exceptions.PRODUCT_NOT_FOUND));

        List<ProductResponse> productResponses = productMapper.productListToProductResponseDtoList(productList);

        return GlobalResponse.of(Constants.SHOW_SUCCESSFULLY, productResponses);
    }


    @Override
    public GlobalResponse<ProductResponse> findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(Exceptions.PRODUCT_NOT_FOUND));

        ProductResponse productResponse = productMapper.productToProductResponseDto(product);

        return GlobalResponse.of(Constants.FOUND_SUCCESSFULLY, productResponse);
    }


    @Override
    public GlobalResponse<ProductResponse> update(Long id, ProductUpdateRequest updateRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(Exceptions.PRODUCT_NOT_FOUND));

        existingProduct.setName(updateRequest.getName() != null ? updateRequest.getName() : existingProduct.getName());
        existingProduct.setDescription(updateRequest.getDescription() != null ? updateRequest.getDescription() : existingProduct.getDescription());
        existingProduct.setPrice(updateRequest.getPrice() != null ? updateRequest.getPrice() : existingProduct.getPrice());

        existingProduct.setCategory(updateRequest.getCategoryId() != null
                ? categoryRepository.findById(updateRequest.getCategoryId())
                .orElseThrow(() -> new ApplicationException(Exceptions.CATEGORY_NOT_FOUND))
                : existingProduct.getCategory());

        existingProduct.setSupplier(updateRequest.getSupplierId() != null
                ? supplierRepository.findById(updateRequest.getSupplierId())
                .orElseThrow(() -> new ApplicationException(Exceptions.SUPPLIER_NOT_FOUND))
                : existingProduct.getSupplier());

        productRepository.save(existingProduct);

        ProductResponse productResponse = productMapper.productToProductResponseDto(existingProduct);

        return GlobalResponse.of(Constants.UPDATE_SUCCESSFULLY, productResponse);
    }


    @Override
    public GlobalResponse<ProductResponse> delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(Exceptions.PRODUCT_NOT_FOUND));

        productRepository.deleteById(id);
        ProductResponse productResponse = productMapper.productToProductResponseDto(product);

        return GlobalResponse.of(Constants.DELETE_SUCCESSFULLY, productResponse);
    }

}
