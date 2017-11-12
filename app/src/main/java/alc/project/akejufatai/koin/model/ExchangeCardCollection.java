package alc.project.akejufatai.koin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-10-28.
 */

public class ExchangeCardCollection {

    private List<ExchangeCard> items;

    public ExchangeCardCollection(){

        items = new ArrayList<>();

    }

    public void add(ExchangeCard exchangeCard){

        items.add(exchangeCard);

    }

    public ExchangeCard get(int index){

        return items.get(index);

    }

    public int count(){

        return items.size();

    }

}
