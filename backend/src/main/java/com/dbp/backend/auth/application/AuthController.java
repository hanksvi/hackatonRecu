package com.dbp.backend.auth.application;

import com.dbp.winproyect.auth.domain.DniValidationService;
import com.dbp.winproyect.auth.domain.RucValidationService;
import com.dbp.winproyect.auth.dto.ClientDtoRegister;
import com.dbp.winproyect.auth.dto.EnterpriseDtoRegister;
import com.dbp.winproyect.auth.dto.FreelancerDtoRegister;
import com.dbp.winproyect.auth.dto.UserLoginDto;
import com.dbp.winproyect.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/auth")

public class AuthController {


}