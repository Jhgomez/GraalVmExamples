package okik.tech.springpygal;

import okik.tech.springpygal.GraalPyContextConfiguration.GraalPyContext;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class PygalServicePureJava implements PygalService {

    public PygalServicePureJava(GraalPyContext context) {
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

    private interface Render {
        String renderChart(String title, List<PygalServiceMixed.XYLine> lines);
    }

    @Override
    public String render() {
        return "pythonGeneratedSvg";
    }
}