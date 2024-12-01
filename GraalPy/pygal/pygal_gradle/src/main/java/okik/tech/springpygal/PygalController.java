package okik.tech.springpygal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PygalController {
    private final PygalServicePurePython servicePurePython;

    public PygalController(
            PygalServicePurePython servicePurePython
    ) {
        this.servicePurePython = servicePurePython;
    }

    @GetMapping("/purepython")
    String purePython() {
        return servicePurePython.render();
    }
}
