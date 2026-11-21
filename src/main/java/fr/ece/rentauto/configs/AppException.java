package fr.ece.rentauto.configs;

import lombok.Getter;

@Getter
public class AppException extends Exception{
    private final int status;
    public AppException(String msg,int status){
        super(msg);
        this.status = status;
    }
}

