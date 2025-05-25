import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.Messageable;
import org.javacord.api.entity.user.User;

import java.util.Locale;
import java.util.Optional;

public class Main
{

    public static void main(String[] args) {

        /*
        DiscordApi api = new DiscordApiBuilder().setToken("KEY").login().join();
        System.out.println(api.createBotInvite());
        */  // Comando para criação de link que adiciona o Bot à um servidor

        // Inicializar o bot (login)
        DiscordApi api = new DiscordApiBuilder()
                .setToken("KEY") // token gerado pelo discord pra cada bot
                .login().join();
                System.out.println(api.createBotInvite());


        // Listener de teste (responde !ping com !pong)
        api.addMessageCreateListener(event ->
        {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {

                System.out.println(event.getMessageAuthor());
                event.getChannel().sendMessage("Pong!");
            }
        });

        Mesa mesa = new Mesa();

        api.addMessageCreateListener
                (event ->
                        {
                            if (event.getMessageContent().equalsIgnoreCase("!iniciar")) {
                                event.getChannel().sendMessage(mesa.iniciar());
                            }

                            if (event.getMessageContent().equalsIgnoreCase("!entrar")) {
                                event.getChannel().sendMessage(mesa.adicionarJogador(event.getMessageAuthor().getName()));
                            }

                            if (event.getMessageContent().equalsIgnoreCase("!embaralhar")) {
                                event.getChannel().sendMessage(mesa.novaRodada());
                            }

                            if (event.getMessageContent().equalsIgnoreCase("!cartas")) {
                                event.getMessageAuthor().asUser().ifPresent(user -> user.sendMessage(mesa.verOpções(event.getMessageAuthor().getName())));
                                // event.getChannel().sendMessage(mesa.verOpções(event.getMessageAuthor().getName()));
                            }

                            if (event.getMessageContent().equalsIgnoreCase("!jogar 1") |
                                    event.getMessageContent().equalsIgnoreCase("!jogar 2") |
                                    event.getMessageContent().equalsIgnoreCase("!jogar 3") |
                                    event.getMessageContent().equalsIgnoreCase("!truco") |
                                    event.getMessageContent().equalsIgnoreCase("!f") |
                                    event.getMessageContent().equalsIgnoreCase("!cai")) {

                                event.getChannel().sendMessage(mesa.jogar(event.getMessageAuthor().getName(),
                                        event.getMessageContent()));
                            }

                            if (event.getMessageContent().equalsIgnoreCase("!pontos")) {
                                event.getChannel().sendMessage(mesa.getPontos());
                            }
                        }
                );
    }
}