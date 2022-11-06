package se331.rest.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
    String firstname;
    String lastname;
    String email;
    String address;
    String age;
    List<AuthorityDTO> authorities;
}