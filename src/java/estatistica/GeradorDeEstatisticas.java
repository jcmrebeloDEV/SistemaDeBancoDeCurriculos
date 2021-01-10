package estatistica;

import entity.PessoaFisica;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositorio.PessoaRepositorio;

public class GeradorDeEstatisticas {

    private PessoaRepositorio prep = new PessoaRepositorio();
    public static final int porcentagemDeCandidatosPorEstado = 0;
    public static final int porcentagemDeCandidatosPorEscolaridade = 1;
    public static final int porcentagemDeCandidatosPorIdade = 2;
    public static final int porcentagemDeCandidatosPorAreaProfissional = 3;

    

    private HashMap<String, Float> porcentagemDeCandidatosPorIdade(ArrayList<PessoaFisica> pfs) {
        HashMap<String, Float> dados = new HashMap<String, Float>();
        int totais = pfs.size();

        dados.put("18 a 25 anos", new Float(0).floatValue());
        dados.put("26 a 35 anos", new Float(0).floatValue());
        dados.put("36 a 45 anos", new Float(0).floatValue());
        dados.put("46 a 55 anos", new Float(0).floatValue());
        dados.put("56 a 65 anos", new Float(0).floatValue());
        dados.put("maior que 65 anos", new Float(0).floatValue());



        for (int a = 0; a < pfs.size(); a++) {

            //int idade = calcularIdade(pfs.get(a).getDataDeNascimento());

            int idade  = pfs.get(a).exibirIdade();

            if (idade > 17 && idade < 26) { //18~25

                dados.put("18 a 25 anos", (float) (dados.get("18 a 25 anos") + 1 / (float) totais));

            } else if (idade > 25 && idade < 36) { //26~35

                dados.put("26 a 35 anos", (float) (dados.get("26 a 35 anos") + 1 / (float) totais));

            } else if (idade > 35 && idade < 46) { //36~45

                dados.put("36 a 45 anos", (float) (dados.get("36 a 45 anos") + 1 / (float) totais));

            } else if (idade > 45 && idade < 56) { //46~55

                dados.put("46 a 55 anos", (float) (dados.get("46 a 55 anos") + 1 / (float) totais));

            } else if (idade > 55 && idade < 66) { //56~65

                dados.put("56 a 65 anos", (float) (dados.get("56 a 65 anos") + 1 / (float) totais));

            } else { //65+

                dados.put("maior que 65 anos", (float) (dados.get("maior que 65 anos") + 1 / (float) totais));
            }

        }

        return dados;

    }

    private HashMap<String, Float> porcentagemDeCandidatosPorEstado(ArrayList<PessoaFisica> pfs) {

        HashMap<String, Float> dados = new HashMap<String, Float>();
        int totais = pfs.size();

        for (int a = 0; a < pfs.size(); a++) {
            String valorUF = pfs.get(a).getUf();

            if (dados.containsKey(valorUF)) {
                dados.put(valorUF, (float) (dados.get(valorUF) + 1 / (float) totais));
            } else {
                dados.put(valorUF, 1 / (float) totais);
            }

        }
        return dados;
    }

    private HashMap<String, Float> porcentagemDeCandidatosPorEscolaridade(ArrayList<PessoaFisica> pfs) {
        HashMap<String, Float> dados = new HashMap<String, Float>();
        int totais = pfs.size();

        for (int a = 0; a < pfs.size(); a++) {
            String valorES = pfs.get(a).getCurriculo().getEscolaridade();

            if (dados.containsKey(valorES)) {
                dados.put(valorES, (float) (dados.get(valorES) + 1 / (float) totais));
            } else {
                dados.put(valorES, 1 / (float) totais);
            }

        }
        return dados;
    }

    private HashMap<String, Float> porcentagemDeCandidatosPorAreaProfissional(ArrayList<PessoaFisica> pfs) {
        HashMap<String, Float> dados = new HashMap<String, Float>();
        int totais = pfs.size();

        for (int a = 0; a < pfs.size(); a++) {
            String valorAP = pfs.get(a).getCurriculo().getAreaProfissional();

            if (dados.containsKey(valorAP)) {
                dados.put(valorAP, (float) (dados.get(valorAP) + 1 / (float) totais));
            } else {
                dados.put(valorAP, 1 / (float) totais);
            }

        }
        return dados;
    }

    public JFreeChart gerarEstatisticas(int tipo) {

        JFreeChart chart = null;
        HashMap<String, Float> dados = null;
        ArrayList<PessoaFisica> pfs = prep.listarPessoasFisicas();

        switch (tipo) {
            case porcentagemDeCandidatosPorIdade:
                dados = porcentagemDeCandidatosPorIdade(pfs);
                break;
            case porcentagemDeCandidatosPorEstado:
                dados = porcentagemDeCandidatosPorEstado(pfs);
                break;

            case porcentagemDeCandidatosPorEscolaridade:
                dados = porcentagemDeCandidatosPorEscolaridade(pfs);
                break;

            case porcentagemDeCandidatosPorAreaProfissional:
                dados = porcentagemDeCandidatosPorAreaProfissional(pfs);
                break;

            default:

                break;
        }

        pfs = null;

        try {

            DefaultPieDataset pizza = new DefaultPieDataset();

            for (Iterator<String> i = dados.keySet().iterator(); i.hasNext();) {
                String key = i.next();
                if (key != null) {
                    Double percent = new Double(dados.get(key));
                    NumberFormat percentFormatter;
                    String percentOut;
                    percentFormatter = NumberFormat.getPercentInstance();
                    percentOut = percentFormatter.format(percent);
                    pizza.setValue(key + " " + percentOut, dados.get(key));
                }
            }
            chart = ChartFactory.createPieChart("", pizza, false, false, false);
            chart.setBackgroundPaint(Color.white);
            chart.setBorderVisible(false);
            chart.getTitle().setPaint(Color.lightGray);

            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelOutlinePaint(Color.LIGHT_GRAY);
            plot.setLabelBackgroundPaint(new Color(255, 255, 170));
            plot.setBackgroundPaint(new Color(242, 242, 242));
            plot.setLabelFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));


        } catch (Exception ex) {
            Logger.getLogger(GeradorDeEstatisticas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return chart;
    }
}
