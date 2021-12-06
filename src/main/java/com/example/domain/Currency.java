package com.example.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data //shortcut of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor together
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "currency")
    private List<CurrencyRate> currencyRate;

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
