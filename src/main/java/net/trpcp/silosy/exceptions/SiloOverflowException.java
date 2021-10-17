package net.trpcp.silosy.exceptions;

public class SiloOverflowException extends RuntimeException{

    public SiloOverflowException(){super();}

    public SiloOverflowException(String message){super(message);}

    public SiloOverflowException(String message, Throwable cause){super(message, cause);}
}
