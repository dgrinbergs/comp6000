package com.comp6000.backend.genetic.feature.corner;

public class CornerPerimeter {

    private final Corner north;
    private final Corner east;
    private final Corner south;
    private final Corner west;

    public CornerPerimeter(Corner corner) {
        this.north = corner;
        this.east = corner;
        this.south = corner;
        this.west = corner;
    }

    public CornerPerimeter(Corner north, Corner east, Corner south, Corner west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    public Corner getNorth() {
        return north;
    }

    public Corner getEast() {
        return east;
    }

    public Corner getSouth() {
        return south;
    }

    public Corner getWest() {
        return west;
    }
}
