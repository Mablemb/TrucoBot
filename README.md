# TrucoBot

TrucoBot é um bot para Discord que permite jogar Truco (Paulista) em grupo, simulando uma mesa virtual com comandos interativos.

## Descrição

O bot gerencia uma mesa de Truco, permitindo que até 4 jogadores entrem, formem times e joguem partidas completas, incluindo embaralhamento, distribuição de cartas, contagem de pontos e comandos clássicos do jogo como pedir truco, aceitar ou correr.

## Funcionalidades

- Comandos para iniciar mesa, entrar, embaralhar, jogar cartas, pedir truco, aceitar ou correr.
- Distribuição automática de cartas e controle de rodadas.
- Contagem de pontos por time.
- Mensagens privadas para mostrar cartas na mão do jogador.
- Implementação das regras básicas do Truco Paulista.

## Tecnologias Utilizadas

- **Java 17**
- **Maven** para gerenciamento de dependências
- **Javacord** (API para bots Discord)
- **Log4j** para logging
- **JetBrains Annotations** (opcional)

## Estrutura do Projeto

```
src/
  main/
    java/
      Carta.java
      Deque.java
      Jogador.java
      Main.java
      Mesa.java
  resources/
test/
  java/
pom.xml
```

## Instalação

1. **Clone o repositório:**
   ```powershell
   git clone <url-do-repositorio>
   cd TrucoBot
   ```

2. **Configure o token do seu bot Discord:**
   - No arquivo `Main.java`, substitua `"KEY"` pelo token do seu bot.

3. **Compile o projeto:**
   ```powershell
   mvn clean package
   ```

4. **Execute o bot:**
   ```powershell
   mvn exec:java -Dexec.mainClass="Main"
   ```

## Como Jogar

No Discord, em um canal de texto onde o bot está presente, utilize os comandos:

- `!iniciar` — Inicia uma nova mesa.
- `!entrar` — Entra na mesa (até 4 jogadores).
- `!embaralhar` — Começa uma nova rodada.
- `!cartas` — Recebe suas cartas por mensagem privada.
- `!jogar 1`, `!jogar 2`, `!jogar 3` — Joga a carta correspondente.
- `!truco` — Pede truco.
- `!cai` — Aceita o truco.
- `!f` — Corre da rodada.
- `!pontos` — Mostra a pontuação dos times.

## Testes

O projeto ainda não possui testes automatizados. Para testar, execute o bot e interaja via Discord.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## Licença

Este projeto está sob a licença MIT.
