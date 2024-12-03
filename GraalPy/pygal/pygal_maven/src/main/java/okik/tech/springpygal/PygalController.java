package okik.tech.springpygal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class PygalController {
    PygalServicePurePython purePythonService;
    PygalServiceMixed mixedService;
    PygalServicePureJava pureJavaService;

    public PygalController(
            PygalServicePurePython purePythonService,
            PygalServiceMixed mixedService,
            PygalServicePureJava pureJavaService
    ) {
        this.purePythonService = purePythonService;
        this.mixedService = mixedService;
        this.pureJavaService = pureJavaService;
    }

    @GetMapping("/purepython")
    public String purePython() {
        return purePythonService.renderChart();
    }

    @RequestMapping(method = GET, path = "/mixed")
    String mixed() {
        return mixedService.renderChart();
    }

    @RequestMapping(method = GET, path = "/purejava")
    String pureJava() {
        return pureJavaService.renderChart();
    }
}
