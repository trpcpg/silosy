package net.trpcp.silosy.exceptions;

public class NegativeStoredException extends RuntimeException{

    public NegativeStoredException(){
        super();
    }

    public NegativeStoredException(String message){
        super(message);
    }

    public NegativeStoredException(String message, Throwable  cause){
        super(message, cause);
    }
}
