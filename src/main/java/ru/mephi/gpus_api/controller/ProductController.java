package ru.mephi.gpus_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRqDto;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRsDto;
import ru.mephi.gpus_api.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductRsDto> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductRsDto getById(@PathVariable String id){
        return productService.getById(id);
    }

    @GetMapping("/{id}/stores")
    public List<StoreRsDto> getStoresById(@PathVariable String id){
        return productService.getStoresById(id);
    }

    @PostMapping("/{id}/stores/add")
    public void addStoreToProduct(@PathVariable String id, @RequestBody StoreRqDto dto){
        productService.addStoreToProduct(id, dto);
    }

    @PostMapping("/{id}/stores/delete")
    public void deleteStoreFromProduct(@PathVariable String id, @RequestBody String url){
        productService.deleteStoreFromProduct(id, url);
    }
}
