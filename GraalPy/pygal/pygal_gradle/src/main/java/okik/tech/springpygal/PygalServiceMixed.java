package okik.tech.springpygal;

import okik.tech.springpygal.GraalPyContextConfiguration.GraalPyContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class PygalServiceMixed implements PygalService {
    private final String pythonGeneratedSvg;

    public PygalServiceMixed(GraalPyContext context) {
        ;


        Render render = context.eval(
                // language=python
                """
                    import pygal
                    
                    def render_xy_chart(values):
                        xy_chart = pygal.XY()
                        xy_chart.title = "XY with GraalPy"
                        for value in values:
                            xy_chart.add(value.tag(), value.values())
                        return xy_chart.render().decode()
                    
                    render_xy_chart
                    """).as(Render.class);

    }

    public record XYLine(String tag, double[][] values) {}

    private interface Render {
        String renderChart(List<XYLine> lines);
    }

    @Override
    public String render() {
        return pythonGeneratedSvg;
    }
}
