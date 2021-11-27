package edu.unifacef.aluguelDeCarros.exception;

public class DocumentNotFound extends RuntimeException {

    public DocumentNotFound(){
        super();
    }

    public DocumentNotFound(String message){
        super(message);
    }

}
