package okik.tech.springpygal;

import okik.tech.springpygal.GraalPyContextConfiguration.GraalPyContext;
import org.springframework.stereotype.Service;

@Service
public class PygalServicePurePython implements PygalService {
    private final String pythonGeneratedSvg;

    public PygalServicePurePython(GraalPyContext context) {
        pythonGeneratedSvg = context.eval(
                // language=python
                """
                from math import cos
                import pygal
                
                xy_chart = pygal.XY()
                xy_chart.title = 'XY Cosinus'
                xy_chart.add('x = cos(y)', [(cos(x / 10.), x / 10.) for x in range(-50, 50, 5)])
                xy_chart.add('y = cos(x)', [(x / 10., cos(x / 10.)) for x in range(-50, 50, 5)])
                xy_chart.add('x = 1',  [(1, -5), (1, 5)])
                xy_chart.add('x = -1', [(-1, -5), (-1, 5)])
                xy_chart.add('y = 1',  [(-5, 1), (5, 1)])
                xy_chart.add('y = -1', [(-5, -1), (5, -1)])
                xy_chart.render().decode
                """
        ).asString();
    }

    @Override
    public String render() {
        return pythonGeneratedSvg;
    }
}
