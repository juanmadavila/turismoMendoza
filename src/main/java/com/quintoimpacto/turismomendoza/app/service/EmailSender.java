package com.quintoimpacto.turismomendoza.app.service;

import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EmailSender {

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value("${spring.mail.password}")
    private String mailPassword;

    private Session session;

    private void init() {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
//        System.out.println(mailFrom);
//        System.out.println(mailPassword);


        session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, mailPassword);
            }
        });
    }

    @Async
    private void enviar(String mail, String titulo, String cuerpo) throws Exception {
        init();

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mailFrom));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        message.setSubject(titulo);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(cuerpo, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);

    }

    @Async
    public void sendAcceptedUser(Usuario usuario, Evento evento, boolean aceptado) throws WebException, Exception {

        String conclusion = aceptado ? "aceptada" : "rechazada por feo jeje salu2";
        String cuerpo = "Hola " + usuario.getName() + "! \n"
                + "La solicitud de asistencia al evento '" + evento.getNombre()
                + "' organizado por " + evento.getAnfitrion().getName() + " para el día "
                + evento.getFecha().toString() + "ha sido " + conclusion;

        enviar(usuario.getEmail(), "Invitación del evento", cuerpo);

    }

    @Async
    public void sendEventoDeshabilitado(Evento evento) throws WebException, Exception {

//        Date now = new Date();
//        if (now.before(evento.getFecha())) return;
        System.out.println(mailFrom);
        for (Usuario usuario : evento.getVisitantes()) {
            String cuerpo = "Hola " + usuario.getName() + "! \n"
                    + "El anfitrión del evento " + evento.getNombre()
                    + " de la fecha " + evento.getFecha() + ", " + evento.getHorario()
                    + " ha finalizado el mismo. no me la canceleiner pa";

            enviar(usuario.getEmail(), "Evento finalizado", cuerpo);
        }

    }

}
