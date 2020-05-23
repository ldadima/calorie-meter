package org.fit.linevich_shchegoleva.services;

import org.fit.linevich_shchegoleva.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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