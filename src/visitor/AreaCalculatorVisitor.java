package visitor;

// Ch.12 — No Duplication:
// The area formula for each shape is defined exactly once inside its visitX() method.
// There is no copy-pasted logic between visitors — each formula lives in one place.
//
// Ch.12 — Expressive:
// Method names (visitCircle, visitSquare, visitTriangle) and variable names (area, totalArea)
// immediately communicate intent without needing comments.
//
// Ch.12 — Minimal Classes and Methods:
// This class has the minimum number of methods required: one visitX() per shape type
// plus one accessor getTotalArea(). Nothing extra.
public class AreaCalculatorVisitor implements ShapeVisitor {
    private double totalArea = 0;

    @Override
    public void visitCircle(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        System.out.printf("Circle area (r=%.1f): %.2f%n", circle.getRadius(), area);
        totalArea += area;
    }

    @Override
    public void visitSquare(Square square) {
        double area = square.getSide() * square.getSide();
        System.out.printf("Square area (side=%.1f): %.2f%n", square.getSide(), area);
        totalArea += area;
    }

    @Override
    public void visitTriangle(Triangle triangle) {
        double area = 0.5 * triangle.getBase() * triangle.getHeight();
        System.out.printf("Triangle area (base=%.1f, height=%.1f): %.2f%n",
                triangle.getBase(), triangle.getHeight(), area);
        totalArea += area;
    }

    public double getTotalArea() {
        return totalArea;
    }
}
