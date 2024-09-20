public class Quadrado implements IQuadrilatero{

    double lado;

    Quadrado(double lado){
        this.lado = lado;
    }

    //Sobrescrita
    public double calcularArea(){
        return this.lado * this.lado;
    }

    //Sobrecarga
    public double calcularArea(double diagonal){
        return (diagonal * diagonal) / 2;
    }
}
