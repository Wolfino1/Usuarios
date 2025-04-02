package com.Usuarios.usuarios.infrastructure.endpoints.rest;

import com.Usuarios.usuarios.application.dto.request.SaveSellerRequest;
import com.Usuarios.usuarios.application.dto.response.SaveSellerResponse;
import com.Usuarios.usuarios.application.service.SellerService;
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
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
@Tag(name= "Seller", description = "Controller for Sellers")
public class SellerController {
    private final SellerService sellerService;

    @PostMapping("/")
    @Operation(summary = "Create seller", description = "This method saves a seller", tags =
            {"Seller"}, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description =
            "Creates a new Seller", required = true, content = @Content(mediaType = "application/jason",
            schema = @Schema(implementation = SaveSellerRequest.class))), responses = {@ApiResponse(
            responseCode = "200",
            description = "Seller created successfully",
            content = @Content(mediaType = "application/jason",
                    schema = @Schema (implementation = SaveSellerResponse.class))
    )})
    public ResponseEntity<SaveSellerResponse> save(@RequestBody SaveSellerRequest saveSellerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.save(saveSellerRequest));
    }
}
