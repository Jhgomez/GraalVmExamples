package okik.tech;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.python.embedding.utils.GraalPyResources;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String input = args.length > 0 ? args[0] : "how far is the sun from earth?";

        try (Context context = GraalPyResources.createContext()) {

            CreateChatCompletionFunction createChatCompletion = context.eval("python",
                    // language=python
                    """
                        import os
                        from openai import OpenAI
                        
                        client = OpenAI(
                            api_key= os.environ.get("OPENAI_API_KEY"),
                        )
                    
                        def create_chat_completion(input):
                            return client.chat.completions.create(
                                model="gpt-4o-mini",
                                messages=[
                                    {
                                        "role": "user",
                                        "content": input
                                    }
                                ]
                            )
                        
                        create_chat_completion
                        """).as(CreateChatCompletionFunction.class);

            ChatCompletion chatCompletion = createChatCompletion.apply(input);

            for(Choice choice : chatCompletion.choices()) {
                System.out.println(choice.message().content());
            }
        } catch(PolyglotException e) {
            throw new RuntimeException(e);
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