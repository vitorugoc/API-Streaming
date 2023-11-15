package com.ufmg.streaming.API.Streaming.payment;

import com.ufmg.streaming.API.Streaming.user.User;
import com.ufmg.streaming.API.Streaming.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository){
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    public void createPayment(PaymentRequest request) {
        var payment = Payment.builder()
                .user(request.getUser())
                .payment_desc(request.getPayment_desc())
                .card_number(request.getCard_number())
                .cvv(request.getCvv())
                .expiration_date(request.getExpiration_date())
                .account_holder_name(request.getAccount_holder_name())
                .build();
        paymentRepository.save(payment);
    }

    public Payment findPaymentById(Integer idPayment) {
        return paymentRepository.findById(idPayment).orElse(null);
    }

    public Payment editPayment(PaymentRequest request, Integer idPayment) {
        Payment existingPayment = paymentRepository.findById(idPayment).orElse(null);

        if(existingPayment == null){
            return null;
        }
        User user = userRepository.findById(request.getUser().getId()).orElse(null);
        existingPayment.setUser(user);
        existingPayment.setPayment_desc(request.getPayment_desc());
        existingPayment.setCard_number(request.getCard_number());
        existingPayment.setCvv(request.getCvv());
        existingPayment.setExpiration_date(request.getExpiration_date());
        existingPayment.setAccount_holder_name(request.getAccount_holder_name());

        return paymentRepository.save(existingPayment);
    }

    public void deletePayment(Payment payment){ paymentRepository.delete(payment);}
}
