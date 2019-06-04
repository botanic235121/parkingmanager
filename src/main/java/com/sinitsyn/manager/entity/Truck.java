package com.sinitsyn.manager.entity;

import java.util.Objects;

/**
 * This class is implementation of the Vehicle interface,
 * allows create entity Truck.
 */

public class Truck implements Vehicle {

    /**
     * Count of created trucks.
     */

    private static long count;
    /**
     * The id of truck.
     */
    private long id;

    public Truck() {
        count++;
        id = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return id == truck.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                '}';
    }
}
