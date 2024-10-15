package tn.houssem.multimail;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("mail")
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping
    public String sayHello() {
        return "Hello, from houssem-eddin!";
    }

    @GetMapping("/send")
    public void sendEmail() {
        try {
            // Load and parse the JSON file
            ObjectMapper mapper = new ObjectMapper();
            List<EmailRecipient> recipients = mapper.readValue(
                    new File("src/main/resources/mails.json"),
                    mapper.getTypeFactory().constructCollectionType(List.class, EmailRecipient.class)
            );
            for (EmailRecipient recipient : recipients) {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                helper.setTo(recipient.getEmail());
                helper.setSubject("Demande de Stage de Projet de Fin d’Études");
                String name = (recipient.getName() == null || recipient.getName().trim().isEmpty())
                        ? "Madame, Monsieur"
                        : recipient.getName();
                helper.setText("Bonjour " + name + ",\n\n" +
                        "Je suis étudiant en 3ème année du cycle d'ingénieur en Informatique à ESPRIT.\n" +
                        "Je souhaite effectuer un stage au sein de votre société pendant 26 semaines.\n" +
                        "Cordialement.");
                emailSender.send(message);
                System.out.println("Email sent to: " + recipient.getEmail());
            }
        } catch (MessagingException | IOException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}

