
package gui;

import cart.Scission;

/**
 * @author Romain
 */
public class ScissionItem {

    private Scission scission;
    private Double chartDegree;

    public ScissionItem(Scission s, Double cd){
        this.scission = s;
        this.chartDegree = cd;
    }

    public Double getChartDegree() {
        return chartDegree;
    }

    public Scission getScission() {
        return scission;
    }

    @Override
    public String toString(){
        String st = "";
        switch (this.scission.getTypeScission()) {
            case Scission.T_STRING:
                st += "" + this.scission.getCriteriaLeft() + " (Str)";
                break;
            case Scission.T_NUMERIC:
                st += "" + this.scission.getCriteriaInterval() + " (Num)";
                break;
            case Scission.T_BINARY:
                st += "" + this.scission.getCriteriaLeft() + " (Bin)";
                break;
        }
        st += " [" + this.chartDegree + "]";
        return st;
    }


    
}
