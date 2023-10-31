package express.az.tms.mapper;

import express.az.tms.model.dto.request.CategoryRequest;
import express.az.tms.model.dto.response.CategoryResponse;
import express.az.tms.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category categoryRequestDtoToCategory(CategoryRequest categoryRequest);
    CategoryResponse categoryToCategoryResponseDto(Category category);
    List<CategoryResponse> categoryListToCategoryResponseDtoList(List<Category> categoryList);

}
