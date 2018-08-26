import java.util.Calendar;

public class Remessa {
    Calendar Data_Entrada;  // Data de chega da Remessa
    int Qtd_Entrada;      // Numero de unidades presentes nesta remessa no momento da entrada
    int Qtd_Perda;        // Numero de unidades presentes nesta remessa que foram perdidas 


    Remessa(Calendar sdf, int e, int p){
        Data_Entrada = sdf;
        Qtd_Entrada = e;
        Qtd_Perda = p;
    }

    public int Busca_Season(int Season){
        int month = this.Data_Entrada.get(Calendar.MONTH);

        if (month > (Season-1)*3 && month < (Season)*3)
            return 1;
        return 0;
    }

    public int Busca_Semana(int semana){
        if (this.Data_Entrada.get(Calendar.WEEK_OF_MONTH) == semana)
            return 1;
        return 0;
    }

    public int Busca_Dia(int dia){
        if (this.Data_Entrada.get(Calendar.DAY_OF_WEEK) == dia)
            return 1;
        return 0;
    }

    public Calendar getData_Entrada() {
        return Data_Entrada;
    }

    public int getQtd_Entrada() {
        return Qtd_Entrada;
    }

    public int getQtd_Perda() {
        return Qtd_Perda;
    }

}
