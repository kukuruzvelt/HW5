package task.users;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BTCtoUSDRate implements Rate{

    final private static String MICROSERVICE_URL = "http://localhost:8081/rate";


    @Override
    public double returnRate() {

        final RestTemplate restTemplate = new RestTemplate();
        final String stringPosts = restTemplate.getForObject(MICROSERVICE_URL, String.class);
        System.out.println(stringPosts);
        Gson g = new Gson();
        CurrentRate r = g.fromJson(stringPosts, CurrentRate.class);

        return r.getRate();
    }
}
