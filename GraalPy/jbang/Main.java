//DEPS org.graalvm.python:python-language:24.1.1
//DEPS org.graalvm.python:python-resources:24.1.1
//DEPS org.graalvm.python:python-launcher:24.1.1
//DEPS org.graalvm.python:python-embedding-tools:24.1.1
//DEPS org.graalvm.python:python-embedding:24.1.1
//PIP qrcode==8.0

import org.graalvm.python.embedding.utils.GraalPyResources;

public class Main {
    public static void main(String[] args) {
        String qrCodeInfo;
        if (args.length == 1) {
            qrCodeInfo = args[0];
        } else {
            throw new RuntimeException("Please provide no more or less than one command line argument");
        }

        try (var context = GraalPyResources.contextBuilder().option("python.PythonHome", "").build()) {
            MakeQrCodeFunction qrCodeFunction = context.eval("python",
                    // languages=python
                    """
                        import qrcode
                        from qrcode.image.svg import SvgPathFillImage
    
                        def make_qrcode(text_info):
                                return qrcode.make(text_info, image_factory=SvgPathFillImage)
    
                        make_qrcode
                        """).as(MakeQrCodeFunction.class);

            SvgImage image = qrCodeFunction.apply(qrCodeInfo);

            image.save("qrCode.svg");
        }
    }


    @FunctionalInterface
    public interface MakeQrCodeFunction {
        SvgImage apply(String info);
    }

    public interface SvgImage {
        void save(String path);
    }
}