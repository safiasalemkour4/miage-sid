
package gui;

import cart.Scission;
import etl.LoadCSV;

/**
 * @author Romain
 */
public class ScissionItem implements java.lang.Comparable {

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
                //st += "" + this.scission.getCriteriaLeft() + " (Str)";
                st += "" + LoadCSV.getData().getColumnName( this.scission.getIdColumnCriteria()) + " ("+ this.scission.getCriteriaLeft() + ")";
                break;
            case Scission.T_NUMERIC:
                st += "" + LoadCSV.getData().getColumnName( this.scission.getIdColumnCriteria()) + " ("+ this.scission.getCriteriaInterval() + ")";
                break;
            case Scission.T_BINARY:
                st += "" + LoadCSV.getData().getColumnName( this.scission.getIdColumnCriteria()) + " ("+ this.scission.getCriteriaLeft() + ")";
                break;
        }
        st += " [" + round(this.chartDegree, 2) + "]";
        return st;
    }

    /** on arrondi à x décimales */
    public double round(double what, int howmuch) {
       return (double) ((int) (what * Math.pow(10, howmuch) + .5)) / Math.pow(10, howmuch);
    }

    /**
     * Compare 2 ScissionItem
     * @param o de type ScissionItem
     * @return le type <, > ou =
     */
    public int compareTo(Object o) {
        Double degree1 = ((ScissionItem) o).getChartDegree();
        Double degree2 = this.getChartDegree();
        if (degree1 < degree2) {
            return -1;
        } else if (degree1 == degree2) {
            return 0;
        } else {
            return 1;
        }
    }
    
}
