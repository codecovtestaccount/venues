package com.grubhub.venuesapi.service

import com.google.gson.Gson
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.cert.CertificateException
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


interface VenueService {

    @GET("browse")
    fun events(@Query("includeSuggested") includeSuggested: Boolean): Single<List<EventResponseModel>>

    companion object Factory {
        fun create(): VenueService {
//            // Load CAs from an InputStream
//            val certificateFactory = CertificateFactory.getInstance("X.509")
//
//            val inputStream = context.resources.openRawResource(R.raw.cert) //(.crt)
//            val certificate = certificateFactory.generateCertificate(inputStream)
//            inputStream.close()
//
//            // Create a KeyStore containing our trusted CAs
//            val keyStoreType = KeyStore.getDefaultType()
//            val keyStore = KeyStore.getInstance(keyStoreType)
//            keyStore.load(null, null)
//            keyStore.setCertificateEntry("ca", certificate)
//
//            // Create a TrustManager that trusts the CAs in our KeyStore.
//            val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
//            val trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)
//            trustManagerFactory.init(keyStore)
//
//            val trustManagers = trustManagerFactory.trustManagers
//            val x509TrustManager = trustManagers[0] as X509TrustManager
//
//            // Create an SSLSocketFactory that uses our TrustManager
//            val sslContext = SSLContext.getInstance("SSL")
//            sslContext.init(null, arrayOf<TrustManager>(x509TrustManager), null)
//            val sslSocketFactory = sslContext.socketFactory
//
//            //create Okhttp client
//            val client = OkHttpClient.Builder()
//                .sslSocketFactory(sslSocketFactory, x509TrustManager)
//                .build()

            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl("https://webservices.vividseats.com/rest/mobile/v1/home/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(client())
                .build()

            return retrofit.create(VenueService::class.java)
        }

        private fun client(): OkHttpClient {
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                })

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory

                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { _, _ -> true }
                return builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }
    }
}