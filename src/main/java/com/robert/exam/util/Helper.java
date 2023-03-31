package com.robert.exam.util;

import com.robert.exam.entity.TestAssignation;
import com.robert.exam.exception.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.UUID;

@Component
public class Helper {

    private static Logger log = LogManager.getLogger();

    @Autowired
    private JavaMailSender mailSender;

    public double assingValue(int total) {
        return 100/total;
    }

    public void sendEmail(TestAssignation instance, String subject) throws EmailException  {
        String zone = instance.getStudent().getTimeZone() != null ? instance.getStudent().getTimeZone().getName() : instance.getTimeZone().getName();

        String realDate = getDateByTimeZone(zone, String.valueOf(instance.getApplicationDate()));

        log.info("fecha {}", realDate);
        instance.setAlumnAplicationDate(Timestamp.valueOf(realDate));

        log.info("Se envia mensaje");

        if (instance.getSended() == null || instance.getSended() == 0) {

            log.info("Aun no se notifica al alumno, enviando");

            String date = this.getDateByTimeZone(zone, String.valueOf(instance.getApplicationDate()));

            String content = "Tienes un examen de " + instance.getTest().getName() + " en la fecha " + date;

            SimpleMailMessage email = new SimpleMailMessage();

            email.setTo(instance.getStudent().getEmail());
            email.setSubject(subject);
            email.setText(content);
            try {
                mailSender.send(email);
            } catch (Exception e) {
                throw new EmailException();
            }

            instance.setSended(1);
        }
    }

    public String getUUID() {
        final String non = UUID.randomUUID().toString();
        return non.replace("-", "");
    }

    public String getDateByTimeZone(String timeZone, String stringDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        df.setTimeZone(TimeZone.getTimeZone(timeZone));

        java.util.Date date = new java.util.Date();

        date = Timestamp.valueOf(stringDate);

        return df.format(date);
    }
}
