//DEPS org.graalvm.python:python-language:24.1.1
//DEPS org.graalvm.python:python-resources:24.1.1
//DEPS org.graalvm.python:python-embedding:24.1.1
//DEPS org.graalvm.python:python-embedding-tools:24.1.1
//PIP qrcode==8.0

import org.graalvm.python.embedding.utils.GraalPyResources;

public class Main {
    public static void main(String[] args) {
        String qrCodeInfo;
//        if (args[0].length > 1) {
//            throw new RuntimeException("Please provide no more or less than one command line argument");
//        } else {
//            qrCodeInfo = args[0];
//        }

        try (var context = GraalPyResources.contextBuilder().option("python.PythonHome", "").build()) {
//            MakeQrCodeFunction qrCodeFunction = context.eval("python", """
//                        import qrcode
//                        # from qrcode.image.pure import PyPNGImage
//                        from qrcode.image.svg import SvgPathFillImage
//
//                        def make_qrCode(text_info):
//                                return qrcode.make(text_info, image_factory=SvgPathFillImage)
//
//                        make_qrCode
//                    """).as(MakeQrCodeFunction.class);

//            SvgImage image = qrCodeFunction.apply("https://github.com/graalvm/graal-languages-demos/blob/main/graalpy/graalpy-jbang-qrcode/qrcode.java");
//
//            System.out.print(image.toString());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    @FunctionalInterface
    public interface MakeQrCodeFunction {
        SvgImage apply(String info);
    }

    public interface SvgImage {
        String toString();
    }
}