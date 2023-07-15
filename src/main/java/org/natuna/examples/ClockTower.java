package org.natuna.examples;

public class ClockTower {

    Clock clock;

    public ClockTower() {
        this(new Clock());
    }

    public ClockTower(final Clock clock) {
        this.clock = clock;
    }
}
