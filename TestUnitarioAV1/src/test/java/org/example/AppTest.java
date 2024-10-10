package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    @Test
    public void testCriarProduto(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        assertEquals("Arroz", produto.getNome());
        assertEquals(4.5f, produto.getPreco());
        assertEquals(20, produto.getEstoque());
    }

    @Test
    public void testPrecoNegativo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Arroz", -4.5f, 20);
        });
    }

    @Test
    public void testProdutoEstoqueNegativo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Arroz", 4.5f, -20);}
        );
    }

    @Test
    public void testAlterarNome(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        produto.setNome("Arroz Branco");
        assertEquals("Arroz Branco", produto.getNome());
    }

    @Test
    public void testAlterarPreco(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        produto.setPreco(7.5f);
        assertEquals(7.5f, produto.getPreco());
    }

    @Test
    public void testAlterarEstoque(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        produto.setEstoque(14);
        assertEquals(14, produto.getEstoque());
    }

    @Test
    public void testAlterarProdutoPrecoNegativo(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        produto.setPreco(-4.5f);
        assertEquals(-4.5f, produto.getPreco());
    }

    @Test
    public void testVendaQuantidadeMenorEstoque(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Venda venda = new org.example.Venda(produto,5);
        assertTrue(venda.realizarVenda());
        assertEquals(15, produto.getEstoque());
    }

    @Test
    public void testVendaIgualEstoque(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Venda venda = new org.example.Venda(produto,20);
        assertTrue(venda.realizarVenda());
        assertEquals(0, produto.getEstoque());
    }

    @Test
    public void testVendaMaiorEstoque(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Venda venda = new org.example.Venda(produto,25);
        assertFalse(venda.realizarVenda());
        assertEquals(20, produto.getEstoque());
    }

    @Test
    public void testTotalVenda(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Venda venda = new org.example.Venda(produto,5);
        assertTrue(venda.realizarVenda());
        assertEquals(22.5f, venda.getTotalVenda());
    }

    @Test
    public void testAumentarEstoqueAposVenda(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        produto.aumentarEstoque(20);
        assertEquals(40, produto.getEstoque());
    }

    @Test
    public void testDiminuirEstoqueAposVenda(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Venda venda = new org.example.Venda(produto, 5);
        venda.realizarVenda();
        assertEquals(15, produto.getEstoque());
    }

    @Test
    public void testVendaProdutoNaoExiste(){
        org.example.Produto produto = null;
        assertThrows(NullPointerException.class, () ->{
            org.example.Venda venda = new org.example.Venda(produto, 5);
            venda.realizarVenda();
        });
    }

    @Test
    public void testVendaQuantidadeNegativa(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Venda venda = new org.example.Venda(produto, -5);
        assertFalse(venda.realizarVenda());
    }

    @Test
    public void testAlterarEstoqueVendaInsulficiente(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Venda venda = new org.example.Venda(produto, 25);
        venda.realizarVenda();
        assertEquals(20, produto.getEstoque());
    }

    @Test
    public void testCriarVariosProdutosEVendasEstoqueCompartilhado(){
        org.example.Produto produto1 = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Produto produto2 = new org.example.Produto("Feijao", 6.5f, 40);

        org.example.Venda venda1 = new org.example.Venda(produto1, 5);
        org.example.Venda venda2 = new org.example.Venda(produto2, 10);

        venda1.realizarVenda();
        venda2.realizarVenda();

        assertEquals(15, produto1.getEstoque());
        assertEquals(30, produto2.getEstoque());
    }

    @Test
    public void testCalculoTotalVendaPrecoAlteradoAntes(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        produto.setPreco(5.5f);

        org.example.Venda venda = new org.example.Venda(produto, 5);
        venda.realizarVenda();

        assertEquals(27.5f, venda.getTotalVenda());
    }

    @Test
    public void testVendaComEstoqueZero(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 20);
        org.example.Venda venda = new org.example.Venda(produto, 5);
        assertFalse(venda.realizarVenda());
    }


    @Test
    public void testAumentoEstoqueAposReposicao(){
        org.example.Produto produto = new org.example.Produto("Arroz", 4.5f, 0);
        produto.aumentarEstoque(30);
        org.example.Venda venda = new org.example.Venda(produto, 5);
        assertTrue(venda.realizarVenda());
        assertEquals(25, produto.getEstoque());
    }
}
