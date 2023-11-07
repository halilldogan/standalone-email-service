package com.example.standaloneemailservice.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsMailConfig(private val customPropertyConfig: CustomPropertyConfig) {
    @Bean
    fun amazonSimpleEmailService(): AmazonSimpleEmailService {
        return AmazonSimpleEmailServiceClientBuilder.standard()
            .withCredentials(
                AWSStaticCredentialsProvider(
                    BasicAWSCredentials(
                        customPropertyConfig.accessKey,
                        customPropertyConfig.secretKey,
                    ),
                ),
            )
            .withRegion(customPropertyConfig.region)
            .build()
    }
}
