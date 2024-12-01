package okik.tech.springpygal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PygalController {
    PygalServicePurePython purePythonService;

    public PygalController(
            PygalServicePurePython purePythonService
    ) {
        this.purePythonService = purePythonService;
    }

    @GetMapping("/purepython")
    public String purePython() {
        return purePythonService.renderChart();
    }
}
