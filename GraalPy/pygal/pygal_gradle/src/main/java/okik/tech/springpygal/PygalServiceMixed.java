package okik.tech.springpygal;

import okik.tech.springpygal.GraalPyContextConfiguration.GraalPyContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class PygalServiceMixed implements PygalService {
    private final String pythonGeneratedSvg;

    public PygalServiceMixed(GraalPyContext context) {

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
