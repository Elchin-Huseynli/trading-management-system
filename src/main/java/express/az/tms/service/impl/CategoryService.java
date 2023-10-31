package express.az.tms.service.impl;

import express.az.tms.exception.ApplicationException;
import express.az.tms.mapper.CategoryMapper;
import express.az.tms.model.constants.Constants;
import express.az.tms.model.dto.request.CategoryRequest;
import express.az.tms.model.dto.response.CategoryResponse;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.entity.Category;
import express.az.tms.model.enums.Exceptions;
import express.az.tms.repository.CategoryRepository;
import express.az.tms.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public GlobalResponse<String> save(CategoryRequest categoryRequest) {
        Category category = categoryMapper.categoryRequestDtoToCategory(categoryRequest);
        categoryRepository.save(category);

        return GlobalResponse.of(Constants.CREATED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @Override
    public GlobalResponse<List<CategoryResponse>> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        categoryList.stream()
                .findAny()
                .orElseThrow(() -> new ApplicationException(Exceptions.CATEGORY_NOT_FOUND));

        List<CategoryResponse> categoryResponses = categoryMapper.categoryListToCategoryResponseDtoList(categoryList);


        return GlobalResponse.of(Constants.SHOW_SUCCESSFULLY, categoryResponses);
    }

}
