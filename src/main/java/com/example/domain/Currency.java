package com.example.domain;


import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data //shortcut of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor together
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Currency() {
    }

    public Currency(@NonNull String abbreviation,@NonNull int code, @NonNull String name) {
        this.abbreviation = abbreviation;
        this.code = code;
        this.name = name;
    }

    @NonNull
    @Column(unique = true)
    private String abbreviation;

    @NonNull
    private int code;
    @NonNull
    private String name;


}
