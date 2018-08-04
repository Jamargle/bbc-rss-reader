package com.jmlb0003.bbcnews.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.transform.RegistryMatcher
import org.simpleframework.xml.transform.Transform
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.text.SimpleDateFormat
import java.util.*

object ServiceGenerator {

    private const val BBC_NEWS_URL = "http://feeds.bbci.co.uk/"
    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BBC_NEWS_URL)
            .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(createSerializer()))

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = retrofitBuilder
                .client(OkHttpClient.Builder().addInterceptor(createLoggingInterceptor()).build())
                .build()
        return retrofit.create(serviceClass)
    }

    private fun createSerializer() = Persister(AnnotationStrategy(), RegistryMatcher().apply {
        bind(Date::class.java, DateTransformer())
    })

    private fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private class DateTransformer : Transform<Date> {

        private val format = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.getDefault())

        override fun write(value: Date?): String {
            return format.format(value)
        }

        override fun read(value: String?): Date {
            return format.parse(value)
        }

    }

}
