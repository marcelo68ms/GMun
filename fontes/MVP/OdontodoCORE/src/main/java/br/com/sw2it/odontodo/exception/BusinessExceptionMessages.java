package br.com.sw2it.odontodo.exception;

/**
 * <b>Descrição: Enum contendo códigos e mensagens de erro para BusinessException.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2015.
 */
public enum BusinessExceptionMessages {

    
    FALHA_AUTENTICACAO(1, "Usuario ou senha inválidos"),
    SENHA_NAO_INFORMADA(2, "Senha não informada"),    
    USUARIO_DUPLICADO(3, "Usuario já existe"),    
    SALVAR_USUARIO_DADOS_INVALIDOS(4, "Dados do usuário inválidos para gravação"),    
    DELETE_USUARIO_EM_USO(5, "Não é possível excluir, o usuário está em uso."),    
    SALVAR_DISPOSITIVO_DADOS_INVALIDOS(6, "Dados do dispositivo inválidos para gravação!"),    
    DELETE_DISPOSITIVO_EM_USO(7, "Não é possível excluir, o dispositivo está em uso!"),    
    DISPOSITIVO_DUPLICADO(8, "Já existe um dispositivo com esta identificação!"),    
    ID_DISPOSITIVO_TAMANHO_INVALIDO(9, "A identificação do dispositivo deve possuir no máximo 13 caracteres."),
    ID_DISPOSITIVO_TAMANHO_MAXIMO_INVALIDO(91, "A identificação do dispositivo deve possuir no máximo 13 caracteres."),    
    ID_DISPOSITIVO_TAMANHO_MINIMO_INVALIDO(92, "A identificação do dispositivo deve possuir no mínimo 6 caracteres."),
    FALHA_CARGA_DISPOSITIVOS(10, "Não foi possível efetuar a carga dos dispositivos!"),
    DELETE_SCRIPT_EM_USO(11, "Não é possível excluir, o script de atendimento está em uso."),
    MUDANCA_ESTADO_NOVO_INVALIDA(12, "Não é permitido mudar para o status selecionado a partir do status Novo."),
    MUDANCA_ESTADO_MANUTENCAO_INVALIDA(13, "Não é permitido mudar para o status selecionado a partir do staus Manutenção."),
    MUDANCA_ESTADO_PRONTO_INVALIDA(14, "Não é permitido mudar para o status selecionado a partir do status Pronto."),
    MUDANCA_ESTADO_DEFEITO_INVALIDA(15, "Não é permitido mudar para o status selecionado a partir do status Defeito."),
    MUDANCA_ESTADO_FABRICA_INVALIDA(16, "Não é permitido mudar para o status selecionado a partir do status Fábrica."),
    MUDANCA_ESTADO_USO_INVALIDA(17, "Não é permitido mudar para o status selecionado a partir do status Uso."),
    MUDANCA_ESTADO_DEVOLVIDO_INVALIDA(18, "Não é permitido mudar para o status selecionado a partir do status Devolvido."),    
    MUDANCA_ESTADO_DESCARTE_INVALIDA(19, "Não é permitido mudar para o status selecionado a partir do status Descarte."),
    MUDANCA_MESMO_ESTADO_INVALIDA(20, "O status selecionado para mudança já é o status atual"),
    MUDANCA_ESTADO_INVALIDA(21, "A partir do status atual não é possível mudar pra nenhum outro status"),
    ID_DISPOSITIVO_VALOR_INVALIDO(22, "A identificação do dispositivo deve possuir somente valores numéricos"),
    PARAMETRO_OBRIGATORIO_RELATORIO_HISTDISPOSITIVO(23, "É preciso selecionar ao menos um parâmetro."),
    PARAMETRO_OBRIGATORIO_RELATORIO_CHAMADAS_ORIGEM(24, "É preciso selecionar o Periodo Inicial e Final."),
    PACOTE_SERVICO_JA_CADASTRADO(200, "Já existe um pacote de serviços cadstrado com este título e/ou descrição !"),
    PACOTE_SERVICO_VENCIDO(201, "Pacote de serviços com a data de validade expirada"),
    DELETE_PACOTE_SERVICO_EM_USO(5, "Não é possível excluir, existem contratos associados a este pacote de serviços."),
    FILTRO_PESQUISA_PRE_ATENDIMENTO_NAO_INFORMADO(500, "Não foi informado um filtro para pesquisa de pre-atendimento"),
    SISTEMA_INDISPONIVEL(1000, "Sistema indiponível");

    private Integer value;
    private String label;

    /**
     * Construtor Padrao Instancia um novo objeto BusinessMessages.
     * @param value the value
     * @param label the label
     */
    private BusinessExceptionMessages(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * Nome: getValue Recupera o valor do atributo 'value'.
     * @return valor do atributo 'value'
     * @see
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Nome: setValue Registra o valor do atributo 'value'.
     * @param value valor do atributo value
     * @see
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Nome: getLabel Recupera o valor do atributo 'label'.
     * @return valor do atributo 'label'
     * @see
     */
    public String getLabel() {
        return label;
    }

    /**
     * Nome: setLabel Registra o valor do atributo 'label'.
     * @param label valor do atributo label
     * @see
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
