# Primeira API RESTful feita com toda dedicação do mundo 


## CLASSES
1. UserOrderSimple - Classe de usuário
2. ShoppingCart - Classe de carrinho de compra
3. Product - Classe de produtos
4. Brand - Classe de marcas de produtos

## Tabelas que serão criadas no **banco de dados** de acordo com a ordem acima:
1. tb_user - Tabela de usuário e tem **associação com a tabela de carrinhos**
2. tb_shopping_cart - tabela de carrinho e tem **associação com a tabela de produtos**
3. tb_product - tabela de produtos e tem **associação como a tabela de carrinho e marca**
4. tb_brand - tabela de marcas e tem **associação com produtos**
5. tb_product_brand - tabela de associação de **produtos e marcas**

## MODO DE CRIAÇÃO

**Criando um usuário**
**URL:**

/api/user (POST)

**JSON:**
{

    "name": "Acerola e Paçoca",
    
    "dateTime":"2024-05-08T12:05:05"   
    
}

**Criando um ShoppingCart**

**URL:**

/api/shoppingCart/UserID/{idUser} (POST)

**Obs:** Associação de um **carrinho** e um **usuário** são feitos via **URL**, por esse motivo, não será necessário a criação via **JSON**

**Criando um produto**
**URL:**

/api/product (POST)

**JSON:**
{

    "name": "Teste....", 
    
    "description": "Teste 123",
    
    "quantity": 1,
    
    "value": 60.60,
    
    "link": "www.seila.com.br" 
    
}

**Criando uma marca**
**URL:**

/api/brand (POST)

**JSON:**
{

    "label": "Teste...."
    
}

## ASSOCIAÇÃO

Associação com **UserOrderSimple** e **ShoppingCart**
**URL:**

/api/shoppingCart/UserID/{idUser} (PUT)

Associação com **ShoppingCart** e **Products**
**URL:**

/api/shoppingCart/addProductCart (PUT)

**JSON:**
{

    "idCart": 1,
    
    "idProduct": 1
    
}

Associação de **Products** e **Brand**

**URL:**

/api/product/{idProduct}/brand/{idBrand} (PUT)

## UPDATE

**UserOrderSimple**
**URL:**
/api/user/updateUser/{idUser} (PUT)

**Products**

**URL:**

/api/product/update/{idProduct} (PUT)

**Brand**
**URL:**

/api/brand/{idBrand} (PUT)

## DELETE

**UserOrderSimple**

/api/user/deleteUser/{idUser} (DEL)

**OBS:** Quando você exclue o **usuário**, o **carrinho de compra e todos os seus produtos** são automaticamente excluídos. Ou seja, para cada **UM** usuário, só deverá ter **OBRIGATORIAMENTE** um carrinho associado a ele.

**ShoppingCart**
**URL:**

/api/shoppingCart/delete/cartId/{idCart}/product/{idProduct} (DEL)

**Products**
**URL:**

/api/product/deleteProduct/{idProduct} (DEL)
