package pl.decerto.recruit.dk.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.decerto.recruit.dk.business.api.ComputeService;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class MainController {

    private final ComputeService<BigDecimal> computeService;

    @PostMapping(path = "/api/compute")
    public BigDecimal compute() {
        return computeService.compute();
    }
}
