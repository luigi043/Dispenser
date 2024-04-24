package pt.ipbeja.po2.app.model;

import java.util.HashMap;
import java.util.Map;

public class Dispenser {

    private  int insertedMoney;
    private  int productPrice;
    private  int productQuantity; // Adicionando a variável productQuantity
    private  int salesMoney;
    private final int[] coinValues = {50, 20, 10, 5};
    private final Map<Integer, Integer> coins = new HashMap<>();

    public Dispenser(int productPrice) {
        this.insertedMoney = 0;
        this.productPrice = productPrice;
        this.productQuantity = 0; // Inicializando productQuantity como 0
        this.salesMoney = 0;
    }

    // Método para cancelar a compra e obter o dinheiro inserido de volta
    public int cancel() {
        int refundedMoney = this.insertedMoney;
        this.insertedMoney = 0;
        return refundedMoney;
    }

    // Método para comprar um produto
    public int buyProduct() {
        if (this.insertedMoney >= this.productPrice) {
            int change = this.insertedMoney - this.productPrice;
            this.insertedMoney = 0;
            return change;
        } else {
            return -1; // Retornar um valor negativo para indicar fundos insuficientes
        }
    }

    public void setProductPrice(int price) {
        this.productPrice = price;
    }

    public void setProductQuantity(int quantity) {
        this.productQuantity = quantity;
    }

    public int getProductQuantity() {
        return this.productQuantity;
    }

    public int getSalesMoney() {
        return this.salesMoney;
    }

    public void changeProductPriceToMultipleOfTen(int newPrice) {
        if (newPrice % 10 == 0) {
            this.productPrice = newPrice;
        }
    }

    public int buyMultipleProducts(int quantity) {
        int totalPrice = this.productPrice * quantity;
        if (this.insertedMoney >= totalPrice && this.productQuantity >= quantity) {
            int change = this.insertedMoney - totalPrice;
            this.insertedMoney = 0;
            this.productQuantity -= quantity;
            this.salesMoney += totalPrice;
            return change;
        } else {
            return -1; // Retorna -1 se não houver dinheiro suficiente ou produtos disponíveis
        }
    }

    public int insertCoin(int coinValue) {
        switch (coinValue) {
            case 5:
            case 10:
            case 20:
            case 50:
                this.coins.put(coinValue, this.coins.getOrDefault(coinValue, 0) + 1);
                this.insertedMoney += coinValue;
                break;
            default:
                System.out.println("Invalid coin value");
                break;
        }
        return coinValue;
    }

    public int[] giveChange(int change) {
        int[] coinsToGive = new int[4]; // Índice 0 para moedas de 5, índice 1 para moedas de 10, e assim por diante
        int remainingChange = change;
        for (int i = 3; i >= 0; i--) {
            int coinValue = this.coinValues[i];
            int numCoins = Math.min(remainingChange / coinValue, this.coins.getOrDefault(coinValue, 0));
            coinsToGive[i] = numCoins;
            remainingChange -= numCoins * coinValue;
        }
        if (remainingChange != 0) {
            System.out.println("Não há troco suficiente.");
            return null;
        }
        for (int i = 3; i >= 0; i--) {
            int coinValue = this.coinValues[i];
            this.coins.put(coinValue, this.coins.getOrDefault(coinValue, 0) - coinsToGive[i]);
        }
        return coinsToGive;
    }


    public int getProductPrice() {
        return productPrice;
    }

    public int getInsertedMoney() {
        return insertedMoney;
    }
}
