package com.phisoft.springreactorone.services;

import java.util.ArrayList;
import java.util.List;

public class Incrementer  implements Runnable{

    private List<Integer> numList=new ArrayList<>();
    private boolean complete;
    private int counter=1;
    @Override
    public void run() {
       while (!complete){
         counter++;
         numList.add(counter);
       }
    }

    public List<Integer> getNumList() {
        return numList;
    }

    public boolean isComplete() {
        return complete;
    }

    public int getCounter() {
        return counter;
    }

    public void setNumList(List<Integer> numList) {
        this.numList = numList;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
