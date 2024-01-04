package et.com.gebeya.asqualacomservice.listeners;

import et.com.gebeya.asqualacomservice.dto.StudentDto;
import et.com.gebeya.asqualacomservice.util.Constants;
import et.com.gebeya.asqualacomservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class kafkaListeners {
    private final EmailService emailService;


    @KafkaListener(topics ={Constants.EMAIL_STUDENT_TOPIC, Constants.EMAIL_STUDENT_TOPIC} ,
            groupId = "group1",
            containerFactory = "studentListenerFactory")
    void dtoListener(StudentDto data) {
        System.out.println("Recieved " + data + "㊗");
        emailService.sendEmail(data.getEmail(), "Student created!", String.format("Dear %s, \n Your account is created on Asquala.", data.getName()));
    }
}
