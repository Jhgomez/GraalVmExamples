package okik.tech.springpygal;

import okik.tech.springpygal.GraalPyContextConfiguration.GraalPyContext;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Service;

import java.util.stream.DoubleStream;

@Service
public class PygalServiceDynamic implements PygalService {
    private final String pythonGeneratedSvg;

    public PygalServiceDynamic(GraalPyContext context) {

        Value pygal = context.eval("import pygal; pygal");

        Value chart = pygal.invokeMember("XY");

        chart.putMember("title","XY with GraalPy");

        chart.invokeMember(
                "add",
                "x = cos(y)",
                DoubleStream
                        .iterate(-50, d -> d + 5)
                        .mapToObj(i -> new double[]{Math.cos(i / 10), i / 10})
                        .limit(20)
                        .toArray(double[][]::new)
                );

        chart.invokeMember(
                "add",
                "y = cos(x)",
                DoubleStream
                        .iterate(-50, d -> d + 5)
                        .mapToObj(d -> new double[]{d / 10, Math.cos(d / 10)})
                        .limit(20)
                        .toArray(double[][]::new)
        );

        chart.invokeMember(
                "add",
                "x = 1",
                DoubleStream
                        .iterate(-5, d -> d + 10)
                        .mapToObj(d -> new double[]{1, d})
                        .limit(2)
                        .toArray(double[][]::new)
        );

        chart.invokeMember(
                "add",
                "x = -1",
                DoubleStream
                        .iterate(-5, d -> d + 10)
                        .mapToObj(d -> new double[]{-1, d})
                        .limit(2)
                        .toArray(double[][]::new)
        );

        chart.invokeMember(
                "add",
                "y = 1",
                DoubleStream
                        .iterate(-5, d -> d + 10)
                        .mapToObj(d -> new double[]{d, 1})
                        .limit(2)
                        .toArray(double[][]::new)
        );

        chart.invokeMember(
                "add",
                "y = -1",
                DoubleStream
                        .iterate(-5, d -> d + 10)
                        .mapToObj(d -> new double[]{d, -1})
                        .limit(2)
                        .toArray(double[][]::new)
        );

        pythonGeneratedSvg = chart.invokeMember("render").invokeMember("decode").asString();
    }


    @Override
    public String render() {
        return pythonGeneratedSvg;
    }
}