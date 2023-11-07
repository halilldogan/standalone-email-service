package com.example.standaloneemailservice.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws")
class CustomPropertyConfig(
    var mailFrom: String,
    var accessKey: String,
    var secretKey: String,
    var region: String,
)
