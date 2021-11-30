package com.example.domain;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Data //shortcut of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor together
@Entity
@Table(name ="currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Currency() {
    }

    public Currency(@NonNull String abbreviation, @NonNull double rate, @NonNull int code, @NonNull String name, Instant date) {
        this.abbreviation = abbreviation;
        this.rate = rate;
        this.code = code;
        this.name = name;
        this.date = date;
    }

    @NonNull
    //@Column(unique = true)
    private String abbreviation;

    @NonNull
    private double rate;
    @NonNull
    private int code;
    @NonNull
    private String name;
    @NotNull
    private Instant date;


}
