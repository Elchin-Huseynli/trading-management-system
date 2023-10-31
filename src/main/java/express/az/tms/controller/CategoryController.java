package express.az.tms.controller;

import express.az.tms.model.dto.request.CategoryRequest;
import express.az.tms.model.dto.response.CategoryResponse;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/tms/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping("/registration")
    public GlobalResponse<String> registration(@RequestBody @Valid CategoryRequest categoryRequest){
        log.info("Request: {}", categoryRequest);
        log.info("Response: {}", "No response");

        return categoryService.save(categoryRequest);
    }

    @GetMapping("/all-categories")
    public GlobalResponse<List<CategoryResponse>> findAll(){
        log.info("Request: {}", "No request");
        GlobalResponse<List<CategoryResponse>> categoryList = categoryService.findAll();
        log.info("Response: {}", categoryList);

        return categoryList;
    }
}
