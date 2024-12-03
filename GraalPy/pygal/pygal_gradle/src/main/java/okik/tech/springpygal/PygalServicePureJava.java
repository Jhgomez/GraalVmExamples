package okik.tech.springpygal;

import okik.tech.springpygal.GraalPyContextConfiguration.GraalPyContext;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.stream.DoubleStream;

@Service
public class PygalServicePureJava implements PygalService {
    private final String pythonGeneratedSvg;

    public PygalServicePureJava(GraalPyContext context) {

        Pygal pygal = context.eval("import pygal; pygal").as(Pygal.class);

        XY chart = pygal.XY();

        chart.title("XY with GraalPy");

        chart.add(
                "x = cos(y)",
                DoubleStream
                        .iterate(-50, d -> d + 5)
                        .mapToObj(i -> new double[]{Math.cos(i / 10), i / 10})
                        .limit(20)
                        .toArray(double[][]::new)
        );

        chart.add(
                "y = cos(x)",
                DoubleStream
                        .iterate(-50, d -> d + 5)
                        .mapToObj(d -> new double[]{d / 10, Math.cos(d / 10)})
                        .limit(20)
                        .toArray(double[][]::new)
        );

        chart.add(
                "x = 1",
                DoubleStream
                        .iterate(-5, d -> d + 10)
                        .mapToObj(d -> new double[]{1, d})
                        .limit(2)
                        .toArray(double[][]::new)
        );

        chart.add(
                "x = -1",
                DoubleStream
                        .iterate(-5, d -> d + 10)
                        .mapToObj(d -> new double[]{-1, d})
                        .limit(2)
                        .toArray(double[][]::new)
        );

        chart.add(
                "y = 1",
                DoubleStream
                        .iterate(-5, d -> d + 10)
                        .mapToObj(d -> new double[]{d, 1})
                        .limit(2)
                        .toArray(double[][]::new)
        );

        chart.add(
                "y = -1",
                DoubleStream
                        .iterate(-5, d -> d + 10)
                        .mapToObj(d -> new double[]{d, -1})
                        .limit(2)
                        .toArray(double[][]::new)
        );

        byte[] decodedBytes = Base64.getDecoder().decode(chart.render());
        pythonGeneratedSvg = new String(decodedBytes);
    }

    public interface Pygal {
        XY XY();
    }

    public interface XY {
        default void title(String title) {
            Value.asValue(this).putMember("title", title);
        }

        void add(String tag, double[][] values);

        String render();
    }

    @Override
    public String render() {
        return "pythonGeneratedSvg";
    }
}