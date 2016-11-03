package eagles.sabor_mel.view;


import eagles.sabor_mel.control.Mensagem;
import eagles.sabor_mel.dao.EstadoDAO;
import eagles.sabor_mel.dao.FuncionarioDAO;
import eagles.sabor_mel.dao.PessoaDAO;
import eagles.sabor_mel.dao.ProdutoDAO;
import eagles.sabor_mel.model.Estado;
import eagles.sabor_mel.model.Funcionario;
import eagles.sabor_mel.model.Pessoa;
import eagles.sabor_mel.model.Produto;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
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
        
        /*Produtos*/
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
//                listProdutos.get(i).getNome(),
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
        jLabel2 = new javax.swing.JLabel();
        nomeProduto = new javax.swing.JTextField();
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

        jLabel2.setText("Nome");

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
                                .addGap(18, 18, 18)
                                .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(produtosLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel54)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(quantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel55)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(precoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel56)
                                    .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(produtosLayout.createSequentialGroup()
                                            .addComponent(refreshProduto)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(confirmProduto)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(deleteProduto))
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel46))
                .addContainerGap(101, Short.MAX_VALUE))
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
                                    .addComponent(nomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54)
                                    .addComponent(quantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55)
                                    .addComponent(precoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addComponent(jLabel56)
                                .addGap(8, 8, 8)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deleteProduto, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(confirmProduto)
                                    .addComponent(refreshProduto))))))
                .addContainerGap(65, Short.MAX_VALUE))
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

//            nomeProduto.setText(produto.getNome());
            quantidadeProduto.setValue(produto.getQuantidade());
            precoProduto.setText(df.format(produto.getValorUnitario()).toString());
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
        
    }//GEN-LAST:event_delTel3MouseClicked

    private void delTel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel2MouseClicked
        

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
        
    }//GEN-LAST:event_delTel2ClienteMouseClicked

    private void delTel3ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel3ClienteMouseClicked
        
       
    }//GEN-LAST:event_delTel3ClienteMouseClicked

    private void refreshClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshClienteMouseClicked
        limpaCampos();
        deleteCliente.setVisible(false);
        confirmCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
    }//GEN-LAST:event_refreshClienteMouseClicked

    private void confirmClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmClienteMouseClicked
        
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
        
    }//GEN-LAST:event_delTel2FornecedorMouseClicked

    private void delTel3FornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delTel3FornecedorMouseClicked
       
    }//GEN-LAST:event_delTel3FornecedorMouseClicked

    private void refreshFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshFornecedorMouseClicked
        limpaCampos();
        deleteFornecedor.setVisible(false);
        confirmFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
    }//GEN-LAST:event_refreshFornecedorMouseClicked

    private void confirmFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmFornecedorMouseClicked
        
        
    }//GEN-LAST:event_confirmFornecedorMouseClicked

    private void tabelaFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFornecedorMouseClicked
        preencheFormulario();
    }//GEN-LAST:event_tabelaFornecedorMouseClicked

    private void deleteFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteFornecedorMouseClicked
        
    }//GEN-LAST:event_deleteFornecedorMouseClicked

    private void imagemProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemProdutoMouseClicked
        
    }//GEN-LAST:event_imagemProdutoMouseClicked

    private void refreshProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshProdutoMouseClicked
        limpaCampos();
        deleteProduto.setVisible(false);
        confirmProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked (1).png")));
    }//GEN-LAST:event_refreshProdutoMouseClicked

    private void confirmProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmProdutoMouseClicked
          
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
        
    }//GEN-LAST:event_deleteProdutoMouseClicked

    private void procuraProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_procuraProdutoMouseClicked
        
       
    }//GEN-LAST:event_procuraProdutoMouseClicked
    
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
    private javax.swing.JTextField nomeProduto;
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
