package zad3;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Maybe <T> {

    private final T value;

    public Maybe(T value) {
        this.value = value;
    }

    private boolean isEmpty(){
        return value == null;
    }

    private Maybe<T> createEmptyMaybe(){
        return new Maybe<T>(null);
    }

    public static <T> Maybe <T> of(T argument){
        return new Maybe<>(argument);
    }

    public void ifPresent(Consumer <T>  cons){ if(!isEmpty()) cons.accept(value); }

//    public <U> Maybe <U> map(Function <T,U> function){
//        return new Maybe<>(function.apply(value));
//    } nie banglalo, bo nie bylo sprawdzenia nulla

    public <F> Maybe<F> map (Function<T, F> function) {
        return this.value != null ? new Maybe<>(function.apply(this.value)) : new Maybe<>(null);
    }

    public T get (){
        if(!isEmpty()) return value;
        else throw new NoSuchElementException("maybe is empty");
    }

    //nie wchodzilo w orElse dopoki nie bylo sprawdzenia nulla w map i wychodzil null
    public T orElse(T defVal) {
        return this.isEmpty() ? defVal : this.value;
    }

    public Maybe<T> filter(Predicate <T> predicate){
        if(!predicate.test(value)) return createEmptyMaybe();
        else return this;
    }

    @Override
    public String toString() {
        if (!isEmpty()) return "Maybe has value " + value;
        else return "Maybe is empty";
    }
}
