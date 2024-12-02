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
        List<XYLine> values = List.of(
                new XYLine(
                        "x = cos(y)",
                        DoubleStream
                                .iterate(-50, d -> d + 5)
                                .mapToObj(i -> new double[]{Math.cos(i / 10), i / 10})
                                .limit(20)
                                .toArray(double[][]::new)
                ),
                new XYLine(
                        "y = cos(x)",
                        DoubleStream
                                .iterate(-50, d -> d + 5)
                                .mapToObj(d -> new double[]{d / 10, Math.cos(d / 10)})
                                .limit(20)
                                .toArray(double[][]::new)
                ),
                new XYLine(
                        "x = 1",
                        DoubleStream
                                .iterate(-5, d -> d + 10)
                                .mapToObj(d -> new double[]{1, d})
                                .limit(2)
                                .toArray(double[][]::new)
                ),
                new XYLine(
                        "x = -1",
                        DoubleStream
                                .iterate(-5, d -> d + 10)
                                .mapToObj(d -> new double[]{-1, d})
                                .limit(2)
                                .toArray(double[][]::new)
                ),
                new XYLine(
                        "y = 1",
                        DoubleStream
                                .iterate(-5, d -> d + 10)
                                .mapToObj(d -> new double[]{d, 1})
                                .limit(2)
                                .toArray(double[][]::new)
                ),
                new XYLine(
                        "y = -1",
                        DoubleStream
                                .iterate(-5, d -> d + 10)
                                .mapToObj(d -> new double[]{d, -1})
                                .limit(2)
                                .toArray(double[][]::new)
                )
        );

        Render render = context.eval(
                // language=python
                """
                    import pygal
                    
                    def render_xy_chart(title, values):
                        xy_chart = pygal.XY()
                        xy_chart.title = title
                        for value in values:
                            xy_chart.add(value.tag(), value.values())
                        return xy_chart.render().decode()
                    
                    render_xy_chart
                    """).as(Render.class);

        pythonGeneratedSvg = render.renderChart("XY with GraalPy", values);
    }

    public record XYLine(String tag, double[][] values) {}

    private interface Render {
        String renderChart(String title, List<XYLine> lines);
    }

    @Override
    public String renderChart() {
        return pythonGeneratedSvg;
    }
}
