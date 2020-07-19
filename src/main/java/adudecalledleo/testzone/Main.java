package adudecalledleo.testzone;

import adudecalledleo.testzone.tests.Test;
import com.google.common.collect.ImmutableMap;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class Main {

    public static final String[] SPINNER = {
            "[    ]",
            "[=   ]",
            "[==  ]",
            "[=== ]",
            "[ ===]",
            "[  ==]",
            "[   =]",
            "[    ]",
            "[   =]",
            "[  ==]",
            "[ ===]",
            "[=== ]",
            "[==  ]",
            "[=   ]"
    };
    public static final int INTERVAL = 50;

    private static TerminalInterface ti;

    public static void main(String[] args) {
        try {
            ti = new TerminalInterface("TestZone");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Map<String, Consumer<TerminalInterface>> testMap;
        {
            Map<String, Consumer<TerminalInterface>> testMapM = new HashMap<>();
            ti.startSpinner("Collecting tests... ", INTERVAL, SPINNER);
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage("adudecalledleo.testzone.tests"))
                    .addScanners(new SubTypesScanner()));
            Set<Class<? extends Test>> testSet = reflections.getSubTypesOf(Test.class);
            for (Class<? extends Test> testClass : testSet) {
                Test testObj;
                try {
                    Constructor<? extends Test> testConstructor = testClass.getConstructor();
                    testConstructor.setAccessible(true);
                    testObj = testConstructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    System.err.format(
                            "Could not locate no-parameter constructor for test class \"%s\"",
                            testClass.getName());
                    e.printStackTrace();
                    continue;
                }
                testMapM.put(testObj.getName(), testObj::run);
            }
            testMap = ImmutableMap.copyOf(testMapM);
            ti.finishSpinner("[done]");
        }
        String[] testNames = testMap.keySet().toArray(new String[0]);
        boolean repeat;
        do {
            String testName = testNames[ti.promptList("Select test:", true, 0, testNames)];
            Consumer<TerminalInterface> testConsumer = testMap.get(testName);
            System.out.println("Now running test \"" + testName + "\"...");
            testConsumer.accept(ti);
            repeat = ti.yesOrNo("Run another test?", false);
        } while (repeat);
    }

}
