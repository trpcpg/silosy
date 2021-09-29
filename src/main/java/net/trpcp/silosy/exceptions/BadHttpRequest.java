package net.trpcp.silosy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadHttpRequest extends RuntimeException {

    public BadHttpRequest(){super();}

    public BadHttpRequest(String s){super(s);}

    public BadHttpRequest(String s, Throwable t){super(s, t);}
}
