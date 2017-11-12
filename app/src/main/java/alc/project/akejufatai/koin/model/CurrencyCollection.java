package alc.project.akejufatai.koin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-11-12.
 */

public class CurrencyCollection {

    private List<String> items;

    public CurrencyCollection(){

        items = new ArrayList<>();

    }

    public void add(String item){

        items.add(item);

    }

    public String get(int index){

        return items.get(index);

    }

    public int count(){

        return items.size();

    }

}
