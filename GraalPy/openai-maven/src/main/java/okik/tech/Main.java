package okik.tech;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.graalvm.python.embedding.utils.GraalPyResources;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Context context = GraalPyResources.createContext()) {

        }
    }

    @FunctionalInterface
    public interface CreateChatCompletionFunction {
        ChatCompletion apply(String content);
    }

    public interface ChatCompletion {
        List<Choice> choices();
    }

    public interface Choice {
        ChatCompletionMessage message();
    }

    public interface ChatCompletionMessage {
        String content();
    }
}