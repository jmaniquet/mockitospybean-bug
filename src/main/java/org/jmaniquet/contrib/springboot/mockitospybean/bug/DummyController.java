package org.jmaniquet.contrib.springboot.mockitospybean.bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    @Autowired
    private DummyService dummyService;

    @GetMapping
    public ResponseEntity<String> dummy() {
        String value = dummyService.foo();
        return ResponseEntity.ok(dummyService.bar(value));
    }
}
