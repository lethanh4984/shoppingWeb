package com.example.demo.controller.admin;


import com.example.demo.model.DTO.ProductForm;
import com.example.demo.model.DTO.ProductTypeForm;
import com.example.demo.model.ProductTypes;
import com.example.demo.model.Products;
import com.example.demo.service.ProductType.IProductTypeService;
import com.example.demo.service.Products.IProductService;
import com.example.demo.service.Review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/admin/products")
public class ProductController {

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IReviewService reviewService;


    @GetMapping("/home")
    public Page<Products> showProduct(@PageableDefault(value = 5) Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("/showProductInfo/{productId}")
    private Products showProductInfo(@PathVariable int productId) {

        Products product = productService.findById(productId).get();

        return product;
    }

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody ProductForm productForm) {
        Products product = new Products();
        ProductTypes productType = productTypeService.findById(productForm.getProductTypeId()).get();

        product.setId(productForm.getId());
        product.setProductTypes(Collections.singletonList(productType));
        product.setColor(productForm.getColor());
        product.setPrice(productForm.getPrice());
        product.setImage(productForm.getImage());
        product.setDescription(productForm.getDescription());
        product.setSize(productForm.getSize());

        productService.save(product);
    }

    @PutMapping("/updateProduct")
    public void updateProduct(@RequestBody ProductForm productForm) {
        Products product = productService.findById(productForm.getId()).get();

        product.setColor(productForm.getColor());
        product.setPrice(productForm.getPrice());
        product.setImage(productForm.getImage());
        product.setDescription(productForm.getDescription());
        product.setSize(productForm.getSize());

        productService.save(product);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteById(productId);
        return "delete product with productId" + productId;
    }

    @GetMapping("/productType")
    public Page<ProductTypes> productTypes(@PageableDefault(value = 5) Pageable pageable){

        return productTypeService.findAll(pageable);
    }

    @PostMapping("/addProductType")
    public ProductTypes addProductType(@RequestBody ProductTypeForm productTypeForm) {
        ProductTypes productTypes = new ProductTypes();

        productTypes.setId(productTypeForm.getId());
        productTypes.setName(productTypeForm.getName());
        productTypes.setNote(productTypeForm.getNote());
        productTypeService.save(productTypes);
        return productTypes;
    }

    @DeleteMapping("/deleteProductType/{productTypeId}")
    public String deleteProductType(@PathVariable int productTypeId){
        productTypeService.delete(productTypeId);
        return "delete product type with id: "+productTypeId;
    }
}
