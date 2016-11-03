package eagles.sabor_mel.view;


import eagles.sabor_mel.control.HashSha;
import eagles.sabor_mel.control.Mensagem;
import eagles.sabor_mel.control.Validacao;
import eagles.sabor_mel.dao.BairroDAO;
import eagles.sabor_mel.dao.CidadeDAO;
import eagles.sabor_mel.dao.DocumentoDAO;
import eagles.sabor_mel.dao.EnderecoDAO;
import eagles.sabor_mel.dao.EstadoDAO;
import eagles.sabor_mel.dao.FuncionarioDAO;
import eagles.sabor_mel.dao.PessoaDAO;
import eagles.sabor_mel.dao.ProdutoDAO;
import eagles.sabor_mel.dao.TelefoneDAO;
import eagles.sabor_mel.model.Acesso;
import eagles.sabor_mel.model.Bairro;
import eagles.sabor_mel.model.Cidade;
import eagles.sabor_mel.model.Documento;
import eagles.sabor_mel.model.Endereco;
import eagles.sabor_mel.model.Estado;
import eagles.sabor_mel.model.Funcionario;
import eagles.sabor_mel.model.Pessoa;
import eagles.sabor_mel.model.Produto;
import eagles.sabor_mel.model.Sexo;
import eagles.sabor_mel.model.Telefone;
import eagles.sabor_mel.model.TipoDocumento;
import eagles.sabor_mel.model.TipoTelefone;
import eagles.sabor_mel.test.Temp;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author etivideo
 */
public class Principal extends javax.swing.JFrame {
    /*Variavel global para definir as ações do operador*/
    public static String acao = "";
    
    /*Variavel para definir a opção selecionada do menu*/
    public static String menu = "";
    
    /*Variavel para definir o caminho da imagem*/
    public static String caminhoArquivo = "";
    public static String nomeArquivo = "";
    
    /*Variavel para definir valor total da venda*/
    public static Double totalParcialVenda = 0.0;
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        Login.permitir = true;

        if(Login.permitir){
            initComponents();
            if(Login.nivelAcesso.equals("Vendedor")){
                btnCompra.setVisible(false);
                btnRelatorio.setVisible(false);
                btnFornecedor.setVisible(false);
                btnUsuario.setVisible(false);
            }
            logado.setText("Usuário: "+Login.nome);
            this.setExtendedState(this.MAXIMIZED_BOTH); 
            carregaComboEstados();
            //carregaComboFornecedores();
            carregaDados();
            redefineEstilo();
        }
        else{
            JOptionPane.showMessageDialog(null, "Permissão Negada.... ");
            new Login().setVisible(true);
            this.dispose();
            //System.exit(0);
        }
        
        
    }

    public void carregaComboEstados() {
        EstadoDAO estadoDAO = new EstadoDAO();
        List<Estado> listDaoEstados = estadoDAO.findAll();
        
        for(int i = 0; i < listDaoEstados.size(); i++){
            estados.addItem(listDaoEstados.get(i).getUf());
            estadosCliente.addItem(listDaoEstados.get(i).getUf());
            estadosFornecedor.addItem(listDaoEstados.get(i).getUf());
        }
    }
    
//    public void carregaComboFornecedores(){
//        PessoaDAO dao = new PessoaDAO();
//        List<Pessoa> Listfornecedores = dao.findAll();
//        
//        for(int i = 0; i < Listfornecedores.size(); i++){
//           if(Listfornecedores.get(i).getDocumento().getTipo().toString().equals("CNPJ")){
//                comboFornecedores.addItem(Listfornecedores.get(i).getNome());
//            }
//        }
//    }

    public void redefineEstilo() {
        acessos.setBackground(Color.white);
        estados.setBackground(Color.white);
        sexo.setBackground(Color.white);
        
        
        checkSenha.setVisible(false);
        delete.setVisible(false);
        confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
        labelTelefone2.setVisible(false);
        ddd2.setVisible(false);
        telefone2.setVisible(false);
        delTel2.setVisible(false);
        labelTelefone3.setVisible(false);
        ddd3.setVisible(false);
        telefone3.setVisible(false);
        delTel3.setVisible(false);
        
        /*Cliente*/
        deleteCliente.setVisible(false);
        confirmCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
        labelTelefone2Cliente.setVisible(false);
        ddd2Cliente.setVisible(false);
        telefone2Cliente.setVisible(false);
        delTel2Cliente.setVisible(false);
        labelTelefone3Cliente.setVisible(false);
        ddd3Cliente.setVisible(false);
        telefone3Cliente.setVisible(false);
        delTel3Cliente.setVisible(false);
        
        /*Fornecedor*/
        deleteFornecedor.setVisible(false);
        confirmFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
        labelTelefone2Fornecedor.setVisible(false);
        ddd2Fornecedor.setVisible(false);
        telefone2Fornecedor.setVisible(false);
        delTel2Fornecedor.setVisible(false);
        labelTelefone3Fornecedor.setVisible(false);
        ddd3Fornecedor.setVisible(false);
        telefone3Fornecedor.setVisible(false);
        delTel3Fornecedor.setVisible(false);
        
        /*Produto*/
        deleteProduto.setVisible(false);
        
        mensagem.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public void carregaDados() {
        
        /*Funcionarios*/
        FuncionarioDAO dao = new FuncionarioDAO();
        List<Funcionario> funcionarios = dao.findAll();
        ((DefaultTableModel)tabelaUsuario.getModel()).setNumRows(0);
        for(int i = 0; i < funcionarios.size(); i++){
            String acesso = funcionarios.get(i).getAcesso().toString();
            
            ((DefaultTableModel)tabelaUsuario.getModel()).addRow(new String[]{
                funcionarios.get(i).getIdPessoa().toString(),
                funcionarios.get(i).getNome(),
                funcionarios.get(i).getUsuario(), 
                acesso
            });
            
            confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
            delete.setVisible(false);
        }
        
        /*Clientes*/
        PessoaDAO pesDAO = new PessoaDAO();
        List<Pessoa> listClientes = pesDAO.findAll();
        ((DefaultTableModel)tabelaCliente.getModel()).setNumRows(0);
        for(int i = 0; i < listClientes.size(); i++){
            if(listClientes.get(i).getDocumento().getTipo().toString().equals("CPF")){
                ((DefaultTableModel)tabelaCliente.getModel()).addRow(new String[]{
                    listClientes.get(i).getIdPessoa().toString(),
                    listClientes.get(i).getNome()
                });
            }
            
            confirmCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
            deleteCliente.setVisible(false);
        }
        
        /*Fornecedores*/
        PessoaDAO fornecedorDAO = new PessoaDAO();
        List<Pessoa> listFornecedores = fornecedorDAO.findAll();
        ((DefaultTableModel)tabelaFornecedor.getModel()).setNumRows(0);
        for(int i = 0; i < listFornecedores.size(); i++){
            if(listFornecedores.get(i).getDocumento().getTipo().toString().equals("CNPJ")){
                ((DefaultTableModel)tabelaFornecedor.getModel()).addRow(new String[]{
                    listClientes.get(i).getIdPessoa().toString(),
                    listClientes.get(i).getNome()
                });
            }
            
            confirmFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
            deleteFornecedor.setVisible(false);
        }
        
        /*Produtos*/
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> listProdutos = produtoDAO.findAll();
        ((DefaultTableModel)tabelaProduto.getModel()).setNumRows(0);
        for(int i = 0; i < listProdutos.size(); i++){
            Double total = listProdutos.get(i).getQuantidade() * listProdutos.get(i).getValorUnitario();
            ((DefaultTableModel)tabelaProduto.getModel()).addRow(new String[]{
                listProdutos.get(i).getIdProduto().toString(),
                listProdutos.get(i).getDescricao(),
                listProdutos.get(i).getValorUnitario().toString(),
                listProdutos.get(i).getQuantidade().toString(),
                total.toString() 
            });
            
            confirmProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
            deleteProduto.setVisible(false);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logo = new javax.swing.JLabel();
        btnVenda = new javax.swing.JButton();
        btnCliente = new javax.swing.JButton();
        btnProduto = new javax.swing.JButton();
        btnCompra = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        btnFornecedor = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        eagles = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        vendas = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        codigoProduto = new javax.swing.JTextField();
        procuraProduto = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelaVendaProduto = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        totalVendaProduto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        clientes = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCliente = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        nomeCliente = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        emailCliente = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        dataNascimentoCliente = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("##/##/####");
            dataNascimentoCliente = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        jLabel31 = new javax.swing.JLabel();
        documentoCliente = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("###.###.###-##");
            documentoCliente = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        jLabel32 = new javax.swing.JLabel();
        dddCliente = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            dddCliente = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefoneCliente = new javax.swing.JTextField();
        addTelCliente = new javax.swing.JButton();
        labelTelefone2Cliente = new javax.swing.JLabel();
        ddd2Cliente = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            ddd2Cliente = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefone2Cliente = new javax.swing.JTextField();
        delTel2Cliente = new javax.swing.JButton();
        labelTelefone3Cliente = new javax.swing.JLabel();
        ddd3Cliente = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            ddd3Cliente = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefone3Cliente = new javax.swing.JTextField();
        delTel3Cliente = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        sexoCliente = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cepCliente = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("#####-###");
            cepCliente = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        jLabel38 = new javax.swing.JLabel();
        logradouroCliente = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        numeroCliente = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        bairroCliente = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        cidadeCliente = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        estadosCliente = new javax.swing.JComboBox<>();
        refreshCliente = new javax.swing.JButton();
        deleteCliente = new javax.swing.JButton();
        confirmCliente = new javax.swing.JButton();
        produtos = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaProduto = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        quantidadeProduto = new javax.swing.JSpinner();
        jLabel55 = new javax.swing.JLabel();
        precoProduto = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        descricaoProduto = new javax.swing.JTextArea();
        deleteProduto = new javax.swing.JButton();
        confirmProduto = new javax.swing.JButton();
        refreshProduto = new javax.swing.JButton();
        imagemProduto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        codigoBarrasProduto = new javax.swing.JFormattedTextField();
        compras = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        relatorios = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fornecedores = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaFornecedor = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        nomeFornecedor = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        emailFornecedor = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        documentoFornecedor = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("##.###.###/####-##");
            documentoFornecedor = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        dddFornecedor = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            dddFornecedor = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefoneFornecedor = new javax.swing.JTextField();
        addTelefoneFornecedor = new javax.swing.JButton();
        labelTelefone2Fornecedor = new javax.swing.JLabel();
        ddd2Fornecedor = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            ddd2Fornecedor = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefone2Fornecedor = new javax.swing.JTextField();
        delTel2Fornecedor = new javax.swing.JButton();
        labelTelefone3Fornecedor = new javax.swing.JLabel();
        ddd3Fornecedor = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            ddd3Fornecedor = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefone3Fornecedor = new javax.swing.JTextField();
        delTel3Fornecedor = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        cepFornecedor = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("#####-###");
            cepFornecedor = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        jLabel49 = new javax.swing.JLabel();
        logradouroFornecedor = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        numeroFornecedor = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        bairroFornecedor = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        cidadeFornecedor = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        estadosFornecedor = new javax.swing.JComboBox<>();
        refreshFornecedor = new javax.swing.JButton();
        deleteFornecedor = new javax.swing.JButton();
        confirmFornecedor = new javax.swing.JButton();
        usuarios = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuario = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        dataNascimento = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("##/##/####");
            dataNascimento = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        jLabel12 = new javax.swing.JLabel();
        documento = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("###.###.###-##");
            documento = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        jLabel13 = new javax.swing.JLabel();
        ddd = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            ddd = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefone = new javax.swing.JTextField();
        addTelefone = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cep = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("#####-###");
            cep = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        jLabel16 = new javax.swing.JLabel();
        logradouro = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        estados = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        senha = new javax.swing.JPasswordField();
        jLabel24 = new javax.swing.JLabel();
        acessos = new javax.swing.JComboBox<>();
        delete = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
        labelTelefone2 = new javax.swing.JLabel();
        ddd2 = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            ddd2 = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefone2 = new javax.swing.JTextField();
        labelTelefone3 = new javax.swing.JLabel();
        ddd3 = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("(##)");
            ddd3 = new javax.swing.JFormattedTextField(mask);
        }
        catch (Exception e){
        }
        telefone3 = new javax.swing.JTextField();
        bairro = new javax.swing.JTextField();
        cidade = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox<>();
        checkSenha = new javax.swing.JCheckBox();
        delTel2 = new javax.swing.JButton();
        delTel3 = new javax.swing.JButton();
        mensagem = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        logado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new_logo.png"))); // NOI18N

        btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cart.png"))); // NOI18N
        btnVenda.setText("Vendas");
        btnVenda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVendaMouseClicked(evt);
            }
        });
        btnVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaActionPerformed(evt);
            }
        });

        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png"))); // NOI18N
        btnCliente.setText("Clientes");
        btnCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClienteMouseClicked(evt);
            }
        });
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clothes.png"))); // NOI18N
        btnProduto.setText("Produtos");
        btnProduto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutoActionPerformed(evt);
            }
        });

        btnCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buying.png"))); // NOI18N
        btnCompra.setText("Compras");
        btnCompra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraActionPerformed(evt);
            }
        });

        btnRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N
        btnRelatorio.setText("Relatórios");
        btnRelatorio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });

        btnFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/truck.png"))); // NOI18N
        btnFornecedor.setText("Fornecedores");
        btnFornecedor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornecedorActionPerformed(evt);
            }
        });

        btnUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users.png"))); // NOI18N
        btnUsuario.setText("Usuários");
        btnUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });

        eagles.setText("Eagle's Alliance © 2016");

        mainPanel.setLayout(new java.awt.CardLayout());

        jLabel57.setText("Produto");

        procuraProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        procuraProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                procuraProdutoMouseClicked(evt);
            }
        });

        tabelaVendaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tabelaVendaProduto);

        jLabel7.setText("TOTAL");

        totalVendaProduto.setEditable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_icon.png"))); // NOI18N

        javax.swing.GroupLayout vendasLayout = new javax.swing.GroupLayout(vendas);
        vendas.setLayout(vendasLayout);
        vendasLayout.setHorizontalGroup(
            vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(vendasLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vendasLayout.createSequentialGroup()
                        .addGroup(vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(procuraProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(807, Short.MAX_VALUE))
        );
        vendasLayout.setVerticalGroup(
            vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendasLayout.createSequentialGroup()
                .addGroup(vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vendasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel57))
                    .addGroup(vendasLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(codigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(procuraProduto)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(totalVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(256, Short.MAX_VALUE))
        );

        mainPanel.add(vendas, "vendas");

        jLabel26.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel26.setText("Clientes");

        tabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaCliente);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setText("Dados Pessoais");

        jLabel27.setText("Nome");

        jLabel28.setText("E-Mail");

        jLabel30.setText("Data de Nascimento");

        jLabel31.setText("CPF");

        jLabel32.setText("Telefone");

        addTelCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        addTelCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addTelClienteMouseClicked(evt);
            }
        });

        labelTelefone2Cliente.setText("Telefone");

        delTel2Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_icon.png"))); // NOI18N
        delTel2Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delTel2ClienteMouseClicked(evt);
            }
        });

        labelTelefone3Cliente.setText("Telefone");

        delTel3Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_icon.png"))); // NOI18N
        delTel3Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delTel3ClienteMouseClicked(evt);
            }
        });

        jLabel35.setText("Sexo");

        sexoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        jLabel36.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel36.setText("Endereço");

        jLabel37.setText("CEP");

        jLabel38.setText("Logradouro");

        jLabel39.setText("N°");

        jLabel40.setText("Bairro");

        jLabel41.setText("Cidade");

        jLabel42.setText("Estado");

        refreshCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh-page-option.png"))); // NOI18N
        refreshCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshClienteMouseClicked(evt);
            }
        });

        deleteCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        deleteCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteClienteMouseClicked(evt);
            }
        });

        confirmCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png"))); // NOI18N
        confirmCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout clientesLayout = new javax.swing.GroupLayout(clientes);
        clientes.setLayout(clientesLayout);
        clientesLayout.setHorizontalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientesLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, clientesLayout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dataNascimentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(documentoCliente))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, clientesLayout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(clientesLayout.createSequentialGroup()
                                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(clientesLayout.createSequentialGroup()
                                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(clientesLayout.createSequentialGroup()
                                                            .addComponent(jLabel40)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(bairroCliente))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, clientesLayout.createSequentialGroup()
                                                            .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(clientesLayout.createSequentialGroup()
                                                                    .addComponent(jLabel37)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(cepCliente))
                                                                .addGroup(clientesLayout.createSequentialGroup()
                                                                    .addComponent(jLabel35)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(sexoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jLabel38)))
                                                    .addComponent(jLabel36))
                                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(clientesLayout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(clientesLayout.createSequentialGroup()
                                                                .addComponent(logradouroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel39)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(numeroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(clientesLayout.createSequentialGroup()
                                                                .addComponent(jLabel41)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cidadeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel42)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(estadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(clientesLayout.createSequentialGroup()
                                                        .addGap(195, 195, 195)
                                                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(clientesLayout.createSequentialGroup()
                                                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(labelTelefone3Cliente)
                                                                    .addComponent(labelTelefone2Cliente))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(ddd2Cliente)
                                                                    .addComponent(ddd3Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(clientesLayout.createSequentialGroup()
                                                                        .addComponent(telefone2Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(delTel2Cliente))
                                                                    .addGroup(clientesLayout.createSequentialGroup()
                                                                        .addComponent(telefone3Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(delTel3Cliente))))
                                                            .addGroup(clientesLayout.createSequentialGroup()
                                                                .addComponent(jLabel32)
                                                                .addGap(12, 12, 12)
                                                                .addComponent(dddCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(telefoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(addTelCliente)))))))
                                        .addGap(0, 80, Short.MAX_VALUE))
                                    .addGroup(clientesLayout.createSequentialGroup()
                                        .addComponent(refreshCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(confirmCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteCliente)))
                                .addContainerGap())))
                    .addGroup(clientesLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addContainerGap())))
        );
        clientesLayout.setVerticalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientesLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(nomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(emailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(dataNascimentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31)
                                    .addComponent(documentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(sexoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(clientesLayout.createSequentialGroup()
                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel32)
                                        .addComponent(dddCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(telefoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(addTelCliente))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(delTel3Cliente)
                                    .addGroup(clientesLayout.createSequentialGroup()
                                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(ddd2Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(telefone2Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(labelTelefone2Cliente))
                                            .addComponent(delTel2Cliente))
                                        .addGap(14, 14, 14)
                                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelTelefone3Cliente)
                                            .addComponent(ddd3Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(telefone3Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel36)
                        .addGap(18, 18, 18)
                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(cepCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38)
                            .addComponent(logradouroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)
                            .addComponent(numeroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(bairroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(cidadeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)
                            .addComponent(estadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshCliente)
                            .addComponent(confirmCliente)
                            .addComponent(deleteCliente)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                .addContainerGap())
        );

        mainPanel.add(clientes, "clientes");

        jLabel46.setText("Produtos");

        tabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "R$", "QTD", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelaProduto);

        jLabel47.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel47.setText("Cadastro de Produtos");

        jLabel54.setText("Quantidade");

        quantidadeProduto.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel55.setText("Preço");

        jLabel56.setText("Descrição");

        descricaoProduto.setColumns(20);
        descricaoProduto.setRows(5);
        jScrollPane5.setViewportView(descricaoProduto);

        deleteProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        deleteProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteProdutoMouseClicked(evt);
            }
        });

        confirmProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png"))); // NOI18N
        confirmProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmProdutoMouseClicked(evt);
            }
        });

        refreshProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh-page-option.png"))); // NOI18N
        refreshProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshProdutoMouseClicked(evt);
            }
        });

        imagemProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagemProduto.setText("IMAGEM");
        imagemProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        imagemProduto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imagemProduto.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        imagemProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagemProdutoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imagemProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imagemProdutoMouseExited(evt);
            }
        });

        jLabel2.setText("Código de Barras");

        try {
            codigoBarrasProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout produtosLayout = new javax.swing.GroupLayout(produtos);
        produtos.setLayout(produtosLayout);
        produtosLayout.setHorizontalGroup(
            produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produtosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(produtosLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addGroup(produtosLayout.createSequentialGroup()
                                .addComponent(imagemProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(produtosLayout.createSequentialGroup()
                                        .addComponent(refreshProduto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(confirmProduto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteProduto))
                                    .addComponent(jLabel56)
                                    .addGroup(produtosLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(codigoBarrasProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(produtosLayout.createSequentialGroup()
                                        .addComponent(jLabel54)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(quantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel55)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(precoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel46))
                .addGap(107, 107, 107))
        );
        produtosLayout.setVerticalGroup(
            produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produtosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(produtosLayout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(18, 18, 18)
                        .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imagemProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(produtosLayout.createSequentialGroup()
                                .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(codigoBarrasProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addComponent(jLabel56)
                                .addGap(8, 8, 8)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produtosLayout.createSequentialGroup()
                                        .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel54)
                                            .addComponent(quantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel55)
                                            .addComponent(precoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                        .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(deleteProduto, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(refreshProduto, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(produtosLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(confirmProduto)))))))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        mainPanel.add(produtos, "produtos");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("PANEL COMPRAS - UNDER CONSTRUCTION...");

        javax.swing.GroupLayout comprasLayout = new javax.swing.GroupLayout(compras);
        compras.setLayout(comprasLayout);
        comprasLayout.setHorizontalGroup(
            comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(683, Short.MAX_VALUE))
        );
        comprasLayout.setVerticalGroup(
            comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(423, Short.MAX_VALUE))
        );

        mainPanel.add(compras, "compras");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setText("PANEL RELATORIOS - UNDER CONSTRUCTION...");

        javax.swing.GroupLayout relatoriosLayout = new javax.swing.GroupLayout(relatorios);
        relatorios.setLayout(relatoriosLayout);
        relatoriosLayout.setHorizontalGroup(
            relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(650, Short.MAX_VALUE))
        );
        relatoriosLayout.setVerticalGroup(
            relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(423, Short.MAX_VALUE))
        );

        mainPanel.add(relatorios, "relatorios");

        jLabel29.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel29.setText("Fornecedores");

        tabelaFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFornecedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaFornecedor);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setText("Dados");

        jLabel33.setText("Nome Fantasia");

        jLabel34.setText("E-mail");

        jLabel43.setText("CNPJ");

        jLabel44.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel44.setText("Endereço");

        jLabel45.setText("Telefone");

        addTelefoneFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        addTelefoneFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addTelefoneFornecedorMouseClicked(evt);
            }
        });

        labelTelefone2Fornecedor.setText("Telefone 2");

        delTel2Fornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_icon.png"))); // NOI18N
        delTel2Fornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delTel2FornecedorMouseClicked(evt);
            }
        });

        labelTelefone3Fornecedor.setText("Telefone 3");

        delTel3Fornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_icon.png"))); // NOI18N
        delTel3Fornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delTel3FornecedorMouseClicked(evt);
            }
        });

        jLabel48.setText("CEP");

        jLabel49.setText("Logradouro");

        jLabel50.setText("Nº");

        jLabel51.setText("Bairro");

        jLabel52.setText("Cidade");

        jLabel53.setText("Estado");

        refreshFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh-page-option.png"))); // NOI18N
        refreshFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshFornecedorMouseClicked(evt);
            }
        });

        deleteFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        deleteFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteFornecedorMouseClicked(evt);
            }
        });

        confirmFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png"))); // NOI18N
        confirmFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmFornecedorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fornecedoresLayout = new javax.swing.GroupLayout(fornecedores);
        fornecedores.setLayout(fornecedoresLayout);
        fornecedoresLayout.setHorizontalGroup(
            fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fornecedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addGroup(fornecedoresLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fornecedoresLayout.createSequentialGroup()
                                .addComponent(refreshFornecedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(confirmFornecedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteFornecedor))
                            .addGroup(fornecedoresLayout.createSequentialGroup()
                                .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(fornecedoresLayout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(fornecedoresLayout.createSequentialGroup()
                                        .addComponent(jLabel43)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(documentoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addComponent(jLabel44)
                            .addGroup(fornecedoresLayout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cepFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(fornecedoresLayout.createSequentialGroup()
                                .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fornecedoresLayout.createSequentialGroup()
                                        .addComponent(jLabel49)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(logradouroFornecedor))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fornecedoresLayout.createSequentialGroup()
                                        .addComponent(jLabel51)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bairroFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel52)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cidadeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(fornecedoresLayout.createSequentialGroup()
                                        .addComponent(jLabel53)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(estadosFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(fornecedoresLayout.createSequentialGroup()
                                        .addComponent(jLabel50)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numeroFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(105, 105, 105))
                            .addGroup(fornecedoresLayout.createSequentialGroup()
                                .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(fornecedoresLayout.createSequentialGroup()
                                        .addComponent(labelTelefone3Fornecedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ddd3Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fornecedoresLayout.createSequentialGroup()
                                        .addGap(286, 286, 286)
                                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel45)
                                            .addComponent(labelTelefone2Fornecedor))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ddd2Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dddFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fornecedoresLayout.createSequentialGroup()
                                        .addComponent(telefone3Fornecedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(delTel3Fornecedor))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fornecedoresLayout.createSequentialGroup()
                                        .addComponent(telefone2Fornecedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(delTel2Fornecedor))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fornecedoresLayout.createSequentialGroup()
                                        .addComponent(telefoneFornecedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addTelefoneFornecedor)))))))
                .addGap(151, 151, 151))
        );
        fornecedoresLayout.setVerticalGroup(
            fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fornecedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(fornecedoresLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(nomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(emailFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(documentoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(dddFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefoneFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addTelefoneFornecedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTelefone2Fornecedor)
                            .addComponent(ddd2Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefone2Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delTel2Fornecedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTelefone3Fornecedor)
                            .addComponent(ddd3Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefone3Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delTel3Fornecedor))
                        .addGap(19, 19, 19)
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(cepFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(logradouroFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50)
                            .addComponent(numeroFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(bairroFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52)
                            .addComponent(cidadeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53)
                            .addComponent(estadosFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(fornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshFornecedor)
                            .addComponent(deleteFornecedor)
                            .addComponent(confirmFornecedor))))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        mainPanel.add(fornecedores, "fornecedores");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel8.setText("Usuários");

        tabelaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Usuário", "Acesso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaUsuario);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Dados Pessoais");

        jLabel9.setText("Nome");

        nome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nomeMouseClicked(evt);
            }
        });

        jLabel10.setText("E-Mail");

        jLabel11.setText("Data de Nascimento");

        jLabel12.setText("CPF");

        jLabel13.setText("Telefone");

        addTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        addTelefone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addTelefoneMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel14.setText("Endereço");

        jLabel15.setText("CEP");

        jLabel16.setText("Logradouro");

        jLabel17.setText("Nº");

        jLabel18.setText("Bairro");

        jLabel19.setText("Cidade");

        jLabel20.setText("Estado");

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel21.setText("Dados de Acesso");

        jLabel22.setText("Usuário");

        jLabel23.setText("Senha");

        jLabel24.setText("Acesso");

        acessos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendedor", "Administrador" }));

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });

        confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png"))); // NOI18N
        confirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmMouseClicked(evt);
            }
        });

        labelTelefone2.setText("Telefone 2");

        labelTelefone3.setText("Telefone 3");

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh-page-option.png"))); // NOI18N
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });

        jLabel25.setText("Sexo");

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        checkSenha.setText("Cadastrar Nova Senha");
        checkSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkSenhaMouseClicked(evt);
            }
        });

        delTel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_icon.png"))); // NOI18N
        delTel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delTel2MouseClicked(evt);
            }
        });

        delTel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_icon.png"))); // NOI18N
        delTel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delTel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout usuariosLayout = new javax.swing.GroupLayout(usuarios);
        usuarios.setLayout(usuariosLayout);
        usuariosLayout.setHorizontalGroup(
            usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usuariosLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(usuariosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(usuariosLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel6))
                                    .addGroup(usuariosLayout.createSequentialGroup()
                                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(usuariosLayout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(documento))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, usuariosLayout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(usuariosLayout.createSequentialGroup()
                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(usuariosLayout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(usuariosLayout.createSequentialGroup()
                                        .addComponent(labelTelefone3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ddd3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(telefone3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(delTel3))
                                    .addGroup(usuariosLayout.createSequentialGroup()
                                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelTelefone2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(usuariosLayout.createSequentialGroup()
                                                .addComponent(ddd2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(telefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(delTel2))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuariosLayout.createSequentialGroup()
                                                .addComponent(ddd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addTelefone)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(usuariosLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel14)
                                    .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(usuariosLayout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel19)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel20)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(estados, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(usuariosLayout.createSequentialGroup()
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, usuariosLayout.createSequentialGroup()
                                            .addComponent(btnRefresh)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(confirm)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(delete))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, usuariosLayout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel23)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(checkSenha)
                                                .addGroup(usuariosLayout.createSequentialGroup()
                                                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel24)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(acessos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(usuariosLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(65, 65, 65))))
        );
        usuariosLayout.setVerticalGroup(
            usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(usuariosLayout.createSequentialGroup()
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(dataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addTelefone)
                            .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(ddd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(delTel2)
                            .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelTelefone2)
                                .addComponent(ddd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(telefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelTelefone3)
                                .addComponent(ddd3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(telefone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(delTel3))
                        .addGap(19, 19, 19)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(estados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuariosLayout.createSequentialGroup()
                                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)))
                        .addGap(19, 19, 19)
                        .addComponent(jLabel21)
                        .addGap(1, 1, 1)
                        .addComponent(checkSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(confirm)
                            .addGroup(usuariosLayout.createSequentialGroup()
                                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)
                                    .addComponent(acessos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(usuariosLayout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(btnRefresh))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuariosLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(delete))))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(usuarios, "usuarios");

        mensagem.setBackground(new java.awt.Color(255, 255, 255));
        mensagem.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        mensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
        });

        logado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnVenda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRelatorio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(eagles)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProduto)
                        .addGap(18, 18, 18)
                        .addComponent(btnCompra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRelatorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFornecedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eagles)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaActionPerformed
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "vendas");
        menu = "vendas";
    }//GEN-LAST:event_btnVendaActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "clientes");
        menu = "clientes";
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutoActionPerformed
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "produtos");
        menu = "produtos";
        //carregaComboFornecedores();
    }//GEN-LAST:event_btnProdutoActionPerformed

    private void btnCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraActionPerformed
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "compras");
        menu = "compras";
    }//GEN-LAST:event_btnCompraActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "relatorios");
        menu = "relatorios";
    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void btnFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornecedorActionPerformed
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "fornecedores");
        menu = "fornecedores";
    }//GEN-LAST:event_btnFornecedorActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "usuarios");
        menu = "usuarios";
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void btnVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendaMouseClicked
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "vendas");
        menu = "vendas";
    }//GEN-LAST:event_btnVendaMouseClicked

    private void btnClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClienteMouseClicked

   
    public void limpaCampos() {
        
        if(menu.equals("usuarios")){
            for (Component C : usuarios.getComponents()){
            
                if (C instanceof JTextField){

                    ((JTextComponent) C).setText(null);
                }
                
            }
            tabelaUsuario.clearSelection();
            
            checkSenha.setVisible(false);
            senha.setEnabled(true);
            senha.setEditable(true);
        }
        
        if(menu.equals("clientes")){
            for (Component C : clientes.getComponents()){
            
                if (C instanceof JTextField){

                    ((JTextComponent) C).setText(null);
                }
                
            }
            tabelaCliente.clearSelection();
            
        }
        
        if(menu.equals("fornecedores")){
            for (Component C : fornecedores.getComponents()){
            
                if (C instanceof JTextField){

                    ((JTextComponent) C).setText(null);
                }
                
            }
            tabelaFornecedor.clearSelection();
            
        }
        
        if(menu.equals("produtos")){
            for (Component C : produtos.getComponents()){
            
                if (C instanceof JTextField){

                    ((JTextComponent) C).setText(null);
                }
                
            }
            tabelaProduto.clearSelection();
            descricaoProduto.setText(null);
            imagemProduto.setIcon(null);
            imagemProduto.setText("IMAGEM");
            quantidadeProduto.setValue(1);
            
        }
        
        
        labelTelefone2.setVisible(false);
        ddd2.setVisible(false);
        telefone2.setVisible(false);
        delTel2.setVisible(false);
        labelTelefone3.setVisible(false);
        ddd3.setVisible(false);
        telefone3.setVisible(false);
        delTel3.setVisible(false);
        
        labelTelefone2Cliente.setVisible(false);
        ddd2Cliente.setVisible(false);
        telefone2Cliente.setVisible(false);
        delTel2Cliente.setVisible(false);
        labelTelefone3Cliente.setVisible(false);
        ddd3Cliente.setVisible(false);
        telefone3Cliente.setVisible(false);
        delTel3Cliente.setVisible(false);
        
        labelTelefone2Fornecedor.setVisible(false);
        ddd2Fornecedor.setVisible(false);
        telefone2Fornecedor.setVisible(false);
        delTel2Fornecedor.setVisible(false);
        labelTelefone3Fornecedor.setVisible(false);
        ddd3Fornecedor.setVisible(false);
        telefone3Fornecedor.setVisible(false);
        delTel3Fornecedor.setVisible(false);
        
        
    }

    public void preencheFormulario() throws NumberFormatException {
       
        if(menu.equals("usuarios")){
            DecimalFormat df = new DecimalFormat("00");
            DecimalFormat dff = new DecimalFormat("0000");
            Long id = Long.parseLong((String) tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0));
            FuncionarioDAO dao = new FuncionarioDAO();
            Funcionario funcionario = dao.getById(id);

            checkSenha.setVisible(true);
            checkSenha.setSelected(false);
            senha.setEditable(false);
            senha.setEnabled(false);


            nome.setText(funcionario.getNome());
            email.setText(funcionario.getEmail());

            String dia = (df.format(funcionario.getDataNascimento().get(Calendar.DAY_OF_MONTH)));
            String mes = (df.format(funcionario.getDataNascimento().get(Calendar.MONTH)+1));
            String ano = dff.format(funcionario.getDataNascimento().get(Calendar.YEAR));

            dataNascimento.setText(dia+"/"+mes+"/"+ano);
            documento.setText(funcionario.getDocumento().getNumero());

            if(funcionario.getTelefones().size() == 1){
                labelTelefone2.setVisible(false);
                ddd2.setVisible(false);
                ddd2.setText(null);
                telefone2.setVisible(false);
                telefone2.setText(null);
                delTel2.setVisible(false);

                labelTelefone3.setVisible(false);
                ddd3.setVisible(false);
                ddd3.setText(null);
                telefone3.setVisible(false);
                telefone3.setText(null);
                delTel3.setVisible(false);

                ddd.setText(funcionario.getTelefones().get(0).getDdd());
                telefone.setText(funcionario.getTelefones().get(0).getNumero());
            }


            if(funcionario.getTelefones().size() == 2){
                labelTelefone2.setVisible(true);
                ddd2.setVisible(true);
                telefone2.setVisible(true);
                delTel2.setVisible(true);

                labelTelefone3.setVisible(false);
                ddd3.setVisible(false);
                ddd3.setText(null);
                telefone3.setVisible(false);
                telefone3.setText(null);
                delTel3.setVisible(false);

                ddd.setText(funcionario.getTelefones().get(0).getDdd());
                telefone.setText(funcionario.getTelefones().get(0).getNumero());
                ddd2.setText(funcionario.getTelefones().get(1).getDdd());
                telefone2.setText(funcionario.getTelefones().get(1).getNumero());
            }


            if(funcionario.getTelefones().size() == 3){
                labelTelefone2.setVisible(true);
                ddd2.setVisible(true);
                telefone2.setVisible(true);
                delTel2.setVisible(true);

                ddd2.setText(funcionario.getTelefones().get(1).getDdd());
                telefone2.setText(funcionario.getTelefones().get(1).getNumero());

                labelTelefone3.setVisible(true);
                ddd3.setVisible(true);
                telefone3.setVisible(true);
                delTel3.setVisible(true);

                ddd.setText(funcionario.getTelefones().get(0).getDdd());
                telefone.setText(funcionario.getTelefones().get(0).getNumero());
                ddd2.setText(funcionario.getTelefones().get(1).getDdd());
                telefone2.setText(funcionario.getTelefones().get(1).getNumero());
                ddd3.setText(funcionario.getTelefones().get(2).getDdd());
                telefone3.setText(funcionario.getTelefones().get(2).getNumero());
            }




            cep.setText(funcionario.getEndereco().getCep());
            logradouro.setText(funcionario.getEndereco().getLogradouro());
            numero.setText(funcionario.getEndereco().getNumero());
            bairro.setText(funcionario.getEndereco().getBairro().getNome());
            cidade.setText(funcionario.getEndereco().getBairro().getCidade().getNome());
            usuario.setText(funcionario.getUsuario());



            senha.setText(null);


            acessos.setSelectedItem(funcionario.getAcesso().toString());

            estados.setSelectedItem(funcionario.getEndereco().getBairro().getCidade().getEstado().getUf());
            sexo.setSelectedItem(funcionario.getSexo().toString());

            delete.setVisible(true);
            confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png")));
        }
        
        
        if(menu.equals("clientes")){
            DecimalFormat df = new DecimalFormat("00");
            DecimalFormat dff = new DecimalFormat("0000");
            Long id = Long.parseLong((String) tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0));
            PessoaDAO dao = new PessoaDAO();
            Pessoa pessoa = dao.getById(id);

            nomeCliente.setText(pessoa.getNome());
            emailCliente.setText(pessoa.getEmail());

            String dia = (df.format(pessoa.getDataNascimento().get(Calendar.DAY_OF_MONTH)));
            String mes = (df.format(pessoa.getDataNascimento().get(Calendar.MONTH)+1));
            String ano = dff.format(pessoa.getDataNascimento().get(Calendar.YEAR));

            dataNascimentoCliente.setText(dia+"/"+mes+"/"+ano);
            documentoCliente.setText(pessoa.getDocumento().getNumero());

            if(pessoa.getTelefones().size() == 1){
                labelTelefone2Cliente.setVisible(false);
                ddd2Cliente.setVisible(false);
                ddd2Cliente.setText(null);
                telefone2Cliente.setVisible(false);
                telefone2Cliente.setText(null);
                delTel2Cliente.setVisible(false);

                labelTelefone3Cliente.setVisible(false);
                ddd3Cliente.setVisible(false);
                ddd3Cliente.setText(null);
                telefone3Cliente.setVisible(false);
                telefone3Cliente.setText(null);
                delTel3Cliente.setVisible(false);

                dddCliente.setText(pessoa.getTelefones().get(0).getDdd());
                telefoneCliente.setText(pessoa.getTelefones().get(0).getNumero());
            }


            if(pessoa.getTelefones().size() == 2){
                labelTelefone2Cliente.setVisible(true);
                ddd2Cliente.setVisible(true);
                telefone2Cliente.setVisible(true);
                delTel2Cliente.setVisible(true);

                labelTelefone3Cliente.setVisible(false);
                ddd3Cliente.setVisible(false);
                ddd3Cliente.setText(null);
                telefone3Cliente.setVisible(false);
                telefone3Cliente.setText(null);
                delTel3Cliente.setVisible(false);

                dddCliente.setText(pessoa.getTelefones().get(0).getDdd());
                telefoneCliente.setText(pessoa.getTelefones().get(0).getNumero());
                ddd2Cliente.setText(pessoa.getTelefones().get(1).getDdd());
                telefone2Cliente.setText(pessoa.getTelefones().get(1).getNumero());
            }


            if(pessoa.getTelefones().size() == 3){
                labelTelefone2Cliente.setVisible(true);
                ddd2Cliente.setVisible(true);
                telefone2Cliente.setVisible(true);
                delTel2Cliente.setVisible(true);

                ddd2Cliente.setText(pessoa.getTelefones().get(1).getDdd());
                telefone2Cliente.setText(pessoa.getTelefones().get(1).getNumero());

                labelTelefone3Cliente.setVisible(true);
                ddd3Cliente.setVisible(true);
                telefone3Cliente.setVisible(true);
                delTel3Cliente.setVisible(true);

                dddCliente.setText(pessoa.getTelefones().get(0).getDdd());
                telefoneCliente.setText(pessoa.getTelefones().get(0).getNumero());
                ddd2Cliente.setText(pessoa.getTelefones().get(1).getDdd());
                telefone2Cliente.setText(pessoa.getTelefones().get(1).getNumero());
                ddd3Cliente.setText(pessoa.getTelefones().get(2).getDdd());
                telefone3Cliente.setText(pessoa.getTelefones().get(2).getNumero());
            }




            cepCliente.setText(pessoa.getEndereco().getCep());
            logradouroCliente.setText(pessoa.getEndereco().getLogradouro());
            numeroCliente.setText(pessoa.getEndereco().getNumero());
            bairroCliente.setText(pessoa.getEndereco().getBairro().getNome());
            cidadeCliente.setText(pessoa.getEndereco().getBairro().getCidade().getNome());





            estadosCliente.setSelectedItem(pessoa.getEndereco().getBairro().getCidade().getEstado().getUf());
            sexoCliente.setSelectedItem(pessoa.getSexo().toString());

            deleteCliente.setVisible(true);
            confirmCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png")));

        }
        
        if(menu.equals("fornecedores")){
            DecimalFormat df = new DecimalFormat("00");
            DecimalFormat dff = new DecimalFormat("0000");
            Long id = Long.parseLong((String) tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0));
            PessoaDAO dao = new PessoaDAO();
            Pessoa pessoa = dao.getById(id);

            nomeFornecedor.setText(pessoa.getNome());
            emailFornecedor.setText(pessoa.getEmail());

            
            documentoFornecedor.setText(pessoa.getDocumento().getNumero());

            if(pessoa.getTelefones().size() == 1){
                labelTelefone2Fornecedor.setVisible(false);
                ddd2Fornecedor.setVisible(false);
                ddd2Fornecedor.setText(null);
                telefone2Fornecedor.setVisible(false);
                telefone2Fornecedor.setText(null);
                delTel2Fornecedor.setVisible(false);

                labelTelefone3Fornecedor.setVisible(false);
                ddd3Fornecedor.setVisible(false);
                ddd3Fornecedor.setText(null);
                telefone3Fornecedor.setVisible(false);
                telefone3Fornecedor.setText(null);
                delTel3Fornecedor.setVisible(false);

                dddFornecedor.setText(pessoa.getTelefones().get(0).getDdd());
                telefoneFornecedor.setText(pessoa.getTelefones().get(0).getNumero());
            }


            if(pessoa.getTelefones().size() == 2){
                labelTelefone2Fornecedor.setVisible(true);
                ddd2Fornecedor.setVisible(true);
                telefone2Fornecedor.setVisible(true);
                delTel2Fornecedor.setVisible(true);

                labelTelefone3Fornecedor.setVisible(false);
                ddd3Fornecedor.setVisible(false);
                ddd3Fornecedor.setText(null);
                telefone3Fornecedor.setVisible(false);
                telefone3Fornecedor.setText(null);
                delTel3Fornecedor.setVisible(false);

                dddFornecedor.setText(pessoa.getTelefones().get(0).getDdd());
                telefoneFornecedor.setText(pessoa.getTelefones().get(0).getNumero());
                ddd2Fornecedor.setText(pessoa.getTelefones().get(1).getDdd());
                telefone2Fornecedor.setText(pessoa.getTelefones().get(1).getNumero());
            }


            if(pessoa.getTelefones().size() == 3){
                labelTelefone2Fornecedor.setVisible(true);
                ddd2Fornecedor.setVisible(true);
                telefone2Fornecedor.setVisible(true);
                delTel2Fornecedor.setVisible(true);

                ddd2Fornecedor.setText(pessoa.getTelefones().get(1).getDdd());
                telefone2Fornecedor.setText(pessoa.getTelefones().get(1).getNumero());

                labelTelefone3Fornecedor.setVisible(true);
                ddd3Fornecedor.setVisible(true);
                telefone3Fornecedor.setVisible(true);
                delTel3Fornecedor.setVisible(true);

                dddFornecedor.setText(pessoa.getTelefones().get(0).getDdd());
                telefoneFornecedor.setText(pessoa.getTelefones().get(0).getNumero());
                ddd2Fornecedor.setText(pessoa.getTelefones().get(1).getDdd());
                telefone2Fornecedor.setText(pessoa.getTelefones().get(1).getNumero());
                ddd3Fornecedor.setText(pessoa.getTelefones().get(2).getDdd());
                telefone3Fornecedor.setText(pessoa.getTelefones().get(2).getNumero());
            }




            cepFornecedor.setText(pessoa.getEndereco().getCep());
            logradouroFornecedor.setText(pessoa.getEndereco().getLogradouro());
            numeroFornecedor.setText(pessoa.getEndereco().getNumero());
            bairroFornecedor.setText(pessoa.getEndereco().getBairro().getNome());
            cidadeFornecedor.setText(pessoa.getEndereco().getBairro().getCidade().getNome());





            estadosFornecedor.setSelectedItem(pessoa.getEndereco().getBairro().getCidade().getEstado().getUf());
            

            deleteFornecedor.setVisible(true);
            confirmFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png")));

        }
        
        
        if(menu.equals("produtos")){
            DecimalFormat df = new DecimalFormat("0.00");
            
            Long id = Long.parseLong((String) tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 0));
            ProdutoDAO dao = new ProdutoDAO();
            Produto produto = dao.getById(id);

            codigoBarrasProduto.setText(produto.getCodigoBarras());
            quantidadeProduto.setValue(produto.getQuantidade());
            precoProduto.setText(df.format(produto.getValorUnitario()));
            descricaoProduto.setText(produto.getDescricao());
            imagemProduto.setText(null);
            imagemProduto.setIcon(new javax.swing.ImageIcon(produto.getImagem()));
            
            caminhoArquivo = null;
            
            deleteProduto.setVisible(true);
            confirmProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png")));

        }
    }

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnSairMouseClicked

    private void delTel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel3MouseClicked
        Validacao valida = new Validacao();
        if(tabelaUsuario.getSelectedRow() > -1){
            if(valida.validaDdd(ddd3.getText()) && valida.validaTelefone(telefone3.getText())){
                TelefoneDAO dao = new TelefoneDAO();
                PessoaDAO pes = new PessoaDAO();
                Long id = Long.parseLong((String) tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0));

                Pessoa pessoa = pes.getById(id);

                for(int i = 0; i < pessoa.getTelefones().size(); i++){
                    if(pessoa.getTelefones().get(i).getDdd().equals(ddd3.getText())
                        && pessoa.getTelefones().get(i).getNumero().equals(telefone3.getText())){
                        dao.removeById(pessoa.getTelefones().get(i).getIdTelefone());
                    }
                }

                preencheFormulario();
            }
        }
        labelTelefone3.setVisible(false);
        ddd3.setVisible(false);
        ddd3.setText(null);
        telefone3.setVisible(false);
        telefone3.setText(null);
        delTel3.setVisible(false);

    }//GEN-LAST:event_delTel3MouseClicked

    private void delTel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel2MouseClicked
        Validacao valida = new Validacao();
        if(tabelaUsuario.getSelectedRow() > -1){
            if(valida.validaDdd(ddd2.getText()) && valida.validaTelefone(telefone2.getText())){
                TelefoneDAO dao = new TelefoneDAO();
                PessoaDAO pes = new PessoaDAO();
                Long id = Long.parseLong((String) tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0));

                Pessoa pessoa = pes.getById(id);

                for(int i = 0; i < pessoa.getTelefones().size(); i++){
                    if(pessoa.getTelefones().get(i).getDdd().equals(ddd2.getText())
                        && pessoa.getTelefones().get(i).getNumero().equals(telefone2.getText())){
                        dao.removeById(pessoa.getTelefones().get(i).getIdTelefone());
                    }
                }

                preencheFormulario();
            }
        }
        labelTelefone2.setVisible(false);
        ddd2.setVisible(false);
        ddd2.setText(null);
        telefone2.setVisible(false);
        telefone2.setText(null);
        delTel2.setVisible(false);

    }//GEN-LAST:event_delTel2MouseClicked

    private void checkSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkSenhaMouseClicked
        if(checkSenha.isSelected()){
            senha.setEnabled(true);
            senha.setEditable(true);
        }
        else{
            senha.setEnabled(false);
            senha.setEditable(false);
        }
    }//GEN-LAST:event_checkSenhaMouseClicked

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        limpaCampos();
        delete.setVisible(false);
        confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void confirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmMouseClicked

        Validacao valida = new Validacao();

        if(valida.validaNome(nome.getText())){
            if(valida.validaEmail(email.getText())){
                if(valida.validaDataNascimento(dataNascimento.getText())){
                    if(valida.validaCpf(documento.getText())){
                        if(valida.validaDdd(ddd.getText())){
                            if(valida.validaTelefone(telefone.getText())){
                                if(valida.validaCep(cep.getText())){
                                    if(valida.validaEndereco(logradouro.getText())){
                                        if(valida.validaEndereco(bairro.getText())){
                                            if(valida.validaEndereco(cidade.getText())){
                                                if(valida.validaNumero(numero.getText())){
                                                    if(valida.validaUsuario(usuario.getText())){
                                                        if(!checkSenha.isSelected()){
                                                            senha.setText("tempsenha");
                                                        }
                                                        if(valida.validaSenha(senha.getText())){
                                                            if(valida.validaCombo(acessos.getSelectedIndex())){

                                                                List<Telefone> telefones = new ArrayList<>();

                                                                if(telefone.getText().length() == 8){
                                                                    telefones.add(new Telefone(ddd.getText(), telefone.getText(), TipoTelefone.Fixo));
                                                                }
                                                                else{
                                                                    telefones.add(new Telefone(ddd.getText(), telefone.getText(), TipoTelefone.Celular));
                                                                }

                                                                if(ddd2.isVisible() && valida.validaDdd(ddd2.getText())){
                                                                    if(valida.validaTelefone(telefone2.getText())){
                                                                        if(telefone2.getText().length() == 8){
                                                                            telefones.add(new Telefone(ddd2.getText(), telefone2.getText(), TipoTelefone.Fixo));
                                                                        }
                                                                        else{
                                                                            telefones.add(new Telefone(ddd2.getText(), telefone2.getText(), TipoTelefone.Celular));
                                                                        }
                                                                    }
                                                                }

                                                                if(ddd3.isVisible() && valida.validaDdd(ddd3.getText())){
                                                                    if(valida.validaTelefone(telefone3.getText())){
                                                                        if(telefone3.getText().length() == 8){
                                                                            telefones.add(new Telefone(ddd3.getText(), telefone3.getText(), TipoTelefone.Fixo));
                                                                        }
                                                                        else{
                                                                            telefones.add(new Telefone(ddd3.getText(), telefone3.getText(), TipoTelefone.Celular));
                                                                        }
                                                                    }
                                                                }

                                                                Calendar c = Calendar.getInstance();
                                                                int day   = Integer.parseInt(dataNascimento.getText().substring(0, 2));
                                                                int month = Integer.parseInt(dataNascimento.getText().substring(3, 5));
                                                                int year  = Integer.parseInt(dataNascimento.getText().substring(6, 10));
                                                                c.set(year, (month-1), day);
                                                                String tipoAcesso = acessos.getSelectedItem().toString().substring(0, 1);
                                                                if(!delete.isVisible()){
                                                                    /*Persistence With Hibernate - Good Luck For Us!!!!*/

                                                                    EstadoDAO      estDAO = new EstadoDAO();
                                                                    CidadeDAO      cidDAO = new CidadeDAO();
                                                                    BairroDAO      baiDAO = new BairroDAO();
                                                                    EnderecoDAO    endDAO = new EnderecoDAO();
                                                                    PessoaDAO      pesDAO = new PessoaDAO();
                                                                    DocumentoDAO   docDAO = new DocumentoDAO();
                                                                    TelefoneDAO    telDAO = new TelefoneDAO();

                                                                    Cidade objCidade = null;
                                                                    for(int i = 0; i < cidDAO.findAll().size(); i++){
                                                                        if(cidDAO.findAll().get(i).getNome().equals(cidade.getText())){
                                                                            objCidade = cidDAO.findAll().get(i);
                                                                            break;
                                                                        }
                                                                    }

                                                                    if(objCidade == null){
                                                                        objCidade = new Cidade(cidade.getText());
                                                                    }

                                                                    Bairro objBairro = null;
                                                                    for(int i = 0; i < baiDAO.findAll().size(); i++){
                                                                        if(baiDAO.findAll().get(i).getNome().equals(bairro.getText())){
                                                                            objBairro = baiDAO.findAll().get(i);
                                                                            break;
                                                                        }
                                                                    }
                                                                    if(objBairro == null){
                                                                        objBairro = new Bairro(bairro.getText());
                                                                    }

                                                                    Endereco objEndereco       = new Endereco   (logradouro.getText(), numero.getText(), cep.getText());
                                                                    Documento objDocumento     = new Documento  (documento.getText(), TipoDocumento.CPF);

                                                                    /*Hex para Senha*/
                                                                    HashSha hash = new HashSha(senha.getText());
                                                                    String hashSenha = "";
                                                                    try {
                                                                        hashSenha = hash.hashSenha();
                                                                    } catch (NoSuchAlgorithmException ex) {
                                                                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                                                    } catch (UnsupportedEncodingException ex) {
                                                                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }

                                                                    Funcionario objFuncionario = new Funcionario(usuario.getText(), hashSenha, Acesso.valueOf(acessos.getSelectedItem().toString()), nome.getText(), email.getText(), c, Sexo.valueOf(sexo.getSelectedItem().toString()));

                                                                    FuncionarioDAO dao = new FuncionarioDAO();
                                                                    EstadoDAO est = new EstadoDAO();
                                                                    List<Estado> list = est.findByUf(estados.getSelectedItem().toString());

                                                                    for(int i = 0; i < list.size(); i++){
                                                                        list.get(i).addCidade(objCidade);
                                                                        objCidade.addBairro(objBairro);
                                                                        objBairro.addEndereco(objEndereco);
                                                                        objFuncionario.setEndereco(objEndereco);
                                                                        objFuncionario.setTelefone(telefones);
                                                                        objFuncionario.setDocumento(objDocumento);

                                                                        dao.merge(objFuncionario);
                                                                    }

                                                                    acao = "insert";
                                                                    Mensagem msg = new Mensagem();
                                                                    Thread mensagemT = new Thread(msg);
                                                                    mensagemT.start();

                                                                    limpaCampos();

                                                                    carregaDados();
                                                                }
                                                                else{
                                                                    /*Atualiza os Dados*/
                                                                    FuncionarioDAO dao = new FuncionarioDAO();
                                                                    CidadeDAO      cidDAO = new CidadeDAO();
                                                                    BairroDAO      baiDAO = new BairroDAO();

                                                                    Long id = Long.parseLong(
                                                                        (String) tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0)
                                                                    );

                                                                    Funcionario funcionario = dao.getById(id);

                                                                    funcionario.setNome(nome.getText());
                                                                    funcionario.setEmail(email.getText());
                                                                    funcionario.setDataNascimento(c);
                                                                    funcionario.getDocumento().setNumero(documento.getText());

                                                                    if(funcionario.getTelefones().size() == 1){
                                                                        if(ddd2.isVisible() && valida.validaDdd(ddd2.getText())){
                                                                            if(valida.validaTelefone(telefone2.getText())){
                                                                                funcionario.getTelefones().get(0).setDdd(ddd.getText());
                                                                                funcionario.getTelefones().get(0).setNumero(telefone.getText());

                                                                                if(ddd2.isVisible() && valida.validaDdd(ddd2.getText())){
                                                                                    if(valida.validaTelefone(telefone2.getText())){
                                                                                        if(telefone2.getText().length() == 8){
                                                                                            funcionario.addTelefone(new Telefone(ddd2.getText(), telefone2.getText(), TipoTelefone.Fixo));
                                                                                        }
                                                                                        else{
                                                                                            funcionario.addTelefone(new Telefone(ddd2.getText(), telefone2.getText(), TipoTelefone.Celular));
                                                                                        }
                                                                                    }
                                                                                }

                                                                                if(ddd3.isVisible() && valida.validaDdd(ddd3.getText())){
                                                                                    if(valida.validaTelefone(telefone3.getText())){
                                                                                        if(telefone3.getText().length() == 8){
                                                                                            funcionario.addTelefone(new Telefone(ddd3.getText(), telefone3.getText(), TipoTelefone.Fixo));
                                                                                        }
                                                                                        else{
                                                                                            funcionario.addTelefone(new Telefone(ddd3.getText(), telefone3.getText(), TipoTelefone.Celular));
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }

                                                                    if(funcionario.getTelefones().size() == 2){
                                                                        if(ddd2.isVisible() && valida.validaDdd(ddd2.getText())){
                                                                            if(valida.validaTelefone(telefone2.getText())){
                                                                                funcionario.getTelefones().get(0).setDdd(ddd.getText());
                                                                                funcionario.getTelefones().get(0).setNumero(telefone.getText());

                                                                                funcionario.getTelefones().get(1).setDdd(ddd2.getText());
                                                                                funcionario.getTelefones().get(1).setNumero(telefone2.getText());

                                                                                if(ddd3.isVisible() && valida.validaDdd(ddd3.getText())){
                                                                                    if(valida.validaTelefone(telefone3.getText())){
                                                                                        if(telefone3.getText().length() == 8){
                                                                                            funcionario.addTelefone(new Telefone(ddd3.getText(), telefone3.getText(), TipoTelefone.Fixo));
                                                                                        }
                                                                                        else{
                                                                                            funcionario.addTelefone(new Telefone(ddd3.getText(), telefone3.getText(), TipoTelefone.Celular));
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }

                                                                    if(funcionario.getTelefones().size() == 3){
                                                                        if(ddd2.isVisible() && valida.validaDdd(ddd2.getText())){
                                                                            if(valida.validaTelefone(telefone2.getText())){
                                                                                funcionario.getTelefones().get(0).setDdd(ddd.getText());
                                                                                funcionario.getTelefones().get(0).setNumero(telefone.getText());

                                                                                funcionario.getTelefones().get(1).setDdd(ddd2.getText());
                                                                                funcionario.getTelefones().get(1).setNumero(telefone2.getText());

                                                                                funcionario.getTelefones().get(2).setDdd(ddd3.getText());
                                                                                funcionario.getTelefones().get(2).setNumero(telefone3.getText());
                                                                            }
                                                                        }
                                                                    }

                                                                    funcionario.getEndereco().setLogradouro(logradouro.getText());
                                                                    funcionario.getEndereco().setNumero(numero.getText());
                                                                    funcionario.getEndereco().setCep(cep.getText());

                                                                    boolean existeBairro = false;
                                                                    Bairro exsBairro = null;
                                                                    for(int i = 0; i < baiDAO.findAll().size(); i++){

                                                                        if(baiDAO.findAll().get(i).getNome().equals(bairro.getText())){
                                                                            existeBairro = true;
                                                                            exsBairro = baiDAO.findAll().get(i);
                                                                            break;
                                                                        }
                                                                    }

                                                                    if(existeBairro){
                                                                        exsBairro.addEndereco(funcionario.getEndereco());
                                                                    }else{
                                                                        new Bairro(bairro.getText()).addEndereco(funcionario.getEndereco());
                                                                    }

                                                                    boolean existeCidade = false;
                                                                    Cidade exsCidade = null;
                                                                    for(int i = 0; i < cidDAO.findAll().size(); i++){

                                                                        if(cidDAO.findAll().get(i).getNome().equals(cidade.getText())){
                                                                            existeCidade = true;
                                                                            exsCidade = cidDAO.findAll().get(i);
                                                                            break;
                                                                        }
                                                                    }

                                                                    if(existeCidade){
                                                                        exsCidade.addBairro(funcionario.getEndereco().getBairro());
                                                                    }else{
                                                                        new Cidade(cidade.getText()).addBairro(funcionario.getEndereco().getBairro());

                                                                    }

                                                                    EstadoDAO      estDao = new EstadoDAO();
                                                                    Estado atualEstado = null;
                                                                    List<Estado> list = estDao.findByUf(estados.getSelectedItem().toString());
                                                                    for(int i = 0; i < list.size(); i++){
                                                                        atualEstado = list.get(i);
                                                                    }

                                                                    atualEstado.addCidade(funcionario.getEndereco().getBairro().getCidade());

                                                                    //funcionario.getEndereco().getBairro().setNome(bairro.getText());
                                                                    //funcionario.getEndereco().getBairro().getCidade().setNome(cidade.getText());
                                                                    funcionario.setDataNascimento(c);
                                                                    funcionario.setUsuario(usuario.getText());

                                                                    if(senha.isEditable()){
                                                                        /*Hex para Senha*/
                                                                        HashSha hash = new HashSha(senha.getText());
                                                                        String hashSenha = "";

                                                                        try {
                                                                            hashSenha = hash.hashSenha();
                                                                            funcionario.setSenha(hashSenha);
                                                                        } catch (NoSuchAlgorithmException ex) {
                                                                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                                                        } catch (UnsupportedEncodingException ex) {
                                                                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                                                        }
                                                                    }

                                                                    senha.setText("tempsenha");

                                                                    funcionario.setAcesso(Acesso.valueOf(acessos.getSelectedItem().toString()));
                                                                    funcionario.setSexo(Sexo.valueOf(sexo.getSelectedItem().toString()));

                                                                    dao.merge(funcionario);

                                                                    acao = "edit";
                                                                    Mensagem msg = new Mensagem();
                                                                    Thread mensagemT = new Thread(msg);
                                                                    mensagemT.start();

                                                                    limpaCampos();
                                                                    carregaDados();
                                                                }
                                                            }
                                                            else{
                                                                JOptionPane.showMessageDialog(null, "Selcione o tipo de acesso");
                                                                acessos.requestFocus();
                                                            }
                                                        }
                                                        else{
                                                            JOptionPane.showMessageDialog(null, "Senha Requerida");
                                                            senha.requestFocus();
                                                        }
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null, "Usuário Requerido");
                                                        usuario.requestFocus();
                                                    }
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Número Requerido");
                                                    numero.requestFocus();
                                                }
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Cidade Requerido");
                                                cidade.requestFocus();
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Bairro Requerido");
                                            bairro.requestFocus();
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Logradouro Requerido");
                                        logradouro.requestFocus();
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Cep Requerido");
                                    cep.requestFocus();
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Telefone Requerido");
                                telefone.requestFocus();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "DDD Requerido");
                            ddd.requestFocus();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "CPF Requerido");
                        documento.requestFocus();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Data de Nascimento Requerido");
                    dataNascimento.requestFocus();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "E-mail Requerido");
                email.requestFocus();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Nome Requerido");
            nome.requestFocus();
        }
    }//GEN-LAST:event_confirmMouseClicked

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        FuncionarioDAO dao = new FuncionarioDAO();
        Long id = Long.parseLong(
            (String) tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0)
        );

        dao.removeById(id);
        acao = "delete";
        Mensagem msg = new Mensagem();
        Thread mensagem = new Thread(msg);
        mensagem.start();
        limpaCampos();
        carregaDados();
    }//GEN-LAST:event_deleteMouseClicked

    private void addTelefoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTelefoneMouseClicked

        if(labelTelefone2.isVisible()){
            labelTelefone3.setVisible(true);
            ddd3.setVisible(true);
            telefone3.setVisible(true);
            delTel3.setVisible(true);
        }
        else{
            labelTelefone2.setVisible(true);
            ddd2.setVisible(true);
            telefone2.setVisible(true);
            delTel2.setVisible(true);
        }
    }//GEN-LAST:event_addTelefoneMouseClicked

    private void nomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeMouseClicked
        nome.setBackground(Color.white);
    }//GEN-LAST:event_nomeMouseClicked

    private void tabelaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaUsuarioMouseClicked
        preencheFormulario();
    }//GEN-LAST:event_tabelaUsuarioMouseClicked

    private void addTelClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTelClienteMouseClicked
        if(labelTelefone2Cliente.isVisible()){
            labelTelefone3Cliente.setVisible(true);
            ddd3Cliente.setVisible(true);
            telefone3Cliente.setVisible(true);
            delTel3Cliente.setVisible(true);
        }
        else{
            labelTelefone2Cliente.setVisible(true);
            ddd2Cliente.setVisible(true);
            telefone2Cliente.setVisible(true);
            delTel2Cliente.setVisible(true);
        }
    }//GEN-LAST:event_addTelClienteMouseClicked

    private void delTel2ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel2ClienteMouseClicked
        Validacao valida = new Validacao();
        if(tabelaCliente.getSelectedRow() > -1){
            if(valida.validaDdd(ddd2Cliente.getText()) && valida.validaTelefone(telefone2Cliente.getText())){
                TelefoneDAO dao = new TelefoneDAO();
                PessoaDAO pes = new PessoaDAO();
                Long id = Long.parseLong((String) tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0));

                Pessoa pessoa = pes.getById(id);

                for(int i = 0; i < pessoa.getTelefones().size(); i++){
                    if(pessoa.getTelefones().get(i).getDdd().equals(ddd2Cliente.getText())
                        && pessoa.getTelefones().get(i).getNumero().equals(telefone2Cliente.getText())){
                        dao.removeById(pessoa.getTelefones().get(i).getIdTelefone());
                    }
                }

                preencheFormulario();
            }
        }
        labelTelefone2Cliente.setVisible(false);
        ddd2Cliente.setVisible(false);
        ddd2Cliente.setText(null);
        telefone2Cliente.setVisible(false);
        telefone2Cliente.setText(null);
        delTel2Cliente.setVisible(false);
    }//GEN-LAST:event_delTel2ClienteMouseClicked

    private void delTel3ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel3ClienteMouseClicked
        Validacao valida = new Validacao();
        if(tabelaCliente.getSelectedRow() > -1){
            if(valida.validaDdd(ddd3Cliente.getText()) && valida.validaTelefone(telefone3Cliente.getText())){
                TelefoneDAO dao = new TelefoneDAO();
                PessoaDAO pes = new PessoaDAO();
                Long id = Long.parseLong((String) tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0));

                Pessoa pessoa = pes.getById(id);

                for(int i = 0; i < pessoa.getTelefones().size(); i++){
                    if(pessoa.getTelefones().get(i).getDdd().equals(ddd3Cliente.getText())
                        && pessoa.getTelefones().get(i).getNumero().equals(telefone3Cliente.getText())){
                        dao.removeById(pessoa.getTelefones().get(i).getIdTelefone());
                    }
                }

                preencheFormulario();
            }
        }
        labelTelefone3Cliente.setVisible(false);
        ddd3Cliente.setVisible(false);
        telefone3Cliente.setVisible(false);
        delTel3Cliente.setVisible(false);
        ddd3Cliente.setText(null);
        telefone3Cliente.setText(null);
       
    }//GEN-LAST:event_delTel3ClienteMouseClicked

    private void refreshClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshClienteMouseClicked
        limpaCampos();
        deleteCliente.setVisible(false);
        confirmCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
    }//GEN-LAST:event_refreshClienteMouseClicked

    private void confirmClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmClienteMouseClicked
        Validacao valida = new Validacao();
        
        if(valida.validaNome(nomeCliente.getText())){
            if(valida.validaEmail(emailCliente.getText())){
                if(valida.validaDataNascimento(dataNascimentoCliente.getText())){
                    if(valida.validaCpf(documentoCliente.getText())){
                        if(valida.validaDdd(dddCliente.getText())){
                            if(valida.validaTelefone(telefoneCliente.getText())){
                                if(valida.validaCep(cepCliente.getText())){
                                    if(valida.validaEndereco(logradouroCliente.getText())){
                                        if(valida.validaNumero(numeroCliente.getText())){
                                            if(valida.validaEndereco(bairroCliente.getText())){
                                                if(valida.validaEndereco(cidadeCliente.getText())){
                                                    JOptionPane.showMessageDialog(null, "ALL DONE!!!");
                                                    /*Persist Pessoa*/
                                                    
                                                    
                                                    List<Telefone> telefones = new ArrayList<>();

                                                    if(telefoneCliente.getText().length() == 8){
                                                        telefones.add(new Telefone(dddCliente.getText(), telefoneCliente.getText(), TipoTelefone.Fixo));
                                                    }
                                                    else{
                                                        telefones.add(new Telefone(dddCliente.getText(), telefoneCliente.getText(), TipoTelefone.Celular));
                                                    }

                                                    if(ddd2Cliente.isVisible() && valida.validaDdd(ddd2Cliente.getText())){
                                                        if(valida.validaTelefone(telefone2Cliente.getText())){
                                                            if(telefone2Cliente.getText().length() == 8){
                                                                telefones.add(new Telefone(ddd2Cliente.getText(), telefone2Cliente.getText(), TipoTelefone.Fixo));
                                                            }
                                                            else{
                                                                telefones.add(new Telefone(ddd2Cliente.getText(), telefone2Cliente.getText(), TipoTelefone.Celular));
                                                            }
                                                        }
                                                    }

                                                    if(ddd3Cliente.isVisible() && valida.validaDdd(ddd3Cliente.getText())){
                                                        if(valida.validaTelefone(telefone3Cliente.getText())){
                                                            if(telefone3Cliente.getText().length() == 8){
                                                                telefones.add(new Telefone(ddd3Cliente.getText(), telefone3Cliente.getText(), TipoTelefone.Fixo));
                                                            }
                                                            else{
                                                                telefones.add(new Telefone(ddd3Cliente.getText(), telefone3Cliente.getText(), TipoTelefone.Celular));
                                                            }
                                                        }
                                                    }

                                                    Calendar c = Calendar.getInstance();
                                                    int day   = Integer.parseInt(dataNascimentoCliente.getText().substring(0, 2));
                                                    int month = Integer.parseInt(dataNascimentoCliente.getText().substring(3, 5));
                                                    int year  = Integer.parseInt(dataNascimentoCliente.getText().substring(6, 10));
                                                    c.set(year, (month-1), day);
                                                    
                                                    if(!deleteCliente.isVisible()){
                                                        /*Persistence With Hibernate - Good Luck For Us!!!!*/

                                                        EstadoDAO      estDAO = new EstadoDAO();
                                                        CidadeDAO      cidDAO = new CidadeDAO();
                                                        BairroDAO      baiDAO = new BairroDAO();
                                                        EnderecoDAO    endDAO = new EnderecoDAO();
                                                        PessoaDAO      pesDAO = new PessoaDAO();
                                                        DocumentoDAO   docDAO = new DocumentoDAO();
                                                        TelefoneDAO    telDAO = new TelefoneDAO();

                                                        Cidade objCidade = null;
                                                        for(int i = 0; i < cidDAO.findAll().size(); i++){
                                                            if(cidDAO.findAll().get(i).getNome().equals(cidadeCliente.getText())){
                                                                objCidade = cidDAO.findAll().get(i);
                                                                break;
                                                            }
                                                        }

                                                        if(objCidade == null){
                                                            objCidade = new Cidade(cidadeCliente.getText());
                                                        }

                                                        Bairro objBairro = null;
                                                        for(int i = 0; i < baiDAO.findAll().size(); i++){
                                                            if(baiDAO.findAll().get(i).getNome().equals(bairroCliente.getText())){
                                                                objBairro = baiDAO.findAll().get(i);
                                                                break;
                                                            }
                                                        }
                                                        if(objBairro == null){
                                                            objBairro = new Bairro(bairroCliente.getText());
                                                        }

                                                        Endereco objEndereco       = new Endereco   (logradouroCliente.getText(), numeroCliente.getText(), cepCliente.getText());
                                                        Documento objDocumento     = new Documento  (documentoCliente.getText(), TipoDocumento.CPF);

                                                        Pessoa objPessoa = new Pessoa(nomeCliente.getText(), emailCliente.getText(), c, Sexo.valueOf(sexoCliente.getSelectedItem().toString())); 
                                                        EstadoDAO est = new EstadoDAO();
                                                        List<Estado> list = est.findByUf(estadosCliente.getSelectedItem().toString());

                                                        for(int i = 0; i < list.size(); i++){
                                                            list.get(i).addCidade(objCidade);
                                                            objCidade.addBairro(objBairro);
                                                            objBairro.addEndereco(objEndereco);
                                                            objPessoa.setEndereco(objEndereco);
                                                            objPessoa.setTelefone(telefones);
                                                            objPessoa.setDocumento(objDocumento);

                                                            pesDAO.merge(objPessoa);
                                                        }

                                                        acao = "insert";
                                                        Mensagem msg = new Mensagem();
                                                        Thread mensagemT = new Thread(msg);
                                                        mensagemT.start();

                                                        limpaCampos();

                                                        carregaDados();
                                                    
                                                    }
                                                    else{
                                                        /*Atualiza os Dados*/
                                                        PessoaDAO dao = new PessoaDAO();
                                                        CidadeDAO      cidDAO = new CidadeDAO();
                                                        BairroDAO      baiDAO = new BairroDAO();

                                                        Long id = Long.parseLong(
                                                            (String) tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0)
                                                        );

                                                        Pessoa pessoa = dao.getById(id);

                                                        pessoa.setNome(nomeCliente.getText());
                                                        pessoa.setEmail(emailCliente.getText());
                                                        pessoa.setDataNascimento(c);
                                                        pessoa.getDocumento().setNumero(documentoCliente.getText());

                                                        if(pessoa.getTelefones().size() == 1){
                                                            if(ddd2Cliente.isVisible() && valida.validaDdd(ddd2Cliente.getText())){
                                                                if(valida.validaTelefone(telefone2Cliente.getText())){
                                                                    pessoa.getTelefones().get(0).setDdd(dddCliente.getText());
                                                                    pessoa.getTelefones().get(0).setNumero(telefoneCliente.getText());

                                                                    if(ddd2Cliente.isVisible() && valida.validaDdd(ddd2Cliente.getText())){
                                                                        if(valida.validaTelefone(telefone2Cliente.getText())){
                                                                            if(telefone2Cliente.getText().length() == 8){
                                                                                pessoa.addTelefone(new Telefone(ddd2Cliente.getText(), telefone2Cliente.getText(), TipoTelefone.Fixo));
                                                                            }
                                                                            else{
                                                                                pessoa.addTelefone(new Telefone(ddd2Cliente.getText(), telefone2Cliente.getText(), TipoTelefone.Celular));
                                                                            }
                                                                        }
                                                                    }

                                                                    if(ddd3Cliente.isVisible() && valida.validaDdd(ddd3Cliente.getText())){
                                                                        if(valida.validaTelefone(telefone3Cliente.getText())){
                                                                            if(telefone3Cliente.getText().length() == 8){
                                                                                pessoa.addTelefone(new Telefone(ddd3Cliente.getText(), telefone3Cliente.getText(), TipoTelefone.Fixo));
                                                                            }
                                                                            else{
                                                                                pessoa.addTelefone(new Telefone(ddd3Cliente.getText(), telefone3Cliente.getText(), TipoTelefone.Celular));
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        if(pessoa.getTelefones().size() == 2){
                                                            if(ddd2Cliente.isVisible() && valida.validaDdd(ddd2Cliente.getText())){
                                                                if(valida.validaTelefone(telefone2Cliente.getText())){
                                                                    pessoa.getTelefones().get(0).setDdd(dddCliente.getText());
                                                                    pessoa.getTelefones().get(0).setNumero(telefoneCliente.getText());

                                                                    pessoa.getTelefones().get(1).setDdd(ddd2Cliente.getText());
                                                                    pessoa.getTelefones().get(1).setNumero(telefone2Cliente.getText());

                                                                    if(ddd3Cliente.isVisible() && valida.validaDdd(ddd3Cliente.getText())){
                                                                        if(valida.validaTelefone(telefone3Cliente.getText())){
                                                                            if(telefone3Cliente.getText().length() == 8){
                                                                                pessoa.addTelefone(new Telefone(ddd3Cliente.getText(), telefone3Cliente.getText(), TipoTelefone.Fixo));
                                                                            }
                                                                            else{
                                                                                pessoa.addTelefone(new Telefone(ddd3Cliente.getText(), telefone3Cliente.getText(), TipoTelefone.Celular));
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        if(pessoa.getTelefones().size() == 3){
                                                            if(ddd2Cliente.isVisible() && valida.validaDdd(ddd2Cliente.getText())){
                                                                if(valida.validaTelefone(telefone2Cliente.getText())){
                                                                    pessoa.getTelefones().get(0).setDdd(dddCliente.getText());
                                                                    pessoa.getTelefones().get(0).setNumero(telefoneCliente.getText());

                                                                    pessoa.getTelefones().get(1).setDdd(ddd2Cliente.getText());
                                                                    pessoa.getTelefones().get(1).setNumero(telefone2Cliente.getText());

                                                                    pessoa.getTelefones().get(2).setDdd(ddd3Cliente.getText());
                                                                    pessoa.getTelefones().get(2).setNumero(telefone3Cliente.getText());
                                                                }
                                                            }
                                                        }

                                                        pessoa.getEndereco().setLogradouro(logradouroCliente.getText());
                                                        pessoa.getEndereco().setNumero(numeroCliente.getText());
                                                        pessoa.getEndereco().setCep(cepCliente.getText());

                                                        boolean existeBairro = false;
                                                        Bairro exsBairro = null;
                                                        for(int i = 0; i < baiDAO.findAll().size(); i++){

                                                            if(baiDAO.findAll().get(i).getNome().equals(bairroCliente.getText())){
                                                                existeBairro = true;
                                                                exsBairro = baiDAO.findAll().get(i);
                                                                break;
                                                            }
                                                        }

                                                        if(existeBairro){
                                                            exsBairro.addEndereco(pessoa.getEndereco());
                                                        }else{
                                                            new Bairro(bairroCliente.getText()).addEndereco(pessoa.getEndereco());
                                                        }

                                                        boolean existeCidade = false;
                                                        Cidade exsCidade = null;
                                                        for(int i = 0; i < cidDAO.findAll().size(); i++){

                                                            if(cidDAO.findAll().get(i).getNome().equals(cidadeCliente.getText())){
                                                                existeCidade = true;
                                                                exsCidade = cidDAO.findAll().get(i);
                                                                break;
                                                            }
                                                        }

                                                        if(existeCidade){
                                                            exsCidade.addBairro(pessoa.getEndereco().getBairro());
                                                        }else{
                                                            new Cidade(cidadeCliente.getText()).addBairro(pessoa.getEndereco().getBairro());

                                                        }

                                                        EstadoDAO estDao = new EstadoDAO();
                                                        Estado atualEstado = null;
                                                        List<Estado> list = estDao.findByUf(estadosCliente.getSelectedItem().toString());
                                                        for(int i = 0; i < list.size(); i++){
                                                            atualEstado = list.get(i);
                                                        }

                                                        atualEstado.addCidade(pessoa.getEndereco().getBairro().getCidade());

                                                        
                                                        pessoa.setDataNascimento(c);
                                                        pessoa.setSexo(Sexo.valueOf(sexoCliente.getSelectedItem().toString()));

                                                        dao.merge(pessoa);

                                                        acao = "edit";
                                                        Mensagem msg = new Mensagem();
                                                        Thread mensagemT = new Thread(msg);
                                                        mensagemT.start();

                                                        limpaCampos();
                                                        carregaDados();
                                                    }
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null,"Cidade requerida!");
                                                    cidadeCliente.requestFocus();
                                                }
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null,"Bairro requerido!");
                                                bairroCliente.requestFocus();
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null,"Número requerido!");
                                            numeroCliente.requestFocus();
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null,"Logradouro requerido!");
                                        logradouroCliente.requestFocus();
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"CEP requerido!");
                                    cepCliente.requestFocus();
                                }              
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Telefone requerido!");
                                telefoneCliente.requestFocus();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"DDD requerido!");
                            dddCliente.requestFocus();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"CPF requerido!");
                        documentoCliente.requestFocus();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Data de Nascimento Requerida!");
                    dataNascimentoCliente.requestFocus();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"E-Mail requerido!");
                emailCliente.requestFocus();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Nome requerido!");
            nomeCliente.requestFocus();
        }
    }//GEN-LAST:event_confirmClienteMouseClicked

    private void tabelaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteMouseClicked
        preencheFormulario();
    }//GEN-LAST:event_tabelaClienteMouseClicked

    private void deleteClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteClienteMouseClicked
        PessoaDAO dao = new PessoaDAO();
        Long id = Long.parseLong(
            (String) tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0)
        );

        dao.removeById(id);
        acao = "delete";
        Mensagem msg = new Mensagem();
        Thread mensagem = new Thread(msg);
        mensagem.start();
        limpaCampos();
        carregaDados();
    }//GEN-LAST:event_deleteClienteMouseClicked

    private void addTelefoneFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTelefoneFornecedorMouseClicked
        if(labelTelefone2Fornecedor.isVisible()){
            labelTelefone3Fornecedor.setVisible(true);
            ddd3Fornecedor.setVisible(true);
            telefone3Fornecedor.setVisible(true);
            delTel3Fornecedor.setVisible(true);
        }
        else{
            labelTelefone2Fornecedor.setVisible(true);
            ddd2Fornecedor.setVisible(true);
            telefone2Fornecedor.setVisible(true);
            delTel2Fornecedor.setVisible(true);
        }
    }//GEN-LAST:event_addTelefoneFornecedorMouseClicked

    private void delTel2FornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel2FornecedorMouseClicked
        Validacao valida = new Validacao();
        if(tabelaFornecedor.getSelectedRow() > -1){
            if(valida.validaDdd(ddd2Fornecedor.getText()) && valida.validaTelefone(telefone2Fornecedor.getText())){
                TelefoneDAO dao = new TelefoneDAO();
                PessoaDAO pes = new PessoaDAO();
                Long id = Long.parseLong((String) tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0));

                Pessoa pessoa = pes.getById(id);

                for(int i = 0; i < pessoa.getTelefones().size(); i++){
                    if(pessoa.getTelefones().get(i).getDdd().equals(ddd2Fornecedor.getText())
                        && pessoa.getTelefones().get(i).getNumero().equals(telefone2Fornecedor.getText())){
                        dao.removeById(pessoa.getTelefones().get(i).getIdTelefone());
                    }
                }

                preencheFormulario();
            }
        }
        labelTelefone2Fornecedor.setVisible(false);
        ddd2Fornecedor.setVisible(false);
        ddd2Fornecedor.setText(null);
        telefone2Fornecedor.setVisible(false);
        telefone2Fornecedor.setText(null);
        delTel2Fornecedor.setVisible(false);
    }//GEN-LAST:event_delTel2FornecedorMouseClicked

    private void delTel3FornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel3FornecedorMouseClicked
       Validacao valida = new Validacao();
        if(tabelaFornecedor.getSelectedRow() > -1){
            if(valida.validaDdd(ddd3Fornecedor.getText()) && valida.validaTelefone(telefone3Fornecedor.getText())){
                TelefoneDAO dao = new TelefoneDAO();
                PessoaDAO pes = new PessoaDAO();
                Long id = Long.parseLong((String) tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0));

                Pessoa pessoa = pes.getById(id);

                for(int i = 0; i < pessoa.getTelefones().size(); i++){
                    if(pessoa.getTelefones().get(i).getDdd().equals(ddd3Fornecedor.getText())
                        && pessoa.getTelefones().get(i).getNumero().equals(telefone3Fornecedor.getText())){
                        dao.removeById(pessoa.getTelefones().get(i).getIdTelefone());
                    }
                }

                preencheFormulario();
            }
        }
        labelTelefone3Fornecedor.setVisible(false);
        ddd3Fornecedor.setVisible(false);
        ddd3Fornecedor.setText(null);
        telefone3Fornecedor.setVisible(false);
        telefone3Fornecedor.setText(null);
        delTel3Fornecedor.setVisible(false);
    }//GEN-LAST:event_delTel3FornecedorMouseClicked

    private void refreshFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshFornecedorMouseClicked
        limpaCampos();
        deleteFornecedor.setVisible(false);
        confirmFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
    }//GEN-LAST:event_refreshFornecedorMouseClicked

    private void confirmFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmFornecedorMouseClicked
        Validacao valida = new Validacao();
        
        if(valida.validaNome(nomeFornecedor.getText())){
            if(valida.validaEmail(emailFornecedor.getText())){
                if(valida.validaCnpj(documentoFornecedor.getText())){
                    if(valida.validaDdd(dddFornecedor.getText())){
                        if(valida.validaTelefone(telefoneFornecedor.getText())){
                            if(valida.validaCep(cepFornecedor.getText())){
                                if(valida.validaEndereco(logradouroFornecedor.getText())){
                                    if(valida.validaNumero(numeroFornecedor.getText())){
                                        if(valida.validaEndereco(bairroFornecedor.getText())){
                                            if(valida.validaEndereco(cidadeFornecedor.getText())){
                                                
                                                /*Persist Pessoa*/


                                                List<Telefone> telefones = new ArrayList<>();

                                                if(telefoneFornecedor.getText().length() == 8){
                                                    telefones.add(new Telefone(dddFornecedor.getText(), telefoneFornecedor.getText(), TipoTelefone.Fixo));
                                                }
                                                else{
                                                    telefones.add(new Telefone(dddFornecedor.getText(), telefoneFornecedor.getText(), TipoTelefone.Celular));
                                                }

                                                if(ddd2Fornecedor.isVisible() && valida.validaDdd(ddd2Fornecedor.getText())){
                                                    if(valida.validaTelefone(telefone2Fornecedor.getText())){
                                                        if(telefone2Fornecedor.getText().length() == 8){
                                                            telefones.add(new Telefone(ddd2Fornecedor.getText(), telefone2Fornecedor.getText(), TipoTelefone.Fixo));
                                                        }
                                                        else{
                                                            telefones.add(new Telefone(ddd2Fornecedor.getText(), telefone2Fornecedor.getText(), TipoTelefone.Celular));
                                                        }
                                                    }
                                                }

                                                if(ddd3Fornecedor.isVisible() && valida.validaDdd(ddd3Fornecedor.getText())){
                                                    if(valida.validaTelefone(telefone3Fornecedor.getText())){
                                                        if(telefone3Fornecedor.getText().length() == 8){
                                                            telefones.add(new Telefone(ddd3Fornecedor.getText(), telefone3Fornecedor.getText(), TipoTelefone.Fixo));
                                                        }
                                                        else{
                                                            telefones.add(new Telefone(ddd3Fornecedor.getText(), telefone3Fornecedor.getText(), TipoTelefone.Celular));
                                                        }
                                                    }
                                                }

                                                Calendar c = Calendar.getInstance();
                                                c.set(0, 0, 0);

                                                if(!deleteCliente.isVisible()){
                                                    /*Persistence With Hibernate - Good Luck For Us!!!!*/

                                                    EstadoDAO      estDAO = new EstadoDAO();
                                                    CidadeDAO      cidDAO = new CidadeDAO();
                                                    BairroDAO      baiDAO = new BairroDAO();
                                                    EnderecoDAO    endDAO = new EnderecoDAO();
                                                    PessoaDAO      pesDAO = new PessoaDAO();
                                                    DocumentoDAO   docDAO = new DocumentoDAO();
                                                    TelefoneDAO    telDAO = new TelefoneDAO();

                                                    Cidade objCidade = null;
                                                    for(int i = 0; i < cidDAO.findAll().size(); i++){
                                                        if(cidDAO.findAll().get(i).getNome().equals(cidadeFornecedor.getText())){
                                                            objCidade = cidDAO.findAll().get(i);
                                                            break;
                                                        }
                                                    }

                                                    if(objCidade == null){
                                                        objCidade = new Cidade(cidadeFornecedor.getText());
                                                    }

                                                    Bairro objBairro = null;
                                                    for(int i = 0; i < baiDAO.findAll().size(); i++){
                                                        if(baiDAO.findAll().get(i).getNome().equals(bairroFornecedor.getText())){
                                                            objBairro = baiDAO.findAll().get(i);
                                                            break;
                                                        }
                                                    }
                                                    if(objBairro == null){
                                                        objBairro = new Bairro(bairroFornecedor.getText());
                                                    }

                                                    Endereco objEndereco = new Endereco   (logradouroFornecedor.getText(), numeroFornecedor.getText(), cepFornecedor.getText());
                                                    Documento objDocumento = new Documento  (documentoFornecedor.getText(), TipoDocumento.CNPJ);

                                                    Pessoa objPessoa = new Pessoa(nomeFornecedor.getText(), emailFornecedor.getText(), c, Sexo.Não_Definido); 
                                                    EstadoDAO est = new EstadoDAO();
                                                    List<Estado> list = est.findByUf(estadosFornecedor.getSelectedItem().toString());

                                                    for(int i = 0; i < list.size(); i++){
                                                        list.get(i).addCidade(objCidade);
                                                        objCidade.addBairro(objBairro);
                                                        objBairro.addEndereco(objEndereco);
                                                        objPessoa.setEndereco(objEndereco);
                                                        objPessoa.setTelefone(telefones);
                                                        objPessoa.setDocumento(objDocumento);

                                                        pesDAO.merge(objPessoa);
                                                    }

                                                    acao = "insert";
                                                    Mensagem msg = new Mensagem();
                                                    Thread mensagemT = new Thread(msg);
                                                    mensagemT.start();

                                                    limpaCampos();

                                                    carregaDados();

                                                }
                                                else{
                                                    /*Atualiza os Dados*/
                                                    PessoaDAO dao = new PessoaDAO();
                                                    CidadeDAO      cidDAO = new CidadeDAO();
                                                    BairroDAO      baiDAO = new BairroDAO();

                                                    Long id = Long.parseLong(
                                                        (String) tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0)
                                                    );

                                                    Pessoa pessoa = dao.getById(id);

                                                    pessoa.setNome(nomeFornecedor.getText());
                                                    pessoa.setEmail(emailFornecedor.getText());
                                                    pessoa.setDataNascimento(c);
                                                    pessoa.getDocumento().setNumero(documentoFornecedor.getText());

                                                    if(pessoa.getTelefones().size() == 1){
                                                        if(ddd2Fornecedor.isVisible() && valida.validaDdd(ddd2Fornecedor.getText())){
                                                            if(valida.validaTelefone(telefone2Fornecedor.getText())){
                                                                pessoa.getTelefones().get(0).setDdd(dddFornecedor.getText());
                                                                pessoa.getTelefones().get(0).setNumero(telefoneFornecedor.getText());

                                                                if(ddd2Fornecedor.isVisible() && valida.validaDdd(ddd2Fornecedor.getText())){
                                                                    if(valida.validaTelefone(telefone2Fornecedor.getText())){
                                                                        if(telefone2Fornecedor.getText().length() == 8){
                                                                            pessoa.addTelefone(new Telefone(ddd2Fornecedor.getText(), telefone2Fornecedor.getText(), TipoTelefone.Fixo));
                                                                        }
                                                                        else{
                                                                            pessoa.addTelefone(new Telefone(ddd2Fornecedor.getText(), telefone2Fornecedor.getText(), TipoTelefone.Celular));
                                                                        }
                                                                    }
                                                                }

                                                                if(ddd3Fornecedor.isVisible() && valida.validaDdd(ddd3Fornecedor.getText())){
                                                                    if(valida.validaTelefone(telefone3Fornecedor.getText())){
                                                                        if(telefone3Fornecedor.getText().length() == 8){
                                                                            pessoa.addTelefone(new Telefone(ddd3Fornecedor.getText(), telefone3Fornecedor.getText(), TipoTelefone.Fixo));
                                                                        }
                                                                        else{
                                                                            pessoa.addTelefone(new Telefone(ddd3Fornecedor.getText(), telefone3Fornecedor.getText(), TipoTelefone.Celular));
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }

                                                    if(pessoa.getTelefones().size() == 2){
                                                        if(ddd2Fornecedor.isVisible() && valida.validaDdd(ddd2Fornecedor.getText())){
                                                            if(valida.validaTelefone(telefone2Fornecedor.getText())){
                                                                pessoa.getTelefones().get(0).setDdd(dddFornecedor.getText());
                                                                pessoa.getTelefones().get(0).setNumero(telefoneFornecedor.getText());

                                                                pessoa.getTelefones().get(1).setDdd(ddd2Fornecedor.getText());
                                                                pessoa.getTelefones().get(1).setNumero(telefone2Fornecedor.getText());

                                                                if(ddd3Fornecedor.isVisible() && valida.validaDdd(ddd3Fornecedor.getText())){
                                                                    if(valida.validaTelefone(telefone3Fornecedor.getText())){
                                                                        if(telefone3Fornecedor.getText().length() == 8){
                                                                            pessoa.addTelefone(new Telefone(ddd3Fornecedor.getText(), telefone3Fornecedor.getText(), TipoTelefone.Fixo));
                                                                        }
                                                                        else{
                                                                            pessoa.addTelefone(new Telefone(ddd3Fornecedor.getText(), telefone3Fornecedor.getText(), TipoTelefone.Celular));
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }

                                                    if(pessoa.getTelefones().size() == 3){
                                                        if(ddd2Fornecedor.isVisible() && valida.validaDdd(ddd2Fornecedor.getText())){
                                                            if(valida.validaTelefone(telefone2Fornecedor.getText())){
                                                                pessoa.getTelefones().get(0).setDdd(dddFornecedor.getText());
                                                                pessoa.getTelefones().get(0).setNumero(telefoneFornecedor.getText());

                                                                pessoa.getTelefones().get(1).setDdd(ddd2Fornecedor.getText());
                                                                pessoa.getTelefones().get(1).setNumero(telefone2Fornecedor.getText());

                                                                pessoa.getTelefones().get(2).setDdd(ddd3Fornecedor.getText());
                                                                pessoa.getTelefones().get(2).setNumero(telefone3Fornecedor.getText());
                                                            }
                                                        }
                                                    }

                                                    pessoa.getEndereco().setLogradouro(logradouroFornecedor.getText());
                                                    pessoa.getEndereco().setNumero(numeroFornecedor.getText());
                                                    pessoa.getEndereco().setCep(cepFornecedor.getText());

                                                    boolean existeBairro = false;
                                                    Bairro exsBairro = null;
                                                    for(int i = 0; i < baiDAO.findAll().size(); i++){

                                                        if(baiDAO.findAll().get(i).getNome().equals(bairroFornecedor.getText())){
                                                            existeBairro = true;
                                                            exsBairro = baiDAO.findAll().get(i);
                                                            break;
                                                        }
                                                    }

                                                    if(existeBairro){
                                                        exsBairro.addEndereco(pessoa.getEndereco());
                                                    }else{
                                                        new Bairro(bairroFornecedor.getText()).addEndereco(pessoa.getEndereco());
                                                    }

                                                    boolean existeCidade = false;
                                                    Cidade exsCidade = null;
                                                    for(int i = 0; i < cidDAO.findAll().size(); i++){

                                                        if(cidDAO.findAll().get(i).getNome().equals(cidadeFornecedor.getText())){
                                                            existeCidade = true;
                                                            exsCidade = cidDAO.findAll().get(i);
                                                            break;
                                                        }
                                                    }

                                                    if(existeCidade){
                                                        exsCidade.addBairro(pessoa.getEndereco().getBairro());
                                                    }else{
                                                        new Cidade(cidadeFornecedor.getText()).addBairro(pessoa.getEndereco().getBairro());

                                                    }

                                                    EstadoDAO estDao = new EstadoDAO();
                                                    Estado atualEstado = null;
                                                    List<Estado> list = estDao.findByUf(estadosFornecedor.getSelectedItem().toString());
                                                    for(int i = 0; i < list.size(); i++){
                                                        atualEstado = list.get(i);
                                                    }

                                                    atualEstado.addCidade(pessoa.getEndereco().getBairro().getCidade());


                                                    pessoa.setDataNascimento(c);

                                                    dao.merge(pessoa);

                                                    acao = "edit";
                                                    Mensagem msg = new Mensagem();
                                                    Thread mensagemT = new Thread(msg);
                                                    mensagemT.start();

                                                    limpaCampos();
                                                    carregaDados();
                                                }
                                                
                                                
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Cidade Requerida!");
                                                cidadeFornecedor.requestFocus();
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Bairro Requerido!");
                                            bairroFornecedor.requestFocus();
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Número Requerido!");
                                        numeroFornecedor.requestFocus();
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Logradouro Requerido!");
                                    logradouroFornecedor.requestFocus();
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "CEP Requerido!");
                                cepFornecedor.requestFocus();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Telefone Requerido!");
                            telefoneFornecedor.requestFocus();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "DDD Requerido!");
                        dddFornecedor.requestFocus();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "CNPJ Requerido!");
                    documentoFornecedor.requestFocus();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "E-Mail Requerido!");
                emailFornecedor.requestFocus();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Nome Fantasia Requerido!");
            nomeFornecedor.requestFocus();
        }
        
    }//GEN-LAST:event_confirmFornecedorMouseClicked

    private void tabelaFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFornecedorMouseClicked
        preencheFormulario();
    }//GEN-LAST:event_tabelaFornecedorMouseClicked

    private void deleteFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteFornecedorMouseClicked
        PessoaDAO dao = new PessoaDAO();
        Long id = Long.parseLong(
            (String) tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0)
        );

        dao.removeById(id);
        acao = "delete";
        Mensagem msg = new Mensagem();
        Thread mensagem = new Thread(msg);
        mensagem.start();
        limpaCampos();
        carregaDados();
    }//GEN-LAST:event_deleteFornecedorMouseClicked

    private void imagemProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemProdutoMouseClicked
        JFileChooser arquivo = new JFileChooser();

        int retorno = arquivo.showOpenDialog(arquivo);
        

        if (retorno == JFileChooser.APPROVE_OPTION) {
            caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
            nomeArquivo = arquivo.getSelectedFile().getName();
            File selecionado = arquivo.getSelectedFile();

            String extensao = getExtensaoArquivo(selecionado);

//            if (!extensao.equals("png") || !extensao.equals("jpg")) {
//                    JOptionPane.showMessageDialog(null, "Arquivo Não Suportado!");
//                }
//            else{
                try {
                    BufferedImage original = ImageIO.read(new File(caminhoArquivo));
                    int tipoImagem = original.getType();
                    BufferedImage rImg = resizeImage(original, tipoImagem);
                    
//                    ImagemVIsualizacao.fundo = caminhoArquivo;
//                    JFrame preImg = new ImagemVIsualizacao();
//                    
//                    
//                    preImg.setVisible(true);
                    imagemProduto.setText(null);
                    imagemProduto.setIcon(new javax.swing.ImageIcon(original));
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
//            }

        }
    }//GEN-LAST:event_imagemProdutoMouseClicked

    private void refreshProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshProdutoMouseClicked
        limpaCampos();
        deleteProduto.setVisible(false);
        confirmProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
    }//GEN-LAST:event_refreshProdutoMouseClicked

    private void confirmProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmProdutoMouseClicked
          Validacao valida = new Validacao();
          
          if(valida.validaCodigoBarras(codigoBarrasProduto.getText())){
          if(valida.validaImagem(imagemProduto.getText())){
                  if(valida.validaTexto(descricaoProduto.getText())){
                        if(valida.validaPreco(precoProduto.getText())){
                            
                            
                            if(!deleteProduto.isVisible()){
                                FileInputStream origem;
                                FileOutputStream destino;
                                FileChannel fcOrigem;
                                FileChannel fcDestino;

                                /*Persistindo Produto*/
                                ProdutoDAO dao = new ProdutoDAO();

                                 try {

                                    URL resource = Principal.class.getResource("/produtos/");

                                    Produto objProduto = new Produto(
                                      codigoBarrasProduto.getText(), descricaoProduto.getText(), Integer.parseInt(quantidadeProduto.getValue().toString()),
                                      Double.parseDouble(precoProduto.getText().replace("R$", "").replace(",", ".")), (Paths.get(resource.toURI()).toFile()+ "/" +nomeArquivo));

                                    origem = new FileInputStream(caminhoArquivo);
                                    destino = new FileOutputStream( Paths.get(resource.toURI()).toFile()+ "/" +nomeArquivo);

                                    fcOrigem = origem.getChannel();
                                    fcDestino = destino.getChannel();

                                    fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);

                                    dao.persist(objProduto);

                                    origem.close();
                                    destino.close();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (URISyntaxException ex) {
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }



                                acao = "insert";
                                Mensagem msg = new Mensagem();
                                Thread mensagemT = new Thread(msg);
                                mensagemT.start();

                                limpaCampos();

                                carregaDados();
                            }
                            else{
                                /*Atualiza os Dados*/
                                ProdutoDAO dao = new ProdutoDAO();

                                Long id = Long.parseLong(
                                    (String) tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 0)
                                );

                                Produto produto = dao.getById(id);

                                
                                produto.setQuantidade(Integer.parseInt(quantidadeProduto.getValue().toString()));
                                produto.setValorUnitario(Double.parseDouble(precoProduto.getText().replace("R$", "").replace(",", ".")));
                                produto.setDescricao(descricaoProduto.getText());
                                
                                if(!caminhoArquivo.isEmpty()){
                                    FileInputStream origem;
                                    FileOutputStream destino;
                                    FileChannel fcOrigem;
                                    FileChannel fcDestino;
                                    
                                    File imagemAntiga = new File(produto.getImagem());
                                    imagemAntiga.delete();
                                    
                                    try {
                                        URL resource = Principal.class.getResource("/produtos/");
                                        origem = new FileInputStream(caminhoArquivo);
                                        JOptionPane.showMessageDialog(null, caminhoArquivo);
                                        JOptionPane.showMessageDialog(null, nomeArquivo);
                                        destino = new FileOutputStream( Paths.get(resource.toURI()).toFile()+ "/" +nomeArquivo);
                                        produto.setImagem((Paths.get(resource.toURI()).toFile()+ "/" +nomeArquivo));
                                        
                                        fcOrigem = origem.getChannel();
                                        fcDestino = destino.getChannel();

                                        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
                                        
                                        origem.close();
                                        destino.close();
                                       
                                    } 
                                    catch (FileNotFoundException ex) {
                                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                    } 
                                    catch (URISyntaxException ex) {
                                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                    } 
                                    catch (IOException ex) {
                                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                }
                                
                                
                                dao.merge(produto);

                                acao = "edit";
                                Mensagem msg = new Mensagem();
                                Thread mensagemT = new Thread(msg);
                                mensagemT.start();

                                limpaCampos();
                                carregaDados();
                            
                            }

                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Valor de compra requerido!");
                            precoProduto.requestFocus();
                        }
                  }
                  else{
                        JOptionPane.showMessageDialog(null,"Descrição requerida!");
                        descricaoProduto.requestFocus();
                        
                  }
              
          }
          else{
              JOptionPane.showMessageDialog(null,"Selecione uma imagem!");
              imagemProduto.requestFocus();
          }
          }
          else{
              JOptionPane.showMessageDialog(null, "Código de Barras Requerdo!");
              codigoBarrasProduto.requestFocus();
          }
    }//GEN-LAST:event_confirmProdutoMouseClicked

    private void imagemProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemProdutoMouseEntered
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
        setCursor(cursor);
    }//GEN-LAST:event_imagemProdutoMouseEntered

    private void imagemProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemProdutoMouseExited
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR); 
        setCursor(cursor);
    }//GEN-LAST:event_imagemProdutoMouseExited

    private void tabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoMouseClicked
        preencheFormulario();
    }//GEN-LAST:event_tabelaProdutoMouseClicked

    private void deleteProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteProdutoMouseClicked
        ProdutoDAO dao = new ProdutoDAO();
        Long id = Long.parseLong(
            (String) tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 0)
        );
        
        File imagemAntiga = new File(dao.getById(id).getImagem());
        imagemAntiga.delete();
        
        dao.removeById(id);
        acao = "delete";
        Mensagem msg = new Mensagem();
        Thread mensagem = new Thread(msg);
        mensagem.start();
        limpaCampos();
        carregaDados();
    }//GEN-LAST:event_deleteProdutoMouseClicked

    private void procuraProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_procuraProdutoMouseClicked
        ProdutoDAO dao = new ProdutoDAO();
        DecimalFormat df = new DecimalFormat("0.00");
        
        
        Long id = Long.parseLong(codigoProduto.getText());
        
        try{
           codigoProduto.setText(null);
            
            ((DefaultTableModel)tabelaVendaProduto.getModel()).addRow(new String[]{
                dao.getById(id).getDescricao(),
                df.format(dao.getById(id).getValorUnitario())
            });
            
            totalParcialVenda += dao.getById(id).getValorUnitario();
            
            totalVendaProduto.setText(totalParcialVenda.toString());
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Produto não encontrado");
            codigoProduto.setText(null);
        }
    }//GEN-LAST:event_procuraProdutoMouseClicked
    
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(200, 200, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, 200, 200, null);
	g.dispose();

	return resizedImage;
    }
    
    private static String getExtensaoArquivo(File file){
        String nomeArquivo = file.getName();
        if(nomeArquivo.lastIndexOf(".") != -1 && nomeArquivo.lastIndexOf(".") != 0){
            return nomeArquivo.substring(nomeArquivo.lastIndexOf(".")+1);
        }
        else {
            return "";
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> acessos;
    private javax.swing.JButton addTelCliente;
    private javax.swing.JButton addTelefone;
    private javax.swing.JButton addTelefoneFornecedor;
    private javax.swing.JTextField bairro;
    private javax.swing.JTextField bairroCliente;
    private javax.swing.JTextField bairroFornecedor;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnCompra;
    private javax.swing.JButton btnFornecedor;
    private javax.swing.JButton btnProduto;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JButton btnVenda;
    private javax.swing.JTextField cep;
    private javax.swing.JTextField cepCliente;
    private javax.swing.JTextField cepFornecedor;
    private javax.swing.JCheckBox checkSenha;
    private javax.swing.JTextField cidade;
    private javax.swing.JTextField cidadeCliente;
    private javax.swing.JTextField cidadeFornecedor;
    private javax.swing.JPanel clientes;
    private javax.swing.JFormattedTextField codigoBarrasProduto;
    private javax.swing.JTextField codigoProduto;
    private javax.swing.JPanel compras;
    private javax.swing.JButton confirm;
    private javax.swing.JButton confirmCliente;
    private javax.swing.JButton confirmFornecedor;
    private javax.swing.JButton confirmProduto;
    private javax.swing.JTextField dataNascimento;
    private javax.swing.JTextField dataNascimentoCliente;
    private javax.swing.JTextField ddd;
    private javax.swing.JTextField ddd2;
    private javax.swing.JTextField ddd2Cliente;
    private javax.swing.JTextField ddd2Fornecedor;
    private javax.swing.JTextField ddd3;
    private javax.swing.JTextField ddd3Cliente;
    private javax.swing.JTextField ddd3Fornecedor;
    private javax.swing.JTextField dddCliente;
    private javax.swing.JTextField dddFornecedor;
    private javax.swing.JButton delTel2;
    private javax.swing.JButton delTel2Cliente;
    private javax.swing.JButton delTel2Fornecedor;
    private javax.swing.JButton delTel3;
    private javax.swing.JButton delTel3Cliente;
    private javax.swing.JButton delTel3Fornecedor;
    private javax.swing.JButton delete;
    private javax.swing.JButton deleteCliente;
    private javax.swing.JButton deleteFornecedor;
    private javax.swing.JButton deleteProduto;
    private javax.swing.JTextArea descricaoProduto;
    private javax.swing.JTextField documento;
    private javax.swing.JTextField documentoCliente;
    private javax.swing.JTextField documentoFornecedor;
    private javax.swing.JLabel eagles;
    private javax.swing.JTextField email;
    private javax.swing.JTextField emailCliente;
    private javax.swing.JTextField emailFornecedor;
    private javax.swing.JComboBox<String> estados;
    private javax.swing.JComboBox<String> estadosCliente;
    private javax.swing.JComboBox<String> estadosFornecedor;
    private javax.swing.JPanel fornecedores;
    private javax.swing.JLabel imagemProduto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelTelefone2;
    private javax.swing.JLabel labelTelefone2Cliente;
    private javax.swing.JLabel labelTelefone2Fornecedor;
    private javax.swing.JLabel labelTelefone3;
    private javax.swing.JLabel labelTelefone3Cliente;
    private javax.swing.JLabel labelTelefone3Fornecedor;
    private javax.swing.JLabel logado;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField logradouro;
    private javax.swing.JTextField logradouroCliente;
    private javax.swing.JTextField logradouroFornecedor;
    private javax.swing.JPanel mainPanel;
    public static javax.swing.JLabel mensagem;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField nomeCliente;
    private javax.swing.JTextField nomeFornecedor;
    private javax.swing.JTextField numero;
    private javax.swing.JTextField numeroCliente;
    private javax.swing.JTextField numeroFornecedor;
    private javax.swing.JTextField precoProduto;
    private javax.swing.JButton procuraProduto;
    private javax.swing.JPanel produtos;
    private javax.swing.JSpinner quantidadeProduto;
    private javax.swing.JButton refreshCliente;
    private javax.swing.JButton refreshFornecedor;
    private javax.swing.JButton refreshProduto;
    private javax.swing.JPanel relatorios;
    private javax.swing.JPasswordField senha;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JComboBox<String> sexoCliente;
    private javax.swing.JTable tabelaCliente;
    private javax.swing.JTable tabelaFornecedor;
    private javax.swing.JTable tabelaProduto;
    private javax.swing.JTable tabelaUsuario;
    private javax.swing.JTable tabelaVendaProduto;
    private javax.swing.JTextField telefone;
    private javax.swing.JTextField telefone2;
    private javax.swing.JTextField telefone2Cliente;
    private javax.swing.JTextField telefone2Fornecedor;
    private javax.swing.JTextField telefone3;
    private javax.swing.JTextField telefone3Cliente;
    private javax.swing.JTextField telefone3Fornecedor;
    private javax.swing.JTextField telefoneCliente;
    private javax.swing.JTextField telefoneFornecedor;
    private javax.swing.JTextField totalVendaProduto;
    private javax.swing.JTextField usuario;
    private javax.swing.JPanel usuarios;
    private javax.swing.JPanel vendas;
    // End of variables declaration//GEN-END:variables
}
