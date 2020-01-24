package Storage.controller;

import Storage.entity.Article;
import Storage.entity.Country;
import Storage.entity.Producer;
import Storage.entity.Product;
import Storage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping("/deleteProduct/{id}/storage={storageId}")
    public String deleteProduct(@PathVariable long id, @PathVariable long storageId) {
        productRepository.deleteById(id);
        return "redirect:/storage/id=" + storageId;
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam Map<String, String> form) {
        Product product = null;
        long barcode = 0L;
        String name = "";
        Article article = null;
        long count = 0L;
        Producer producer = null;
        Country country = null;
        if(form.get("id") != null){
            product = productRepository.findAllById(Long.parseLong(form.get("id")));
        }else{
            if (!form.get("barcode").isBlank()) {
                product = productRepository.findAllByBarCode(Long.parseLong(form.get("barcode")));
            }
        }
        if (!form.get("barcode").isBlank()) {
            barcode = Long.parseLong(form.get("barcode"));
        }
        if (!form.get("name").isBlank()) {
            name = form.get("name");
        }
        if (!form.get("article").isBlank()) {
            article = articleRepository.findAllById(Long.parseLong(form.get("article")));
        }
        if (!form.get("count").isBlank()) {
            count = Long.parseLong(form.get("count"));
        }
        if (!form.get("producer").isBlank()) {
            producer = producerRepository.findAllById(Long.parseLong(form.get("producer")));
        }
        if (!form.get("country").isBlank()) {
            country = countryRepository.findAllById(Long.parseLong(form.get("country")));
        }

        if (product == null) {
            if (barcode != 0 && !name.isEmpty() && article != null && count != 0 && producer != null && country != null){
                product = new Product();
                product(form, product, barcode, name, article, count, producer, country, true);
            }
        }else{
            boolean bool = true;
            System.out.println(form.get("check"));
            if(form.get("check") == null){
                bool = false;
            }
            product(form, product, barcode, name, article, count, producer, country, bool);
        }
        return "redirect:/storage/id=" + form.get("storageId");
    }

    public void product(@RequestParam Map<String, String> form, Product product, Long barcode, String name, Article article, Long count, Producer producer, Country country, Boolean bool) {
        product.setBarCode(barcode);
        product.setName(name);
        product.setArticle(article);
        product.setCount(count);
        product.setCountry(country);
        product.setProducer(producer);
        product.setPresence(bool);
        product.setStorage(storageRepository.findAllByIdOrderByName(Integer.parseInt(form.get("storageId"))));
        productRepository.save(product);
    }
}
