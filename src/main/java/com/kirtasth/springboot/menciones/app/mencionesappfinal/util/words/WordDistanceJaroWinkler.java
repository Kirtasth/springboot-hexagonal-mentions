package com.kirtasth.springboot.menciones.app.mencionesappfinal.util.words;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WordDistanceJaroWinkler implements WordDistance{

    private final JaroWinklerDistance jaro;

    public WordDistanceJaroWinkler(){
        this.jaro = new JaroWinklerDistance();
    }

    @Override
    public String getMostLikelyOfList(String name, List<String> list) {
        name = name.toLowerCase().trim();
        FullName name1 = new FullName(name);

        List<AbstractMap.SimpleEntry<String, Double>> distances = list.stream().map( listName -> {
            FullName name2 = new FullName(listName);
            double nameSimilarity = jaro.apply(name1.firstName, name2.firstName);

            double surnameSimilarity = 0.0;
            int minSurnames = Math.min(name1.surnames.length, name2.surnames.length);
            int maxSurnames = Math.max(name1.surnames.length, name2.surnames.length);


            for (int i = 0; i < minSurnames; i++) {
                surnameSimilarity += jaro.apply(name1.surnames[i], name2.surnames[i]);
            }
            surnameSimilarity /= minSurnames;

            surnameSimilarity *= (double) minSurnames / maxSurnames;

            return new AbstractMap.SimpleEntry<>(listName, 0.4 * nameSimilarity + 0.6 * surnameSimilarity);
        }).toList();
        List<AbstractMap.SimpleEntry<String, Double>> orderedDistances = new ArrayList<>(distances);
        orderedDistances.sort(Map.Entry.comparingByValue());

        return orderedDistances.getFirst().getKey();
    }
}
