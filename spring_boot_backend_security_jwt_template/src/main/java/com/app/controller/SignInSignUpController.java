package com.app.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.AdminDTO;
import com.app.dto.AuthRequest;
import com.app.dto.RegularUserDTO;
import com.app.dto.UserDTO;
import com.app.dto.WeightGainUserDTO;
import com.app.dto.WeightLossUserDTO;
import com.app.enums.Role;
import com.app.security.JWTUtils;
import com.app.service.UserService;
import net.bytebuddy.asm.Advice.Return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class SignInSignUpController {
	private static final Logger log = LoggerFactory.getLogger(SignInSignUpController.class);
//dep : JWT utils : for generating JWT
	@Autowired
	private JWTUtils utils;
	// dep : Auth mgr
	@Autowired
	private AuthenticationManager manager;
	//dep : user service for handling users
	@Autowired
	private UserService userService;
	
	@Value("${adminSecretKey}")
	private String adminSecretKey;

	// add a method to authenticate user . In case of success --send back token , o.w
	// send back err mesg
	@PostMapping("/signin")
	public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request, HttpServletRequest httpServletRequest) throws Exception {
		// store incoming user details(not yet validated) into Authentication object
		System.out.println(httpServletRequest.getRemoteAddr());
		// Authentication i/f ---> implemented by UserNamePasswordAuthToken
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		log.info("auth token before {}",authToken);
		try {
			// authenticate the credentials
			Authentication authenticatedDetails = manager.authenticate(authToken);
			log.info("auth token again {} " , authenticatedDetails);
			// => auth succcess
			
			Object obj = userService.temporaryValidateUser(request.getEmail());
			if(obj instanceof WeightGainUserDTO) {
				WeightGainUserDTO weightgainuser = (WeightGainUserDTO)obj;
				weightgainuser.setJwt(utils.generateJwtToken(authenticatedDetails));
				weightgainuser.setMessage("Authentication Successfull");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(weightgainuser);
			}
			if(obj instanceof WeightLossUserDTO) {
				WeightLossUserDTO regularuser = (WeightLossUserDTO)obj;
				regularuser.setJwt(utils.generateJwtToken(authenticatedDetails));
				regularuser.setMessage("Authentication Successfull");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(regularuser);
			}
			if(obj instanceof RegularUserDTO) {
				RegularUserDTO regularuser = (RegularUserDTO)obj;
				regularuser.setJwt(utils.generateJwtToken(authenticatedDetails));
				regularuser.setMessage("Authentication Successfull");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(regularuser);
			}
			if(obj instanceof AdminDTO) {
				AdminDTO admin = (AdminDTO)obj;
				Set<Role> loginRole = new HashSet<>();
				loginRole.add(Role.valueOf("ROLE_ADMIN"));
				admin.setRoles(loginRole);
				admin.setJwt(utils.generateJwtToken(authenticatedDetails));
				admin.setMessage("Authentication Successfull");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(admin);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Your role not found");
			
			
			
		} catch (BadCredentialsException e) { // lab work : replace this by a method in global exc handler
			// send back err resp code
			System.out.println("err "+e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}

	}
	//add request handling method for user registration
	@PostMapping("/signup")
	public ResponseEntity<?> userRegistration(@RequestBody @Valid UserDTO user)
	{
		if(!adminSecretKey.equals(user.getAdminSecretKey()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Secret Key you provided");
		System.out.println("in reg user : user "+user+" roles "+user.getRoles());//{....."roles" : [ROLE_USER,...]}
		//invoke service layer method , for saving : user info + associated roles info
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));		
	}
	
	@PostMapping("/register_weightgainuser")
	public ResponseEntity<?> registerweightgainuser(@RequestBody WeightGainUserDTO regularuser){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerWeightGainUser(regularuser));
	}
	
	@PostMapping("/register_weightlossuser")
	public ResponseEntity<?> registerweightlossuser(@RequestBody WeightLossUserDTO weightgainuser) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerWeightLossUser(weightgainuser));
	}
	
	@PostMapping("/register_regularuser")
	public ResponseEntity<?> regularuser(@RequestBody  RegularUserDTO regularuser) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerRegularUser(regularuser));
	}
	
	/*@PatchMapping("/send_otp")
	public ResponseEntity<?> sendOTP(@RequestBody UpdatePasswordDTO updatePasswordDTO) throws Exception{
		userService.sendOTPForForgotPassword(updatePasswordDTO.getEmail());
		return ResponseEntity.status(HttpStatus.OK).body("OTP sent to your email");
		
	}
	
	/*@PatchMapping("/update_password")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO update) throws Exception{
		userService.updateUserPassword(update.getEmail(), update.getNewPassword(), update.getOtp());
		return ResponseEntity.status(HttpStatus.OK).body("Password Updated");
	}*/
}
