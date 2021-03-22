package pl.zzpj2021.solid.lsp.shape;

public class Triangle implements Shape {

    private double height, width, sideA, sideB;

    public Triangle(double height, double width, double sideA, double sideB) {
        this.height = height;
        this.width = width;
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double calculateField() {
        return width * height / 2;
    }

    @Override
    public double calculateCircumference() {
        return width + sideA + sideB;
    }
}
