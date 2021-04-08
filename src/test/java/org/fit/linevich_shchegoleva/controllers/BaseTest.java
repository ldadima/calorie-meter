// package org.fit.linevich_shchegoleva.controllers;
//
// import io.restassured.module.mockmvc.RestAssuredMockMvc;
// import org.junit.Before;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
// import org.springframework.test.annotation.DirtiesContext;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvcBuilder;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
// @DirtiesContext
// @AutoConfigureMessageVerifier
// public abstract class BaseTest {
//
//     @Autowired
//     private WebApplicationContext context;
//
//
//     @Before
//     public void setup() {
//         MockMvcBuilder mockMvcBuilder
//                 = MockMvcBuilders.webAppContextSetup(context);
//         RestAssuredMockMvc.standaloneSetup(mockMvcBuilder);
//     }
// }
