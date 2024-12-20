package okik.tech.springpygal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class PygalController {
    private final PygalServicePurePython servicePurePython;
    private final PygalServiceMixed serviceMixed;
    private final PygalServicePureJava servicePureJava;
    private final PygalServiceDynamic serviceDynamic;

    public PygalController(
            PygalServicePurePython servicePurePython,
            PygalServiceMixed serviceMixed,
            PygalServicePureJava servicePureJava,
            PygalServiceDynamic serviceDynamic
    ) {
        this.servicePurePython = servicePurePython;
        this.serviceMixed = serviceMixed;
        this.servicePureJava = servicePureJava;
        this.serviceDynamic = serviceDynamic;
    }

    @GetMapping("/purepython")
    String purePython() {
        return servicePurePython.render();
    }

    @RequestMapping(method = GET, path = "/mixed")
    String mixed() {
        return serviceMixed.render();
    }

    @GetMapping("/purejava")
    String pureJava() {
        return servicePureJava.render();
    }

    @GetMapping("/dynamic")
    String dynamci() {
        return serviceDynamic.render();
    }
}
