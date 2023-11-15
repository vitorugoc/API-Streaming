package com.ufmg.streaming.API.Streaming.payment;

import com.ufmg.streaming.API.Streaming.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_payment")
public class Payment {
    @Id
    @GeneratedValue
    private Integer id_payment;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
    private String payment_desc;
    private String card_number;
    private Integer cvv;
    private Date expiration_date;
    private String account_holder_name;
}
