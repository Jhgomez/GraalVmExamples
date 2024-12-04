package okik.tech.springpygal;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.graalvm.python.embedding.utils.GraalPyResources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraalPyContextConfiguration {

    @Bean(destroyMethod = "close")
    public GraalPyContext graalPyContext() {
        Context context = GraalPyResources.createContext();
        context.initialize("python");

        return new GraalPyContext(context);
    }

    public record GraalPyContext(Context context) {
        public Value eval(String sourceCode) {
            return context.eval("python", sourceCode);
        }

        public void close() {
            context.close(true);
        }
    }
}
