package com.ufmg.streaming.API.Streaming.payment;

import com.ufmg.streaming.API.Streaming.user.User;
import com.ufmg.streaming.API.Streaming.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePayment() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUser(new User());
        Payment payment = Payment.builder().build();

        paymentService.createPayment(paymentRequest);

        // Verifica se o método save foi chamado no repositório
        verify(paymentRepository, times(1)).save(eq(payment));
    }

    @Test
    public void testFindPaymentById() {
        Integer idPayment = 1;
        Payment payment = Payment.builder().build();

        when(paymentRepository.findById(idPayment)).thenReturn(Optional.of(payment));

        Payment result = paymentService.findPaymentById(idPayment);

        assertNotNull(result);
        assertEquals(payment, result);

        // Verifica se o método findById foi chamado no repositório
        verify(paymentRepository, times(1)).findById(eq(idPayment));
    }

    @Test
    public void testFindPaymentByIdNotFound() {
        Integer idPayment = 1;

        when(paymentRepository.findById(idPayment)).thenReturn(Optional.empty());

        Payment result = paymentService.findPaymentById(idPayment);

        assertNull(result);

        // Verifica se o método findById foi chamado no repositório
        verify(paymentRepository, times(1)).findById(eq(idPayment));
    }

    @Test
    public void testEditPayment() {
        Integer idPayment = 1;
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUser(new User());
        Payment existingPayment = Payment.builder().build();

        when(paymentRepository.findById(idPayment)).thenReturn(Optional.of(existingPayment));
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        when(paymentRepository.save(any())).thenReturn(existingPayment);

        Payment result = paymentService.editPayment(paymentRequest, idPayment);

        assertNotNull(result);
        assertEquals(existingPayment, result);

        // Verifica se o método findById e save foram chamados no repositório
        verify(paymentRepository, times(1)).findById(eq(idPayment));
        verify(paymentRepository, times(1)).save(eq(existingPayment));
    }

    @Test
    public void testEditPaymentNotFound() {
        Integer idPayment = 1;
        PaymentRequest paymentRequest = new PaymentRequest();

        when(paymentRepository.findById(idPayment)).thenReturn(Optional.empty());

        Payment result = paymentService.editPayment(paymentRequest, idPayment);

        assertNull(result);

        // Verifica se o método findById não foi chamado no repositório
        verify(paymentRepository, never()).findById(eq(idPayment));
        // Verifica se o método save não foi chamado no repositório
        verify(paymentRepository, never()).save(any());
    }

    @Test
    public void testDeletePayment() {
        Payment payment = Payment.builder().build();

        paymentService.deletePayment(payment);

        // Verifica se o método delete foi chamado no repositório
        verify(paymentRepository, times(1)).delete(eq(payment));
    }
}