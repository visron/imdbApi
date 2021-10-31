package com.davis.imdbsample.network;

import com.davis.imdbsample.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    static Retrofit retrofit = null;
    static String baseUrl = "https://imdb-api.com/en/API/";
    public static Retrofit getApiClient(){
        if (retrofit==null){
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(baseUrl);
            //retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
            retrofitBuilder.addConverterFactory(ScalarsConverterFactory.create());

            retrofitBuilder.client(getUnsafeOkHttpClient().build());
            retrofit = retrofitBuilder.build();
        }
        return retrofit;
}
    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            int KEY_TIMEOUT = 900;
            builder.readTimeout(KEY_TIMEOUT, TimeUnit.SECONDS).connectTimeout(KEY_TIMEOUT, TimeUnit.SECONDS).writeTimeout(KEY_TIMEOUT, TimeUnit.SECONDS);
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            if (BuildConfig.DEBUG) {
                // Can be Level.BASIC, Level.HEADERS, or Level.BODY
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.networkInterceptors().add(httpLoggingInterceptor);

            }
            //builder.addInterceptor(new NetworkConnInterceptor(UBNApplication.getContext()));
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
