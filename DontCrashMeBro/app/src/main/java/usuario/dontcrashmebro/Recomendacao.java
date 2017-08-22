package usuario.dontcrashmebro;

/**
 * Created by LuizC on 21/08/2017.
 */

public class Recomendacao {

    public String calculoResultado(){
        double modelo3kg = 0.4;
        double modelo3kgN = 0.6;
        double sol = 0.7;
        double solN = 0.3;
        double nublado = 0.4;
        double nubladoN = 0.6;
        double chuva = 0.2;
        double chuvaN = 0.2;
        double vento20 = 0.7;
        double vento20N = 0.3;
        double vento30 = 0.4;
        double vento30N = 0.6;
        double peso = 0.95;
        double pesoN = 0.05;
        double resultadoEq;
        double resultadoPorcentagem;

        resultadoEq = modelo3kg*sol*vento20*peso/((modelo3kg*sol*vento20*peso)+(modelo3kgN*solN*vento20N*pesoN));
        resultadoPorcentagem = resultadoEq*100;
        String resultado = String.format("%.2f%%", resultadoPorcentagem);
        return resultado;
    }
}
