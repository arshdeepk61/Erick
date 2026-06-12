package builder;

/**
 * PRODUCT — the complex object we want to build.
 *
 * Why the Builder pattern?
 *  - A Computer has many parts. Some are required (cpu, ram), many are optional
 *    (gpu, storage, wifi, bluetooth...).
 *  - Without Builder you end up with "telescoping constructors":
 *        new Computer(cpu, ram)
 *        new Computer(cpu, ram, gpu)
 *        new Computer(cpu, ram, gpu, storage)
 *        ...
 *    which are hard to read and easy to get wrong (which boolean was which?).
 *  - Builder lets us construct the object step by step with readable, named calls
 *    and produce an immutable result.
 */
public class Computer {

    // Required parameters
    private final String cpu;
    private final int ramGb;

    // Optional parameters
    private final String gpu;
    private final int storageGb;
    private final boolean hasWifi;
    private final boolean hasBluetooth;

    // Private constructor: the ONLY way to create a Computer is through the Builder.
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ramGb = builder.ramGb;
        this.gpu = builder.gpu;
        this.storageGb = builder.storageGb;
        this.hasWifi = builder.hasWifi;
        this.hasBluetooth = builder.hasBluetooth;
    }

    // Getters only — no setters, so the object is immutable after construction.
    public String getCpu() { return cpu; }
    public int getRamGb() { return ramGb; }
    public String getGpu() { return gpu; }
    public int getStorageGb() { return storageGb; }
    public boolean hasWifi() { return hasWifi; }
    public boolean hasBluetooth() { return hasBluetooth; }

    @Override
    public String toString() {
        return "Computer {" +
                "cpu='" + cpu + '\'' +
                ", ramGb=" + ramGb +
                ", gpu='" + (gpu == null ? "none" : gpu) + '\'' +
                ", storageGb=" + storageGb +
                ", wifi=" + hasWifi +
                ", bluetooth=" + hasBluetooth +
                '}';
    }

    /**
     * BUILDER — a static nested class that collects the configuration and finally
     * assembles the immutable Computer.
     *
     * Each setter returns "this" so calls can be chained (a fluent interface):
     *      new Computer.Builder("Intel i9", 32)
     *              .gpu("RTX 4090")
     *              .storageGb(2000)
     *              .build();
     */
    public static class Builder {
        // Mirror the product's fields.
        private final String cpu;     // required
        private final int ramGb;      // required

        private String gpu;           // optional
        private int storageGb = 256;  // optional, with a sensible default
        private boolean hasWifi;      // optional, defaults to false
        private boolean hasBluetooth; // optional, defaults to false

        // Required parameters go in the Builder's constructor so they can't be skipped.
        public Builder(String cpu, int ramGb) {
            this.cpu = cpu;
            this.ramGb = ramGb;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder storageGb(int storageGb) {
            this.storageGb = storageGb;
            return this;
        }

        public Builder withWifi() {
            this.hasWifi = true;
            return this;
        }

        public Builder withBluetooth() {
            this.hasBluetooth = true;
            return this;
        }

        // build() can also validate before constructing the final object.
        public Computer build() {
            if (cpu == null || cpu.isBlank()) {
                throw new IllegalStateException("A computer needs a CPU.");
            }
            if (ramGb <= 0) {
                throw new IllegalStateException("RAM must be greater than 0 GB.");
            }
            return new Computer(this);
        }
    }
}
