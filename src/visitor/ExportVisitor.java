package visitor;

// Ch.12 — No Duplication:
// XML formatting per shape type is defined exactly once per visitX() method.
// The wrapping <shapes> tag is assembled once in getXml() — not repeated per element.
//
// Ch.12 — Expressive:
// "ExportVisitor" and "getXml()" express their intent without ambiguity.
// A reader instantly knows this visitor produces XML output.
//
// Ch.13 — Limit the Scope of Data:
// The StringBuilder "output" is private — no external class can access or corrupt
// the intermediate XML state. Only the final result is exposed via getXml().
public class ExportVisitor implements ShapeVisitor {
    private final StringBuilder output = new StringBuilder();

    @Override
    public void visitCircle(Circle circle) {
        String entry = String.format("<circle radius=\"%.1f\"/>", circle.getRadius());
        System.out.println("Exporting: " + entry);
        output.append(entry).append("\n");
    }

    @Override
    public void visitSquare(Square square) {
        String entry = String.format("<square side=\"%.1f\"/>", square.getSide());
        System.out.println("Exporting: " + entry);
        output.append(entry).append("\n");
    }

    @Override
    public void visitTriangle(Triangle triangle) {
        String entry = String.format(
                "<triangle base=\"%.1f\" height=\"%.1f\" sides=\"%.1f,%.1f,%.1f\"/>",
                triangle.getBase(), triangle.getHeight(),
                triangle.getSideA(), triangle.getSideB(), triangle.getSideC());
        System.out.println("Exporting: " + entry);
        output.append(entry).append("\n");
    }

    public String getXml() {
        return "<shapes>\n" + output + "</shapes>";
    }
}
