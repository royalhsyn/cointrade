package com.example.cointrade.service;

import com.example.cointrade.dto.RegistrationDto;
import com.example.cointrade.entity.User;
import com.example.cointrade.exception.NoDataFoundException;
import com.example.cointrade.map.UserMap;
import com.example.cointrade.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepo userRepo;
    private final UserMap userMap;
    private final MailSender mailSender;
    private boolean isSending = false;

    private Queue<SimpleMailMessage> queue = new ConcurrentLinkedQueue<>();

    @Value("${app.registration.base-url}")
    private String baseUrl;

    public void register(RegistrationDto dto) {
        User user = Optional.of(dto)
                .map(userMap::toEntity)
                .map(userRepo::save)
                .orElseThrow();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getUsername());
        message.setFrom("info@good.company");
        message.setSubject("Registration Confirmation");
        message.setText(baseUrl + "/registration/confirmation/" + user.getUuid());
        queue.add(message);

//        System.out.println(baseUrl + "/registration/confirmation/" + user.getUuid());
    }

    public void confirm(UUID uuid) {
        userRepo.findByUuid(uuid)
                .ifPresentOrElse(this::activateUser, () -> {
                    throw new NoDataFoundException();
                });
    }

    protected void activateUser(User user) {
        user.setEnabled(true);
        userRepo.save(user);
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMail() {
        log.info("starting...");
        while (!isSending && !queue.isEmpty()) {
            log.info("found email...");
            isSending = true;
            log.info("sending...");
            mailSender.send(queue.poll());
            log.info("sent 1");
        }
        isSending = false;
        log.info("sending finished");
    }

    public void resend(String email) {
        userRepo.findByUsername(email)
                .filter(user -> !user.isEnabled())
                .map(this::toMessage)
                .ifPresent(queue::add);
    }

    private SimpleMailMessage toMessage(User user) {
        log.info("resend");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getUsername());
        message.setFrom("info@good.company");
        message.setSubject("Registration Confirmation");
        message.setText(baseUrl + "/registration/confirmation/" + user.getUuid());
        return message;
    }
}
