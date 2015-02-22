package br.com.sw2it.odontodo.exception;

/**
 * <b>Descrição: Exception genérica para regras exceptions retornadas pelo DAO.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2015.
 */
public class DataBaseException extends RuntimeException {

    /** Atributo delete violacao constraint. */
    public static final int DELETE_VIOLACAO_CONSTRAINT = 2000;

    /** Constante FALHA_COMUNICACAO_BANCO. */
    public static final int FALHA_COMUNICACAO_BANCO = 2001;

    /** Atributo exception code. */
    private int exceptionCode;

    /** Constante serialVersionUID. */
    public static final long serialVersionUID = -6353287459086929559L;

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     */
    public DataBaseException() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     * @param message the message
     */
    public DataBaseException(String message) {
        super(message);
    }

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     * @param code the code
     * @param message the message
     */
    public DataBaseException(int code, String message) {
        super(message);
        this.exceptionCode = code;
    }

    /**
     * Construtor Padrao Instancia um novo objeto DataBaseException.
     * @param cause the cause
     */
    public DataBaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Construtor Padrao Instancia um novo objeto DataBaseException.
     * @param message the message
     * @param cause the cause
     */
    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Nome: getExceptionCode Recupera o valor do atributo 'exceptionCode'.
     * @return valor do atributo 'exceptionCode'
     * @see
     */
    public int getExceptionCode() {
        return exceptionCode;
    }

    /**
     * Nome: setExceptionCode Registra o valor do atributo 'exceptionCode'.
     * @param exceptionCode valor do atributo exception code
     * @see
     */
    public void setExceptionCode(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

}
