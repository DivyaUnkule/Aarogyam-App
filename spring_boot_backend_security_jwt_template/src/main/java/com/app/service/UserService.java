package com.app.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import com.app.dto.RegularUserDTO;
import com.app.dto.UserDTO;
import com.app.dto.UserRegResponse;
import com.app.dto.UserResponseDTO;
import com.app.dto.WeightGainUserDTO;
import com.app.dto.WeightLossUserDTO;

import io.swagger.v3.oas.annotations.servers.Server;

public interface UserService {

	UserRegResponse registerUser(UserDTO user);
	
	UserResponseDTO registerRegularUser (RegularUserDTO regularUser);

	
	UserResponseDTO registerWeightGainUser (WeightGainUserDTO weightGainUser);

	UserResponseDTO registerWeightLossUser ( WeightLossUserDTO weightLossUser);
	
	<T> T temporaryValidateUser(String email) throws Exception;

}
