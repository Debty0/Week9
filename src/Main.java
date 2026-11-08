import visitor.*;

public class Main {
    public static void main(String[] args) {
        ShapeCollection collection = new ShapeCollection();
        collection.add(new Circle(5.0));
        collection.add(new Square(4.0));
        collection.add(new Triangle(6.0, 3.0, 5.0, 5.0, 6.0));
        collection.add(new Circle(2.5));
        collection.add(new Square(7.0));

        // --- AreaCalculatorVisitor ---
        System.out.println("=== Area Calculator ===");
        AreaCalculatorVisitor areaVisitor = new AreaCalculatorVisitor();
        collection.applyVisitor(areaVisitor);
        System.out.printf("Total area: %.2f%n%n", areaVisitor.getTotalArea());

        // --- ExportVisitor ---
        System.out.println("=== Export to XML ===");
        ExportVisitor exportVisitor = new ExportVisitor();
        collection.applyVisitor(exportVisitor);
        System.out.println("\nFull XML output:");
        System.out.println(exportVisitor.getXml());

        // --- PerimeterCalculatorVisitor ---
        System.out.println("=== Perimeter Calculator ===");
        PerimeterCalculatorVisitor perimeterVisitor = new PerimeterCalculatorVisitor();
        collection.applyVisitor(perimeterVisitor);
        System.out.printf("Total perimeter: %.2f%n", perimeterVisitor.getTotalPerimeter());
    }
}
