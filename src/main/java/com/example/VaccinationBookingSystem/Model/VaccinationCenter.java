package com.example.VaccinationBookingSystem.Model;


import com.example.VaccinationBookingSystem.Enum.CenterType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String centerName;

    @Enumerated(EnumType.STRING)
    CenterType centerType;

    String address;

    @OneToMany(mappedBy = "center",cascade = CascadeType.ALL)
    List<Doctor> doctors = new ArrayList<>();




}
