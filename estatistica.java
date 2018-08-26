import java.util.ArrayList;
import java.util.List;

public class estatistica {

    public static double sum(ArrayList l){
        double ac = 0;
        for(Object i : l){
            ac += (double) i;
        }
        return ac;
    }
    
    public static double med(ArrayList l){
        return sum(l)/l.size();
    }
    
    public double calcula_proc(List<Remessa> r){
        ArrayList<Double> tot = new ArrayList();
        for(Remessa i : r){
            double ent = i.Qtd_Entrada;
            double perd = i.Qtd_Perda;
            tot.add(ent-perd);
            System.out.println(ent + "" + perd);
        }
        
        return med(tot);
    }
}
