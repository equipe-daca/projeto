package br.edu.ufcg.models;


import java.util.Map;

public class Solution {

    String body, input, output;

    public Solution() {
    }

    public Solution(String body, String input, String output) {
        this.body = body;
        this.input = input;
        this.output = output;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
