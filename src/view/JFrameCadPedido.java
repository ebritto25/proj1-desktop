/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClienteDAO;
import controller.ItensPedidoDAO;
import controller.PedidoDAO;
import controller.ProdutoDAO;
import controller.TipoPagamentoDAO;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.ItensPedido;
import model.Pedido;
import model.Produto;
import model.TipoPagamento;
import utils.Mode;

/**
 *
 * @author rodrigo
 */
public class JFrameCadPedido extends javax.swing.JFrame {

    /**
     * Creates new form JFrameCadPedido
     */
    
    private Cliente cliente;
    private Map<String,TipoPagamento> formaPgto;
    private ArrayList<Pair<Produto,Integer>> itens;
    
    private Pedido atual;
    private Mode mode;
    
    public JFrameCadPedido(Pedido pedido, Mode _mode)
    {
        mode = _mode;
        atual = pedido;
        itens = new ArrayList<Pair<Produto,Integer>>();
        
        initComponents();

        
        
        fillFields();
        
        if( mode == Mode.DELETE )
            disableFields();
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public JFrameCadPedido() {
        formaPgto = new HashMap<String,TipoPagamento>();
        
        itens = new ArrayList<Pair<Produto,Integer>>();

        initComponents();
        
        txtID.setVisible(false);
        lbID.setVisible(false);
        
        fillCombo();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lbCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        lbFormaPgto = new javax.swing.JLabel();
        lbProduto = new javax.swing.JLabel();
        txtProduto = new javax.swing.JTextField();
        btnBuscarProduto = new javax.swing.JButton();
        lbProdutos = new javax.swing.JLabel();
        lbDesconto = new javax.swing.JLabel();
        txtDesconto = new javax.swing.JTextField();
        lbTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTroco = new javax.swing.JTextField();
        jComboFormaPgto = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbID.setText("ID:");

        lbCliente.setText("Cliente:");

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        lbFormaPgto.setText("Forma de Pagamento:");

        lbProduto.setText("Produto:");

        btnBuscarProduto.setText("Buscar");
        btnBuscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdutoActionPerformed(evt);
            }
        });

        lbProdutos.setText("Produtos:");

        lbDesconto.setText("Desconto:");

        lbTotal.setText("Total:");

        jLabel8.setText("Troco:");

        jComboFormaPgto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSalvar.setText("Registrar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Quantidade"
            }
        ));
        jScrollPane2.setViewportView(jTableProdutos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbFormaPgto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboFormaPgto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lbProduto)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbCliente)
                                        .addComponent(lbProdutos, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(lbID)
                                    .addComponent(lbTotal)
                                    .addComponent(lbDesconto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProduto)
                                    .addComponent(txtCliente)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTroco, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                        .addGap(15, 15, 15))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(110, 110, 110))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarCliente)
                            .addComponent(btnBuscarProduto)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbProduto)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProduto))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(lbProdutos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotal))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDesconto)
                    .addComponent(txtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFormaPgto)
                    .addComponent(jComboFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        
        if(fieldsEmpty())
        {
            JOptionPane.showMessageDialog(this,"Preencha todos os campos para o registro do pedido!", "Campos Vazio!", 0);
        }
        else 
        {
            ArrayList<Pedido> list = PedidoDAO.queryAll();
            
            int id = 0;
            if( !list.isEmpty() )
                id = list.get( list.size() - 1 ).getId();
            
            Date data = new Date();        
            
            
            Double subTotal = Double.parseDouble(txtTotal.getText()) - Double.parseDouble(txtDesconto.getText());
            
            Pedido p = new Pedido(id,data,cliente,Double.parseDouble(txtTotal.getText()),Double.parseDouble(txtDesconto.getText()),
            formaPgto.get(jComboFormaPgto.getSelectedItem()),Double.parseDouble(txtTroco.getText()),subTotal);
            
            PedidoDAO.insert(p);
            
            
            for(Pair<Produto,Integer> item : itens)
                ItensPedidoDAO.insert(new ItensPedido(item.getKey(),p,item.getValue()));
            
            
            JOptionPane.showMessageDialog(this,"Pedido Registrado com Sucesso!", "Registro de Pedido", 1);

        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        
        if( txtCliente.getText().equals("") )
        {
           JOptionPane.showMessageDialog(this,"Nenhum cliente informado!", "Campos Vazio!", 0);
        }
        else
        {
            ArrayList<Cliente> clientes = ClienteDAO.queryByNome(txtCliente.getText());
           
            if(!clientes.isEmpty())
            {
                Map<Integer,Cliente> clis = new HashMap<Integer,Cliente>();

                String col[] =  {"ID","Nome","Endereço","Telefone"};

                DefaultTableModel tableModel = new DefaultTableModel(col,0);


                jDialogTabela janela = new jDialogTabela(this,true,tableModel);


                for(Cliente cliente : clientes )
                {
                    clis.put(cliente.getId(), cliente);

                    Object[] data = {cliente.getId(),cliente.getNome(),
                        cliente.getEndereco(),cliente.getTelefone()};


                    tableModel.addRow(data);
                }



                int id = -1;

                id = janela.showWindow();

                if(id != -1)
                {
                    cliente = clis.get(id);

                    txtCliente.setText(cliente.getNome());
                }
                else
                    JOptionPane.showMessageDialog(rootPane,"Nenhum Cliente Selecionado!");

            }
            else
                JOptionPane.showMessageDialog(rootPane, "Cliente Não encontrado!");

        }
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdutoActionPerformed
        if( txtProduto.getText().equals("") )
        {
           JOptionPane.showMessageDialog(this,"Nenhum produto informado!", "Campos Vazio!", 0);
        }
        else
        {
            ArrayList<Produto> list = ProdutoDAO.queryByDescricao(txtProduto.getText());
            
            
            if(!list.isEmpty())
            {
                Map<Integer,Produto> produtos = new HashMap<Integer,Produto>();

                String col[] =  {"ID","Descrição","Preço"};

                DefaultTableModel tableModel = new DefaultTableModel(col,0);

                jDialogTabela janela = new jDialogTabela(this,true,tableModel);

                for(Produto pro : list)
                {
                    produtos.put(pro.getId(), pro);

                    Object[] data = {pro.getId(),pro.getDescricao(),pro.getPreco()};

                    tableModel.addRow(data);
                }


                int id = -1;
                id = janela.showWindow();

                if(id != -1)
                {
                    Produto pro = produtos.get(id);
                    
                    int qtd = 0;
                    
                    while(qtd == 0)
                        qtd = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade:"));

                    itens.add(new Pair<Produto,Integer>(pro,qtd));


                    DefaultTableModel dm = (DefaultTableModel) jTableProdutos.getModel();

                    Object[] data = {pro.getDescricao(),qtd};

                    dm.addRow(data);
                }
                else
                    JOptionPane.showMessageDialog(rootPane,"Nenhum Produto Selecionado!");

            }
            else
                JOptionPane.showMessageDialog(rootPane, "Produto Não encontrado!");
 
            
            
        }
    }//GEN-LAST:event_btnBuscarProdutoActionPerformed

    private boolean fieldsEmpty()
    {
        if(txtCliente.getText().equals("") || txtDesconto.getText().equals("") || txtProduto.getText().equals("") ||
            txtTotal.getText().equals("") || txtTroco.getText().equals("") || jTableProdutos.equals(null))
            return true;
        else
            return false;
    }
    
    
    
    private void fillCombo()
    {
        ArrayList<TipoPagamento> formas = TipoPagamentoDAO.queryAll();
        
        if(!formas.isEmpty())
        {
            for(TipoPagamento forma : formas)
                formaPgto.put(forma.getDescricao(), forma);
            
            
            jComboFormaPgto.setModel(new DefaultComboBoxModel(formaPgto.keySet().toArray()));
        }
        
        
    }
    
    
    private void fillFields()
    {
        txtID.setText(Integer.toString(atual.getId()));
        txtCliente.setText(atual.getIdCliente().getNome());
        txtDesconto.setText(Double.toString(atual.getDesconto()));
        txtTotal.setText(Double.toString(atual.getTotal()));
        txtTroco.setText(Double.toString(atual.getTroco()));
    }
    
    private void disableFields()
    {
        txtID.setEnabled(false);
        txtCliente.setEnabled(false);
        txtDesconto.setEnabled(false);
        txtTotal.setEnabled(false);
        txtTroco.setEnabled(false);
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
            java.util.logging.Logger.getLogger(JFrameCadPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameCadPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameCadPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameCadPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameCadPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarProduto;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> jComboFormaPgto;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JLabel lbCliente;
    private javax.swing.JLabel lbDesconto;
    private javax.swing.JLabel lbFormaPgto;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbProduto;
    private javax.swing.JLabel lbProdutos;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDesconto;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTroco;
    // End of variables declaration//GEN-END:variables
}
