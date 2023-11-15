package com.ufmg.streaming.API.Streaming.payment;

import com.ufmg.streaming.API.Streaming.profile.Profile;
import com.ufmg.streaming.API.Streaming.profile.ProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<String> createPayment(@RequestBody PaymentRequest request){
        paymentService.createPayment(request);
        return ResponseEntity.ok("Forma de Pagamento criada com sucesso!");
    }

    @GetMapping("/{idPayment}")
    public ResponseEntity<Payment> findPaymentById(@PathVariable Integer idPayment){
        Payment payment = paymentService.findPaymentById(idPayment);
        if(payment == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/edit/{idPayment}")
    public ResponseEntity<Payment> editProfile(@PathVariable Integer idPayment, @RequestBody PaymentRequest request){
        Payment newPayment = paymentService.editPayment(request, idPayment);

        if (newPayment == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(newPayment);
    }

    @DeleteMapping("/delete/{idProfile}")
    public  ResponseEntity<String> deleteProfile(@PathVariable Integer idPayment){
        Payment toDeletePayment = paymentService.findPaymentById(idPayment);
        paymentService.deletePayment(toDeletePayment);

        return ResponseEntity.ok("Forma de Pagamento deletada com sucesso!");
    }

}
