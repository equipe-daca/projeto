package br.edu.ufcg.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Response implements Serializable {

    private String input;

    private String output;

    public Response() {
    }

    public Response(String input, String output) {
        this.input = input;
        this.output = output;
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
