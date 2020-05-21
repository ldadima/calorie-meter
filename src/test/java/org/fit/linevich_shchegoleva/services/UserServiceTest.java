package org.fit.linevich_shchegoleva.services;

import org.fit.linevich_shchegoleva.model.CalculateNormTest;
import org.fit.linevich_shchegoleva.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    // @ParameterizedTest
    // @EnumSource(CalculateNormTest.class)
    // void calculateNorm(CalculateNormTest test) {
    //     when(userRepository.findById(test.getInfo().getLogin())).thenReturn(Optional.of(test.getInfo()));
    //     Integer norm = service.calculateNorm(test.getInfo().getLogin());
    //     assertEquals(test.getNorm(), norm);
    // }
}