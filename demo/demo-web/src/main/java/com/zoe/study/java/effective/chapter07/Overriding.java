package com.zoe.study.java.effective.chapter07;

/**
 * Created by  on 2017/8/15.
 */
public class Overriding {

    public static  void main(String [] args) {
        Wine [] wines = {
            new Wine(),
            new SparklingWine(),
            new Champagne()
        };

        for (Wine w: wines) {
            System.out.println(w.name());
        }
    }
}

class Wine {
    String name() {
        return "Wine";
    }
}

class SparklingWine extends Wine {
    @Override
    String name() {
        return "SparklingWine";
    }
}
class Champagne extends SparklingWine {
    @Override
    String name() {
        return "Champagne";
    }
}
