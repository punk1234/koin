package alc.project.akejufatai.koin.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-10-28.
 */

public class QueryParameter {

    private String name;
    private List<String> values;

    public QueryParameter(String name){

        this.name = name;
        this.values = new ArrayList<>();

    }

    public void add(String value){

        this.values.add(value);

    }

    public String getValue(int index){

        return values.get(index);

    }

    public int getCount(){

        return values.size();

    }

}
