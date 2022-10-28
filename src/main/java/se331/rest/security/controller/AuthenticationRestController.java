package se331.rest.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;
import se331.rest.repository.DoctorRepository;
import se331.rest.security.dao.AuthorityDao;
import se331.rest.security.dao.UserDao;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.JwtUser;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;
import se331.rest.security.service.UserService;
import se331.rest.security.util.JwtTokenUtil;
import se331.rest.util.ProjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    AuthorityDao authorityDao;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Map result = new HashMap();
        result.put("token", token);
        User user = userRepository.findById(((JwtUser) userDetails).getId()).orElse(null);
        if (user != null) {
            result.put("user", ProjectMapper.INSTANCE.getUserAuthDTO(user));
        }

        return ResponseEntity.ok(result);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws  AuthenticationException{
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = authorityDao.getAuthority(01L);
        authorityRepository.save(authUser);
        User user2 = User.builder()
                .enabled(true)
                .email(user.getEmail())
                .firstname("")
                .lastname("")
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2.getAuthorities().add(authUser);
        authUser.getUsers().add(user2);

        userRepository.save(user2);
        userService.save(user2);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDTO(user2));
    }
    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/changeRole")
    public ResponseEntity<?> changeRole(@RequestBody User user) {
        User output = userService.saveRole(user);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDTO(output));
    }

    @GetMapping("user")
    public ResponseEntity<?> getUserLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page) {
        perPage = perPage == null ? 10 : perPage;
        page = page == null ? 1 : page;
        Page<User> pageOutput;
        pageOutput = userService.getUsers(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();

        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(ProjectMapper.INSTANCE.getUserDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
}
