package com.example.standaloneemailservice.service

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.*
import com.example.standaloneemailservice.config.CustomPropertyConfig
import com.example.standaloneemailservice.model.EmailModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val amazonSimpleEmailService: AmazonSimpleEmailService,
    private val customPropertyConfig: CustomPropertyConfig,
    private val javaMailSender: JavaMailSender,
    @Value("\${spring.mail.username}") private val sender: String,
) {

    fun sendMailWithSES(mail: EmailModel): ResponseEntity<String> {
        var body = Body()

        val bodyPlainContent = Content().withCharset("UTF-8").withData(mail.plainContent)
        val bodyHtmlContent = Content().withCharset("UTF-8").withData(mail.htmlContent)
        val subjectContent = Content().withCharset("UTF-8").withData(mail.subject)

        body = body.withHtml(bodyHtmlContent).withText(bodyPlainContent)
        val request = SendEmailRequest()
            .withDestination(
                Destination().withToAddresses(mail.receiver),
            )
            .withMessage(Message().withBody(body).withSubject(subjectContent))
            .withSource(customPropertyConfig.mailFrom)

        amazonSimpleEmailService.sendEmail(request)
        return ResponseEntity.ok().body("Mail sent Successfully to " + mail.receiver + " with subject " + mail.subject)
    }

    fun sendMailWithSMTP(mail: EmailModel): ResponseEntity<String> {
        val mimeMessage = javaMailSender.createMimeMessage()

        val helper = MimeMessageHelper(mimeMessage, true)

        helper.setFrom(sender)
        helper.setTo(mail.receiver.toTypedArray())
        helper.setText(mail.plainContent, mail.htmlContent)
        helper.setSubject(mail.subject)

        javaMailSender.send(mimeMessage)
        return ResponseEntity.ok().body("Mail sent Successfully to " + mail.receiver + " with subject " + mail.subject)
    }
}
