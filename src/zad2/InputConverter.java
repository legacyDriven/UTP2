package zad2;

import java.util.function.Function;

public class InputConverter <T> {

  private final T constructorData;

    public InputConverter(T data) {
        this.constructorData = data;
    }

    public <R> R convertBy(Function... functions){

        Object argument = constructorData;

        for(Function f : functions){
            argument = f.apply(argument);
        }
        return (R) argument;
    }
}
