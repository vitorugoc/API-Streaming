package com.ufmg.streaming.API.Streaming.payment;

import com.ufmg.streaming.API.Streaming.payment.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePayment() {
        PaymentRequest paymentRequest = new PaymentRequest();
        ResponseEntity<String> responseEntity = paymentController.createPayment(paymentRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Forma de Pagamento criada com sucesso!", responseEntity.getBody());

        // Verifica se o método createPayment foi chamado no serviço
        verify(paymentService, times(1)).createPayment(eq(paymentRequest));
    }

    @Test
    public void testFindPaymentById() {
        Integer idPayment = 1;
        Payment payment = new Payment();
        when(paymentService.findPaymentById(idPayment)).thenReturn(payment);

        ResponseEntity<Payment> responseEntity = paymentController.findPaymentById(idPayment);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(payment, responseEntity.getBody());

        // Verifica se o método findPaymentById foi chamado no serviço
        verify(paymentService, times(1)).findPaymentById(eq(idPayment));
    }

    @Test
    public void testEditPayment() {
        Integer idPayment = 1;
        PaymentRequest paymentRequest = new PaymentRequest();
        Payment editedPayment = new Payment();

        when(paymentService.editPayment(eq(paymentRequest), eq(idPayment))).thenReturn(editedPayment);

        ResponseEntity<Payment> responseEntity = paymentController.editProfile(idPayment, paymentRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(editedPayment, responseEntity.getBody());

        // Verifica se o método editPayment foi chamado no serviço
        verify(paymentService, times(1)).editPayment(eq(paymentRequest), eq(idPayment));
    }

    @Test
    public void testDeletePayment() {
        Integer idPayment = 1;
        Payment toDeletePayment = new Payment();

        when(paymentService.findPaymentById(idPayment)).thenReturn(toDeletePayment);

        ResponseEntity<String> responseEntity = paymentController.deleteProfile(idPayment);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Forma de Pagamento deletada com sucesso!", responseEntity.getBody());

        // Verifica se o método findPaymentById e deletePayment foram chamados no serviço
        verify(paymentService, times(1)).findPaymentById(eq(idPayment));
        verify(paymentService, times(1)).deletePayment(eq(toDeletePayment));
    }
}
