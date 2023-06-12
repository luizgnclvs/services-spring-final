# Serviços - Projeto Final [Spring]

Projeto final da cadeira de Aplicações Orientadas a Serviços ofertada pela UNICAP em 2023.1. O projeto consiste na reformulação do Back-End previamente feito em [Node.js](https://github.com/luizgnclvs/servicos-final-project) agora utilizando Spring.

## Setup

### Requerimentos

- Java **17**
- Spring Boot **2.7.12**
- Maven **3.8.7**

É preciso também definir as seguintes variáveis locais: DB_URL, DB_USER e DB_PASS para conectar ao banco de dados.

### Lombok

Nosso projeto utiliza a lib do *Project Lombok* e portanto é necessário configurá-lo de antemão.
O **IntelliJ** tem um suporte melhor para o Lombok, por isso recomendamos utilizá-lo em favor do **Eclipse**. De qualquer modo, seguem as intruções para habilitar o Project Lombok nas duas IDEs:

#### IntelliJ

- O IntelliJ, por padrão, vem com um plugin do Lombok instalado e ativado. Para conferir basta seguir o caminho:

```
File > Settings > Plugins > Installed
```

- Caso o Lombok não esteja habilitado na sua IDE, é preciso apenas buscá-lo no Marketplace e fazer a a instalação:

```
File > Settings > Plugins > Marketplace
```

- Por fim, ao executar o projeto pela primeira vez, é possível que apareça um pop-up dizendo que o Lombok precisa do processamento de anotações habilitado. Basta confirmar e talvez você precise rodar o projeto novamente.

#### Eclipse

- No Eclipse o processo é um pouco mais complexo e demorado, mas seguindo o passo a passo abaixo você deve conseguir habilitar o Project Lombok na sua IDE:

```
Help > Install New Software
```

- No campo *Work With* coloque o link do Lombok: **<https://projectlombok.org/p2/>**
- Clique em *Add* e no campo *Name* escreva **Lombok**
- Após o carregamento, clique no quadro Lombok que foi gerado e avançe até chegar nos termos de uso
- Após ler os termos, aceite-os e finalize a instalação.
- É possível que apareça um pop-up perguntando se você confia no programa
- Salve e reinicie o Eclipse
- No navegador do projeto, vá em *Maven Dependencies*
- Procure o pacote do Lombok, clique com o botão direito e selecione a opção **Run as** e **Java Application**
- Instale o pacote no projeto
- Após finalizar a instalação, reinicie o Eclipse uma seguda vez
