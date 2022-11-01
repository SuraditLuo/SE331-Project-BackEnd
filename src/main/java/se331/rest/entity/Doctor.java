package se331.rest.entity;

import lombok.*;
import se331.rest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String firstname;
    String lastname;
    @OneToMany(mappedBy = "doctor")
    @Builder.Default
    List<Patient> inCharge = new ArrayList<>();
    @ElementCollection
    List<String> imageUrls;
    @OneToOne(cascade = {CascadeType.ALL})
    User user;
}
