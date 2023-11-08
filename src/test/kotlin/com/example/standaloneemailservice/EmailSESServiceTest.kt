package com.example.standaloneemailservice

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.SendEmailRequest
import com.example.standaloneemailservice.model.ChannelType
import com.example.standaloneemailservice.model.EmailModel
import com.example.standaloneemailservice.service.EmailService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class EmailSESServiceTest {

    @Autowired
    private lateinit var emailService: EmailService

    @MockBean
    private lateinit var amazonSimpleEmailService: AmazonSimpleEmailService

    @Test
    fun testSendMessage() {
        val to = listOf("receiver@example.com")
        val subject = "Test Subject"
        val plainContent = "Test Plain Content"
        val htmlContent = "Test Html Content"

        val mail = EmailModel(to, subject, plainContent, htmlContent, ChannelType.SES)
        emailService.sendMailWithSES(mail)

        val sendEmailRequestCaptor: ArgumentCaptor<SendEmailRequest> =
            ArgumentCaptor.forClass(SendEmailRequest::class.java)

        verify(amazonSimpleEmailService).sendEmail(sendEmailRequestCaptor.capture())

        val sendEmailRequest = sendEmailRequestCaptor.value

        assertEquals(mail.receiver[0], sendEmailRequest.destination.toAddresses[0])
        assertEquals(mail.subject, sendEmailRequest.message.subject.data)
        assertEquals(mail.htmlContent, sendEmailRequest.message.body.html.data)
        assertEquals(mail.plainContent, sendEmailRequest.message.body.text.data)
    }
}
