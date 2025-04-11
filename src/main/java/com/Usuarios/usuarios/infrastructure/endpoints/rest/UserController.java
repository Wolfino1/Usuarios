package com.Usuarios.usuarios.infrastructure.endpoints.rest;

import com.Usuarios.usuarios.application.dto.request.SaveUserRequest;
import com.Usuarios.usuarios.application.dto.response.SaveUserResponse;
import com.Usuarios.usuarios.application.service.UserService;
import com.Usuarios.usuarios.domain.Utils.Constants.DomainConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name= "Users", description = "Controller for User")
public class UserController {
    private final UserService userService;

    @PostMapping("/buyer")
    @Operation(
            summary = "Create buyer user",
            description = "This method saves a user with buyer role",
            tags = {"Users"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Creates a new User with buyer role",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveUserRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveUserResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request"
                    )
            }
    )
    public ResponseEntity<SaveUserResponse> saveBuyer(@RequestBody SaveUserRequest request) {
        SaveUserRequest requestWithRole = new SaveUserRequest(
                request.id(),
                request.name(),
                request.lastname(),
                request.document(),
                request.phoneNumber(),
                request.dateOfBirth(),
                request.email(),
                request.password(),
                DomainConstants.BUYER_ID
        );

        SaveUserResponse response = userService.save(requestWithRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/seller")
    @Operation(
            summary = "Create seller user",
            description = "This method saves a user with seller role",
            tags = {"Users"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Creates a new User with seller role",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveUserRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveUserResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<SaveUserResponse> saveSeller(@RequestBody SaveUserRequest request) {
        SaveUserRequest requestWithRole = new SaveUserRequest(
                request.id(),
                request.name(),
                request.lastname(),
                request.document(),
                request.phoneNumber(),
                request.dateOfBirth(),
                request.email(),
                request.password(),
                DomainConstants.SELLER_ID
        );

        SaveUserResponse response = userService.save(requestWithRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
