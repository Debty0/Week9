package visitor;

// Ch.13 — Single Responsibility Principle:
// PerimeterCalculatorVisitor does one thing only: compute perimeters.
// It is completely independent of area calculation and XML export logic.
//
// Ch.13 — Use Copies of Data:
// totalPerimeter accumulates a local running sum. The result is returned
// as a primitive double via getTotalPerimeter() — a value copy, not shared mutable state.
//
// Ch.12 — Minimal Classes and Methods:
// Three visit methods (one per shape) plus one accessor. No unnecessary abstractions.
public class PerimeterCalculatorVisitor implements ShapeVisitor {
    private double totalPerimeter = 0;

    @Override
    public void visitCircle(Circle circle) {
        double perimeter = 2 * Math.PI * circle.getRadius();
        System.out.printf("Circle perimeter (r=%.1f): %.2f%n", circle.getRadius(), perimeter);
        totalPerimeter += perimeter;
    }

    @Override
    public void visitSquare(Square square) {
        double perimeter = 4 * square.getSide();
        System.out.printf("Square perimeter (side=%.1f): %.2f%n", square.getSide(), perimeter);
        totalPerimeter += perimeter;
    }

    @Override
    public void visitTriangle(Triangle triangle) {
        double perimeter = triangle.getSideA() + triangle.getSideB() + triangle.getSideC();
        System.out.printf("Triangle perimeter (sides=%.1f,%.1f,%.1f): %.2f%n",
                triangle.getSideA(), triangle.getSideB(), triangle.getSideC(), perimeter);
        totalPerimeter += perimeter;
    }

    public double getTotalPerimeter() {
        return totalPerimeter;
    }
}
