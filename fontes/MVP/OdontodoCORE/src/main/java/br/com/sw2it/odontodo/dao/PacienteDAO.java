/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2it.odontodo.dao;

import br.com.sw2it.odontodo.bean.Paciente;

/**
 *
 * @author marcelo
 */
public class PacienteDAO extends BaseDao<Paciente> {

    public PacienteDAO() {
        super(Paciente.class);
    }
    
}
