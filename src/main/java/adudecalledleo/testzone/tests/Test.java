package adudecalledleo.testzone.tests;

import adudecalledleo.testzone.TerminalInterface;

public abstract class Test {
    public Test() { }

    public abstract String getName();
    public abstract void run(TerminalInterface ti);
}
