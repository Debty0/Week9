package visitor;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

// Ch.13 — Single Responsibility Principle:
// ShapeCollection manages only the traversal of shapes.
// It does not perform any computation, export, or formatting.
//
// Ch.13 — Limit the Scope of Data:
// The internal list is private and never exposed directly.
// External code interacts only through add() and applyVisitor().
//
// Ch.12 — Minimal Classes and Methods:
// Only two public methods exist — exactly what clients need.
public class ShapeCollection {
    private final List<Shape> shapes = new ArrayList<>();

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public void applyVisitor(ShapeVisitor visitor) {
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }

    // Ch.13 — Use Copies of Data:
    // Returns an unmodifiable view so no external code can mutate the collection.
    public List<Shape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }
}
