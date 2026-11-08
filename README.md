## Overview

This project implements the **Visitor** behavioral design pattern applied to a shape hierarchy.  
A collection of shapes (`Circle`, `Square`, `Triangle`) is traversed by independent visitors, each performing a different operation, without modifying any element class.

---
## Visitors

| Visitor | Operation |
|---|---|
| `AreaCalculatorVisitor` | Computes area of each shape; accumulates total |
| `ExportVisitor` | Serializes each shape to XML |
| `PerimeterCalculatorVisitor` | Computes perimeter of each shape; accumulates total |

---

## Clean Code Principles Applied

### Chapter 12 — Emergence

#### 1. No Duplication
Each shape's formula is defined exactly once inside its own `visitX()` method. No logic is copy-pasted between visitors or between element classes.

```java
// Area formula for Circle exists in exactly ONE place:
public void visitCircle(Circle circle) {
    double area = Math.PI * circle.getRadius() * circle.getRadius();
    totalArea += area;
}
```

#### 2. Expressive
Every class name, method name, and variable name communicates its intent without ambiguity. A reader instantly understands the code without needing external explanation.

```java
// Class name tells you it visits shapes and calculates area:
public class AreaCalculatorVisitor implements ShapeVisitor { ... }

// Method name tells you exactly what shape it handles:
public void visitTriangle(Triangle triangle) { ... }

// Variable name tells you what it accumulates:
private double totalArea = 0;
```

#### 3. Minimal Classes and Methods
No class contains methods beyond what is strictly necessary. Each visitor has exactly one `visitX()` method per element type plus one result accessor — nothing more.

```java
// PerimeterCalculatorVisitor has exactly 4 methods — 3 visit + 1 accessor:
void visitCircle(Circle c)   { ... }
void visitSquare(Square s)   { ... }
void visitTriangle(Triangle t) { ... }
double getTotalPerimeter()   { ... }
```

---

### Chapter 13 — Concurrency

#### 4. Single Responsibility Principle
Each class has one clearly defined responsibility. `ShapeCollection` manages traversal only. `AreaCalculatorVisitor` computes area only. `ExportVisitor` produces XML only. No class mixes concerns.

```java
// ShapeCollection — ONLY traversal, nothing else:
public void applyVisitor(ShapeVisitor visitor) {
    for (Shape shape : shapes) {
        shape.accept(visitor);
    }
}
```

#### 5. Limit the Scope of Data
All mutable state is kept as private as possible. The internal list in `ShapeCollection`, the running totals in visitors, and the XML buffer in `ExportVisitor` are all `private` — never exposed directly.

```java
// Private state in ExportVisitor — only the final result is exposed:
private final StringBuilder output = new StringBuilder();

public String getXml() {
    return "<shapes>\n" + output + "</shapes>";
}

// Private list in ShapeCollection — only an unmodifiable view is exposed:
private final List<Shape> shapes = new ArrayList<>();

public List<Shape> getShapes() {
    return Collections.unmodifiableList(shapes);
}
```

#### 6. Use Copies of Data
Where possible, data is returned as value copies (primitive `double`) rather than references to shared mutable state. `ShapeCollection.getShapes()` returns an unmodifiable copy of the list to prevent external mutation.

```java
// Primitive double returned — a value copy, not shared mutable state:
public double getTotalArea() {
    return totalArea;
}

// Unmodifiable view — caller cannot mutate the internal collection:
public List<Shape> getShapes() {
    return Collections.unmodifiableList(shapes);
}
```

---

## Sample Output

```
=== Area Calculator ===
Circle area (r=5.0): 78.54
Square area (side=4.0): 16.00
Triangle area (base=6.0, height=3.0): 9.00
Circle area (r=2.5): 19.63
Square area (side=7.0): 49.00
Total area: 172.17

=== Export to XML ===
Exporting: <circle radius="5.0"/>
Exporting: <square side="4.0"/>
Exporting: <triangle base="6.0" height="3.0" sides="5.0,5.0,6.0"/>
Exporting: <circle radius="2.5"/>
Exporting: <square side="7.0"/>

Full XML output:
<shapes>
<circle radius="5.0"/>
<square side="4.0"/>
<triangle base="6.0" height="3.0" sides="5.0,5.0,6.0"/>
<circle radius="2.5"/>
<square side="7.0"/>
</shapes>

=== Perimeter Calculator ===
Circle perimeter (r=5.0): 31.42
Square perimeter (side=4.0): 16.00
Triangle perimeter (sides=5.0,5.0,6.0): 16.00
Circle perimeter (r=2.5): 15.71
Square perimeter (side=7.0): 28.00
Total perimeter: 107.12
```

---

## Conclusion

The main encapsulation trade-off in the Visitor pattern: visitors need access to element internals to perform their computations. This is handled by exposing the minimum necessary state through read-only getters, so visitors can read but never mutate element data.

The benefit: new operations can be added indefinitely by creating new visitor classes, without touching any element — a direct application of the Open/Closed Principle.