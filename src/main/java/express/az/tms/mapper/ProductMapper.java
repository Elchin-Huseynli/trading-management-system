package express.az.tms.mapper;

import express.az.tms.model.dto.request.ProductRequest;
import express.az.tms.model.dto.response.ProductResponse;
import express.az.tms.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryId",target = "category.id")
    @Mapping(source = "supplierId",target = "supplier.id")
    @Mapping(target = "id", ignore = true)
    Product productRequestDtoToProduct(ProductRequest productRequest);

    ProductResponse productToProductResponseDto(Product product);

    List<ProductResponse> productListToProductResponseDtoList(List<Product> productList);

}
