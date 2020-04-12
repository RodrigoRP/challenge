package services;

import model.Usuario;

import java.util.*;
import java.util.stream.Collectors;

public class ElevadorServiceImpl implements IElevadorService {

    List<Usuario> usuarios = JsonReader.carregarJson();

    /**
     * Deve retornar uma List contendo o(s) andar(es) menos utilizado(s).
     */
    @Override
    public List<Integer> andarMenosUtilizado() {

        Map<Integer, Long> countersAndares = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getAndar, Collectors.counting()));

        return getAndaresMenosUtilizados(countersAndares);
    }


    private List<Integer> getAndaresMenosUtilizados(Map<Integer, Long> mapGroup) {
        if (mapGroup.isEmpty())
            return Collections.emptyList();

        long min = mapGroup.values().stream()
                .min(Comparator.naturalOrder())
                .orElse(null);

        return mapGroup.entrySet().stream()
                .filter(e -> e.getValue() == min)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    /**
     * Deve retornar uma List contendo o(s) elevador(es) mais frequentado(s).
     */
    @Override
    public List<Character> elevadorMaisFrequentado() {

        Map<Character, Long> countersElevadores = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getElevador, Collectors.counting()));

        return getMaisFrequentados(countersElevadores);
    }


    private List<Character> getMaisFrequentados(Map<Character, Long> mapGroup) {
        if (mapGroup.isEmpty())
            return Collections.emptyList();

        long max = mapGroup.values().stream()
                .max(Comparator.naturalOrder())
                .orElse(null);

        return mapGroup.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    /**
     * Deve retornar uma List contendo o período de maior fluxo de cada um dos elevadores mais frequentados (se houver mais de um).
     */
    @Override
    public List<Character> periodoMaiorFluxoElevadorMaisFrequentado() {
        return getFrequenciaTurno(elevadorMaisFrequentado());
    }

    /**
     * Deve retornar uma List contendo o(s) elevador(es) menos frequentado(s).
     */
    @Override
    public List<Character> elevadorMenosFrequentado() {

        Map<Character, Long> countersElevadores = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getElevador, Collectors.counting()));

        return getElevadoresMenosFrequentados(countersElevadores);
    }

    private List<Character> getElevadoresMenosFrequentados(Map<Character, Long> countersElevadores) {

        if (countersElevadores.isEmpty())
            return Collections.emptyList();

        long min = countersElevadores.values().stream().min(Comparator.naturalOrder()).orElse(null);
        return countersElevadores.entrySet().stream()
                .filter(e -> e.getValue() == min)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Deve retornar uma List contendo o período de menor fluxo de cada um dos elevadores menos frequentados (se houver mais de um).
     */
    @Override
    public List<Character> periodoMenorFluxoElevadorMenosFrequentado() {
        return getFrequenciaTurno(elevadorMenosFrequentado());
    }

    private List<Character> getFrequenciaTurno(List<Character> elevadorFrequenciaList) {
        List<Character> turnoFrequenciaList = new ArrayList<>();

        for (Character elevadorFrequencia : elevadorFrequenciaList) {
            Map<Character, Long> countersTurno = usuarios.stream()
                    .filter(c -> c.getElevador() == elevadorFrequencia)
                    .collect(Collectors.groupingBy(Usuario::getTurno, Collectors.counting()));

            List<Character> frequentadoList = getMaisFrequentados(countersTurno);
            for (Character frequentado : frequentadoList) {
                turnoFrequenciaList.add(frequentado);
            }
        }
        return turnoFrequenciaList;
    }

    /**
     * Deve retornar uma List contendo o(s) periodo(s) de maior utilização do conjunto de elevadores.
     */
    @Override
    public List<Character> periodoMaiorUtilizacaoConjuntoElevadores() {

        Map<Character, Long> countersTurno = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getTurno, Collectors.counting()));

        return getMaisFrequentados(countersTurno);
    }


    @Override
    public float percentualDeUsoElevadorA() {
        return getPercentualDeUsoElevador('A');
    }

    @Override
    public float percentualDeUsoElevadorB() {
        return getPercentualDeUsoElevador('B');
    }

    @Override
    public float percentualDeUsoElevadorC() {
        return getPercentualDeUsoElevador('C');
    }

    @Override
    public float percentualDeUsoElevadorD() {
        return getPercentualDeUsoElevador('D');
    }

    @Override
    public float percentualDeUsoElevadorE() {
        return getPercentualDeUsoElevador('E');
    }


    private float getPercentualDeUsoElevador(char elevador) {
        float totalUso = usuarios.size();
        float parcialUso = getUsuariosPorElevador(elevador).size();

        return (parcialUso / totalUso) * 100;
    }

    private List<Usuario> getUsuariosPorElevador(Character elevador) {
        return usuarios.stream()
                .filter(x -> x.getElevador().equals(elevador))
                .collect(Collectors.toList());
    }

}
