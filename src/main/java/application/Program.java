package application;

import services.ElevadorServiceImpl;

import java.util.Optional;

public class Program {

    public static void main(String[] args) {

        ElevadorServiceImpl elevadorServiceImpl = new ElevadorServiceImpl();


        System.out.print("Andar menos utilizado: " + elevadorServiceImpl.andarMenosUtilizado());

        System.out.print("\nElevador mais frequentado: " + elevadorServiceImpl.elevadorMaisFrequentado());

        System.out.print("\nElevador menos frequentado: " + elevadorServiceImpl.elevadorMenosFrequentado());

        System.out.print("\nPeriodo de MAIOR fluxo do elevador MAIS frequentado: "
                + elevadorServiceImpl.periodoMaiorFluxoElevadorMaisFrequentado());

        System.out.print("\nPeriodo de MENOR fluxo do(s) elevador(es) MENOS frequentado(s): "
                + elevadorServiceImpl.periodoMenorFluxoElevadorMenosFrequentado());

        System.out.print("\nPeriodo de MAIOR utilizacao conjunto de elevadores: "
                + elevadorServiceImpl.periodoMaiorUtilizacaoConjuntoElevadores());

        System.out.println("");
        System.out.printf("%nPercentual de uso elevador A: %.2f ", elevadorServiceImpl.percentualDeUsoElevadorA());
        System.out.printf("%nPercentual de uso elevador B: %.2f ", elevadorServiceImpl.percentualDeUsoElevadorB());
        System.out.printf("%nPercentual de uso elevador C: %.2f ", elevadorServiceImpl.percentualDeUsoElevadorC());
        System.out.printf("%nPercentual de uso elevador D: %.2f ", elevadorServiceImpl.percentualDeUsoElevadorD());
        System.out.printf("%nPercentual de uso elevador E: %.2f ", elevadorServiceImpl.percentualDeUsoElevadorE());


    }
}
