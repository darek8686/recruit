package pl.decerto.recruit.dk.business.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.decerto.recruit.dk.business.api.DataProvider;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Component
@Qualifier("restRandom")
class RandomOrgProvider implements DataProvider<BigDecimal> {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${org.random.apikey}")
    private String API_KEY;
    @Value("${org.random.apiurl}")
    private String API_URL;

    @Override
    public BigDecimal generate() {
        final RandomIntegerRequest REQUEST = new RandomIntegerRequest(API_KEY);
        URI url = UriComponentsBuilder.fromUriString(API_URL).build().toUri();
        RandomIntegerResponse body = restTemplate.postForEntity(url, REQUEST, RandomIntegerResponse.class).getBody();
        assert body != null;
        Integer random = body.result.random.data.get(0);
        return BigDecimal.valueOf(random);
    }

    private static class RandomIntegerRequest {
        public final Integer id = 1;
        public final String jsonrpc = "2.0";
        public final String method = "generateIntegers";
        public final Params params;

        RandomIntegerRequest(final String apiKey) {
            this.params = new Params(apiKey);
        }

        private static class Params {
            public final String apiKey;
            public final Integer n = 1;
            public final Integer min = -10000000;
            public final Integer max = 10000000;

            Params(final String apiKey) {
                this.apiKey = apiKey;
            }
        }
    }

    private static class RandomIntegerResponse {

        public Result result;

        private static class Result {
            public Random random;

            private static class Random {
                public List<Integer> data;
            }
        }
    }
}
