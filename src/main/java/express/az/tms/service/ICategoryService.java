package express.az.tms.service;

import express.az.tms.model.dto.request.CategoryRequest;
import express.az.tms.model.dto.response.CategoryResponse;
import express.az.tms.model.dto.response.GlobalResponse;
import java.util.List;


public interface ICategoryService {
    GlobalResponse<String> save(CategoryRequest categoryRequest);
    GlobalResponse<List<CategoryResponse>> findAll();
}
