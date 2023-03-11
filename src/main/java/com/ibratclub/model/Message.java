package com.ibratclub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "prot_sms")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String rezult;
    @Column(columnDefinition = "text")
    private String zapros;
    @Column(length = 150)
    private String platforma;

    @Column(length = 50)
    private String tel, employeePhone;

    @Column(nullable = false)
    private int flag = 0;

    private LocalDateTime sana;

    public Message(String zapros, String rezult, String platforma, String tel, int flag) {
        this.zapros = zapros;
        this.rezult = rezult;
        this.platforma = platforma;
        this.tel = tel;
        this.flag = flag;
    }
}
