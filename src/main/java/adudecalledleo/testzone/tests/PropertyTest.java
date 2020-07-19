package adudecalledleo.testzone.tests;

import adudecalledleo.testzone.TerminalInterface;
import adudecalledleo.testzone.property.MutableProperty;
import adudecalledleo.testzone.property.Properties;
import adudecalledleo.testzone.property.Property;
import adudecalledleo.testzone.property.indexed.IndexedProperties;
import adudecalledleo.testzone.property.indexed.MutableIndexedProperty;

public class PropertyTest extends Test {
    @Override
    public String getName() {
        return "properties";
    }

    private final String strA = "Test Property A";
    private String strB = "Test Property B";
    private final Property<String> strC = Properties.constant("Test!");
    private final MutableProperty<String> strD = Properties.wrap("Test Property C");
    private final String[] array = new String[] { "A", "B", "C", "D" };

    @Override
    public void run(TerminalInterface ti) {
        Property<String> propLazy = Properties.createLazy(() -> "Lazy test!");
        Property<String> propStrA = Properties.create(() -> strA);
        MutableProperty<String> propStrB = Properties.createMutable(() -> strB, val -> strB = val);
        MutableIndexedProperty<String> props = IndexedProperties.combineMutable(propLazy,
                propStrA, propStrB, strC, strD);
        MutableIndexedProperty<String> propArray = IndexedProperties.wrapArrayMutable(array);
        printIndexed(props);
        printIndexed(propArray);
    }

    @SafeVarargs
    private final void printIndexed(MutableIndexedProperty<String>... propCollections) {
        for (MutableIndexedProperty<String> props : propCollections) {
            props.visitMutable((index, getter, setter) -> {
                System.out.println("ORG [" + index + "] = " + getter.get());
                try {
                    setter.accept(getter.get() + " (visited)");
                } catch (Throwable ignored) {
                    System.out.println("MOD [" + index + "] FAIL");
                }
                System.out.println("MOD [" + index + "] = " + getter.get());
            });
        }
    }
}
