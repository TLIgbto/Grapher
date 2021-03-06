package grapher.fc;

import grapher.ui.ErrorPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class FunctionFactory {

    static final String NAME = "DynamicFunction";
    static final String PATH = "dyna";

    public static Function createFunction(String expression) {
        File temp = new File(NAME + ".java");
        temp.delete();
        try {
            temp.createNewFile();
            temp.deleteOnExit();

            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            out.write("package grapher.fc;\n");
            out.write("import static java.lang.Math.*;\n");
            out.write("public class DynamicFunction implements Function {\n");
            out.write("	public String toString() { return \"" + expression + "\"; }\n");
            out.write("	public double y(double x) { return " + expression + "; }\n");
            out.write("}\n");
            out.close();
        } catch (IOException e) {
            new ErrorPane("Erreur inattendue lors de la création de la fontion");
            throw new RuntimeException("unable to create file.");
        }

        File path = new File(PATH);
        if (!path.exists()) {
            path.mkdir();
        }

        if (com.sun.tools.javac.Main.compile(new String[]{"-d", PATH, NAME + ".java"}) != 0) {
            new ErrorPane("Expression non supporté");
            throw new RuntimeException("compilation failed");
        }

        URL url = null;
        try {
            url = path.toURL();
        } catch (MalformedURLException e) {
        }

        URLClassLoader loader = new URLClassLoader(new URL[]{url},
                Function.class.getClassLoader());
        Class<Function> DynamicFunction = load(loader);
        Function instance = null;
        try {
            instance = DynamicFunction.newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        return instance;
    }

    @SuppressWarnings("unchecked")
    protected static Class<Function> load(ClassLoader loader) {
        try {
            return (Class<Function>) loader.loadClass("grapher.fc." + NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("unable to load class");
        }
    }
}
