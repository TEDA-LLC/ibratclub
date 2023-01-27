package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibratclub.model.enums.Gender;
import com.ibratclub.model.enums.Language;
import com.ibratclub.model.enums.RegisteredType;
import com.ibratclub.model.enums.State;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:01   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username, fullName;
    //    @Column(unique = true)
    private String phone, email;
    //    @Column(unique = true)
    private String chatId;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private State state;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String passportNumber;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Language language;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Avatar avatar;
    @ManyToOne
    private Company company;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Bot bot;
    private int count = 0;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate brithDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registeredTime = LocalDateTime.now();
    @JsonIgnore
    private LocalDateTime lastOperationTime;
    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<ActivityStatus> statusList;
    private boolean active = true;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private RegisteredType registeredType;

    //    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(
//                            name = "uuid_gen_strategy_class",
//                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
//                    )
//            }
//    )
    @JsonIgnore
    @Column(unique = true)
    private UUID qrcode = UUID.randomUUID();

}
