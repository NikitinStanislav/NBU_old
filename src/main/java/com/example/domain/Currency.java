package com.example.domain;


import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data //shortcut of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor together
@Entity
@Table(name ="currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Currency() {
    }

    public Currency(@NonNull String abbreviation, @NonNull double rate, @NonNull int code, @NonNull String name) {
        this.abbreviation = abbreviation;
        this.rate = rate;
        this.code = code;
        this.name = name;
    }

    @NonNull
    private String abbreviation;
    @NonNull
    private double rate;
    @NonNull
    private int code;
    @NonNull
    private String name;


}
