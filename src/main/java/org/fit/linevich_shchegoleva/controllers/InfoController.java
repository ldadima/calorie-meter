package org.fit.linevich_shchegoleva.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.fit.linevich_shchegoleva.model.UserInfo;
import org.fit.linevich_shchegoleva.services.InfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Api
public class InfoController {
    private final InfoService infoService;

    @PutMapping("/calculate-norm")
    public ResponseEntity<Integer> getNorm(@RequestBody UserInfo info){
        Integer norm = infoService.calculateNorm(info);
        return ResponseEntity.ok(norm);
    }
}
