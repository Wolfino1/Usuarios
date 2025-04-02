package com.Usuarios.common.configurations.beans;

import com.Usuarios.usuarios.domain.ports.in.SellerServicePort;
import com.Usuarios.usuarios.domain.ports.out.SellerPersistencePort;
import com.Usuarios.usuarios.domain.usecases.SellerUseCase;
import com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql.SellerPersistenceAdapter;
import com.Usuarios.usuarios.infrastructure.mappers.SellerEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final SellerRepository sellerRepository;
    private final SellerEntityMapper sellerEntityMapper;

    @Bean
    public SellerServicePort sellerServicePort() {
        return new SellerUseCase(sellerPersistencePort());
    }

    @Bean
    public SellerPersistencePort sellerPersistencePort() {
        return new SellerPersistenceAdapter(sellerRepository, sellerEntityMapper);
    }

}
