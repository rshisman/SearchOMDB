package com.example.movielocator.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public class RestApiInvoker {

    public <T> ResponseEntity<T> execute(HttpMethod httpMethod, String url, MultiValueMap<String, String> headers, Class<T> responseType) throws Exception {

        ResponseEntity<T> response = null;
        RestTemplate restTemplate = this.getRestTemplate();
        HttpEntity<T> httpEntity = new HttpEntity<T>(headers);

        response = restTemplate.exchange(url, httpMethod, httpEntity, responseType);
        return response;
    }

    public <T> ResponseEntity<T> execute(HttpMethod httpMethod, String url, MultiValueMap<String, String> headers, Class<T> responseType, Object requestEntity) throws Exception {

        ResponseEntity<T> response = null;
        RestTemplate restTemplate = this.getRestTemplate();
        HttpEntity httpEntity = new HttpEntity(requestEntity, headers);

        response = restTemplate.exchange(url, httpMethod, httpEntity, responseType);
        return response;
    }

    private RestTemplate getRestTemplate() throws Exception {

        RestTemplate restTemplate;

        CloseableHttpClient httpClient = createHttpClient();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        requestFactory.setConnectTimeout(60 * 1000);
        requestFactory.setReadTimeout(60 * 1000);
        requestFactory.setConnectionRequestTimeout(20 * 1000);

        restTemplate = new RestTemplate(requestFactory);
        restTemplate.setErrorHandler(new RestApiInvokerErrorHandler());
        return restTemplate;
    }

    private CloseableHttpClient createHttpClient() throws Exception {

        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(new TrustStrategy() {
            @Override
            public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws CertificateException {

                return true;
            }
        });

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), new NoopHostnameVerifier());
        return HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setMaxConnTotal(50)
                .setMaxConnPerRoute(30)
                .evictExpiredConnections()
                .evictIdleConnections((long) 5000, TimeUnit.MILLISECONDS)
                .build();
    }

    public MultiValueMap<String, String> getHeaders(String contentTypeVal, String acceptVal) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", contentTypeVal);
        headers.add("Accept", acceptVal);

        return headers;
    }

    public <T> boolean isResponseOK(ResponseEntity<T> response) {

        if (response == null) {
            return false;
        }

        if (!response.getStatusCode().is2xxSuccessful()) {
            return false;
        }

        return true;
    }

    public <T> RESTApiResponse<T> httpGetForJson(String url, MultiValueMap<String, String> headers, Type responseEntityType) throws Exception {

        ResponseEntity<String> response = execute(HttpMethod.GET, url, headers, String.class);
        if (!isResponseOK(response)) {
            return new RESTApiResponse(response, null);
        }

        Optional<T> resultEntity = convertResponseToJson(response, responseEntityType);
        if (!resultEntity.isPresent()) {
            return new RESTApiResponse(response, null);
        }

        return new RESTApiResponse(response, resultEntity.get());
    }

    public <T> RESTApiResponse<T> httpPostForJson(String url, MultiValueMap<String, String> headers, Object requestEntity, Type responseEntityType) throws Exception {
        return invokeHttpRequestWithJsonResponse(HttpMethod.POST, url, headers, requestEntity, responseEntityType);
    }

    public <T> RESTApiResponse<T> invokeHttpRequestWithJsonResponse(HttpMethod httpMethod, String url, MultiValueMap<String, String> headers, Object requestEntity, Type responseEntityType) throws Exception {

        ResponseEntity<String> response = execute(httpMethod, url, headers, String.class, requestEntity);
        if (!isResponseOK(response)) {
            return new RESTApiResponse(response, null);
        }

        Optional<T> resultEntity = convertResponseToJson(response, responseEntityType);
        if (!resultEntity.isPresent()) {
            return new RESTApiResponse(response, null);
        }

        return new RESTApiResponse(response, resultEntity.get());
    }

    private <T> Optional<T> convertResponseToJson(ResponseEntity<String> response, Type responseEntityType) {

        try {
            Gson gson = new GsonBuilder().create();
            T resultEntity = gson.fromJson(response.getBody(), responseEntityType);
            return Optional.of(resultEntity);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static class RestApiInvokerErrorHandler extends DefaultResponseErrorHandler {

        public void handleError(ClientHttpResponse response) throws IOException {
            // do nothing
        }
    }

}

