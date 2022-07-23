package mn.ecommerce.ecommerce.HttpClient;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.String;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private OkHttpClient client;
    private ObjectMapper mapper;

    private final String host;
    private final static Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private static final int defaultTimeOut = 60;

    private static final int defaultMaxIdleConnections = 2;

    private static final Long defaultKeepIdleDuration = 15L;

    private static final TimeUnit measure = TimeUnit.SECONDS;
    private static final String encoding = "UTF-8";
    private static final String delimiter = "&";
    private static final String equalizer = "=";
    private static final String delimiterF = "?";

    private MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    public HttpClient(String host, boolean sslVerify) {
        this.host = host;
        initRequest(sslVerify);
    }

    public <T extends Interceptor> HttpClient(String host, boolean sslVerify, T interceptor) {
        this.host = host;
        OkHttpClient.Builder builder = initBuilder(defaultTimeOut, measure, new ConnectionPool(defaultMaxIdleConnections, defaultKeepIdleDuration, TimeUnit.SECONDS), sslVerify);
        builder.addInterceptor(interceptor);
        this.client = builder.build();
    }

    public HttpClient(String host, int timeout, TimeUnit measure, boolean sslVerify) {
        this.host = host;
        initRequest(timeout, measure, sslVerify);
    }

    public HttpClient(String host, int timeout, TimeUnit measure, ConnectionPool connectionPool, boolean sslVerify) {
        this.host = host;
        initRequest(timeout, measure, connectionPool, sslVerify);
    }

    public OkHttpClient getInstance() {
        return this.client;
    }

    private void initMapper() {
        this.mapper = new ObjectMapper();
    }

    private void initRequest(boolean sslVerify) {
        this.initRequest(defaultTimeOut, measure, sslVerify);

    }

    private void initRequest(int timeout, TimeUnit measure, boolean sslVerify) {
        this.initRequest(timeout, measure,
                new ConnectionPool(defaultMaxIdleConnections, defaultKeepIdleDuration, TimeUnit.SECONDS),
                sslVerify);
    }

    private OkHttpClient.Builder initBuilder(int timeout, TimeUnit measure, ConnectionPool connectionPool, boolean sslVerify) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(timeout, measure)
                .connectionPool(connectionPool);

        if (!sslVerify) {
            builder.hostnameVerifier((s, sslSession) -> true);
            try {
                TrustManager[] trustManagers = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustManagers, null);
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory, trustManager);

            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                logger.error(e.getMessage());
            }
        }
        return builder;
    }

    private void initRequest(int timeout, TimeUnit measure, ConnectionPool connectionPool, boolean sslVerify) {
        this.initMapper();
        OkHttpClient.Builder builder = initBuilder(timeout, measure, connectionPool, sslVerify);
        this.client = builder.build();
    }

    /**
     * Хүсэлтийн Body бэлтгэх
     */
    public RequestBody body(String body) {
        return RequestBody.create(mediaType, body);
    }

    /**
     * Зарим хүсэлтүүдийн MediaType-ийг оноох
     */
    public void setMediaType(String mediaType) {
        this.mediaType = MediaType.parse(mediaType);
    }

    /**
     * Хүсэлтийн толгой бэлтгэх
     */
    public static Headers buildHeaders(Map<String, String> headers) {
        return Headers.of(headers);
    }

    /**
     * Хүсэлтийн толгой бэлтгэх
     */
    public static Headers buildHeaders(String... namesAndValues) {
        return Headers.of(namesAndValues);
    }

    /**
     * POST төрлийн хүсэлт
     *
     * @param url
     * @param payload
     * @param headers
     * @return
     * @throws IOException
     */
    public Response post(String url, Object payload, Headers headers) throws IOException {
        return this.processRequest(url, payload, headers, RequestMethod.POST);
    }

    public Response post(String url, RequestBody body, Headers headers) throws IOException {
        return this.processRequest(url, body, headers, RequestMethod.POST);
    }

    public Response post(Object payload, Headers headers) throws IOException {
        return this.processRequest(this.host, payload, headers, RequestMethod.POST);
    }

    public Response post(String json, Headers headers) throws IOException {
        return this.request(this.host, body(json), headers, RequestMethod.POST);
    }

    public Response asyncPost(String json, Headers headers, Callback callback) throws IOException {
        return this.request(this.host, body(json), headers, RequestMethod.POST, callback);
    }

    /**
     * GET төрлийн хүсэлт
     *
     * @param url
     * @param payload
     * @param headers
     * @return
     */
    public Response get(String url, Object payload, Headers headers) throws IOException {
        return this.processRequest(url, payload, headers, RequestMethod.GET);
    }

    public Response get(Object payload, Headers headers) throws IOException {
        return this.processRequest(this.host, payload, headers, RequestMethod.GET);
    }

    public Response get(String url, Headers headers) throws IOException {
        return this.processRequest(url, null, headers, RequestMethod.GET);
    }

    public Response get(Headers headers) throws IOException {
        return this.processRequest(this.host, null, headers, RequestMethod.GET);
    }


    /**
     * GET төрлийн хүсэлт + queryParameter-үүдийг URLEncode хийж бэлтгэх
     *
     * @param url
     * @param payload
     * @param headers
     * @param queryParams
     * @return
     * @throws IOException
     */
    public Response get(String url, Object payload, Headers headers, Map<String, Object> queryParams) throws IOException {
        return this.processRequest(url + this.processQueryParams(queryParams), payload, headers, RequestMethod.GET);
    }

    public Response get(Object payload, Headers headers, Map<String, Object> queryParams) throws IOException {
        return this.processRequest(this.host + this.processQueryParams(queryParams), payload, headers, RequestMethod.GET);
    }

    public Response get(String url, Headers headers, Map<String, Object> queryParams) throws IOException {
        return this.processRequest(url + this.processQueryParams(queryParams), null, headers, RequestMethod.GET);
    }

    public Response get(Headers headers, Map<String, Object> queryParams) throws IOException {
        return this.processRequest(this.host + this.processQueryParams(queryParams), null, headers, RequestMethod.GET);
    }


    /**
     * PUT төрлийн хүсэлт
     *
     * @param url
     * @param payload
     * @param headers
     * @return
     */
    public Response put(String url, Object payload, Headers headers) throws IOException {
        return this.processRequest(url, payload, headers, RequestMethod.PUT);
    }

    public Response put(Object payload, Headers headers) throws IOException {
        return this.processRequest(this.host, payload, headers, RequestMethod.PUT);
    }

    /**
     * DELETE төрлийн хүсэлт
     *
     * @param url
     * @param payload
     * @param headers
     * @return
     */
    public Response delete(String url, Object payload, Headers headers) throws IOException {
        return this.processRequest(url, payload, headers, RequestMethod.DELETE);
    }

    /**
     * String төрлийн body-гоор өгөгдлийг бэлтгэх
     *
     * @param url
     * @param body
     * @param headers
     * @param method
     * @return
     * @throws IOException
     */
    private Response processRequest(String url, RequestBody body, Headers headers, RequestMethod method) throws IOException {
        return this.request(url, body, headers, method);
    }

    /**
     * Object төрлийн payload-ийг String body болгож хүсэлтийг бэлтгэх
     *
     * @param url
     * @param payload
     * @param headers
     * @param method
     * @return
     * @throws IOException
     */
    private Response processRequest(String url, Object payload, Headers headers, RequestMethod method) throws IOException {
        RequestBody body = this.body(this.processBodyWithMediaType(payload));
        if (Objects.isNull(headers)) return this.request(url, body, null, method);
        return this.request(url, body, headers, method);
    }

    /**
     * application/json эсвэл www-url-encoded эсэхээс хамааруулж хүсэлтийн биеийг боловсруулах
     *
     * @param payload
     * @return
     * @throws JsonProcessingException
     */
    private String processBodyWithMediaType(Object payload) throws JsonProcessingException {
        // todo: www-url-encoded нөхцөлд зориулсан үйлдлийг хийх
        return mapper.writeValueAsString(payload);
    }

    // todo: тест хийх, эхэнд байх ? болон төгсгөлд орших & нь цаад сервисийн ажиллагаанд саад учруулахгүй гэж тооцсон
    private String processQueryParams(Map<String, Object> queryParams) throws UnsupportedEncodingException {
        String query = "";
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            String value = String.valueOf(entry.getValue());
            query = query
                    .concat(entry.getKey())
                    .concat(equalizer)
                    .concat(URLEncoder.encode(value, encoding))
                    .concat(delimiter);
        }
        return delimiterF + query;
    }

    private Response request(String path, RequestBody body, Headers headers, RequestMethod method) throws IOException {
        return this.request(path, body, headers, method, null);
    }

    /**
     * Хүсэлтийг төрөлтэй нь цуг боловсруулах
     *
     * @param path
     * @param body
     * @param headers
     * @param method
     * @return
     */
    private Response request(String path, RequestBody body, Headers headers, RequestMethod method, Callback callback) throws IOException {
        String requestUrl = this.host.equals(path) ? this.host : this.host.concat(path);
        Request.Builder requestBuilder = new Request.Builder()
                .headers(headers)
                .url(requestUrl);
        Request request = this.initRequestMethod(requestBuilder, method, body).build();
        if (callback == null) {
            return client.newCall(request).execute();
        }
        client.newCall(request).enqueue(callback);
        return null;
    }

    /**
     * Хүсэлтийг төрлөөс нь хамааруулж боловсруулах
     *
     * @param request
     * @param method
     * @param body
     * @return
     */
    private Request.Builder initRequestMethod(Request.Builder request, RequestMethod method, RequestBody body) {
        switch (method) {
            case GET:
                request.get();
                break;
            case PUT:
                request.put(body);
                break;
            case DELETE:
                request.delete(body);
                break;
            default:
                request.post(body);
        }
        return request;
    }

    public static String responseString(Response response) throws IOException {
        ResponseBody body = response.body();
        String responseString = Objects.isNull(body) ? "" : body.string();
        response.close();
        return responseString;
    }

    public static String responseExceptionString(Response response) {
        String result = response.message();
        response.close();
        return result;
    }

    public static String isSuccess(Response response) {
        if (!response.isSuccessful())
            try {
                String body = responseString(response);
                if (StringUtils.hasText(body))
                    throw new RuntimeException(body);
                throw new RuntimeException(responseExceptionString(response));
            } catch (RuntimeException ex) {
                throw ex;
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
            }
        try {
            return responseString(response);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
