package mn.ecommerce.ecommerce.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mn.ecommerce.ecommerce.HttpClient.HttpClient;
import mn.ecommerce.ecommerce.model.OtherProduct;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class OtherProductService {

    private final Logger logger = LoggerFactory.getLogger(OtherProductService.class);
    @Value("${product.url}")
    String url;
    ObjectMapper objectMapper = new ObjectMapper();

    public List<OtherProduct> findALl() {
        HttpClient client = new HttpClient(url, false);
        Response response;
        try {
            response = client.get("/findAll", HttpClient.buildHeaders("content-type", "application/json"));
            String resText = HttpClient.responseString(response);
            if (response.isSuccessful())
                return objectMapper.readValue(resText, new TypeReference<List<OtherProduct>>() {
                });

            throw new RuntimeException(resText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public OtherProduct findById(String id) {
        HttpClient client = new HttpClient(url, false);
        Response response;
        try {
            response = client.get("/findById?id=" + id, HttpClient
                    .buildHeaders("content-type", "application/json"));
            String resText = HttpClient.responseString(response);
            if (response.isSuccessful()) {
                return objectMapper.readValue(resText, new TypeReference<OtherProduct>() {
                });
            }
            throw new RuntimeException(resText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public OtherProduct save(OtherProduct product) {
    HttpClient client = new HttpClient(url, false);
    Response response;
        try{
            response = client.post("/save" ,product,  HttpClient
                    .buildHeaders("content-type", "application/json"));
            String resText = HttpClient.responseString(response);
            if (response.isSuccessful()){
                return objectMapper.readValue(resText, new TypeReference<OtherProduct>() {});
            }
            throw new RuntimeException(resText);

        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

}
