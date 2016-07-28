package br.edu.ufcg.models;

import java.io.Serializable;
import java.util.HashMap;


public class Test implements Serializable {

    String name, tip;
    HashMap<String, String> inputs;

    public Test(String name, String tip, HashMap<String, String> inputs) {
        this.name = name;
        this.tip = tip;
        this.inputs = inputs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public HashMap<String, String> getInputs() {
        return inputs;
    }

    public void setInputs(HashMap<String, String> inputs) {
        this.inputs = inputs;
    }
}
