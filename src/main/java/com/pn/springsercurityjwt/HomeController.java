package com.pn.springsercurityjwt;

import com.pn.springsercurityjwt.models.AuthenticationRequest;
import com.pn.springsercurityjwt.models.AuthenticationResponse;
import com.pn.springsercurityjwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

/**
 * created by : pnema
 * on 6/30/2021
 */

@RestController
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager; //need to create bean for AuthenticationManager in webSecurityConfig.

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/")
    public String rootPage() {
        return "<h1> -: JWT APP :- </h1>";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "<h1>Welcome User </h1>";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username or Passoword");
        }
        final UserDetails userDetails= myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwtToken = jwtUtil.generateToken(userDetails);

        return ok(new AuthenticationResponse(jwtToken));
    }
}
