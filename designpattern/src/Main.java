import builder.Computer;

/**
 * BUILDER DESIGN PATTERN — demonstration.
 *
 * The Builder pattern separates the construction of a complex object from its
 * representation, so the same construction process can create different objects.
 *
 * Use it when:
 *  - An object has many parameters, especially optional ones.
 *  - You want to avoid "telescoping constructors".
 *  - You want the constructed object to be immutable.
 *
 * See builder/Computer.java for the Product + Builder implementation.
 */
void main() {
    IO.println("=== Builder Design Pattern Demo ===\n");

    // 1) A fully loaded gaming PC — chained, readable, self-documenting calls.
    Computer gamingPc = new Computer.Builder("Intel i9", 32)
            .gpu("RTX 4090")
            .storageGb(2000)
            .withWifi()
            .withBluetooth()
            .build();
    IO.println("Gaming PC   : " + gamingPc);

    // 2) A minimal office machine — only the required parts, defaults for the rest.
    Computer officePc = new Computer.Builder("Intel i5", 8)
            .build();
    IO.println("Office PC   : " + officePc);

    // 3) A mid-range laptop — pick and choose only the options you need.
    Computer laptop = new Computer.Builder("AMD Ryzen 7", 16)
            .storageGb(512)
            .withWifi()
            .build();
    IO.println("Laptop      : " + laptop);

    // 4) The Builder can validate input — this fails fast with a clear message.
    IO.println("\n--- Validation example ---");
    try {
        new Computer.Builder("", 16).build();
    } catch (IllegalStateException e) {
        IO.println("Build failed as expected: " + e.getMessage());
    }
}
