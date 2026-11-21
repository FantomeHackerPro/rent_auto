package fr.ece.rentauto.controllers.api;

import fr.ece.rentauto.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;


}
