package zad4;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList <T> extends ArrayList <T> {

    @SafeVarargs
    public XList(T... arguments) {
        Collections.addAll(this, arguments);
    }

    public XList(Collection<T> collection) {
        super(collection);
    }

    @SafeVarargs
    public static <T> XList <T> of(T... arguments) {
        return new XList<>(arguments);
    }

    public static <T> XList <T> of (Collection <T> collection) {
        return new XList<>(collection);
    }

    public static XList<String> charsOf(String input) {
        ArrayList<String> result = new ArrayList<>(Arrays.asList(input.split("")));
        return new XList<>(result);
    }

    public static XList<String> tokensOf(String input){
        return XList.of(input.split(" "));
    }

    public static XList<String> tokensOf(String input, String separator) {
        return XList.of(input.split(separator));
    }

    @SafeVarargs
    public final XList <T> union(T... arguments){
        XList <T> result = new XList<>(this);
        result.addAll(Arrays.asList(arguments));
        return result;
    }

    public XList <T> union (Collection <T> collection){
        XList <T> result = new XList<T>(this);
        result.addAll(collection);
        return result;
    }

    public XList <T> diff(Collection<T> collection){
        XList<T> result = new XList<T>();
        for (T t: this) {
            if(!collection.contains(t)) result.add(t);
        }
        return result;
    }

    public XList <T> unique(){
        return new XList<T>(this.stream().distinct().collect(Collectors.toCollection(XList::new)));
    }

    public XList<XList<T>> combine(){
       XList <XList<T>> result = new XList<>();

       cartesianProduct(result, new XList<T>(), (XList<XList<T>>) this);
       return result;
    }

    private void cartesianProduct (XList<XList<T>> result, XList<T> existingTupleComplete, XList<XList<T>> valuesToUse){
        for(T value : Collections.unmodifiableList(valuesToUse.get(0))) { // wywalalo jak byl getter poprawilo sie z unmodifiableList why?
            XList<T> newExisting = new XList<T>(existingTupleComplete);
            newExisting.add(value);

            if(valuesToUse.size() == 1){
                result.add(newExisting);
            } else {

                XList<XList<T>> newValues = new XList<XList<T>>();
                for(int i=1; i< valuesToUse.size(); i++){
                    newValues.add(valuesToUse.get(i));
                }
                cartesianProduct(result, newExisting, newValues);
            }
        }
    }
    public String join(String separator) {
        return this.stream()
                .map( Object::toString )
                .collect( Collectors.joining(separator));
    }

    public String join() {
        return join("");
    }

    public <U> XList <U> collect(Function<T,U> function){
        XList<U> resultList = new XList<>();
        for (T t:this) {
            resultList.add(function.apply(t));
        }
        return resultList;
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }
    }
}

/*
public XList<XList<T>> combine(){
       XList <XList<T>> result = new XList<XList<T>>();

       cartesianProduct(result, new XList<T>(), (XList<XList<T>>) this);
       return result;
    }

         // z tego wywala classcastexception = valuesToUse.get(0)
    private void cartesianProduct (XList<XList<T>> result, XList<T> existingTupleComplete, XList<XList<T>> valuesToUse){
        for(T value : valuesToUse.get(0)){//Collections.unmodifiableList(valuesToUse.get(0))) { // wywalalo jak byl getter poprawilo sie z unmodifiableList why?
            XList<T> newExisting = new XList<T>(existingTupleComplete);
            newExisting.add(value);

            if(valuesToUse.size() == 1){
                result.add(newExisting);
            } else {

                XList<XList<T>> newValues = new XList<XList<T>>();
                for(int i=1; i< valuesToUse.size(); i++){
                    newValues.add(valuesToUse.get(i));
                }
                cartesianProduct(result, newExisting, newValues);
            }
        }
    }

    //    public String join(String... separator){
//        if (separator.length==0) return collect(Collectors.joining);// this.toString().join("");
//        else return this.toString().join(separator[0]);
//    }
 */