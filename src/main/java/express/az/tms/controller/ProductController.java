package express.az.tms.controller;

import express.az.tms.model.dto.request.ProductRequest;
import express.az.tms.model.dto.request.ProductUpdateRequest;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.dto.response.ProductResponse;
import express.az.tms.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/tms/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final IProductService productService;

    @PostMapping("/registration")
    public GlobalResponse<String> registration(@RequestBody @Valid ProductRequest productRequest) {
        log.info("Request: {}", productRequest);
        log.info("Response: {}", "No response");

        return productService.save(productRequest);
    }

    @GetMapping("/all-products")
    public GlobalResponse<List<ProductResponse>> findAll() {
        log.info("Request: {}", "No request");
        GlobalResponse<List<ProductResponse>> productList = productService.findAll();
        log.info("Response: {}", productList);

        return productList;
    }

    @GetMapping("/{id}")
    public GlobalResponse<ProductResponse> findById(@PathVariable Long id) {
        log.info("Request: {}", id);
        GlobalResponse<ProductResponse> product = productService.findById(id);
        log.info("Response: {}", product);

        return product;
    }

    @PutMapping("/{id}")
    public GlobalResponse<ProductResponse> update(@PathVariable Long id, @RequestBody @Valid ProductUpdateRequest updateProduct) {
        log.info("Request: {}, {}", id, updateProduct);
        GlobalResponse<ProductResponse> product = productService.update(id, updateProduct);
        log.info("Response: {}", product);

        return product;
    }

    @DeleteMapping("/{id}")
    public GlobalResponse<ProductResponse> delete(@PathVariable Long id) {
        log.info("Request: {}", id);
        log.info("Response: {}", "No response");

        return productService.delete(id);
    }
}
