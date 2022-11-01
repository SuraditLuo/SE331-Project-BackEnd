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

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String firstname;
    String lastname;
    String age;
    String address;
    String status;
    @ManyToOne
    Doctor doctor;
    @OneToMany(mappedBy = "commentTo")
    @Builder.Default
    List<Comment> comments = new ArrayList<>();
    @ManyToMany
    @Builder.Default
    List<Vaccine> vaccines = new ArrayList<>();
    @ElementCollection
    List<String> imageUrls;
    @OneToOne(cascade = {CascadeType.ALL})
    User user;

}
