package com.example.standaloneemailservice.model

class EmailModel(
    val receiver: List<String>,
    val subject: String,
    val plainContent: String,
    val htmlContent: String,
    val channel: ChannelType,
) enum class ChannelType {
    SMTP,
    SES,
}
