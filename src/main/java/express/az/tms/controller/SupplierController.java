package express.az.tms.controller;

import express.az.tms.model.dto.request.SupplierRequest;
import express.az.tms.model.dto.response.GlobalResponse;
import express.az.tms.model.dto.response.SupplierResponse;
import express.az.tms.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/tms/supplier")
@RequiredArgsConstructor
@Slf4j
public class SupplierController {

    private final ISupplierService supplierService;

    @PostMapping("/registration")
    public GlobalResponse<String> registration(@RequestBody @Valid SupplierRequest supplierRequest) {
        log.info("Request: {}", supplierRequest);
        log.info("Response: {}", "No response");

        return supplierService.save(supplierRequest);
    }

    @GetMapping("/all-suppliers")
    public GlobalResponse<List<SupplierResponse>> findAll() {
        log.info("Request: {}", "No request");
        GlobalResponse<List<SupplierResponse>> supplierList = supplierService.findAll();
        log.info("Response: {}", supplierList);

        return supplierList;
    }
}
