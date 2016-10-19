/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dhiego.balthazar
 */

@Entity
@Table(name = "venda")
public class Venda implements Serializable{
    
    @Id
    @SequenceGenerator(name = "idVenda", sequenceName="idvenda_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPessoa")
    @Column(name="idPessoa", nullable=false)
    private Long idVenda;
    
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Calendar dataVenda;
    
    @Column(name = "tipoVenda")
    private String tipoVenda;
    
    @Column(name = "formaPagamento")
    private String formaPagamento;
    
    @Column(name = "desconto")
    private Double desconto;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="idPessoa", nullable=true)
    private Pessoa cliente;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="idFuncionario", nullable=false)
    private Funcionario vendedor;
    
    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemVenda> itens;

    public List<ItemVenda> getItens() {
        return itens;
    }
    
    public void addItem(ItemVenda item){
        item.setVenda(this);
        itens.add(item);
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Calendar getDataVenda() {
        return dataVenda;
    }

    public void setData(Calendar dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }
    
    
}
