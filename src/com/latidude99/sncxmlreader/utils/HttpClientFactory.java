package com.latidude99.sncxmlreader.utils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.security.SecureRandom;

@SuppressWarnings("deprecation")
public class HttpClientFactory {

    private static CloseableHttpClient client;

    public static HttpClient getHttpsClient() {

        if (client != null) {
            return client;
        }
        try {
        	SSLContext sslcontext = SSLContexts.custom().useSSL().build();
            sslcontext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            client = HttpClients.custom().setSSLSocketFactory(factory).build();

        }catch(Exception e) {
        	e.getMessage();
        }    
        return client;
    }

    public static void releaseInstance() {
        client = null;
    }
}

















