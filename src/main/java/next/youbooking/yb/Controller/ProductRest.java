package next.youbooking.yb.Controller;

import next.youbooking.yb.models.entity.Image;
import next.youbooking.yb.models.entity.Product;
import next.youbooking.yb.repository.ProductRep;
import next.youbooking.yb.security.models.domains.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("${api.endpoint}/product")
public class ProductRest {
    @Autowired
    ProductRep productRep;

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(false,
                "get all product", productRep.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject<?>> saveProduct(@RequestPart List<MultipartFile> images, @RequestPart String name) {
        System.out.println("saveProduct");
        Product product = new Product();
        product.setImages(new ArrayList<>());

        images.forEach(image->{
            Image image1 = new Image();
            try {
                image1.setImage(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            image1.setProduct(product);
            product.getImages().add(image1);
        });
        product.setName(name);
        ResponseObject<Product> responseObject = new ResponseObject<>(false,
                "product created successfully", productRep.save(product));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject<?>> save(@RequestPart MultipartFile image,
                                              @RequestPart String name) throws IOException {
        System.out.println("saveProduct");
        Product product = new Product();
        product.setImages(new ArrayList<>());

        Image image1 = new Image();
        image1.setImage(image.getBytes());
        product.getImages().add(image1);
        product.setName(name);
        ResponseObject<Product> responseObject = new ResponseObject<>(false,
                "product created successfully", productRep.save(product));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


}
