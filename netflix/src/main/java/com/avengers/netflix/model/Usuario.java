package com.avengers.netflix.model;

import java.time.LocalDate;

public class Usuario {
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String email;
    private String senhaHash;
    private String numeroCartao;
    private String validadeCartao;
    private String codigoSeguranca;
    private String nomeTitular;
    private String cpfCnpj;
    private boolean confirmado;

    public Usuario() {}

    public String getNomeCompleto(){return nomeCompleto;}
    public void setNomeCompleto(String nomeCompleto){this.nomeCompleto=nomeCompleto;}
    public LocalDate getDataNascimento(){return dataNascimento;}
    public void setDataNascimento(LocalDate dataNascimento){this.dataNascimento=dataNascimento;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public String getSenhaHash(){return senhaHash;}
    public void setSenhaHash(String senhaHash){this.senhaHash=senhaHash;}
    public String getNumeroCartao(){return numeroCartao;}
    public void setNumeroCartao(String numeroCartao){this.numeroCartao=numeroCartao;}
    public String getValidadeCartao(){return validadeCartao;}
    public void setValidadeCartao(String validadeCartao){this.validadeCartao=validadeCartao;}
    public String getCodigoSeguranca(){return codigoSeguranca;}
    public void setCodigoSeguranca(String codigoSeguranca){this.codigoSeguranca=codigoSeguranca;}
    public String getNomeTitular(){return nomeTitular;}
    public void setNomeTitular(String nomeTitular){this.nomeTitular=nomeTitular;}
    public String getCpfCnpj(){return cpfCnpj;}
    public void setCpfCnpj(String cpfCnpj){this.cpfCnpj=cpfCnpj;}
    public boolean isConfirmado(){return confirmado;}
    public void setConfirmado(boolean confirmado){this.confirmado=confirmado;}
}
