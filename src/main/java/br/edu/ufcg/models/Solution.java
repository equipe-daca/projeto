package br.edu.ufcg.models;

import java.util.Map;

public class Solution {

    private String body;
    private Map<String, String> inputs;

    public Solution() {
    }

    public Solution(String body, Map<String, String> inputs) {
        this.body = body;
        this.inputs = inputs;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, String> inputs) {
        this.inputs = inputs;
    }
}
