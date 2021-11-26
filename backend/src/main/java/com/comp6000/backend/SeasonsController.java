package com.comp6000.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/seasons")
class SeasonsController {

  @GetMapping
  @CrossOrigin
  ResponseEntity<List<String>> getSeasons() {
    return ResponseEntity.ok(List.of("summer", "winter", "autumn", "spring"));
  }

}
