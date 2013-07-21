package com.justagameclan.litsys.pvp;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;


public class DrawControl<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;


    public DrawControl() {
        this(new Random());
    }

    public DrawControl(Random random) {
        this.random = random;
    }

    public void add(double weight, E result) {
        if (weight <= 0) return;
        total += weight;
        map.put(total, result);
    }

    public E next(double modifier) {
    	if (modifier > 0)
    	{
    		//modifier is set!
    	} else {
    		modifier = 1;
    	}
        double value = (random.nextDouble() * total) * modifier;
        return map.ceilingEntry(value).getValue();
    }
}
