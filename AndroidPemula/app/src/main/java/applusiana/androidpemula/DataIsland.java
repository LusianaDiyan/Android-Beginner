package applusiana.androidpemula;

import java.util.ArrayList;

public class DataIsland {
    public static String[][]data = new String[][]{
            {"Sumatera", "Andalas", "https://upload.wikimedia.org/wikipedia/commons/0/06/Sumatra_Topography.png"},
            {"Jawa", "Java", "https://upload.wikimedia.org/wikipedia/commons/4/43/Java_Topography.png"},
            {"Kalimantan", "Borneo", "https://upload.wikimedia.org/wikipedia/commons/2/28/Borneo_Topography.png"},
            {"Sulawesi", "Celebes", "https://upload.wikimedia.org/wikipedia/commons/6/65/Sulawesi_Topography.png"},
            {"Papua", "New Guinea", "https://upload.wikimedia.org/wikipedia/commons/b/b6/New_Guinea_Topography.png"},
            {"Bali", "Pulau Dewata", "https://upload.wikimedia.org/wikipedia/commons/b/bf/Bali_Labeled.png"},
            {"Pulau Seribu", "Kepulauan Seribu", "https://upload.wikimedia.org/wikipedia/commons/c/c6/Peta_Banten_Utara.png"},
            {"Madura", "Pulau Garam","https://upload.wikimedia.org/wikipedia/commons/9/98/Madura_Topography.png"},
            {"Pulau Komodo", "Taman Nasional Komodo", "https://upload.wikimedia.org/wikipedia/commons/c/c6/Flores_Locator_Topography.png"},
            {"Pulau Sunda", "Sunda", "https://upload.wikimedia.org/wikipedia/commons/5/5f/Map_of_Sunda_and_Sahul.png"},
            {"Pulau Timor", "Timor Barat", "https://upload.wikimedia.org/wikipedia/commons/f/fd/Timor.png"}
    };

    public static ArrayList<Pulau> getListData(){
        ArrayList<Pulau> list = new ArrayList<>();
        for (String[] aData : data){
            Pulau pulau = new Pulau();
            pulau.setNamaPulau(aData[0]);
            pulau.setNamaLain(aData[1]);
            pulau.setPhoto(aData[2]);

            list.add(pulau);
        }
        return list;
    }
}
