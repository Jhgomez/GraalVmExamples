package okik.tech.springpygal;

import org.springframework.stereotype.Service;
import okik.tech.springpygal.GraalPyContextConfiguration.GraalPyContext;

@Service
public class PygalServicePurePython implements PygalService {
    public final String pygalXyChartSvg;

    public PygalServicePurePython(GraalPyContext context) {
        this.pygalXyChartSvg = context.eval(
                // language=python
                """
                        from math import cos
                        import pygal
                        
                        xy_chart = pygal.XY()
                        xy_chart.title = 'XY with GraalPy'
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
    public String renderChart() {
        return pygalXyChartSvg;
    }
}
