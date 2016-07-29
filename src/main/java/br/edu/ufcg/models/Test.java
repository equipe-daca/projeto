package br.edu.ufcg.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Test implements Serializable {

    String name, tip;
    Map<String, String> inputs;

    public Test() {
    }

    public Test(String name, String tip, Map<String, String> inputs) {
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

    public Map<String, String> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, String> inputs) {
        this.inputs = inputs;
    }
}
