package se331.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String surname;
    String age;
    String hometown;
    String status;
    @ElementCollection
    List<String> DComment = new ArrayList<>();
    @ManyToOne
    Doctor doctor;
    @ManyToMany(mappedBy = "patients")
    @Builder.Default
    List<Vaccine> listOfDose = new ArrayList<>();
    @ElementCollection
    List<String> imageUrls;

}
