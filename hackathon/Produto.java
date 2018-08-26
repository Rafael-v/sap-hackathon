package hackathon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Produto {
    String Nome;                   // Nome do produto no Varejo
    int Categoria;                 // Categoria na qual o produto se encontra(Frutas/Vegetais/Carnes)
    float Preco_Ideal;             // Preço ideal levando em conta média de preços geral do produto
    String Observacoes;            // Detalhes do produto que podem ser importantes para considerar
    List<Remessa> Dados;           // Cada remessa de entrada daquele produto no varejo, diferentes datas = diferentes quantidades

    Produto(String c, int cat, float p, String obs){
        Nome = c;
        Categoria = cat;
        Preco_Ideal = p;
        Observacoes = obs;
        Dados = new ArrayList<Remessa>();
        System.out.println("anal");
    }
    
    public void setRemessa(Remessa s){
        Dados.add(s);
    }

    public String getNome() {
        return Nome;
    }

    public int getCategoria() {
        return Categoria;
    }

    public float getPreço_Ideal() {
        return Preco_Ideal;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public List<Remessa> getDados() {
        return Dados;
    }
    
    public List<Remessa> Busca_Dados_Season(Calendar data){
        List<Remessa> Retorno = new ArrayList();
        int season = Converte_Data_Season(data);
        
        for(Remessa i: Dados){
            if(i.Busca_Season( season ) == 1){
                Retorno.add(i);
            }
        }
        return Retorno;
    }
    
    private int Converte_Data_Season(Calendar Data){
        return Data.get(Calendar.MONTH) / 3;
    }
    
    public List<Remessa> Busca_Dados_Semana(Calendar data){
        List<Remessa> Retorno = new ArrayList();
        int sem = Converte_Data_Semana(data);
        
        for(Remessa i: Dados){
            if(i.Busca_Semana( sem ) == 1){
                Retorno.add(i);
            }
        }
        return Retorno;
        
    }
    
    private int Converte_Data_Semana(Calendar Data){
        return Data.get(Calendar.WEEK_OF_MONTH);
    }
    
    public List<Remessa> Busca_Dados_Dia(Calendar data){
        List<Remessa> Retorno = new ArrayList();
        int dia = Converte_Data_Dia(data);
        
        for(Remessa i: Dados){
            if(i.Busca_Dia( dia ) == 1){
                Retorno.add(i);
            }
        }
        System.out.println(Retorno.size() + 'a');
        return Retorno;
        
    }
    
    private int Converte_Data_Dia(Calendar Data){
        return Data.get(Calendar.DAY_OF_WEEK);
    }

}
