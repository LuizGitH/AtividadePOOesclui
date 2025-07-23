/**
 * Exceção personalizada para quando uma data é inválida (nula ou no passado).
 */
public class TratamentoDataInvalida extends Exception { /* Cria uma classe que herda a classe Exception, que é uma classe do Java que representa exceções verificadas*/
    public TratamentoDataInvalida(String message) { /*Define um construtor público*/
        super(message); /*Chama o construtor da classe mãe, que nesse caso é Exception, passando a mensagem recebida, retornado a mensagem*/
    }
}

/*CONFERI*/