package pt.ipbeja.po2.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispenserTest {

    @Test
    void testInsertCoin() {
        // Teste para verificar a inserção de moedas
        Dispenser dispenser = new Dispenser(0);
        int res = dispenser.insertCoin(20);
        assertEquals(20, res);

        res = dispenser.insertCoin(30);
        assertEquals(50, res);
    }

    @Test
    void testCancel() {
        // Teste para verificar o cancelamento da compra
        Dispenser dispenser = new Dispenser(0);
        dispenser.insertCoin(20);
        int refundedMoney = dispenser.cancel();
        assertEquals(20, refundedMoney);
    }

    @Test
    void testBuyProduct() {
        // Teste para verificar a compra de um produto
        Dispenser dispenser = new Dispenser(40);
        dispenser.insertCoin(50);
        int change = dispenser.buyProduct();
        assertEquals(10, change);
    }

    @Test
    void testSetProductPrice() {
        // Teste para verificar a definição do preço do produto
        Dispenser dispenser = new Dispenser(0);
        dispenser.setProductPrice(50);
        assertEquals(50, dispenser.getProductPrice());
    }

    @Test
    void testSetProductQuantity() {
        // Teste para verificar a definição da quantidade de produtos
        Dispenser dispenser = new Dispenser(0);
        dispenser.setProductQuantity(20);
        assertEquals(20, dispenser.getProductQuantity());
    }

    @Test
    void testGetProductQuantity() {
        // Teste para verificar a obtenção da quantidade de produtos
        Dispenser dispenser = new Dispenser(0);
        dispenser.setProductQuantity(15);
        assertEquals(15, dispenser.getProductQuantity());
    }

    @Test
    void testGetSalesMoney() {
        // Teste para verificar a obtenção do dinheiro de vendas
        Dispenser dispenser = new Dispenser(0);
        dispenser.insertCoin(50);
        dispenser.buyProduct();
        dispenser.insertCoin(30);
        dispenser.buyProduct();
        assertEquals(40, dispenser.getSalesMoney());
    }

    @Test
    void testChangeProductPriceToMultipleOfTen() {
        // Teste para verificar a alteração do preço para múltiplo de 10
        Dispenser dispenser = new Dispenser(0);
        dispenser.changeProductPriceToMultipleOfTen(35);
        assertEquals(0, dispenser.getProductPrice());

        dispenser.changeProductPriceToMultipleOfTen(30);
        assertEquals(30, dispenser.getProductPrice());
    }

    @Test
    void testBuyMultipleProducts() {
        // Teste para verificar a compra de múltiplos produtos
        Dispenser dispenser = new Dispenser(40);
        dispenser.insertCoin(100);
        int change = dispenser.buyMultipleProducts(2);
        assertEquals(60, change);
        assertEquals(8, dispenser.getProductQuantity());
    }

    // Testes para as funcionalidades extras 3 e 4
    @Test
    void testInsertCoinDifferentValues() {
        // Teste para verificar a inserção de moedas de diferentes valores
        Dispenser dispenser = new Dispenser(0);
        dispenser.insertCoin(50);
        dispenser.insertCoin(20);
        dispenser.insertCoin(10);
        dispenser.insertCoin(5);
        assertEquals(85, dispenser.getInsertedMoney());
    }

    @Test
    void testGiveChange() {
        // Teste para verificar a devolução de troco
        Dispenser dispenser = new Dispenser(40);
        dispenser.insertCoin(100);
        int[] change = dispenser.giveChange(60);
        int[] expectedChange = {0, 2, 0, 0}; // Espera-se 2 moedas de 20
        assertArrayEquals(expectedChange, change);
    }
}
