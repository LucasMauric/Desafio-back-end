``` mermaid
erDiagram
    User {
        Long id
        String username
        String password
        String cpfCnpj
        String email
    }
    MoneyWallet {
        Long id
        Float saldo
    }
    User ||--|| MoneyWallet : "has one"



```
