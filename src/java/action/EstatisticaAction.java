package action;

import com.opensymphony.xwork2.ActionSupport;
import estatistica.GeradorDeEstatisticas;
import org.jfree.chart.JFreeChart;

public class EstatisticaAction extends ActionSupport  {

    private int tipo;
    private GeradorDeEstatisticas est = new GeradorDeEstatisticas();
    private JFreeChart chart;


    public String execute() throws Exception {
     
        if (tipo==-1) return "view";
        else {
           chart = est.gerarEstatisticas(tipo);
           return SUCCESS;   
        }
      
    }

  
    /**
     * @return the chart
     */
    public JFreeChart getChart() {
        return chart;
    }

    /**
     * @param chart the chart to set
     */
    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
