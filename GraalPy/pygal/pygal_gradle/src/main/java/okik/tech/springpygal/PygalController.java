package okik.tech.springpygal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class PygalController {
    private final PygalServicePurePython servicePurePython;
    private final PygalServiceMixed serviceMixed;

    public PygalController(
            PygalServicePurePython servicePurePython,
            PygalServiceMixed serviceMixed
    ) {
        this.servicePurePython = servicePurePython;
        this.serviceMixed = serviceMixed;
    }

    @GetMapping("/purepython")
    String purePython() {
        return servicePurePython.render();
    }

    @RequestMapping(method = GET, path = "/mixed")
    String mixed() {
        return serviceMixed.render();
    }
}
