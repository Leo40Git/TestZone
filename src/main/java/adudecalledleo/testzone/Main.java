package adudecalledleo.testzone;

import adudecalledleo.testzone.tests.Test;
import com.google.common.collect.ImmutableMap;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, Consumer<Scanner>> testMap;
        {
            Map<String, Consumer<Scanner>> testMapM = new HashMap<>();
            System.out.println("Collecting tests...");
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
                            "Could not locate no-parameter constructor for test class \"%s\"\n",
                            testClass.getName());
                    e.printStackTrace();
                    continue;
                }
                testMapM.put(testObj.getName(), testObj::run);
            }
            testMap = ImmutableMap.copyOf(testMapM);
        }
        while (true) {
            System.out.println("Available tests are:");
            for (String testName : testMap.keySet())
                System.out.format(" - %s%n", testName);
            System.out.print("Select a test: ");
            String testName = in.nextLine();
            Consumer<Scanner> testConsumer = testMap.get(testName);
            if (testConsumer == null) {
                System.out.format("Unknown test \"%s\", try again%n", testName);
                continue;
            }
            System.out.println("Now running test \"" + testName + "\"...");
            testConsumer.accept(in);
            System.out.print("Run another test? [y/N] ");
            String prompt = in.nextLine();
            if (prompt.isEmpty())
                break;
            char yn = prompt.charAt(0);
            if (yn != 'y' && yn != 'Y')
                break;
        }
    }

}
