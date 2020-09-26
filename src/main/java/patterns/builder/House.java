package patterns.builder;

public class House {

    private int doors;
    private int walls;
    private int windows;
    private int roof;
    private int garage;

    private House(HouseBuilder houseBuilder) {
        this.doors = houseBuilder.doors;
        this.walls = houseBuilder.walls;
        this.windows = houseBuilder.windows;
        this.roof = houseBuilder.roof;
        this.garage = houseBuilder.garage;
    }

    public int getDoors() {
        return doors;
    }

    public int getWalls() {
        return walls;
    }

    public int getWindows() {
        return windows;
    }

    public int getRoof() {
        return roof;
    }

    public int getGarage() {
        return garage;
    }

    public static class HouseBuilder {
        private int doors;
        private int walls;
        private int windows;
        private int roof;
        private int garage;

        public HouseBuilder(int doors) {
            this.doors = doors;
        }

        public HouseBuilder buildDoors(int doors) {
            this.doors = doors;
            return this;
        }

        public HouseBuilder buildWalls(int walls) {
            this.walls = walls;
            return this;
        }

        public HouseBuilder buildWindows(int windows) {
            this.windows = windows;
            return this;
        }

        public HouseBuilder buildRoof(int roof) {
            this.roof = roof;
            return this;
        }

        public HouseBuilder buildGarage(int garage) {
            this.garage = garage;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}
