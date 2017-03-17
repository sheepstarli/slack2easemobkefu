package com.chenxing.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final RequestConfig defaultRequestConfig = getRequestConfig(3000, 3000, 3000);

    private static RequestConfig getRequestConfig(int socketTimeOut, int connectTimeout, int ConnectionRequestTimeout){
    	return RequestConfig.custom()
	      .setSocketTimeout(socketTimeOut)
	      .setConnectTimeout(connectTimeout)
	      .setConnectionRequestTimeout(ConnectionRequestTimeout)
	      .build();
    }
    
    private static final SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setSoReuseAddress(true).setSoTimeout(1000).build();
    private static final ResponseErrorHandler ERROR_HANDLER = new ResponseErrorHandler() {
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
        }
    };
    
    private static final HttpClientBuilder httpClientBuilderRetry;

    private static final ClientHttpRequestFactory clientHttpRequestFactory;

    public static RestTemplate restTemplate;

    static {
    	httpClientBuilderRetry = createCommonHttpClientBuilder()
        		.setDefaultRequestConfig(defaultRequestConfig)
        		.setRetryHandler(new StandardHttpRequestRetryHandler(5, false) {
                    public boolean retryRequest(final IOException exception, final int executionCount, final HttpContext context) {
                        boolean retry = super.retryRequest(exception, executionCount, context);
                        boolean customRetry = (exception instanceof ConnectException || exception instanceof ConnectTimeoutException
                                || exception instanceof SocketTimeoutException || exception instanceof NoHttpResponseException);
                        return (retry || customRetry) && (executionCount < getRetryCount());
                    }
                });
        CloseableHttpClient hc = httpClientBuilderRetry.build();
        
        clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(hc);
        restTemplate =  new RestTemplate(HttpUtil.clientHttpRequestFactory);
        restTemplate.setErrorHandler(ERROR_HANDLER);
    }


	private static HttpClientBuilder createCommonHttpClientBuilder() {
		return HttpClientBuilder.create()
                .setDefaultSocketConfig(socketConfig)
                .setMaxConnTotal(1000)
                .setMaxConnPerRoute(1000)
				.setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
				.setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy() {
                    @Override
                    public boolean retryRequest(final HttpResponse response, final int executionCount,
                            final HttpContext context) {
                        boolean retry = super.retryRequest(response, executionCount, context);
                        boolean customRetry = response.getStatusLine().getStatusCode() == HttpStatus.SC_GATEWAY_TIMEOUT;
                        return (retry || customRetry) && (executionCount < 3);
                    }
                });
	}


}
