package com.example.standaloneemailservice.controller

import com.example.standaloneemailservice.model.ChannelType
import com.example.standaloneemailservice.model.EmailModel
import com.example.standaloneemailservice.service.EmailService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class EmailController(private val emailService: EmailService) {
    @PostMapping("/email/send")
    fun sendEmail(@RequestBody emailModel: EmailModel): ResponseEntity<String> {
        return when (emailModel.channel) {
            ChannelType.SMTP -> {
                emailService.sendMailWithSMTP(emailModel)
            }
            ChannelType.SES -> {
                emailService.sendMailWithSES(emailModel)
            }
        }
    }
}
