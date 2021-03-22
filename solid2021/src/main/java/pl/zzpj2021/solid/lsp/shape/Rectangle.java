package pl.zzpj2021.solid.lsp.shape;

public class Rectangle implements Shape {

    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double calculateField() {
        return height * width;
    }

    @Override
    public double calculateCircumference() {
        return 2 * height + 2 * width;
    }
}
